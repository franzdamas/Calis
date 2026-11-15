import sys

def main():
    with open('app/src/main/java/com/example/ble/BleManager.kt', 'r') as f:
        content = f.read()

    # Add variables near isSimulatedMode
    content = content.replace(
        "    var isSimulatedMode = false",
        "    var isSimulatedMode = false\n    var lastTargetAddress: String? = null\n    var queuedRawPacket: String? = null"
    )

    with open('app/src/main/java/com/example/ble/BleManager.kt', 'w') as f:
        f.write(content)

if __name__ == '__main__':
    main()
