package com.example.ble

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.bluetooth.BluetoothStatusCodes
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import android.os.Build
import android.os.ParcelUuid
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.UUID

@SuppressLint("MissingPermission")
class BleManager(private val context: Context) {

    companion object {
        const val TAG = "BleManager"

        val SERVICE_UUID: UUID = UUID.fromString("CA113726-49D2-4D7F-A5A0-000000000001")
        val RX_CHAR_UUID: UUID = UUID.fromString("CA113726-49D2-4D7F-A5A0-000000000002")
        val TX_CHAR_UUID: UUID = UUID.fromString("CA113726-49D2-4D7F-A5A0-000000000003")
        val CCCD_UUID: UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")

        @Volatile
        private var INSTANCE: BleManager? = null

        fun getInstance(context: Context): BleManager {
            return INSTANCE ?: synchronized(this) {
                val instance = BleManager(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }

    data class ScannedDevice(
        val name: String,
        val address: String,
        val state: DeviceState,
        val rssi: Int,
        val hasServiceUuid: Boolean = false,
        val lastSeenTimestamp: Long = System.currentTimeMillis()
    )

    sealed class ConnectionState {
        object Disconnected : ConnectionState()
        object Scanning : ConnectionState()
        object Connecting : ConnectionState()
        object Disconnecting : ConnectionState()
        data class Connected(val deviceName: String, val address: String, val state: DeviceState) : ConnectionState()
        data class Error(val message: String) : ConnectionState()
    }

    private val bluetoothManager: BluetoothManager? =
        context.getSystemService(Context.BLUETOOTH_SERVICE) as? BluetoothManager
    private val bluetoothAdapter: BluetoothAdapter? = bluetoothManager?.adapter

        data class DebugLog(val timestamp: Long, val message: String, val isError: Boolean = false, val isRx: Boolean = false, val isTx: Boolean = false)

    private val _debugLogs = MutableStateFlow<List<DebugLog>>(emptyList())
    val debugLogs: StateFlow<List<DebugLog>> = _debugLogs.asStateFlow()

    private val _currentRssi = MutableStateFlow<Int>(0)
    val currentRssi: StateFlow<Int> = _currentRssi.asStateFlow()

    fun addDebugLog(message: String, isError: Boolean = false, isRx: Boolean = false, isTx: Boolean = false) {
        val log = DebugLog(System.currentTimeMillis(), message, isError, isRx, isTx)
        _debugLogs.value = (_debugLogs.value + log).takeLast(1000)
    }

    fun requestRssi() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
            bluetoothGatt?.readRemoteRssi()
        }
    }
    
    fun sendRawPacket(payload: String) {
        val gatt = bluetoothGatt
        val rx = rxCharacteristic
        if (gatt == null || rx == null) {
            val target = lastTargetAddress
            if (target != null) {
                addDebugLog("Not connected. Connecting to $target and queuing packet...", isError = false)
                queuedRawPacket = payload
                connect(target)
                return
            } else {
                addDebugLog("Cannot send: Not connected or RX missing and no target address known", isError = true)
                return
            }
        }
        addDebugLog("Sent raw to RX: $payload", isTx = true)
        val writeType = if ((rx.properties and BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE) != 0) {
            BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
        } else {
            BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            gatt.writeCharacteristic(rx, payload.toByteArray(Charsets.UTF_8), writeType)
        } else {
            rx.value = payload.toByteArray(Charsets.UTF_8)
            rx.writeType = writeType
            gatt.writeCharacteristic(rx)
        }
    }

    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private val _deviceState = MutableStateFlow<DeviceState>(DeviceState.FACTORY_MODE)
    val deviceState: StateFlow<DeviceState> = _deviceState.asStateFlow()

    private val _scannedDevices = MutableStateFlow<List<ScannedDevice>>(emptyList())
    val scannedDevices: StateFlow<List<ScannedDevice>> = _scannedDevices.asStateFlow()

    private val _incomingPackets = MutableSharedFlow<BlePacket>()
    val incomingPackets: SharedFlow<BlePacket> = _incomingPackets.asSharedFlow()

    private val _pendingAction = MutableStateFlow<String?>(null)
    val pendingAction: StateFlow<String?> = _pendingAction.asStateFlow()

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    fun setAuthenticated(auth: Boolean) {
        _isAuthenticated.value = auth
        CalisBleService.updateNotification(context)
    }

    fun setPendingAction(action: String?) {
        _pendingAction.value = action
    }

    private val _lastTxResponse = MutableStateFlow("")
    val lastTxResponse: StateFlow<String> = _lastTxResponse.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO + Job())

    private var bluetoothGatt: BluetoothGatt? = null
    private var rxCharacteristic: BluetoothGattCharacteristic? = null

    // Simulation / Virtual Bridge State for non-hardware environment testing
    var isSimulatedMode = false
        private set
    var isExplicitlyDisconnected = false
        set(value) {
            field = value
            if (value) {
                _isAuthenticated.value = false
            }
        }

    private var virtualClassroomName = "Calis Classroom"
    private var virtualTeacherName = "Teacher Account"
    private var virtualTid = ""
    private var virtualCid = ""

    fun isBluetoothSupported(): Boolean = bluetoothAdapter != null

    fun isBluetoothEnabled(): Boolean = isSimulatedMode || (bluetoothAdapter?.isEnabled == true)

    private var isScanning = false

    /**
     * Start scanning for Calis Devices advertising Service UUID or Manufacturer Data
     */
    @SuppressLint("MissingPermission")
    fun startScan() {
        if (isSimulatedMode) {
            enableVirtualMode()
            return
        }

        if (bluetoothAdapter == null) {
            enableVirtualMode()
            return
        }

        if (!bluetoothAdapter.isEnabled) {
            Log.d(TAG, "Bluetooth is disabled. Cannot start scan.")
            _connectionState.value = ConnectionState.Disconnected
            return
        }

        if (isScanning && _connectionState.value is ConnectionState.Scanning) {
            Log.d(TAG, "Scan is already active. Preserving continuous scan.")
            return
        }

        try {
            val scanner: BluetoothLeScanner? = bluetoothAdapter.bluetoothLeScanner
            if (scanner == null) {
                return
            }

            _connectionState.value = ConnectionState.Scanning
            isScanning = true
            val now = System.currentTimeMillis()
            _scannedDevices.value = _scannedDevices.value.filter { now - it.lastSeenTimestamp <= 60000L }

            val settings = ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .build()

            scanner.startScan(null, settings, scanCallback)
            Log.d(TAG, "Started continuous BLE scan with SCAN_MODE_LOW_LATENCY")
        } catch (e: SecurityException) {
            Log.e(TAG, "Scan failed due to missing permission", e)
            isScanning = false
            _connectionState.value = ConnectionState.Disconnected
        } catch (e: Exception) {
            Log.e(TAG, "Scan failed", e)
            isScanning = false
            _connectionState.value = ConnectionState.Disconnected
        }
    }

    @SuppressLint("MissingPermission")
    fun stopScan() {
        isScanning = false
        try {
            bluetoothAdapter?.bluetoothLeScanner?.stopScan(scanCallback)
            if (_connectionState.value is ConnectionState.Scanning) {
                _connectionState.value = ConnectionState.Disconnected
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error stopping scan", e)
        }
    }

    private val scanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            result?.let {
                val hasService = it.scanRecord?.serviceUuids?.contains(ParcelUuid(SERVICE_UUID)) == true
                val rawName = it.scanRecord?.deviceName ?: it.device.name ?: ""
                var name = if (rawName.isBlank()) "Unknown Device" else rawName
                val address = it.device.address

                val isSetupCalis = name.contains("Setup Calis", ignoreCase = true)
                val isCalisName = name.contains("Calis", ignoreCase = true) || name.contains("Classroom", ignoreCase = true)
                val mData = it.scanRecord?.manufacturerSpecificData
                val rawBytes = it.scanRecord?.bytes
                val hasMData = (mData != null && mData.size() > 0)
                val hasRawMData = rawBytes != null && hasManufacturerDataInBytes(rawBytes)

                if (!hasService && !isSetupCalis && !isCalisName && !hasMData && !hasRawMData && rawName.isBlank()) {
                    return
                }

                var state = DeviceState.UNKNOWN
                var mDataHex = "None"

                // 1. High-priority check for active session 0x0111 (Busy) and 0x0011 (Open)
                // in manufacturer data to avoid any masking by 0xFFFF (Bluetooth SIG testing company ID)
                if (mData != null) {
                    for (i in 0 until mData.size()) {
                        val key = mData.keyAt(i)
                        val bytes = mData.valueAt(i) ?: byteArrayOf()
                        val hexKey = String.format("%04X", key)

                        if (key == 0x0111 || key == 0x1101 || hexKey == "0111" || hexKey == "1101") {
                            state = DeviceState.SESSION_ACTIVE_BUSY
                            mDataHex = hexKey
                            break
                        }
                        if (key == 0x0011 || key == 0x1100 || hexKey == "0011" || hexKey == "1100") {
                            state = DeviceState.SESSION_ACTIVE_OPEN
                            mDataHex = hexKey
                            break
                        }

                        val st2 = DeviceState.fromBytes(bytes)
                        if (st2 == DeviceState.SESSION_ACTIVE_BUSY || st2 == DeviceState.SESSION_ACTIVE_OPEN) {
                            state = st2
                            mDataHex = bytes.joinToString("") { b -> String.format("%02X", b) }
                            break
                        }
                    }
                }

                // Check raw advertisement bytes for 0x0011 / 0x0111 status byte in manufacturer data (Type 0xFF)
                if (state == DeviceState.UNKNOWN && rawBytes != null && rawBytes.isNotEmpty()) {
                    var idx = 0
                    while (idx < rawBytes.size - 1) {
                        val length = rawBytes[idx].toInt() and 0xFF
                        if (length == 0 || idx + length >= rawBytes.size) break
                        val type = rawBytes[idx + 1].toInt() and 0xFF
                        if (type == 0xFF && length >= 2) {
                            val mBytes = rawBytes.copyOfRange(idx + 2, idx + 1 + length)
                            val st = DeviceState.fromBytes(mBytes)
                            if (st == DeviceState.SESSION_ACTIVE_BUSY || st == DeviceState.SESSION_ACTIVE_OPEN) {
                                state = st
                                mDataHex = mBytes.joinToString("") { b -> String.format("%02X", b) }
                                break
                            }
                        }
                        idx += length + 1
                    }
                }

                // 2. Secondary check for all other device states (Factory mode, registering, authenticate, inactive)
                if (state == DeviceState.UNKNOWN && mData != null) {
                    for (i in 0 until mData.size()) {
                        val key = mData.keyAt(i)
                        val hex = String.format("%04X", key)
                        val st = DeviceState.fromHex(hex)
                        if (st != DeviceState.UNKNOWN) {
                            state = st
                            mDataHex = hex
                            break
                        }

                        val bytes = mData.valueAt(i)
                        val st2 = DeviceState.fromBytes(bytes)
                        if (st2 != DeviceState.UNKNOWN) {
                            state = st2
                            mDataHex = bytes.joinToString("") { b -> String.format("%02X", b) }
                            break
                        }
                    }
                }

                if (state == DeviceState.UNKNOWN && rawBytes != null && rawBytes.isNotEmpty()) {
                    var idx = 0
                    while (idx < rawBytes.size - 1) {
                        val length = rawBytes[idx].toInt() and 0xFF
                        if (length == 0 || idx + length >= rawBytes.size) break
                        val type = rawBytes[idx + 1].toInt() and 0xFF
                        if (type == 0xFF && length >= 2) {
                            val mBytes = rawBytes.copyOfRange(idx + 2, idx + 1 + length)
                            val st = DeviceState.fromBytes(mBytes)
                            if (st != DeviceState.UNKNOWN) {
                                state = st
                                mDataHex = mBytes.joinToString("") { b -> String.format("%02X", b) }
                                break
                            }
                        }
                        idx += length + 1
                    }
                }

                if (state == DeviceState.UNKNOWN && isSetupCalis) {
                    state = DeviceState.FACTORY_MODE
                }

                // If device advertises active session status (0x0011 / 0x0111), ensure it has a friendly name
                if (state == DeviceState.SESSION_ACTIVE_OPEN || state == DeviceState.SESSION_ACTIVE_BUSY) {
                    if (rawName.isBlank() || name == "Unknown Device") {
                        name = "Calis Device (${address.takeLast(5)})"
                    }
                }

                Log.d("CALIS", "[REG] Scanned device name: $name, address: $address, state: ${state.name} ($mDataHex), RSSI: ${it.rssi}")

                if (state != DeviceState.UNKNOWN && (hasService || isCalisName || isSetupCalis || state == DeviceState.SESSION_ACTIVE_OPEN || state == DeviceState.SESSION_ACTIVE_BUSY)) {
                    _deviceState.value = state
                }

                val existing = _scannedDevices.value.toMutableList()
                val index = existing.indexOfFirst { d -> d.address == address }
                val device = ScannedDevice(name, address, state, it.rssi, hasServiceUuid = hasService, lastSeenTimestamp = System.currentTimeMillis())

                if (index >= 0) {
                    existing[index] = device
                } else {
                    existing.add(device)
                }
                _scannedDevices.value = existing
                CalisBleService.updateNotification(context)
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Log.e(TAG, "Scan failed with error code: $errorCode")
            if (errorCode == ScanCallback.SCAN_FAILED_ALREADY_STARTED) {
                isScanning = true
                _connectionState.value = ConnectionState.Scanning
            } else {
                isScanning = false
                if (_connectionState.value is ConnectionState.Scanning) {
                    _connectionState.value = ConnectionState.Disconnected
                }
            }
        }
    }

    private fun hasManufacturerDataInBytes(rawBytes: ByteArray?): Boolean {
        if (rawBytes == null || rawBytes.isEmpty()) return false
        var idx = 0
        while (idx < rawBytes.size - 1) {
            val length = rawBytes[idx].toInt() and 0xFF
            if (length == 0 || idx + length >= rawBytes.size) break
            val type = rawBytes[idx + 1].toInt() and 0xFF
            if (type == 0xFF) return true
            idx += length + 1
        }
        return false
    }

    /**
     * Connect to target device by address or start Virtual Device
     */
    fun connect(address: String) {
        lastTargetAddress = address
        isExplicitlyDisconnected = false
        _isAuthenticated.value = false
        CalisBleService.updateNotification(context)
        Log.d("CALIS", "connect called with address: $address, isSimulatedMode=$isSimulatedMode")
        if (isSimulatedMode || address.startsWith("VIRTUAL_")) {
            connectVirtual()
            return
        }

        try {
            try {
                bluetoothGatt?.disconnect()
                bluetoothGatt?.close()
            } catch (e: Exception) {
                Log.e(TAG, "Error closing previous GATT: ${e.message}")
            }
            bluetoothGatt = null
            rxCharacteristic = null

            val device = bluetoothAdapter?.getRemoteDevice(address)
            if (device == null) {
                _connectionState.value = ConnectionState.Error("Device not found: $address")
                CalisBleService.updateNotification(context)
                return
            }

            addDebugLog("GATT Connected, requesting MTU 255...")
                _connectionState.value = ConnectionState.Connecting
            CalisBleService.updateNotification(context)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                bluetoothGatt = device.connectGatt(context, false, gattCallback, BluetoothDevice.TRANSPORT_LE)
            } else {
                bluetoothGatt = device.connectGatt(context, false, gattCallback)
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Connection failed due to missing permission", e)
            _connectionState.value = ConnectionState.Error("Missing BLE permissions")
            CalisBleService.updateNotification(context)
        } catch (e: Exception) {
            Log.e(TAG, "Connection failed", e)
            _connectionState.value = ConnectionState.Error("Connection failed: ${e.message}")
            CalisBleService.updateNotification(context)
        }
    }

    fun clearScannedDevices() {
        _scannedDevices.value = emptyList()
        CalisBleService.updateNotification(context)
    }

    fun disconnect() {
        isExplicitlyDisconnected = true
        _isAuthenticated.value = false
        _connectionState.value = ConnectionState.Disconnected
        CalisBleService.updateNotification(context)
        Log.d(TAG, "Disconnect requested. isSimulatedMode=$isSimulatedMode")
        if (isSimulatedMode) {
            startScan()
            return
        }

        val gatt = bluetoothGatt
        if (gatt != null) {
            _connectionState.value = ConnectionState.Disconnected
            try {
                gatt.disconnect()
            } catch (e: Exception) {
                Log.e(TAG, "Error requesting GATT disconnect: ${e.message}")
            }

            scope.launch {
                delay(4000)
                if (bluetoothGatt != null) {
                    try {
                        bluetoothGatt?.close()
                    } catch (e: Exception) {
                        Log.e(TAG, "Error closing GATT: ${e.message}")
                    }
                    bluetoothGatt = null
                }
            }
        } else {
            _connectionState.value = ConnectionState.Disconnected
        }
        startScan()
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            Log.d("CALIS", "[REG] Connection state change: status=$status, newState=$newState (Connected=2, Disconnected=0)")
            if (status != BluetoothGatt.GATT_SUCCESS && newState != BluetoothProfile.STATE_CONNECTED) {
                Log.e("CALIS", "[REG] Unexpected GATT connection drop or error: status=$status, newState=$newState")
                rxCharacteristic = null
                _isAuthenticated.value = false
                try {
                    bluetoothGatt?.close()
                } catch (e: Exception) {
                    Log.e(TAG, "Error closing GATT on drop: ${e.message}")
                }
                bluetoothGatt = null
                addDebugLog("Connection failed: status $status", isError = true)
                _connectionState.value = ConnectionState.Error("Connection failed (status $status)")
                CalisBleService.updateNotification(context)
                if (bluetoothAdapter?.isEnabled == true) {
                    startScan()
                }
                return
            }
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                addDebugLog("GATT Connected, requesting MTU 255...")
                _connectionState.value = ConnectionState.Connecting
                CalisBleService.updateNotification(context)

                scope.launch {
                    delay(300)
                    Log.d("CALIS", "[REG] Requesting MTU size 255...")
                    gatt?.requestMtu(255)

                    delay(1500)
                    if (gatt != null && rxCharacteristic == null) {
                        Log.d("CALIS", "[REG] Failsafe: Triggering service discovery...")
                        gatt.discoverServices()
                    }
                }
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                Log.d("CALIS", "[REG] Expected or unexpected device disconnect")
                rxCharacteristic = null
                _isAuthenticated.value = false
                try {
                    bluetoothGatt?.close()
                } catch (e: Exception) {
                    Log.e(TAG, "Error closing GATT on disconnect: ${e.message}")
                }
                bluetoothGatt = null
                _connectionState.value = ConnectionState.Disconnected
                CalisBleService.updateNotification(context)
                if (bluetoothAdapter?.isEnabled == true) {
                    startScan()
                }
            }
        }

        override fun onReadRemoteRssi(gatt: BluetoothGatt?, rssi: Int, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                _currentRssi.value = rssi
            }
        }

        override fun onMtuChanged(gatt: BluetoothGatt?, mtu: Int, status: Int) {
            Log.d("CALIS", "[REG] onMtuChanged: mtu=$mtu, status=$status. Discovering services...")
            gatt?.discoverServices()
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            Log.d("CALIS", "[REG] Services discovered status: $status")
            if (status == BluetoothGatt.GATT_SUCCESS && gatt != null) {
                val service = gatt.getService(SERVICE_UUID)
                Log.d("CALIS", "[REG] Service UUID found: ${service != null}")
                if (service != null) {
                    rxCharacteristic = service.getCharacteristic(RX_CHAR_UUID)
                    val txChar = service.getCharacteristic(TX_CHAR_UUID)
                    Log.d("CALIS", "[REG] RX=${rxCharacteristic != null}, TX=${txChar != null}")

                    if (txChar != null) {
                        Log.d("CALIS", "[REG] Subscribing to Tx notifications...")
                        gatt.setCharacteristicNotification(txChar, true)
                        val descriptor = txChar.getDescriptor(CCCD_UUID)
                        if (descriptor != null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                gatt.writeDescriptor(descriptor, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE)
                            } else {
                                descriptor.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                                gatt.writeDescriptor(descriptor)
                            }

                            scope.launch {
                                delay(1200)
                                if (_connectionState.value !is ConnectionState.Connected && bluetoothGatt != null) {
                                    _connectionState.value = ConnectionState.Connected(
                                        deviceName = gatt.device.name ?: "Calis Device",
                                        address = gatt.device.address,
                                        state = _deviceState.value
                                    )
                                    CalisBleService.updateNotification(context)
                                }
                            }
                            return
                        }
                    }

                    _connectionState.value = ConnectionState.Connected(
                        deviceName = gatt.device.name ?: "Calis Device",
                        address = gatt.device.address,
                        state = _deviceState.value
                    )
                    CalisBleService.updateNotification(context)
                }
            }
        }

        @Suppress("DEPRECATION")
        override fun onDescriptorWrite(
            gatt: BluetoothGatt?,
            descriptor: BluetoothGattDescriptor?,
            status: Int
        ) {
            Log.d("CALIS", "[REG] onDescriptorWrite status: $status, descriptor UUID: ${descriptor?.uuid}")
            if (gatt != null && status == BluetoothGatt.GATT_SUCCESS && descriptor?.uuid == CCCD_UUID) {
                Log.d("CALIS", "[REG] TX notification enabled. Setting ConnectionState to Connected.")
                _connectionState.value = ConnectionState.Connected(
                    deviceName = gatt.device.name ?: "Calis Device",
                    address = gatt.device.address,
                    state = _deviceState.value
                )
            }
        }

        override fun onCharacteristicWrite(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            Log.d(TAG, "onCharacteristicWrite status: $status for characteristic: ${characteristic?.uuid}")
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            value: ByteArray
        ) {
            val payload = String(value, Charsets.UTF_8).trim()
            Log.d("CALIS", "[REG] Received notification packet: $payload")
            addDebugLog("RX: $payload", isRx = true)
            _lastTxResponse.value = payload
            val packet = PacketParser.parse(payload)
            if (packet is BlePacket.AuthOkResponse) {
                _isAuthenticated.value = true
                _deviceState.value = DeviceState.SESSION_INACTIVE
            } else if (packet is BlePacket.AuthFailedResponse || packet is BlePacket.UnregisteredResponse) {
                _isAuthenticated.value = false
            }
            scope.launch {
                _incomingPackets.emit(packet)
            }
        }

        @Deprecated("Deprecated in API 33")
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?
        ) {
            val bytes = characteristic?.value ?: return
            val payload = String(bytes, Charsets.UTF_8).trim()
            Log.d("CALIS", "[REG] Received notification packet (deprecated callback): $payload")
            addDebugLog("RX: $payload", isRx = true)
            _lastTxResponse.value = payload
            val packet = PacketParser.parse(payload)
            if (packet is BlePacket.AuthOkResponse) {
                _isAuthenticated.value = true
                _deviceState.value = DeviceState.SESSION_INACTIVE
            } else if (packet is BlePacket.AuthFailedResponse || packet is BlePacket.UnregisteredResponse) {
                _isAuthenticated.value = false
            }
            scope.launch {
                _incomingPackets.emit(packet)
            }
        }
    }

    /**
     * Send Packet over BLE RX characteristic or Virtual Bridge
     */
    fun sendPacket(packet: BlePacket, onComplete: ((Boolean) -> Unit)? = null) {
        val payload = when (packet) {
            is BlePacket.RegCommand -> packet.encode()
            is BlePacket.AuthCommand -> packet.encode()
            is BlePacket.StartSessionCommand -> packet.encode()
            is BlePacket.EndSessionCommand -> packet.encode()
            is BlePacket.TaskCommand -> packet.encode()
            is BlePacket.UnregCommand -> packet.encode()
            is BlePacket.StudentRegRequest -> packet.encodeForDevice()
            is BlePacket.CheckInRequest -> packet.encodeForDevice()
            is BlePacket.TaskSubmissionRequest -> packet.encodeForDevice()
            is BlePacket.RequestUpdateRequest -> packet.encodeForDevice()
            is BlePacket.RegApprovedResponse -> packet.encode()
            is BlePacket.RegRejectedResponse -> packet.encode()
            is BlePacket.AtdAcceptedResponse -> packet.encode()
            is BlePacket.AtdRejectedResponse -> packet.encode()
            is BlePacket.MarkedResponse -> packet.encode()
            is BlePacket.SchedResponse -> packet.encode()
            is BlePacket.DoneResponse -> packet.encode()
            else -> ""
        }

        if (payload.isEmpty()) {
            onComplete?.invoke(false)
            return
        }

        Log.d(TAG, "Sending packet payload: $payload (isSimulatedMode=$isSimulatedMode)")

        if (isSimulatedMode) {
            onComplete?.invoke(true)
            handleVirtualPacket(payload, packet)
            return
        }

        val gatt = bluetoothGatt
        val rx = rxCharacteristic
        if (gatt == null || rx == null) {
            Log.e(TAG, "Cannot send packet '$payload': gatt=$gatt, rx=$rx")
            onComplete?.invoke(false)
            return
        }

        val writeType = if ((rx.properties and BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE) != 0) {
            BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
        } else {
            BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val status = gatt.writeCharacteristic(
                rx,
                payload.toByteArray(Charsets.UTF_8),
                writeType
            )
            val success = (status == BluetoothStatusCodes.SUCCESS)
            Log.d(TAG, "Sent packet to RX: '$payload' status=$status success=$success writeType=$writeType")
            addDebugLog("TX: $payload", isTx = true)
            onComplete?.invoke(success)
        } else {
            rx.value = payload.toByteArray(Charsets.UTF_8)
            rx.writeType = writeType
            val success = gatt.writeCharacteristic(rx)
            Log.d(TAG, "Sent packet to RX: '$payload' success=$success writeType=$writeType")
            addDebugLog("TX: $payload", isTx = true)
            onComplete?.invoke(success)
        }
    }

    fun setVirtualClassroomInfo(classroomName: String, isBound: Boolean) {
        if (classroomName.isNotBlank()) {
            virtualClassroomName = classroomName
        }
        if (isBound) {
            if (_deviceState.value == DeviceState.FACTORY_MODE || _deviceState.value == DeviceState.REGISTERING) {
                _deviceState.value = DeviceState.SESSION_INACTIVE
            }
        }
    }

    // --- VIRTUAL BLE LOOPBACK BRIDGE ENGINE ---

    private fun enableVirtualMode() {
        isSimulatedMode = true
        val currentName = if (_deviceState.value == DeviceState.FACTORY_MODE || _deviceState.value == DeviceState.REGISTERING) {
            "Setup Calis (Virtual)"
        } else {
            virtualClassroomName
        }
        val currentDev = ScannedDevice(
            name = currentName,
            address = "VIRTUAL_CALIS_DEVICE",
            state = _deviceState.value,
            rssi = -50,
            hasServiceUuid = true
        )

        _scannedDevices.value = listOf(currentDev)
        _connectionState.value = ConnectionState.Disconnected
        startVirtualHeartbeat()
    }

    private var virtualHeartbeatJob: Job? = null

    private fun startVirtualHeartbeat() {
        virtualHeartbeatJob?.cancel()
        virtualHeartbeatJob = scope.launch {
            while (isActive) {
                delay(2000L)
                if (isSimulatedMode) {
                    val now = System.currentTimeMillis()
                    _scannedDevices.value = _scannedDevices.value.map { dev ->
                        dev.copy(lastSeenTimestamp = now)
                    }
                }
            }
        }
    }

    private fun connectVirtual() {
        isSimulatedMode = true
        _connectionState.value = ConnectionState.Connected(
            deviceName = if (_deviceState.value == DeviceState.FACTORY_MODE || _deviceState.value == DeviceState.REGISTERING) "Setup Calis" else virtualClassroomName,
            address = "VIRTUAL_CALIS_DEVICE",
            state = _deviceState.value
        )
        CalisBleService.updateNotification(context)
    }

    fun setVirtualDeviceState(state: DeviceState) {
        _deviceState.value = state
        val name = if (state == DeviceState.FACTORY_MODE || state == DeviceState.REGISTERING) "Setup Calis" else virtualClassroomName
        val virtDev = ScannedDevice(
            name = name,
            address = "VIRTUAL_CALIS_DEVICE",
            state = state,
            rssi = -50,
            hasServiceUuid = true
        )
        _scannedDevices.value = listOf(virtDev)
        if (_connectionState.value is ConnectionState.Connected) {
            _connectionState.value = ConnectionState.Connected(
                deviceName = name,
                address = "VIRTUAL_CALIS_DEVICE",
                state = state
            )
        }
        CalisBleService.updateNotification(context)
    }

    private fun handleVirtualPacket(payload: String, packet: BlePacket) {
        scope.launch {
            delay(150)
            when (packet) {
                is BlePacket.RegCommand -> {
                    virtualCid = packet.cid
                    virtualTid = packet.tid
                    virtualClassroomName = packet.cls
                    virtualTeacherName = packet.tc
                    setVirtualDeviceState(DeviceState.REGISTERING)
                    _lastTxResponse.value = "REGISTERED"
                    _incomingPackets.emit(BlePacket.RegisteredResponse)

                    scope.launch {
                        delay(600)
                        setVirtualDeviceState(DeviceState.AUTHENTICATE)
                        _connectionState.value = ConnectionState.Disconnected
                    }
                }

                is BlePacket.AuthCommand -> {
                    if (virtualTid.isNotEmpty() && packet.tid != virtualTid) {
                        _lastTxResponse.value = "AUTH_FAILED"
                        _isAuthenticated.value = false
                        _incomingPackets.emit(BlePacket.AuthFailedResponse)
                    } else {
                        virtualTid = packet.tid
                        virtualCid = packet.cid
                        setVirtualDeviceState(DeviceState.SESSION_INACTIVE)
                        _lastTxResponse.value = "AUTH_OK"
                        _isAuthenticated.value = true
                        _incomingPackets.emit(BlePacket.AuthOkResponse)
                    }
                }

                is BlePacket.StartSessionCommand -> {
                    setVirtualDeviceState(DeviceState.SESSION_ACTIVE_OPEN)
                }

                is BlePacket.EndSessionCommand -> {
                    setVirtualDeviceState(DeviceState.SESSION_INACTIVE)
                }

                is BlePacket.UnregCommand -> {
                    virtualTid = ""
                    virtualCid = ""
                    _isAuthenticated.value = false
                    setVirtualDeviceState(DeviceState.FACTORY_MODE)
                    _incomingPackets.emit(BlePacket.UnregisteredResponse)
                }

                is BlePacket.StudentRegRequest -> {
                    val forwarded = BlePacket.StudentRegRequest(
                        sid = packet.sid,
                        st = packet.st,
                        sex = packet.sex,
                        lrn = packet.lrn,
                        birth = packet.birth,
                        rid = 1
                    )
                    _incomingPackets.emit(forwarded)
                }

                is BlePacket.CheckInRequest -> {
                    val forwarded = BlePacket.CheckInRequest(
                        sid = packet.sid,
                        rid = 1
                    )
                    _incomingPackets.emit(forwarded)
                }

                is BlePacket.TaskSubmissionRequest -> {
                    val forwarded = BlePacket.TaskSubmissionRequest(
                        sid = packet.sid,
                        tkid = packet.tkid,
                        score = packet.score,
                        rid = 1
                    )
                    _incomingPackets.emit(forwarded)
                }

                is BlePacket.RequestUpdateRequest -> {
                    val forwarded = BlePacket.RequestUpdateRequest(
                        sid = packet.sid,
                        rid = 1
                    )
                    _incomingPackets.emit(forwarded)
                }

                is BlePacket.RegApprovedResponse,
                is BlePacket.RegRejectedResponse,
                is BlePacket.AtdAcceptedResponse,
                is BlePacket.AtdRejectedResponse,
                is BlePacket.MarkedResponse,
                is BlePacket.SchedResponse,
                is BlePacket.TaskCommand,
                is BlePacket.DoneResponse -> {
                    _incomingPackets.emit(packet)
                }

                else -> {}
            }
        }
    }
}
