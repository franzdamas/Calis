import re

# Fix BleManager.kt
with open('app/src/main/java/com/example/ble/BleManager.kt', 'r') as f:
    ble_content = f.read()

# The missing addDebugLog is in the deprecated onCharacteristicChanged
deprecated_log = 'Log.d("CALIS", "[REG] Received notification packet (deprecated callback): $payload")'
fixed_deprecated_log = deprecated_log + '\n            addDebugLog("RX: $payload", isRx = true)'
ble_content = ble_content.replace(deprecated_log, fixed_deprecated_log)

with open('app/src/main/java/com/example/ble/BleManager.kt', 'w') as f:
    f.write(ble_content)


# Fix DebugConsole.kt
with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'r') as f:
    console_content = f.read()

# Remove the Close button
close_btn_pattern = r"\s*Spacer\(modifier = Modifier\.width\(8\.dp\)\)\s*IconButton\(onClick = onClose, modifier = Modifier\.size\(28\.dp\)\) \{\s*Icon\(Icons\.Default\.Close, \"Close\", tint = Color\.LightGray\)\s*\}"
console_content = re.sub(close_btn_pattern, "", console_content)

# Remove the onClose parameter from ExpandedDebugConsole
console_content = console_content.replace(
    "fun ExpandedDebugConsole(onMinimize: () -> Unit, onClose: () -> Unit)", 
    "fun ExpandedDebugConsole(onMinimize: () -> Unit)"
)

# Remove onClose = { isVisible = false }
console_content = console_content.replace(
    "                onClose = { isVisible = false }\n            )", 
    "            )"
)
console_content = console_content.replace(
    "                onClose = { isVisible = false }", 
    ""
)

# Remove var isVisible and if (!isVisible) return from DebugConsolePopup
console_content = re.sub(r"\s*var isVisible by remember \{ mutableStateOf\(true\) \}", "", console_content)
console_content = re.sub(r"\s*if \(\!isVisible\) return", "", console_content)

with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'w') as f:
    f.write(console_content)

