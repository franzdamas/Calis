sed -i '414,617c\
@Composable\
fun TeacherDashboardView(\
    classroom: ClassroomEntity?,\
    connectionState: BleManager.ConnectionState,\
    deviceState: DeviceState,\
    scannedDevices: List<BleManager.ScannedDevice>,\
    onConnectDevice: (String) -> Unit,\
    onDisconnectDevice: () -> Unit,\
    onScanDevices: () -> Unit,\
    onStartSession: () -> Unit,\
    onEndSession: () -> Unit,\
    onUnregister: () -> Unit\
) {\
    val context = LocalContext.current\
    var hasPermissions by remember { mutableStateOf(BlePermissionUtils.hasPermissions(context)) }\
    var isBtEnabled by remember { mutableStateOf(BlePermissionUtils.isBluetoothEnabled(context)) }\
    val permissionLauncher = rememberLauncherForActivityResult(\
        ActivityResultContracts.RequestMultiplePermissions()\
    ) { _ ->\
        hasPermissions = BlePermissionUtils.hasPermissions(context)\
        isBtEnabled = BlePermissionUtils.isBluetoothEnabled(context)\
    }\
    val enableBtLauncher = rememberLauncherForActivityResult(\
        ActivityResultContracts.StartActivityForResult()\
    ) { _ ->\
        isBtEnabled = BlePermissionUtils.isBluetoothEnabled(context)\
    }\
    Column(\
        modifier = Modifier\
            .fillMaxSize()\
            .verticalScroll(rememberScrollState())\
            .padding(16.dp),\
        verticalArrangement = Arrangement.spacedBy(16.dp)\
    ) {\
        if (!hasPermissions) {\
            Card(\
                modifier = Modifier.fillMaxWidth(),\
                shape = RoundedCornerShape(12.dp),\
                colors = CardDefaults.cardColors(\
                    containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.35f)\
                ),\
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.5f))\
            ) {\
                Column(\
                    modifier = Modifier.padding(16.dp),\
                    verticalArrangement = Arrangement.spacedBy(10.dp)\
                ) {\
                    Row(\
                        verticalAlignment = Alignment.CenterVertically,\
                        horizontalArrangement = Arrangement.spacedBy(10.dp)\
                    ) {\
                        Icon(\
                            imageVector = Icons.Default.BluetoothDisabled,\
                            contentDescription = null,\
                            tint = MaterialTheme.colorScheme.error\
                        )\
                        Text(\
                            text = "Connection Requirement",\
                            style = MaterialTheme.typography.titleSmall,\
                            fontWeight = FontWeight.Bold,\
                            color = MaterialTheme.colorScheme.onErrorContainer\
                        )\
                    }\
                    val reqText = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {\
                        "Nearby Devices permission is required to search and connect to Calis Hardware receivers."\
                    } else {\
                        "Location permission is required to search and locate nearby Calis Hardware receivers."\
                    }\
                    Text(\
                        text = reqText,\
                        style = MaterialTheme.typography.bodySmall,\
                        color = MaterialTheme.colorScheme.onSurfaceVariant\
                    )\
                    Button(\
                        onClick = { permissionLauncher.launch(BlePermissionUtils.getRequiredPermissions()) },\
                        modifier = Modifier\
                            .align(Alignment.End)\
                            .testTag("request_ble_permission_tile_button"),\
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),\
                        shape = RoundedCornerShape(8.dp)\
                    ) {\
                        Text("Allow Permission", color = MaterialTheme.colorScheme.onError, fontWeight = FontWeight.Bold)\
                    }\
                }\
            }\
        } else {\
            if (classroom != null) {\
                Card(\
                    modifier = Modifier.fillMaxWidth(),\
                    shape = RoundedCornerShape(12.dp),\
                    colors = CardDefaults.cardColors(\
                        containerColor = MaterialTheme.colorScheme.surface\
                    ),\
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)\
                ) {\
                    Column(\
                        modifier = Modifier.padding(20.dp),\
                        verticalArrangement = Arrangement.spacedBy(16.dp)\
                    ) {\
                        Row(\
                            modifier = Modifier.fillMaxWidth(),\
                            horizontalArrangement = Arrangement.SpaceBetween,\
                            verticalAlignment = Alignment.CenterVertically\
                        ) {\
                            Text(\
                                text = classroom.classroomName,\
                                style = MaterialTheme.typography.titleMedium,\
                                fontWeight = FontWeight.ExtraBold,\
                                color = MaterialTheme.colorScheme.primary\
                            )\
                            Surface(\
                                shape = RoundedCornerShape(50),\
                                color = if (classroom.isActiveSession) StatusPresentGreen.copy(alpha = 0.2f) else StatusAbsentRed.copy(alpha = 0.2f)\
                            ) {\
                                Text(\
                                    text = if (classroom.isActiveSession) "SESSION OPEN" else "SESSION CLOSED",\
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),\
                                    style = MaterialTheme.typography.labelSmall,\
                                    fontWeight = FontWeight.Bold,\
                                    color = if (classroom.isActiveSession) StatusPresentGreen else StatusAbsentRed\
                                )\
                            }\
                        }\
                        Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))\
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {\
                            Text(\
                                text = "Classroom ID: ${classroom.cid}  •  Teacher: ${classroom.teacherName}",\
                                style = MaterialTheme.typography.bodySmall,\
                                color = MaterialTheme.colorScheme.onSurfaceVariant\
                            )\
                            Text(\
                                text = "Schedule: ${classroom.scheduleDays} | ${classroom.scheduleStartTime} - ${classroom.scheduleEndTime}",\
                                style = MaterialTheme.typography.bodySmall,\
                                color = MaterialTheme.colorScheme.onSurfaceVariant\
                            )\
                        }\
                    }\
                }\
            } else {\
                Card(\
                    modifier = Modifier.fillMaxWidth(),\
                    shape = RoundedCornerShape(12.dp),\
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))\
                ) {\
                    Text(\
                        text = "No Active Classroom. Please register a classroom to manage sessions.",\
                        modifier = Modifier.padding(20.dp),\
                        style = MaterialTheme.typography.bodyMedium,\
                        color = MaterialTheme.colorScheme.onSurfaceVariant\
                    )\
                }\
            }\
            \
            Spacer(modifier = Modifier.height(8.dp))\
            \
            Card(\
                modifier = Modifier.fillMaxWidth(),\
                shape = RoundedCornerShape(12.dp),\
                colors = CardDefaults.cardColors(\
                    containerColor = MaterialTheme.colorScheme.surface\
                ),\
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)\
            ) {\
                Column(\
                    modifier = Modifier.padding(20.dp),\
                    verticalArrangement = Arrangement.spacedBy(16.dp)\
                ) {\
                    Row(\
                        modifier = Modifier.fillMaxWidth(),\
                        horizontalArrangement = Arrangement.SpaceBetween,\
                        verticalAlignment = Alignment.CenterVertically\
                    ) {\
                        Text(\
                            text = "Calis Device Receiver",\
                            style = MaterialTheme.typography.titleMedium,\
                            fontWeight = FontWeight.Bold\
                        )\
                        Icon(\
                            imageVector = Icons.Default.Bluetooth,\
                            contentDescription = null,\
                            tint = MaterialTheme.colorScheme.primary,\
                            modifier = Modifier.size(24.dp)\
                        )\
                    }\
                    if (!isBtEnabled) {\
                        Button(\
                            onClick = { \
                                try {\
                                    enableBtLauncher.launch(android.content.Intent(android.bluetooth.BluetoothAdapter.ACTION_REQUEST_ENABLE))\
                                } catch (e: Exception) {\
                                    isBtEnabled = true\
                                }\
                            },\
                            modifier = Modifier.fillMaxWidth().height(48.dp),\
                            shape = RoundedCornerShape(8.dp),\
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)\
                        ) {\
                            Text("Enable Bluetooth", fontSize = 14.sp, fontWeight = FontWeight.Bold)\
                        }\
                    } else {\
                        if (classroom?.isActiveSession == true) {\
                            Button(\
                                onClick = onEndSession,\
                                colors = ButtonDefaults.buttonColors(\
                                    containerColor = StatusLateAmber,\
                                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer\
                                ),\
                                modifier = Modifier\
                                    .fillMaxWidth()\
                                    .height(48.dp)\
                                    .testTag("end_session_button"),\
                                shape = RoundedCornerShape(8.dp)\
                            ) {\
                                Icon(Icons.Default.Stop, contentDescription = null, modifier = Modifier.size(18.dp))\
                                Spacer(modifier = Modifier.width(6.dp))\
                                Text("Stop Session", fontSize = 14.sp, fontWeight = FontWeight.Bold)\
                            }\
                        } else {\
                            Button(\
                                onClick = onStartSession,\
                                colors = ButtonDefaults.buttonColors(\
                                    containerColor = StatusPresentGreen,\
                                    contentColor = MaterialTheme.colorScheme.onPrimary\
                                ),\
                                modifier = Modifier\
                                    .fillMaxWidth()\
                                    .height(48.dp)\
                                    .testTag("start_session_button"),\
                                shape = RoundedCornerShape(8.dp)\
                            ) {\
                                Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))\
                                Spacer(modifier = Modifier.width(6.dp))\
                                Text("Start Session", fontSize = 14.sp, fontWeight = FontWeight.Bold)\
                            }\
                        }\
                    }\
                }\
            }\
        }\
    }\
}
