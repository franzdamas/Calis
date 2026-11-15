with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'r') as f:
    lines = f.readlines()

new_lines = []
skip = False
for line in lines:
    if "    } else {" in line and "Popup(" in "".join(lines[lines.index(line):lines.index(line)+5]):
        pass
    new_lines.append(line)

with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'r') as f:
    text = f.read()

import re
# Find the exact block we want to keep for DebugConsolePopup
func_pattern = r"fun DebugConsolePopup\(\) \{[\s\S]*?\}\n\n@Composable"

def get_correct_func():
    return """fun DebugConsolePopup() {
    var isVisible by remember { mutableStateOf(true) }
    var isMinimized by remember { mutableStateOf(true) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    if (!isVisible) return

    val dragModifier = Modifier.pointerInput(Unit) {
        detectDragGestures { change, dragAmount ->
            change.consume()
            offsetX += dragAmount.x
            offsetY += dragAmount.y
        }
    }

    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .then(dragModifier)
    ) {
        if (isMinimized) {
            MinimizedDebugConsole(onExpand = { isMinimized = false })
        } else {
            ExpandedDebugConsole(
                onMinimize = { isMinimized = true },
                onClose = { isVisible = false }
            )
        }
    }
}

@Composable"""

new_text = re.sub(r"fun DebugConsolePopup\(\) \{[\s\S]*?@Composable", get_correct_func(), text)

with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'w') as f:
    f.write(new_text)

