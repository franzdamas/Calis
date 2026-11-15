import sys

def main():
    with open('app/src/main/java/com/example/ble/BleManager.kt', 'r') as f:
        content = f.read()

    # 1. Add lastTargetAddress and queuedRawPacket
    content = content.replace(
        "    private var isSimulatedMode = false",
        "    private var isSimulatedMode = false\n    var lastTargetAddress: String? = null\n    private var queuedRawPacket: String? = null"
    )

    # 2. Update connect to save lastTargetAddress
    content = content.replace(
        "    fun connect(address: String) {\n        isExplicitlyDisconnected = false",
        "    fun connect(address: String) {\n        lastTargetAddress = address\n        isExplicitlyDisconnected = false"
    )

    # 3. Update sendRawPacket
    send_raw_replacement = """    fun sendRawPacket(payload: String) {
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
        addDebugLog("Sent raw to RX: $payload", isTx = true)"""
    content = content.replace(
        """    fun sendRawPacket(payload: String) {
        val gatt = bluetoothGatt
        val rx = rxCharacteristic
        if (gatt == null || rx == null) {
            addDebugLog("Cannot send: Not connected or RX missing", isError = true)
            return
        }
        addDebugLog("Sent raw to RX: $payload", isTx = true)""",
        send_raw_replacement
    )

    # 4. Send queued packet when RX is ready
    rx_ready_hook = """                rxCharacteristic = rx
                txCharacteristic = tx
                
                if (tx != null) {
                    gatt.setCharacteristicNotification(tx, true)
                    val descriptor = tx.getDescriptor(CCCD_UUID)
                    if (descriptor != null) {
                        Log.d("CALIS", "[REG] Writing CCCD to enable TX notifications")
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            gatt.writeDescriptor(descriptor, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE)
                        } else {
                            descriptor.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                            gatt.writeDescriptor(descriptor)
                        }
                    }
                }
                
                val queued = queuedRawPacket
                if (queued != null) {
                    queuedRawPacket = null
                    scope.launch {
                        kotlinx.coroutines.delay(500) // Wait a bit for descriptor write to finish
                        sendRawPacket(queued)
                    }
                }"""
    
    # Let's find where rxCharacteristic is set.
    with open('patch_tmp_blemanager.kt', 'w') as f:
        f.write(content)

if __name__ == '__main__':
    main()
