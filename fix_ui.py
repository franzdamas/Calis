import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    main = f.read()

main = main.replace("""                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalisAppNavigation(
                        authViewModel = authViewModel,
                        teacherViewModel = teacherViewModel,
                        studentViewModel = studentViewModel
                    )
                    com.example.ui.components.DebugConsolePopup()
                }""", """                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CalisAppNavigation(
                            authViewModel = authViewModel,
                            teacherViewModel = teacherViewModel,
                            studentViewModel = studentViewModel
                        )
                        com.example.ui.components.DebugConsolePopup()
                    }
                }""")

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(main)

with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'r') as f:
    console = f.read()

pattern = r"    if \(isMinimized\) \{\s*Popup\([\s\S]*?\}\s*\)\s*\}\s*\}"

replacement = """    Box(
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
    }"""

console = re.sub(pattern, replacement, console)

with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'w') as f:
    f.write(console)
