import re

with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'r') as f:
    content = f.read()

new_expanded_console = """@Composable
fun ExpandedDebugConsole(onMinimize: () -> Unit, onClose: () -> Unit) {
    val context = LocalContext.current
    val bleManager = remember { BleManager.getInstance(context) }
    val logs by bleManager.debugLogs.collectAsState()
    val connState by bleManager.connectionState.collectAsState()
    val rssi by bleManager.currentRssi.collectAsState()

    var customPacket by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(logs.size) {
        if (logs.isNotEmpty()) {
            listState.animateScrollToItem(logs.size - 1)
        }
    }

    LaunchedEffect(connState) {
        while (connState is BleManager.ConnectionState.Connected) {
            bleManager.requestRssi()
            kotlinx.coroutines.delay(2000)
        }
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFF1E1E1E),
        modifier = Modifier
            .width(340.dp)
            .height(450.dp)
            .padding(16.dp)
            .shadow(16.dp, RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2D2D2D))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "BLE Terminal",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.Monospace
                )
                Row {
                    IconButton(onClick = onMinimize, modifier = Modifier.size(28.dp)) {
                        Icon(Icons.Default.KeyboardArrowDown, "Minimize", tint = Color.LightGray)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = onClose, modifier = Modifier.size(28.dp)) {
                        Icon(Icons.Default.Close, "Close", tint = Color.LightGray)
                    }
                }
            }

            // Status Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF333333))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val statusText = when(val state = connState) {
                    is BleManager.ConnectionState.Connected -> "Connected: ${state.address}"
                    is BleManager.ConnectionState.Connecting -> "Connecting..."
                    is BleManager.ConnectionState.Disconnected -> "Disconnected"
                    is BleManager.ConnectionState.Error -> "Error"
                    is BleManager.ConnectionState.Scanning -> "Scanning..."
                    else -> "Unknown"
                }
                val statusColor = if (connState is BleManager.ConnectionState.Connected) Color(0xFF4CAF50) else Color(0xFFFFB300)
                Text(statusText, color = statusColor, style = MaterialTheme.typography.labelSmall, fontFamily = FontFamily.Monospace)
                
                if (connState is BleManager.ConnectionState.Connected) {
                    Text("$rssi dBm", color = Color.White, style = MaterialTheme.typography.labelSmall, fontFamily = FontFamily.Monospace)
                }
            }

            // Log List (Chat Bubble Style)
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFF121212))
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(logs) { log ->
                    val time = SimpleDateFormat("HH:mm:ss.SSS", Locale.US).format(Date(log.timestamp))
                    
                    val alignment = when {
                        log.isTx -> Alignment.CenterEnd
                        log.isRx -> Alignment.CenterStart
                        else -> Alignment.Center
                    }
                    
                    val bgColor = when {
                        log.isError -> Color(0xFFB00020)
                        log.isTx -> Color(0xFF2E7D32) // Sent (Green)
                        log.isRx -> Color(0xFF1565C0) // Received (Blue)
                        else -> Color(0xFF424242) // System (Gray)
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = alignment
                    ) {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(bgColor)
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                            horizontalAlignment = if (log.isTx) Alignment.End else Alignment.Start
                        ) {
                            Text(
                                text = time,
                                color = Color.LightGray,
                                fontSize = 8.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            val cleanMsg = log.message.removePrefix("RX: ").removePrefix("TX: ").removePrefix("Sent raw to RX: ")
                            Text(
                                text = cleanMsg,
                                color = Color.White,
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                lineHeight = 14.sp
                            )
                        }
                    }
                }
            }

            // Input Area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2D2D2D))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = customPacket,
                    onValueChange = { customPacket = it },
                    modifier = Modifier.weight(1f).height(50.dp),
                    placeholder = { Text("Custom TX packet", color = Color.Gray, fontSize = 12.sp) },
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(color = Color.White, fontSize = 12.sp, fontFamily = FontFamily.Monospace),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF1E1E1E),
                        unfocusedContainerColor = Color(0xFF1E1E1E),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(25.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (customPacket.isNotEmpty()) {
                            bleManager.sendRawPacket(customPacket)
                            customPacket = ""
                        }
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF2E7D32), RoundedCornerShape(20.dp))
                ) {
                    Icon(Icons.Default.Send, "Send", tint = Color.White, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(6.dp))
                IconButton(
                    onClick = { saveLogs(context, logs) },
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF424242), RoundedCornerShape(20.dp))
                ) {
                    Icon(Icons.Default.Share, "Save Logs", tint = Color.White, modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}"""

pattern = r"@Composable\nfun ExpandedDebugConsole\(onMinimize: \(\) -> Unit, onClose: \(\) -> Unit\) \{[\s\S]*?private fun saveLogs"
new_content = re.sub(pattern, new_expanded_console + "\n\nprivate fun saveLogs", content)

with open('app/src/main/java/com/example/ui/components/DebugConsole.kt', 'w') as f:
    f.write(new_content)
