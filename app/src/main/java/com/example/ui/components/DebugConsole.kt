package com.example.ui.components

import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ble.TeacherBleManager
import com.example.ble.StudentBleManager
import com.example.ble.BleManager
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun DebugConsolePopup() {
    val isTeacherVisible by TeacherBleManager.isDebugConsoleVisible.collectAsState()
    val isStudentVisible by StudentBleManager.isDebugConsoleVisible.collectAsState()
    val isVisible = isTeacherVisible || isStudentVisible
    if (!isVisible) return

    var isMinimized by remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthPx = remember(configuration.screenWidthDp, density) {
        with(density) { configuration.screenWidthDp.dp.toPx() }
    }
    val screenHeightPx = remember(configuration.screenHeightDp, density) {
        with(density) { configuration.screenHeightDp.dp.toPx() }
    }

    val expandedWidthDp = remember(configuration.screenWidthDp) {
        minOf(340.dp, (configuration.screenWidthDp - 20).dp)
    }
    val expandedHeightDp = remember(configuration.screenHeightDp) {
        minOf(450.dp, (configuration.screenHeightDp - 80).dp)
    }

    val expandedWidthPx = remember(expandedWidthDp, density) {
        with(density) { expandedWidthDp.toPx() }
    }
    val expandedHeightPx = remember(expandedHeightDp, density) {
        with(density) { expandedHeightDp.toPx() }
    }

    val minBubbleSizePx = remember(density) { with(density) { 48.dp.toPx() } }

    val initialX = remember(screenWidthPx) { screenWidthPx - minBubbleSizePx - with(density) { 16.dp.toPx() } }
    val initialY = remember(screenHeightPx) { screenHeightPx - minBubbleSizePx - with(density) { 100.dp.toPx() } }
    var offsetX by remember { mutableFloatStateOf(initialX) }
    var offsetY by remember { mutableFloatStateOf(initialY) }

    val dragModifier = Modifier.pointerInput(isMinimized, screenWidthPx, screenHeightPx, expandedWidthPx, expandedHeightPx) {
        detectDragGestures { change, dragAmount ->
            change.consume()
            val curWidthPx = if (isMinimized) minBubbleSizePx else expandedWidthPx
            val curHeightPx = if (isMinimized) minBubbleSizePx else expandedHeightPx
            val maxOffsetX = (screenWidthPx - curWidthPx - with(density) { 4.dp.toPx() }).coerceAtLeast(0f)
            val maxOffsetY = (screenHeightPx - curHeightPx - with(density) { 4.dp.toPx() }).coerceAtLeast(0f)

            offsetX = (offsetX + dragAmount.x).coerceIn(4f, maxOffsetX)
            offsetY = (offsetY + dragAmount.y).coerceIn(4f, maxOffsetY)
        }
    }

    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
    ) {
        if (isMinimized) {
            Box(modifier = dragModifier) {
                MinimizedDebugConsole(
                    onExpand = {
                        val maxExpandedX = (screenWidthPx - expandedWidthPx - with(density) { 8.dp.toPx() }).coerceAtLeast(0f)
                        val maxExpandedY = (screenHeightPx - expandedHeightPx - with(density) { 8.dp.toPx() }).coerceAtLeast(0f)
                        if (offsetX > maxExpandedX) {
                            offsetX = maxExpandedX
                        }
                        if (offsetY > maxExpandedY) {
                            offsetY = maxExpandedY
                        }
                        isMinimized = false
                    }
                )
            }
        } else {
            ExpandedDebugConsole(
                onMinimize = { isMinimized = true },
                dragModifier = dragModifier,
                widthDp = expandedWidthDp,
                heightDp = expandedHeightDp
            )
        }
    }
}

@Composable
fun MinimizedDebugConsole(onExpand: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier
            .size(48.dp),
        onClick = onExpand
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text("DBG", style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun ExpandedDebugConsole(
    onMinimize: () -> Unit,
    dragModifier: Modifier = Modifier,
    widthDp: androidx.compose.ui.unit.Dp = 340.dp,
    heightDp: androidx.compose.ui.unit.Dp = 450.dp
) {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE) }
    val userRole = prefs.getString("user_role", null)
    val isStudentVisible by StudentBleManager.isDebugConsoleVisible.collectAsState()
    val isTeacherVisible by TeacherBleManager.isDebugConsoleVisible.collectAsState()
    val isStudent = userRole == "STUDENT" || (isStudentVisible && !isTeacherVisible)

    val studentBle = remember { StudentBleManager.getInstance(context) }
    val teacherBle = remember { TeacherBleManager.getInstance(context) }

    val studentLogs by studentBle.debugLogs.collectAsState()
    val teacherLogs by teacherBle.debugLogs.collectAsState()

    val studentConnState by studentBle.connectionState.collectAsState()
    val teacherConnState by teacherBle.connectionState.collectAsState()

    val studentRssi by studentBle.currentRssi.collectAsState()
    val teacherRssi by teacherBle.currentRssi.collectAsState()

    val logs = remember(isStudent, studentLogs, teacherLogs) {
        if (isStudent) {
            studentLogs.map { TeacherBleManager.DebugLog(it.timestamp, it.message, it.isError, it.isRx, it.isTx) }
        } else {
            teacherLogs
        }
    }

    val statusText = if (isStudent) {
        when (val state = studentConnState) {
            is StudentBleManager.ConnectionState.Connected -> "ST Connected: ${state.address}"
            is StudentBleManager.ConnectionState.Connecting -> "ST Connecting..."
            is StudentBleManager.ConnectionState.Disconnecting -> "ST Disconnecting..."
            is StudentBleManager.ConnectionState.Disconnected -> "ST Disconnected"
            is StudentBleManager.ConnectionState.Error -> "ST Error"
            is StudentBleManager.ConnectionState.Scanning -> "ST Scanning..."
        }
    } else {
        when (val state = teacherConnState) {
            is TeacherBleManager.ConnectionState.Connected -> "TC Connected: ${state.address}"
            is TeacherBleManager.ConnectionState.Connecting -> "TC Connecting..."
            is TeacherBleManager.ConnectionState.Disconnecting -> "TC Disconnecting..."
            is TeacherBleManager.ConnectionState.Disconnected -> "TC Disconnected"
            is TeacherBleManager.ConnectionState.Error -> "TC Error"
            is TeacherBleManager.ConnectionState.Scanning -> "TC Scanning..."
        }
    }

    val statusColor = if (isStudent) {
        if (studentConnState is StudentBleManager.ConnectionState.Connected) Color(0xFF4CAF50) else Color(0xFFFFB300)
    } else {
        if (teacherConnState is TeacherBleManager.ConnectionState.Connected) Color(0xFF4CAF50) else Color(0xFFFFB300)
    }

    val isConnected = if (isStudent) {
        studentConnState is StudentBleManager.ConnectionState.Connected
    } else {
        teacherConnState is TeacherBleManager.ConnectionState.Connected
    }

    val rssi = if (isStudent) studentRssi else teacherRssi

    var customPacket by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(logs.size) {
        if (logs.isNotEmpty()) {
            listState.animateScrollToItem(logs.size - 1)
        }
    }

    LaunchedEffect(isConnected) {
        while (isConnected) {
            if (isStudent) studentBle.requestRssi() else teacherBle.requestRssi()
            kotlinx.coroutines.delay(2000)
        }
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFF1E1E1E),
        shadowElevation = 0.dp,
        modifier = Modifier
            .width(widthDp)
            .height(heightDp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {}
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2D2D2D))
                    .padding(start = 12.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .then(dragModifier)
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        if (isStudent) "ST BLE Terminal" else "TC BLE Terminal",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        fontFamily = FontFamily.Monospace
                    )
                }
                IconButton(onClick = {
                    TeacherBleManager.isDebugConsoleVisible.value = false
                    StudentBleManager.isDebugConsoleVisible.value = false
                    onMinimize()
                }, modifier = Modifier.size(36.dp)) {
                    Icon(Icons.Default.KeyboardArrowDown, "Minimize", tint = Color.LightGray)
                }
            }

            // Status Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF333333))
                    .then(dragModifier)
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(statusText, color = statusColor, style = MaterialTheme.typography.labelSmall, fontFamily = FontFamily.Monospace)
                
                if (isConnected) {
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
                            if (isStudent) {
                                studentBle.sendRawPacket(customPacket)
                            } else {
                                teacherBle.sendRawPacket(customPacket)
                            }
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
}

private fun saveLogs(context: Context, logs: List<TeacherBleManager.DebugLog>) {
    try {
        val fileName = "Calis_Debug_Log_${System.currentTimeMillis()}.txt"
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        if (dir != null && !dir.exists()) {
            dir.mkdirs()
        }
        val file = File(dir, fileName)
        val fos = FileOutputStream(file)
        
        val sb = StringBuilder()
        logs.forEach { log ->
            val time = SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS", Locale.US).format(Date(log.timestamp))
            val prefix = when {
                log.isError -> "[ERROR]"
                log.isRx -> "[RX]"
                log.isTx -> "[TX]"
                else -> "[INFO]"
            }
            sb.append("$time $prefix ${log.message}\n")
        }
        
        fos.write(sb.toString().toByteArray())
        fos.close()
        Toast.makeText(context, "Log saved to Documents: $fileName", Toast.LENGTH_LONG).show()
    } catch (e: Exception) {
        Toast.makeText(context, "Failed to save log: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun VerboseLogOverlay() {
    // Redundant lower verbose message overlay removed; top banner overlay in Teacher/Student screens is used instead.
}
