package com.example.ui.screens
import android.util.Log
import android.Manifest
import android.provider.Settings
import android.os.Build

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.zIndex
import androidx.compose.material.icons.filled.Info
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import kotlinx.coroutines.delay
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.ui.text.style.TextOverflow
import com.example.data.db.ApprovedStudentEntity
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.border
import kotlinx.coroutines.launch
import com.example.ble.StudentBleManager
import com.example.data.db.AttendanceEntity
import com.example.data.db.ClassroomEntity
import com.example.data.db.StudentProfileEntity
import com.example.data.db.TaskEntity
import com.example.data.db.TaskSubmissionEntity
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import com.example.ui.theme.AccentGradient
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Flag
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.isActive
import com.example.ui.components.SettingsDialog
import com.example.ui.theme.StatusAbsentRed
import com.example.ui.theme.StatusLateAmber
import com.example.ui.theme.StatusPresentGreen
import com.example.ui.viewmodel.AuthViewModel
import com.example.ui.viewmodel.StudentViewModel
import kotlinx.coroutines.flow.collectLatest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.runtime.DisposableEffect
import com.example.util.BlePermissionUtils
import com.example.ui.components.LazyTabContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentMainScreen(
    studentViewModel: StudentViewModel,
    authViewModel: AuthViewModel,
    onLogOut: () -> Unit,
    onAccountDeleted: () -> Unit
) {
    val context = LocalContext.current
    val prefs = remember(context) { context.getSharedPreferences("calis_app_prefs", android.content.Context.MODE_PRIVATE) }
    var showPrototypeDialog by remember {
        mutableStateOf(
            prefs.getBoolean("show_prototype_dialog_student", false) ||
            (!prefs.getBoolean("has_seen_prototype_dialog_student", false) && prefs.getString("user_role", "") == "STUDENT")
        )
    }
    var selectedTab by remember { mutableStateOf("HOME") }
    var settingsSubpage by remember { mutableStateOf<String?>(null) }
    var checkInSelectedLog by remember { mutableStateOf<AttendanceEntity?>(null) }
    var tasksSelectedTask by remember { mutableStateOf<TaskEntity?>(null) }
    val studentTasksListState = rememberSaveable(saver = LazyListState.Saver) { LazyListState() }
    var lastBackTime by remember { mutableStateOf(0L) }
    androidx.activity.compose.BackHandler(enabled = true) {
        if (checkInSelectedLog != null) {
            checkInSelectedLog = null
        } else if (tasksSelectedTask != null) {
            tasksSelectedTask = null
        } else if (selectedTab == "SETTINGS" && settingsSubpage != null) {
            settingsSubpage = null
        } else if (selectedTab == "ABOUT") {
            selectedTab = "HOME"
        } else if (selectedTab != "HOME") {
            selectedTab = "HOME"
        } else {
            val now = System.currentTimeMillis()
            if (now - lastBackTime < 2000) {
                (context as? android.app.Activity)?.finish()
            } else {
                lastBackTime = now
                android.widget.Toast.makeText(context, "Press back again to exit", android.widget.Toast.LENGTH_SHORT).show()
            }
        }
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var hasPermissions by remember { mutableStateOf(BlePermissionUtils.hasPermissions(context)) }
    var isBtEnabled by remember { mutableStateOf(BlePermissionUtils.isBluetoothEnabled(context)) }
    var isBtEnabling by remember { mutableStateOf(false) }
    var isNotificationGranted by remember { mutableStateOf(BlePermissionUtils.hasNotificationPermission(context)) }
    var isLocationEnabled by remember { mutableStateOf(BlePermissionUtils.isLocationEnabled(context)) }

    val enableBtLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        val enabled = BlePermissionUtils.isBluetoothEnabled(context)
        isBtEnabled = enabled
        if (enabled) {
            isBtEnabling = false
        }
    }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        isNotificationGranted = isGranted || BlePermissionUtils.hasNotificationPermission(context)
        if (isNotificationGranted) {
            com.example.util.ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(context)
        }
    }

    val onRequestNotification: () -> Unit = {
        BlePermissionUtils.requestNotificationPermission(context, notificationPermissionLauncher)
    }

    LaunchedEffect(Unit) {
        val activity = context as? android.app.Activity
        val isTap = activity?.intent?.getBooleanExtra("check_in_reminder_tap", false) == true
        val targetCid = activity?.intent?.getStringExtra("classroom_id") ?: studentViewModel.registeredClassroom.value?.cid ?: ""
        if (isTap) {
            activity?.intent?.removeExtra("check_in_reminder_tap")
            val isOlderThanAndroid12 = android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S
            val currentHasPermissions = BlePermissionUtils.hasPermissions(context)
            val currentIsBtEnabled = BlePermissionUtils.isBluetoothEnabled(context)
            val currentIsLocEnabled = BlePermissionUtils.isLocationEnabled(context)

            // Sequential connection requirements check upon tapping check-in notification
            if (!currentHasPermissions) {
                val act = BlePermissionUtils.findActivity(context)
                if (act != null) {
                    androidx.core.app.ActivityCompat.requestPermissions(
                        act,
                        BlePermissionUtils.getRequiredPermissions(),
                        1
                    )
                }
            } else if (!currentIsBtEnabled) {
                isBtEnabling = true
                BlePermissionUtils.requestEnableBluetooth(
                    activity = BlePermissionUtils.findActivity(context),
                    launcher = enableBtLauncher,
                    onEnabling = { isBtEnabling = true }
                )
            } else if (isOlderThanAndroid12 && !currentIsLocEnabled) {
                try {
                    val intent = android.content.Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                } catch (e: Exception) {
                    // Ignore
                }
            } else {
                // All connection requirements granted and enabled
                if (targetCid.isNotBlank()) {
                    com.example.util.ReminderNotificationHelper.updateNotificationToFindingCalisDevice(context, targetCid)
                }
                studentViewModel.startBleScan(forceRestart = true)
            }
        }
        com.example.util.ReminderNotificationHelper.checkAndPostCheckInReminder(context)
    }

    LaunchedEffect(isBtEnabling) {
        if (isBtEnabling) {
            var attempts = 0
            while (attempts < 20 && isBtEnabling) {
                kotlinx.coroutines.delay(500L)
                if (BlePermissionUtils.isBluetoothEnabled(context)) {
                    isBtEnabled = true
                    isBtEnabling = false
                    break
                }
                attempts++
            }
            if (isBtEnabling) {
                isBtEnabled = BlePermissionUtils.isBluetoothEnabled(context)
                isBtEnabling = false
            }
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, context) {
        val filter = android.content.IntentFilter(android.bluetooth.BluetoothAdapter.ACTION_STATE_CHANGED)
        filter.addAction(android.location.LocationManager.PROVIDERS_CHANGED_ACTION)
        val receiver = object : android.content.BroadcastReceiver() {
            override fun onReceive(ctx: android.content.Context, intent: android.content.Intent) {
                if (intent.action == android.bluetooth.BluetoothAdapter.ACTION_STATE_CHANGED) {
                    val state = intent.getIntExtra(android.bluetooth.BluetoothAdapter.EXTRA_STATE, android.bluetooth.BluetoothAdapter.ERROR)
                    if (state == android.bluetooth.BluetoothAdapter.STATE_TURNING_ON) {
                        isBtEnabling = true
                    } else if (state == android.bluetooth.BluetoothAdapter.STATE_OFF || state == android.bluetooth.BluetoothAdapter.STATE_TURNING_OFF) {
                        isBtEnabling = false
                        isBtEnabled = false
                        com.example.util.ReminderNotificationHelper.checkAndPostCheckInReminder(context)
                    } else if (state == android.bluetooth.BluetoothAdapter.STATE_ON) {
                        isBtEnabling = false
                        isBtEnabled = true
                        if (hasPermissions) {
                            studentViewModel.startBleScan(forceRestart = true)
                        }
                    } else {
                        val enabled = BlePermissionUtils.isBluetoothEnabled(context)
                        isBtEnabled = enabled
                        if (enabled) isBtEnabling = false
                        com.example.util.ReminderNotificationHelper.checkAndPostCheckInReminder(context)
                    }
                } else if (intent.action == android.location.LocationManager.PROVIDERS_CHANGED_ACTION) {
                    isLocationEnabled = BlePermissionUtils.isLocationEnabled(context)
                    com.example.util.ReminderNotificationHelper.checkAndPostCheckInReminder(context)
                }
            }
        }
        androidx.core.content.ContextCompat.registerReceiver(
            context,
            receiver,
            filter,
            androidx.core.content.ContextCompat.RECEIVER_EXPORTED
        )

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                hasPermissions = BlePermissionUtils.hasPermissions(context)
                val enabled = BlePermissionUtils.isBluetoothEnabled(context)
                isBtEnabled = enabled
                if (enabled) isBtEnabling = false
                isNotificationGranted = BlePermissionUtils.hasNotificationPermission(context)
                isLocationEnabled = BlePermissionUtils.isLocationEnabled(context)
                com.example.util.ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(context)
                if (isBtEnabled && hasPermissions) {
                    val connState = studentViewModel.bleManager.connectionState.value
                    if (connState !is StudentBleManager.ConnectionState.Connected && connState !is StudentBleManager.ConnectionState.Connecting) {
                        studentViewModel.startBleScan(forceRestart = false)
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            try {
                context.unregisterReceiver(receiver)
            } catch (e: Exception) {
                // ignore
            }
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


    val profile by studentViewModel.currentProfile.collectAsState()
    val connectionState by studentViewModel.connectionState.collectAsState()
    val deviceState by studentViewModel.deviceState.collectAsState()
    val scannedDevices by studentViewModel.scannedDevices.collectAsState()
    val registeredClassroom by studentViewModel.registeredClassroom.collectAsState()
    val registrationStatus by studentViewModel.registrationStatus.collectAsState()
    val syncState by studentViewModel.syncState.collectAsState()
    val syncMessage by studentViewModel.syncMessage.collectAsState()
    val isRegisteredSessionActive by studentViewModel.isRegisteredSessionActive.collectAsState()
    val registeredDeviceState by studentViewModel.registeredDeviceState.collectAsState()
    val isRegisteredDeviceInRange by studentViewModel.isRegisteredDeviceInRange.collectAsState()
    val registeredDeviceRawHex by studentViewModel.registeredDeviceRawHex.collectAsState()
    val isClassroomExpired by studentViewModel.isClassroomExpired.collectAsState()
    val showClassroomExpiredDialog by studentViewModel.showClassroomExpiredDialog.collectAsState()

    val myAttendanceLogs by studentViewModel.myAttendanceLogs.collectAsState()
    val todayYmd = remember { java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date()) }
    val todayMdy = remember { java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date()) }
    val hasCheckedInToday = remember(myAttendanceLogs) {
        myAttendanceLogs.any { log ->
            val logDateFromTs = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date(log.timestamp * 1000L))
            logDateFromTs == todayYmd || log.date.startsWith(todayYmd) || log.date.startsWith(todayMdy)
        }
    }
    val mySubmissions by studentViewModel.mySubmissions.collectAsState()
    val classroomTasks by studentViewModel.classroomTasks.collectAsState()
    val classroomApprovedStudents by studentViewModel.classroomApprovedStudents.collectAsState()
    val classroomAttendanceLogs by studentViewModel.classroomAttendanceLogs.collectAsState()

    val checkInState by studentViewModel.checkInState.collectAsState()
    val checkInMessage by studentViewModel.checkInMessage.collectAsState()
    val checkInTasks by studentViewModel.checkInTasks.collectAsState()
    val lastTxResponse by studentViewModel.lastTxResponse.collectAsState()
    val checkInLogs by studentViewModel.checkInLogs.collectAsState()

    val taskSubmitState by studentViewModel.taskSubmitState.collectAsState()
    val taskSubmitMessage by studentViewModel.taskSubmitMessage.collectAsState()
    val taskSubmitLogs by studentViewModel.taskSubmitLogs.collectAsState()
    val taskSubmitTargetTask by studentViewModel.taskSubmitTargetTask.collectAsState()
    val taskSubmitScore by studentViewModel.taskSubmitScore.collectAsState()
    val taskSubmitMarkedTimestamp by studentViewModel.taskSubmitMarkedTimestamp.collectAsState()

    var submitTaskDialogFor by remember { mutableStateOf<TaskEntity?>(null) }
    var showJoinDialog by remember { mutableStateOf(false) }

    var isRefreshingHome by remember { mutableStateOf(false) }
    var isRefreshingTasks by remember { mutableStateOf(false) }

    if (showPrototypeDialog && selectedTab == "HOME") {
        com.example.ui.components.PrototypeDisclaimerDialog(
            onDismiss = {
                showPrototypeDialog = false
                prefs.edit()
                    .putBoolean("has_seen_prototype_dialog_student", true)
                    .putBoolean("show_prototype_dialog_student", false)
                    .apply()
            }
        )
    }

    if (showClassroomExpiredDialog) {
        AlertDialog(
            onDismissRequest = { studentViewModel.dismissClassroomExpired() },
            tonalElevation = 0.dp,
            title = {
                Text(
                    text = "Classroom Expired",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
            },
            text = {
                Text(
                    text = "This classroom has expired. The Calis Device has been unregistered or renamed by the teacher back to Factory Mode.",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                Button(
                    onClick = { studentViewModel.dismissClassroomExpired() },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("OK", fontWeight = FontWeight.Bold)
                }
            }
        )
    }

    if (checkInState != com.example.ui.viewmodel.StudentCheckInState.IDLE) {
        AlertDialog(
            onDismissRequest = {
                if (checkInState == com.example.ui.viewmodel.StudentCheckInState.SUCCESS || checkInState == com.example.ui.viewmodel.StudentCheckInState.FAILED) {
                    studentViewModel.resetCheckInState()
                }
            },
            tonalElevation = 0.dp,
            title = {
                Text(
                    text = "Attendance Check-In Progress",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
            Column(
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
            },
            confirmButton = {
                if (checkInState == com.example.ui.viewmodel.StudentCheckInState.SUCCESS || checkInState == com.example.ui.viewmodel.StudentCheckInState.FAILED) {
                    Button(
                        onClick = { studentViewModel.resetCheckInState() },
                        modifier = Modifier.testTag("close_checkin_dialog")
                    ) {
                        Text(if (checkInState == com.example.ui.viewmodel.StudentCheckInState.SUCCESS) "Proceed to Main UI" else "Close")
                    }
                }
            }
        )
    }

    if (taskSubmitState != com.example.ui.viewmodel.StudentTaskSubmitState.IDLE &&
        taskSubmitState != com.example.ui.viewmodel.StudentTaskSubmitState.SUCCESS) {
        AlertDialog(
            onDismissRequest = {
                if (taskSubmitState == com.example.ui.viewmodel.StudentTaskSubmitState.FAILED) {
                    studentViewModel.resetTaskSubmitState()
                }
            },
            tonalElevation = 0.dp,
            title = {
                Text(
                    text = "Task Score Submission Progress",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (taskSubmitState == com.example.ui.viewmodel.StudentTaskSubmitState.FAILED) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Icon(
                                Icons.Default.Warning,
                                "Failed",
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(64.dp)
                            )
                            Text(
                                text = taskSubmitMessage,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Processing Transaction...",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            },
            confirmButton = {
                if (taskSubmitState == com.example.ui.viewmodel.StudentTaskSubmitState.FAILED) {
                    Button(
                        onClick = {
                            studentViewModel.resetTaskSubmitState()
                        },
                        modifier = Modifier.testTag("close_task_submit_dialog")
                    ) {
                        Text("Close")
                    }
                }
            }
        )
    }

    var topMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        studentViewModel.userMessage.collectLatest { msg ->
            topMessage = msg
        }
    }

    LaunchedEffect(topMessage) {
        if (topMessage != null) {
            kotlinx.coroutines.delay(4500)
            topMessage = null
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.surface,
                drawerContentColor = MaterialTheme.colorScheme.onSurface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 16.dp)
                ) {
                    val formattedStudentName = remember(profile?.fullName) {
                        com.example.util.BlePermissionUtils.formatStudentNameWithMiddleInitial(
                            profile?.fullName?.trim()?.ifBlank { "Student" } ?: "Student",
                            context
                        ).ifBlank { "Student" }
                    }

                    // --- RIBBON MENU HEADER ---
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 16.dp)
                    ) {
                        androidx.compose.foundation.Image(
                            painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.logo_calis_inverted),
                            contentDescription = "Calis Logo",
                            modifier = Modifier.size(120.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = formattedStudentName,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.School, contentDescription = null) },
                        label = { Text("Classroom & Device Control") },
                        selected = selectedTab == "HOME",
                        onClick = {
                            selectedTab = "HOME"
                            settingsSubpage = null
                            checkInSelectedLog = null
                            tasksSelectedTask = null
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = null) },
                        label = { Text("Profile") },
                        selected = selectedTab == "PROFILE",
                        onClick = {
                            selectedTab = "PROFILE"
                            settingsSubpage = null
                            checkInSelectedLog = null
                            tasksSelectedTask = null
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                        label = { Text("Settings") },
                        selected = selectedTab == "SETTINGS",
                        onClick = {
                            selectedTab = "SETTINGS"
                            settingsSubpage = null
                            checkInSelectedLog = null
                            tasksSelectedTask = null
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Info, contentDescription = null) },
                        label = { Text("About") },
                        selected = selectedTab == "ABOUT",
                        onClick = {
                            selectedTab = "ABOUT"
                            settingsSubpage = null
                            checkInSelectedLog = null
                            tasksSelectedTask = null
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                contentDescription = "Log Out",
                                tint = MaterialTheme.colorScheme.error
                            )
                        },
                        label = {
                            Text(
                                text = "Log Out",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.error
                            )
                        },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            authViewModel.logout {
                                onLogOut()
                            }
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                            .padding(bottom = 12.dp)
                            .testTag("logout_button")
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        when {
                            selectedTab == "ABOUT" -> {
                                Text(
                                    text = "About",
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 25.sp, fontWeight = FontWeight.ExtraBold),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            selectedTab == "SETTINGS" && settingsSubpage != null -> {
                                Text(
                                    text = when (settingsSubpage) {
                                        "SECURITY" -> "Security & Startup"
                                        "NOTIFICATIONS" -> "Notifications"
                                        "ACTIONS" -> "Account Actions"
                                        else -> "Settings"
                                    },
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 25.sp, fontWeight = FontWeight.ExtraBold),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            selectedTab == "CHECKIN" && checkInSelectedLog != null -> {
                                Text(
                                    text = "Attendance Detail",
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 25.sp, fontWeight = FontWeight.ExtraBold),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            selectedTab == "TASKS" && tasksSelectedTask != null -> {
                                Text(
                                    text = tasksSelectedTask?.title ?: "Task",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            else -> {
                                Text(
                                    text = when (selectedTab) {
                                        "HOME" -> registeredClassroom?.classroomName ?: "Classroom"
                                        "CHECKIN" -> "Check-In"
                                        "SUBJECTS" -> "Class Subjects"
                                        "TASKS" -> "Tasks"
                                        "PROFILE" -> "Student Profile"
                                        "SETTINGS" -> "Settings"
                                        else -> "Classroom"
                                    },
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 25.sp, fontWeight = FontWeight.ExtraBold),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    },
                    navigationIcon = {
                        when {
                            selectedTab == "ABOUT" -> {
                                IconButton(
                                    onClick = { selectedTab = "HOME" },
                                    modifier = Modifier.testTag("about_back_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                            selectedTab == "SETTINGS" && settingsSubpage != null -> {
                                IconButton(
                                    onClick = { settingsSubpage = null },
                                    modifier = Modifier.testTag("settings_subpage_back_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                            selectedTab == "CHECKIN" && checkInSelectedLog != null -> {
                                IconButton(
                                    onClick = { checkInSelectedLog = null },
                                    modifier = Modifier.testTag("student_attendance_back_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                            selectedTab == "TASKS" && tasksSelectedTask != null -> {
                                IconButton(
                                    onClick = { tasksSelectedTask = null },
                                    modifier = Modifier.testTag("student_task_back_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                            else -> {
                                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                    Icon(Icons.Default.Menu, contentDescription = "Open Drawer")
                                }
                            }
                        }
                    },
                    actions = {},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.School, contentDescription = null) },
                        label = { Text("Classroom") },
                        selected = selectedTab == "HOME",
                        onClick = {
                            selectedTab = "HOME"
                            checkInSelectedLog = null
                            tasksSelectedTask = null
                        },
                        modifier = Modifier.testTag("tab_classroom")
                    )
                    NavigationBarItem(
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (!hasCheckedInToday && registeredClassroom != null) {
                                        Badge(
                                            containerColor = Color.Red,
                                            modifier = Modifier
                                                .size(8.dp)
                                                .offset(x = 4.dp, y = (-4.dp))
                                        )
                                    }
                                }
                            ) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null)
                            }
                        },
                        label = { Text("Check-In") },
                        selected = selectedTab == "CHECKIN",
                        onClick = {
                            selectedTab = "CHECKIN"
                            checkInSelectedLog = null
                            tasksSelectedTask = null
                        },
                        enabled = registeredClassroom != null,
                        modifier = Modifier.testTag("tab_checkin")
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.MenuBook, contentDescription = null) },
                        label = { Text("Subjects") },
                        selected = selectedTab == "SUBJECTS",
                        onClick = {
                            selectedTab = "SUBJECTS"
                            checkInSelectedLog = null
                            tasksSelectedTask = null
                        },
                        modifier = Modifier.testTag("tab_subjects")
                    )
                    val unsubmittedTasksCount = classroomTasks.count { task -> 
                        mySubmissions.none { it.taskId == task.tkid } 
                    }
                    NavigationBarItem(
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (unsubmittedTasksCount > 0) {
                                        Badge { Text("$unsubmittedTasksCount") }
                                    }
                                }
                            ) {
                                Icon(Icons.Default.Assignment, contentDescription = null)
                            }
                        },
                        label = { Text("Tasks") },
                        selected = selectedTab == "TASKS",
                        onClick = {
                            selectedTab = "TASKS"
                            tasksSelectedTask = null
                            checkInSelectedLog = null
                        },
                        enabled = registeredClassroom != null,
                        modifier = Modifier.testTag("tab_tasks")
                    )
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    when (selectedTab) {
                    "HOME" -> LazyTabContent(selectedTab) {
                        PullToRefreshBox(
                            isRefreshing = isRefreshingHome,
                            onRefresh = {
                                scope.launch {
                                    isRefreshingHome = true
                                    studentViewModel.manualRefresh()
                                    delay(1000)
                                    isRefreshingHome = false
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        ) {
                            StudentHomeView(
                                viewModel = studentViewModel,
                                profile = profile,
                                registeredClassroom = registeredClassroom,
                                registrationStatus = registrationStatus,
                                attendanceLogs = myAttendanceLogs,
                                submissions = mySubmissions,
                                classroomApprovedStudents = classroomApprovedStudents,
                                classroomAttendanceLogs = classroomAttendanceLogs,
                                hasPermissions = hasPermissions,
                                isBtEnabled = isBtEnabled,
                                isBtEnabling = isBtEnabling,
                                isNotificationGranted = isNotificationGranted,
                                isLocationEnabled = isLocationEnabled,
                                onRequestPermissions = {
                                    val activity = BlePermissionUtils.findActivity(context)
                                    if (activity != null) {
                                        androidx.core.app.ActivityCompat.requestPermissions(
                                            activity,
                                            com.example.util.BlePermissionUtils.getRequiredPermissions(),
                                            1
                                        )
                                    }
                                },
                                onEnableBt = {
                                    isBtEnabling = true
                                    BlePermissionUtils.requestEnableBluetooth(
                                        activity = BlePermissionUtils.findActivity(context),
                                        launcher = enableBtLauncher,
                                        onEnabling = { isBtEnabling = true }
                                    )
                                },
                                onRequestNotification = onRequestNotification,
                                onEnableLocation = {
                                    try {
                                        val intent = android.content.Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                                        intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
                                        context.startActivity(intent)
                                    } catch (e: Exception) {
                                        // Ignore fallback
                                    }
                                },
                                onJoinClassroom = {
                                    studentViewModel.startJoinDialog()
                                    showJoinDialog = true
                                },
                                connectionState = connectionState,
                                isRegisteredSessionActive = isRegisteredSessionActive,
                                registeredDeviceState = registeredDeviceState,
                                isRegisteredDeviceInRange = isRegisteredDeviceInRange,
                                registeredDeviceRawHex = registeredDeviceRawHex
                            )
                        }
                    }

                    "CHECKIN" -> LazyTabContent(selectedTab) {
                        val hasCheckedInToday by studentViewModel.hasCheckedInToday.collectAsState()
                        StudentCheckInView(
                            registeredClassroom = registeredClassroom,
                            attendanceLogs = myAttendanceLogs,
                            submissions = mySubmissions,
                            tasks = classroomTasks,
                            isRegisteredSessionActive = isRegisteredSessionActive,
                            hasCheckedInToday = hasCheckedInToday,
                            onCheckIn = { studentViewModel.checkIn() },
                            selectedLog = checkInSelectedLog,
                            onSelectedLogChanged = { checkInSelectedLog = it }
                        )
                    }

                    "SUBJECTS" -> LazyTabContent(selectedTab) {
                        StudentScheduleView(
                            registeredClassroom = registeredClassroom,
                            onRefresh = { studentViewModel.manualRefresh() }
                        )
                    }

                    "TASKS" -> LazyTabContent(selectedTab) {
                        PullToRefreshBox(
                            isRefreshing = isRefreshingTasks,
                            onRefresh = {
                                scope.launch {
                                    isRefreshingTasks = true
                                    studentViewModel.manualRefresh()
                                    delay(1000)
                                    isRefreshingTasks = false
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        ) {
                            StudentTasksView(
                                tasks = classroomTasks,
                                submissions = mySubmissions,
                                onSubmitScoreClick = { task ->
                                    if (task.maxScore == 0) {
                                        studentViewModel.submitTaskScoreBle(task, 0)
                                    } else {
                                        submitTaskDialogFor = task
                                    }
                                },
                                selectedTask = tasksSelectedTask,
                                onSelectedTaskChanged = { tasksSelectedTask = it },
                                tasksListState = studentTasksListState,
                                isRegisteredSessionActive = isRegisteredSessionActive
                            )
                        }
                    }

                    "PROFILE" -> LazyTabContent(selectedTab) {
                        StudentProfileView(
                            profile = profile,
                            registeredClassroom = registeredClassroom,
                            attendanceLogs = myAttendanceLogs,
                            submissions = mySubmissions
                        )
                    }

                    "ABOUT" -> LazyTabContent(selectedTab) {
                        AboutScreen()
                    }

                    "SETTINGS" -> LazyTabContent(selectedTab) {
                        SettingsScreen(
                            authViewModel = authViewModel,
                            onNavigateToProfile = { selectedTab = "PROFILE" },
                            onLoggedOut = onLogOut,
                            onAccountDeleted = onAccountDeleted,
                            currentSubpageExternal = settingsSubpage,
                            onSubpageChangeExternal = { settingsSubpage = it }
                        )
                    }
                }

                // Custom Top Banner Overlay, refined & aligned directly at the bottom of the tab name bar
                androidx.compose.animation.AnimatedVisibility(
                    visible = topMessage != null,
                    enter = androidx.compose.animation.slideInVertically(
                        initialOffsetY = { -it },
                        animationSpec = androidx.compose.animation.core.spring(
                            dampingRatio = androidx.compose.animation.core.Spring.DampingRatioMediumBouncy,
                            stiffness = androidx.compose.animation.core.Spring.StiffnessMedium
                        )
                    ) + androidx.compose.animation.fadeIn(),
                    exit = androidx.compose.animation.slideOutVertically(
                        targetOffsetY = { -it }
                    ) + androidx.compose.animation.fadeOut(),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 8.dp) // Below action bar safely
                        .zIndex(99f)
                ) {
                    topMessage?.let { msg ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .widthIn(max = 500.dp)
                                .wrapContentWidth()
                                .shadow(4.dp, RoundedCornerShape(24.dp))
                                .background(Color(0x99111827), RoundedCornerShape(24.dp)) // Sleek darker, 60% semi-transparent slate-black
                                .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(24.dp))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = msg,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(1f, fill = false)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
}

    if (submitTaskDialogFor != null) {
        val task = submitTaskDialogFor!!
        SubmitScoreDialog(
            task = task,
            isRegisteredSessionActive = isRegisteredSessionActive,
            onDismiss = { submitTaskDialogFor = null },
            onSubmit = { score ->
                submitTaskDialogFor = null
                studentViewModel.submitTaskScoreBle(task, score)
            }
        )
    }

    if (showJoinDialog) {
        StudentJoinDialog(
            viewModel = studentViewModel,
            scannedDevices = scannedDevices,
            onDismiss = {
                showJoinDialog = false
                studentViewModel.stopBleScan()
                if (registeredClassroom == null) {
                    studentViewModel.resetRegistrationState()
                }
            }
        )
    }
}

// --- STUDENT SUB-VIEWS ---

@Composable
private fun StudentStepRow(
    stepNum: Int,
    title: String,
    description: String,
    isCompleted: Boolean,
    forceShowAction: Boolean = false,
    actionButton: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        brush = if (isCompleted) {
                            Brush.linearGradient(listOf(Color(0xFF0EA5E9), Color(0xFF38BDF8)))
                        } else {
                            Brush.linearGradient(listOf(Color(0xFF64748B), Color(0xFF94A3B8)))
                        },
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Completed",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                } else {
                    Text(
                        text = stepNum.toString(),
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
        }
        
        if (actionButton != null && (!isCompleted || forceShowAction)) {
            Box(modifier = Modifier.padding(start = 8.dp)) {
                actionButton()
            }
        }
    }
}

private data class StudentStepItem(
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val actionButton: @Composable (() -> Unit)? = null
)

@Composable
fun StudentConnectionRequirementsCard(
    hasPermissions: Boolean,
    isBtEnabled: Boolean,
    isBtEnabling: Boolean = false,
    isNotificationGranted: Boolean = false,
    isLocationEnabled: Boolean,
    onRequestPermissions: () -> Unit,
    onEnableBt: () -> Unit,
    onRequestNotification: () -> Unit = {},
    onEnableLocation: () -> Unit,
    showJoinClassroomStep: Boolean = false,
    registeredClassroom: ClassroomEntity? = null,
    onJoinClassroom: (() -> Unit)? = null
) {
    val isOlderThanAndroid12 = android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S
    val locationGranted = if (!isOlderThanAndroid12) true else {
        androidx.core.content.ContextCompat.checkSelfPermission(
            LocalContext.current,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    val coroutineScope = rememberCoroutineScope()
    var helpDialogStep by remember { mutableStateOf<StudentStepItem?>(null) }

    val steps = buildList {
        // STEP 1: Grant Permissions
        if (isOlderThanAndroid12) {
            add(
                StudentStepItem(
                    title = "Grant Location Permission",
                    description = "Location permission is required to scan for BLE devices on older Android versions.",
                    isCompleted = locationGranted,
                    actionButton = {
                        Button(
                            onClick = onRequestPermissions,
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48)),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                        ) {
                            Text("Grant", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
                        }
                    }
                )
            )
        } else {
            add(
                StudentStepItem(
                    title = "Grant Bluetooth Permissions",
                    description = "Bluetooth scan and connect permissions are required for communication.",
                    isCompleted = hasPermissions,
                    actionButton = {
                        Button(
                            onClick = onRequestPermissions,
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48)),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                            modifier = Modifier.height(48.dp).defaultMinSize(minWidth = 140.dp)
                        ) {
                            Text("Grant", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                        }
                    }
                )
            )
        }

        // STEP 2: Enable Bluetooth
        add(
            StudentStepItem(
                title = "Enable Bluetooth",
                description = if (isBtEnabling) "Initializing Bluetooth adapter..." else "Directly turn on Bluetooth receiver.",
                isCompleted = isBtEnabled,
                actionButton = if (!isBtEnabled) {
                    {
                        Button(
                            onClick = onEnableBt,
                            enabled = !isBtEnabling,
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE11D48),
                                disabledContainerColor = Color(0xFFE11D48).copy(alpha = 0.7f),
                                disabledContentColor = Color.White
                            ),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                            modifier = Modifier.height(48.dp).defaultMinSize(minWidth = 140.dp)
                        ) {
                            if (isBtEnabling) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        color = Color.White,
                                        strokeWidth = 2.dp
                                    )
                                    Text("Enabling...", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                                }
                            } else {
                                Text("Enable", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                            }
                        }
                    }
                } else null
            )
        )

        // STEP 3: Allow Notifications
        add(
            StudentStepItem(
                title = "Allow Notifications",
                description = if (isNotificationGranted) "Notification alerts permitted." else "Required for classroom updates and task notifications.",
                isCompleted = isNotificationGranted,
                actionButton = if (!isNotificationGranted) {
                    {
                        Button(
                            onClick = onRequestNotification,
                            modifier = Modifier.height(48.dp).defaultMinSize(minWidth = 140.dp).testTag("allow_notification_button"),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48)),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                        ) {
                            Text("Allow", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                        }
                    }
                } else null
            )
        )

        if (isOlderThanAndroid12) {
            // STEP 4: Enable Location (Only if older than Android 12)
            add(
                StudentStepItem(
                    title = "Enable Location",
                    description = "Directly turn on Location receiver.",
                    isCompleted = isLocationEnabled,
                    actionButton = {
                        Button(
                            onClick = onEnableLocation,
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48)),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                            modifier = Modifier.height(48.dp).defaultMinSize(minWidth = 140.dp)
                        ) {
                            Text("Enable", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                        }
                    }
                )
            )
        }

        if (showJoinClassroomStep) {
            // STEP 4 / 5: Join Classroom
            add(
                StudentStepItem(
                    title = "Join Classroom",
                    description = "Discover and register to your classroom device.",
                    isCompleted = registeredClassroom != null,
                    actionButton = {
                        Button(
                            onClick = { onJoinClassroom?.invoke() },
                            enabled = isBtEnabled && isNotificationGranted && (!isOlderThanAndroid12 || (locationGranted && isLocationEnabled)),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48)),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                            modifier = Modifier.height(48.dp).defaultMinSize(minWidth = 140.dp)
                        ) {
                            Text("Join", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                        }
                    }
                )
            )
        }
    }

    val initialStepPage = remember {
        val initialDisabled = steps.indices.filter { !steps[it].isCompleted }
        if (initialDisabled.isNotEmpty()) initialDisabled.first() else 0
    }
    val pagerState = rememberPagerState(
        initialPage = initialStepPage.coerceIn(0, (steps.size - 1).coerceAtLeast(0)),
        pageCount = { steps.size }
    )

    // Auto-navigate: If one is disabled, navigate to it. If two or more, show the first.
    LaunchedEffect(hasPermissions, isBtEnabled, isNotificationGranted, isLocationEnabled, locationGranted, registeredClassroom != null) {
        val currentDisabled = steps.indices.filter { !steps[it].isCompleted }
        val target = if (currentDisabled.isNotEmpty()) currentDisabled.first() else 0
        if (target in 0 until steps.size && pagerState.currentPage != target) {
            pagerState.animateScrollToPage(target)
        }
    }

    if (helpDialogStep != null) {
        val step = helpDialogStep!!
        AlertDialog(
            onDismissRequest = { helpDialogStep = null },
            icon = {
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFFFF1F2),
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.HelpOutline,
                            contentDescription = null,
                            tint = Color(0xFFE11D48),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            },
            title = {
                Text(
                    text = step.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFF9FAFB),
                    border = BorderStroke(1.dp, Color(0xFFE5E7EB)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = step.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(14.dp),
                        textAlign = TextAlign.Center
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { helpDialogStep = null },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Got it", fontWeight = FontWeight.Bold)
                }
            },
            shape = RoundedCornerShape(16.dp),
            containerColor = Color.White
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("connection_requirements_card"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.5.dp, Color(0xFFE11D48)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Connection Requirements",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFE11D48) // Highlighted rose/red color
                )
                Icon(
                    imageVector = Icons.Default.Bluetooth,
                    contentDescription = null,
                    tint = Color(0xFFE11D48), // Highlighted rose/red color
                    modifier = Modifier.size(22.dp)
                )
            }

            HorizontalDivider(color = Color(0xFFFECDD3))

            // Fixed-size row with Left Arrow, Pager, and Right Arrow
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(118.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        if (pagerState.currentPage > 0) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    },
                    enabled = pagerState.currentPage > 0,
                    modifier = Modifier
                        .size(40.dp)
                        .testTag("connection_req_prev")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Previous requirement",
                        tint = if (pagerState.currentPage > 0) Color(0xFFE11D48) else Color(0xFFE11D48).copy(alpha = 0.25f),
                        modifier = Modifier.size(26.dp)
                    )
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .weight(1f)
                        .height(118.dp)
                        .testTag("connection_req_pager")
                ) { page ->
                    if (page in steps.indices) {
                        val step = steps[page]
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                // Step title with question mark icon at the end: e.g. Register Calis Device (?)
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = step.title,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFE11D48),
                                        textAlign = TextAlign.Center,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    IconButton(
                                        onClick = { helpDialogStep = step },
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.HelpOutline,
                                            contentDescription = "Details for ${step.title}",
                                            tint = Color(0xFFE11D48),
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                // Centered button or Completed badge
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (step.actionButton != null && !step.isCompleted) {
                                        step.actionButton.invoke()
                                    } else if (step.isCompleted) {
                                        val completedLabel = when {
                                            step.title.contains("permission", ignoreCase = true) || step.title.contains("notification", ignoreCase = true) || step.title.contains("grant", ignoreCase = true) || step.title.contains("allow", ignoreCase = true) -> "Granted"
                                            step.title.contains("bluetooth", ignoreCase = true) || step.title.contains("location", ignoreCase = true) -> "Enabled"
                                            step.title.contains("join", ignoreCase = true) -> "Joined"
                                            step.title.contains("register", ignoreCase = true) -> "Registered"
                                            else -> "Granted"
                                        }
                                        Surface(
                                            shape = RoundedCornerShape(12.dp),
                                            color = Color(0xFFFFE4E6),
                                            border = BorderStroke(1.dp, Color(0xFFFDA4AF)),
                                            modifier = Modifier.height(48.dp).defaultMinSize(minWidth = 140.dp)
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center,
                                                modifier = Modifier.padding(horizontal = 24.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = Color(0xFFBE123C),
                                                    modifier = Modifier.size(20.dp)
                                                )
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Text(
                                                    text = completedLabel,
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.ExtraBold,
                                                    color = Color(0xFFBE123C)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                IconButton(
                    onClick = {
                        if (pagerState.currentPage < steps.size - 1) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                    enabled = pagerState.currentPage < steps.size - 1,
                    modifier = Modifier
                        .size(40.dp)
                        .testTag("connection_req_next")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Next requirement",
                        tint = if (pagerState.currentPage < steps.size - 1) Color(0xFFE11D48) else Color(0xFFE11D48).copy(alpha = 0.25f),
                        modifier = Modifier.size(26.dp)
                    )
                }
            }

            if (steps.size > 1) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    steps.forEachIndexed { index, item ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 3.dp)
                                .size(if (pagerState.currentPage == index) 7.dp else 5.dp)
                                .clip(CircleShape)
                                .background(
                                    if (pagerState.currentPage == index) {
                                        Color(0xFFE11D48)
                                    } else if (item.isCompleted) {
                                        Color(0xFFE11D48).copy(alpha = 0.5f)
                                    } else {
                                        Color(0xFFFECDD3)
                                    }
                                )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StudentHomeView(
    viewModel: com.example.ui.viewmodel.StudentViewModel,
    profile: StudentProfileEntity?,
    registeredClassroom: ClassroomEntity?,
    registrationStatus: String,
    attendanceLogs: List<AttendanceEntity>,
    submissions: List<TaskSubmissionEntity>,
    classroomApprovedStudents: List<ApprovedStudentEntity>,
    classroomAttendanceLogs: List<AttendanceEntity>,
    hasPermissions: Boolean,
    isBtEnabled: Boolean,
    isBtEnabling: Boolean = false,
    isNotificationGranted: Boolean = false,
    isLocationEnabled: Boolean,
    onRequestPermissions: () -> Unit,
    onEnableBt: () -> Unit,
    onRequestNotification: () -> Unit = {},
    onEnableLocation: () -> Unit,
    connectionState: com.example.ble.StudentBleManager.ConnectionState,
    isRegisteredSessionActive: Boolean,
    registeredDeviceState: com.example.ble.DeviceState,
    isRegisteredDeviceInRange: Boolean,
    registeredDeviceRawHex: String,
    onJoinClassroom: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val syncState by viewModel.syncState.collectAsState()
        val syncMessage by viewModel.syncMessage.collectAsState()
        val isClassroomExpired by viewModel.isClassroomExpired.collectAsState()

        val context = LocalContext.current
        val isOlderThanAndroid12 = android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S
        val requirementsMissing = !hasPermissions || !isBtEnabled || !isNotificationGranted || (isOlderThanAndroid12 && !isLocationEnabled)

        if (syncState != com.example.ui.viewmodel.StudentSyncState.IDLE) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = when (syncState) {
                    com.example.ui.viewmodel.StudentSyncState.SUCCESS -> Color(0xFFD1FAE5) // Success green tint
                    com.example.ui.viewmodel.StudentSyncState.ERROR -> Color(0xFFFEE2E2) // Error red tint
                    else -> Color(0xFFEFF6FF) // Loading/Syncing blue tint
                },
                border = BorderStroke(
                    1.dp,
                    when (syncState) {
                        com.example.ui.viewmodel.StudentSyncState.SUCCESS -> Color(0xFF10B981)
                        com.example.ui.viewmodel.StudentSyncState.ERROR -> Color(0xFFEF4444)
                        else -> Color(0xFF3B82F6)
                    }
                )
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (syncState == com.example.ui.viewmodel.StudentSyncState.CONNECTING || syncState == com.example.ui.viewmodel.StudentSyncState.SYNCING) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp,
                            color = Color(0xFF3B82F6)
                        )
                    } else if (syncState == com.example.ui.viewmodel.StudentSyncState.SUCCESS) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Success",
                            tint = Color(0xFF10B981)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Warning",
                            tint = Color(0xFFEF4444)
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = when (syncState) {
                                com.example.ui.viewmodel.StudentSyncState.CONNECTING -> "Connecting..."
                                com.example.ui.viewmodel.StudentSyncState.SYNCING -> "Synchronizing..."
                                com.example.ui.viewmodel.StudentSyncState.SUCCESS -> "Sync Successful!"
                                else -> "Synchronization Alert"
                            },
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1F2937)
                        )
                        Text(
                            text = syncMessage,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF4B5563)
                        )
                    }
                    IconButton(onClick = { viewModel.dismissSyncBanner() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color(0xFF4B5563)
                        )
                    }
                }
            }
        }

        if (registeredClassroom != null) {
            if (isClassroomExpired) {
                Card(
                    modifier = Modifier.fillMaxWidth().testTag("classroom_expired_card"),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF3F4F6) // Light-gray background
                    ),
                    border = BorderStroke(1.2.dp, Color(0xFFD1D5DB)), // Soft gray border
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = Color(0xFF4B5563), // Medium gray
                            modifier = Modifier.size(24.dp)
                        )
                        Column {
                            Text(
                                text = "Classroom Expired",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1F2937) // Dark gray
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "This classroom has expired. The Calis Device has been unregistered or renamed by the teacher back to Factory Mode.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF4B5563) // Medium gray
                            )
                        }
                    }
                }
            } else if (requirementsMissing) {
                StudentConnectionRequirementsCard(
                    hasPermissions = hasPermissions,
                    isBtEnabled = isBtEnabled,
                    isBtEnabling = isBtEnabling,
                    isNotificationGranted = isNotificationGranted,
                    isLocationEnabled = isLocationEnabled,
                    onRequestPermissions = onRequestPermissions,
                    onEnableBt = onEnableBt,
                    onRequestNotification = onRequestNotification,
                    onEnableLocation = onEnableLocation,
                    showJoinClassroomStep = false
                )
            }

            // Main Student Profile & Registration Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f).padding(end = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Text(
                                text = registeredClassroom.classroomName,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "Teacher: ${registeredClassroom.teacherName}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        val isSessionActive = registeredClassroom.isActiveSession ||
                                registeredDeviceState == com.example.ble.DeviceState.SESSION_ACTIVE_OPEN ||
                                registeredDeviceState == com.example.ble.DeviceState.SESSION_ACTIVE_BUSY ||
                                isRegisteredSessionActive
                        val indicatorText = if (isSessionActive) "Active" else "Inactive"
                        val indicatorColor = if (isSessionActive) StatusPresentGreen else Color(0xFF64748B)
                        val indicatorBg = if (isSessionActive) StatusPresentGreen.copy(alpha = 0.15f) else Color(0xFF64748B).copy(alpha = 0.15f)
                        val indicatorBorder = if (isSessionActive) BorderStroke(1.dp, StatusPresentGreen.copy(alpha = 0.4f)) else BorderStroke(1.dp, Color(0xFF64748B).copy(alpha = 0.3f))

                        Surface(
                            shape = RoundedCornerShape(50),
                            color = indicatorBg,
                            border = indicatorBorder,
                            modifier = Modifier.testTag("student_session_indicator")
                        ) {
                            Text(
                                text = indicatorText,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = indicatorColor,
                                maxLines = 1
                            )
                        }
                    }

                    Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                    // Combined Classroom Information & Attendance Overview Tile
                    StudentClassroomInformationTile(
                        registeredClassroom = registeredClassroom,
                        attendanceLogs = attendanceLogs
                    )
                }
            }
        } else {
            StudentConnectionRequirementsCard(
                hasPermissions = hasPermissions,
                isBtEnabled = isBtEnabled,
                isBtEnabling = isBtEnabling,
                isNotificationGranted = isNotificationGranted,
                isLocationEnabled = isLocationEnabled,
                onRequestPermissions = onRequestPermissions,
                onEnableBt = onEnableBt,
                onRequestNotification = onRequestNotification,
                onEnableLocation = onEnableLocation,
                showJoinClassroomStep = true,
                registeredClassroom = null,
                onJoinClassroom = onJoinClassroom
            )
        }
    }
}

@Composable
fun StudentCheckInView(
    registeredClassroom: ClassroomEntity?,
    attendanceLogs: List<AttendanceEntity>,
    submissions: List<TaskSubmissionEntity>,
    tasks: List<TaskEntity> = emptyList(),
    isRegisteredSessionActive: Boolean,
    hasCheckedInToday: Boolean,
    onCheckIn: () -> Unit,
    selectedLog: AttendanceEntity? = null,
    onSelectedLogChanged: (AttendanceEntity?) -> Unit = {}
) {
    androidx.activity.compose.BackHandler(enabled = selectedLog != null) {
        onSelectedLogChanged(null)
    }

    if (selectedLog != null) {
        StudentAttendanceLogDetailSubPage(
            log = selectedLog!!,
            submissions = submissions,
            tasks = tasks,
            onBack = { onSelectedLogChanged(null) }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier.size(56.dp),
                        tint = if (hasCheckedInToday) StatusPresentGreen else MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = if (hasCheckedInToday) "Checked-In for Today" else "Attendance Check-In",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = when {
                            hasCheckedInToday -> "You have already checked-in for today. No need to check-in again."
                            isRegisteredSessionActive -> "Transmit your SID via BLE to mark attendance in Calis Device."
                            else -> "Calis Device session is currently inactive."
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (hasCheckedInToday) MaterialTheme.colorScheme.onPrimaryContainer else if (isRegisteredSessionActive) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.error,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onCheckIn,
                        enabled = isRegisteredSessionActive && !hasCheckedInToday,
                        modifier = Modifier.fillMaxWidth().height(50.dp).testTag("student_checkin_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = StatusPresentGreen,
                            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Icon(
                            imageVector = if (hasCheckedInToday) Icons.Default.Check else Icons.Default.Send,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (hasCheckedInToday) "Already Checked-In" else "Check-In Now (CHK_IN)",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Text("My Attendance Logs", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            if (attendanceLogs.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                    Text("No attendance logs recorded yet.")
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(attendanceLogs) { log ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelectedLogChanged(log) },
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        modifier = Modifier.weight(1f).padding(end = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.CalendarToday,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(32.dp)
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        val displayDate = java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date(log.timestamp * 1000L))
                                        val timeStr = java.text.SimpleDateFormat("hh:mm a", java.util.Locale.US).format(java.util.Date(log.timestamp * 1000L))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = displayDate,
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold,
                                                maxLines = 1,
                                                modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE)
                                            )
                                            Text(
                                                text = timeStr,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                maxLines = 1
                                            )
                                        }
                                    }

                                    Surface(
                                        modifier = Modifier.width(80.dp),
                                        shape = RoundedCornerShape(6.dp),
                                        color = when (log.status) {
                                            "PRESENT" -> StatusPresentGreen.copy(alpha = 0.15f)
                                            "LATE" -> StatusLateAmber.copy(alpha = 0.15f)
                                            "ABSENT" -> StatusAbsentRed.copy(alpha = 0.15f)
                                            else -> MaterialTheme.colorScheme.surfaceVariant
                                        }
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 5.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = log.status,
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = when (log.status) {
                                                    "PRESENT" -> StatusPresentGreen
                                                    "LATE" -> StatusLateAmber
                                                    "ABSENT" -> StatusAbsentRed
                                                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                                                },
                                                maxLines = 1
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StudentAttendanceLogDetailSubPage(
    log: AttendanceEntity,
    submissions: List<TaskSubmissionEntity>,
    tasks: List<TaskEntity> = emptyList(),
    onBack: () -> Unit
) {
    val dateSubmissions = remember(submissions, log) {
        val logTs = if (log.timestamp < 10000000000L) log.timestamp * 1000L else log.timestamp
        val logDateYmd = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date(logTs))
        val logDateMdy = java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date(logTs))
        
        submissions.filter { sub ->
            val subTs = if (sub.timestamp < 10000000000L) sub.timestamp * 1000L else sub.timestamp
            val subDateYmd = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date(subTs))
            val subDateMdy = java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date(subTs))
            
            subDateYmd == logDateYmd || subDateMdy == logDateMdy ||
            log.date.startsWith(subDateYmd) || log.date.startsWith(subDateMdy) ||
            log.date.contains(subDateMdy) || log.date.contains(subDateYmd)
        }
    }
    val timeStr = java.text.SimpleDateFormat("hh:mm a", java.util.Locale.US).format(
        java.util.Date(if (log.timestamp < 10000000000L) log.timestamp * 1000L else log.timestamp)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).padding(top = 16.dp)) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    val displayDate = java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(
                        java.util.Date(if (log.timestamp < 10000000000L) log.timestamp * 1000L else log.timestamp)
                    )
                    Text("Date: $displayDate", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Time: $timeStr", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Status: ${log.status}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            
            Text("Tasks Submitted on this Date", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            if (dateSubmissions.isEmpty()) {
                Text("No tasks submitted on this date.", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(dateSubmissions) { sub ->
                        val matchedTask = tasks.find { it.tkid == sub.taskId }
                        val taskTitle = matchedTask?.title ?: "Task ID: ${sub.taskId}"
                        val taskMaxScore = matchedTask?.maxScore ?: 0
                        val scoreText = if (taskMaxScore > 0) "Score: ${sub.score} / $taskMaxScore pts" else if (sub.score > 0) "Score: ${sub.score} pts" else "Marked Complete"
                        val submitTimeStr = try {
                            val ms = if (sub.timestamp < 10000000000L) sub.timestamp * 1000L else sub.timestamp
                            java.text.SimpleDateFormat("hh:mm a", java.util.Locale.US).format(java.util.Date(ms))
                        } catch (e: Exception) { "--:--" }

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                                        Text(taskTitle, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text("Submitted at $submitTimeStr", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
                                    }
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = MaterialTheme.colorScheme.primaryContainer
                                    ) {
                                        Text(
                                            text = scoreText,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StudentScheduleView(
    registeredClassroom: ClassroomEntity?,
    onRefresh: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Coming Soon", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Calis is currently under development. Thank you for testing this app out!",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun StudentTasksView(
    tasks: List<TaskEntity>,
    submissions: List<TaskSubmissionEntity>,
    onSubmitScoreClick: (TaskEntity) -> Unit,
    selectedTask: TaskEntity? = null,
    onSelectedTaskChanged: (TaskEntity?) -> Unit = {},
    tasksListState: LazyListState = rememberSaveable(saver = LazyListState.Saver) { LazyListState() },
    isRegisteredSessionActive: Boolean = false
) {
    androidx.activity.compose.BackHandler(enabled = selectedTask != null) {
        onSelectedTaskChanged(null)
    }
    val datePostedFormatter = remember { java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US) }
    val deadlineFormatter = remember { java.text.SimpleDateFormat("MM/dd/yyyy hh:mm a", java.util.Locale.US) }

    if (selectedTask != null) {
        val task = selectedTask!!
        val submission = submissions.find { it.taskId == task.tkid }
        
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Surface(
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            ) {
                Text(
                    text = "TKID: ${task.tkid}",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Task Description",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 24.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            if (submission != null) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = StatusPresentGreen.copy(alpha = 0.15f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = StatusPresentGreen, modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (task.maxScore == 0) "Complete" else "Submitted Score: ${submission.score} / ${task.maxScore}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = StatusPresentGreen
                            )
                        }
                        val markedDateStr = remember(submission.timestamp) {
                            val ms = if (submission.timestamp < 10_000_000_000L) submission.timestamp * 1000L else submission.timestamp
                            val sdf = java.text.SimpleDateFormat("MM/dd/yyyy hh:mm a", java.util.Locale.US)
                            sdf.format(java.util.Date(ms))
                        }
                        Text(
                            text = "Marked on: $markedDateStr",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                if (!isRegisteredSessionActive) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Calis Device session is currently inactive.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Button(
                    onClick = { onSubmitScoreClick(task) },
                    enabled = isRegisteredSessionActive,
                    modifier = Modifier.fillMaxWidth().height(50.dp).testTag("submit_score_button_${task.tkid}"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (task.maxScore == 0) {
                        Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Mark as Submitted", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    } else {
                        Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Submit Score", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    } else {
        LazyColumn(
            state = tasksListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            if (tasks.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No tasks received yet. Refresh or check back when teacher posts tasks.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            } else {
                items(tasks.sortedByDescending { it.datePosted }) { task ->
                    val submission = submissions.find { s -> s.taskId == task.tkid }
                    val deadlineStr = try {
                        val ms = if (task.deadline > 100_000_000_000L) task.deadline else task.deadline * 1000L
                        deadlineFormatter.format(java.util.Date(ms))
                    } catch (e: Exception) { "--" }

                    Card(
                        modifier = Modifier.fillMaxWidth().clickable { onSelectedTaskChanged(task) },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f).padding(end = 12.dp)) {
                                Text(task.title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Due: $deadlineStr", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.SemiBold)
                                if (submission != null) {
                                    val markedDateStr = remember(submission.timestamp) {
                                        val ms = if (submission.timestamp < 10_000_000_000L) submission.timestamp * 1000L else submission.timestamp
                                        val sdf = java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US)
                                        sdf.format(java.util.Date(ms))
                                    }
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text("Marked Date: $markedDateStr", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                            
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                if (submission != null) {
                                    Card(
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text("Score", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                                            Text(
                                                text = if (task.maxScore > 0) "${submission.score} / ${task.maxScore}" else "Done",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.ExtraBold,
                                                color = Color(0xFF1B5E20)
                                            )
                                        }
                                    }
                                } else {
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = MaterialTheme.colorScheme.primaryContainer
                                    ) {
                                        Text(
                                            text = if (task.maxScore > 0) "Max: ${task.maxScore}" else "N/A",
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }
                                }

                                Icon(
                                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = "View Task Details",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 120.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Pull down to refresh",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun SubmitScoreDialog(
    task: TaskEntity,
    isRegisteredSessionActive: Boolean = false,
    onDismiss: () -> Unit,
    onSubmit: (Int) -> Unit
) {
    var scoreStr by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        tonalElevation = 0.dp,
        title = { Text("Submit Score for: ${task.title}") },
        text = {
            Column {
                Text("Max Score: ${task.maxScore}")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = scoreStr,
                    onValueChange = { input ->
                        val trimmed = input.trim()
                        if (trimmed.isEmpty()) {
                            scoreStr = ""
                        } else {
                            val parsedVal = trimmed.toIntOrNull()
                            if (parsedVal != null) {
                                if (parsedVal > task.maxScore) {
                                    scoreStr = "" // Reset if score is higher than max score
                                } else {
                                    scoreStr = input
                                }
                            } else {
                                scoreStr = input
                            }
                        }
                    },
                    label = { Text("Obtained Score") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.testTag("score_obtained_input")
                )
                if (!isRegisteredSessionActive) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Calis Device session is currently inactive.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val score = scoreStr.toIntOrNull() ?: 0
                    onSubmit(score)
                },
                enabled = isRegisteredSessionActive && scoreStr.isNotBlank(),
                modifier = Modifier.testTag("confirm_submit_score")
            ) {
                Text("Submit Score")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

enum class StepState {
    COMPLETED,
    ACTIVE,
    WAITING
}


@Composable
fun StudentJoinDialog(
    viewModel: com.example.ui.viewmodel.StudentViewModel,
    scannedDevices: List<com.example.ble.StudentBleManager.ScannedDevice>,
    onDismiss: () -> Unit
) {
    val registrationStatus by viewModel.registrationStatus.collectAsState()
    val regDialogStep by viewModel.regDialogStep.collectAsState()
    val connectionState by viewModel.connectionState.collectAsState()
    val registrationTasks by viewModel.registrationTasks.collectAsState()
    val registeredClassroom by viewModel.registeredClassroom.collectAsState()
    val studentProfile by viewModel.currentProfile.collectAsState()
    val lastTxResponse by viewModel.lastTxResponse.collectAsState()
    val joinErrorMessage by viewModel.joinErrorMessage.collectAsState()

    // Stop scan when dialog is closed
    DisposableEffect(Unit) {
        onDispose {
            viewModel.clearJoinErrorMessage()
            viewModel.stopBleScan()
        }
    }

    // Continuously scan for Calis Devices (including 0x0011 status byte) while in Join Classroom dialog
    LaunchedEffect(regDialogStep, registrationStatus) {
        if (registrationStatus == "NOT_REGISTERED") {
            while (isActive) {
                if (viewModel.bleManager.isBluetoothEnabled()) {
                    val conn = viewModel.bleManager.connectionState.value
                    if (conn !is com.example.ble.StudentBleManager.ConnectionState.Connected &&
                        conn !is com.example.ble.StudentBleManager.ConnectionState.Connecting
                    ) {
                        viewModel.startBleScan()
                    }
                }
                delay(2000)
            }
        } else {
            viewModel.stopBleScan()
        }
    }

    // Strictly filter real Calis Devices advertising active session (0x0011 Session Active Open or 0x0111 Session Active Busy)
    val joinableDevices = scannedDevices.filter { dev ->
        // Strictly exclude virtual, mock, or setup devices
        if (dev.address.startsWith("VIRTUAL_", ignoreCase = true) || 
            dev.name.contains("Virtual", ignoreCase = true) || 
            dev.address.contains("MOCK", ignoreCase = true) ||
            dev.name.contains("Mock", ignoreCase = true) ||
            dev.name.contains("Setup", ignoreCase = true) ||
            dev.name.contains("Setup Calis", ignoreCase = true) ||
            dev.name.contains("Calis Setup", ignoreCase = true)
        ) {
            return@filter false
        }

        // Strictly exclude Factory mode, Registering, Authenticate, or Inactive session states
        if (dev.state == com.example.ble.DeviceState.FACTORY_MODE ||
            dev.state == com.example.ble.DeviceState.REGISTERING ||
            dev.state == com.example.ble.DeviceState.AUTHENTICATE ||
            dev.state == com.example.ble.DeviceState.SESSION_INACTIVE
        ) {
            return@filter false
        }

        // Must be in active classroom session state
        val isSessionActive = dev.state == com.example.ble.DeviceState.SESSION_ACTIVE_OPEN ||
                dev.state == com.example.ble.DeviceState.SESSION_ACTIVE_BUSY

        isSessionActive
    }

    AlertDialog(
        onDismissRequest = {
            viewModel.clearJoinErrorMessage()
            if (registrationStatus == "APPROVED" || registrationStatus == "REJECTED") {
                onDismiss()
            } else if (registrationStatus != "APPROVED_WAITING_DONE" && registrationStatus != "APPROVED_WAITING_TASKS" && registrationStatus != "RECEIVING_TASKS") {
                viewModel.resetRegistrationState()
                viewModel.stopBleScan()
                onDismiss()
            }
        },
        tonalElevation = 0.dp,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = when (registrationStatus) {
                        "APPROVED" -> Icons.Default.CheckCircle
                        "REJECTED" -> Icons.Default.Cancel
                        else -> Icons.Default.Bluetooth
                    },
                    contentDescription = null,
                    tint = when (registrationStatus) {
                        "APPROVED" -> StatusPresentGreen
                        "REJECTED" -> MaterialTheme.colorScheme.error
                        else -> MaterialTheme.colorScheme.primary
                    }
                )
                Text(
                    text = when (registrationStatus) {
                        "APPROVED" -> "Registration Approved!"
                        "REJECTED" -> "Registration Rejected"
                        "PENDING" -> "Registering with Classroom..."
                        "APPROVED_WAITING_TASKS", "RECEIVING_TASKS", "APPROVED_WAITING_DONE" -> "Synchronizing Classroom..."
                        else -> "Join Classroom"
                    },
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        text = {
            Column(
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
                                            Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                                                Text(device.name.ifBlank { "Calis Device (${device.address.takeLast(5)})" }, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                                Spacer(modifier = Modifier.height(2.dp))
                                                Text(if (isBusy) "Classroom Session Busy" else "Active Classroom Ready", style = MaterialTheme.typography.bodySmall, color = if (isBusy) StatusLateAmber else StatusPresentGreen, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                            }
                                            Button(
                                                onClick = { viewModel.initiateJoinClassroom(device.address, device.name) },
                                                shape = RoundedCornerShape(8.dp),
                                                enabled = !isBusy
                                            ) {
                                                Text(if (isBusy) "Busy" else "Join")
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
        },
        confirmButton = {
            if (registrationStatus == "APPROVED" || registrationStatus == "REJECTED") {
                Button(
                    onClick = {
                        viewModel.stopBleScan()
                        viewModel.bleManager.clearBleObject()
                        onDismiss()
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(if (registrationStatus == "APPROVED") "Enter Classroom" else "Close")
                }
            }
        },
        dismissButton = {
            if (registrationStatus != "APPROVED" && registrationStatus != "REJECTED" &&
                registrationStatus != "APPROVED_WAITING_DONE" && registrationStatus != "APPROVED_WAITING_TASKS" &&
                registrationStatus != "RECEIVING_TASKS"
            ) {
                TextButton(
                    onClick = {
                        viewModel.clearJoinErrorMessage()
                        viewModel.resetRegistrationState()
                        viewModel.stopBleScan()
                        viewModel.bleManager.clearBleObject()
                        onDismiss()
                    }
                ) {
                    Text("Cancel")
                }
            }
        }
    )
}

// --- CLASSROOM INFORMATION OVERVIEW TILE COMPONENT FOR STUDENT ACCOUNT ---

@Composable
private fun StudentClassroomInformationTile(
    registeredClassroom: ClassroomEntity,
    attendanceLogs: List<AttendanceEntity> = emptyList()
) {
    var currentTimeStr by remember { mutableStateOf("") }
    var currentDateStr by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val timeFormat = java.text.SimpleDateFormat("hh:mm:ss a", java.util.Locale.US)
        val dateFormat = java.text.SimpleDateFormat("EEEE, MMMM dd, yyyy", java.util.Locale.US)
        while (isActive) {
            val now = java.util.Date()
            currentTimeStr = timeFormat.format(now)
            currentDateStr = dateFormat.format(now)
            delay(1000L)
        }
    }

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()

    var showHistoryDialog by remember { mutableStateOf(false) }

    val todayDateStr = remember {
        java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date())
    }
    val todayLog = attendanceLogs.find { it.date == todayDateStr }
    val presentCount = attendanceLogs.count { it.status == "PRESENT" }
    val lateCount = attendanceLogs.count { it.status == "LATE" }
    val absentCount = attendanceLogs.count { it.status == "ABSENT" }
    val totalRecorded = attendanceLogs.size

    val formattedDate = remember {
        java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date())
    }

    if (showHistoryDialog) {
        AlertDialog(
            onDismissRequest = { showHistoryDialog = false },
            tonalElevation = 0.dp,
            title = { Text("My Attendance History") },
            text = {
                if (attendanceLogs.isEmpty()) {
                    Text("No attendance logs recorded yet.")
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.heightIn(max = 300.dp)
                    ) {
                        items(attendanceLogs.sortedByDescending { it.timestamp }) { log ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text("Date: ${log.date}", fontWeight = FontWeight.Bold)
                                        val timeStr = remember(log.timestamp) {
                                            try {
                                                java.text.SimpleDateFormat("hh:mm:ss a", java.util.Locale.US).format(java.util.Date(log.timestamp * 1000L))
                                            } catch (e: Exception) { "" }
                                        }
                                        if (timeStr.isNotBlank()) {
                                            Text("Time: $timeStr", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        }
                                    }
                                    Surface(
                                        shape = RoundedCornerShape(50),
                                        color = when (log.status) {
                                            "PRESENT" -> StatusPresentGreen.copy(alpha = 0.15f)
                                            "LATE" -> StatusLateAmber.copy(alpha = 0.15f)
                                            else -> StatusAbsentRed.copy(alpha = 0.15f)
                                        }
                                    ) {
                                        Text(
                                            text = log.status,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 11.sp,
                                            color = when (log.status) {
                                                "PRESENT" -> StatusPresentGreen
                                                "LATE" -> StatusLateAmber
                                                else -> StatusAbsentRed
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showHistoryDialog = false }) {
                    Text("Close")
                }
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // --- 1. LARGE SUB-TILE: REAL-TIME CLOCK WITH SECONDS (STAYS INTACT) ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AccentGradient)
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (currentDateStr.isNotEmpty()) currentDateStr else "Loading...",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.White.copy(alpha = 0.95f),
                        fontSize = 13.sp
                    )
                    Text(
                        text = if (currentTimeStr.isNotEmpty()) currentTimeStr else "--:--:-- --",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        fontSize = 30.sp
                    )
                }
            }
        }

        // --- SWIPEABLE CONTAINER (HORIZONTAL PAGER) ---
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
        ) { page ->
            if (page == 0) {
                // VIEW 1: ATTENDANCE OVERVIEW CONTENT (PRESENT, LATE, ABSENT)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("student_attendance_overview_card"),
                    verticalArrangement = Arrangement.Center
                ) {
                    // 3 columns breakdown: Present, Late, Absent
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(115.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Present Tile
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clickable { showHistoryDialog = true }
                                .testTag("student_attendance_present_tile"),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFE8F5E9)
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 8.dp, horizontal = 4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = "$presentCount",
                                    style = MaterialTheme.typography.headlineLarge,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFF1B5E20),
                                    fontSize = 28.sp
                                )
                                Text(
                                    text = "Present",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2E7D32)
                                )
                            }
                        }

                        // Late Tile
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clickable { showHistoryDialog = true }
                                .testTag("student_attendance_late_tile"),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFF8E1)
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 8.dp, horizontal = 4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = "$lateCount",
                                    style = MaterialTheme.typography.headlineLarge,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFFE65100),
                                    fontSize = 28.sp
                                )
                                Text(
                                    text = "Late",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF57F17)
                                )
                            }
                        }

                        // Absent Tile
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clickable { showHistoryDialog = true }
                                .testTag("student_attendance_absent_tile"),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (absentCount > 0) StatusAbsentRed.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 8.dp, horizontal = 4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = "$absentCount",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (absentCount > 0) StatusAbsentRed else MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 28.sp
                                )
                                Text(
                                    text = "Absent",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (absentCount > 0) StatusAbsentRed else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            } else {
                // VIEW 2: SCHEDULE DETAILS (START, GRACE, DISMISSAL + WEEKLY SCHEDULE)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val calendar = java.util.Calendar.getInstance()
                        val currentMinutes = calendar.get(java.util.Calendar.HOUR_OF_DAY) * 60 + calendar.get(java.util.Calendar.MINUTE)
                        val startMins = parseStudentTimeToMinutes(registeredClassroom.startTime)
                        val graceMins = parseStudentTimeToMinutes(registeredClassroom.graceTime)
                        val endMins = parseStudentTimeToMinutes(registeredClassroom.endTime)

                        val isStartHighlighted = currentMinutes in startMins until (if (graceMins > startMins) graceMins else startMins + 15)
                        val isGraceHighlighted = currentMinutes in graceMins until (if (endMins > graceMins) endMins else graceMins + 60)
                        val isEndHighlighted = currentMinutes in endMins until (endMins + 120)

                        val isBound = registeredClassroom.isBound

                        StudentScheduleTimeSubTile(
                            modifier = Modifier.weight(1f),
                            title = "Start Time",
                            value = formatStudentDisplayTime(registeredClassroom.startTime),
                            accentColor = if (isBound) MaterialTheme.colorScheme.primary else Color(0xFF64748B),
                            isHighlighted = isStartHighlighted && isBound,
                            isBound = isBound
                        )

                        StudentScheduleTimeSubTile(
                            modifier = Modifier.weight(1f),
                            title = "Grace Period",
                            value = formatStudentDisplayTime(registeredClassroom.graceTime),
                            accentColor = if (isBound) MaterialTheme.colorScheme.secondary else Color(0xFF64748B),
                            isHighlighted = isGraceHighlighted && isBound,
                            isBound = isBound
                        )

                        StudentScheduleTimeSubTile(
                            modifier = Modifier.weight(1f),
                            title = "Dismissal",
                            value = formatStudentDisplayTime(registeredClassroom.endTime),
                            accentColor = if (isBound) MaterialTheme.colorScheme.tertiary else Color(0xFF64748B),
                            isHighlighted = isEndHighlighted && isBound,
                            isBound = isBound
                        )
                    }

                    StudentDaysScheduleSubTileRow(scheduleDaysString = registeredClassroom.days)
                }
            }
        }

        // Centered Attendance/Schedule Pill switch at the bottom
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, bottom = 0.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ) {
                Row(
                    modifier = Modifier.padding(2.dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = if (pagerState.currentPage == 0) MaterialTheme.colorScheme.primary else Color.Transparent,
                        modifier = Modifier.clickable {
                            coroutineScope.launch { pagerState.animateScrollToPage(0) }
                        }
                    ) {
                        Text(
                            text = "Attendance",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (pagerState.currentPage == 0) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = if (pagerState.currentPage == 1) MaterialTheme.colorScheme.primary else Color.Transparent,
                        modifier = Modifier.clickable {
                            coroutineScope.launch { pagerState.animateScrollToPage(1) }
                        }
                    ) {
                        Text(
                            text = "Schedule",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (pagerState.currentPage == 1) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StudentScheduleTimeSubTile(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    accentColor: Color,
    isHighlighted: Boolean = false,
    isBound: Boolean = true
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (!isBound) Color(0xFFE2E8F0) else if (isHighlighted) accentColor.copy(alpha = 0.16f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        ),
        border = if (isBound) null else BorderStroke(1.dp, Color(0xFFCBD5E1))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = if (isBound) accentColor else Color(0xFF64748B),
                fontSize = 11.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null,
                    tint = if (isBound) accentColor else Color(0xFF64748B),
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = if (isHighlighted && isBound) FontWeight.ExtraBold else FontWeight.Bold,
                    color = if (isBound) MaterialTheme.colorScheme.onSurface else Color(0xFF475569),
                    fontSize = 13.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun StudentDaysScheduleSubTileRow(scheduleDaysString: String) {
    val weekDays = remember {
        listOf(
            Triple("MON", java.util.Calendar.MONDAY, "Monday"),
            Triple("TUE", java.util.Calendar.TUESDAY, "Tuesday"),
            Triple("WED", java.util.Calendar.WEDNESDAY, "Wednesday"),
            Triple("THU", java.util.Calendar.THURSDAY, "Thursday"),
            Triple("FRI", java.util.Calendar.FRIDAY, "Friday"),
            Triple("SAT", java.util.Calendar.SATURDAY, "Saturday"),
            Triple("SUN", java.util.Calendar.SUNDAY, "Sunday")
        )
    }

    val todayCalendarDay = remember {
        java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 4.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        text = "WEEKLY SCHEDULE",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp,
                        letterSpacing = 0.5.sp
                    )
                }
                Surface(
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                ) {
                    Text(
                        text = if (scheduleDaysString.isBlank()) "MON-FRI" else scheduleDaysString,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 11.sp
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                weekDays.forEach { (abbr, calDay, fullName) ->
                    val isToday = (calDay == todayCalendarDay)
                    val isInSchedule = remember(scheduleDaysString, abbr, fullName) {
                        isStudentDayInSchedule(abbr, fullName, scheduleDaysString)
                    }

                    StudentDaySubTile(
                        modifier = Modifier.weight(1f),
                        abbr = abbr,
                        isToday = isToday,
                        isInSchedule = isInSchedule
                    )
                }
            }
        }
    }
}

@Composable
private fun StudentDaySubTile(
    modifier: Modifier = Modifier,
    abbr: String,
    isToday: Boolean,
    isInSchedule: Boolean
) {
    val backgroundColor = when {
        isToday -> MaterialTheme.colorScheme.primary
        isInSchedule -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
        else -> MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
    }

    val contentColor = when {
        isToday -> Color.White
        isInSchedule -> MaterialTheme.colorScheme.onPrimaryContainer
        else -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = abbr,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = if (isToday || isInSchedule) FontWeight.ExtraBold else FontWeight.Bold,
                    color = contentColor,
                    fontSize = 13.5.sp
                )
            }
        }
    }
}

private fun formatStudentDisplayTime(raw: String): String {
    if (raw.isBlank() || raw == "N/A") return "--:--"
    try {
        if (raw.contains("AM", ignoreCase = true) || raw.contains("PM", ignoreCase = true)) {
            return raw
        }
        if (raw.length == 4 && raw.all { it.isDigit() }) {
            val hr = raw.substring(0, 2).toInt()
            val min = raw.substring(2, 4)
            val ampm = if (hr >= 12) "PM" else "AM"
            val hr12 = if (hr % 12 == 0) 12 else hr % 12
            return String.format("%d:%s %s", hr12, min, ampm)
        }
    } catch (e: Exception) {
    }
    return raw
}

private fun isStudentDayInSchedule(abbr: String, fullName: String, scheduleDaysString: String): Boolean {
    if (scheduleDaysString.isBlank()) return false
    val schedUpper = scheduleDaysString.uppercase()
    val abbrUpper = abbr.uppercase()
    val fullUpper = fullName.uppercase()

    if (schedUpper.contains(abbrUpper) || schedUpper.contains(fullUpper)) return true

    if (schedUpper.contains("-")) {
        val parts = schedUpper.split("-").map { it.trim() }
        if (parts.size == 2) {
            val dayOrder = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
            val startIdx = dayOrder.indexOfFirst { parts[0].startsWith(it) }
            val endIdx = dayOrder.indexOfFirst { parts[1].startsWith(it) }
            val currentIdx = dayOrder.indexOf(abbrUpper)
            if (startIdx != -1 && endIdx != -1 && currentIdx != -1) {
                if (startIdx <= endIdx) {
                    return currentIdx in startIdx..endIdx
                } else {
                    return currentIdx >= startIdx || currentIdx <= endIdx
                }
            }
        }
    }
    return false
}

private fun parseStudentTimeToMinutes(raw: String): Int {
    val trimmed = raw.trim().uppercase()
    if (trimmed.isEmpty() || trimmed == "N/A") return -1
    
    val isPM = trimmed.contains("PM")
    val isAM = trimmed.contains("AM")
    val clean = trimmed.replace("AM", "").replace("PM", "").trim()
    
    val parts = clean.split(":")
    var hour = 0
    var min = 0
    if (parts.size >= 2) {
        hour = parts[0].toIntOrNull() ?: 0
        min = parts[1].toIntOrNull() ?: 0
    } else if (clean.length >= 3) {
        hour = clean.dropLast(2).toIntOrNull() ?: 0
        min = clean.takeLast(2).toIntOrNull() ?: 0
    }
    
    if (isPM && hour < 12) hour += 12
    if (isAM && hour == 12) hour = 0
    
    return hour * 60 + min
}

@Composable
fun StudentProfileView(
    profile: com.example.data.db.StudentProfileEntity?,
    registeredClassroom: com.example.data.db.ClassroomEntity?,
    attendanceLogs: List<com.example.data.db.AttendanceEntity>,
    submissions: List<com.example.data.db.TaskSubmissionEntity>
) {
    val birthFormatted = remember(profile?.birth) {
        if (profile != null && profile.birth > 0) {
            val sdf = java.text.SimpleDateFormat("MMMM dd, yyyy", java.util.Locale.getDefault())
            sdf.format(java.util.Date(profile.birth * 1000L))
        } else {
            "N/A"
        }
    }

    val registeredAtFormatted = remember(profile?.createdAt) {
        if (profile != null && profile.createdAt > 0) {
            val sdf = java.text.SimpleDateFormat("MMMM dd, yyyy", java.util.Locale.getDefault())
            sdf.format(java.util.Date(profile.createdAt))
        } else {
            "N/A"
        }
    }

    val sexFormatted = remember(profile?.sex) {
        when (profile?.sex?.uppercase()) {
            "M", "MALE", "BOY" -> "Male"
            "F", "FEMALE", "GIRL" -> "Female"
            else -> profile?.sex ?: "N/A"
        }
    }

    val totalAttendance = attendanceLogs.size
    val presentCount = attendanceLogs.count { it.status == "PRESENT" }
    val lateCount = attendanceLogs.count { it.status == "LATE" }
    val absentCount = attendanceLogs.count { it.status == "ABSENT" }
    val attendanceRate = if (totalAttendance > 0) {
        ((presentCount + lateCount).toDouble() / totalAttendance * 100).toInt()
    } else 100

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // TOP HERO PROFILE CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                var personTapCount by remember { androidx.compose.runtime.mutableIntStateOf(0) }
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(72.dp)
                        .clickable {
                            personTapCount++
                            if (personTapCount >= 7) {
                                StudentBleManager.isDebugConsoleVisible.value = true
                            }
                        },
                    shadowElevation = 0.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(44.dp)
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = profile?.fullName?.ifBlank { "Student Account" } ?: "Student Account",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "SID: ${profile?.sid ?: "N/A"}",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "LRN: ${profile?.lrn ?: "N/A"}",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }

        // 1. PERSONAL INFORMATION CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Badge,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Personal Information",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                StudentProfileDetailRow(label = "Full Name", value = profile?.fullName ?: "N/A")
                StudentProfileDetailRow(label = "Student ID (SID)", value = profile?.sid ?: "N/A")
                StudentProfileDetailRow(label = "LRN", value = profile?.lrn ?: "N/A")
                StudentProfileDetailRow(label = "Gender / Sex", value = sexFormatted)
                StudentProfileDetailRow(label = "Date of Birth", value = birthFormatted)
                StudentProfileDetailRow(label = "Account Registered", value = registeredAtFormatted)
            }
        }

        // 2. ENROLLED CLASSROOM CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Enrolled Classroom",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                StudentProfileDetailRow(
                    label = "Classroom Name",
                    value = registeredClassroom?.classroomName ?: "Not Enrolled"
                )
                StudentProfileDetailRow(
                    label = "Classroom ID (CID)",
                    value = registeredClassroom?.cid ?: "N/A"
                )
                StudentProfileDetailRow(
                    label = "Classroom Teacher",
                    value = registeredClassroom?.teacherName?.ifBlank { "Teacher" } ?: "N/A"
                )
                StudentProfileDetailRow(
                    label = "Weekly Schedule",
                    value = registeredClassroom?.days ?: "N/A"
                )
                StudentProfileDetailRow(
                    label = "Class Hours",
                    value = if (registeredClassroom != null) {
                        "${formatStudentDisplayTime(registeredClassroom.startTime)} - ${formatStudentDisplayTime(registeredClassroom.endTime)}"
                    } else "N/A"
                )
                StudentProfileDetailRow(
                    label = "Grace Period",
                    value = formatStudentDisplayTime(registeredClassroom?.graceTime ?: "")
                )
            }
        }

        // 3. ATTENDANCE & ACADEMIC ACTIVITY CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Attendance & Academic Stats",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                StudentProfileDetailRow(label = "Sessions Logged", value = "$totalAttendance session(s)")
                StudentProfileDetailRow(label = "Present Days", value = "$presentCount")
                StudentProfileDetailRow(label = "Late Days", value = "$lateCount")
                StudentProfileDetailRow(label = "Absent Days", value = "$absentCount")
                StudentProfileDetailRow(label = "Attendance Rate", value = "$attendanceRate%")
                StudentProfileDetailRow(label = "Tasks Submitted", value = "${submissions.size} task(s)")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun StudentProfileDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1.2f),
            textAlign = androidx.compose.ui.text.style.TextAlign.End
        )
    }
}



