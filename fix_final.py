with open('app/src/main/java/com/example/ui/screens/StudentMainScreen.kt', 'r') as f:
    lines = f.readlines()

new_lines = []
i = 0
while i < len(lines):
    line = lines[i]
    
    # 1. CheckInDialog cleanup
    if line.strip() == "} else if (checkInState == com.example.ui.viewmodel.StudentCheckInState.FAILED) {":
        # We are around line 261.
        new_lines.append(line)
        i += 1
        continue
    
    # Let's just delete by line numbers as we verified them
    if 272 <= i <= 538:
        i += 1
        continue
        
    if 597 <= i <= 830:
        i += 1
        continue
        
    if "confirmButton = {" in line and "if (registrationStatus == \"APPROVED\" || registrationStatus == \"REJECTED\") {" in lines[min(i+1, len(lines)-1)]:
        # This is JoinDialog confirmButton.
        new_lines.append("        },\n")
        new_lines.append(line)
        i += 1
        continue
        
    new_lines.append(line)
    i += 1
    
with open('app/src/main/java/com/example/ui/screens/StudentMainScreen.kt', 'w') as f:
    f.writelines(new_lines)
