with open('app/src/main/java/com/example/ui/screens/StudentMainScreen.kt', 'r') as f:
    lines = f.readlines()

for i, line in enumerate(lines[260:300], 261):
    print(f"{i}: {line.rstrip()}")
