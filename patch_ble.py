import re

with open('app/src/main/java/com/example/ble/BleManager.kt', 'r') as f:
    content = f.read()

# Add imports
imports_to_add = """import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
"""
if "import android.Manifest" not in content:
    content = content.replace('import android.os.Build\n', imports_to_add + 'import android.os.Build\n')

# Insert Debug definitions before _connectionState
debug_defs = """    data class DebugLog(val timestamp: Long, val message: String, val isError: Boolean = false, val isRx: Boolean = false, val isTx: Boolean = false)

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
            addDebugLog("Cannot send: Not connected or RX missing", isError = true)
            return
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
"""
if "data class DebugLog" not in content:
    content = content.replace(
        'private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)',
        debug_defs + '\n    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)'
    )

# Insert onReadRemoteRssi in gattCallback
rssi_cb = """        override fun onReadRemoteRssi(gatt: BluetoothGatt?, rssi: Int, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                _currentRssi.value = rssi
            }
        }
"""
if "onReadRemoteRssi" not in content:
    content = content.replace(
        '        override fun onMtuChanged(',
        rssi_cb + '\n        override fun onMtuChanged('
    )

# Request MTU size of 255
content = content.replace('gatt?.requestMtu(517)', 'gatt?.requestMtu(255)')
content = content.replace('Requesting MTU size 517...', 'Requesting MTU size 255...')

# Log connection state changes
content = content.replace(
    '_connectionState.value = ConnectionState.Connecting',
    'addDebugLog("GATT Connected, requesting MTU 255...")\n                _connectionState.value = ConnectionState.Connecting'
)
content = content.replace(
    '_connectionState.value = ConnectionState.Error("Connection failed (status $status)")',
    'addDebugLog("Connection failed: status $status", isError = true)\n                _connectionState.value = ConnectionState.Error("Connection failed (status $status)")'
)

# Replace Received notification packet logging with addDebugLog
content = content.replace(
    'Log.d("CALIS", "[REG] Received notification packet: $payload")',
    'Log.d("CALIS", "[REG] Received notification packet: $payload")\n            addDebugLog("RX: $payload", isRx = true)'
)

# Replace send packet logging with addDebugLog
content = content.replace(
    'Log.d(TAG, "Sent packet to RX: \'$payload\' status=$status success=$success writeType=$writeType")',
    'Log.d(TAG, "Sent packet to RX: \'$payload\' status=$status success=$success writeType=$writeType")\n            addDebugLog("TX: $payload", isTx = true)'
)

content = content.replace(
    'Log.d(TAG, "Sent packet to RX: \'$payload\' success=$success writeType=$writeType")',
    'Log.d(TAG, "Sent packet to RX: \'$payload\' success=$success writeType=$writeType")\n            addDebugLog("TX: $payload", isTx = true)'
)

with open('app/src/main/java/com/example/ble/BleManager.kt', 'w') as f:
    f.write(content)
