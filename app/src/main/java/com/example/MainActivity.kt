package com.example

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.compose.runtime.LaunchedEffect
import com.example.ui.screens.AuthScreen
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.StartupLoadingScreen
import com.example.ui.screens.StudentMainScreen
import com.example.ui.screens.TeacherMainScreen
import com.example.ui.theme.AppThemeMode
import com.example.ui.theme.CalisMobileTheme
import com.example.ui.theme.ThemeConfig
import com.example.ui.viewmodel.AuthViewModel
import com.example.ui.viewmodel.StudentViewModel
import com.example.ui.viewmodel.TeacherViewModel

import androidx.core.app.ActivityCompat

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.fragment.app.FragmentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.ui.platform.LocalContext
import com.example.ble.TeacherBleManager
import com.example.ble.CalisBleService

class MainActivity : FragmentActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    private val teacherViewModel: TeacherViewModel by viewModels()
    private val studentViewModel: StudentViewModel by viewModels()

    override fun attachBaseContext(newBase: Context) {
        val config = Configuration(newBase.resources.configuration)
        if (config.fontScale > 1.0f) {
            config.fontScale = 1.0f
        }
        val metrics = newBase.resources.displayMetrics
        val minDimensionPx = minOf(metrics.widthPixels, metrics.heightPixels)
        val maxAllowedDpi = ((minDimensionPx / 380f) * 160f).toInt()
        if (maxAllowedDpi > 0 && config.densityDpi > maxAllowedDpi) {
            config.densityDpi = maxAllowedDpi
        }
        val context = newBase.createConfigurationContext(config)
        super.attachBaseContext(context)
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            if (overrideConfiguration.fontScale > 1.0f) {
                overrideConfiguration.fontScale = 1.0f
            }
            val metrics = resources.displayMetrics
            val minDimensionPx = minOf(metrics.widthPixels, metrics.heightPixels)
            val maxAllowedDpi = ((minDimensionPx / 380f) * 160f).toInt()
            if (maxAllowedDpi > 0 && overrideConfiguration.densityDpi > maxAllowedDpi) {
                overrideConfiguration.densityDpi = maxAllowedDpi
            }
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        com.example.util.BlePermissionUtils.appContext = applicationContext
        
        // Dynamically allow landscape rotation only for tablets (smallest width >= 600dp)
        val isTablet = resources.configuration.smallestScreenWidthDp >= 600
        if (isTablet) {
            requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        } else {
            requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.decorView.isForceDarkAllowed = false
        }
        enableEdgeToEdge()

        android.util.Log.d("MainActivity", "[DIAGNOSTIC] CalisBleService is now managed by TeacherViewModel")

        handleNotificationIntent(intent)

        setContent {
            val themeMode = ThemeConfig.themeMode
            val isDarkTheme = when (themeMode) {
                AppThemeMode.LIGHT -> false
                AppThemeMode.DARK -> true
                AppThemeMode.SYSTEM -> androidx.compose.foundation.isSystemInDarkTheme()
            }

            CalisMobileTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CalisAppNavigation(
                            authViewModel = authViewModel,
                            teacherViewModel = teacherViewModel,
                            studentViewModel = studentViewModel
                        )
                        com.example.ui.components.DebugConsolePopup()
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: android.content.Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleNotificationIntent(intent)
    }

    private fun handleNotificationIntent(intent: android.content.Intent?) {
        if (intent == null) return
        val navigateTarget = intent.getStringExtra("EXTRA_NAVIGATE_TARGET")
        val openTaskId = intent.getStringExtra("EXTRA_OPEN_TASK_ID")
        if (navigateTarget == "TASK_SUBMISSION" || !openTaskId.isNullOrEmpty()) {
            val targetId = if (!openTaskId.isNullOrEmpty()) openTaskId else "GENERAL_TASKS"
            teacherViewModel.setPendingNavigationTaskId(targetId)
            intent.removeExtra("EXTRA_NAVIGATE_TARGET")
            intent.removeExtra("EXTRA_OPEN_TASK_ID")
        }
    }

    override fun onResume() {
        super.onResume()
        // When resuming (e.g. after user changes Date & Time in phone settings or grants permissions),
        // re-evaluate and trigger any due session or task reminders immediately
        val prefs = getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val role = prefs.getString("user_role", null)
        val lastSeenDay = prefs.getString("last_seen_day_str", null)
        val currentDay = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
        if (lastSeenDay != null && lastSeenDay != currentDay) {
            com.example.util.ReminderNotificationHelper.clearTaskReminderKeys(applicationContext)
            com.example.util.ReminderNotificationHelper.clearSessionReminderKeys(applicationContext)
            com.example.util.ReminderNotificationHelper.clearCheckInReminderKeys(applicationContext)
        }
        prefs.edit().putString("last_seen_day_str", currentDay).apply()

        lifecycleScope.launch {
            if (role == "TEACHER" && prefs.getBoolean("remind_start_session", true)) {
                com.example.util.ReminderNotificationHelper.checkAndPostSessionReminder(applicationContext)
            } else if (role == "STUDENT") {
                if (prefs.getBoolean("remind_check_in", true)) {
                    com.example.util.ReminderNotificationHelper.checkAndPostCheckInReminder(applicationContext)
                }
            }
            if (role == "STUDENT" && prefs.getBoolean("remind_task_deadline", true)) {
                com.example.util.ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(applicationContext)
            }
        }
    }
}

@Composable
fun CalisAppNavigation(
    authViewModel: AuthViewModel,
    teacherViewModel: TeacherViewModel,
    studentViewModel: StudentViewModel
) {
    val context = LocalContext.current
    val currentTeacher by authViewModel.currentTeacher.collectAsState()
    val currentStudentProfile by authViewModel.currentStudentProfile.collectAsState()
    val userRole by authViewModel.userRole.collectAsState()
    val saveLogin by authViewModel.saveLogin.collectAsState()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
    val isDbLoaded by authViewModel.isDbLoaded.collectAsState()
    val hasAccount by authViewModel.hasAccount.collectAsState()

    val isBleConnected = teacherViewModel.bleManager.connectionState.value is TeacherBleManager.ConnectionState.Connected
    val isBgServiceRunning = CalisBleService.isServiceRunning()
    val isAlreadyRunningInBackground = isBleConnected || isBgServiceRunning

    var activeScreen by remember {
        mutableStateOf(if (isAlreadyRunningInBackground) "TEACHER_MAIN" else "STARTUP")
    }
    var authScreenInitialStep by remember { mutableStateOf(0) }
    var startupStatusText by remember { mutableStateOf("Starting Calis...") }

    LaunchedEffect(activeScreen, userRole, isDbLoaded) {
        if (!isDbLoaded) return@LaunchedEffect

        val isServiceActive = CalisBleService.isServiceRunning()
        val isConnected = teacherViewModel.bleManager.connectionState.value is TeacherBleManager.ConnectionState.Connected

        // If currently connected or background service is running for Teacher, NEVER stop the service!
        if (isConnected || isServiceActive) {
            return@LaunchedEffect
        }

        if (userRole == "STUDENT" || activeScreen == "STUDENT_MAIN") {
            CalisBleService.stopService(context)
        } else if (userRole == "TEACHER") {
            // CalisBleService is managed by TeacherViewModel based on Classroom binding
        }
    }

    LaunchedEffect(Unit) {
        if (isAlreadyRunningInBackground) {
            // Already running in background with active connection/service.
            // Skip the startup splash delay and stay directly on TEACHER_MAIN!
            activeScreen = "TEACHER_MAIN"
            return@LaunchedEffect
        }

        val startTime = System.currentTimeMillis()
        startupStatusText = "Reading account credentials..."

        while (!authViewModel.isDbLoaded.value) {
            kotlinx.coroutines.delay(50)
        }

        val hasAcct = authViewModel.hasAccount.value
        val role = authViewModel.userRole.value

        if (!hasAcct) {
            val elapsed = System.currentTimeMillis() - startTime
            if (elapsed < 1400) {
                kotlinx.coroutines.delay(1400 - elapsed)
            }
            startupStatusText = "Welcome"
            kotlinx.coroutines.delay(300)
            authScreenInitialStep = 0
            activeScreen = "AUTH"
        } else {
            if (role == "TEACHER") {
                startupStatusText = "Loading teacher & classroom data..."
                val waitStart = System.currentTimeMillis()
                while (!teacherViewModel.isClassroomLoaded.value && (System.currentTimeMillis() - waitStart) < 2500) {
                    kotlinx.coroutines.delay(50)
                }
            } else {
                startupStatusText = "Loading student profile & classroom..."
                val waitStart = System.currentTimeMillis()
                while (!studentViewModel.isClassroomLoaded.value && (System.currentTimeMillis() - waitStart) < 2500) {
                    kotlinx.coroutines.delay(50)
                }
            }

            val elapsed = System.currentTimeMillis() - startTime
            if (elapsed < 1500) {
                kotlinx.coroutines.delay(1500 - elapsed)
            }

            startupStatusText = "Classroom ready"
            kotlinx.coroutines.delay(250)

            authScreenInitialStep = 4
            val shouldSaveLogin = authViewModel.saveLogin.value
            activeScreen = if (shouldSaveLogin) {
                if (role == "TEACHER") "TEACHER_MAIN" else "STUDENT_MAIN"
            } else {
                "LOGIN"
            }
        }
    }

    when (activeScreen) {
        "STARTUP" -> {
            StartupLoadingScreen(statusMessage = startupStatusText)
        }

        "AUTH" -> AuthScreen(
            authViewModel = authViewModel,
            initialStep = authScreenInitialStep,
            onTeacherSelected = { activeScreen = "TEACHER_MAIN" },
            onStudentSelected = { activeScreen = "STUDENT_MAIN" }
        )

        "LOGIN" -> LoginScreen(
            authViewModel = authViewModel,
            onLoginSuccess = {
                activeScreen = if (userRole == "TEACHER") "TEACHER_MAIN" else "STUDENT_MAIN"
            },
            onSwitchAccountRequested = {
                authViewModel.selectRole("")
                authScreenInitialStep = 4
                activeScreen = "AUTH"
            }
        )

        "TEACHER_MAIN" -> {
            androidx.compose.runtime.LaunchedEffect("teacher_reminders") {
                com.example.util.ReminderNotificationHelper.startPeriodicChecker(context)
                com.example.util.ReminderNotificationHelper.checkAndPostSessionReminder(context)
            }
            TeacherMainScreen(
                teacherViewModel = teacherViewModel,
                authViewModel = authViewModel,
                onLogOut = {
                    activeScreen = "LOGIN"
                },
                onAccountDeleted = {
                    context.getSharedPreferences("calis_app_prefs", android.content.Context.MODE_PRIVATE)
                        .edit()
                        .remove("has_seen_prototype_dialog_teacher")
                        .remove("show_prototype_dialog_teacher")
                        .apply()
                    teacherViewModel.resetAllState()
                    authScreenInitialStep = 0
                    activeScreen = "AUTH"
                }
            )
        }

        "STUDENT_MAIN" -> {
            androidx.compose.runtime.LaunchedEffect("student_reminders") {
                com.example.util.ReminderNotificationHelper.startPeriodicChecker(context)
                com.example.util.ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(context)
                com.example.util.ReminderNotificationHelper.checkAndPostCheckInReminder(context)
            }
            StudentMainScreen(
                studentViewModel = studentViewModel,
                authViewModel = authViewModel,
                onLogOut = {
                    activeScreen = "LOGIN"
                },
                onAccountDeleted = {
                    context.getSharedPreferences("calis_app_prefs", android.content.Context.MODE_PRIVATE)
                        .edit()
                        .remove("has_seen_prototype_dialog_student")
                        .remove("show_prototype_dialog_student")
                        .apply()
                    studentViewModel.resetAllState()
                    authScreenInitialStep = 0
                    activeScreen = "AUTH"
                }
            )
        }
    }
}
