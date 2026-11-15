package com.example.ui.screens

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.StatusAbsentRed
import com.example.ui.viewmodel.AuthViewModel
import com.example.util.BlePermissionUtils

@Composable
fun SettingsScreen(
    authViewModel: AuthViewModel,
    onNavigateToProfile: () -> Unit,
    onLoggedOut: () -> Unit,
    onAccountDeleted: () -> Unit,
    currentSubpageExternal: String? = null,
    onSubpageChangeExternal: ((String?) -> Unit)? = null,
    isCalisDeviceRegistered: Boolean = false
) {
    val context = LocalContext.current
    val saveLogin by authViewModel.saveLogin.collectAsState()
    val enableBiometrics by authViewModel.enableBiometrics.collectAsState()
    val autoStartSession by authViewModel.autoStartSession.collectAsState()
    val autoConnectOnStartup by authViewModel.autoConnectOnStartup.collectAsState()

    val remindStartSession by authViewModel.remindStartSession.collectAsState()
    val remindStartSessionMinutes by authViewModel.remindStartSessionMinutes.collectAsState()
    val remindTaskDeadline by authViewModel.remindTaskDeadline.collectAsState()
    val remindTaskDeadlineDays by authViewModel.remindTaskDeadlineDays.collectAsState()
    val remindTaskDeadlineTime by authViewModel.remindTaskDeadlineTime.collectAsState()
    val remindTaskDeadlineHour by authViewModel.remindTaskDeadlineHour.collectAsState()
    val remindTaskDeadlineMinute by authViewModel.remindTaskDeadlineMinute.collectAsState()
    val remindCheckIn by authViewModel.remindCheckIn.collectAsState()
    val remindCheckInMinutes by authViewModel.remindCheckInMinutes.collectAsState()

    var showMinutesDropdown by remember { mutableStateOf(false) }
    var showCustomMinutesDialog by remember { mutableStateOf(false) }
    var showDaysDropdown by remember { mutableStateOf(false) }
    var showCustomDaysDialog by remember { mutableStateOf(false) }
    var showTimeDropdown by remember { mutableStateOf(false) }
    var showCheckInMinutesDropdown by remember { mutableStateOf(false) }
    var showCustomCheckInMinutesDialog by remember { mutableStateOf(false) }

    val currentTeacher by authViewModel.currentTeacher.collectAsState()
    val currentStudentProfile by authViewModel.currentStudentProfile.collectAsState()
    val isTeacher = currentTeacher != null
    val isDeleteAccountDisabled = isTeacher && isCalisDeviceRegistered

    var localSubpage by remember { mutableStateOf<String?>(null) }
    val currentSubpage = currentSubpageExternal ?: localSubpage
    val setCurrentSubpage: (String?) -> Unit = { sub ->
        if (onSubpageChangeExternal != null) {
            onSubpageChangeExternal(sub)
        } else {
            localSubpage = sub
        }
    }

    androidx.activity.compose.BackHandler(enabled = currentSubpage != null) {
        setCurrentSubpage(null)
    }

    val supportsBiometrics = remember(context) {
        val biometricManager = androidx.biometric.BiometricManager.from(context)
        val authenticators = androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG or androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
        val canAuth = biometricManager.canAuthenticate(authenticators)
        canAuth == androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS ||
                canAuth == androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ||
                context.packageManager.hasSystemFeature(android.content.pm.PackageManager.FEATURE_FINGERPRINT) ||
                context.packageManager.hasSystemFeature("android.hardware.biometrics.face") ||
                context.packageManager.hasSystemFeature("android.hardware.biometrics.iris")
    }

    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }

    val notificationPermissionLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            if (currentTeacher != null) {
                authViewModel.setRemindStartSession(true)
                com.example.util.ReminderNotificationHelper.checkAndPostSessionReminder(context)
            } else if (currentStudentProfile != null) {
                authViewModel.setRemindTaskDeadline(true)
                com.example.util.ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(context)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (currentSubpage == null) {
            // MAIN SETTINGS MENU
            // 1. My Profile Button
            SettingsMenuItem(
                icon = Icons.Default.Person,
                title = "My Profile",
                subtitle = "View your personal details and account ID",
                onClick = onNavigateToProfile,
                testTag = "menu_item_profile"
            )

            // 2. Security & Startup Button
            SettingsMenuItem(
                icon = Icons.Default.Lock,
                title = "Security & Startup",
                subtitle = "Configure biometrics and login preference",
                onClick = { setCurrentSubpage("SECURITY") },
                testTag = "menu_item_security"
            )

            // 3. Notifications Button (For TC or ST account)
            SettingsMenuItem(
                icon = Icons.Default.Notifications,
                title = "Notifications",
                subtitle = if (currentTeacher != null) "Configure session start reminders" else "Configure check-in and task reminders",
                onClick = { setCurrentSubpage("NOTIFICATIONS") },
                testTag = "menu_item_notifications"
            )

            // 4. Calis Device Button (For TC account only)
            if (currentTeacher != null) {
                SettingsMenuItem(
                    icon = Icons.Default.Settings,
                    title = "Calis Device",
                    subtitle = "Automate session behavior and device features",
                    onClick = { setCurrentSubpage("CALIS_DEVICE") },
                    testTag = "menu_item_calis_device"
                )
            }

            // 4. Account Actions Button
            SettingsMenuItem(
                icon = Icons.Default.Warning,
                title = "Account Actions",
                subtitle = "Permanently delete your account",
                onClick = { setCurrentSubpage("ACTIONS") },
                testTag = "menu_item_actions"
            )

        } else {
            // SUBPAGES VIEW (Header and back button are handled in TopBar)
            when (currentSubpage) {
                "SECURITY" -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Save Login Toggle
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { authViewModel.setSaveLogin(!saveLogin) },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Save Login",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Bypass PIN/Biometrics authentication on app launch",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Switch(
                                    checked = saveLogin,
                                    onCheckedChange = null,
                                    modifier = Modifier.testTag("save_login_switch")
                                )
                            }

                            if (supportsBiometrics) {
                                Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                                // Enable Biometrics Toggle
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { authViewModel.setEnableBiometrics(!enableBiometrics) },
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Enable Biometrics",
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Use fingerprint or face unlock on login page",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Switch(
                                        checked = enableBiometrics,
                                        onCheckedChange = null,
                                        modifier = Modifier.testTag("enable_biometrics_switch")
                                    )
                                }
                            }

                        }
                    }
                }

                "CALIS_DEVICE" -> {
                    if (currentTeacher != null) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                // 1. Auto-Start Session Switch
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { authViewModel.setAutoStartSession(!autoStartSession) },
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Auto-Start Session",
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Automatically start classroom session as soon as the app is connected and authenticated by the TC account",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Switch(
                                        checked = autoStartSession,
                                        onCheckedChange = null,
                                        modifier = Modifier.testTag("auto_start_session_switch")
                                    )
                                }

                                Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                                // 2. Auto-Connect on Startup Switch
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { authViewModel.setAutoConnectOnStartup(!autoConnectOnStartup) },
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Auto-Connect on Startup",
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Automatically connect to the Calis Device on app startup once connection requirements are met",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Switch(
                                        checked = autoConnectOnStartup,
                                        onCheckedChange = null,
                                        modifier = Modifier.testTag("auto_connect_on_startup_switch")
                                    )
                                }
                            }
                        }
                    }
                }

                "NOTIFICATIONS" -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            if (currentTeacher != null) {
                                // 1. 'Remind Me to Start Session' Switch
                                val toggleRemindStartSession = {
                                    val enabled = !remindStartSession
                                    if (enabled && !BlePermissionUtils.hasNotificationPermission(context)) {
                                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                                            notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                                        } else {
                                            BlePermissionUtils.requestNotificationPermission(context)
                                        }
                                    }
                                    if (enabled && !com.example.util.ReminderNotificationHelper.hasExactAlarmPermission(context)) {
                                        android.widget.Toast.makeText(
                                            context,
                                            "Exact alarm permission is unavailable. Reminders will use standard scheduling.",
                                            android.widget.Toast.LENGTH_LONG
                                        ).show()
                                    }
                                    authViewModel.setRemindStartSession(enabled)
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { toggleRemindStartSession() },
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Remind Me to Start Session",
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Receive a system notification before session starts to open app and initiate session",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Switch(
                                        checked = remindStartSession,
                                        onCheckedChange = null,
                                        modifier = Modifier.testTag("remind_start_session_switch")
                                    )
                                }

                                // 2. 'Reminder time' option directly under it
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .alpha(if (remindStartSession) 1f else 0.4f),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Reminder time",
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.SemiBold,
                                            color = if (remindStartSession) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                                        )
                                        Text(
                                            text = "Minutes before session begins",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = if (remindStartSession) 1f else 0.4f)
                                        )
                                    }

                                    Box {
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = if (remindStartSession) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
                                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = if (remindStartSession) 0.5f else 0.2f)),
                                            modifier = Modifier
                                                .testTag("reminder_time_teacher_dropdown")
                                                .clickable(enabled = remindStartSession) {
                                                    showMinutesDropdown = true
                                                }
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                                            ) {
                                                val label = when (remindStartSessionMinutes) {
                                                    5 -> "5 minutes"
                                                    10 -> "10 minutes"
                                                    15 -> "15 minutes"
                                                    30 -> "30 minutes"
                                                    else -> "Custom ($remindStartSessionMinutes min)"
                                                }
                                                Text(
                                                    text = label,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    color = if (remindStartSession) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                                )
                                                Icon(
                                                    imageVector = Icons.Default.ArrowDropDown,
                                                    contentDescription = "Select minutes",
                                                    tint = if (remindStartSession) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                                )
                                            }
                                        }

                                        DropdownMenu(
                                            expanded = showMinutesDropdown && remindStartSession,
                                            onDismissRequest = { showMinutesDropdown = false }
                                        ) {
                                            listOf(5, 10, 15, 30).forEach { mins ->
                                                DropdownMenuItem(
                                                    text = { Text("$mins minutes") },
                                                    onClick = {
                                                        authViewModel.setRemindStartSessionMinutes(mins)
                                                        showMinutesDropdown = false
                                                    }
                                                )
                                            }
                                            DropdownMenuItem(
                                                text = { Text("Custom") },
                                                onClick = {
                                                    showMinutesDropdown = false
                                                    showCustomMinutesDialog = true
                                                }
                                            )
                                        }
                                    }
                                }
                            } else if (currentStudentProfile != null) {
                                // 1. 'Remind Me of Task\'s Deadline' Switch
                                val toggleRemindTaskDeadline = {
                                    val enabled = !remindTaskDeadline
                                    if (enabled && !BlePermissionUtils.hasNotificationPermission(context)) {
                                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                                            notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                                        } else {
                                            BlePermissionUtils.requestNotificationPermission(context)
                                        }
                                    }
                                    authViewModel.setRemindTaskDeadline(enabled)
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { toggleRemindTaskDeadline() },
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Remind Me of Task's Deadline",
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Receive notifications for upcoming task deadlines",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Switch(
                                        checked = remindTaskDeadline,
                                        onCheckedChange = null,
                                        modifier = Modifier.testTag("remind_task_deadline_switch")
                                    )
                                }

                                // 2. 'Reminder days' option
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .alpha(if (remindTaskDeadline) 1f else 0.4f),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Reminder days",
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.SemiBold,
                                            color = if (remindTaskDeadline) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                                        )
                                        Text(
                                            text = "Days before deadline",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = if (remindTaskDeadline) 1f else 0.4f)
                                        )
                                    }

                                    Box {
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = if (remindTaskDeadline) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
                                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = if (remindTaskDeadline) 0.5f else 0.2f)),
                                            modifier = Modifier
                                                .testTag("reminder_time_student_dropdown")
                                                .clickable(enabled = remindTaskDeadline) {
                                                    showDaysDropdown = true
                                                }
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                                            ) {
                                                val label = when (remindTaskDeadlineDays) {
                                                    1 -> "1 day"
                                                    2 -> "2 days"
                                                    3 -> "3 days"
                                                    else -> "Custom ($remindTaskDeadlineDays days)"
                                                }
                                                Text(
                                                    text = label,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    color = if (remindTaskDeadline) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                                )
                                                Icon(
                                                    imageVector = Icons.Default.ArrowDropDown,
                                                    contentDescription = "Select days",
                                                    tint = if (remindTaskDeadline) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                                )
                                            }
                                        }

                                        DropdownMenu(
                                            expanded = showDaysDropdown && remindTaskDeadline,
                                            onDismissRequest = { showDaysDropdown = false }
                                        ) {
                                            listOf(1, 2, 3).forEach { d ->
                                                DropdownMenuItem(
                                                    text = { Text("$d day${if (d > 1) "s" else ""}") },
                                                    onClick = {
                                                        authViewModel.setRemindTaskDeadlineDays(d)
                                                        showDaysDropdown = false
                                                    }
                                                )
                                            }
                                            DropdownMenuItem(
                                                text = { Text("Custom") },
                                                onClick = {
                                                    showDaysDropdown = false
                                                    showCustomDaysDialog = true
                                                }
                                            )
                                        }
                                    }
                                }

                                Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

                                // 3. 'Reminder time' option (Time when to be reminded)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .alpha(if (remindTaskDeadline) 1f else 0.4f),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Reminder time",
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.SemiBold,
                                            color = if (remindTaskDeadline) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                                        )
                                        Text(
                                            text = "Time when to be reminded",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = if (remindTaskDeadline) 1f else 0.4f)
                                        )
                                    }

                                    Box {
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = if (remindTaskDeadline) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
                                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = if (remindTaskDeadline) 0.5f else 0.2f)),
                                            modifier = Modifier
                                                .testTag("reminder_exact_time_student_dropdown")
                                                .clickable(enabled = remindTaskDeadline) {
                                                    showTimeDropdown = true
                                                }
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                                            ) {
                                                Text(
                                                    text = remindTaskDeadlineTime,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    color = if (remindTaskDeadline) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                                )
                                                Icon(
                                                    imageVector = Icons.Default.ArrowDropDown,
                                                    contentDescription = "Select time",
                                                    tint = if (remindTaskDeadline) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                                )
                                            }
                                        }

                                        DropdownMenu(
                                            expanded = showTimeDropdown && remindTaskDeadline,
                                            onDismissRequest = { showTimeDropdown = false }
                                        ) {
                                            listOf(
                                                "8:00 AM" to (8 to 0),
                                                "9:00 AM" to (9 to 0),
                                                "12:00 PM" to (12 to 0),
                                                "6:00 PM" to (18 to 0),
                                                "8:00 PM" to (20 to 0)
                                            ).forEach { (label, timePair) ->
                                                DropdownMenuItem(
                                                    text = { Text(label) },
                                                    onClick = {
                                                        authViewModel.setRemindTaskDeadlineTime(timePair.first, timePair.second)
                                                        showTimeDropdown = false
                                                    }
                                                )
                                            }
                                            DropdownMenuItem(
                                                text = { Text("Custom Time...") },
                                                onClick = {
                                                    showTimeDropdown = false
                                                    android.app.TimePickerDialog(
                                                        context,
                                                        { _, selectedHour, selectedMinute ->
                                                            authViewModel.setRemindTaskDeadlineTime(selectedHour, selectedMinute)
                                                        },
                                                        remindTaskDeadlineHour,
                                                        remindTaskDeadlineMinute,
                                                        false
                                                    ).show()
                                                }
                                            )
                                        }
                                    }
                                }

                                Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))

                                // 4. 'Remind Me to Check-In' Switch & Minutes option
                                val toggleRemindCheckIn = {
                                    val enabled = !remindCheckIn
                                    if (enabled && !BlePermissionUtils.hasNotificationPermission(context)) {
                                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                                            notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                                        } else {
                                            BlePermissionUtils.requestNotificationPermission(context)
                                        }
                                    }
                                    authViewModel.setRemindCheckIn(enabled)
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { toggleRemindCheckIn() },
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Remind Me to Check-In",
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Remind to check-in per day even beyond start time, with late warning after grace period",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Switch(
                                        checked = remindCheckIn,
                                        onCheckedChange = null,
                                        modifier = Modifier.testTag("remind_check_in_switch")
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .alpha(if (remindCheckIn) 1f else 0.4f),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Check-in reminder delay",
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.SemiBold,
                                            color = if (remindCheckIn) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                                        )
                                        Text(
                                            text = "Minutes after start time",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = if (remindCheckIn) 1f else 0.4f)
                                        )
                                    }

                                    Box {
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = if (remindCheckIn) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
                                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = if (remindCheckIn) 0.5f else 0.2f)),
                                            modifier = Modifier
                                                .testTag("reminder_check_in_minutes_dropdown")
                                                .clickable(enabled = remindCheckIn) {
                                                    showCheckInMinutesDropdown = true
                                                }
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                                            ) {
                                                val label = when (remindCheckInMinutes) {
                                                    5 -> "5 minutes"
                                                    10 -> "10 minutes"
                                                    15 -> "15 minutes"
                                                    30 -> "30 minutes"
                                                    else -> "Custom ($remindCheckInMinutes min)"
                                                }
                                                Text(
                                                    text = label,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    color = if (remindCheckIn) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                                )
                                                Icon(
                                                    imageVector = Icons.Default.ArrowDropDown,
                                                    contentDescription = "Select minutes",
                                                    tint = if (remindCheckIn) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                                )
                                            }
                                        }

                                        DropdownMenu(
                                            expanded = showCheckInMinutesDropdown && remindCheckIn,
                                            onDismissRequest = { showCheckInMinutesDropdown = false }
                                        ) {
                                            listOf(5, 10, 15, 30).forEach { mins ->
                                                DropdownMenuItem(
                                                    text = { Text("$mins minutes") },
                                                    onClick = {
                                                        authViewModel.setRemindCheckInMinutes(mins)
                                                        showCheckInMinutesDropdown = false
                                                    }
                                                )
                                            }
                                            DropdownMenuItem(
                                                text = { Text("Custom") },
                                                onClick = {
                                                    showCheckInMinutesDropdown = false
                                                    showCustomCheckInMinutesDialog = true
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                "ACTIONS" -> {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Delete Account Button (Disabled only if classroom is registered to a CD)
                        Button(
                            onClick = {
                                if (!isDeleteAccountDisabled) {
                                    showDeleteConfirmationDialog = true
                                }
                            },
                            enabled = !isDeleteAccountDisabled,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("delete_account_button"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = StatusAbsentRed,
                                contentColor = MaterialTheme.colorScheme.onError,
                                disabledContainerColor = StatusAbsentRed.copy(alpha = 0.35f),
                                disabledContentColor = MaterialTheme.colorScheme.onError.copy(alpha = 0.6f)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Account",
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Delete Account",
                                fontWeight = FontWeight.Bold
                            )
                        }

                        if (isTeacher && isCalisDeviceRegistered) {
                            Row(
                                modifier = Modifier.padding(top = 4.dp, start = 4.dp, end = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "Unregister Calis Device first",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.85f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Delete Account Confirmation Warning Dialog
    if (showDeleteConfirmationDialog && !isDeleteAccountDisabled) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmationDialog = false },
            tonalElevation = 0.dp,
            icon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Warning",
                    tint = StatusAbsentRed,
                    modifier = Modifier.size(36.dp)
                )
            },
            title = {
                Text(
                    text = "Delete Account & Classroom Data?",
                    fontWeight = FontWeight.Bold,
                    color = StatusAbsentRed
                )
            },
            text = {
                Text(
                    text = "Warning: Confirming will permanently erase your account profile and ALL stored data in the classroom (schedules, tasks, attendance records, and student masterlists). This action cannot be undone.",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDeleteConfirmationDialog = false
                        authViewModel.deleteAccountAndAllData {
                            onAccountDeleted()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = StatusAbsentRed
                    ),
                    modifier = Modifier.testTag("confirm_delete_account_button")
                ) {
                    Text("Delete Everything", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmationDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showCustomMinutesDialog) {
        var customInput by remember { mutableStateOf(remindStartSessionMinutes.toString()) }
        AlertDialog(
            onDismissRequest = { showCustomMinutesDialog = false },
            title = { Text("Custom Reminder Time", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Enter minutes before session starts (1–120):", style = MaterialTheme.typography.bodyMedium)
                    OutlinedTextField(
                        value = customInput,
                        onValueChange = { customInput = it.filter { char -> char.isDigit() } },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("custom_reminder_minutes_input")
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val parsed = customInput.toIntOrNull()?.coerceIn(1, 120) ?: 15
                        authViewModel.setRemindStartSessionMinutes(parsed)
                        showCustomMinutesDialog = false
                    },
                    modifier = Modifier.testTag("custom_reminder_minutes_save")
                ) {
                    Text("Save", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showCustomMinutesDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showCustomDaysDialog) {
        var customInput by remember { mutableStateOf(remindTaskDeadlineDays.toString()) }
        AlertDialog(
            onDismissRequest = { showCustomDaysDialog = false },
            title = { Text("Custom Reminder Time", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Enter days before deadline (1–30):", style = MaterialTheme.typography.bodyMedium)
                    OutlinedTextField(
                        value = customInput,
                        onValueChange = { customInput = it.filter { char -> char.isDigit() } },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("custom_reminder_days_input")
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val parsed = customInput.toIntOrNull()?.coerceIn(1, 30) ?: 1
                        authViewModel.setRemindTaskDeadlineDays(parsed)
                        showCustomDaysDialog = false
                    },
                    modifier = Modifier.testTag("custom_reminder_days_save")
                ) {
                    Text("Save", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showCustomDaysDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showCustomCheckInMinutesDialog) {
        var customInput by remember { mutableStateOf(remindCheckInMinutes.toString()) }
        AlertDialog(
            onDismissRequest = { showCustomCheckInMinutesDialog = false },
            title = { Text("Custom Check-In Reminder Time", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Enter minutes after start time (1–120):", style = MaterialTheme.typography.bodyMedium)
                    OutlinedTextField(
                        value = customInput,
                        onValueChange = { customInput = it.filter { char -> char.isDigit() } },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("custom_reminder_check_in_input")
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val parsed = customInput.toIntOrNull()?.coerceIn(1, 120) ?: 15
                        authViewModel.setRemindCheckInMinutes(parsed)
                        showCustomCheckInMinutesDialog = false
                    },
                    modifier = Modifier.testTag("custom_reminder_check_in_save")
                ) {
                    Text("Save", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showCustomCheckInMinutesDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun SettingsMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    testTag: String = ""
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .testTag(testTag),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(40.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (subtitle.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun ProfileRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
