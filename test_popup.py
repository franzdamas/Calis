with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'r') as f:
    content = f.read()

import re

# We will replace the Popup block.
popup_pattern = r"    Popup\([^\{]+\{\s*Box\(\s*modifier = Modifier\s*\.pointerInput\(Unit\) \{\s*detectDragGestures \{ change, dragAmount ->\s*change\.consume\(\)\s*offsetX \+= dragAmount\.x\s*offsetY \+= dragAmount\.y\s*\}\s*\}\s*\) \{\s*if \(isMinimized\) \{\s*MinimizedDebugConsole\(\s*onExpand = \{ isMinimized = false \}\s*\)\s*\} else \{\s*ExpandedDebugConsole\(\s*onMinimize = \{ isMinimized = true \},\s*onClose = \{ isVisible = false \}\s*\)\s*\}\s*\}\s*\}"

replacement = """    val dragModifier = Modifier.pointerInput(Unit) {
        detectDragGestures { change, dragAmount ->
            change.consume()
            offsetX += dragAmount.x
            offsetY += dragAmount.y
        }
    }

    if (isMinimized) {
        Popup(
            alignment = Alignment.TopStart,
            offset = IntOffset(offsetX.roundToInt(), offsetY.roundToInt()),
            properties = PopupProperties(focusable = false, dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(modifier = dragModifier) {
                MinimizedDebugConsole(onExpand = { isMinimized = false })
            }
        }
    } else {
        Popup(
            alignment = Alignment.TopStart,
            offset = IntOffset(offsetX.roundToInt(), offsetY.roundToInt()),
            properties = PopupProperties(focusable = true, dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(modifier = dragModifier) {
                ExpandedDebugConsole(
                    onMinimize = { isMinimized = true },
                    onClose = { isVisible = false }
                )
            }
        }
    }"""

new_content = re.sub(popup_pattern, replacement, content)

if new_content != content:
    with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'w') as f:
        f.write(new_content)
    print("Replaced popup successfully.")
else:
    print("Could not find pattern.")
