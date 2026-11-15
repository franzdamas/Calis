with open('app/src/main/java/com/example/ui/screens/StudentMainScreen.kt', 'r') as f:
    content = f.read()

# First, I need to restore the file from before I broke it...
# Is it possible to use git? Let's check git status in the root.
import subprocess
print(subprocess.run(["git", "status"], capture_output=True, text=True).stdout)
