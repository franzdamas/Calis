with open('app/src/main/java/com/example/ui/screens/StudentMainScreen.kt', 'r') as f:
    lines = f.readlines()

def replace_block(lines, start_trigger, end_trigger, replacement_lines):
    new_lines = []
    in_block = False
    i = 0
    while i < len(lines):
        if not in_block and start_trigger in lines[i]:
            new_lines.append(lines[i])
            # wait for text = {
            while i + 1 < len(lines) and "text = {" not in lines[i+1]:
                i += 1
                new_lines.append(lines[i])
            if i + 1 < len(lines) and "text = {" in lines[i+1]:
                i += 1
                new_lines.append(lines[i])
                in_block = True
                new_lines.extend(replacement_lines)
            i += 1
            continue
        
        if in_block:
            if end_trigger in lines[i]:
                in_block = False
                new_lines.append(lines[i])
            i += 1
            continue
            
        new_lines.append(lines[i])
        i += 1
    return new_lines

checkin_rep = """            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (checkInState == com.example.ui.viewmodel.StudentCheckInState.SUCCESS) {
                    Surface(shape = RoundedCornerShape(10.dp), color = StatusPresentGreen.copy(alpha = 0.12f), border = BorderStroke(1.dp, StatusPresentGreen.copy(alpha = 0.4f)), modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                        Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CheckCircle, null, tint = StatusPresentGreen, modifier = Modifier.size(22.dp))
                            Column {
                                Text("Attendance Marked!", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = StatusPresentGreen)
                                Text(checkInMessage, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                } else if (checkInState == com.example.ui.viewmodel.StudentCheckInState.FAILED) {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Icon(Icons.Default.Warning, "Failed", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(64.dp))
                        Text(checkInMessage, style = MaterialTheme.typography.bodyLarge, textAlign = androidx.compose.ui.text.style.TextAlign.Center, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.error)
                    }
                } else {
                    Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp, color = MaterialTheme.colorScheme.primary)
                        Text("Processing Transaction...", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
""".splitlines(keepends=True)

task_rep = """            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (taskSubmitState == com.example.ui.viewmodel.StudentTaskSubmitState.SUCCESS) {
                    Surface(shape = RoundedCornerShape(10.dp), color = StatusPresentGreen.copy(alpha = 0.12f), border = BorderStroke(1.dp, StatusPresentGreen.copy(alpha = 0.4f)), modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                        Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CheckCircle, null, tint = StatusPresentGreen, modifier = Modifier.size(22.dp))
                            Column {
                                Text("Task Submitted!", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = StatusPresentGreen)
                                Text(taskSubmitMessage, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                } else if (taskSubmitState == com.example.ui.viewmodel.StudentTaskSubmitState.FAILED) {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Icon(Icons.Default.Warning, "Failed", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(64.dp))
                        Text(taskSubmitMessage, style = MaterialTheme.typography.bodyLarge, textAlign = androidx.compose.ui.text.style.TextAlign.Center, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.error)
                    }
                } else {
                    Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp, color = MaterialTheme.colorScheme.primary)
                        Text("Processing Transaction...", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
""".splitlines(keepends=True)

join_rep = """            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (regDialogStep == com.example.ui.viewmodel.StudentRegDialogStep.CONNECTING || registrationStatus != "NOT_REGISTERED") {
                    if (registrationStatus == "APPROVED") {
                        Surface(shape = RoundedCornerShape(10.dp), color = StatusPresentGreen.copy(alpha = 0.12f), border = BorderStroke(1.dp, StatusPresentGreen.copy(alpha = 0.4f)), modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.CheckCircle, null, tint = StatusPresentGreen, modifier = Modifier.size(22.dp))
                                Column {
                                    Text("Registration Complete!", style = MaterialTheme.typography.bodyMedium, color = StatusPresentGreen, fontWeight = FontWeight.Bold)
                                    Text("You are now enrolled in ${registeredClassroom?.classroomName ?: "the classroom"}. Schedule and tasks are loaded.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
                                }
                            }
                        }
                    } else if (registrationStatus == "REJECTED") {
                        Surface(shape = RoundedCornerShape(10.dp), color = MaterialTheme.colorScheme.errorContainer, modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Cancel, null, tint = MaterialTheme.colorScheme.error)
                                Text("The Teacher rejected your registration request.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onErrorContainer, fontWeight = FontWeight.Bold)
                            }
                        }
                    } else {
                        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp, color = MaterialTheme.colorScheme.primary)
                            Text("Processing Transaction...", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                    if (joinErrorMessage != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(joinErrorMessage ?: "", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall, textAlign = androidx.compose.ui.text.style.TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    }
                } else {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("Available Calis Devices (${joinableDevices.size})", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("Scanning...", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                            CircularProgressIndicator(modifier = Modifier.size(14.dp), strokeWidth = 2.dp, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth().height(220.dp)) {
                        if (joinableDevices.isEmpty()) {
                            Surface(shape = RoundedCornerShape(10.dp), color = MaterialTheme.colorScheme.surface, modifier = Modifier.fillMaxSize()) {
                                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                    Icon(Icons.Default.Bluetooth, null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(28.dp))
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text("Scanning for Calis Devices...", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center)
                                }
                            }
                        } else {
                            LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                items(joinableDevices) { device ->
                                    val isBusy = device.state == com.example.ble.DeviceState.SESSION_ACTIVE_BUSY
                                    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))) {
                                        Row(modifier = Modifier.padding(12.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(device.name.ifBlank { "Calis Device" }, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                                    Text(device.address, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                                    Surface(shape = RoundedCornerShape(50), color = if (isBusy) StatusLateAmber.copy(alpha = 0.2f) else StatusPresentGreen.copy(alpha = 0.2f)) {
                                                        Text(if (isBusy) "0x0111 Busy" else "0x0011 Active", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 9.sp, fontWeight = FontWeight.Bold, color = if (isBusy) StatusLateAmber else StatusPresentGreen)
                                                    }
                                                }
                                            }
                                            Button(
                                                onClick = { viewModel.initiateJoinClassroom(device.address, device.name) },
                                                shape = RoundedCornerShape(8.dp),
                                                enabled = !isBusy
                                            ) {
                                                Text(if (isBusy) "Slots Full" else "Join")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (joinErrorMessage != null) {
                        Text(joinErrorMessage ?: "", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall, textAlign = androidx.compose.ui.text.style.TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
""".splitlines(keepends=True)


lines = replace_block(lines, "if (checkInState != com.example.ui.viewmodel.StudentCheckInState.IDLE)", "        },", checkin_rep)
lines = replace_block(lines, "if (taskSubmitState != com.example.ui.viewmodel.StudentTaskSubmitState.IDLE)", "        },", task_rep)
lines = replace_block(lines, "val joinableDevices = scannedDevices.filter", "        confirmButton = {", join_rep)


# Now remove fun RegistrationStepItem block
import re
content = "".join(lines)
content = re.sub(r"@Composable\nfun RegistrationStepItem\([\s\S]*?\n\}\n\n", "\n", content)

with open('app/src/main/java/com/example/ui/screens/StudentMainScreen.kt', 'w') as f:
    f.write(content)
