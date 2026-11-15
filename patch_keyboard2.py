import sys
import re

with open("app/src/main/java/com/example/ui/screens/AuthScreen.kt", "r") as f:
    content = f.read()

content = content.replace("defaultKeyboardAction(androidx.compose.ui.text.input.ImeAction.Done)", "keyboardController?.hide()")

# Add keyboardController and imports
import_statement1 = "import androidx.compose.ui.platform.LocalSoftwareKeyboardController\n"
if "LocalSoftwareKeyboardController" not in content:
    content = content.replace("import androidx.compose.ui.platform.LocalContext", import_statement1 + "import androidx.compose.ui.platform.LocalContext")

# TeacherDetailsView
if "val keyboardController =" not in content.split("fun TeacherDetailsView")[1].split("Column")[0]:
    content = content.replace("fun TeacherDetailsView(\n", "fun TeacherDetailsView(\n    keyboardController: androidx.compose.ui.platform.SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,\n")

# StudentDetailsView
if "val keyboardController =" not in content.split("fun StudentDetailsView")[1].split("Column")[0]:
    content = content.replace("fun StudentDetailsView(\n", "fun StudentDetailsView(\n    keyboardController: androidx.compose.ui.platform.SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,\n")


with open("app/src/main/java/com/example/ui/screens/AuthScreen.kt", "w") as f:
    f.write(content)
