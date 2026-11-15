with open('app/src/main/java/com/example/ui/screens/StudentMainScreen.kt', 'r') as f:
    lines = f.readlines()

for i, line in enumerate(lines[590:610], 591):
    print(f"{i}: {line.rstrip()}")

print("---")
for i, line in enumerate(lines[680:710], 681):
    print(f"{i}: {line.rstrip()}")
