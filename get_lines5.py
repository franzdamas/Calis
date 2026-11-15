with open('app/src/main/java/com/example/ui/screens/StudentMainScreen.kt', 'r') as f:
    lines = f.readlines()

for i, line in enumerate(lines[1005:1020], 1006):
    print(f"{i}: {line.rstrip()}")

print("---")
for i, line in enumerate(lines[2100:2115], 2101):
    print(f"{i}: {line.rstrip()}")
