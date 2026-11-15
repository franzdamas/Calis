package com.example.ble

import android.Manifest
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
import android.bluetooth.le.ScanRecord
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.ParcelUuid
import android.util.Log
import androidx.core.content.ContextCompat
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
class StudentBleManager(private val context: Context) {

    companion object {
        const val TAG = "StudentBleManager"

        val SERVICE_UUID: UUID = UUID.fromString("CA113726-49D2-4D7F-A5A0-000000000001")
        val RX_CHAR_UUID: UUID = UUID.fromString("CA113726-49D2-4D7F-A5A0-000000000002")
        val TX_CHAR_UUID: UUID = UUID.fromString("CA113726-49D2-4D7F-A5A0-000000000003")
        val CCCD_UUID: UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")

        val isDebugConsoleVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)

        @Volatile
        private var INSTANCE: StudentBleManager? = null

        fun getInstance(context: Context): StudentBleManager {
            com.example.util.BlePermissionUtils.appContext = context.applicationContext
            return INSTANCE ?: synchronized(this) {
                val instance = StudentBleManager(context.applicationContext)
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

    private val bluetoothManager: BluetoothManager? by lazy {
        context.getSystemService(Context.BLUETOOTH_SERVICE) as? BluetoothManager
    }
    private val bluetoothAdapter: BluetoothAdapter?
        get() = bluetoothManager?.adapter ?: BluetoothAdapter.getDefaultAdapter()

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
                addDebugLog("Student not connected. Connecting to $target and queuing packet...", isError = false)
                queuedRawPacket = payload
                connect(target)
                return
            } else {
                addDebugLog("Student cannot send: Not connected or RX missing and no target address known", isError = true)
                return
            }
        }
        addDebugLog("Student sent raw to RX: $payload", isTx = true)
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

    private val _deviceState = MutableStateFlow<DeviceState>(DeviceState.UNKNOWN)
    val deviceState: StateFlow<DeviceState> = _deviceState.asStateFlow()

    private val _scannedDevices = MutableStateFlow<List<ScannedDevice>>(emptyList())
    val scannedDevices: StateFlow<List<ScannedDevice>> = _scannedDevices.asStateFlow()

    private val scannedDevicesMap = java.util.concurrent.ConcurrentHashMap<String, ScannedDevice>()
    private var lastScannedEmittedTime = 0L

    private fun updateScannedDevice(device: ScannedDevice) {
        val old = scannedDevicesMap[device.address]
        val isNew = old == null
        scannedDevicesMap[device.address] = device

        val now = System.currentTimeMillis()
        if (isNew || (now - lastScannedEmittedTime > 50L)) {
            lastScannedEmittedTime = now
            val iterator = scannedDevicesMap.entries.iterator()
            while (iterator.hasNext()) {
                val entry = iterator.next()
                if (now - entry.value.lastSeenTimestamp > 15000L) { // Increased to 15s
                    iterator.remove()
                }
            }
            _scannedDevices.value = scannedDevicesMap.values
                .sortedByDescending { it.rssi }
        }
    }

    private val _incomingPackets = MutableSharedFlow<BlePacket>(
        extraBufferCapacity = 64,
        onBufferOverflow = kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
    )
    val incomingPackets: SharedFlow<BlePacket> = _incomingPackets.asSharedFlow()

    private val _lastTxResponse = MutableStateFlow("")
    val lastTxResponse: StateFlow<String> = _lastTxResponse.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO + Job())

    private var bluetoothGatt: BluetoothGatt? = null
    private var rxCharacteristic: BluetoothGattCharacteristic? = null
    private var txCharacteristic: BluetoothGattCharacteristic? = null
    private var lastScanStartTime = 0L

    /**
     * Clean teardown of GATT connection, characteristics, and Android BLE stack state.
     * Prevents GATT client leaks (status 133), flushes device cache, and restarts continuous scanning.
     */
    @Synchronized
    fun teardownGatt(gattToClose: BluetoothGatt? = null, triggerScan: Boolean = true) {
        disconnectJob?.cancel()
        disconnectJob = null
        connectTimeoutJob?.cancel()
        connectTimeoutJob = null

        val gatt = gattToClose ?: bluetoothGatt
        Log.d(TAG, "Student teardownGatt invoked. gatt=${gatt != null}, triggerScan=$triggerScan")

        if (gatt != null) {
            try {
                txCharacteristic?.let { tx ->
                    try {
                        gatt.setCharacteristicNotification(tx, false)
                    } catch (_: Exception) {}
                }
                rxCharacteristic?.let { rx ->
                    try {
                        gatt.setCharacteristicNotification(rx, false)
                    } catch (_: Exception) {}
                }
                gatt.disconnect()
            } catch (e: Exception) {
                Log.w(TAG, "Student error during gatt.disconnect(): ${e.message}")
            }
            try {
                gatt.close()
            } catch (e: Exception) {
                Log.e(TAG, "Student error during gatt.close(): ${e.message}")
            }
        }

        if (gattToClose == null || gattToClose == bluetoothGatt) {
            bluetoothGatt = null
        }
        rxCharacteristic = null
        txCharacteristic = null
        queuedRawPacket = null
        _currentRssi.value = 0

        _connectionState.value = ConnectionState.Disconnected

        // If we just disconnected from a device, "refresh" its timestamp in the map
        // so it doesn't immediately disappear from UI
        gatt?.device?.address?.let { addr ->
            refreshScannedDevice(addr)
        }

        if (triggerScan && isBluetoothEnabled()) {
            startScan(forceRestart = true)
        }
    }

    /**
     * Explicitly and immediately clears all BLE GATT objects, characteristics,
     * and queues, tearing down any existing connection and triggering scanning if enabled.
     * Ensures that after every kind of student transaction, the BLE object is cleared.
     */
    /**
     * Updates device timestamp in the map to prevent premature staleness detection
     */
    fun refreshScannedDevice(address: String) {
        val old = scannedDevicesMap[address]
        if (old != null) {
            val refreshed = old.copy(lastSeenTimestamp = System.currentTimeMillis())
            scannedDevicesMap[address] = refreshed
            _scannedDevices.value = scannedDevicesMap.values.toList()
        }
    }

    fun clearBleObject(triggerScan: Boolean = true) {
        clearTransactionState()
        teardownGatt(null, triggerScan = triggerScan && isBluetoothEnabled())
    }

    /**
     * Clears in-flight transaction state, queued packets, and last Tx responses.
     */
    fun clearTransactionState() {
        queuedRawPacket = null
        _lastTxResponse.value = ""
    }

    /**
     * Resets and cleans BLE state and cached objects on application startup.
     */
    fun resetBleStateOnStartup(force: Boolean = false) {
        Log.d(TAG, "resetBleStateOnStartup: Clearing Student BLE state and cached objects on app startup.")
        teardownGatt(bluetoothGatt, triggerScan = false)
        _scannedDevices.value = emptyList()
        scannedDevicesMap.clear()
        _currentRssi.value = 0
        isExplicitlyDisconnected = false
        _connectionState.value = ConnectionState.Disconnected
        if (isBluetoothEnabled()) {
            startScan(forceRestart = true)
        }
    }

    var lastTargetAddress: String? = null
    var isExplicitlyDisconnected = false
    var registeredClassroomName: String = ""
    var queuedRawPacket: String? = null
        private set
    private var disconnectJob: kotlinx.coroutines.Job? = null
    private var connectTimeoutJob: kotlinx.coroutines.Job? = null
    private var retryScanJob: kotlinx.coroutines.Job? = null
    private var pruneStaleDevicesJob: kotlinx.coroutines.Job? = null

    private fun startPruningJob() {
        pruneStaleDevicesJob?.cancel()
        pruneStaleDevicesJob = scope.launch {
            while (isActive) {
                delay(2000L)
                val now = System.currentTimeMillis()
                val iterator = scannedDevicesMap.entries.iterator()
                var modified = false
                while (iterator.hasNext()) {
                    val entry = iterator.next()
                    if (now - entry.value.lastSeenTimestamp > 15000L) { // Increased to 15s
                        iterator.remove()
                        modified = true
                    }
                }
                if (modified) {
                    _scannedDevices.value = scannedDevicesMap.values.sortedByDescending { it.rssi }
                }
            }
        }
    }

    private fun stopPruningJob() {
        pruneStaleDevicesJob?.cancel()
        pruneStaleDevicesJob = null
    }

    private val btStateReceiver = object : BroadcastReceiver() {
        override fun onReceive(c: Context?, intent: Intent?) {
            if (intent?.action == BluetoothAdapter.ACTION_STATE_CHANGED) {
                val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                onBluetoothStateChanged(state)
            }
        }
    }

    init {
        try {
            val filter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
            ContextCompat.registerReceiver(context, btStateReceiver, filter, ContextCompat.RECEIVER_EXPORTED)
        } catch (e: Exception) {
            Log.e(TAG, "Error registering btStateReceiver in StudentBleManager", e)
        }
    }

    fun onBluetoothStateChanged(state: Int) {
        when (state) {
            BluetoothAdapter.STATE_TURNING_OFF, BluetoothAdapter.STATE_OFF -> {
                Log.d(TAG, "[BLE] Bluetooth turned OFF ($state). Resetting scanning & GATT state via teardownGatt.")
                retryScanJob?.cancel()
                retryScanJob = null
                isScanning = false
                teardownGatt(triggerScan = false)
                _scannedDevices.value = emptyList()
            }
            BluetoothAdapter.STATE_ON -> {
                Log.d(TAG, "[BLE] Bluetooth turned ON (STATE_ON). Scheduling scan restart...")
                isScanning = false
                if (_connectionState.value is ConnectionState.Scanning) {
                    _connectionState.value = ConnectionState.Disconnected
                }
                retryScanJob?.cancel()
                retryScanJob = scope.launch {
                    delay(100L)
                    if (isBluetoothEnabled() &&
                        _connectionState.value !is ConnectionState.Connected &&
                        _connectionState.value !is ConnectionState.Connecting &&
                        _connectionState.value !is ConnectionState.Disconnecting
                    ) {
                        startScan(forceRestart = true)
                    }
                }
            }
        }
    }

    fun isBluetoothSupported(): Boolean = bluetoothAdapter != null

    fun isBluetoothEnabled(): Boolean = bluetoothAdapter?.isEnabled == true

    var isScanning = false
        private set

    /**
     * Start scanning for Calis Devices
     */
    @SuppressLint("MissingPermission")
    fun startScan(forceRestart: Boolean = false) {
        val adapter = bluetoothAdapter
        if (adapter == null || !adapter.isEnabled) {
            Log.d(TAG, "Bluetooth is disabled or adapter is null. Cannot start scan.")
            isScanning = false
            if (_connectionState.value is ConnectionState.Scanning) {
                _connectionState.value = ConnectionState.Disconnected
            }
            return
        }

        // Protect active or in-flight connections: NEVER tear down or scan if connected, connecting, or disconnecting
        if (_connectionState.value is ConnectionState.Connected ||
            _connectionState.value is ConnectionState.Connecting ||
            _connectionState.value is ConnectionState.Disconnecting
        ) {
            Log.d(TAG, "Student connection active or in-progress (${_connectionState.value}). Skipping startScan to protect active state.")
            return
        }

        val now = System.currentTimeMillis()
        if (!forceRestart && isScanning && (now - lastScanStartTime < 1500L)) {
            Log.d(TAG, "Student scan is active and started recently (<1.5s ago). Skipping redundant restart.")
            return
        }

        if (forceRestart) {
            Log.d(TAG, "forceRestart requested: resetting student scanning state.")
            if (isScanning) {
                try {
                    adapter.bluetoothLeScanner?.stopScan(scanCallback)
                } catch (_: Exception) {}
                isScanning = false
            }
            if (_connectionState.value is ConnectionState.Scanning) {
                _connectionState.value = ConnectionState.Disconnected
            }
        }

        lastScanStartTime = now

        try {
            val scanner: BluetoothLeScanner? = adapter.bluetoothLeScanner
            if (scanner == null) {
                Log.d(TAG, "BluetoothLeScanner is null. Retrying scan in fast loop...")
                isScanning = false
                retryScanJob?.cancel()
                retryScanJob = scope.launch {
                    var attempts = 0
                    while (attempts < 40 && isActive) {
                        delay(50L)
                        if (isBluetoothEnabled()) {
                            val sc = bluetoothAdapter?.bluetoothLeScanner
                            if (sc != null) {
                                if (_connectionState.value !is ConnectionState.Connected &&
                                    _connectionState.value !is ConnectionState.Connecting &&
                                    _connectionState.value !is ConnectionState.Disconnecting
                                ) {
                                    startScan(forceRestart = true)
                                }
                                break
                            }
                        }
                        attempts++
                    }
                }
                return
            }

            try {
                scanner.stopScan(scanCallback)
            } catch (e: Exception) {}

            if (forceRestart) {
                // Do not clear the map anymore to prevent UI flickering and connection drops.
                // Stale devices are handled by startPruningJob()
            }

            _connectionState.value = ConnectionState.Scanning
            isScanning = true
            val currTime = System.currentTimeMillis()
            _scannedDevices.value = _scannedDevices.value.filter { currTime - it.lastSeenTimestamp <= 60000L }

            val settings = ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .setReportDelay(0)
                .build()

            try {
                scanner.startScan(null, settings, scanCallback)
            } catch (e: Exception) {
                Log.w(TAG, "Failed startScan without filters, trying with filters as fallback", e)
                val filters = mutableListOf<ScanFilter>()
                try {
                    filters.add(ScanFilter.Builder().setServiceUuid(ParcelUuid(SERVICE_UUID)).build())
                    if (!lastTargetAddress.isNullOrBlank() && BluetoothAdapter.checkBluetoothAddress(lastTargetAddress)) {
                        filters.add(ScanFilter.Builder().setDeviceAddress(lastTargetAddress).build())
                    }
                } catch (_: Exception) {}
                scanner.startScan(filters, settings, scanCallback)
            }
            startPruningJob()
            Log.d(TAG, "Student started continuous BLE scan with SCAN_MODE_LOW_LATENCY")
        } catch (e: SecurityException) {
            Log.e(TAG, "Scan failed due to missing permission", e)
            isScanning = false
            stopPruningJob()
            _connectionState.value = ConnectionState.Disconnected
        } catch (e: IllegalStateException) {
            Log.w(TAG, "BluetoothLeScanner not ready (IllegalStateException). Retrying in 1000ms...", e)
            isScanning = false
            stopPruningJob()
            retryScanJob?.cancel()
            retryScanJob = scope.launch {
                delay(1000L)
                if (isBluetoothEnabled() &&
                    _connectionState.value !is ConnectionState.Connected &&
                    _connectionState.value !is ConnectionState.Connecting &&
                    _connectionState.value !is ConnectionState.Disconnecting
                ) {
                    startScan(forceRestart = false)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Scan failed", e)
            isScanning = false
            stopPruningJob()
            _connectionState.value = ConnectionState.Disconnected
        }
    }

    @SuppressLint("MissingPermission")
    fun stopScan() {
        isScanning = false
        stopPruningJob()
        retryScanJob?.cancel()
        retryScanJob = null
        try {
            bluetoothAdapter?.bluetoothLeScanner?.stopScan(scanCallback)
            if (_connectionState.value is ConnectionState.Scanning) {
                _connectionState.value = ConnectionState.Disconnected
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error stopping scan", e)
        }
    }

    private fun containsCalisServiceUuid(scanRecord: ScanRecord?, rawBytes: ByteArray?): Boolean {
        if (scanRecord?.serviceUuids?.contains(ParcelUuid(SERVICE_UUID)) == true ||
            scanRecord?.serviceUuids?.contains(ParcelUuid(RX_CHAR_UUID)) == true ||
            scanRecord?.serviceUuids?.contains(ParcelUuid(TX_CHAR_UUID)) == true
        ) {
            return true
        }
        if (rawBytes == null || rawBytes.isEmpty()) return false
        for (i in 0 until rawBytes.size - 3) {
            val b0 = rawBytes[i].toInt() and 0xFF
            val b1 = rawBytes[i + 1].toInt() and 0xFF
            val b2 = rawBytes[i + 2].toInt() and 0xFF
            val b3 = rawBytes[i + 3].toInt() and 0xFF
            if ((b0 == 0xCA && b1 == 0x11 && b2 == 0x37 && b3 == 0x26) ||
                (b0 == 0x26 && b1 == 0x37 && b2 == 0x11 && b3 == 0xCA)
            ) {
                return true
            }
        }
        return false
    }

    private fun extractDeviceNameFromRawBytes(rawBytes: ByteArray?): String? {
        if (rawBytes == null || rawBytes.isEmpty()) return null
        var idx = 0
        while (idx < rawBytes.size - 1) {
            val length = rawBytes[idx].toInt() and 0xFF
            if (length == 0 || idx + length >= rawBytes.size) break
            val type = rawBytes[idx + 1].toInt() and 0xFF
            if (type == 0x08 || type == 0x09) {
                val nameBytes = rawBytes.copyOfRange(idx + 2, idx + 1 + length)
                val parsed = String(nameBytes, Charsets.UTF_8).trim { it <= ' ' || it == '\u0000' }
                if (parsed.isNotBlank()) return parsed
            }
            idx += length + 1
        }
        return null
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

    private val scanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            result?.let {
                val rawBytes = it.scanRecord?.bytes
                val hasService = containsCalisServiceUuid(it.scanRecord, rawBytes)
                val parsedRawName = extractDeviceNameFromRawBytes(rawBytes)
                val rawName = parsedRawName ?: it.scanRecord?.deviceName ?: it.device.name ?: ""
                var name = if (rawName.isBlank()) "Unknown Device" else rawName
                val address = it.device.address

                val isSetupCalis = name.contains("Setup Calis", ignoreCase = true) || name.contains("Setup", ignoreCase = true) || name.contains("Factory", ignoreCase = true) || name.contains("ESP32", ignoreCase = true)
                val isCalisName = name.contains("Calis", ignoreCase = true) || name.contains("Classroom", ignoreCase = true) || name.contains("CD", ignoreCase = true)
                val isRegisteredName = registeredClassroomName.isNotBlank() && (name.contains(registeredClassroomName, ignoreCase = true) || name.equals(registeredClassroomName, ignoreCase = true))
                val mData = it.scanRecord?.manufacturerSpecificData
                val hasMData = (mData != null && mData.size() > 0)
                val hasRawMData = rawBytes != null && hasManufacturerDataInBytes(rawBytes)

                val isTargetAddress = lastTargetAddress != null && address.equals(lastTargetAddress, ignoreCase = true)
                val isCalisDevice = hasService || isSetupCalis || isCalisName || isRegisteredName || isTargetAddress || hasMData || hasRawMData
                if (!isCalisDevice) {
                    return
                }

                var state = DeviceState.UNKNOWN
                var mDataHex = "None"
                // mData is already defined at line 565

                // Check active session status
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

                Log.d("CALIS", "[REG] Student scanned device name: $name, address: $address, state: ${state.name} ($mDataHex), RSSI: ${it.rssi}")

                // Protect session state: do not update global device state from scan results if already connected
                val isConnected = _connectionState.value is ConnectionState.Connected
                if (!isConnected && state != DeviceState.UNKNOWN) {
                    _deviceState.value = state
                }

                Log.d("CALIS", "[REG] Student scanned device name: $name, address: $address, state: ${state.name} ($mDataHex), RSSI: ${it.rssi}")

                val device = ScannedDevice(name, address, state, it.rssi, hasServiceUuid = hasService, lastSeenTimestamp = System.currentTimeMillis())
                updateScannedDevice(device)
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Log.e(TAG, "Student scan failed with error code: $errorCode")
            isScanning = false
            if (errorCode == ScanCallback.SCAN_FAILED_ALREADY_STARTED) {
                try {
                    bluetoothAdapter?.bluetoothLeScanner?.stopScan(this)
                } catch (e: Exception) {}
            }
            if (_connectionState.value is ConnectionState.Scanning) {
                _connectionState.value = ConnectionState.Disconnected
            }
            retryScanJob?.cancel()
            retryScanJob = scope.launch {
                delay(200L)
                if (isBluetoothEnabled() &&
                    _connectionState.value !is ConnectionState.Connected &&
                    _connectionState.value !is ConnectionState.Connecting &&
                    _connectionState.value !is ConnectionState.Disconnecting
                ) {
                    startScan(forceRestart = true)
                }
            }
        }
    }

    /**
     * Connect to target device by address (Student temporary RID 1-5 connection)
     */
    fun connect(address: String) {
        val currentConn = _connectionState.value
        if (currentConn is ConnectionState.Connected &&
            currentConn.address.equals(address, ignoreCase = true) &&
            bluetoothGatt != null
        ) {
            Log.d("CALIS", "Student connect called but already connected to $address. Preserving active connection.")
            return
        }

        if (currentConn is ConnectionState.Connecting &&
            lastTargetAddress.equals(address, ignoreCase = true) &&
            bluetoothGatt != null
        ) {
            Log.d("CALIS", "Student connect called but already connecting to $address. Preserving connection attempt.")
            return
        }

        disconnectJob?.cancel()
        disconnectJob = null
        lastTargetAddress = address
        isExplicitlyDisconnected = false
        Log.d("CALIS", "Student connect called with address: $address")
        if (address.isBlank()) {
            Log.e("CALIS", "Cannot connect: Address is blank")
            return
        }

        try {
            if (isScanning) {
                stopScan()
            }

            val oldGatt = bluetoothGatt
            if (oldGatt != null) {
                try {
                    oldGatt.close()
                } catch (e: Exception) {}
                bluetoothGatt = null
            }
            rxCharacteristic = null
            txCharacteristic = null

            val device = bluetoothAdapter?.getRemoteDevice(address)
            if (device == null) {
                _connectionState.value = ConnectionState.Error("Device not found: $address")
                return
            }

            addDebugLog("Student connecting to $address (temporary RID)...")
            _connectionState.value = ConnectionState.Connecting

            connectTimeoutJob?.cancel()
            connectTimeoutJob = scope.launch {
                delay(12000L)
                if (_connectionState.value is ConnectionState.Connecting) {
                    Log.w("CALIS", "Student connection attempt still connecting after 12 seconds. Clearing BLE object state.")
                    clearBleObject(triggerScan = isBluetoothEnabled())
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                bluetoothGatt = device.connectGatt(context, false, gattCallback, BluetoothDevice.TRANSPORT_LE)
            } else {
                bluetoothGatt = device.connectGatt(context, false, gattCallback)
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Connection failed due to missing permission", e)
            _connectionState.value = ConnectionState.Error("Missing BLE permissions")
        } catch (e: Exception) {
            Log.e(TAG, "Connection failed", e)
            _connectionState.value = ConnectionState.Error("Connection failed: ${e.message}")
        }
    }

    fun clearScannedDevices() {
        scannedDevicesMap.clear()
        _scannedDevices.value = emptyList()
    }

    fun disconnect() {
        isExplicitlyDisconnected = true

        val gatt = bluetoothGatt
        val wasActive = _connectionState.value is ConnectionState.Connected ||
                        _connectionState.value is ConnectionState.Connecting ||
                        gatt != null

        if (!wasActive) {
            teardownGatt(null, triggerScan = true)
            return
        }

        _connectionState.value = ConnectionState.Disconnecting
        Log.d(TAG, "Student disconnect requested.")

        teardownGatt(gatt, triggerScan = true)
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            Log.d("CALIS", "[REG] Student connection state change: status=$status, newState=$newState (Connected=2, Disconnected=0)")
            disconnectJob?.cancel()
            disconnectJob = null
            connectTimeoutJob?.cancel()
            connectTimeoutJob = null

            if (status != BluetoothGatt.GATT_SUCCESS) {
                Log.e("CALIS", "[REG] Student GATT error reported: status=$status, newState=$newState")
                addDebugLog("Student GATT error (status $status, newState $newState)", isError = true)
                teardownGatt(gattToClose = gatt, triggerScan = true)
                _connectionState.value = ConnectionState.Error("Connection failed (status $status)")
                return
            }
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                addDebugLog("Student GATT Connected, requesting MTU 255...")
                _connectionState.value = ConnectionState.Connecting

                scope.launch {
                    delay(300)
                    Log.d("CALIS", "[REG] Student requesting MTU size 255...")
                    gatt?.requestMtu(255)

                    delay(1500)
                    if (gatt != null && rxCharacteristic == null) {
                        Log.d("CALIS", "[REG] Failsafe: Student triggering service discovery...")
                        gatt.discoverServices()
                    }
                }
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                Log.d("CALIS", "[REG] Student device disconnected. Performing clean GATT teardown.")
                addDebugLog("Student device disconnected callback. Calling close() and clearing state...")
                teardownGatt(gattToClose = gatt, triggerScan = true)
            }
        }

        override fun onReadRemoteRssi(gatt: BluetoothGatt?, rssi: Int, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                _currentRssi.value = rssi
            }
        }

        override fun onMtuChanged(gatt: BluetoothGatt?, mtu: Int, status: Int) {
            Log.d("CALIS", "[REG] Student onMtuChanged: mtu=$mtu, status=$status. Discovering services...")
            gatt?.discoverServices()
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            Log.d("CALIS", "[REG] Student services discovered status: $status")
            if (status != BluetoothGatt.GATT_SUCCESS || gatt == null) {
                Log.e("CALIS", "[REG] Student service discovery failed: status=$status, gattIsNull=${gatt == null}")
                addDebugLog("Service discovery failed: status $status", isError = true)
                teardownGatt(gattToClose = gatt, triggerScan = true)
                _connectionState.value = ConnectionState.Error("Service discovery failed (status $status)")
                return
            }
            val service = gatt.getService(SERVICE_UUID)
            if (service == null) {
                Log.e("CALIS", "[REG] Service UUID $SERVICE_UUID not found on device")
                addDebugLog("Required Calis service not found", isError = true)
                teardownGatt(gattToClose = gatt, triggerScan = true)
                _connectionState.value = ConnectionState.Error("Service not found")
                return
            }
            rxCharacteristic = service.getCharacteristic(RX_CHAR_UUID)
            val txChar = service.getCharacteristic(TX_CHAR_UUID)
            txCharacteristic = txChar
            Log.d("CALIS", "[REG] Student RX=${rxCharacteristic != null}, TX=${txChar != null}")

            rxCharacteristic?.let { rx ->
                gatt.setCharacteristicNotification(rx, true)
            }
            txChar?.let { tx ->
                gatt.setCharacteristicNotification(tx, true)
            }

            val rxCccd = rxCharacteristic?.getDescriptor(CCCD_UUID)
            val txCccd = txChar?.getDescriptor(CCCD_UUID)

            if (rxCccd != null) {
                Log.d("CALIS", "[REG] Student subscribing to RX notifications via CCCD...")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    gatt.writeDescriptor(rxCccd, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE)
                } else {
                    rxCccd.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                    gatt.writeDescriptor(rxCccd)
                }
            } else if (txCccd != null) {
                Log.d("CALIS", "[REG] Student subscribing to TX notifications via CCCD...")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    gatt.writeDescriptor(txCccd, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE)
                } else {
                    txCccd.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                    gatt.writeDescriptor(txCccd)
                }
            } else {
                Log.d("CALIS", "[REG] Student no CCCD descriptors found. Setting ConnectionState to Connected.")
                _connectionState.value = ConnectionState.Connected(
                    deviceName = gatt.device.name ?: "Calis Device",
                    address = gatt.device.address,
                    state = _deviceState.value
                )
            }
        }

        @Suppress("DEPRECATION")
        override fun onDescriptorWrite(
            gatt: BluetoothGatt?,
            descriptor: BluetoothGattDescriptor?,
            status: Int
        ) {
            Log.d("CALIS", "[REG] Student onDescriptorWrite status: $status, descriptor UUID: ${descriptor?.uuid}")
            if (status != BluetoothGatt.GATT_SUCCESS) {
                Log.w("CALIS", "[REG] Descriptor write status non-success: $status, continuing...")
            }

            if (descriptor?.characteristic?.uuid == RX_CHAR_UUID) {
                val txCccd = txCharacteristic?.getDescriptor(CCCD_UUID)
                if (txCccd != null && gatt != null) {
                    Log.d("CALIS", "[REG] Sequential write: Student subscribing to TX notifications via CCCD...")
                    val writeResult = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        gatt.writeDescriptor(txCccd, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE) == BluetoothStatusCodes.SUCCESS
                    } else {
                        txCccd.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                        gatt.writeDescriptor(txCccd)
                    }
                    if (writeResult) {
                        return
                    }
                }
            }

            if (gatt != null) {
                Log.d("CALIS", "[REG] Student notification configuration completed. Setting ConnectionState to Connected.")
                _connectionState.value = ConnectionState.Connected(
                    deviceName = gatt.device.name ?: "Calis Device",
                    address = gatt.device.address,
                    state = _deviceState.value
                )
                val queued = queuedRawPacket
                if (queued != null) {
                    queuedRawPacket = null
                    scope.launch {
                        delay(200)
                        sendRawPacket(queued)
                    }
                }
            }
        }

        override fun onCharacteristicWrite(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            Log.d(TAG, "Student onCharacteristicWrite status: $status for characteristic: ${characteristic?.uuid}")
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            value: ByteArray
        ) {
            val payload = String(value, Charsets.UTF_8).trim()
            Log.d("CALIS", "[REG] Student received notification packet: $payload")
            addDebugLog("RX: $payload", isRx = true)
            _lastTxResponse.value = payload
            val packet = PacketParser.parse(payload)
            scope.launch {
                _incomingPackets.emit(packet)
            }
        }

        @Deprecated("Deprecated in API 33")
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                return
            }
            val bytes = characteristic?.value ?: return
            val payload = String(bytes, Charsets.UTF_8).trim()
            Log.d("CALIS", "[REG] Student received notification packet (deprecated callback): $payload")
            addDebugLog("RX: $payload", isRx = true)
            _lastTxResponse.value = payload
            val packet = PacketParser.parse(payload)
            scope.launch {
                _incomingPackets.emit(packet)
            }
        }
    }

    /**
     * Send Packet over BLE RX characteristic (Student requests)
     */
    fun sendPacket(packet: BlePacket, onComplete: ((Boolean) -> Unit)? = null) {
        val payload = when (packet) {
            is BlePacket.StudentRegRequest -> packet.encodeForDevice()
            is BlePacket.CheckInRequest -> packet.encodeForDevice()
            is BlePacket.TaskSubmissionRequest -> packet.encodeForDevice()
            is BlePacket.RequestUpdateRequest -> packet.encodeForDevice()
            else -> ""
        }

        if (payload.isEmpty()) {
            onComplete?.invoke(false)
            return
        }

        Log.d(TAG, "Student sending packet payload to GATT: $payload")

        val gatt = bluetoothGatt
        val rx = rxCharacteristic
        if (gatt == null || rx == null) {
            Log.e(TAG, "Student cannot send packet '$payload': gatt=$gatt, rx=$rx")
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
            Log.d(TAG, "Student sent packet to RX: '$payload' status=$status success=$success writeType=$writeType")
            addDebugLog("TX: $payload", isTx = true)
            onComplete?.invoke(success)
        } else {
            rx.value = payload.toByteArray(Charsets.UTF_8)
            rx.writeType = writeType
            val success = gatt.writeCharacteristic(rx)
            Log.d(TAG, "Student sent packet to RX: '$payload' success=$success writeType=$writeType")
            addDebugLog("TX: $payload", isTx = true)
            onComplete?.invoke(success)
        }
    }

    fun setRegisteredDevice(address: String?, classroomName: String?) {
        if (!address.isNullOrBlank()) {
            lastTargetAddress = address
        }
        if (!classroomName.isNullOrBlank()) {
            registeredClassroomName = classroomName
        }
    }
}
