package com.example.ui.screens

import android.os.Build
import android.util.Log
import android.Manifest
import android.content.Intent
import android.provider.Settings
import androidx.compose.ui.graphics.graphicsLayer
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.filled.Info
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.border
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.material3.LocalTextStyle
import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.example.ble.CalisBleService
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.TableChart
import com.example.util.CsvExportUtils
import com.example.util.AttendanceLabels
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.HelpOutline

import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.ui.input.pointer.positionChange
import kotlinx.coroutines.TimeoutCancellationException
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.zIndex
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.BluetoothDisabled
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.calisTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ble.TeacherBleManager
import com.example.ble.DeviceState
import com.example.util.BlePermissionUtils
import com.example.data.db.ApprovedStudentEntity
import com.example.data.db.AttendanceEntity
import com.example.data.db.ClassroomEntity
import com.example.data.db.PendingRegistrationEntity
import com.example.data.db.TaskEntity
import com.example.data.db.TaskSubmissionEntity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import com.example.ui.components.SettingsDialog
import com.example.ui.components.LazyTabContent
import com.example.ui.theme.StatusAbsentRed
import com.example.ui.theme.StatusLateAmber
import com.example.ui.theme.StatusPresentGreen
import com.example.ui.theme.AccentGradient
import com.example.ui.viewmodel.AuthViewModel
import com.example.ui.viewmodel.TeacherViewModel
import com.example.ui.viewmodel.TeacherViewModel.RegDialogStep
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherMainScreen(
    teacherViewModel: TeacherViewModel,
    authViewModel: AuthViewModel,
    onLogOut: () -> Unit,
    onAccountDeleted: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val prefs = remember(context) { context.getSharedPreferences("calis_app_prefs", android.content.Context.MODE_PRIVATE) }
    var showPrototypeDialog by remember {
        mutableStateOf(
            prefs.getBoolean("show_prototype_dialog_teacher", false) ||
            (!prefs.getBoolean("has_seen_prototype_dialog_teacher", false) && prefs.getString("user_role", "") == "TEACHER")
        )
    }

    var selectedStudentForDetail by remember { mutableStateOf<ApprovedStudentEntity?>(null) }
    var previousScreen by remember { mutableStateOf<String?>(null) }

    var dashboardShowHistorySubPage by remember { mutableStateOf(false) }
    var dashboardSelectedMonthKey by remember { mutableStateOf<String?>(null) }
    var dashboardSelectedDate by remember { mutableStateOf<String?>(null) }
    var studentsShowRequestsSubPage by remember { mutableStateOf(false) }
    var studentsShowHistorySubPage by remember { mutableStateOf(false) }

    val teacher by teacherViewModel.currentTeacher.collectAsState()
    val classroom by teacherViewModel.activeClassroom.collectAsState()
    val currentClassroom by teacherViewModel.currentClassroom.collectAsState()
    val targetClassroom by teacherViewModel.targetClassroom.collectAsState()
    val isClassroomLoaded by teacherViewModel.isClassroomLoaded.collectAsState()
    val connectionState by teacherViewModel.connectionState.collectAsState()
    val deviceState by teacherViewModel.deviceState.collectAsState()
    val scannedDevices by teacherViewModel.scannedDevices.collectAsState()
    val isAuthenticated by teacherViewModel.isAuthenticated.collectAsState()
    val isUserDisconnected by teacherViewModel.isUserDisconnected.collectAsState()

    val pendingRegistrations by teacherViewModel.pendingRegistrations.collectAsState()
    val approvedStudents by teacherViewModel.approvedStudents.collectAsState()
    val attendanceLogs by teacherViewModel.attendanceLogs.collectAsState()
    val tasks by teacherViewModel.tasks.collectAsState()
    val selectedTaskForSubmissions by teacherViewModel.selectedTaskForSubmissions.collectAsState()
    val currentSubmissions by teacherViewModel.currentSubmissions.collectAsState()
    val unviewedTaskCount by teacherViewModel.unviewedTaskSubmissionsCount.collectAsState()
    val unviewedTaskIds by teacherViewModel.unviewedSubmittedTaskIds.collectAsState()
    val unviewedTaskCounts by teacherViewModel.taskUnviewedSubmissionCounts.collectAsState()
    val pendingNavigationTaskId by teacherViewModel.pendingNavigationTaskId.collectAsState()

    var currentScreen by remember { mutableStateOf("DASHBOARD") }

    LaunchedEffect(pendingNavigationTaskId, tasks) {
        val taskId = pendingNavigationTaskId
        if (!taskId.isNullOrBlank()) {
            if (taskId == "GENERAL_TASKS") {
                currentScreen = "TASKS"
                teacherViewModel.setPendingNavigationTaskId(null)
                CalisBleService.dismissTaskSubmissionNotification(context)
            } else {
                val targetTask = tasks.find { it.tkid == taskId }
                if (targetTask != null) {
                    teacherViewModel.selectTaskForSubmissions(targetTask)
                    teacherViewModel.markTaskSubmissionsAsViewed(targetTask.tkid)
                    currentScreen = "TASKS"
                    teacherViewModel.setPendingNavigationTaskId(null)
                    CalisBleService.dismissTaskSubmissionNotification(context)
                } else if (tasks.isNotEmpty()) {
                    currentScreen = "TASKS"
                    teacherViewModel.setPendingNavigationTaskId(null)
                    CalisBleService.dismissTaskSubmissionNotification(context)
                }
            }
        }
    }
    var settingsSubpage by remember { mutableStateOf<String?>(null) }
    var lastBackTime by remember { mutableStateOf(0L) }
    androidx.activity.compose.BackHandler(enabled = true) {
        if (studentsShowRequestsSubPage) {
            studentsShowRequestsSubPage = false
        } else if (studentsShowHistorySubPage) {
            if (dashboardSelectedDate != null) {
                dashboardSelectedDate = null
            } else if (dashboardSelectedMonthKey != null) {
                dashboardSelectedMonthKey = null
            } else {
                studentsShowHistorySubPage = false
            }
        } else if (dashboardShowHistorySubPage) {
            if (dashboardSelectedDate != null) {
                dashboardSelectedDate = null
            } else if (dashboardSelectedMonthKey != null) {
                dashboardSelectedMonthKey = null
            } else {
                dashboardShowHistorySubPage = false
            }
        } else if (selectedStudentForDetail != null) {
            selectedStudentForDetail = null
            previousScreen?.let {
                currentScreen = it
                previousScreen = null
            }
        } else if (selectedTaskForSubmissions != null) {
            teacherViewModel.selectTaskForSubmissions(null)
        } else if (currentScreen == "SETTINGS" && settingsSubpage != null) {
            settingsSubpage = null
        } else if (currentScreen == "ABOUT") {
            currentScreen = "DASHBOARD"
        } else if (currentScreen != "DASHBOARD") {
            currentScreen = "DASHBOARD"
            previousScreen = null
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

    var showCreateTaskDialog by remember { mutableStateOf(false) }
    val teacherTasksListState = rememberSaveable(saver = LazyListState.Saver) { LazyListState() }
    var exportInitialTab by remember { mutableStateOf(0) }
    var pendingExportFileName by remember { mutableStateOf("export.csv") }
    var pendingExportCsvContent by remember { mutableStateOf("") }
    val todayDateStr = remember {
        java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date())
    }

    fun performDirectCsvSaveFallback(fileName: String, csvContent: String) {
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                val contentValues = android.content.ContentValues().apply {
                    put(android.provider.MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(android.provider.MediaStore.MediaColumns.MIME_TYPE, "text/csv")
                    put(android.provider.MediaStore.MediaColumns.RELATIVE_PATH, android.os.Environment.DIRECTORY_DOWNLOADS)
                }
                val uri = context.contentResolver.insert(android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
                if (uri != null) {
                    context.contentResolver.openOutputStream(uri)?.use { os ->
                        os.write(csvContent.toByteArray(Charsets.UTF_8))
                    }
                    Toast.makeText(context, "Saved to Downloads: $fileName", Toast.LENGTH_LONG).show()
                    return
                }
            }
            val downloadsDir = android.os.Environment.getExternalStoragePublicDirectory(android.os.Environment.DIRECTORY_DOWNLOADS)
            if (!downloadsDir.exists()) downloadsDir.mkdirs()
            val file = java.io.File(downloadsDir, fileName)
            file.writeText(csvContent, Charsets.UTF_8)
            Toast.makeText(context, "Saved to Downloads: ${file.name}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Log.e("TeacherMainScreen", "Direct save fallback failed, opening share menu", e)
            try {
                val sendIntent = android.content.Intent().apply {
                    action = android.content.Intent.ACTION_SEND
                    putExtra(android.content.Intent.EXTRA_TEXT, csvContent)
                    type = "text/csv"
                }
                val shareIntent = android.content.Intent.createChooser(sendIntent, "Save or Share CSV")
                shareIntent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(shareIntent)
            } catch (e2: Exception) {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? android.content.ClipboardManager
                val clip = android.content.ClipData.newPlainText("Exported CSV", csvContent)
                clipboard?.setPrimaryClip(clip)
                Toast.makeText(context, "CSV copied to clipboard!", Toast.LENGTH_LONG).show()
            }
        }
    }

    val createDocumentLauncher = rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.CreateDocument("*/*")
    ) { uri ->
        if (uri != null) {
            try {
                context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                    outputStream.write(pendingExportCsvContent.toByteArray(Charsets.UTF_8))
                }
                Toast.makeText(context, "CSV file saved successfully!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e("TeacherMainScreen", "Failed to write to file picker URI, using fallback", e)
                performDirectCsvSaveFallback(pendingExportFileName, pendingExportCsvContent)
            }
        }
    }

    var topMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        teacherViewModel.userMessage.collectLatest { msg ->
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
                val formattedTeacherName = remember(teacher?.fullName) {
                    com.example.util.BlePermissionUtils.formatTeacherNameWithMiddleInitial(
                        teacher?.fullName?.trim()?.ifBlank { "Teacher" } ?: "Teacher",
                        context
                    ).ifBlank { "Teacher" }
                }

                // --- RIBBON MENU HEADER ---
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 18.dp)
                ) {
                    androidx.compose.foundation.Image(
                        painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.logo_calis_inverted),
                        contentDescription = "Calis Logo",
                        modifier = Modifier.size(120.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = formattedTeacherName,
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
                    label = { Text("Classroom") },
                    selected = currentScreen == "DASHBOARD",
                    onClick = {
                        currentScreen = "DASHBOARD"
                        settingsSubpage = null
                        dashboardShowHistorySubPage = false
                        dashboardSelectedMonthKey = null
                        dashboardSelectedDate = null
                        selectedStudentForDetail = null
                        studentsShowRequestsSubPage = false
                        studentsShowHistorySubPage = false
                        teacherViewModel.selectTaskForSubmissions(null)
                        previousScreen = null
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Profile") },
                    selected = currentScreen == "PROFILE",
                    onClick = {
                        currentScreen = "PROFILE"
                        settingsSubpage = null
                        dashboardShowHistorySubPage = false
                        dashboardSelectedMonthKey = null
                        dashboardSelectedDate = null
                        selectedStudentForDetail = null
                        studentsShowRequestsSubPage = false
                        studentsShowHistorySubPage = false
                        teacherViewModel.selectTaskForSubmissions(null)
                        previousScreen = null
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Download, contentDescription = null) },
                    label = { Text("Export Center") },
                    selected = currentScreen == "EXPORT",
                    onClick = {
                        exportInitialTab = 0
                        currentScreen = "EXPORT"
                        settingsSubpage = null
                        dashboardShowHistorySubPage = false
                        dashboardSelectedMonthKey = null
                        dashboardSelectedDate = null
                        selectedStudentForDetail = null
                        studentsShowRequestsSubPage = false
                        studentsShowHistorySubPage = false
                        teacherViewModel.selectTaskForSubmissions(null)
                        previousScreen = null
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                    label = { Text("Settings") },
                    selected = currentScreen == "SETTINGS",
                    onClick = {
                        currentScreen = "SETTINGS"
                        settingsSubpage = null
                        dashboardShowHistorySubPage = false
                        dashboardSelectedMonthKey = null
                        dashboardSelectedDate = null
                        selectedStudentForDetail = null
                        studentsShowRequestsSubPage = false
                        studentsShowHistorySubPage = false
                        teacherViewModel.selectTaskForSubmissions(null)
                        previousScreen = null
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    label = { Text("About") },
                    selected = currentScreen == "ABOUT",
                    onClick = {
                        currentScreen = "ABOUT"
                        settingsSubpage = null
                        dashboardShowHistorySubPage = false
                        dashboardSelectedMonthKey = null
                        dashboardSelectedDate = null
                        selectedStudentForDetail = null
                        studentsShowRequestsSubPage = false
                        studentsShowHistorySubPage = false
                        teacherViewModel.selectTaskForSubmissions(null)
                        previousScreen = null
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
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
            topBar = {
                val isAttendanceHistoryActive = (currentScreen == "DASHBOARD" && dashboardShowHistorySubPage) ||
                        (currentScreen == "STUDENTS" && studentsShowHistorySubPage)
                val attendanceHeaderTitle = when {
                    dashboardSelectedDate != null -> formatAttendanceMonthAndDay(dashboardSelectedDate!!)
                    dashboardSelectedMonthKey != null -> {
                        try {
                            val d = java.text.SimpleDateFormat("yyyy-MM", java.util.Locale.US).parse(dashboardSelectedMonthKey!!)
                            if (d != null) java.text.SimpleDateFormat("MMMM yyyy", java.util.Locale.US).format(d) else "Month"
                        } catch (e: Exception) { "Month" }
                    }
                    else -> "Attendance History"
                }

                TopAppBar(
                    title = {
                        when {
                            currentScreen == "ABOUT" -> {
                                Text(
                                    text = "About",
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 25.sp, fontWeight = FontWeight.ExtraBold),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            currentScreen == "SETTINGS" && settingsSubpage != null -> {
                                Text(
                                    text = when (settingsSubpage) {
                                        "SECURITY" -> "Security & Startup"
                                        "NOTIFICATIONS" -> "Notifications"
                                        "CALIS_DEVICE" -> "Calis Device Settings"
                                        "ACTIONS" -> "Account Actions"
                                        else -> "Settings"
                                    },
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 25.sp, fontWeight = FontWeight.ExtraBold),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            isAttendanceHistoryActive -> {
                                Text(
                                    text = attendanceHeaderTitle,
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 25.sp, fontWeight = FontWeight.ExtraBold),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            currentScreen == "STUDENTS" && studentsShowRequestsSubPage -> {
                                Text(
                                    text = "Join Requests",
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 25.sp, fontWeight = FontWeight.ExtraBold),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            currentScreen == "TASKS" && selectedTaskForSubmissions != null -> {
                                Text(
                                    text = selectedTaskForSubmissions?.title ?: "Task",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            currentScreen == "EXPORT" -> {
                                Text(
                                    text = "Export Center",
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 25.sp, fontWeight = FontWeight.ExtraBold),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            else -> {
                                Text(
                                    text = when (currentScreen) {
                                        "DASHBOARD" -> currentClassroom?.classroomName ?: classroom?.classroomName ?: "Classroom"
                                        "STUDENTS" -> "Students"
                                        "SUBJECTS" -> "Class Subjects"
                                        "TASKS" -> "Tasks"
                                        "PROFILE" -> "Teacher Profile"
                                        "SETTINGS" -> "Settings"
                                        else -> currentClassroom?.classroomName ?: classroom?.classroomName ?: "Classroom"
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
                            currentScreen == "ABOUT" -> {
                                IconButton(
                                    onClick = { currentScreen = "DASHBOARD" },
                                    modifier = Modifier.testTag("about_back_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                            currentScreen == "SETTINGS" && settingsSubpage != null -> {
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
                            isAttendanceHistoryActive -> {
                                IconButton(
                                    onClick = {
                                        if (dashboardSelectedDate != null) {
                                            dashboardSelectedDate = null
                                        } else if (dashboardSelectedMonthKey != null) {
                                            dashboardSelectedMonthKey = null
                                        } else {
                                            if (dashboardShowHistorySubPage) dashboardShowHistorySubPage = false
                                            if (studentsShowHistorySubPage) studentsShowHistorySubPage = false
                                        }
                                    },
                                    modifier = Modifier.testTag("attendance_history_back_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                            currentScreen == "STUDENTS" && studentsShowRequestsSubPage -> {
                                IconButton(
                                    onClick = { studentsShowRequestsSubPage = false },
                                    modifier = Modifier.testTag("back_to_masterlist_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back to Students"
                                    )
                                }
                            }
                            currentScreen == "TASKS" && selectedTaskForSubmissions != null -> {
                                IconButton(
                                    onClick = { teacherViewModel.selectTaskForSubmissions(null) },
                                    modifier = Modifier.testTag("back_to_tasks_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back to Tasks"
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
            floatingActionButton = {
                when {
                    currentScreen == "TASKS" && selectedTaskForSubmissions == null && tasks.isNotEmpty() -> {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ExtendedFloatingActionButton(
                                onClick = {
                                    exportInitialTab = 1
                                    currentScreen = "EXPORT"
                                },
                                modifier = Modifier.testTag("export_tasks_fab"),
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp)
                            ) {
                                Text("Export Tasks")
                            }
                            FloatingActionButton(
                                onClick = { showCreateTaskDialog = true },
                                modifier = Modifier.testTag("create_task_fab"),
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Create Task")
                            }
                        }
                    }
                    currentScreen == "STUDENTS" && !studentsShowRequestsSubPage && !studentsShowHistorySubPage && selectedStudentForDetail == null -> {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ExtendedFloatingActionButton(
                                onClick = {
                                    studentsShowHistorySubPage = true
                                },
                                modifier = Modifier.testTag("history_students_fab"),
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp)
                            ) {
                                Text("History")
                            }
                            BadgedBox(
                                badge = {
                                    if (pendingRegistrations.isNotEmpty()) {
                                        Badge(
                                            containerColor = MaterialTheme.colorScheme.error,
                                            contentColor = MaterialTheme.colorScheme.onError
                                        ) {
                                            Text("${pendingRegistrations.size}")
                                        }
                                    }
                                }
                            ) {
                                FloatingActionButton(
                                    onClick = { studentsShowRequestsSubPage = true },
                                    modifier = Modifier.testTag("registration_requests_fab"),
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary,
                                    elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.GroupAdd,
                                        contentDescription = "Join Requests"
                                    )
                                }
                            }
                        }
                    }
                }
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.School, contentDescription = null) },
                        label = { Text("Classroom") },
                        selected = currentScreen == "DASHBOARD",
                        onClick = {
                            currentScreen = "DASHBOARD"
                            dashboardShowHistorySubPage = false
                            dashboardSelectedMonthKey = null
                            dashboardSelectedDate = null
                            selectedStudentForDetail = null
                            studentsShowRequestsSubPage = false
                            studentsShowHistorySubPage = false
                            teacherViewModel.selectTaskForSubmissions(null)
                            previousScreen = null
                        },
                        modifier = Modifier.testTag("teacher_tab_dashboard")
                    )
                    NavigationBarItem(
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (pendingRegistrations.isNotEmpty()) {
                                        Badge { Text("${pendingRegistrations.size}") }
                                    }
                                }
                            ) {
                                Icon(Icons.Default.Group, contentDescription = null)
                            }
                        },
                        label = { Text("Students") },
                        selected = currentScreen == "STUDENTS",
                        onClick = {
                            currentScreen = "STUDENTS"
                            selectedStudentForDetail = null
                            studentsShowRequestsSubPage = false
                            studentsShowHistorySubPage = false
                            dashboardShowHistorySubPage = false
                            dashboardSelectedMonthKey = null
                            dashboardSelectedDate = null
                            teacherViewModel.selectTaskForSubmissions(null)
                            previousScreen = null
                        },
                        modifier = Modifier.testTag("teacher_tab_students")
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.MenuBook, contentDescription = null) },
                        label = { Text("Subjects") },
                        selected = currentScreen == "SUBJECTS",
                        onClick = {
                            currentScreen = "SUBJECTS"
                            selectedStudentForDetail = null
                            studentsShowRequestsSubPage = false
                            studentsShowHistorySubPage = false
                            dashboardShowHistorySubPage = false
                            dashboardSelectedMonthKey = null
                            dashboardSelectedDate = null
                            teacherViewModel.selectTaskForSubmissions(null)
                            previousScreen = null
                        },
                        modifier = Modifier.testTag("teacher_tab_subjects")
                    )
                    NavigationBarItem(
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (unviewedTaskCount > 0) {
                                        Badge { Text("$unviewedTaskCount") }
                                    }
                                }
                            ) {
                                Icon(Icons.Default.Assignment, contentDescription = null)
                            }
                        },
                        label = { Text("Tasks") },
                        selected = currentScreen == "TASKS",
                        onClick = {
                            currentScreen = "TASKS"
                            teacherViewModel.selectTaskForSubmissions(null)
                            selectedStudentForDetail = null
                            studentsShowRequestsSubPage = false
                            studentsShowHistorySubPage = false
                            dashboardShowHistorySubPage = false
                            dashboardSelectedMonthKey = null
                            dashboardSelectedDate = null
                            previousScreen = null
                        },
                        modifier = Modifier.testTag("teacher_tab_tasks")
                    )
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                when (currentScreen) {
                    "DASHBOARD" -> LazyTabContent(currentScreen) {
                        val isWaitingForReconnect by teacherViewModel.isWaitingForReconnectFlow.collectAsState()
                        TeacherDashboardView(
                            classroom = classroom,
                            currentClassroom = currentClassroom,
                            connectionState = connectionState,
                            deviceState = deviceState,
                            scannedDevices = scannedDevices,
                            isAuthenticated = isAuthenticated,
                            isUserDisconnected = isUserDisconnected,
                            isWaitingForReconnect = isWaitingForReconnect,
                            approvedStudents = approvedStudents,
                            attendanceLogs = attendanceLogs,
                            onConnectDevice = { teacherViewModel.connectToDevice(it) },
                            onDisconnectDevice = { teacherViewModel.disconnectDevice() },
                            onScanDevices = { teacherViewModel.startBleScan() },
                            onStartSession = { teacherViewModel.startSession() },
                            onEndSession = { teacherViewModel.endSession() },
                            onUnregister = { teacherViewModel.unregisterDevice() },
                            teacherViewModel = teacherViewModel,
                            onNavigateToExport = {
                                exportInitialTab = 0
                                currentScreen = "EXPORT"
                            },
                            onStudentClick = { student ->
                                previousScreen = currentScreen
                                selectedStudentForDetail = student
                                currentScreen = "STUDENTS"
                            },
                            showHistorySubPage = dashboardShowHistorySubPage,
                            onShowHistorySubPageChanged = { dashboardShowHistorySubPage = it },
                            selectedMonthKey = dashboardSelectedMonthKey,
                            onSelectedMonthKeyChanged = { dashboardSelectedMonthKey = it },
                            selectedDate = dashboardSelectedDate,
                            onSelectedDateChanged = { dashboardSelectedDate = it },
                            onNavigateToClassroom = {
                                currentScreen = "DASHBOARD"
                                dashboardShowHistorySubPage = false
                                dashboardSelectedMonthKey = null
                                dashboardSelectedDate = null
                                selectedStudentForDetail = null
                                studentsShowRequestsSubPage = false
                                studentsShowHistorySubPage = false
                                teacherViewModel.selectTaskForSubmissions(null)
                                previousScreen = null
                            }
                        )
                    }

                    "STUDENTS" -> LazyTabContent(currentScreen) {
                        TeacherStudentsView(
                            pendingList = pendingRegistrations,
                            approvedList = approvedStudents,
                            attendanceLogs = attendanceLogs,
                            isActiveSession = (classroom ?: currentClassroom)?.isActiveSession == true,
                            onApprove = { teacherViewModel.approveStudent(it) },
                            onReject = { teacherViewModel.rejectStudent(it) },
                            onUpdateStatus = { sid, dateStr, newStatus ->
                                teacherViewModel.updateAttendanceStatus(sid, dateStr, newStatus)
                            },
                            onNavigateToExport = {
                                exportInitialTab = 0
                                currentScreen = "EXPORT"
                            },
                            autoApproveEnabled = teacherViewModel.autoApproveEnabled.collectAsState().value,
                            onToggleAutoApprove = { teacherViewModel.setAutoApproveEnabled(it) },
                            selectedStudent = selectedStudentForDetail,
                            onSelectedStudentChanged = { student ->
                                selectedStudentForDetail = student
                                if (student == null) {
                                    previousScreen?.let {
                                        currentScreen = it
                                        previousScreen = null
                                    }
                                }
                            },
                            showingRequestsSubPage = studentsShowRequestsSubPage,
                            onShowingRequestsSubPageChanged = { studentsShowRequestsSubPage = it },
                            showingHistorySubPage = studentsShowHistorySubPage,
                            onShowingHistorySubPageChanged = { studentsShowHistorySubPage = it },
                            selectedMonthKey = dashboardSelectedMonthKey,
                            onSelectedMonthKeyChanged = { dashboardSelectedMonthKey = it },
                            selectedDate = dashboardSelectedDate,
                            onSelectedDateChanged = { dashboardSelectedDate = it }
                        )
                    }

                    "SUBJECTS" -> LazyTabContent(currentScreen) {
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
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    "TASKS" -> LazyTabContent(currentScreen) {
                        val currentDeliveries by teacherViewModel.currentDeliveries.collectAsState()
                        val isSubmissionsLoading by teacherViewModel.isSubmissionsLoading.collectAsState()
                        androidx.activity.compose.BackHandler(enabled = selectedTaskForSubmissions != null) {
                            teacherViewModel.selectTaskForSubmissions(null)
                        }
                        TasksManagementView(
                            tasks = tasks,
                            selectedTask = selectedTaskForSubmissions,
                            submissions = currentSubmissions,
                            deliveries = currentDeliveries,
                            approvedStudents = approvedStudents,
                            unviewedTaskIds = unviewedTaskIds,
                            unviewedTaskCounts = unviewedTaskCounts,
                            tasksListState = teacherTasksListState,
                            onSelectTask = { task ->
                                teacherViewModel.selectTaskForSubmissions(task)
                                if (task != null) {
                                    teacherViewModel.markTaskSubmissionsAsViewed(task.tkid)
                                }
                            },
                            onDeleteTask = { teacherViewModel.deleteTask(it) },
                            isSubmissionsLoading = isSubmissionsLoading,
                            onStudentClick = { student ->
                                previousScreen = currentScreen
                                selectedStudentForDetail = student
                                currentScreen = "STUDENTS"
                            },
                            onCreateTaskClick = { showCreateTaskDialog = true }
                        )
                    }

                    "PROFILE" -> LazyTabContent(currentScreen) {
                        TeacherProfileView(
                            teacher = teacher,
                            classroom = classroom ?: currentClassroom,
                            approvedStudentsCount = approvedStudents.size,
                            bleConnectionState = connectionState
                        )
                    }

                    "EXPORT" -> LazyTabContent(currentScreen) {
                        ExportCenterView(
                            teacherViewModel = teacherViewModel,
                            classroom = classroom ?: currentClassroom,
                            tasks = tasks,
                            initialTab = exportInitialTab,
                            onExportReady = { csv, title ->
                                pendingExportCsvContent = csv
                                val safeFileName = title.lowercase(Locale.US)
                                    .replace(Regex("[^a-z0-9]"), "_")
                                    .trim('_') + ".csv"
                                pendingExportFileName = safeFileName
                                try {
                                    createDocumentLauncher.launch(safeFileName)
                                } catch (e: Exception) {
                                    Log.e("TeacherMainScreen", "createDocumentLauncher.launch failed, saving directly", e)
                                    performDirectCsvSaveFallback(safeFileName, csv)
                                }
                            }
                        )
                    }

                    "ABOUT" -> LazyTabContent(currentScreen) {
                        AboutScreen()
                    }

                    "SETTINGS" -> LazyTabContent(currentScreen) {
                        SettingsScreen(
                            authViewModel = authViewModel,
                            onNavigateToProfile = { currentScreen = "PROFILE" },
                            onLoggedOut = onLogOut,
                            onAccountDeleted = onAccountDeleted,
                            currentSubpageExternal = settingsSubpage,
                            onSubpageChangeExternal = { settingsSubpage = it },
                            isCalisDeviceRegistered = (classroom?.isBound == true || currentClassroom?.isBound == true || targetClassroom?.isBound == true)
                        )
                    }
                }

                // Custom Top Banner Overlay, refined & aligned directly at the bottom of the tab name bar with a small space
                AnimatedVisibility(
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
                        .padding(top = 8.dp) // Refined and aligned directly at the bottom of the tab name bar
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

    // --- DIALOGS ---
    if (showPrototypeDialog && currentScreen == "DASHBOARD") {
        com.example.ui.components.PrototypeDisclaimerDialog(
            onDismiss = {
                showPrototypeDialog = false
                prefs.edit()
                    .putBoolean("has_seen_prototype_dialog_teacher", true)
                    .putBoolean("show_prototype_dialog_teacher", false)
                    .apply()
            }
        )
    }

    if (showCreateTaskDialog) {
        CreateTaskDialog(
            onDismiss = { showCreateTaskDialog = false },
            onCreate = { title, desc, deadline, score ->
                teacherViewModel.createTask(title, desc, deadline, score)
                showCreateTaskDialog = false
            }
        )
    }

}

// --- SUB-VIEWS FOR TEACHER ---

@Composable
fun StepRow(
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

@Composable
fun InteractiveRegistrationDialog(
    viewModel: TeacherViewModel,
    scannedDevices: List<TeacherBleManager.ScannedDevice>,
    onDismiss: () -> Unit,
    onNavigateToClassroom: () -> Unit = {}
) {
    val regDialogStep by viewModel.regDialogStep.collectAsState()
    val registrationDeviceName by viewModel.registrationDeviceName.collectAsState()
    val registrationClassroomName by viewModel.registrationClassroomName.collectAsState()
    val registrationError by viewModel.registrationError.collectAsState()
    val bleConnState by viewModel.bleManager.connectionState.collectAsState()
    val isGattEstablished = bleConnState is TeacherBleManager.ConnectionState.Connected ||
            regDialogStep == RegDialogStep.REGISTERING ||
            regDialogStep == RegDialogStep.AUTHENTICATING

    LaunchedEffect(Unit) {
        viewModel.resetRegistrationToDiscovering()
    }

    AlertDialog(
        onDismissRequest = {
            if (regDialogStep == RegDialogStep.DISCOVERING || regDialogStep == RegDialogStep.SUCCESS || registrationError != null) {
                viewModel.stopRegistrationFlow()
                onDismiss()
            } else if (!isGattEstablished) {
                viewModel.resetRegistrationToDiscovering()
            }
        },
        tonalElevation = 0.dp,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.fillMaxWidth(0.92f),
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurface,
        title = {
            Text(
                text = when (regDialogStep) {
                    RegDialogStep.SUCCESS -> "Registration Complete"
                    else -> "Register Calis Device"
                },
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                when (regDialogStep) {
                    RegDialogStep.DISCOVERING -> {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Available Calis Devices",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(
                                        text = "Scanning...",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(14.dp),
                                        strokeWidth = 2.dp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }

                            val filteredDevices = scannedDevices.filter { dev ->
                                val isFactoryMode = dev.state == DeviceState.FACTORY_MODE
                                val isCalisHardware = dev.hasServiceUuid || dev.name.contains("Calis", ignoreCase = true) || dev.name.contains("Setup", ignoreCase = true)
                                isFactoryMode && isCalisHardware
                            }

                            Box(modifier = Modifier.fillMaxWidth().height(220.dp)) {
                                if (filteredDevices.isEmpty()) {
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(16.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Bluetooth,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                                modifier = Modifier.size(28.dp)
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text = "Searching for unregistered Calis Devices near you... Make sure hardware is powered and in Factory Mode.",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                            )
                                        }
                                    }
                                } else {
                                    LazyColumn(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        items(filteredDevices) { dev ->
                                            Card(
                                                modifier = Modifier.fillMaxWidth(),
                                                shape = RoundedCornerShape(12.dp),
                                                colors = CardDefaults.cardColors(
                                                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                                                )
                                            ) {
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(12.dp),
                                                    horizontalArrangement = Arrangement.SpaceBetween,
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Column(modifier = Modifier.weight(1f)) {
                                                        Text(
                                                            text = dev.name.ifBlank { "Calis Device" },
                                                            fontWeight = FontWeight.Bold,
                                                            style = MaterialTheme.typography.bodyMedium
                                                        )
                                                    }
                                                    Button(
                                                        onClick = { 
                                                            viewModel.initiateDeviceRegistration(dev.address, dev.name) 
                                                        },
                                                        shape = RoundedCornerShape(8.dp),
                                                        colors = ButtonDefaults.buttonColors(
                                                            containerColor = MaterialTheme.colorScheme.primary
                                                        )
                                                    ) {
                                                        Text("Register", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    RegDialogStep.CONNECTING, RegDialogStep.REGISTERING, RegDialogStep.AUTHENTICATING -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (registrationError != null) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Error",
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(36.dp)
                                )
                            } else {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(28.dp),
                                    strokeWidth = 2.5.dp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                val stepTitle = when (regDialogStep) {
                                    RegDialogStep.CONNECTING -> "Connecting to Device..."
                                    RegDialogStep.REGISTERING -> "Registering Classroom..."
                                    RegDialogStep.AUTHENTICATING -> "Authenticating Device..."
                                    else -> "Processing..."
                                }
                                Text(
                                    text = stepTitle,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                val targetDevice = registrationDeviceName ?: "Calis Device"
                                val targetClassroom = if (registrationClassroomName.isNotBlank()) " for $registrationClassroomName" else ""
                                Text(
                                    text = "$targetDevice$targetClassroom",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                if (registrationError != null) {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = registrationError ?: "",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.error,
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                        modifier = Modifier.padding(horizontal = 16.dp)
                                    )
                                }
                            }
                        }
                    }

                    RegDialogStep.SUCCESS -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Success",
                                tint = StatusPresentGreen,
                                modifier = Modifier.size(36.dp)
                            )
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    text = "Registration Successful!",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = StatusPresentGreen
                                )
                                val clsName = if (registrationClassroomName.isNotBlank()) "Classroom \"$registrationClassroomName\"" else "Classroom"
                                Text(
                                    text = "$clsName is now registered and bound to your Calis Device.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                            }
                        }
                    }
                    else -> {}
                }
            }
        },
        confirmButton = {
            when (regDialogStep) {
                RegDialogStep.SUCCESS -> {
                    Button(
                        onClick = {
                            viewModel.stopRegistrationFlow()
                            onDismiss()
                            onNavigateToClassroom()
                        },
                        modifier = Modifier.testTag("dismiss_success_registration_dialog"),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Proceed to Classroom")
                    }
                }
                RegDialogStep.DISCOVERING -> {
                    TextButton(
                        onClick = {
                            viewModel.stopRegistrationFlow()
                            onDismiss()
                        }
                    ) {
                        Text("Cancel")
                    }
                }
                RegDialogStep.CONNECTING, RegDialogStep.REGISTERING, RegDialogStep.AUTHENTICATING -> {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (!isGattEstablished || registrationError != null) {
                            TextButton(
                                onClick = {
                                    viewModel.resetRegistrationToDiscovering()
                                },
                                modifier = Modifier.testTag("reset_registration_to_discovering_button")
                            ) {
                                Text("Back to Devices")
                            }
                        }
                        if (registrationError != null) {
                            Button(
                                onClick = {
                                    viewModel.retryRegistration()
                                },
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.testTag("retry_registration_button")
                            ) {
                                Text("Try Again")
                            }
                        }
                    }
                }
                else -> {}
            }
        },
        dismissButton = null
    )
}

private data class TeacherStepItem(
    val stepNum: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val actionButton: @Composable (() -> Unit)? = null
)

@Composable
fun TeacherDashboardView(
    classroom: ClassroomEntity?,
    currentClassroom: ClassroomEntity?,
    connectionState: TeacherBleManager.ConnectionState,
    deviceState: DeviceState,
    scannedDevices: List<TeacherBleManager.ScannedDevice>,
    isAuthenticated: Boolean = false,
    isUserDisconnected: Boolean = false,
    isWaitingForReconnect: Boolean = false,
    approvedStudents: List<ApprovedStudentEntity> = emptyList(),
    attendanceLogs: List<AttendanceEntity> = emptyList(),
    onConnectDevice: (String) -> Unit,
    onDisconnectDevice: () -> Unit,
    onScanDevices: () -> Unit,
    onStartSession: () -> Unit,
    onEndSession: () -> Unit,
    onUnregister: () -> Unit,
    teacherViewModel: TeacherViewModel,
    onNavigateToExport: () -> Unit = {},
    onHistorySubPageChanged: (Boolean) -> Unit = {},
    onStudentClick: (ApprovedStudentEntity) -> Unit = {},
    showHistorySubPage: Boolean = false,
    onShowHistorySubPageChanged: (Boolean) -> Unit = {},
    selectedMonthKey: String? = null,
    onSelectedMonthKeyChanged: (String?) -> Unit = {},
    selectedDate: String? = null,
    onSelectedDateChanged: (String?) -> Unit = {},
    onNavigateToClassroom: () -> Unit = {}
) {
    val context = LocalContext.current
    val activity = BlePermissionUtils.findActivity(context)
    var hasPermissions by remember { mutableStateOf(BlePermissionUtils.hasPermissions(context)) }
    var isBtEnabled by remember { mutableStateOf(BlePermissionUtils.isBluetoothEnabled(context)) }
    var isBtEnabling by remember { mutableStateOf(false) }
    var isNotificationGranted by remember { mutableStateOf(BlePermissionUtils.hasNotificationPermission(context)) }
    var isLocationEnabled by remember { mutableStateOf(BlePermissionUtils.isLocationEnabled(context)) }
    val isOlderThanAndroid12 = remember { android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S }
    val isLocationConditionMet = !isOlderThanAndroid12 || isLocationEnabled

    val enableBtLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        val enabled = BlePermissionUtils.isBluetoothEnabled(context)
        isBtEnabled = enabled
        if (enabled) {
            isBtEnabling = false
            if (hasPermissions && (!isOlderThanAndroid12 || isLocationEnabled)) {
                teacherViewModel.startBleScan(forceRestart = true)
                teacherViewModel.checkAndTriggerAutoConnect()
            }
        } else {
            isBtEnabling = true
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { _ ->
        val granted = BlePermissionUtils.hasPermissions(context)
        hasPermissions = granted
        if (granted && isBtEnabled && (!isOlderThanAndroid12 || isLocationEnabled)) {
            teacherViewModel.startBleScan(forceRestart = true)
            teacherViewModel.checkAndTriggerAutoConnect()
        }
    }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        isNotificationGranted = isGranted || BlePermissionUtils.hasNotificationPermission(context)
        if (isNotificationGranted) {
            com.example.util.ReminderNotificationHelper.checkAndPostSessionReminder(context)
            if (isBtEnabled && hasPermissions && (!isOlderThanAndroid12 || isLocationEnabled)) {
                teacherViewModel.startBleScan(forceRestart = true)
                teacherViewModel.checkAndTriggerAutoConnect()
            }
        }
    }
    
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, context) {
        val filter = android.content.IntentFilter().apply {
            addAction(android.bluetooth.BluetoothAdapter.ACTION_STATE_CHANGED)
            addAction(android.location.LocationManager.PROVIDERS_CHANGED_ACTION)
        }
        val receiver = object : android.content.BroadcastReceiver() {
            override fun onReceive(ctx: android.content.Context, intent: android.content.Intent) {
                when (intent.action) {
                    android.bluetooth.BluetoothAdapter.ACTION_STATE_CHANGED -> {
                        val state = intent.getIntExtra(android.bluetooth.BluetoothAdapter.EXTRA_STATE, android.bluetooth.BluetoothAdapter.ERROR)
                        when (state) {
                            android.bluetooth.BluetoothAdapter.STATE_TURNING_ON -> {
                                isBtEnabling = true
                            }
                            android.bluetooth.BluetoothAdapter.STATE_ON -> {
                                isBtEnabling = false
                                isBtEnabled = true
                                if (hasPermissions && (!isOlderThanAndroid12 || isLocationEnabled)) {
                                    teacherViewModel.startBleScan(forceRestart = true)
                                    teacherViewModel.checkAndTriggerAutoConnect()
                                }
                            }
                            android.bluetooth.BluetoothAdapter.STATE_TURNING_OFF -> {
                                isBtEnabling = false
                                isBtEnabled = false
                            }
                            android.bluetooth.BluetoothAdapter.STATE_OFF -> {
                                isBtEnabling = false
                                isBtEnabled = false
                            }
                            else -> {
                                val enabled = BlePermissionUtils.isBluetoothEnabled(context)
                                isBtEnabled = enabled
                                if (enabled) {
                                    isBtEnabling = false
                                    if (hasPermissions && (!isOlderThanAndroid12 || isLocationEnabled)) {
                                        teacherViewModel.startBleScan(forceRestart = true)
                                        teacherViewModel.checkAndTriggerAutoConnect()
                                    }
                                }
                            }
                        }
                    }
                    android.location.LocationManager.PROVIDERS_CHANGED_ACTION -> {
                        val locEnabled = BlePermissionUtils.isLocationEnabled(context)
                        isLocationEnabled = locEnabled
                        if (isBtEnabled && hasPermissions && (!isOlderThanAndroid12 || locEnabled)) {
                            teacherViewModel.startBleScan(forceRestart = true)
                            teacherViewModel.checkAndTriggerAutoConnect()
                        }
                    }
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
                if (enabled) {
                    isBtEnabling = false
                }
                isNotificationGranted = BlePermissionUtils.hasNotificationPermission(context)
                isLocationEnabled = BlePermissionUtils.isLocationEnabled(context)
                if (isBtEnabled && hasPermissions && (!isOlderThanAndroid12 || isLocationEnabled)) {
                    val connState = teacherViewModel.bleManager.connectionState.value
                    if (connState !is TeacherBleManager.ConnectionState.Connected && connState !is TeacherBleManager.ConnectionState.Connecting) {
                        teacherViewModel.startBleScan(forceRestart = true)
                    }
                    teacherViewModel.checkAndTriggerAutoConnect()
                }
                teacherViewModel.checkAndAutoAuthenticate()
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

    val enableBluetoothAction: () -> Unit = {
        isBtEnabling = true
        BlePermissionUtils.requestEnableBluetooth(
            activity = activity,
            launcher = enableBtLauncher,
            onEnabling = { isBtEnabling = true }
        )
    }

    LaunchedEffect(isBtEnabling) {
        if (isBtEnabling) {
            var attempts = 0
            while (attempts < 30 && isBtEnabling) {
                delay(100L)
                if (BlePermissionUtils.isBluetoothEnabled(context)) {
                    isBtEnabled = true
                    isBtEnabling = false
                    if (hasPermissions && isLocationConditionMet) {
                        teacherViewModel.startBleScan(forceRestart = true)
                        teacherViewModel.checkAndTriggerAutoConnect()
                    }
                    break
                }
                attempts++
            }
            if (isBtEnabling) {
                val enabled = BlePermissionUtils.isBluetoothEnabled(context)
                isBtEnabled = enabled
                isBtEnabling = false
                if (enabled && hasPermissions && isLocationConditionMet) {
                    teacherViewModel.startBleScan(forceRestart = true)
                    teacherViewModel.checkAndTriggerAutoConnect()
                }
            }
        }
    }

    var showUnregConfirmDialog by remember { mutableStateOf(false) }
    var showInteractiveRegDialog by remember { mutableStateOf(false) }

    var now by remember { mutableLongStateOf(System.currentTimeMillis()) }
    LaunchedEffect(Unit) {
        while (isActive) {
            now = System.currentTimeMillis()
            delay(500L)
        }
    }

    val targetClassroom by teacherViewModel.targetClassroom.collectAsState()
    val isClassroomLoaded by teacherViewModel.isClassroomLoaded.collectAsState()
    val activeCls = targetClassroom ?: classroom ?: currentClassroom


    if (showUnregConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showUnregConfirmDialog = false },
            tonalElevation = 0.dp,
            properties = DialogProperties(usePlatformDefaultWidth = false),
            modifier = Modifier.fillMaxWidth(0.92f),
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            textContentColor = MaterialTheme.colorScheme.onSurface,
            title = { Text("Unregister Calis Device?", fontWeight = FontWeight.Bold) },
            text = { Text("Are you sure you want to unregister this Calis Device back to Factory Mode?") },
            confirmButton = {
                Button(
                    onClick = {
                        showUnregConfirmDialog = false
                        onUnregister()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = StatusAbsentRed)
                ) {
                    Text("Unregister", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showUnregConfirmDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showInteractiveRegDialog) {
        InteractiveRegistrationDialog(
            viewModel = teacherViewModel,
            scannedDevices = scannedDevices,
            onDismiss = { showInteractiveRegDialog = false },
            onNavigateToClassroom = {
                onShowHistorySubPageChanged(false)
                onSelectedMonthKeyChanged(null)
                onSelectedDateChanged(null)
                onNavigateToClassroom()
            }
        )
    }

    LaunchedEffect(hasPermissions, isBtEnabled, isLocationConditionMet) {
        if (hasPermissions && isBtEnabled && isLocationConditionMet) {
            val conn = teacherViewModel.bleManager.connectionState.value
            if (conn !is TeacherBleManager.ConnectionState.Connected &&
                conn !is TeacherBleManager.ConnectionState.Connecting &&
                conn !is TeacherBleManager.ConnectionState.Disconnecting
            ) {
                teacherViewModel.startBleScan(forceRestart = true)
                teacherViewModel.checkAndTriggerAutoConnect()
            }
        }
    }

    val todayDateStr = remember {
        java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date())
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
        // Dedicated Connection Requirements Tile (Light-Red Highlighted on Top)
        if (isClassroomLoaded && (activeCls?.isBound != true || !isBtEnabled || !hasPermissions || !isNotificationGranted || !isLocationConditionMet)) {
            val coroutineScope = rememberCoroutineScope()
            var helpDialogStep by remember { mutableStateOf<TeacherStepItem?>(null) }

            val steps = buildList {
                // STEP 1: Permissions
                add(
                    TeacherStepItem(
                        title = "Grant Bluetooth Permissions",
                        description = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                            "Nearby Devices permission is required."
                        } else {
                            "Location permission is required to search for BLE devices."
                        },
                        isCompleted = hasPermissions,
                        actionButton = {
                            Button(
                                onClick = {
                                    val perms = BlePermissionUtils.getRequiredPermissions()
                                    val activity = BlePermissionUtils.findActivity(context)
                                    permissionLauncher.launch(perms)
                                    if (activity != null) androidx.core.app.ActivityCompat.requestPermissions(activity, perms, 1)
                                },
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

                // STEP 2: Enable Bluetooth
                add(
                    TeacherStepItem(
                        title = "Enable Bluetooth",
                        description = if (isBtEnabling) "Initializing Bluetooth adapter..." else "Directly turn on Bluetooth receiver.",
                        isCompleted = isBtEnabled,
                        actionButton = if (!isBtEnabled) {
                            {
                                Button(
                                    onClick = enableBluetoothAction,
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
                    TeacherStepItem(
                        title = "Allow Notifications",
                        description = if (isNotificationGranted) "Notification alerts permitted." else "Required for foreground session controls and Calis Device status notifications.",
                        isCompleted = isNotificationGranted,
                        actionButton = if (!isNotificationGranted) {
                            {
                                Button(
                                    onClick = {
                                        BlePermissionUtils.requestNotificationPermission(context, notificationPermissionLauncher)
                                    },
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

                // STEP 4: Enable Location Services (Required on Android 11 or older)
                if (isOlderThanAndroid12 && !isLocationEnabled) {
                    add(
                        TeacherStepItem(
                            title = "Enable Location Services",
                            description = "Location Services is required to search for BLE devices on Android 11 or older.",
                            isCompleted = isLocationEnabled,
                            actionButton = {
                                Button(
                                    onClick = {
                                        try {
                                            val intent = android.content.Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                                            intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
                                            context.startActivity(intent)
                                        } catch (e: Exception) {
                                            // ignore
                                        }
                                    },
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

                // STEP 5: Register Device
                add(
                    TeacherStepItem(
                        title = "Register Calis Device",
                        description = "Discover and bind your Calis ESP32.",
                        isCompleted = activeCls?.isBound == true,
                        actionButton = {
                            Button(
                                onClick = {
                                    teacherViewModel.startRegistrationFlow(activeCls?.classroomName ?: "10 - Newton")
                                    showInteractiveRegDialog = true
                                },
                                enabled = hasPermissions && isBtEnabled && isNotificationGranted && isLocationConditionMet,
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48)),
                                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                                modifier = Modifier.height(48.dp).defaultMinSize(minWidth = 140.dp)
                            ) {
                                Text(
                                    text = if (activeCls?.isBound == true) "Re-Register" else "Register",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                    )
                )
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
            LaunchedEffect(hasPermissions, isBtEnabled, isNotificationGranted, isLocationConditionMet, activeCls?.isBound) {
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
                            color = Color(0xFFE11D48) // Highlighted text color
                        )
                        Icon(
                            imageVector = Icons.Default.Bluetooth,
                            contentDescription = null,
                            tint = Color(0xFFE11D48), // Highlighted icon color
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
                                            } else if (step.actionButton != null && step.isCompleted && step.title.contains("Register", ignoreCase = true)) {
                                                step.actionButton.invoke()
                                            } else if (step.isCompleted) {
                                                val completedLabel = when {
                                                    step.title.contains("permission", ignoreCase = true) || step.title.contains("notification", ignoreCase = true) || step.title.contains("grant", ignoreCase = true) || step.title.contains("allow", ignoreCase = true) -> "Granted"
                                                    step.title.contains("bluetooth", ignoreCase = true) || step.title.contains("location", ignoreCase = true) -> "Enabled"
                                                    step.title.contains("register", ignoreCase = true) -> "Registered"
                                                    step.title.contains("join", ignoreCase = true) -> "Joined"
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
        // Main Classroom Info & Session Controls Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (activeCls?.isBound == true) MaterialTheme.colorScheme.surface else Color(0xFFF1F5F9)
            ),
            border = if (activeCls?.isBound == true) null else BorderStroke(1.dp, Color(0xFFCBD5E1)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (activeCls != null) {
                    val targetClsName = activeCls.classroomName
                    val savedMac = teacherViewModel.savedMacAddress.collectAsState().value
                    val tempMac = teacherViewModel.registrationDeviceAddress.collectAsState().value
                    val registeredMac = if (!activeCls.deviceAddress.isNullOrBlank()) activeCls.deviceAddress else (savedMac ?: tempMac)

                    val matchedCalisDevice = scannedDevices.find { dev ->
                        val macMatches = !registeredMac.isNullOrBlank() && dev.address.equals(registeredMac, ignoreCase = true)
                        val nameMatches = (targetClsName.isNotBlank() && (dev.name.equals(targetClsName, ignoreCase = true) || dev.name.contains(targetClsName, ignoreCase = true))) ||
                                dev.name.contains("Calis", ignoreCase = true) ||
                                dev.name.contains("Classroom", ignoreCase = true) ||
                                dev.name.contains("Setup", ignoreCase = true) ||
                                dev.name.contains("CD", ignoreCase = true)
                        val serviceMatches = dev.hasServiceUuid
                        val stateMatches = dev.state != DeviceState.UNKNOWN

                        macMatches || nameMatches || serviceMatches || stateMatches
                    }

                    // Track when the device was last found to provide a UI grace period
                    var lastFoundTime by remember { mutableLongStateOf(0L) }
                    
                    // Consider device ready/active if scanned within the last 20 seconds (20000ms)
                    val isRecentlyActive = matchedCalisDevice != null && (now - matchedCalisDevice.lastSeenTimestamp <= 20000L)
                    
                    if (isRecentlyActive) {
                        lastFoundTime = now
                    }

                    // Provide a 15-second grace period for the "Connect" button state to give user plenty of time to connect
                    val isCalisDeviceReady = isRecentlyActive || (lastFoundTime > 0L && now - lastFoundTime <= 15000L) || !registeredMac.isNullOrBlank()

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
                                text = activeCls.classroomName,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = if (activeCls.isBound) MaterialTheme.colorScheme.primary else Color(0xFF475569),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            val formattedTeacherName = remember(activeCls.teacherName) {
                                com.example.util.BlePermissionUtils.formatTeacherNameWithMiddleInitial(activeCls.teacherName)
                            }
                            Text(
                                text = "Teacher: $formattedTeacherName",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (activeCls.isBound) MaterialTheme.colorScheme.onSurfaceVariant else Color(0xFF64748B),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        val isSessionActive = activeCls.isActiveSession && (
                                    connectionState is TeacherBleManager.ConnectionState.Connected || isRecentlyActive
                                )
                        val indicatorText = if (isSessionActive) "Active" else "Inactive"
                        val indicatorColor = if (!activeCls.isBound) Color(0xFF475569) else if (isSessionActive) StatusPresentGreen else Color(0xFF64748B)
                        val indicatorBg = if (!activeCls.isBound) Color(0xFFE2E8F0) else if (isSessionActive) StatusPresentGreen.copy(alpha = 0.15f) else Color(0xFF64748B).copy(alpha = 0.15f)
                        val indicatorBorder = if (!activeCls.isBound) BorderStroke(1.dp, Color(0xFF94A3B8)) else if (isSessionActive) BorderStroke(1.dp, StatusPresentGreen.copy(alpha = 0.4f)) else BorderStroke(1.dp, Color(0xFF64748B).copy(alpha = 0.3f))

                        Surface(
                            shape = RoundedCornerShape(50),
                            color = indicatorBg,
                            border = indicatorBorder,
                            modifier = Modifier.testTag("teacher_session_indicator")
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
                    Divider(color = if (activeCls.isBound) MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f) else Color(0xFFCBD5E1))
                    ClassroomInformationOverviewTile(
                        activeCls = activeCls,
                        approvedStudents = approvedStudents,
                        attendanceLogs = attendanceLogs,
                        todayDateStr = todayDateStr,
                        onHistoryClick = { onShowHistorySubPageChanged(true) }
                    )

                    if (activeCls.isBound) {
                        Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                        val isDisconnecting by teacherViewModel.isDisconnecting.collectAsState()

                        val isControlsEnabled by teacherViewModel.isControlsEnabled.collectAsState()
                        val controlsEnabled = isBtEnabled && isControlsEnabled

                        val isDeviceActive = isBtEnabled && (
                            (connectionState is TeacherBleManager.ConnectionState.Connected) ||
                            (connectionState is TeacherBleManager.ConnectionState.Connecting) ||
                            isDisconnecting ||
                            (isUserDisconnected && isRecentlyActive) ||
                            isCalisDeviceReady ||
                            isRecentlyActive
                        ) && !(isUserDisconnected && !isRecentlyActive)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Calis Device",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f, fill = false).padding(end = 8.dp),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Wi-Fi 3 wave symbols
                            WifiWavesIcon(
                                color = if (activeCls.isActiveSession && connectionState is TeacherBleManager.ConnectionState.Connected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            // 1. ESP32 CPU Icon (Centered between waves symbol and dotted line)
                            Esp32MicrochipIcon(
                                tint = if (isDeviceActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(56.dp).graphicsLayer(scaleX = -1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            
                            AnimatedDottedLine(
                                isActive = connectionState is TeacherBleManager.ConnectionState.Connected || connectionState is TeacherBleManager.ConnectionState.Connecting,
                                activeColor = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.weight(1f).height(24.dp)
                            )
                            
                            Spacer(modifier = Modifier.width(8.dp))

                            // 2. Connect / Disconnect Button (Right)
                            if (!isBtEnabled) {
                                Button(
                                    onClick = enableBluetoothAction,
                                    enabled = !isBtEnabling,
                                    shape = RoundedCornerShape(12.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                                    ),
                                    modifier = Modifier.width(130.dp).height(40.dp).testTag("enable_bluetooth_start_session_button")
                                ) {
                                    if (isBtEnabling) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.size(14.dp),
                                                color = MaterialTheme.colorScheme.onPrimary,
                                                strokeWidth = 2.dp
                                            )
                                            Text("Enabling...", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                        }
                                    } else {
                                        Text("Enable Bluetooth", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            } else if (isDisconnecting ||
                                       connectionState is TeacherBleManager.ConnectionState.Connected ||
                                       connectionState is TeacherBleManager.ConnectionState.Connecting ||
                                       connectionState is TeacherBleManager.ConnectionState.Disconnecting) {
                                OutlinedButton(
                                    onClick = onDisconnectDevice,
                                    enabled = !isDisconnecting && connectionState is TeacherBleManager.ConnectionState.Connected,
                                    shape = RoundedCornerShape(12.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    modifier = Modifier.width(130.dp).height(40.dp)
                                ) {
                                    val buttonText = when {
                                        isDisconnecting || connectionState is TeacherBleManager.ConnectionState.Disconnecting -> "Disconnecting..."
                                        connectionState is TeacherBleManager.ConnectionState.Connecting -> "Connecting..."
                                        else -> "Disconnect"
                                    }
                                    Text(buttonText, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                }
                            } else {
                                Button(
                                    onClick = {
                                        val targetAddr = matchedCalisDevice?.address ?: registeredMac ?: savedMac ?: tempMac ?: ""
                                        if (targetAddr.isNotBlank()) {
                                            onConnectDevice(targetAddr)
                                        } else {
                                            teacherViewModel.startBleScan(forceRestart = true)
                                        }
                                    },
                                    enabled = isBtEnabled && (isCalisDeviceReady || !registeredMac.isNullOrBlank()),
                                    shape = RoundedCornerShape(12.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = if (isCalisDeviceReady) {
                                        ButtonDefaults.buttonColors()
                                    } else {
                                        ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
                                            contentColor = MaterialTheme.colorScheme.onPrimary
                                        )
                                    },
                                    modifier = Modifier.width(130.dp).height(40.dp).testTag("connect_calis_device_button")
                                ) {
                                    if (isCalisDeviceReady) {
                                        Text(
                                            text = "Connect",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    } else {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.size(14.dp),
                                                color = MaterialTheme.colorScheme.onPrimary,
                                                strokeWidth = 2.dp
                                            )
                                            Text(
                                                text = "Scanning...",
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                        val isSessionActive = activeCls.isActiveSession

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            OutlinedButton(
                                onClick = { showUnregConfirmDialog = true },
                                enabled = controlsEnabled && !isSessionActive,
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = StatusAbsentRed),
                                modifier = Modifier
                                    .weight(1f)
                                    .defaultMinSize(minHeight = 48.dp)
                                    .testTag("unregister_device_button"),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                            ) {
                                Text("Unregister", fontSize = 15.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            }
                            if (activeCls.isActiveSession) {
                                OutlinedButton(
                                    onClick = onEndSession,
                                    enabled = controlsEnabled,
                                    modifier = Modifier
                                        .weight(1.2f)
                                        .defaultMinSize(minHeight = 48.dp)
                                        .testTag("end_session_button"),
                                    shape = RoundedCornerShape(12.dp),
                                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                                ) {
                                    Icon(Icons.Default.Stop, contentDescription = null, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Stop Session", fontSize = 16.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                }
                            } else {
                                Button(
                                    onClick = onStartSession,
                                    enabled = controlsEnabled,
                                    colors = ButtonDefaults.buttonColors(containerColor = StatusPresentGreen),
                                    modifier = Modifier
                                        .weight(1.2f)
                                        .defaultMinSize(minHeight = 48.dp)
                                        .testTag("start_session_button"),
                                    shape = RoundedCornerShape(12.dp),
                                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                                ) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Start Session", fontSize = 16.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                }
                            }
                        }
                    } else {
                        Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
                        Text(
                            text = "To start tracking attendance sessions, register a Calis Device Receiver in the Connection Requirements tile below.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else if (isClassroomLoaded) {
                    Text(
                        text = "No Active Classroom",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Please set up or select a classroom.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 2.5.dp
                        )
                    }
                }
            }
        }
    }

    if (showHistorySubPage) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .clickable(
                    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                    indication = null,
                    onClick = {}
                )
        ) {
            TeacherAttendanceHistorySubPage(
                onBack = { onShowHistorySubPageChanged(false) },
                attendanceLogs = attendanceLogs,
                approvedStudents = approvedStudents,
                isActiveSession = activeCls?.isActiveSession == true,
                todayDateStr = todayDateStr,
                onUpdateStatus = teacherViewModel::updateAttendanceStatus,
                onExportTasksClick = {
                    onShowHistorySubPageChanged(false)
                    onNavigateToExport()
                },
                onStudentClick = onStudentClick,
                selectedMonthKey = selectedMonthKey,
                onSelectedMonthKeyChanged = onSelectedMonthKeyChanged,
                selectedDate = selectedDate,
                onSelectedDateChanged = onSelectedDateChanged
            )
        }
    }
    }
}

@Composable
fun PendingApprovalsView(
    pendingList: List<PendingRegistrationEntity>,
    onApprove: (PendingRegistrationEntity) -> Unit,
    onReject: (PendingRegistrationEntity) -> Unit
) {
    var selectedStudent by remember { mutableStateOf<PendingRegistrationEntity?>(null) }
    val dateFormatter = remember { java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US) }

    val distinctPendingList = remember(pendingList) { pendingList.distinctBy { it.studentId } }

    if (distinctPendingList.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Group,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text("No pending student registrations", style = MaterialTheme.typography.titleMedium)
                Text("Student requests sent via BLE will appear here for approval.", style = MaterialTheme.typography.bodySmall)
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(distinctPendingList, key = { it.studentId }) { pending ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { selectedStudent = pending }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(pending.studentName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("SID: ${pending.studentId}", style = MaterialTheme.typography.bodySmall)
                            Text("Route ID: RID ${pending.rid}", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            IconButton(
                                onClick = { onApprove(pending) },
                                modifier = Modifier.testTag("approve_student_button_${pending.studentId}")
                            ) {
                                Icon(Icons.Default.Check, contentDescription = "Approve", tint = StatusPresentGreen)
                            }
                            IconButton(
                                onClick = { onReject(pending) },
                                modifier = Modifier.testTag("reject_student_button_${pending.studentId}")
                            ) {
                                Icon(Icons.Default.Close, contentDescription = "Reject", tint = StatusAbsentRed)
                            }
                        }
                    }
                }
            }
        }
    }

    if (selectedStudent != null) {
        val student = selectedStudent!!
        val birthStr = if (student.birth > 0) dateFormatter.format(java.util.Date(student.birth * 1000L)) else "N/A"
        AlertDialog(
            onDismissRequest = { selectedStudent = null },
            tonalElevation = 0.dp,
            properties = DialogProperties(usePlatformDefaultWidth = false),
            modifier = Modifier.fillMaxWidth(0.92f),
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            textContentColor = MaterialTheme.colorScheme.onSurface,
            title = { Text("Student Profile") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Name: ${student.studentName}", fontWeight = FontWeight.Bold)
                    Text("Student ID: ${student.studentId}")
                    Text("Sex: ${student.sex}")
                    Text("LRN: ${student.lrn}")
                    Text("Birth Date: $birthStr")
                    Text("Connection Route: RID ${student.rid}", color = MaterialTheme.colorScheme.primary)
                }
            },
            confirmButton = {
                Button(onClick = { 
                    onApprove(student)
                    selectedStudent = null
                }) {
                    Text("Approve")
                }
            },
            dismissButton = {
                TextButton(onClick = { 
                    onReject(student)
                    selectedStudent = null
                }) {
                    Text("Reject", color = StatusAbsentRed)
                }
            }
        )
    }
}

@Composable
fun ApprovedMasterlistView(
    approvedList: List<ApprovedStudentEntity>
) {
    if (approvedList.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No approved students in masterlist yet.")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(approvedList) { student ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(36.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = student.studentName,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE)
                            )
                            Text("SID: ${student.studentId} | LRN: ${student.lrn}", style = MaterialTheme.typography.bodySmall, maxLines = 1)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AttendanceManagementView(
    attendanceLogs: List<AttendanceEntity>,
    onUpdateStatus: (String, String, String) -> Unit
) {
    if (attendanceLogs.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No attendance logs recorded yet.")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(attendanceLogs) { log ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                                Text(
                                    text = log.studentName,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE)
                                )
                                val displayDate = java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date(log.timestamp * 1000L))
                                val timeStr = java.text.SimpleDateFormat("hh:mm a", java.util.Locale.US).format(java.util.Date(log.timestamp * 1000L))
                                Text("SID: ${log.studentId} | $displayDate | $timeStr", style = MaterialTheme.typography.bodySmall, maxLines = 1)
                            }
                            Surface(
                                modifier = Modifier.width(80.dp),
                                shape = RoundedCornerShape(6.dp),
                                color = when (log.status) {
                                    "PRESENT" -> StatusPresentGreen.copy(alpha = 0.2f)
                                    "LATE" -> StatusLateAmber.copy(alpha = 0.2f)
                                    else -> StatusAbsentRed.copy(alpha = 0.2f)
                                }
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = log.status,
                                        color = when (log.status) {
                                            "PRESENT" -> StatusPresentGreen
                                            "LATE" -> StatusLateAmber
                                            else -> StatusAbsentRed
                                        },
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.labelSmall,
                                        maxLines = 1
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Edit Status: ", style = MaterialTheme.typography.labelSmall)
                            Spacer(modifier = Modifier.width(4.dp))
                            OutlinedButton(
                                onClick = { onUpdateStatus(log.studentId, log.date, "PRESENT") },
                                modifier = Modifier.padding(end = 4.dp)
                            ) {
                                Text("Present", fontSize = 10.sp)
                            }
                            OutlinedButton(
                                onClick = { onUpdateStatus(log.studentId, log.date, "LATE") },
                                modifier = Modifier.padding(end = 4.dp)
                            ) {
                                Text("Late", fontSize = 10.sp)
                            }
                            OutlinedButton(
                                onClick = { onUpdateStatus(log.studentId, log.date, "ABSENT") }
                            ) {
                                Text("Absent", fontSize = 10.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TasksManagementView(
    tasks: List<TaskEntity>,
    selectedTask: TaskEntity?,
    submissions: List<TaskSubmissionEntity>,
    deliveries: List<com.example.data.db.TaskDeliveryEntity>,
    approvedStudents: List<ApprovedStudentEntity>,
    unviewedTaskIds: Set<String> = emptySet(),
    unviewedTaskCounts: Map<String, Int> = emptyMap(),
    tasksListState: LazyListState = rememberSaveable(saver = LazyListState.Saver) { LazyListState() },
    onSelectTask: (TaskEntity?) -> Unit,
    onDeleteTask: (String) -> Unit,
    isSubmissionsLoading: Boolean = false,
    onStudentClick: (ApprovedStudentEntity) -> Unit = {},
    onCreateTaskClick: () -> Unit = {}
) {
    if (selectedTask != null) {
        var taskGenderFirstOption by remember { mutableStateOf(GenderFirstOption.BOYS_FIRST) }
        val isBoySex: (String) -> Boolean = { sex ->
            sex.equals("M", ignoreCase = true) || sex.contains("Boy", ignoreCase = true) || sex.contains("Male", ignoreCase = true)
        }
        val isGirlSex: (String) -> Boolean = { sex ->
            sex.equals("F", ignoreCase = true) || sex.contains("Girl", ignoreCase = true) || sex.contains("Female", ignoreCase = true)
        }

        val sortedStudents = remember(approvedStudents, taskGenderFirstOption) {
            if (taskGenderFirstOption == GenderFirstOption.BOYS_FIRST) {
                approvedStudents.sortedWith(
                    compareByDescending<ApprovedStudentEntity> { isBoySex(it.sex) }
                        .thenBy { it.studentName }
                )
            } else {
                approvedStudents.sortedWith(
                    compareByDescending<ApprovedStudentEntity> { isGirlSex(it.sex) }
                        .thenBy { it.studentName }
                )
            }
        }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Surface(
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            ) {
                Text(
                    text = "TKID: ${selectedTask.tkid}",
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
                        text = selectedTask.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 24.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Students Status",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                // Compact Gender Sorter (Male/Female)
                val isBoys = taskGenderFirstOption == GenderFirstOption.BOYS_FIRST
                val badgeBgColor = if (isBoys) Color(0xFFE3F2FD) else Color(0xFFFCE4EC)
                val badgeContentColor = if (isBoys) Color(0xFF0D47A1) else Color(0xFFC2185B)
                val badgeLabel = if (isBoys) "Male" else "Female"

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = badgeBgColor,
                    modifier = Modifier
                        .clickable {
                            taskGenderFirstOption = if (isBoys) GenderFirstOption.GIRLS_FIRST else GenderFirstOption.BOYS_FIRST
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = badgeContentColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = badgeLabel,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = badgeContentColor
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            if (isSubmissionsLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(36.dp),
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 3.dp
                    )
                }
            } else if (approvedStudents.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No students in this classroom's masterlist yet.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(sortedStudents) { student ->
                        val hasReceived = deliveries.any { it.studentId == student.studentId && it.received }
                        val sub = submissions.find { it.studentId == student.studentId }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("student_task_status_${student.studentId}")
                                .clickable { onStudentClick(student) },
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val isFemale = student.sex.equals("F", ignoreCase = true) || student.sex.contains("Girl", ignoreCase = true) || student.sex.contains("Female", ignoreCase = true)
                                val avatarBgColor = if (isFemale) Color(0xFFFCE4EC) else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                                val avatarIconColor = if (isFemale) Color(0xFFC2185B) else MaterialTheme.colorScheme.primary

                                Surface(
                                    shape = CircleShape,
                                    color = avatarBgColor,
                                    modifier = Modifier.size(30.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = null,
                                            tint = avatarIconColor,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(10.dp))

                                Column(modifier = Modifier.weight(1f).padding(end = 6.dp)) {
                                    Text(
                                        text = student.studentName,
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp),
                                        maxLines = 1,
                                        modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE)
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    
                                    // Received status badge
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Surface(
                                            shape = RoundedCornerShape(4.dp),
                                            color = if (hasReceived) StatusPresentGreen.copy(alpha = 0.15f) else MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.15f)
                                        ) {
                                            Text(
                                                text = if (hasReceived) "Received" else "Not Received",
                                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp),
                                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                                fontWeight = FontWeight.Bold,
                                                color = if (hasReceived) StatusPresentGreen else MaterialTheme.colorScheme.error
                                            )
                                        }
                                    }
                                }
                                
                                // Score/Status Indicator copied from Max Score UI design (thinner)
                                if (selectedTask.maxScore > 0) {
                                    Card(
                                        shape = RoundedCornerShape(10.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = if (sub != null) Color(0xFFE3F2FD) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                                        )
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "Score",
                                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                                                fontWeight = FontWeight.Bold,
                                                color = if (sub != null) Color(0xFF1976D2) else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                                            )
                                            Text(
                                                text = if (sub != null) "${sub.score}" else "-",
                                                style = MaterialTheme.typography.titleSmall.copy(fontSize = 13.sp),
                                                fontWeight = FontWeight.ExtraBold,
                                                color = if (sub != null) Color(0xFF0D47A1) else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                                            )
                                        }
                                    }
                                } else {
                                    Card(
                                        shape = RoundedCornerShape(10.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = if (sub != null) Color(0xFFE8F5E9) else Color(0xFFFFF3E0)
                                        )
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "Status",
                                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                                                fontWeight = FontWeight.Bold,
                                                color = if (sub != null) Color(0xFF2E7D32) else Color(0xFFE65100)
                                            )
                                            Text(
                                                text = if (sub != null) "Complete" else "Pending",
                                                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                                fontWeight = FontWeight.ExtraBold,
                                                color = if (sub != null) Color(0xFF1B5E20) else Color(0xFFE65100)
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
    } else {
        if (tasks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Assignment,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                        modifier = Modifier.size(72.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No tasks created yet",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = onCreateTaskClick,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(48.dp)
                            .testTag("create_first_task_button"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Create a Task", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
        } else {
            val datePostedFormatter = remember {
                java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US)
            }
            val deadlineFormatter = remember {
                java.text.SimpleDateFormat("MM/dd/yyyy hh:mm a", java.util.Locale.US)
            }
            LazyColumn(
                state = tasksListState,
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tasks) { task ->
                    val datePostedStr = try {
                        val ms = if (task.datePosted > 100_000_000_000L) task.datePosted else task.datePosted * 1000L
                        datePostedFormatter.format(java.util.Date(ms))
                    } catch (e: Exception) { "--" }
                    val deadlineStr = try {
                        val ms = if (task.deadline > 100_000_000_000L) task.deadline else task.deadline * 1000L
                        deadlineFormatter.format(java.util.Date(ms))
                    } catch (e: Exception) { "--" }

                    Card(
                        onClick = { onSelectTask(task) },
                        modifier = Modifier.fillMaxWidth().testTag("teacher_task_card_${task.tkid}"),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f).padding(end = 16.dp)) {
                                        Text(
                                            text = task.title,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Due: $deadlineStr",
                                            style = MaterialTheme.typography.bodySmall,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.error
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "Posted: $datePostedStr",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        if (task.maxScore > 0) {
                                            Card(
                                                shape = RoundedCornerShape(12.dp),
                                                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                                            ) {
                                                Column(
                                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ) {
                                                    Text("Max Score", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))
                                                    Text(
                                                        text = "${task.maxScore}",
                                                        style = MaterialTheme.typography.titleMedium,
                                                        fontWeight = FontWeight.ExtraBold,
                                                        color = Color(0xFF0D47A1)
                                                    )
                                                }
                                            }
                                        }
                                        IconButton(
                                            onClick = { onSelectTask(task) },
                                            modifier = Modifier.size(32.dp)
                                        ) {
                                            Icon(
                                                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                                contentDescription = "View Submissions"
                                            )
                                        }
                                    }
                                }
                            }

                            val unviewedCount = unviewedTaskCounts[task.tkid] ?: 0
                            if (unviewedCount > 0) {
                                Badge(
                                    containerColor = MaterialTheme.colorScheme.error,
                                    contentColor = MaterialTheme.colorScheme.onError,
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(top = 12.dp, end = 12.dp)
                                ) {
                                    Text(
                                        text = "$unviewedCount",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

@Composable
fun ExportCenterView(
    teacherViewModel: TeacherViewModel,
    classroom: ClassroomEntity?,
    tasks: List<TaskEntity>,
    initialTab: Int = 0,
    onExportReady: (csvContent: String, title: String) -> Unit
) {
    val context = LocalContext.current
    val exportPrefs = remember(context) { context.getSharedPreferences("calis_export_center_prefs", Context.MODE_PRIVATE) }

    val attendanceLogs by teacherViewModel.attendanceLogs.collectAsState()
    val approvedStudents by teacherViewModel.approvedStudents.collectAsState()

    val savedTab = remember { exportPrefs.getInt("export_selected_tab", initialTab) }
    var selectedTab by remember(savedTab) { mutableStateOf(savedTab) }
    LaunchedEffect(selectedTab) {
        exportPrefs.edit().putInt("export_selected_tab", selectedTab).apply()
    }

    var isAttendanceLoading by remember { mutableStateOf(true) }
    var isTasksLoading by remember { mutableStateOf(true) }

    LaunchedEffect(attendanceLogs) {
        if (attendanceLogs.isNotEmpty()) {
            kotlinx.coroutines.delay(400)
            isAttendanceLoading = false
        } else {
            kotlinx.coroutines.delay(850)
            isAttendanceLoading = false
        }
    }

    LaunchedEffect(tasks) {
        if (tasks.isNotEmpty()) {
            kotlinx.coroutines.delay(400)
            isTasksLoading = false
        } else {
            kotlinx.coroutines.delay(850)
            isTasksLoading = false
        }
    }

    // --- ATTENDANCE EXPORT STATE ---
    val availableMonths = remember(attendanceLogs) {
        val months = attendanceLogs.map { CsvExportUtils.getMonthKey(it) }
            .filter { it != "Unknown" }
            .distinct()
            .sortedDescending()
        if (months.isEmpty()) {
            val currentMonth = SimpleDateFormat("yyyy-MM", Locale.US).format(Date())
            listOf(currentMonth)
        } else {
            months
        }
    }

    val savedSelectedMonths = remember { exportPrefs.getStringSet("export_selected_months", null) }
    var selectedMonths by remember(availableMonths, savedSelectedMonths) {
        mutableStateOf(
            if (savedSelectedMonths != null) {
                val intersection = savedSelectedMonths.intersect(availableMonths.toSet())
                if (intersection.isNotEmpty()) intersection else availableMonths.toSet()
            } else {
                availableMonths.toSet()
            }
        )
    }

    val defaultColOrder = CsvExportUtils.AttendanceColumnKey.DEFAULT_ORDER
    val savedColOrderStr = remember { exportPrefs.getString("export_att_column_order", null) }
    var attendanceColumnOrder by remember(savedColOrderStr) {
        mutableStateOf(
            if (!savedColOrderStr.isNullOrBlank()) {
                val parsed = savedColOrderStr.split(",").mapNotNull { name ->
                    try { CsvExportUtils.AttendanceColumnKey.valueOf(name) } catch (e: Exception) { null }
                }
                if (parsed.isNotEmpty()) parsed else defaultColOrder
            } else {
                defaultColOrder
            }
        )
    }

    val savedIncludedColsStr = remember { exportPrefs.getString("export_att_included_columns", null) }
    var includedAttendanceColumns by remember(savedIncludedColsStr) {
        mutableStateOf(
            if (!savedIncludedColsStr.isNullOrBlank()) {
                val parsed = savedIncludedColsStr.split(",").mapNotNull { name ->
                    try { CsvExportUtils.AttendanceColumnKey.valueOf(name) } catch (e: Exception) { null }
                }.toSet()
                if (parsed.isNotEmpty()) parsed else defaultColOrder.toSet()
            } else {
                defaultColOrder.toSet()
            }
        )
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    var presentLabel by remember {
        mutableStateOf(exportPrefs.getString("export_present_label", AttendanceLabels.present) ?: "Present")
    }
    var absentLabel by remember {
        mutableStateOf(exportPrefs.getString("export_absent_label", AttendanceLabels.absent) ?: "Absent")
    }
    var lateLabel by remember {
        mutableStateOf(exportPrefs.getString("export_late_label", AttendanceLabels.late) ?: "Late")
    }

    LaunchedEffect(presentLabel, absentLabel, lateLabel) {
        AttendanceLabels.present = presentLabel
        AttendanceLabels.absent = absentLabel
        AttendanceLabels.late = lateLabel
    }

    // --- PERSIST ATTENDANCE CONFIGURATION AUTOMATICALLY ---
    LaunchedEffect(
        selectedMonths,
        attendanceColumnOrder,
        includedAttendanceColumns,
        presentLabel,
        absentLabel,
        lateLabel
    ) {
        exportPrefs.edit()
            .putStringSet("export_selected_months", selectedMonths)
            .putString("export_att_column_order", attendanceColumnOrder.joinToString(",") { it.name })
            .putString("export_att_included_columns", includedAttendanceColumns.joinToString(",") { it.name })
            .putString("export_present_label", presentLabel)
            .putString("export_absent_label", absentLabel)
            .putString("export_late_label", lateLabel)
            .apply()
    }

    // --- TASKS EXPORT STATE ---
    val savedTaskIdsStrSet = remember { exportPrefs.getStringSet("export_selected_task_ids", null) }
    var selectedTaskIds by remember(tasks, savedTaskIdsStrSet) {
        mutableStateOf(
            if (savedTaskIdsStrSet != null) {
                val savedIds = savedTaskIdsStrSet.mapNotNull { it.toLongOrNull() }.toSet()
                val validIds = tasks.map { it.tkid }.toSet()
                val validSaved = savedIds.intersect(validIds)
                if (validSaved.isNotEmpty()) validSaved else validIds
            } else {
                tasks.map { it.tkid }.toSet()
            }
        )
    }

    // --- PERSIST TASKS CONFIGURATION AUTOMATICALLY ---
    LaunchedEffect(selectedTaskIds) {
        exportPrefs.edit()
            .putStringSet("export_selected_task_ids", selectedTaskIds.map { it.toString() }.toSet())
            .apply()
    }

    val totalBoys = remember(approvedStudents) { approvedStudents.count { CsvExportUtils.isBoySex(it.sex) } }
    val totalGirls = remember(approvedStudents) { approvedStudents.count { CsvExportUtils.isGirlSex(it.sex) } }

    val scrollState = rememberScrollState()
    var isDraggingActive by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState, enabled = !isDraggingActive)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Card matching app's theme
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Modern Segmented Tab Row
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (selectedTab == 0) MaterialTheme.colorScheme.primary else Color.Transparent,
                            modifier = Modifier
                                .weight(1f)
                                .clickable { selectedTab = 0 }
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 10.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CalendarToday,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = if (selectedTab == 0) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Attendance Log",
                                    fontSize = 13.sp,
                                    fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Medium,
                                    color = if (selectedTab == 0) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (selectedTab == 1) MaterialTheme.colorScheme.primary else Color.Transparent,
                            modifier = Modifier
                                .weight(1f)
                                .clickable { selectedTab = 1 }
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 10.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Assignment,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = if (selectedTab == 1) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Task Records",
                                    fontSize = 13.sp,
                                    fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Medium,
                                    color = if (selectedTab == 1) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }

        // --- TAB 0: ATTENDANCE EXPORT ---
        if (selectedTab == 0) {
            // Card 1: Target Month(s) Selection
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Select Month/s",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row {
                            TextButton(
                                onClick = { selectedMonths = availableMonths.toSet() },
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text("Select All", fontSize = 12.sp)
                            }
                            TextButton(
                                onClick = { selectedMonths = emptySet() },
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text("Clear", fontSize = 12.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (isAttendanceLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                strokeWidth = 2.dp
                            )
                        }
                    } else {
                        // Month Chips
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            availableMonths.forEach { monthKey ->
                                val isSelected = monthKey in selectedMonths
                                val displayMonth = CsvExportUtils.formatMonthDisplay(monthKey)
                                val count = attendanceLogs
                                    .filter { CsvExportUtils.getMonthKey(it) == monthKey }
                                    .map { CsvExportUtils.getNormalizedDate(it) }
                                    .filter { it.isNotBlank() }
                                    .distinct()
                                    .size

                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                    border = BorderStroke(
                                        1.dp,
                                        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            selectedMonths = if (isSelected) {
                                                selectedMonths - monthKey
                                            } else {
                                                selectedMonths + monthKey
                                            }
                                        }
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 14.dp, vertical = 12.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            Checkbox(
                                                checked = isSelected,
                                                onCheckedChange = { checked ->
                                                    selectedMonths = if (checked) {
                                                        selectedMonths + monthKey
                                                    } else {
                                                        selectedMonths - monthKey
                                                    }
                                                }
                                            )
                                            Text(
                                                text = displayMonth,
                                                style = MaterialTheme.typography.bodyMedium,
                                                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                        }

                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                                        ) {
                                            Text(
                                                text = "$count ${if (count == 1) "Session" else "Sessions"}",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Card 2: Spreadsheet Format & Organization Options
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.TableChart,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Matrix Layout",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Attendance Status Format", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                        OutlinedTextField(value = presentLabel, onValueChange = { presentLabel = it; AttendanceLabels.present = it }, label = { Text("Present Label") }, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done), keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }))
                        OutlinedTextField(value = absentLabel, onValueChange = { absentLabel = it; AttendanceLabels.absent = it }, label = { Text("Absent Label") }, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done), keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }))
                        OutlinedTextField(
                            value = lateLabel,
                            onValueChange = { lateLabel = it; AttendanceLabels.late = it },
                            label = { Text("Late Label") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
                        )
                    }

                    Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

                    // Column Sequence & Inclusion Customizer
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Customize Columns & Order",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Choose columns and hold 0.3s to drag stack",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        TextButton(
                            onClick = {
                                attendanceColumnOrder = CsvExportUtils.AttendanceColumnKey.DEFAULT_ORDER
                                includedAttendanceColumns = CsvExportUtils.AttendanceColumnKey.DEFAULT_ORDER.toSet()
                            }
                        ) {
                            Text("Reset", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    var draggingIndex by remember { mutableStateOf<Int?>(null) }
                    var dragOffsetY by remember { mutableFloatStateOf(0f) }
                    var itemHeightPx by remember { mutableFloatStateOf(160f) }
                    val density = LocalDensity.current
                    val currentOrderState = rememberUpdatedState(attendanceColumnOrder)

                    // Stack Items Container
                    val totalHeightDp = remember(density, attendanceColumnOrder.size, itemHeightPx) {
                        with(density) { (itemHeightPx * attendanceColumnOrder.size).toDp() }
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(totalHeightDp + 16.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        userScrollEnabled = false
                    ) {
                        itemsIndexed(
                            items = attendanceColumnOrder,
                            key = { _, colKey -> colKey.name }
                        ) { index, colKey ->
                            val isIncluded = colKey in includedAttendanceColumns
                            val isDragging = draggingIndex == index

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isDragging) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
                                shadowElevation = 0.dp,
                                border = BorderStroke(
                                    width = if (isDragging) 1.5.dp else 0.5.dp,
                                    color = if (isDragging) MaterialTheme.colorScheme.primary else if (isIncluded) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .then(if (isDragging) Modifier else Modifier.animateItem())
                                    .zIndex(if (isDragging) 10f else 1f)
                                    .graphicsLayer {
                                        translationY = if (isDragging) dragOffsetY else 0f
                                        scaleX = if (isDragging) 1.02f else 1f
                                        scaleY = if (isDragging) 1.02f else 1f
                                    }
                                    .onGloballyPositioned { coords ->
                                        if (coords.size.height > 0) {
                                            val newHeight = coords.size.height.toFloat() + with(density) { 6.dp.toPx() }
                                            if (itemHeightPx != newHeight) {
                                                itemHeightPx = newHeight
                                            }
                                        }
                                    }
                                    .pointerInput(colKey) {
                                        detectDragGesturesAfterLongPress(
                                            onDragStart = { offset ->
                                                val currentIdx = currentOrderState.value.indexOf(colKey)
                                                if (currentIdx != -1) {
                                                    draggingIndex = currentIdx
                                                    isDraggingActive = true
                                                    dragOffsetY = 0f
                                                }
                                            },
                                            onDragEnd = {
                                                draggingIndex = null
                                                isDraggingActive = false
                                                dragOffsetY = 0f
                                            },
                                            onDragCancel = {
                                                draggingIndex = null
                                                isDraggingActive = false
                                                dragOffsetY = 0f
                                            },
                                            onDrag = { change, dragAmount ->
                                                change.consume()
                                                dragOffsetY += dragAmount.y

                                                val idx = currentOrderState.value.indexOf(colKey)
                                                if (idx != -1) {
                                                    val step = if (itemHeightPx > 0f) itemHeightPx else 100f
                                                    if (dragOffsetY > step * 0.5f && idx < currentOrderState.value.size - 1) {
                                                        val nextIndex = idx + 1
                                                        val mutable = currentOrderState.value.toMutableList()
                                                        val item = mutable.removeAt(idx)
                                                        mutable.add(nextIndex, item)
                                                        attendanceColumnOrder = mutable
                                                        draggingIndex = nextIndex
                                                        dragOffsetY -= step
                                                    } else if (dragOffsetY < -step * 0.5f && idx > 0) {
                                                        val prevIndex = idx - 1
                                                        val mutable = currentOrderState.value.toMutableList()
                                                        val item = mutable.removeAt(idx)
                                                        mutable.add(prevIndex, item)
                                                        attendanceColumnOrder = mutable
                                                        draggingIndex = prevIndex
                                                        dragOffsetY += step
                                                    }
                                                }
                                            }
                                        )
                                    }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Checkbox(
                                            checked = isIncluded,
                                            onCheckedChange = { checked ->
                                                val mutable = includedAttendanceColumns.toMutableSet()
                                                if (checked) {
                                                    mutable.add(colKey)
                                                } else {
                                                    mutable.remove(colKey)
                                                }
                                                includedAttendanceColumns = mutable
                                            }
                                        )
                                        Text(
                                            text = colKey.displayName,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.SemiBold,
                                            color = if (isIncluded) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                        )
                                    }

                                    if (isIncluded) {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Surface(
                                                shape = CircleShape,
                                                color = if (isDragging) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
                                                modifier = Modifier.size(24.dp)
                                            ) {
                                                Box(contentAlignment = Alignment.Center) {
                                                    Text(
                                                        text = "${index + 1}",
                                                        fontSize = 11.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = if (isDragging) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer
                                                    )
                                                }
                                            }
                                            Icon(
                                                imageVector = Icons.Default.DragHandle,
                                                contentDescription = "Hold & Drag",
                                                tint = if (isDragging) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                                modifier = Modifier.size(22.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }

            // Export Attendance Button
            Button(
                onClick = {
                    val config = CsvExportUtils.AttendanceExportConfig(
                        selectedMonths = selectedMonths,
                        isMatrixLayout = true,
                        presentLabel = presentLabel,
                        absentLabel = absentLabel,
                        lateLabel = lateLabel,
                        columnOrder = attendanceColumnOrder.filter { it in includedAttendanceColumns }
                    )
                    teacherViewModel.exportCustomAttendanceCsv(config) { csv ->
                        val monthLabel = if (selectedMonths.isEmpty()) "All Months" else selectedMonths.sorted().joinToString("-")
                        onExportReady(csv, "Attendance Export ($monthLabel)")
                    }
                },
                enabled = selectedMonths.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("export_attendance_button"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Download, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (selectedMonths.isEmpty()) "Select at least 1 month to export"
                           else "Export Attendance CSV (${selectedMonths.size} ${if (selectedMonths.size == 1) "Month" else "Months"})",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // --- TAB 1: TASKS & GRADEBOOK EXPORT ---
        if (selectedTab == 1) {
            // Card 1: Multi-Task Selection
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Assignment,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Select Task/s",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        if (tasks.isNotEmpty() && !isTasksLoading) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(
                                    onClick = { selectedTaskIds = tasks.map { it.tkid }.toSet() },
                                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text("Select All", fontSize = 12.sp)
                                }
                                TextButton(
                                    onClick = { selectedTaskIds = emptySet() },
                                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text("Clear", fontSize = 12.sp)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (isTasksLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                strokeWidth = 2.dp
                            )
                        }
                    } else {
                        if (tasks.isEmpty()) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(24.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Assignment,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.outline,
                                        modifier = Modifier.size(36.dp)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "No tasks available to export",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = "Create tasks in the Classroom tab first.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                }
                            }
                        } else {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                tasks.forEach { task ->
                                    val isSelected = task.tkid in selectedTaskIds
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                        border = BorderStroke(
                                            1.dp,
                                            if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                selectedTaskIds = if (isSelected) {
                                                    selectedTaskIds - task.tkid
                                                } else {
                                                    selectedTaskIds + task.tkid
                                                }
                                            }
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 12.dp, vertical = 10.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                modifier = Modifier.weight(1f)
                                            ) {
                                                Checkbox(
                                                    checked = isSelected,
                                                    onCheckedChange = { checked ->
                                                        selectedTaskIds = if (checked) {
                                                            selectedTaskIds + task.tkid
                                                        } else {
                                                            selectedTaskIds - task.tkid
                                                        }
                                                    }
                                                )
                                                Column {
                                                    Text(
                                                        text = task.title,
                                                        style = MaterialTheme.typography.bodyMedium,
                                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                                                        color = MaterialTheme.colorScheme.onSurface
                                                    )
                                                }
                                            }

                                            Surface(
                                                shape = RoundedCornerShape(8.dp),
                                                color = MaterialTheme.colorScheme.secondaryContainer
                                            ) {
                                                Text(
                                                    text = "${task.maxScore} pts",
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
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

            // Export Tasks Button
            val selectedTasksList = remember(tasks, selectedTaskIds) {
                tasks.filter { it.tkid in selectedTaskIds }
            }
            Button(
                onClick = {
                    val config = CsvExportUtils.TasksExportConfig()
                    teacherViewModel.exportCustomTasksCsv(selectedTasksList, config) { csv ->
                        onExportReady(csv, "Tasks Gradebook Matrix (${selectedTasksList.size} Tasks)")
                    }
                },
                enabled = selectedTasksList.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("export_tasks_button"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Download, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (selectedTasksList.isEmpty()) "Select at least 1 task to export"
                           else "Export Gradebook CSV (${selectedTasksList.size} ${if (selectedTasksList.size == 1) "Task Column" else "Task Columns"})",
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        Spacer(modifier = Modifier.height(48.dp))
    }
}

// --- DIALOG COMPOSABLES ---

@Composable
fun CreateClassroomDialog(
    onDismiss: () -> Unit,
    onCreate: (cls: String, days: String, start: String, grace: String, end: String) -> Unit
) {
    var clsName by remember { mutableStateOf("10 - Newton") }
    var days by remember { mutableStateOf("MON-FRI") }
    var start by remember { mutableStateOf("0800") }
    var grace by remember { mutableStateOf("0815") }
    var end by remember { mutableStateOf("1700") }

    AlertDialog(
        onDismissRequest = onDismiss,
        tonalElevation = 0.dp,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.fillMaxWidth(0.92f),
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurface,
        title = { Text("Register Calis Device Classroom") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth().heightIn(max = 450.dp).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = clsName,
                    onValueChange = { clsName = it.take(16) },
                    label = { Text("Classroom / Section (CLS)") },
                    supportingText = { Text("${clsName.length}/16") },
                    colors = calisTextFieldColors(),
                    modifier = Modifier.fillMaxWidth().testTag("cls_name_input")
                )
                OutlinedTextField(
                    value = days,
                    onValueChange = { days = it },
                    label = { Text("Days (DAYS e.g. MON-FRI)") },
                    colors = calisTextFieldColors(),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = start,
                    onValueChange = { start = it },
                    label = { Text("Start Time (START HHMM)") },
                    colors = calisTextFieldColors(),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = grace,
                    onValueChange = { grace = it },
                    label = { Text("Grace Period (GRACE HHMM)") },
                    colors = calisTextFieldColors(),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = end,
                    onValueChange = { end = it },
                    label = { Text("Dismissal Time (END HHMM)") },
                    colors = calisTextFieldColors(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onCreate(clsName, days, start, grace, end) },
                modifier = Modifier.testTag("confirm_create_classroom")
            ) {
                Text("Register (REG)")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Composable
fun CreateTaskDialog(
    onDismiss: () -> Unit,
    onCreate: (title: String, desc: String, deadline: Long, maxScore: Int) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var enableScores by remember { mutableStateOf(true) }
    var maxScoreStr by remember { mutableStateOf("100") }

    val descFocusRequester = remember { FocusRequester() }
    val maxScoreFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val titleLetterCount = title.length
    val descLetterCount = desc.length
    val isTitleExceeded = titleLetterCount > 30
    val isDescExceeded = descLetterCount > 100

    val currentCal = remember {
        java.util.Calendar.getInstance().apply {
            add(java.util.Calendar.DAY_OF_YEAR, 1)
        }
    }
    var selectedYear by remember { mutableIntStateOf(currentCal.get(java.util.Calendar.YEAR)) }
    var selectedMonth by remember { mutableIntStateOf(currentCal.get(java.util.Calendar.MONTH)) }
    var selectedDay by remember { mutableIntStateOf(currentCal.get(java.util.Calendar.DAY_OF_MONTH)) }

    var selectedHour by remember { mutableIntStateOf(23) }
    var selectedMinute by remember { mutableIntStateOf(59) }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    val formattedDateStr = remember(selectedYear, selectedMonth, selectedDay) {
        String.format(java.util.Locale.US, "%02d/%02d/%04d", selectedMonth + 1, selectedDay, selectedYear)
    }
    val formattedTimeStr = remember(selectedHour, selectedMinute) {
        formatTime12Hour(selectedHour, selectedMinute)
    }

    if (showDatePicker) {
        CalendarDatePickerDialog(
            initialYear = selectedYear,
            initialMonth = selectedMonth,
            initialDay = selectedDay,
            onDismiss = { showDatePicker = false },
            onConfirm = { year, month, day ->
                selectedYear = year
                selectedMonth = month
                selectedDay = day
                showDatePicker = false
            }
        )
    }

    if (showTimePicker) {
        AnalogTimePickerDialog(
            title = "Select Deadline Time",
            initialHour = selectedHour,
            initialMinute = selectedMinute,
            onDismiss = { showTimePicker = false },
            onConfirm = { h, m ->
                selectedHour = h
                selectedMinute = m
                showTimePicker = false
            }
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.fillMaxWidth(0.92f),
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = 0.dp,
        shape = RoundedCornerShape(16.dp),
        title = { Text("Create Task", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface) },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 480.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { if (it.length <= 30) title = it },
                        label = { Text("Task Title") },
                        isError = isTitleExceeded,
                        colors = calisTextFieldColors(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                descFocusRequester.requestFocus()
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("task_title_input")
                    )
                    Text(
                        text = if (isTitleExceeded) "Exceeded limit ($titleLetterCount/30 letters)" else "Letters: $titleLetterCount/30",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isTitleExceeded) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                    )
                }

                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = desc,
                        onValueChange = { newDesc ->
                            if (newDesc.endsWith("\n")) {
                                val cleanText = newDesc.replace("\n", "")
                                if (cleanText.length <= 100) {
                                    desc = cleanText
                                }
                                if (enableScores) {
                                    maxScoreFocusRequester.requestFocus()
                                } else {
                                    keyboardController?.hide()
                                }
                            } else if (newDesc.length <= 100) {
                                val wasUnderLimit = desc.length < 100
                                desc = newDesc
                                if (wasUnderLimit && newDesc.length == 100) {
                                    if (enableScores) {
                                        maxScoreFocusRequester.requestFocus()
                                    } else {
                                        keyboardController?.hide()
                                    }
                                }
                            }
                        },
                        label = { Text("Task Description") },
                        isError = isDescExceeded,
                        colors = calisTextFieldColors(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = if (enableScores) ImeAction.Next else ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (enableScores) {
                                    maxScoreFocusRequester.requestFocus()
                                } else {
                                    keyboardController?.hide()
                                }
                            },
                            onDone = {
                                keyboardController?.hide()
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(descFocusRequester)
                            .testTag("task_desc_input")
                    )
                    Text(
                        text = if (isDescExceeded) "Exceeded limit ($descLetterCount/100 letters)" else "Letters: $descLetterCount/100",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isDescExceeded) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                    )
                }

                // Deadline Section
                Text(
                    text = "Deadline",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Deadline Date Selector Box
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surface,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                        modifier = Modifier
                            .weight(1f)
                            .clickable { showDatePicker = true }
                            .testTag("select_deadline_date_button")
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Date (mm/dd/yyyy)",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CalendarToday,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = formattedDateStr,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    // Deadline Time Selector Box
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surface,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)),
                        modifier = Modifier
                            .weight(1f)
                            .clickable { showTimePicker = true }
                            .testTag("select_deadline_time_button")
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Time",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.AccessTime,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = formattedTimeStr,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { enableScores = !enableScores },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Enable Scores", fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
                    Switch(
                        checked = enableScores,
                        onCheckedChange = null,
                        modifier = Modifier.testTag("enable_scores_switch")
                    )
                }
                if (enableScores) {
                    OutlinedTextField(
                        value = maxScoreStr,
                        onValueChange = { maxScoreStr = it },
                        label = { Text("Maximum Score (e.g. 100)") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hide()
                            }
                        ),
                        colors = calisTextFieldColors(),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(maxScoreFocusRequester)
                            .testTag("task_score_input")
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val score = if (enableScores) (maxScoreStr.toIntOrNull() ?: 100) else 0
                    val deadlineCal = java.util.Calendar.getInstance().apply {
                        set(java.util.Calendar.YEAR, selectedYear)
                        set(java.util.Calendar.MONTH, selectedMonth)
                        set(java.util.Calendar.DAY_OF_MONTH, selectedDay)
                        set(java.util.Calendar.HOUR_OF_DAY, selectedHour)
                        set(java.util.Calendar.MINUTE, selectedMinute)
                        set(java.util.Calendar.SECOND, 0)
                        set(java.util.Calendar.MILLISECOND, 0)
                    }
                    val deadlineEpochSeconds = deadlineCal.timeInMillis / 1000L
                    onCreate(title.trim(), desc.trim(), deadlineEpochSeconds, score)
                },
                enabled = title.isNotBlank() && !isTitleExceeded && !isDescExceeded && (!enableScores || maxScoreStr.isNotBlank()),
                modifier = Modifier.testTag("confirm_create_task")
            ) {
                Text("Create Task")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarDatePickerDialog(
    initialYear: Int,
    initialMonth: Int,
    initialDay: Int,
    onDismiss: () -> Unit,
    onConfirm: (year: Int, month: Int, day: Int) -> Unit
) {
    val initialUtcMillis = remember(initialYear, initialMonth, initialDay) {
        val cal = java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("UTC")).apply {
            clear()
            set(initialYear, initialMonth, initialDay)
        }
        cal.timeInMillis
    }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialUtcMillis)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        tonalElevation = 0.dp,
        colors = DatePickerDefaults.colors(
            containerColor = Color.White
        ),
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val utcCal = java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("UTC")).apply {
                            timeInMillis = millis
                        }
                        onConfirm(
                            utcCal.get(java.util.Calendar.YEAR),
                            utcCal.get(java.util.Calendar.MONTH),
                            utcCal.get(java.util.Calendar.DAY_OF_MONTH)
                        )
                    }
                    onDismiss()
                }
            ) {
                Text("OK", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                containerColor = Color.White,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                headlineContentColor = MaterialTheme.colorScheme.onSurface,
                weekdayContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                subheadContentColor = MaterialTheme.colorScheme.onSurface,
                navigationContentColor = MaterialTheme.colorScheme.onSurface,
                yearContentColor = MaterialTheme.colorScheme.onSurface,
                currentYearContentColor = MaterialTheme.colorScheme.primary,
                selectedYearContentColor = Color.White,
                selectedYearContainerColor = MaterialTheme.colorScheme.primary,
                dayContentColor = MaterialTheme.colorScheme.onSurface,
                selectedDayContentColor = Color.White,
                selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                todayContentColor = MaterialTheme.colorScheme.primary,
                todayDateBorderColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AnalogTimePickerDialog(
    title: String,
    initialHour: Int,
    initialMinute: Int,
    onDismiss: () -> Unit,
    onConfirm: (hour: Int, minute: Int) -> Unit
) {
    val timePickerState = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = false
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        tonalElevation = 0.dp,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.fillMaxWidth(0.94f),
        containerColor = Color.White,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurface,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(timePickerState.hour, timePickerState.minute)
                }
            ) {
                Text("OK", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                TimePicker(
                    state = timePickerState,
                    colors = TimePickerDefaults.colors(
                        clockDialColor = Color(0xFFF1F5F9),
                        clockDialSelectedContentColor = Color.White,
                        clockDialUnselectedContentColor = MaterialTheme.colorScheme.onSurface,
                        selectorColor = MaterialTheme.colorScheme.primary,
                        containerColor = Color.White,
                        periodSelectorBorderColor = MaterialTheme.colorScheme.outlineVariant,
                        periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        periodSelectorUnselectedContainerColor = Color.White,
                        periodSelectorSelectedContentColor = MaterialTheme.colorScheme.primary,
                        periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        timeSelectorUnselectedContainerColor = Color(0xFFF8FAFC),
                        timeSelectorSelectedContentColor = MaterialTheme.colorScheme.primary,
                        timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
    )
}

private fun formatTime12Hour(hour: Int, minute: Int): String {
    val cal = java.util.Calendar.getInstance().apply {
        set(java.util.Calendar.HOUR_OF_DAY, hour)
        set(java.util.Calendar.MINUTE, minute)
    }
    return java.text.SimpleDateFormat("hh:mm a", java.util.Locale.US).format(cal.time)
}

fun formatStudentNameSurnameFirst(name: String): String {
    val clean = name.trim()
    if (clean.isEmpty()) return ""

    // If already in "SURNAME, FIRSTNAME ..." format
    if (clean.contains(",")) {
        val parts = clean.split(",", limit = 2)
        val surname = parts[0].trim().uppercase()
        val rest = parts[1].trim()
        val restTokens = rest.split("\\s+".toRegex()).filter { it.isNotBlank() }
        if (restTokens.isEmpty()) return surname
        val firstNames = mutableListOf<String>()
        var mi = ""
        for (i in restTokens.indices) {
            val token = restTokens[i]
            if (i == restTokens.size - 1 && (token.length == 1 || (token.length == 2 && token.endsWith(".")) || (restTokens.size > 1 && token.length <= 2))) {
                mi = if (token.endsWith(".")) token.uppercase() else "${token[0].uppercaseChar()}."
            } else {
                firstNames.add(token.uppercase())
            }
        }
        val firstStr = firstNames.joinToString(" ")
        return if (mi.isNotEmpty()) "$surname, $firstStr $mi".trim() else "$surname, $firstStr".trim()
    }

    val tokens = clean.split("\\s+".toRegex()).filter { it.isNotBlank() }
    if (tokens.size == 1) return tokens[0].uppercase()
    if (tokens.size == 2) return "${tokens[1].uppercase()}, ${tokens[0].uppercase()}"

    val surnamePrefixes = setOf("DELA", "DE", "DEL", "DE LOS", "DE LAS", "SAN", "SANTA", "VAN", "VON", "AL", "EL")

    // Check if middle token is an initial (e.g. "Juan B. Dela Cruz" or "Juan B Dela Cruz")
    val miIndex = tokens.indexOfFirst { (it.length == 1 || (it.length == 2 && it.endsWith("."))) }
    if (miIndex > 0 && miIndex < tokens.size - 1) {
        val firstNames = tokens.subList(0, miIndex).joinToString(" ") { it.uppercase() }
        val mi = if (tokens[miIndex].endsWith(".")) tokens[miIndex].uppercase() else "${tokens[miIndex].uppercase()}."
        val surname = tokens.subList(miIndex + 1, tokens.size).joinToString(" ") { it.uppercase() }
        return "$surname, $firstNames $mi"
    }

    // Check compound surname prefixes: "Juan Dela Cruz" -> prefix at index 1 -> surname = "DELA CRUZ", first = "JUAN"
    // "Juan Bautista Dela Cruz" -> prefix at index 2 -> surname = "DELA CRUZ", first = "JUAN", mi = "B."
    for (i in 1 until tokens.size - 1) {
        val singlePrefix = tokens[i].uppercase()
        val doublePrefix = if (i + 1 < tokens.size) "${tokens[i].uppercase()} ${tokens[i+1].uppercase()}" else ""
        if (singlePrefix in surnamePrefixes || doublePrefix in surnamePrefixes) {
            val surname = tokens.subList(i, tokens.size).joinToString(" ") { it.uppercase() }
            val firstAndMiddle = tokens.subList(0, i)
            if (firstAndMiddle.size == 1) {
                return "$surname, ${firstAndMiddle[0].uppercase()}"
            } else {
                val firstNames = firstAndMiddle.subList(0, firstAndMiddle.size - 1).joinToString(" ") { it.uppercase() }
                val middle = firstAndMiddle.last()
                val mi = "${middle.first().uppercaseChar()}."
                return "$surname, $firstNames $mi"
            }
        }
    }

    // 3 tokens without explicit prefix or MI token: e.g. "Juan Bautista Cruz"
    if (tokens.size == 3) {
        val first = tokens[0].uppercase()
        val mi = "${tokens[1].first().uppercaseChar()}."
        val surname = tokens[2].uppercase()
        return "$surname, $first $mi"
    }

    // 4+ tokens: e.g. "Mary Jane Bautista Cruz"
    val surname = tokens.last().uppercase()
    val firstNames = tokens.subList(0, tokens.size - 2).joinToString(" ") { it.uppercase() }
    val mi = "${tokens[tokens.size - 2].first().uppercaseChar()}."
    return "$surname, $firstNames $mi"
}

enum class StudentSortOption(val displayName: String) {
    A_TO_Z("A-Z (Default)"),
    RECENT("Recent"),
    FREQUENTLY_ABSENT("Frequently Absent")
}

enum class GenderFirstOption(val displayName: String) {
    BOYS_FIRST("By Boys"),
    GIRLS_FIRST("By Girls")
}

@Composable
fun TeacherStudentsView(
    pendingList: List<PendingRegistrationEntity>,
    approvedList: List<ApprovedStudentEntity>,
    attendanceLogs: List<AttendanceEntity>,
    isActiveSession: Boolean,
    onApprove: (PendingRegistrationEntity) -> Unit,
    onReject: (PendingRegistrationEntity) -> Unit,
    onUpdateStatus: (String, String, String) -> Unit,
    onNavigateToExport: () -> Unit = {},
    onHistorySubPageChanged: (Boolean) -> Unit = {},
    onRequestsSubPageChanged: (Boolean) -> Unit = {},
    onStudentProfileActiveChanged: (Boolean) -> Unit = {},
    autoApproveEnabled: Boolean = false,
    onToggleAutoApprove: (Boolean) -> Unit = {},
    selectedStudent: ApprovedStudentEntity? = null,
    onSelectedStudentChanged: (ApprovedStudentEntity?) -> Unit = {},
    showingRequestsSubPage: Boolean = false,
    onShowingRequestsSubPageChanged: (Boolean) -> Unit = {},
    showingHistorySubPage: Boolean = false,
    onShowingHistorySubPageChanged: (Boolean) -> Unit = {},
    selectedMonthKey: String? = null,
    onSelectedMonthKeyChanged: (String?) -> Unit = {},
    selectedDate: String? = null,
    onSelectedDateChanged: (String?) -> Unit = {}
) {
    val selectedStudentForDetail = selectedStudent

    var searchQuery by remember { mutableStateOf("") }
    
    val context = LocalContext.current
    val sharedPrefs = remember(context) { context.getSharedPreferences("teacher_students_prefs", android.content.Context.MODE_PRIVATE) }
    
    var currentSortOption by remember {
        mutableStateOf(
            StudentSortOption.valueOf(
                sharedPrefs.getString("student_sort_option", StudentSortOption.A_TO_Z.name) ?: StudentSortOption.A_TO_Z.name
            )
        )
    }
    var showSortDropdown by remember { mutableStateOf(false) }

    var genderFirstOption by remember {
        mutableStateOf(
            GenderFirstOption.valueOf(
                sharedPrefs.getString("gender_first_option", GenderFirstOption.BOYS_FIRST.name) ?: GenderFirstOption.BOYS_FIRST.name
            )
        )
    }

    val todayDateStr = remember {
        java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date())
    }

    androidx.activity.compose.BackHandler(enabled = showingRequestsSubPage) {
        onShowingRequestsSubPageChanged(false)
    }
    androidx.activity.compose.BackHandler(enabled = showingHistorySubPage) {
        onShowingHistorySubPageChanged(false)
    }
    androidx.activity.compose.BackHandler(enabled = selectedStudentForDetail != null) {
        onSelectedStudentChanged(null)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        val isBoySex: (String) -> Boolean = { sex ->
            sex.equals("M", ignoreCase = true) || sex.contains("Boy", ignoreCase = true) || sex.contains("Male", ignoreCase = true)
        }
        val isGirlSex: (String) -> Boolean = { sex ->
            sex.equals("F", ignoreCase = true) || sex.contains("Girl", ignoreCase = true) || sex.contains("Female", ignoreCase = true)
        }

        val totalStudentsCount = approvedList.size
        val totalBoysCount = approvedList.count { isBoySex(it.sex) }
        val totalGirlsCount = approvedList.count { isGirlSex(it.sex) }

        val currentSessionDate = remember(attendanceLogs, todayDateStr) {
            val matchingToday = attendanceLogs.filter { 
                getNormalizedSessionDate(it.date, it.timestamp) == todayDateStr || it.date == todayDateStr 
            }
            // Always prefer today's date if it's the actual current date, 
            // even if matchingToday is empty (to show all as ABSENT for the current session start)
            todayDateStr
        }

        val activeLogs = remember(attendanceLogs, currentSessionDate) {
            attendanceLogs.filter { 
                getNormalizedSessionDate(it.date, it.timestamp) == currentSessionDate || it.date == currentSessionDate
            }
        }

        val presentStudentIds = activeLogs
            .filter { it.status == "PRESENT" || it.status == "LATE" }
            .map { it.studentId }
            .toSet()

        val absentStudentIds = activeLogs
            .filter { it.status == "ABSENT" }
            .map { it.studentId }
            .toSet()

        val totalPresentCount = approvedList.count { it.studentId in presentStudentIds }
        val presentBoysCount = approvedList.count { isBoySex(it.sex) && it.studentId in presentStudentIds }
        val presentGirlsCount = approvedList.count { isGirlSex(it.sex) && it.studentId in presentStudentIds }
        val totalAbsentCount = approvedList.count { it.studentId !in presentStudentIds }

        val filteredList = approvedList.filter { student ->
            if (searchQuery.isBlank()) true
            else {
                student.studentName.contains(searchQuery, ignoreCase = true) ||
                formatStudentNameSurnameFirst(student.studentName).contains(searchQuery, ignoreCase = true) ||
                student.studentId.contains(searchQuery, ignoreCase = true) ||
                student.lrn.contains(searchQuery, ignoreCase = true)
            }
        }

        val absentCountMap = remember(attendanceLogs) {
            attendanceLogs
                .filter { it.status == "ABSENT" }
                .groupingBy { it.studentId }
                .eachCount()
        }

        val sortedList = remember(filteredList, currentSortOption, genderFirstOption, absentCountMap) {
            val baseSorted = when (currentSortOption) {
                StudentSortOption.A_TO_Z -> filteredList.sortedBy { formatStudentNameSurnameFirst(it.studentName) }
                StudentSortOption.RECENT -> filteredList.sortedByDescending { it.approvedAt }
                StudentSortOption.FREQUENTLY_ABSENT -> filteredList.sortedByDescending { absentCountMap[it.studentId] ?: 0 }
            }
            if (genderFirstOption == GenderFirstOption.BOYS_FIRST) {
                baseSorted.sortedWith(compareByDescending { isBoySex(it.sex) })
            } else {
                baseSorted.sortedWith(compareByDescending { isGirlSex(it.sex) })
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {


            // MIDDLE SECTION: SEARCH BAR
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search name, SID, LRN...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear search")
                        }
                    }
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .testTag("student_search_input")
            )

            // FILTER / SORT BAR
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clickable { showSortDropdown = true }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                            .testTag("sort_filter_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Sort,
                            contentDescription = "Sort",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Sort: ${currentSortOption.displayName}",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = showSortDropdown,
                        onDismissRequest = { showSortDropdown = false },
                        containerColor = Color.White,
                        modifier = Modifier.background(Color.White)
                    ) {
                        StudentSortOption.values().forEach { option ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = option.displayName,
                                        fontWeight = if (option == currentSortOption) FontWeight.ExtraBold else FontWeight.Bold
                                    )
                                },
                                onClick = {
                                    currentSortOption = option
                                    sharedPrefs.edit().putString("student_sort_option", option.name).apply()
                                    showSortDropdown = false
                                }
                            )
                        }
                    }
                }

                // Compact Gender Sorter (Male/Female)
                val isBoys = genderFirstOption == GenderFirstOption.BOYS_FIRST
                val badgeBgColor = if (isBoys) Color(0xFFE3F2FD) else Color(0xFFFCE4EC)
                val badgeContentColor = if (isBoys) Color(0xFF0D47A1) else Color(0xFFC2185B)
                val badgeLabel = if (isBoys) "Male" else "Female"

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = badgeBgColor,
                    modifier = Modifier
                        .testTag("gender_priority_button")
                        .clickable {
                            val newOption = if (isBoys) GenderFirstOption.GIRLS_FIRST else GenderFirstOption.BOYS_FIRST
                            genderFirstOption = newOption
                            sharedPrefs.edit().putString("gender_first_option", newOption.name).apply()
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = badgeContentColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = badgeLabel,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = badgeContentColor
                        )
                    }
                }
            }

            // MASTERLIST STUDENT ENTRIES
            if (sortedList.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (searchQuery.isNotBlank()) "No students found matching \"$searchQuery\"."
                               else "No approved students in masterlist yet.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(sortedList) { student ->
                        val todayAttendance = activeLogs.find { it.studentId == student.studentId }
                        val todayStatus = todayAttendance?.status ?: "ABSENT"
                        var showMarkMenu by remember { mutableStateOf(false) }
                        val displayName = remember(student.studentName) {
                            formatStudentNameSurnameFirst(student.studentName)
                        }

                        val isFemale = student.sex.equals("F", ignoreCase = true) || student.sex.contains("Girl", ignoreCase = true) || student.sex.contains("Female", ignoreCase = true)
                        val avatarBgColor = if (isFemale) Color(0xFFFCE4EC) else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                        val avatarIconColor = if (isFemale) Color(0xFFC2185B) else MaterialTheme.colorScheme.primary

                        Card(
                            onClick = { onSelectedStudentChanged(student) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("student_card_${student.studentId}"),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        shape = CircleShape,
                                        color = avatarBgColor,
                                        modifier = Modifier.size(30.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Icon(
                                                imageVector = Icons.Default.Person,
                                                contentDescription = null,
                                                tint = avatarIconColor,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        BackAndForthMarqueeText(
                                            text = displayName,
                                            style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp),
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }

                                Box {
                                    Surface(
                                        modifier = Modifier.width(76.dp),
                                        shape = RoundedCornerShape(6.dp),
                                        color = when (todayStatus) {
                                            "PRESENT" -> StatusPresentGreen.copy(alpha = 0.15f)
                                            "LATE" -> StatusLateAmber.copy(alpha = 0.15f)
                                            "ABSENT" -> StatusAbsentRed.copy(alpha = 0.15f)
                                            else -> MaterialTheme.colorScheme.surfaceVariant
                                        },
                                        onClick = { showMarkMenu = true }
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 3.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = todayStatus,
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = when (todayStatus) {
                                                    "PRESENT" -> StatusPresentGreen
                                                    "LATE" -> StatusLateAmber
                                                    "ABSENT" -> StatusAbsentRed
                                                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                                                },
                                                maxLines = 1
                                            )
                                        }
                                    }
                                    androidx.compose.material3.DropdownMenu(
                                        expanded = showMarkMenu,
                                        onDismissRequest = { showMarkMenu = false },
                                        containerColor = Color.White,
                                        modifier = Modifier.background(Color.White)
                                    ) {
                                        androidx.compose.material3.DropdownMenuItem(
                                            text = { Text("Present") },
                                            onClick = {
                                                onUpdateStatus(student.studentId, currentSessionDate, "PRESENT")
                                                showMarkMenu = false
                                            }
                                        )
                                        androidx.compose.material3.DropdownMenuItem(
                                            text = { Text("Late") },
                                            onClick = {
                                                onUpdateStatus(student.studentId, currentSessionDate, "LATE")
                                                showMarkMenu = false
                                            }
                                        )
                                        androidx.compose.material3.DropdownMenuItem(
                                            text = { Text("Absent") },
                                            onClick = {
                                                onUpdateStatus(student.studentId, currentSessionDate, "ABSENT")
                                                showMarkMenu = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }
        }



        if (showingRequestsSubPage) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .clickable(
                        interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                        indication = null,
                        onClick = {}
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onToggleAutoApprove(!autoApproveEnabled) }
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Auto-Approve Requests",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "Automatically approve student registrations via BLE",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Switch(
                            checked = autoApproveEnabled,
                            onCheckedChange = null,
                            modifier = Modifier.testTag("auto_approve_switch")
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Divider()
                    Spacer(modifier = Modifier.height(12.dp))

                    PendingApprovalsView(
                        pendingList = pendingList,
                        onApprove = onApprove,
                        onReject = onReject
                    )
                }
            }
        }

        if (showingHistorySubPage) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .clickable(
                        interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                        indication = null,
                        onClick = {}
                    )
            ) {
                TeacherAttendanceHistorySubPage(
                    onBack = { onShowingHistorySubPageChanged(false) },
                    attendanceLogs = attendanceLogs,
                    approvedStudents = approvedList,
                    isActiveSession = isActiveSession,
                    todayDateStr = todayDateStr,
                    onUpdateStatus = onUpdateStatus,
                    onExportTasksClick = {
                        onShowingHistorySubPageChanged(false)
                        onNavigateToExport()
                    },
                    onStudentClick = { onSelectedStudentChanged(it) },
                    selectedMonthKey = selectedMonthKey,
                    onSelectedMonthKeyChanged = onSelectedMonthKeyChanged,
                    selectedDate = selectedDate,
                    onSelectedDateChanged = onSelectedDateChanged
                )
            }
        }

        if (selectedStudentForDetail != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .clickable(
                        interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                        indication = null,
                        onClick = {}
                    )
            ) {
                StudentDetailSubPage(
                    student = selectedStudentForDetail!!,
                    attendanceLogs = attendanceLogs,
                    todayDateStr = todayDateStr,
                    onBack = { onSelectedStudentChanged(null) },
                    onUpdateStatus = onUpdateStatus
                )
            }
        }
    }
}

@Composable
fun StudentDetailSubPage(
    student: ApprovedStudentEntity,
    attendanceLogs: List<AttendanceEntity>,
    todayDateStr: String,
    onBack: () -> Unit,
    onUpdateStatus: (String, String, String) -> Unit
) {
    val studentLogs = remember(attendanceLogs, student.studentId) {
        attendanceLogs.filter { it.studentId == student.studentId }.sortedByDescending { it.date }
    }

    val totalLogged = studentLogs.size
    val presentCount = studentLogs.count { it.status == "PRESENT" }
    val lateCount = studentLogs.count { it.status == "LATE" }
    val absentCount = studentLogs.count { it.status == "ABSENT" }
    val attendanceRate = if (totalLogged > 0) {
        ((presentCount + lateCount).toDouble() / totalLogged * 100).toInt()
    } else 100

    val todayLog = studentLogs.find { 
        getNormalizedSessionDate(it.date, it.timestamp) == todayDateStr || it.date == todayDateStr || it.date.startsWith(todayDateStr)
    }
    val todayStatus = todayLog?.status ?: "ABSENT"

    val birthFormatted = remember(student.birth) {
        formatEpochSecondsToDate(student.birth)
    }

    val approvedDateFormatted = remember(student.approvedAt) {
        val sdf = java.text.SimpleDateFormat("MM/dd/yyyy hh:mm a", java.util.Locale.US)
        sdf.format(java.util.Date(student.approvedAt))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // TOP HEADER WITH BACK BUTTON
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.testTag("student_detail_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to Masterlist",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Student Profile",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Detailed student information & attendance records",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // HERO / IDENTITY CARD
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("student_identity_card"),
            shape = RoundedCornerShape(16.dp),
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
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Avatar Circle
                    val isFemale = student.sex.equals("F", true) || student.sex.contains("Female", true) || student.sex.contains("Girl", true)
                    val avatarBgColor = if (isFemale) Color(0xFFFCE4EC) else MaterialTheme.colorScheme.primaryContainer
                    val avatarTextColor = if (isFemale) Color(0xFFC2185B) else MaterialTheme.colorScheme.onPrimaryContainer
                    val sidBgColor = if (isFemale) Color(0xFFFCE4EC) else MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                    val sidTextColor = if (isFemale) Color(0xFFC2185B) else MaterialTheme.colorScheme.primary

                    Surface(
                        shape = CircleShape,
                        color = avatarBgColor,
                        modifier = Modifier.size(56.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = student.studentName.take(1).uppercase(),
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = avatarTextColor
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = student.studentName,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = sidBgColor
                            ) {
                                Text(
                                    text = "SID: ${student.studentId}",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = sidTextColor,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            val isMale = student.sex.equals("M", true) || student.sex.contains("Male", true)
                            val sexColor = if (isMale) Color(0xFF1976D2) else Color(0xFFC2185B)
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = sexColor.copy(alpha = 0.12f)
                            ) {
                                Text(
                                    text = if (isMale) "Male" else if (student.sex.equals("F", true) || student.sex.contains("Female", true)) "Female" else student.sex,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = sexColor,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }

                Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                // INFORMATION GRID
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    StudentInfoRow(
                        icon = Icons.Default.Person,
                        label = "12-Digit LRN",
                        value = student.lrn.ifBlank { "Not specified" }
                    )
                    StudentInfoRow(
                        icon = Icons.Default.CalendarToday,
                        label = "Date of Birth",
                        value = birthFormatted
                    )
                    StudentInfoRow(
                        icon = Icons.Default.Group,
                        label = "Sex / Gender",
                        value = if (student.sex.equals("M", true) || student.sex.contains("Male", true)) "Male (M)" else if (student.sex.equals("F", true) || student.sex.contains("Female", true)) "Female (F)" else student.sex
                    )
                    StudentInfoRow(
                        icon = Icons.Default.Schedule,
                        label = "Approved On",
                        value = approvedDateFormatted
                    )
                }
            }
        }

        // ATTENDANCE STATS SUMMARY CARD
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("student_attendance_stats_card"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Attendance Statistics",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = if (attendanceRate >= 80) StatusPresentGreen.copy(alpha = 0.15f) else StatusAbsentRed.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = "$attendanceRate% Present Rate",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (attendanceRate >= 80) StatusPresentGreen else StatusAbsentRed,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StatBox(
                        title = "Present",
                        value = "$presentCount",
                        modifier = Modifier.weight(1f)
                    )
                    StatBox(
                        title = "Late",
                        value = "$lateCount",
                        modifier = Modifier.weight(1f)
                    )
                    StatBox(
                        title = "Absent",
                        value = "$absentCount",
                        modifier = Modifier.weight(1f),
                        isWarning = absentCount > 0
                    )
                    StatBox(
                        title = "Total Logs",
                        value = "$totalLogged",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // TODAY'S ATTENDANCE QUICK ACTION CARD
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("student_today_attendance_card"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Today's Status",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = todayDateStr,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = when (todayStatus) {
                            "PRESENT" -> StatusPresentGreen.copy(alpha = 0.15f)
                            "LATE" -> StatusLateAmber.copy(alpha = 0.15f)
                            "ABSENT" -> StatusAbsentRed.copy(alpha = 0.15f)
                            else -> MaterialTheme.colorScheme.surfaceVariant
                        }
                    ) {
                        Text(
                            text = todayStatus,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = when (todayStatus) {
                                "PRESENT" -> StatusPresentGreen
                                "LATE" -> StatusLateAmber
                                "ABSENT" -> StatusAbsentRed
                                else -> MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { onUpdateStatus(student.studentId, todayDateStr, "PRESENT") },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("mark_present_detail_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (todayStatus == "PRESENT") StatusPresentGreen else MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = if (todayStatus == "PRESENT") Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) {
                        Text("Present", fontSize = 12.sp)
                    }

                    Button(
                        onClick = { onUpdateStatus(student.studentId, todayDateStr, "LATE") },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("mark_late_detail_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (todayStatus == "LATE") StatusLateAmber else MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = if (todayStatus == "LATE") Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) {
                        Text("Late", fontSize = 12.sp)
                    }

                    Button(
                        onClick = { onUpdateStatus(student.studentId, todayDateStr, "ABSENT") },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("mark_absent_detail_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (todayStatus == "ABSENT") StatusAbsentRed else MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = if (todayStatus == "ABSENT") Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) {
                        Text("Absent", fontSize = 12.sp)
                    }
                }
            }
        }

        // ATTENDANCE LOGS HISTORY FOR THIS STUDENT
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Attendance Records",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            if (studentLogs.isEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No attendance records found for this student yet.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                studentLogs.forEach { log ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = log.date,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                val timeRecorded = remember(log.timestamp) {
                                    val sdf = java.text.SimpleDateFormat("hh:mm:ss a", java.util.Locale.US)
                                    sdf.format(java.util.Date(log.timestamp * 1000L))
                                }
                                Text(
                                    text = "Recorded at: $timeRecorded",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = when (log.status) {
                                    "PRESENT" -> StatusPresentGreen.copy(alpha = 0.15f)
                                    "LATE" -> StatusLateAmber.copy(alpha = 0.15f)
                                    else -> StatusAbsentRed.copy(alpha = 0.15f)
                                }
                            ) {
                                Text(
                                    text = log.status,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
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
    }
}

private fun formatEpochSecondsToDate(epochSeconds: Long): String {
    if (epochSeconds <= 0L) return "N/A"
    val millis = if (epochSeconds > 100_000_000_000L) epochSeconds else epochSeconds * 1000L
    val sdf = java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US)
    return sdf.format(java.util.Date(millis))
}

@Composable
private fun StudentInfoRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
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
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun StatBox(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    isWarning: Boolean = false
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        color = if (isWarning) StatusAbsentRed.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (isWarning) StatusAbsentRed else MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
        }
    }
}

data class MonthAttendanceGroup(
    val monthKey: String,
    val monthName: String,
    val dates: List<String>
)

fun parseAttendanceDate(dateStr: String): java.util.Calendar? {
    if (dateStr.isBlank()) return null
    val str = dateStr.trim()

    // 0. Numeric epoch timestamp check (e.g., "1729238400000" or "1729238400")
    if (str.all { it.isDigit() }) {
        try {
            val num = str.toLong()
            if (num > 0) {
                val ts = if (num < 10000000000L) num * 1000L else num
                return java.util.Calendar.getInstance().apply { timeInMillis = ts }
            }
        } catch (_: Exception) {}
    }

    val dateOnlyPart = str.split(" ")[0]

    // 1. Regex check for ISO format "yyyy-MM-dd" or "yyyy/MM/dd"
    val isoMatch = Regex("""^(\d{4})[-/](\d{1,2})[-/](\d{1,2})""").find(dateOnlyPart)
    if (isoMatch != null) {
        val (y, m, d) = isoMatch.destructured
        val year = y.toIntOrNull() ?: 0
        val month = (m.toIntOrNull() ?: 0) - 1
        val day = d.toIntOrNull() ?: 0
        if (year in 2000..2100 && month in 0..11 && day in 1..31) {
            return java.util.Calendar.getInstance().apply {
                clear()
                set(year, month, day)
            }
        }
    }

    // 2. Regex check for US format "MM/dd/yyyy" or "MM-dd-yyyy"
    val usMatch = Regex("""^(\d{1,2})[-/](\d{1,2})[-/](\d{4})""").find(dateOnlyPart)
    if (usMatch != null) {
        val (m, d, y) = usMatch.destructured
        val month = (m.toIntOrNull() ?: 0) - 1
        val day = d.toIntOrNull() ?: 0
        val year = y.toIntOrNull() ?: 0
        if (year in 2000..2100 && month in 0..11 && day in 1..31) {
            return java.util.Calendar.getInstance().apply {
                clear()
                set(year, month, day)
            }
        }
    }

    // 3. Strict SimpleDateFormat fallback
    val patterns = listOf(
        "MMMM d, yyyy",
        "MMMM d yyyy",
        "MMM d, yyyy",
        "MMM d yyyy",
        "yyyy-MM-dd HH:mm:ss",
        "yyyy-MM-dd HH:mm",
        "yyyy/MM/dd HH:mm:ss",
        "yyyy/MM/dd HH:mm",
        "MM/dd/yyyy hh:mm a",
        "MM/dd/yyyy HH:mm",
        "MM-dd-yyyy hh:mm a",
        "MM-dd-yyyy HH:mm",
        "MM/dd/yyyy",
        "yyyy-MM-dd",
        "MM-dd-yyyy"
    )
    for (pat in patterns) {
        try {
            val sdf = java.text.SimpleDateFormat(pat, java.util.Locale.US)
            sdf.isLenient = false
            val parsed = sdf.parse(str) ?: sdf.parse(dateOnlyPart)
            if (parsed != null) {
                val cal = java.util.Calendar.getInstance().apply { time = parsed }
                val yr = cal.get(java.util.Calendar.YEAR)
                if (yr in 2000..2100) return cal
            }
        } catch (_: Exception) {}
    }

    return null
}

fun getNormalizedSessionDate(dateStr: String, timestamp: Long = 0L): String {
    val cal = parseAttendanceDate(dateStr)
    if (cal != null) {
        return java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(cal.time)
    }
    if (timestamp > 0) {
        try {
            val ts = if (timestamp < 10000000000L) timestamp * 1000L else timestamp
            return java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date(ts))
        } catch (_: Exception) {}
    }
    // Final fallback: try to re-parse and format if it's already in a recognizable but not normalized format
    val parts = dateStr.trim().split(" ")[0].split('-', '/')
    if (parts.size == 3) {
        try {
            val y: Int; val m: Int; val d: Int
            if (parts[0].length == 4) { // yyyy-MM-dd
                y = parts[0].toInt(); m = parts[1].toInt(); d = parts[2].toInt()
            } else if (parts[2].length == 4) { // MM-dd-yyyy or dd-MM-yyyy
                // Assume MM-dd-yyyy as primary US format used in other parts
                y = parts[2].toInt(); m = parts[0].toInt(); d = parts[1].toInt()
            } else {
                return dateStr.trim().split(" ")[0]
            }
            val c = java.util.Calendar.getInstance().apply {
                clear()
                set(y, m - 1, d)
            }
            return java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(c.time)
        } catch (_: Exception) {}
    }
    return dateStr.trim().split(" ")[0]
}

fun formatAttendanceDayOnly(dateStr: String): String {
    val cal = parseAttendanceDate(dateStr)
    if (cal != null) {
        return cal.get(java.util.Calendar.DAY_OF_MONTH).toString()
    }
    val parts = dateStr.trim().split('-', '/')
    val lastPart = parts.lastOrNull()?.trim() ?: dateStr
    return lastPart.toIntOrNull()?.toString() ?: lastPart
}

fun formatAttendanceMonthAndDay(dateStr: String): String {
    val cal = parseAttendanceDate(dateStr)
    if (cal != null) {
        val sdf = java.text.SimpleDateFormat("MMMM d", java.util.Locale.US)
        return sdf.format(cal.time)
    }
    return dateStr
}

fun isSessionDateEarlierThanApproved(sessionDateStr: String, approvedAtMs: Long): Boolean {
    val parsedSession = parseAttendanceDate(sessionDateStr)
    if (parsedSession != null) {
        val approvedCal = java.util.Calendar.getInstance().apply { timeInMillis = approvedAtMs }
        
        val isEarlierDay = parsedSession.get(java.util.Calendar.YEAR) < approvedCal.get(java.util.Calendar.YEAR) ||
            (parsedSession.get(java.util.Calendar.YEAR) == approvedCal.get(java.util.Calendar.YEAR) && 
             parsedSession.get(java.util.Calendar.DAY_OF_YEAR) < approvedCal.get(java.util.Calendar.DAY_OF_YEAR))
             
        return isEarlierDay
    }
    return false
}

@Composable
fun TeacherAttendanceHistorySubPage(
    onBack: () -> Unit,
    attendanceLogs: List<com.example.data.db.AttendanceEntity>,
    approvedStudents: List<com.example.data.db.ApprovedStudentEntity>,
    isActiveSession: Boolean,
    todayDateStr: String,
    onUpdateStatus: (String, String, String) -> Unit,
    onExportTasksClick: () -> Unit = {},
    onStudentClick: (com.example.data.db.ApprovedStudentEntity) -> Unit = {},
    selectedMonthKey: String? = null,
    onSelectedMonthKeyChanged: (String?) -> Unit = {},
    selectedDate: String? = null,
    onSelectedDateChanged: (String?) -> Unit = {}
) {
    val monthGroups = remember(attendanceLogs) {
        val groupsMap = mutableMapOf<String, Pair<String, MutableSet<String>>>()

        attendanceLogs.forEach { log ->
            // 1. Parse log.date strictly first
            var cal = parseAttendanceDate(log.date)

            // 2. Fallback to timestamp ONLY if log.date is completely unparseable or empty
            if (cal == null && log.timestamp > 0) {
                val ts = if (log.timestamp < 10000000000L) log.timestamp * 1000L else log.timestamp
                cal = java.util.Calendar.getInstance().apply { timeInMillis = ts }
            }

            var monthKey = ""
            var monthName = ""

            if (cal != null) {
                monthKey = java.text.SimpleDateFormat("yyyy-MM", java.util.Locale.US).format(cal.time)
                monthName = java.text.SimpleDateFormat("MMMM yyyy", java.util.Locale.US).format(cal.time)
            } else {
                monthKey = "OTHER"
                monthName = "Other Sessions"
            }

            val normDay = if (cal != null) {
                java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(cal.time)
            } else {
                getNormalizedSessionDate(log.date, log.timestamp)
            }

            val entry = groupsMap.getOrPut(monthKey) { Pair(monthName, mutableSetOf()) }
            if (normDay.isNotBlank()) {
                entry.second.add(normDay)
            }
        }

        groupsMap.entries
            .sortedByDescending { it.key }
            .map { entry ->
                MonthAttendanceGroup(
                    monthKey = entry.key,
                    monthName = entry.value.first,
                    dates = entry.value.second.toList().sortedByDescending { dayStr ->
                        parseAttendanceDate(dayStr)?.timeInMillis ?: 0L
                    }
                )
            }
    }

    var localSelectedMonthKey by remember(selectedMonthKey) { mutableStateOf(selectedMonthKey) }
    var localSelectedDate by remember(selectedDate) { mutableStateOf(selectedDate) }

    val currentSelectedMonthKey = selectedMonthKey ?: localSelectedMonthKey
    val currentSelectedDate = selectedDate ?: localSelectedDate

    val setMonthKey: (String?) -> Unit = {
        localSelectedMonthKey = it
        onSelectedMonthKeyChanged(it)
    }
    val setDate: (String?) -> Unit = {
        localSelectedDate = it
        onSelectedDateChanged(it)
    }

    val currentMonthGroup = remember(monthGroups, currentSelectedMonthKey) {
        monthGroups.find { it.monthKey == currentSelectedMonthKey }
    }

    androidx.activity.compose.BackHandler {
        if (currentSelectedDate != null) {
            setDate(null)
        } else if (currentSelectedMonthKey != null) {
            setMonthKey(null)
        } else {
            onBack()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (currentSelectedDate != null) {
            // Level 3: Session Attendance Record (Student List)
            val dateLogs = attendanceLogs.filter { log ->
                getNormalizedSessionDate(log.date, log.timestamp) == currentSelectedDate || log.date == currentSelectedDate
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(approvedStudents) { student ->
                    val log = dateLogs.find { it.studentId == student.studentId }
                    val isEarlier = currentSelectedDate.let { isSessionDateEarlierThanApproved(it, student.approvedAt) }
                    val status = if (log == null) {
                        "UNMARKED"
                    } else if (isEarlier) {
                        "UNMARKED"
                    } else {
                        log.status
                    }
                    var showMarkMenu by remember { mutableStateOf(false) }
                    val displayName = remember(student.studentName) {
                        formatStudentNameSurnameFirst(student.studentName)
                    }

                    val isFemale = student.sex.equals("F", ignoreCase = true) || student.sex.contains("Girl", ignoreCase = true) || student.sex.contains("Female", ignoreCase = true)
                    val avatarBgColor = if (isFemale) Color(0xFFFCE4EC) else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                    val avatarIconColor = if (isFemale) Color(0xFFC2185B) else MaterialTheme.colorScheme.primary

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("student_card_${student.studentId}")
                            .clickable { onStudentClick(student) },
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 7.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    shape = CircleShape,
                                    color = avatarBgColor,
                                    modifier = Modifier.size(30.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = null,
                                            tint = avatarIconColor,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    BackAndForthMarqueeText(
                                        text = displayName,
                                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.5.sp),
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }

                            Box {
                                Surface(
                                    modifier = Modifier.width(76.dp),
                                    shape = RoundedCornerShape(6.dp),
                                    color = when (status) {
                                        "PRESENT" -> StatusPresentGreen.copy(alpha = 0.15f)
                                        "LATE" -> StatusLateAmber.copy(alpha = 0.15f)
                                        "ABSENT" -> StatusAbsentRed.copy(alpha = 0.15f)
                                        else -> MaterialTheme.colorScheme.surfaceVariant
                                    },
                                    onClick = { showMarkMenu = true }
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = status,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = when (status) {
                                                "PRESENT" -> StatusPresentGreen
                                                "LATE" -> StatusLateAmber
                                                "ABSENT" -> StatusAbsentRed
                                                else -> MaterialTheme.colorScheme.onSurfaceVariant
                                            },
                                            maxLines = 1
                                        )
                                    }
                                }
                                androidx.compose.material3.DropdownMenu(
                                    expanded = showMarkMenu,
                                    onDismissRequest = { showMarkMenu = false },
                                    containerColor = Color.White,
                                    modifier = Modifier.background(Color.White)
                                ) {
                                    androidx.compose.material3.DropdownMenuItem(
                                        text = { Text("Present") },
                                        onClick = {
                                            onUpdateStatus(student.studentId, currentSelectedDate, "PRESENT")
                                            showMarkMenu = false
                                        }
                                    )
                                    androidx.compose.material3.DropdownMenuItem(
                                        text = { Text("Late") },
                                        onClick = {
                                            onUpdateStatus(student.studentId, currentSelectedDate, "LATE")
                                            showMarkMenu = false
                                        }
                                    )
                                    androidx.compose.material3.DropdownMenuItem(
                                        text = { Text("Absent") },
                                        onClick = {
                                            onUpdateStatus(student.studentId, currentSelectedDate, "ABSENT")
                                            showMarkMenu = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        } else if (currentSelectedMonthKey != null && currentMonthGroup != null) {
            // Level 2: Month Session List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(currentMonthGroup.dates) { date ->
                    val dateLogs = attendanceLogs.filter { log ->
                        getNormalizedSessionDate(log.date, log.timestamp) == date || log.date == date
                    }
                    val presentCount = dateLogs.count { it.status.equals("PRESENT", ignoreCase = true) || it.status.equals("LATE", ignoreCase = true) }
                    val totalCount = approvedStudents.size
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { setDate(date) },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = formatAttendanceMonthAndDay(date),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Card(
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
                                ) {
                                    Column(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Present",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF2E7D32)
                                        )
                                        Text(
                                            text = "$presentCount",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color(0xFF1B5E20)
                                        )
                                    }
                                }
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = "View Details",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        } else {
            // Level 1: Month Groups List
            if (monthGroups.isEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No Previous Attendance Records",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Attendance logs for past completed sessions will appear here.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(monthGroups) { group ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { setMonthKey(group.monthKey) },
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                                ) {
                                    Text(
                                        text = group.monthName,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Card(
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "Sessions",
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFF2E7D32)
                                            )
                                            Text(
                                                text = "${group.dates.size}",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.ExtraBold,
                                                color = Color(0xFF1B5E20)
                                            )
                                        }
                                    }
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = "Open Month",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }
        }
    }

        // Floating action button for Export Attendance (bottom right, same size, no icon)
        ExtendedFloatingActionButton(
            onClick = onExportTasksClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
                .testTag("export_attendance_fab"),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Text("Export Attendance")
        }
    }
}

// -----------------------------------------------------------------------------------------
// REDESIGNED CLASSROOM INFORMATION OVERVIEW TILE WITH COMBINED ATTENDANCE OVERVIEW
// -----------------------------------------------------------------------------------------
@Composable
private fun ClassroomInformationOverviewTile(
    activeCls: com.example.data.db.ClassroomEntity,
    approvedStudents: List<ApprovedStudentEntity> = emptyList(),
    attendanceLogs: List<AttendanceEntity> = emptyList(),
    todayDateStr: String = "",
    onHistoryClick: () -> Unit = {}
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

    // Attendance Calculations
    val isBoySex: (String) -> Boolean = { sex ->
        sex.equals("M", ignoreCase = true) || sex.contains("Boy", ignoreCase = true) || sex.contains("Male", ignoreCase = true)
    }
    val isGirlSex: (String) -> Boolean = { sex ->
        sex.equals("F", ignoreCase = true) || sex.contains("Girl", ignoreCase = true) || sex.contains("Female", ignoreCase = true)
    }

    val totalStudentsCount = approvedStudents.size
    val totalBoysCount = approvedStudents.count { isBoySex(it.sex) }
    val totalGirlsCount = approvedStudents.count { isGirlSex(it.sex) }

    val activeLogs = attendanceLogs.filter { 
        getNormalizedSessionDate(it.date, it.timestamp) == todayDateStr || it.date == todayDateStr 
    }

    val presentStudentIds = activeLogs
        .filter { it.status == "PRESENT" || it.status == "LATE" }
        .map { it.studentId }
        .toSet()

    val totalPresentCount = approvedStudents.count { it.studentId in presentStudentIds }
    val presentBoysCount = approvedStudents.count { isBoySex(it.sex) && it.studentId in presentStudentIds }
    val presentGirlsCount = approvedStudents.count { isGirlSex(it.sex) && it.studentId in presentStudentIds }
    val totalAbsentCount = approvedStudents.count { it.studentId !in presentStudentIds }

    val isSessionActive = activeCls.isActiveSession
    val formattedDate = remember {
        java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date())
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // --- 1. SIDE-BY-SIDE: TOTAL PRESENT TILE (LEFT) & RTC TILE (RIGHT) ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Total Present Tile with History Link (Left)
            Card(
                modifier = Modifier
                    .weight(0.95f)
                    .height(105.dp)
                    .clickable { onHistoryClick() }
                    .testTag("attendance_total_tile"),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            if (!activeCls.isBound) androidx.compose.ui.graphics.SolidColor(Color(0xFF94A3B8))
                            else if (isSessionActive) AccentGradient
                            else androidx.compose.ui.graphics.SolidColor(Color.Gray)
                        )
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Total Present",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (activeCls.isBound) Color.White else Color(0xFFF8FAFC),
                        fontSize = 13.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 2.dp)
                    )
                    Text(
                        text = "$totalPresentCount / $totalStudentsCount",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = if (activeCls.isBound) Color.White else Color(0xFFF8FAFC),
                        fontSize = 34.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 2.dp, end = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "History",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = if (activeCls.isBound) Color.White.copy(alpha = 0.9f) else Color(0xFFE2E8F0)
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "History",
                            tint = if (activeCls.isBound) Color.White.copy(alpha = 0.9f) else Color(0xFFE2E8F0),
                            modifier = Modifier.size(13.dp)
                        )
                    }
                }
            }

            // RTC Tile (Right - Larger)
            Card(
                modifier = Modifier
                    .weight(1.5f)
                    .height(105.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            if (activeCls.isBound) AccentGradient
                            else androidx.compose.ui.graphics.SolidColor(Color(0xFF94A3B8))
                        )
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (currentTimeStr.isNotEmpty()) currentTimeStr else "--:--:-- --",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = if (activeCls.isBound) Color.White else Color(0xFFF8FAFC),
                            fontSize = 30.sp,
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (currentDateStr.isNotEmpty()) currentDateStr else "Loading...",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            color = if (activeCls.isBound) Color.White.copy(alpha = 0.9f) else Color(0xFFE2E8F0),
                            fontSize = 11.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

        // --- SWIPEABLE CONTAINER (HORIZONTAL PAGER) ---
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .height(118.dp)
        ) { page ->
            if (page == 0) {
                // VIEW 1: ATTENDANCE OVERVIEW TILE CONTENT (DEFAULT)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("attendance_overview_card"),
                    verticalArrangement = Arrangement.Center
                ) {

                    // Row of 3 columns for Boys, Girls, Absents (Styled like ST Present, Late, Absent tiles)
                    Row(
                        modifier = Modifier.fillMaxWidth().height(115.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Boys Tile
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .testTag("attendance_boys_tile"),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (activeCls.isBound) Color(0xFFE3F2FD) else Color(0xFFE2E8F0)
                            ),
                            border = if (activeCls.isBound) null else BorderStroke(1.dp, Color(0xFFCBD5E1))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 8.dp, horizontal = 4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = "$presentBoysCount / $totalBoysCount",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (activeCls.isBound) Color(0xFF0D47A1) else Color(0xFF475569),
                                    fontSize = 26.sp
                                )
                                Text(
                                    text = "Boys",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (activeCls.isBound) Color(0xFF1565C0) else Color(0xFF64748B)
                                )
                            }
                        }

                        // Girls Tile
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .testTag("attendance_girls_tile"),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (activeCls.isBound) Color(0xFFFCE4EC) else Color(0xFFE2E8F0)
                            ),
                            border = if (activeCls.isBound) null else BorderStroke(1.dp, Color(0xFFCBD5E1))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 8.dp, horizontal = 4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = "$presentGirlsCount / $totalGirlsCount",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (activeCls.isBound) Color(0xFF880E4F) else Color(0xFF475569),
                                    fontSize = 26.sp
                                )
                                Text(
                                    text = "Girls",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (activeCls.isBound) Color(0xFFC2185B) else Color(0xFF64748B)
                                )
                            }
                        }

                        // Absents Tile
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .testTag("attendance_absents_tile"),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (activeCls.isBound) (if (totalAbsentCount > 0) StatusAbsentRed.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)) else Color(0xFFE2E8F0)
                            ),
                            border = if (activeCls.isBound) null else BorderStroke(1.dp, Color(0xFFCBD5E1))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 8.dp, horizontal = 4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = "$totalAbsentCount",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (activeCls.isBound) (if (totalAbsentCount > 0) StatusAbsentRed else MaterialTheme.colorScheme.onSurfaceVariant) else Color(0xFF475569),
                                    fontSize = 26.sp
                                )
                                Text(
                                    text = "Absents",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (activeCls.isBound) (if (totalAbsentCount > 0) StatusAbsentRed else MaterialTheme.colorScheme.onSurfaceVariant) else Color(0xFF64748B)
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
                        val startMins = parseTimeToMinutes(activeCls.startTime)
                        val graceMins = parseTimeToMinutes(activeCls.graceTime)
                        val endMins = parseTimeToMinutes(activeCls.endTime)

                        val isStartHighlighted = currentMinutes in startMins until (if (graceMins > startMins) graceMins else startMins + 15)
                        val isGraceHighlighted = currentMinutes in graceMins until (if (endMins > graceMins) endMins else graceMins + 60)
                        val isEndHighlighted = currentMinutes in endMins until (endMins + 120)

                        ScheduleTimeSubTile(
                            modifier = Modifier.weight(1f),
                            title = "Start Time",
                            value = formatDisplayTime(activeCls.startTime),
                            accentColor = if (activeCls.isBound) MaterialTheme.colorScheme.primary else Color(0xFF64748B),
                            isHighlighted = isStartHighlighted && activeCls.isBound,
                            isBound = activeCls.isBound
                        )

                        ScheduleTimeSubTile(
                            modifier = Modifier.weight(1f),
                            title = "Grace Period",
                            value = formatDisplayTime(activeCls.graceTime),
                            accentColor = if (activeCls.isBound) MaterialTheme.colorScheme.secondary else Color(0xFF64748B),
                            isHighlighted = isGraceHighlighted && activeCls.isBound,
                            isBound = activeCls.isBound
                        )

                        ScheduleTimeSubTile(
                            modifier = Modifier.weight(1f),
                            title = "Dismissal",
                            value = formatDisplayTime(activeCls.endTime),
                            accentColor = if (activeCls.isBound) MaterialTheme.colorScheme.tertiary else Color(0xFF64748B),
                            isHighlighted = isEndHighlighted && activeCls.isBound,
                            isBound = activeCls.isBound
                        )
                    }

                    DaysScheduleSubTileRow(scheduleDaysString = activeCls.days, isBound = activeCls.isBound)
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
                color = if (activeCls.isBound) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f) else Color(0xFFE2E8F0),
                border = if (activeCls.isBound) null else BorderStroke(1.dp, Color(0xFFCBD5E1))
            ) {
                Row(
                    modifier = Modifier.padding(2.dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = if (pagerState.currentPage == 0) (if (activeCls.isBound) MaterialTheme.colorScheme.primary else Color(0xFF64748B)) else Color.Transparent,
                        modifier = Modifier.clickable {
                            coroutineScope.launch { pagerState.animateScrollToPage(0) }
                        }
                    ) {
                        Text(
                            text = "Attendance",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (pagerState.currentPage == 0) Color.White else (if (activeCls.isBound) MaterialTheme.colorScheme.onSurfaceVariant else Color(0xFF64748B)),
                            fontSize = 11.sp
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = if (pagerState.currentPage == 1) (if (activeCls.isBound) MaterialTheme.colorScheme.primary else Color(0xFF64748B)) else Color.Transparent,
                        modifier = Modifier.clickable {
                            coroutineScope.launch { pagerState.animateScrollToPage(1) }
                        }
                    ) {
                        Text(
                            text = "Schedule",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (pagerState.currentPage == 1) Color.White else (if (activeCls.isBound) MaterialTheme.colorScheme.onSurfaceVariant else Color(0xFF64748B)),
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ScheduleTimeSubTile(
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
private fun DaysScheduleSubTileRow(scheduleDaysString: String, isBound: Boolean = true) {
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
            .height(64.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (!isBound) Color(0xFFE2E8F0) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        ),
        border = if (isBound) null else BorderStroke(1.dp, Color(0xFFCBD5E1))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 5.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
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
                        tint = if (isBound) MaterialTheme.colorScheme.primary else Color(0xFF64748B),
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        text = "WEEKLY SCHEDULE",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (isBound) MaterialTheme.colorScheme.onSurfaceVariant else Color(0xFF64748B),
                        fontSize = 11.sp,
                        letterSpacing = 0.5.sp
                    )
                }
                Surface(
                    shape = RoundedCornerShape(50),
                    color = if (isBound) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f) else Color(0xFFCBD5E1)
                ) {
                    Text(
                        text = scheduleDaysString,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (isBound) MaterialTheme.colorScheme.primary else Color(0xFF475569),
                        fontSize = 11.sp
                    )
                }
            }

            // Up to 7 sub-tiles for the days of the week
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                weekDays.forEach { (abbr, calDay, fullName) ->
                    val isToday = (calDay == todayCalendarDay)
                    val isInSchedule = remember(scheduleDaysString, abbr, fullName) {
                        isDayInSchedule(abbr, fullName, scheduleDaysString)
                    }

                    DaySubTile(
                        modifier = Modifier.weight(1f),
                        abbr = abbr,
                        isToday = isToday,
                        isInSchedule = isInSchedule,
                        isBound = isBound
                    )
                }
            }
        }
    }
}

@Composable
private fun DaySubTile(
    modifier: Modifier = Modifier,
    abbr: String,
    isToday: Boolean,
    isInSchedule: Boolean,
    isBound: Boolean = true
) {
    val backgroundColor = when {
        !isBound -> if (isInSchedule) Color(0xFFCBD5E1) else Color(0xFFF1F5F9)
        isToday -> MaterialTheme.colorScheme.primary
        isInSchedule -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
        else -> MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
    }

    val contentColor = when {
        !isBound -> if (isInSchedule) Color(0xFF334155) else Color(0xFF94A3B8)
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

        if (isToday) {
            // "TODAY" text removed as per request
        }
    }
}

private fun isDayInSchedule(abbr: String, fullName: String, daysString: String): Boolean {
    val upper = daysString.uppercase().trim()
    if (upper.isEmpty()) return false

    if (upper.contains("MON-FRI") || upper.contains("MON - FRI") || upper.contains("MONDAY TO FRIDAY") || upper.contains("MONDAY - FRIDAY")) {
        return abbr in listOf("MON", "TUE", "WED", "THU", "FRI")
    }
    if (upper.contains("MON-SAT") || upper.contains("MON - SAT") || upper.contains("MONDAY TO SATURDAY")) {
        return abbr in listOf("MON", "TUE", "WED", "THU", "FRI", "SAT")
    }
    if (upper.contains("DAILY") || upper.contains("EVERYDAY") || upper.contains("MON-SUN") || upper.contains("ALL DAYS")) {
        return true
    }

    if (upper.contains(abbr) || upper.contains(fullName.uppercase())) {
        return true
    }

    if (upper.contains("-")) {
        val parts = upper.split("-").map { it.trim() }
        if (parts.size == 2) {
            val dayOrder = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
            val startIdx = dayOrder.indexOfFirst { parts[0].startsWith(it) }
            val endIdx = dayOrder.indexOfFirst { parts[1].startsWith(it) }
            val currentIdx = dayOrder.indexOf(abbr)
            if (startIdx != -1 && endIdx != -1 && currentIdx != -1) {
                if (startIdx <= endIdx) {
                    if (currentIdx in startIdx..endIdx) return true
                } else {
                    if (currentIdx >= startIdx || currentIdx <= endIdx) return true
                }
            }
        }
    }

    if (abbr == "MON" && (upper.contains("M,") || upper.contains("M-") || upper.startsWith("M"))) return true
    if (abbr == "TUE" && (upper.contains("T,") || upper.contains("TU"))) return true
    if (abbr == "WED" && (upper.contains("W,") || upper.contains("W-"))) return true
    if (abbr == "THU" && (upper.contains("TH,") || upper.contains("TH"))) return true
    if (abbr == "FRI" && (upper.contains("F,") || upper.contains("F-"))) return true

    return false
}

private fun formatDisplayTime(raw: String): String {
    if (raw.isBlank()) return "--:--"
    val trimmed = raw.trim()
    if (trimmed.contains("AM", ignoreCase = true) || trimmed.contains("PM", ignoreCase = true)) {
        return trimmed
    }
    if (trimmed.length == 4 && trimmed.all { it.isDigit() }) {
        val hour = trimmed.substring(0, 2).toIntOrNull() ?: 0
        val min = trimmed.substring(2, 4)
        val ampm = if (hour >= 12) "PM" else "AM"
        val displayHour = if (hour % 12 == 0) 12 else hour % 12
        return String.format("%02d:%s %s", displayHour, min, ampm)
    }
    if (trimmed.contains(":")) {
        val parts = trimmed.split(":")
        if (parts.size >= 2) {
            val hour = parts[0].trim().toIntOrNull()
            val min = parts[1].trim().take(2)
            if (hour != null) {
                val ampm = if (hour >= 12) "PM" else "AM"
                val displayHour = if (hour % 12 == 0) 12 else hour % 12
                return String.format("%02d:%s %s", displayHour, min, ampm)
            }
        }
    }
    return trimmed
}

private fun parseTimeToMinutes(raw: String): Int {
    val trimmed = raw.trim().uppercase()
    if (trimmed.isEmpty()) return -1
    
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
fun BackAndForthMarqueeText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    fontWeight: FontWeight? = null,
    color: Color = Color.Unspecified
) {
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()

    BoxWithConstraints(
        modifier = modifier.clipToBounds()
    ) {
        val containerWidthPx = with(density) { maxWidth.toPx() }
        val textLayoutResult = remember(text, style, fontWeight, containerWidthPx) {
            textMeasurer.measure(
                text = AnnotatedString(text),
                style = style.copy(
                    fontWeight = fontWeight ?: style.fontWeight
                ),
                maxLines = 1
            )
        }
        val textWidthPx = textLayoutResult.size.width.toFloat()
        val overflow = (textWidthPx - containerWidthPx).coerceAtLeast(0f)

        val offsetX = remember { Animatable(0f) }

        LaunchedEffect(text, overflow) {
            if (overflow > 2f) {
                while (isActive) {
                    delay(1200)
                    val duration = (overflow * 25).toInt().coerceIn(1500, 6000)
                    offsetX.animateTo(
                        targetValue = -overflow,
                        animationSpec = tween(durationMillis = duration, easing = LinearEasing)
                    )
                    delay(1200)
                    offsetX.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = duration, easing = LinearEasing)
                    )
                }
            } else {
                offsetX.snapTo(0f)
            }
        }

        Text(
            text = text,
            style = style,
            fontWeight = fontWeight,
            color = color,
            maxLines = 1,
            softWrap = false,
            modifier = Modifier.offset { IntOffset(offsetX.value.roundToInt(), 0) }
        )
    }
}

@Composable
fun TeacherProfileView(
    teacher: com.example.data.db.TeacherEntity?,
    classroom: com.example.data.db.ClassroomEntity?,
    approvedStudentsCount: Int,
    bleConnectionState: TeacherBleManager.ConnectionState
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val memberSinceFormatted = remember(teacher?.createdAt) {
        if (teacher != null && teacher.createdAt > 0) {
            val sdf = java.text.SimpleDateFormat("MMMM dd, yyyy", java.util.Locale.getDefault())
            sdf.format(java.util.Date(teacher.createdAt))
        } else {
            "N/A"
        }
    }

    val sexFormatted = remember(teacher?.sex) {
        when (teacher?.sex?.uppercase()) {
            "M", "MALE", "BOY" -> "Male"
            "F", "FEMALE", "GIRL" -> "Female"
            else -> teacher?.sex ?: "N/A"
        }
    }

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
                                TeacherBleManager.isDebugConsoleVisible.value = true
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
                        text = teacher?.fullName?.ifBlank { "Teacher Account" } ?: "Teacher Account",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = "TID: ${teacher?.tid ?: "N/A"}",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
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
                        text = "Account Information",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                ProfileDetailItemRow(label = "Full Name", value = teacher?.fullName ?: "N/A")
                ProfileDetailItemRow(label = "Teacher ID (TID)", value = teacher?.tid ?: "N/A")
                ProfileDetailItemRow(label = "Gender / Sex", value = sexFormatted)
                ProfileDetailItemRow(label = "Role", value = "Classroom Teacher / Administrator")
                ProfileDetailItemRow(label = "Member Since", value = memberSinceFormatted)
            }
        }

        // 2. ACTIVE CLASSROOM INFORMATION CARD
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
                        text = "Classroom Information",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                ProfileDetailItemRow(label = "Classroom Name", value = classroom?.classroomName ?: "No classroom active")
                ProfileDetailItemRow(label = "Classroom ID (CID)", value = classroom?.cid ?: "N/A")
                ProfileDetailItemRow(label = "Weekly Schedule", value = classroom?.days ?: "N/A")
                ProfileDetailItemRow(label = "Start Time", value = formatDisplayTime(classroom?.startTime ?: ""))
                ProfileDetailItemRow(label = "Grace Period", value = formatDisplayTime(classroom?.graceTime ?: ""))
                ProfileDetailItemRow(label = "Dismissal Time", value = formatDisplayTime(classroom?.endTime ?: ""))
                ProfileDetailItemRow(label = "Enrolled Students", value = "$approvedStudentsCount student(s)")
                ProfileDetailItemRow(label = "Session Status", value = if (classroom?.isActiveSession == true) "Active Session in progress" else "Standby")
            }
        }

        // 3. HARDWARE & DEVICE STATUS CARD
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
                        imageVector = Icons.Default.Devices,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Device & Hardware Link",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                ProfileDetailItemRow(
                    label = "Binding Status",
                    value = if (classroom?.isBound == true) "Paired & Bound" else "Not Bound"
                )
                ProfileDetailItemRow(
                    label = "Hardware Address",
                    value = classroom?.deviceAddress?.ifBlank { "None" } ?: "None"
                )
                ProfileDetailItemRow(
                    label = "Bluetooth State",
                    value = when (bleConnectionState) {
                        is TeacherBleManager.ConnectionState.Connected -> "Connected"
                        is TeacherBleManager.ConnectionState.Connecting -> "Connecting..."
                        is TeacherBleManager.ConnectionState.Scanning -> "Scanning..."
                        is TeacherBleManager.ConnectionState.Disconnected -> "Disconnected"
                        else -> "Disconnected"
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun ProfileDetailItemRow(label: String, value: String) {
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

@Composable
fun Esp32MicrochipIcon(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.primary
) {
    Canvas(modifier = modifier.size(24.dp)) {
        val width = size.width
        val height = size.height

        // Board dimensions (horizontal rectangle centered)
        val boardWidth = width * 0.85f
        val boardHeight = height * 0.55f
        val boardX = (width - boardWidth) / 2f
        val boardY = (height - boardHeight) / 2f

        // Draw outer board outline
        drawRoundRect(
            color = tint,
            topLeft = Offset(boardX, boardY),
            size = Size(boardWidth, boardHeight),
            cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx()),
            style = Stroke(width = 1.5.dp.toPx())
        )

        // Draw silver chip module on the right side
        val chipWidth = boardWidth * 0.42f
        val chipHeight = boardHeight * 0.72f
        val chipX = boardX + boardWidth - chipWidth - 2.dp.toPx()
        val chipY = boardY + (boardHeight - chipHeight) / 2f
        drawRoundRect(
            color = tint,
            topLeft = Offset(chipX, chipY),
            size = Size(chipWidth, chipHeight),
            cornerRadius = CornerRadius(1.dp.toPx(), 1.dp.toPx()),
            style = Stroke(width = 1.2.dp.toPx())
        )

        // Draw small USB port on the left edge
        val portWidth = boardWidth * 0.15f
        val portHeight = boardHeight * 0.42f
        val portX = boardX - portWidth / 2f
        val portY = boardY + (boardHeight - portHeight) / 2f
        drawRoundRect(
            color = tint,
            topLeft = Offset(portX, portY),
            size = Size(portWidth, portHeight),
            cornerRadius = CornerRadius(1.dp.toPx(), 1.dp.toPx())
        )

        // Draw pin header lines on top and bottom edges
        val pinCount = 6
        val pinSpacing = boardWidth / (pinCount + 1)
        for (i in 1..pinCount) {
            val pinX = boardX + i * pinSpacing
            // Top pins
            drawLine(
                color = tint,
                start = Offset(pinX, boardY - 1.0.dp.toPx()),
                end = Offset(pinX, boardY + 1.2.dp.toPx()),
                strokeWidth = 1.0.dp.toPx()
            )
            // Bottom pins
            drawLine(
                color = tint,
                start = Offset(pinX, boardY + boardHeight - 1.2.dp.toPx()),
                end = Offset(pinX, boardY + boardHeight + 1.0.dp.toPx()),
                strokeWidth = 1.0.dp.toPx()
            )
        }
    }
}

