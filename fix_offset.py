import re

with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'r') as f:
    content = f.read()

# Add imports
imports_to_add = """import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
"""
if "LocalConfiguration" not in content:
    content = content.replace("import androidx.compose.ui.platform.LocalContext", imports_to_add + "import androidx.compose.ui.platform.LocalContext")

# Replace offset vars
target_vars = """    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }"""

replacement_vars = """    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val initialX = remember { with(density) { (configuration.screenWidthDp - 70).dp.toPx() } }
    val initialY = remember { with(density) { (configuration.screenHeightDp - 150).dp.toPx() } }
    var offsetX by remember { mutableFloatStateOf(initialX) }
    var offsetY by remember { mutableFloatStateOf(initialY) }"""

content = content.replace(target_vars, replacement_vars)

with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'w') as f:
    f.write(content)
