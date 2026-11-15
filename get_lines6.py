with open('app/src/main/java/com/example/ui/screens/StudentMainScreen.kt', 'r') as f:
    lines = f.readlines()

for i, line in enumerate(lines[2280:2330], 2281):
    print(f"{i}: {line.rstrip()}")
