import sys
import re

def main():
    with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'r') as f:
        content = f.read()
    
    # 1. Ensure Popup is imported
    if "import androidx.compose.ui.window.Popup" not in content:
        content = content.replace("import androidx.compose.ui.unit.sp", "import androidx.compose.ui.unit.sp\nimport androidx.compose.ui.window.Popup\nimport androidx.compose.ui.window.PopupProperties")

    # 2. Rewrite DebugConsolePopup
    old_popup = """@Composable
fun DebugConsolePopup() {
    var isMinimized by remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val initialX = remember { with(density) { (configuration.screenWidthDp - 70).dp.toPx() } }
    val initialY = remember { with(density) { (configuration.screenHeightDp - 150).dp.toPx() } }
    var offsetX by remember { mutableFloatStateOf(initialX) }
    var offsetY by remember { mutableFloatStateOf(initialY) }

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
            )
        }
    }
}"""
    
    new_popup = """@Composable
fun DebugConsolePopup() {
    var isMinimized by remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val initialX = remember { with(density) { (configuration.screenWidthDp - 70).dp.toPx() } }
    val initialY = remember { with(density) { (configuration.screenHeightDp - 150).dp.toPx() } }
    var offsetX by remember { mutableFloatStateOf(initialX) }
    var offsetY by remember { mutableFloatStateOf(initialY) }

    val dragModifier = Modifier.pointerInput(Unit) {
        detectDragGestures { change, dragAmount ->
            change.consume()
            offsetX += dragAmount.x
            offsetY += dragAmount.y
        }
    }

    Popup(
        alignment = Alignment.TopStart,
        offset = IntOffset(offsetX.roundToInt(), offsetY.roundToInt()),
        properties = PopupProperties(
            focusable = !isMinimized,
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            clippingEnabled = false
        )
    ) {
        Box(modifier = dragModifier) {
            if (isMinimized) {
                MinimizedDebugConsole(onExpand = { isMinimized = false })
            } else {
                ExpandedDebugConsole(
                    onMinimize = { isMinimized = true },
                )
            }
        }
    }
}"""
    
    # Simple replace
    new_content = content.replace(old_popup, new_popup)
    
    # Wait, the original code had: ExpandedDebugConsole(onMinimize = { isMinimized = true })
    # but the python script patch_console.py might have added an `onClose` parameter?
    # Let's check if the current file has onClose
    if "onClose =" in content and "ExpandedDebugConsole(" in old_popup:
        # We should just use regex to replace the function body
        pass
    
    with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'w') as f:
        f.write(new_content)

if __name__ == '__main__':
    main()
