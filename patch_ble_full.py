import sys

def main():
    with open('patch_tmp_blemanager.kt', 'r') as f:
        content = f.read()

    # onDescriptorWrite logic
    hook = """                _connectionState.value = ConnectionState.Connected(
                    deviceName = gatt.device.name ?: "Calis Device",
                    address = gatt.device.address,
                    state = _deviceState.value
                )
                val queued = queuedRawPacket
                if (queued != null) {
                    queuedRawPacket = null
                    scope.launch {
                        kotlinx.coroutines.delay(200)
                        sendRawPacket(queued)
                    }
                }"""
    content = content.replace(
        """                _connectionState.value = ConnectionState.Connected(
                    deviceName = gatt.device.name ?: "Calis Device",
                    address = gatt.device.address,
                    state = _deviceState.value
                )""",
        hook
    )

    # Log for detecting status byte
    status_log_hook1 = """                                state = st
                                mDataHex = mBytes.joinToString("") { b -> String.format("%02X", b) }
                                addDebugLog("Detected device state: ${state.name}")
                                break"""
    content = content.replace(
        """                                state = st
                                mDataHex = mBytes.joinToString("") { b -> String.format("%02X", b) }
                                break""",
        status_log_hook1
    )

    status_log_hook2 = """                            state = st2
                            mDataHex = bytes.joinToString("") { b -> String.format("%02X", b) }
                            addDebugLog("Detected device state: ${state.name}")
                            break"""
    content = content.replace(
        """                            state = st2
                            mDataHex = bytes.joinToString("") { b -> String.format("%02X", b) }
                            break""",
        status_log_hook2
    )
    
    # Save back
    with open('app/src/main/java/com/example/ble/BleManager.kt', 'w') as f:
        f.write(content)

if __name__ == '__main__':
    main()
