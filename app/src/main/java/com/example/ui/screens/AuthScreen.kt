package com.example.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import com.example.ble.TeacherBleManager
import com.example.ble.BlePacket
import com.example.ble.DeviceState
import com.example.util.BlePermissionUtils
import com.example.util.IdGenerator
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.calisTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.Lifecycle
import androidx.core.app.ActivityCompat
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CalisMobileTheme
import com.example.ui.theme.StatusPresentGreen
import com.example.ui.theme.AccentGradient
import com.example.ui.theme.SecondaryAccentGradient
import androidx.compose.ui.graphics.Brush
import com.example.ui.viewmodel.AuthViewModel
import java.util.Calendar

/**
 * Filter input according to Philippine Statistics Authority (PSA) name rules:
 * Only standard Latin letters (A-Z, a-z), 'Ñ', 'ñ', space, hyphen '-', apostrophe ''', and period '.' are permitted.
 * Automatically capitalizes the 1st letter of each word (Title Case).
 */
fun formatPsaTitleCaseName(input: String): String {
    val filtered = input.filter { char ->
        (char in 'a'..'z') || (char in 'A'..'Z') || char == 'Ñ' || char == 'ñ' ||
                char == ' ' || char == '-' || char == '\'' || char == '.'
    }
    val words = filtered.split(" ")
    return words.joinToString(" ") { word ->
        if (word.isNotEmpty()) {
            word.substring(0, 1).uppercase() + word.substring(1)
        } else ""
    }
}

/**
 * Automatically capitalizes the first letter of each word in the section name.
 */
fun formatSectionName(input: String): String {
    val words = input.split(" ")
    return words.joinToString(" ") { word ->
        if (word.isNotEmpty()) {
            word.substring(0, 1).uppercase() + word.substring(1)
        } else ""
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AuthScreen(
    authViewModel: AuthViewModel,
    initialStep: Int = 0,
    onTeacherSelected: () -> Unit,
    onStudentSelected: () -> Unit
) {
    // Force light mode theme for setup pages throughout first time startup
    CalisMobileTheme(darkTheme = false) {
        val context = LocalContext.current

        // Steps:
        // 0 = Welcome
        // 1 = Capability 1 (Off-Grid BLE Synchronization)
        // 2 = Capability 2 (Automated Attendance & Check-In)
        // 3 = Capability 3 (Class Schedules & Task Management)
        // 4 = Choose Role (Teacher / Student tiles)
        // 5 = Details Registration Form
        // 6 = Teacher Honorific Selection (Teacher only)
        // 7 = 6-Digit PIN & Biometrics Setup
        // 8 = Classroom Creation (Grade & Section, Schedule)
        // 9 = Calis Hardware Setup
        val hasAccount by authViewModel.hasAccount.collectAsState()
        val startStep = remember(initialStep, hasAccount) {
            if (hasAccount && initialStep < 4) 4 else initialStep
        }
        var currentStep by remember(startStep) { mutableStateOf(startStep) }

        var hasRequiredPermission by remember(context) {
            mutableStateOf(BlePermissionUtils.hasPermissions(context))
        }

        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    hasRequiredPermission = BlePermissionUtils.hasPermissions(context)
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }

        val activity = remember(context) {
            var currentContext = context
            while (currentContext is android.content.ContextWrapper) {
                if (currentContext is android.app.Activity) break
                currentContext = currentContext.baseContext
            }
            currentContext as? android.app.Activity
        }

        val requiredPermissions = remember { BlePermissionUtils.getRequiredPermissions() }

        LaunchedEffect(hasAccount) {
            if (hasAccount && currentStep < 4) {
                currentStep = 4
            }
        }
        var selectedRole by remember { mutableStateOf<String?>(null) } // "TEACHER" or "STUDENT"

        // System back navigation (button / swipe gesture): navigate to previous setup page until welcome screen
        BackHandler(enabled = if (hasAccount) currentStep > 4 else currentStep > 0) {
            when (currentStep) {
                9 -> {
                    if (selectedRole == "TEACHER") {
                        currentStep = 8
                    } else {
                        currentStep = 7
                    }
                }
                8 -> currentStep = 7
                7 -> {
                    if (selectedRole == "TEACHER") {
                        currentStep = 6
                    } else {
                        currentStep = 5
                    }
                }
                6 -> currentStep = 5
                5 -> currentStep = 4
                4 -> currentStep = 3
                3 -> currentStep = 2
                2 -> currentStep = 1
                1 -> currentStep = 0
                else -> {}
            }
        }

        // Teacher fields
        var teacherFirstName by remember { mutableStateOf("") }
        var teacherMiddleName by remember { mutableStateOf("") }
        var teacherSurname by remember { mutableStateOf("") }
        var teacherSex by remember { mutableStateOf("M") }
        var teacherHonorific by remember { mutableStateOf("Sir") }

        // Student fields
        var studentFirstName by remember { mutableStateOf("") }
        var studentMiddleName by remember { mutableStateOf("") }
        var studentSurname by remember { mutableStateOf("") }
        var studentSex by remember { mutableStateOf("M") }
        var studentLrn by remember { mutableStateOf("") }

        // Birth Date setup (Wheel picker style: Month, Day, Year - max year = currentYear - 9)
        val currentYear = remember { Calendar.getInstance().get(Calendar.YEAR) }
        val maxBirthYear = remember { currentYear - 9 }
        var birthYear by remember { mutableStateOf(maxBirthYear - 8) } // e.g. 2009
        var birthMonthIndex by remember { mutableStateOf(0) } // 0 = Jan
        var birthDayIndex by remember { mutableStateOf(0) } // 0 = 01

        val monthNames = remember {
            listOf(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            )
        }

        val birthDateText = remember(birthYear, birthMonthIndex, birthDayIndex) {
            String.format("%04d-%02d-%02d", birthYear, birthMonthIndex + 1, birthDayIndex + 1)
        }

        // Check if device hardware supports biometrics
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

        // PIN, Biometrics, and Save Login
        var pinCode by remember { mutableStateOf("") }
        var enableBiometrics by remember(supportsBiometrics) { mutableStateOf(supportsBiometrics) }
        var saveLogin by remember { mutableStateOf(true) }

        // Teacher Classroom Creation Fields (Step 8)
        var teacherGrade by remember { mutableStateOf("11") }
        var teacherSection by remember { mutableStateOf("") }
        var scheduleDays by remember { mutableStateOf("MON-FRI") }
        var scheduleStartTime by remember { mutableStateOf("06:00 AM") }
        var scheduleGraceTime by remember { mutableStateOf("06:15 AM") }
        var scheduleEndTime by remember { mutableStateOf("07:00 AM") }

        // Generated teacher IDs
        var teacherTid by remember { mutableStateOf<String>(IdGenerator.generateTID()) }
        var teacherCid by remember { mutableStateOf<String>(IdGenerator.generateCID()) }

        fun buildFullName(first: String, middle: String, surname: String): String {
            val f = first.trim()
            val m = middle.trim()
            val s = surname.trim()
            return if (m.isNotBlank()) "$f $m $s" else "$f $s"
        }

        fun parseDateToEpoch(dateStr: String): Long {
            return try {
                val parts = dateStr.split("-")
                if (parts.size == 3) {
                    val yr = parts[0].toInt()
                    val mo = parts[1].toInt() - 1
                    val dy = parts[2].toInt()
                    val cal = Calendar.getInstance().apply {
                        set(yr, mo, dy, 0, 0, 0)
                        set(Calendar.MILLISECOND, 0)
                    }
                    cal.timeInMillis / 1000L
                } else {
                    System.currentTimeMillis() / 1000L - 500000000L
                }
            } catch (e: Exception) {
                System.currentTimeMillis() / 1000L - 500000000L
            }
        }

        Scaffold(
            containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                AnimatedContent(
                    targetState = currentStep,
                    transitionSpec = {
                        if (targetState > initialState) {
                            (slideInHorizontally(initialOffsetX = { width -> width }) + fadeIn()).togetherWith(
                                slideOutHorizontally(targetOffsetX = { width -> -width }) + fadeOut()
                            )
                        } else {
                            (slideInHorizontally(initialOffsetX = { width -> -width }) + fadeIn()).togetherWith(
                                slideOutHorizontally(targetOffsetX = { width -> width }) + fadeOut()
                            )
                        }
                    },
                    label = "SwipePageTransition"
                ) { step ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (step) {
                            0 -> WelcomePage(
                                onGetStarted = { currentStep = 1 }
                            )

                            1, 2, 3 -> CapabilityCarouselPage(
                                pageIndex = step,
                                onNext = { currentStep = step + 1 },
                                onBack = { currentStep = step - 1 },
                                onSkip = { currentStep = 4 }
                            )

                            4 -> ChooseRolePage(
                                selectedRole = selectedRole,
                                onSelectRole = { role ->
                                    selectedRole = role
                                    currentStep = 5
                                },
                                onBack = { currentStep = 3 }
                            )

                            5 -> FormPage(
                                selectedRole = selectedRole ?: "TEACHER",
                                // Teacher fields
                                teacherFirstName = teacherFirstName,
                                onTeacherFirstNameChange = { teacherFirstName = formatPsaTitleCaseName(it) },
                                teacherMiddleName = teacherMiddleName,
                                onTeacherMiddleNameChange = { teacherMiddleName = formatPsaTitleCaseName(it) },
                                teacherSurname = teacherSurname,
                                onTeacherSurnameChange = { teacherSurname = formatPsaTitleCaseName(it) },
                                teacherSex = teacherSex,
                                onTeacherSexChange = { teacherSex = it },
                                onNextTeacher = { currentStep = 6 },
                                // Student fields
                                studentFirstName = studentFirstName,
                                onStudentFirstNameChange = { studentFirstName = formatPsaTitleCaseName(it) },
                                studentMiddleName = studentMiddleName,
                                onStudentMiddleNameChange = { studentMiddleName = formatPsaTitleCaseName(it) },
                                studentSurname = studentSurname,
                                onStudentSurnameChange = { studentSurname = formatPsaTitleCaseName(it) },
                                studentLrn = studentLrn,
                                onStudentLrnChange = { studentLrn = it },
                                // Wheel date states
                                maxBirthYear = maxBirthYear,
                                birthYear = birthYear,
                                onBirthYearChange = { birthYear = it },
                                birthMonthIndex = birthMonthIndex,
                                onBirthMonthChange = { birthMonthIndex = it },
                                birthDayIndex = birthDayIndex,
                                onBirthDayChange = { birthDayIndex = it },
                                monthNames = monthNames,
                                studentSex = studentSex,
                                onStudentSexChange = { studentSex = it },
                                onNextStudent = { currentStep = 7 },
                                onBack = { currentStep = 4 }
                            )

                            6 -> TeacherHonorificPage(
                                firstName = teacherFirstName,
                                surname = teacherSurname,
                                sex = teacherSex,
                                selectedHonorific = teacherHonorific,
                                onHonorificSelected = { teacherHonorific = it },
                                onNext = { currentStep = 7 },
                                onBack = { currentStep = 5 }
                            )

                            7 -> PinSetupPage(
                                pinCode = pinCode,
                                onPinChange = { pinCode = it },
                                supportsBiometrics = supportsBiometrics,
                                enableBiometrics = enableBiometrics,
                                onToggleBiometrics = { enableBiometrics = it },
                                saveLogin = saveLogin,
                                onToggleSaveLogin = { saveLogin = it },
                                nextText = "Next",
                                onSubmit = {
                                    authViewModel.saveAuthCredentials(pinCode, enableBiometrics, saveLogin)
                                    if (selectedRole == "TEACHER") {
                                        currentStep = 8
                                    } else {
                                        currentStep = 9
                                    }
                                },
                                onBack = {
                                    if (selectedRole == "TEACHER") currentStep = 6 else currentStep = 5
                                }
                            )

                            8 -> ClassroomCreationPage(
                                teacherCid = teacherCid,
                                grade = teacherGrade,
                                onGradeChange = { if (it.length <= 2 && it.all { c -> c.isDigit() }) teacherGrade = it },
                                section = teacherSection,
                                onSectionChange = { if (it.length <= 25) teacherSection = formatSectionName(it) },
                                scheduleDays = scheduleDays,
                                onScheduleDaysChange = { scheduleDays = it },
                                scheduleStartTime = scheduleStartTime,
                                onScheduleStartTimeChange = { scheduleStartTime = it },
                                scheduleGraceTime = scheduleGraceTime,
                                onScheduleGraceTimeChange = { scheduleGraceTime = it },
                                scheduleEndTime = scheduleEndTime,
                                onScheduleEndTimeChange = { scheduleEndTime = it },
                                onSubmit = {
                                    currentStep = 9
                                },
                                onBack = { currentStep = 7 }
                            )

                            9 -> ConnectionRequirementsPage(
                                selectedRole = selectedRole ?: "STUDENT",
                                hasRequiredPermission = hasRequiredPermission,
                                onRequestPermissions = {
                                    activity?.let { act ->
                                        ActivityCompat.requestPermissions(act, requiredPermissions, 101)
                                    }
                                },
                                onBack = {
                                     if (selectedRole == "TEACHER") {
                                         currentStep = 8
                                     } else {
                                         currentStep = 7
                                     }
                                 },
                                 onFinish = {
                                     if (selectedRole == "TEACHER") {
                                         val rawName = buildFullName(teacherFirstName, teacherMiddleName, teacherSurname)
                                         val fullTeacherName = "$teacherHonorific $rawName"
                                         val classroomName = "$teacherGrade - $teacherSection"
                                         authViewModel.createTeacherProfileWithClassroom(
                                             fullName = fullTeacherName,
                                             sex = teacherSex,
                                             classroomName = classroomName,
                                             days = scheduleDays,
                                             startTime = scheduleStartTime,
                                             graceTime = scheduleGraceTime,
                                             endTime = scheduleEndTime,
                                             tid = teacherTid,
                                             cid = teacherCid,
                                             firstName = teacherFirstName,
                                             middleName = teacherMiddleName,
                                             surname = teacherSurname,
                                             honorific = teacherHonorific
                                         ) {
                                             context.getSharedPreferences("calis_app_prefs", android.content.Context.MODE_PRIVATE)
                                                 .edit()
                                                 .putBoolean("show_prototype_dialog_teacher", true)
                                                 .putBoolean("has_seen_prototype_dialog_teacher", false)
                                                 .apply()
                                             onTeacherSelected()
                                         }
                                     } else {
                                         val fullStudentName = buildFullName(studentFirstName, studentMiddleName, studentSurname)
                                         val epoch = parseDateToEpoch(birthDateText)
                                        if (studentFirstName.isNotBlank() || studentSurname.isNotBlank()) {
                                            context.getSharedPreferences("calis_auth_prefs", android.content.Context.MODE_PRIVATE)
                                                .edit()
                                                .putString("student_first_name", studentFirstName.trim())
                                                .putString("student_middle_name", studentMiddleName.trim())
                                                .putString("student_surname", studentSurname.trim())
                                                .apply()
                                        }
                                         authViewModel.createStudentProfile(
                                             fullName = fullStudentName,
                                             sex = studentSex,
                                             lrn = studentLrn,
                                             birthDate = epoch
                                         ) {
                                             context.getSharedPreferences("calis_app_prefs", android.content.Context.MODE_PRIVATE)
                                                 .edit()
                                                 .putBoolean("show_prototype_dialog_student", true)
                                                 .putBoolean("has_seen_prototype_dialog_student", false)
                                                 .apply()
                                             onStudentSelected()
                                         }
                                     }
                                 }
                             )
                        }
                    }
                }
            }
        }
    }
}

// -----------------------------------------------------------------------------------------
// REUSABLE WHEEL PICKER COMPOSABLE
// -----------------------------------------------------------------------------------------
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WheelPicker(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    visibleItemsCount: Int = 3,
    itemHeight: Dp = 44.dp
) {
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val initialIndex = remember(items, selectedItem) {
        val idx = items.indexOf(selectedItem)
        if (idx >= 0) idx else 0
    }

    LaunchedEffect(initialIndex) {
        listState.scrollToItem(initialIndex)
    }

    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val centerIndex = listState.firstVisibleItemIndex
            if (centerIndex in items.indices) {
                onItemSelected(items[centerIndex])
            }
        }
    }

    Box(
        modifier = modifier
            .height(itemHeight * visibleItemsCount),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            contentPadding = PaddingValues(vertical = itemHeight * ((visibleItemsCount - 1) / 2)),
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(items) { index, item ->
                val isSelected = item == selectedItem

                // Dynamically adjust font size based on itemHeight
                val selectedFontSize = (itemHeight.value * 0.45).sp
                val unselectedFontSize = (itemHeight.value * 0.35).sp

                Box(
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onItemSelected(item)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                        fontSize = if (isSelected) selectedFontSize else unselectedFontSize,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

// -----------------------------------------------------------------------------------------
// REUSABLE BOTTOM NAVIGATION BAR (Icon Back on Left, Smaller Next on Right)
// -----------------------------------------------------------------------------------------
@Composable
private fun BottomNavigationRow(
    onBack: (() -> Unit)?,
    onNext: (() -> Unit)?,
    onSkip: (() -> Unit)? = null,
    nextText: String = "Next",
    nextEnabled: Boolean = true,
    nextTag: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBack != null) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        } else {
            Spacer(modifier = Modifier.size(48.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (onSkip != null) {
                TextButton(
                    onClick = onSkip,
                    modifier = Modifier.height(48.dp)
                ) {
                    Text(
                        text = "Skip",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
            }

            if (onNext != null) {
                Button(
                    onClick = onNext,
                    enabled = nextEnabled,
                    modifier = Modifier
                        .widthIn(min = 135.dp)
                        .height(48.dp)
                        .testTag(nextTag),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = if (nextEnabled) AccentGradient else Brush.linearGradient(listOf(Color(0xFFE2E8F0), Color(0xFFE2E8F0))),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                nextText, 
                                fontWeight = FontWeight.Bold, 
                                fontSize = 15.sp, 
                                color = if (nextEnabled) Color.White else Color(0xFF94A3B8)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = if (nextEnabled) Color.White else Color(0xFF94A3B8)
                            )
                        }
                    }
                }
            } else {
                Spacer(modifier = Modifier.width(135.dp))
            }
        }
    }
}

// -----------------------------------------------------------------------------------------
// PAGE 0: WELCOME PAGE
// -----------------------------------------------------------------------------------------
@Composable
private fun WelcomePage(
    onGetStarted: () -> Unit
) {
    androidx.compose.foundation.layout.BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTablet = maxWidth > 600.dp
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(if (isTablet) 48.dp else 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // "Welcome" text moved higher
                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(36.dp))

                // Logo positioned in the dedicated space
                androidx.compose.foundation.Image(
                    painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.logo_calis_inverted),
                    contentDescription = "Calis Logo",
                    modifier = Modifier.size(if (isTablet) 240.dp else 180.dp)
                )

                Spacer(modifier = Modifier.height(36.dp))

                // Subtitle moved lower
                Text(
                    text = "Manage your class with Bluetooth",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }

            // Bottom Navigation
            BottomNavigationRow(
                onBack = null,
                onNext = onGetStarted,
                nextText = "Get Started",
                nextTag = "welcome_get_started_button"
            )
        }
    }
}

// -----------------------------------------------------------------------------------------
// CAPABILITY CAROUSEL PAGES (1, 2, 3) - Fixed Header & Buttons, Transitioning Center
// -----------------------------------------------------------------------------------------
private data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)

@Composable
private fun CapabilityCarouselPage(
    pageIndex: Int,
    onNext: () -> Unit,
    onBack: () -> Unit,
    onSkip: () -> Unit
) {
    val (icon, title, subtitle, description) = when (pageIndex) {
        1 -> Quadruple(
            Icons.Default.Bluetooth,
            "Offline Sync",
            "No internet required",
            "Connect directly to Calis devices over Bluetooth."
        )
        2 -> Quadruple(
            Icons.Default.CheckCircle,
            "Attendance Check-In",
            "Fast and wireless",
            "Verify and record student attendance instantly."
        )
        else -> Quadruple(
            Icons.Default.School,
            "Tasks & Schedules",
            "Offline workspace",
            "Manage schedules and share classroom tasks offline."
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(52.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
            ) {
                Text(
                    text = subtitle,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                ),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(20.dp)
                )
            }
        }

        // Step Indicator Dots
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            for (i in 1..3) {
                Box(
                    modifier = Modifier
                        .size(if (i == pageIndex) 12.dp else 8.dp)
                        .clip(CircleShape)
                        .background(
                            if (i == pageIndex) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.outlineVariant
                        )
                )
            }
        }

        BottomNavigationRow(
            onBack = onBack,
            onNext = onNext,
            onSkip = if (pageIndex == 3) null else onSkip,
            nextText = if (pageIndex == 3) "Setup" else "Next"
        )
    }
}

// -----------------------------------------------------------------------------------------
// PAGE 4: CHOOSE ROLE PAGE
// -----------------------------------------------------------------------------------------
@Composable
private fun ChooseRolePage(
    selectedRole: String?,
    onSelectRole: (String) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Centered Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Select Account Type",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Choose your role to get started.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            // Teacher Role Tile
            Card(
                onClick = { onSelectRole("TEACHER") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("role_teacher_button"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (selectedRole == "TEACHER")
                        MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = "Teacher Role",
                            modifier = Modifier.size(28.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Teacher",
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Manage classroom, schedules, tasks, and attendance.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Student Role Tile
            Card(
                onClick = { onSelectRole("STUDENT") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("role_student_button"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (selectedRole == "STUDENT")
                        MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Student Role",
                            modifier = Modifier.size(28.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Student",
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Join sessions, check in, and view tasks.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Bottom Navigation Bar
        BottomNavigationRow(
            onBack = onBack,
            onNext = null
        )
    }
}

// -----------------------------------------------------------------------------------------
// PAGE 5: FORM REGISTRATION PAGE (Centered layout, Auto focus navigation, Wheel Birth Date)
// -----------------------------------------------------------------------------------------
@Composable
private fun FormPage(
    keyboardController: androidx.compose.ui.platform.SoftwareKeyboardController? = androidx.compose.ui.platform.LocalSoftwareKeyboardController.current,
    selectedRole: String,
    // Teacher
    teacherFirstName: String,
    onTeacherFirstNameChange: (String) -> Unit,
    teacherMiddleName: String,
    onTeacherMiddleNameChange: (String) -> Unit,
    teacherSurname: String,
    onTeacherSurnameChange: (String) -> Unit,
    teacherSex: String,
    onTeacherSexChange: (String) -> Unit,
    onNextTeacher: () -> Unit,
    // Student
    studentFirstName: String,
    onStudentFirstNameChange: (String) -> Unit,
    studentMiddleName: String,
    onStudentMiddleNameChange: (String) -> Unit,
    studentSurname: String,
    onStudentSurnameChange: (String) -> Unit,
    studentLrn: String,
    onStudentLrnChange: (String) -> Unit,
    // Wheel birth date
    maxBirthYear: Int,
    birthYear: Int,
    onBirthYearChange: (Int) -> Unit,
    birthMonthIndex: Int,
    onBirthMonthChange: (Int) -> Unit,
    birthDayIndex: Int,
    onBirthDayChange: (Int) -> Unit,
    monthNames: List<String>,
    studentSex: String,
    onStudentSexChange: (String) -> Unit,
    onNextStudent: () -> Unit,
    onBack: () -> Unit
) {
    val focusFirst = remember { FocusRequester() }
    val focusMiddle = remember { FocusRequester() }
    val focusSurname = remember { FocusRequester() }
    val focusLrn = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val isTeacherValid = teacherFirstName.isNotBlank() && teacherSurname.isNotBlank()
    val isStudentValid = studentFirstName.isNotBlank() && studentSurname.isNotBlank() && studentLrn.length == 12

    val yearsList = remember(maxBirthYear) {
        (maxBirthYear downTo (maxBirthYear - 60)).map { it.toString() }
    }
    val daysList = remember {
        (1..31).map { String.format("%02d", it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Centered Content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                shape = RoundedCornerShape(50),
                color = if (selectedRole == "TEACHER") MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                else MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)
            ) {
                Text(
                    text = if (selectedRole == "TEACHER") "Teacher Registration" else "Student Registration",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = if (selectedRole == "TEACHER") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (selectedRole == "TEACHER") "Teacher Information" else "Student Information",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (selectedRole == "TEACHER") {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = teacherFirstName,
                        onValueChange = onTeacherFirstNameChange,
                        label = { Text("First Name *") },
                        colors = calisTextFieldColors(),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (teacherFirstName.isNotBlank()) {
                                    focusMiddle.requestFocus()
                                }
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusFirst)
                            .testTag("teacher_first_name_input"),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = teacherMiddleName,
                        onValueChange = onTeacherMiddleNameChange,
                        label = { Text("Middle Name (Optional)") },
                        colors = calisTextFieldColors(),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusSurname.requestFocus()
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusMiddle)
                            .testTag("teacher_middle_name_input"),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = teacherSurname,
                        onValueChange = onTeacherSurnameChange,
                        label = { Text("Surname / Last Name *") },
                        colors = calisTextFieldColors(),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusSurname)
                            .testTag("teacher_surname_input"),
                        singleLine = true
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth()
                    ) {
                        Text("Sex:", fontWeight = FontWeight.SemiBold)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.weight(1f)) {
                            Button(
                                onClick = { onTeacherSexChange("M") },
                                modifier = Modifier.weight(1f).testTag("teacher_sex_male_button"),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (teacherSex == "M") androidx.compose.ui.graphics.Color(0xFF1E88E5) else MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = if (teacherSex == "M") androidx.compose.ui.graphics.Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            ) {
                                Text("Male")
                            }
                            Button(
                                onClick = { onTeacherSexChange("F") },
                                modifier = Modifier.weight(1f).testTag("teacher_sex_female_button"),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (teacherSex == "F") androidx.compose.ui.graphics.Color(0xFFE91E63) else MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = if (teacherSex == "F") androidx.compose.ui.graphics.Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            ) {
                                Text("Female")
                            }
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = studentFirstName,
                        onValueChange = onStudentFirstNameChange,
                        label = { Text("First Name *") },
                        colors = calisTextFieldColors(),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (studentFirstName.isNotBlank()) {
                                    focusMiddle.requestFocus()
                                }
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusFirst)
                            .testTag("student_first_name_input"),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = studentMiddleName,
                        onValueChange = onStudentMiddleNameChange,
                        label = { Text("Middle Name (Optional)") },
                        colors = calisTextFieldColors(),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusSurname.requestFocus()
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusMiddle)
                            .testTag("student_middle_name_input"),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = studentSurname,
                        onValueChange = onStudentSurnameChange,
                        label = { Text("Surname / Last Name *") },
                        colors = calisTextFieldColors(),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusLrn.requestFocus()
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusSurname)
                            .testTag("student_surname_input"),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = studentLrn,
                        onValueChange = { newValue ->
                            if (newValue.length <= 12 && newValue.all { char -> char.isDigit() }) {
                                onStudentLrnChange(newValue)
                                if (newValue.length == 12) {
                                    keyboardController?.hide()
                                    focusManager.clearFocus()
                                }
                            }
                        },
                        label = { Text("12-Digit LRN *") },
                        colors = calisTextFieldColors(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hide()
                                focusManager.clearFocus()
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusLrn)
                            .testTag("student_lrn_input"),
                        singleLine = true
                    )

                    // Birth Date Wheel Pickers
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Date of Birth",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Month
                            WheelPicker(
                                items = monthNames,
                                selectedItem = monthNames.getOrElse(birthMonthIndex) { monthNames[0] },
                                onItemSelected = { selectedMonth ->
                                    val idx = monthNames.indexOf(selectedMonth)
                                    if (idx >= 0) onBirthMonthChange(idx)
                                },
                                modifier = Modifier.weight(1.3f)
                            )

                            // Day
                            WheelPicker(
                                items = daysList,
                                selectedItem = daysList.getOrElse(birthDayIndex) { daysList[0] },
                                onItemSelected = { selectedDayStr ->
                                    val idx = daysList.indexOf(selectedDayStr)
                                    if (idx >= 0) onBirthDayChange(idx)
                                },
                                modifier = Modifier.weight(0.8f)
                            )

                            // Year (Max year = currentYear - 9)
                            WheelPicker(
                                items = yearsList,
                                selectedItem = birthYear.toString(),
                                onItemSelected = { selectedYearStr ->
                                    val yr = selectedYearStr.toIntOrNull()
                                    if (yr != null) onBirthYearChange(yr)
                                },
                                modifier = Modifier.weight(0.9f)
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth()
                    ) {
                        Text("Sex:", fontWeight = FontWeight.SemiBold)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.weight(1f)) {
                            Button(
                                onClick = { onStudentSexChange("M") },
                                modifier = Modifier.weight(1f).testTag("student_sex_male_button"),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (studentSex == "M") androidx.compose.ui.graphics.Color(0xFF1E88E5) else MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = if (studentSex == "M") androidx.compose.ui.graphics.Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            ) {
                                Text("Male")
                            }
                            Button(
                                onClick = { onStudentSexChange("F") },
                                modifier = Modifier.weight(1f).testTag("student_sex_female_button"),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (studentSex == "F") androidx.compose.ui.graphics.Color(0xFFE91E63) else MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = if (studentSex == "F") androidx.compose.ui.graphics.Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            ) {
                                Text("Female")
                            }
                        }
                    }
                }
            }
        }

        // Bottom Navigation Bar
        BottomNavigationRow(
            onBack = onBack,
            onNext = {
                if (selectedRole == "TEACHER") onNextTeacher() else onNextStudent()
            },
            nextEnabled = if (selectedRole == "TEACHER") isTeacherValid else isStudentValid,
            nextTag = if (selectedRole == "TEACHER") "submit_teacher_profile" else "submit_student_profile"
        )
    }
}

// -----------------------------------------------------------------------------------------
// PAGE 6: TEACHER HONORIFIC PAGE IN SENTENCE FORM WITH WHEEL PICKERS
// -----------------------------------------------------------------------------------------
@Composable
private fun TeacherHonorificPage(
    firstName: String,
    surname: String,
    sex: String, // "M" or "F"
    selectedHonorific: String,
    onHonorificSelected: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val honorificOptions = if (sex == "F") listOf("Ma'am", "Ms.", "Mrs.", "Bb.", "Gng.")
    else listOf("Sir", "Mr.", "G.")

    val nameFormatOptions = remember(firstName, surname) {
        listOf(
            "$firstName",
            "$surname"
        )
    }

    var selectedNameFormat by remember(firstName, surname) {
        mutableStateOf(nameFormatOptions.first())
    }

    // Ensure honorific is valid for current sex
    LaunchedEffect(sex) {
        if (!honorificOptions.contains(selectedHonorific)) {
            onHonorificSelected(honorificOptions.first())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Center Sentence & Wheel Pickers (No card or background fields)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = "How would you want to be addressed?",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Sentence wheel pickers: Honorific & Name
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WheelPicker(
                        items = honorificOptions,
                        selectedItem = selectedHonorific,
                        onItemSelected = onHonorificSelected,
                        modifier = Modifier.weight(1f),
                        visibleItemsCount = 3,
                        itemHeight = 80.dp
                    )
                    WheelPicker(
                        items = nameFormatOptions,
                        selectedItem = selectedNameFormat,
                        onItemSelected = { selectedNameFormat = it },
                        modifier = Modifier.weight(1.5f),
                        visibleItemsCount = 3,
                        itemHeight = 80.dp
                    )
                }
            }
        }

        // Bottom Navigation Bar
        BottomNavigationRow(
            onBack = onBack,
            onNext = onNext,
            nextText = "Next"
        )
    }
}

// -----------------------------------------------------------------------------------------
// PAGE 7: 6-DIGIT PIN & BIOMETRICS SETUP PAGE (Centered layout, biometrics at bottom of number pad)
// -----------------------------------------------------------------------------------------
@Composable
private fun PinSetupPage(
    pinCode: String,
    onPinChange: (String) -> Unit,
    supportsBiometrics: Boolean,
    enableBiometrics: Boolean,
    onToggleBiometrics: (Boolean) -> Unit,
    saveLogin: Boolean,
    onToggleSaveLogin: (Boolean) -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit,
    nextText: String = "Finish"
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(4.dp))

        // Center Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Set Up 6-Digit PIN",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Enter a 6-digit PIN for offline sign-in",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 6-Digit Dots Indicator Centered
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0 until 6) {
                    val isFilled = i < pinCode.length
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(
                                if (isFilled) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.surfaceVariant
                            )
                            .border(
                                width = 1.5.dp,
                                color = if (isFilled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                shape = CircleShape
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 3x4 Number Pad (Bigger and lower)
            NumberPad(
                onDigit = { digit ->
                    if (pinCode.length < 6) {
                        onPinChange(pinCode + digit)
                    }
                },
                onBackspace = {
                    if (pinCode.isNotEmpty()) {
                        onPinChange(pinCode.dropLast(1))
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Security Options Card (Biometrics only if supported, Save Login removed but enabled by default)
            if (supportsBiometrics) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                    ),
                    modifier = Modifier.fillMaxWidth(0.95f)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onToggleBiometrics(!enableBiometrics) },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Fingerprint,
                                    contentDescription = "Biometrics",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(22.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Enable Biometrics",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                            Switch(
                                checked = enableBiometrics,
                                onCheckedChange = null,
                                modifier = Modifier.testTag("setup_biometrics_switch")
                            )
                        }
                    }
                }
            }
        }

        // Bottom Navigation Bar
        BottomNavigationRow(
            onBack = onBack,
            onNext = onSubmit,
            nextText = nextText,
            nextEnabled = pinCode.length == 6,
            nextTag = if (nextText == "Next") "next_pin_setup_button" else "finish_pin_setup_button"
        )
    }
}

@Composable
private fun NumberPad(
    onDigit: (String) -> Unit,
    onBackspace: () -> Unit
) {
    val buttons = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("", "0", "DEL")
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttons.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { item ->
                    if (item.isEmpty()) {
                        Spacer(modifier = Modifier.size(72.dp))
                    } else if (item == "DEL") {
                        IconButton(
                            onClick = onBackspace,
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Backspace,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
                                .clickable { onDigit(item) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item,
                                fontSize = 24.sp,
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

// -----------------------------------------------------------------------------------------
// PAGE 8: CLASSROOM CREATION PAGE (Grade max 2 digits, Section max 25 chars, Schedule Time Wheel Pickers)
// -----------------------------------------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClassroomCreationPage(
    teacherCid: String = "",
    grade: String,
    onGradeChange: (String) -> Unit,
    section: String,
    onSectionChange: (String) -> Unit,
    scheduleDays: String,
    onScheduleDaysChange: (String) -> Unit,
    scheduleStartTime: String,
    onScheduleStartTimeChange: (String) -> Unit,
    scheduleGraceTime: String,
    onScheduleGraceTimeChange: (String) -> Unit,
    scheduleEndTime: String,
    onScheduleEndTimeChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit
) {
    val isFormValid = grade.isNotBlank() && section.isNotBlank()

    val timesList = remember {
        listOf(
            "06:00", "06:30", "07:00", "07:30", "08:00", "08:30",
            "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
            "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
            "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
            "18:00", "18:30", "19:00"
        )
    }

    val graceList = remember {
        listOf("05 MINS", "10 MINS", "15 MINS", "20 MINS", "30 MINS", "45 MINS", "60 MINS")
    }

    val daysList = remember {
        listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
    }
    
    // Parse current scheduleDays into start and end day.
    // Assuming format "MON-FRI"
    var startDay by remember(scheduleDays) {
        mutableStateOf(scheduleDays.split("-").getOrNull(0) ?: "MON")
    }
    var endDay by remember(scheduleDays) {
        mutableStateOf(scheduleDays.split("-").getOrNull(1) ?: "FRI")
    }

    var showStartTimePicker by remember { mutableStateOf(false) }
    var showGraceTimePicker by remember { mutableStateOf(false) }
    var showEndTimePicker by remember { mutableStateOf(false) }

    if (showStartTimePicker) {
        val (h, m) = parseHourMinute(scheduleStartTime, 6, 0)
        AnalogTimePickerDialog(
            title = "Select Start Time",
            initialHour = h,
            initialMinute = m,
            onDismiss = { showStartTimePicker = false },
            onConfirm = { hour, minute ->
                showStartTimePicker = false
                onScheduleStartTimeChange(formatTime12Hour(hour, minute))
            }
        )
    }

    if (showGraceTimePicker) {
        val (h, m) = parseHourMinute(scheduleGraceTime, 6, 15)
        AnalogTimePickerDialog(
            title = "Select Grace Period",
            initialHour = h,
            initialMinute = m,
            onDismiss = { showGraceTimePicker = false },
            onConfirm = { hour, minute ->
                showGraceTimePicker = false
                onScheduleGraceTimeChange(formatTime12Hour(hour, minute))
            }
        )
    }

    if (showEndTimePicker) {
        val (h, m) = parseHourMinute(scheduleEndTime, 7, 0)
        AnalogTimePickerDialog(
            title = "Select End Time",
            initialHour = h,
            initialMinute = m,
            onDismiss = { showEndTimePicker = false },
            onConfirm = { hour, minute ->
                showEndTimePicker = false
                onScheduleEndTimeChange(formatTime12Hour(hour, minute))
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
            ) {
                Text(
                    text = "Classroom Setup",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Set Up Your Classroom",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Enter your grade, section, and schedule.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            )

            if (teacherCid.isNotBlank()) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Text(
                        text = "Classroom ID (CID): $teacherCid",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            // Grade & Section Fields
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                var gradeExpanded by remember { mutableStateOf(false) }
                val gradeOptions = listOf("11", "12")

                ExposedDropdownMenuBox(
                    expanded = gradeExpanded,
                    onExpandedChange = { gradeExpanded = !gradeExpanded },
                    modifier = Modifier.weight(0.9f)
                ) {
                    OutlinedTextField(
                        value = if (grade.isNotBlank()) grade else "Select Grade",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Grade *") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = gradeExpanded) },
                        colors = calisTextFieldColors(),
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .testTag("classroom_grade_input")
                    )
                    ExposedDropdownMenu(
                        expanded = gradeExpanded,
                        onDismissRequest = { gradeExpanded = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        gradeOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option, color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    onGradeChange(option)
                                    gradeExpanded = false
                                },
                                modifier = Modifier.testTag("grade_option_$option")
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = section,
                    onValueChange = { onSectionChange(formatSectionName(it)) },
                    label = { Text("Section Name *") },
                    colors = calisTextFieldColors(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .weight(1.3f)
                        .testTag("classroom_section_input"),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Schedule Config Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                ),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Classroom Schedule",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    // Days selector
                    Text(
                        text = "Active Days",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        WheelPicker(
                            items = daysList,
                            selectedItem = startDay,
                            onItemSelected = { 
                                startDay = it
                                onScheduleDaysChange("$startDay-$endDay")
                            },
                            modifier = Modifier.weight(1f),
                            visibleItemsCount = 3,
                            itemHeight = 44.dp
                        )
                        Text(
                            text = "-",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        WheelPicker(
                            items = daysList,
                            selectedItem = endDay,
                            onItemSelected = {
                                endDay = it
                                onScheduleDaysChange("$startDay-$endDay")
                            },
                            modifier = Modifier.weight(1f),
                            visibleItemsCount = 3,
                            itemHeight = 44.dp
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // 3 Time Selection Buttons: Start, Grace, End
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Start Time Box
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surface,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { showStartTimePicker = true }
                                .testTag("select_start_time_button")
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Start Time",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.AccessTime,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = scheduleStartTime,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }

                        // Grace Period Box
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surface,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { showGraceTimePicker = true }
                                .testTag("select_grace_time_button")
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Grace Period",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.AccessTime,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = scheduleGraceTime,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }

                        // End Time Box
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surface,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { showEndTimePicker = true }
                                .testTag("select_end_time_button")
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "End Time",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.AccessTime,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.tertiary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = scheduleEndTime,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Bottom Navigation Bar
        BottomNavigationRow(
            onBack = onBack,
            onNext = onSubmit,
            nextText = "Next",
            nextEnabled = isFormValid,
            nextTag = "next_classroom_creation_button"
        )
    }
}

// -----------------------------------------------------------------------------------------
// PAGE 9: CALIS DEVICE SETUP PAGE (Step-by-step connection requirements, device discovery, skippable)
// -----------------------------------------------------------------------------------------
@Composable
private fun CalisDeviceSetupPage(
    teacherFirstName: String,
    teacherMiddleName: String,
    teacherSurname: String,
    teacherHonorific: String,
    teacherSex: String,
    teacherGrade: String,
    teacherSection: String,
    teacherTid: String,
    teacherCid: String,
    scheduleDays: String,
    scheduleStartTime: String,
    scheduleGraceTime: String,
    scheduleEndTime: String,
    onFinish: () -> Unit,
    onSkip: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val bleManager = remember { TeacherBleManager.getInstance(context) }

    val clsName = remember(teacherGrade, teacherSection) {
        "$teacherGrade-$teacherSection"
    }

    val tcName = remember(teacherHonorific, teacherFirstName, teacherMiddleName, teacherSurname) {
        BlePermissionUtils.formatTeacherName(
            honorific = teacherHonorific,
            firstName = teacherFirstName,
            middleName = teacherMiddleName,
            surname = teacherSurname
        )
    }

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
        if (enabled) isBtEnabling = false
    }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        isNotificationGranted = isGranted || BlePermissionUtils.hasNotificationPermission(context)
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
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                hasPermissions = BlePermissionUtils.hasPermissions(context)
                val enabled = BlePermissionUtils.isBluetoothEnabled(context)
                isBtEnabled = enabled
                if (enabled) isBtEnabling = false
                isNotificationGranted = BlePermissionUtils.hasNotificationPermission(context)
                isLocationEnabled = BlePermissionUtils.isLocationEnabled(context)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val activity = remember(context) {
        var currentContext = context
        while (currentContext is android.content.ContextWrapper) {
            if (currentContext is android.app.Activity) break
            currentContext = currentContext.baseContext
        }
        currentContext as? android.app.Activity
    }

    var registeringAddress by remember { mutableStateOf<String?>(null) }
    var savedMacAddress by remember { mutableStateOf<String?>(null) }
    var tempRegisteredResponse by remember { mutableStateOf<BlePacket.RegisteredResponse?>(null) }
    var isWaitingForReconnect by remember { mutableStateOf(false) }
    var isSuccessfullyRegistered by remember { mutableStateOf(false) }

    LaunchedEffect(hasPermissions, isBtEnabled, isLocationEnabled) {
        if (hasPermissions && isBtEnabled && isLocationEnabled) {
            bleManager.startScan()
        }
    }

    val scannedDevices by bleManager.scannedDevices.collectAsState()
    val connectionState by bleManager.connectionState.collectAsState()
    val deviceState by bleManager.deviceState.collectAsState()

    // Listen to incoming packets
    LaunchedEffect(Unit) {
        bleManager.incomingPackets.collect { packet ->
            when (packet) {
                is BlePacket.RegisteredResponse -> {
                    Log.d("CALIS", "[REG] REGISTERED received successfully")
                    tempRegisteredResponse = packet
                    if (registeringAddress != null) {
                        savedMacAddress = registeringAddress
                    }
                    isWaitingForReconnect = true
                    bleManager.disconnect()
                }
                is BlePacket.AuthOkResponse -> {
                    Log.d("CALIS", "[REG] AUTH_OK received")
                    Log.d("CALIS", "[REG] Registration verification successful")
                    Log.d("CALIS", "[REG] Navigating to Registration Success")
                    isSuccessfullyRegistered = true
                    isWaitingForReconnect = false
                    bleManager.disconnect() // Disconnect cleanly!
                }
                else -> {}
            }
        }
    }

    // Monitor connection and state changes for auto-auth & registration flow
    LaunchedEffect(connectionState) {
        if (connectionState is TeacherBleManager.ConnectionState.Disconnected) {
            if (!isSuccessfullyRegistered && (tempRegisteredResponse != null || registeringAddress != null)) {
                Log.d("CALIS", "[REG] Expected device disconnect / reboot after registration command")
                Log.d("CALIS", "[REG] Waiting for BLE restart")
                kotlinx.coroutines.delay(1000) // Delay to let device reboot its BLE stack
                if (registeringAddress != null && savedMacAddress == null) {
                    savedMacAddress = registeringAddress
                }
                isWaitingForReconnect = true
                Log.d("CALIS", "[REG] Starting verification scan")
                bleManager.startScan()
            }
        } else if (connectionState is TeacherBleManager.ConnectionState.Connected) {
            val conn = connectionState as TeacherBleManager.ConnectionState.Connected
            if (tempRegisteredResponse == null && conn.address == registeringAddress && !isSuccessfullyRegistered) {
                // Connected for registration: send REG packet
                Log.d("CALIS", "[REG] Initial connection established")
                Log.d("CALIS", "[REG] Device entered Registering state")
                Log.d("CALIS", "[REG] Sending REG packet: REG|${teacherCid}|${teacherTid}|${clsName}|${tcName}")
                bleManager.sendPacket(
                    BlePacket.RegCommand(
                        cid = teacherCid,
                        tid = teacherTid,
                        cls = clsName,
                        tc = tcName
                    )
                ) { success ->
                    Log.d("CALIS", "[REG] REG packet send completed with success=$success")
                    if (!isSuccessfullyRegistered && tempRegisteredResponse == null) {
                        if (registeringAddress != null) {
                            savedMacAddress = registeringAddress
                        }
                        isWaitingForReconnect = true
                        bleManager.disconnect()
                        bleManager.startScan()
                    }
                }
            } else if (!isSuccessfullyRegistered && tempRegisteredResponse != null) {
                // Connected after restart/registration for verification/authentication: send AUTH packet
                Log.d("CALIS", "[REG] Reconnection established")
                Log.d("CALIS", "[REG] Service UUID verified")
                Log.d("CALIS", "[REG] Classroom name verified")
                Log.d("CALIS", "[REG] Manufacturer Data = 0xFF00")
                Log.d("CALIS", "[REG] Sending AUTH packet: AUTH|${teacherTid}|${teacherCid}")
                bleManager.sendPacket(
                    BlePacket.AuthCommand(
                        tid = teacherTid,
                        cid = teacherCid
                    )
                )
            }
        }
    }

    // Continuous scanning loop while waiting for device restart & advertising in AUTHENTICATE state
    LaunchedEffect(isWaitingForReconnect, connectionState) {
        if (isWaitingForReconnect && connectionState is TeacherBleManager.ConnectionState.Disconnected) {
            while (isWaitingForReconnect && connectionState is TeacherBleManager.ConnectionState.Disconnected) {
                if (bleManager.isBluetoothEnabled()) {
                    bleManager.startScan()
                }
                kotlinx.coroutines.delay(1500)
            }
        }
    }

    LaunchedEffect(scannedDevices, isWaitingForReconnect, connectionState) {
        if (isWaitingForReconnect && connectionState is TeacherBleManager.ConnectionState.Disconnected) {
            val device = scannedDevices.find { dev ->
                val macMatches = (!savedMacAddress.isNullOrBlank() && dev.address.equals(savedMacAddress, ignoreCase = true)) ||
                        (!registeringAddress.isNullOrBlank() && dev.address.equals(registeringAddress, ignoreCase = true))
                val nameMatches = (clsName.isNotBlank() && (dev.name.equals(clsName, ignoreCase = true) || dev.name.contains(clsName, ignoreCase = true))) ||
                        dev.name.equals("Calis Classroom", ignoreCase = true) ||
                        dev.name.contains("Calis", ignoreCase = true) ||
                        dev.name.contains("Setup", ignoreCase = true)
                macMatches || nameMatches || dev.hasServiceUuid || dev.state != DeviceState.UNKNOWN
            }
            if (device != null) {
                Log.d("CALIS", "[REG] Found device: ${device.name}, state: ${device.state.name}")
                isWaitingForReconnect = false
                bleManager.stopScan()
                bleManager.connect(device.address)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (isSuccessfullyRegistered) {
            // SUCCESS UI: Hide all other UI elements except Finish Setup
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                    ),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = StatusPresentGreen.copy(alpha = 0.2f),
                            modifier = Modifier.size(72.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = StatusPresentGreen,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Registered Calis Device Successfully",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Your Calis Device is connected and ready for classroom sessions.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // Single Finish Setup button
            Button(
                onClick = onFinish,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("finish_registered_setup_button")
            ) {
                Text("Finish Setup", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        } else if (registeringAddress != null || tempRegisteredResponse != null || isWaitingForReconnect || deviceState == DeviceState.REGISTERING) {
            // REGISTERING STATE: Hide all UI elements and display big 'Registering' text
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(24.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(72.dp),
                        strokeWidth = 6.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    Text(
                        text = "Registering",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    val isDisconnected = connectionState is TeacherBleManager.ConnectionState.Disconnected
                    val subtitleText = when {
                        isDisconnected && (tempRegisteredResponse != null || isWaitingForReconnect) -> {
                            "Disconnected from device during Registering\nReconnecting to Calis Device...\nConfirming classroom configuration."
                        }
                        isDisconnected -> {
                            "Disconnected from device during Registering\nConfiguring Calis Device for $clsName..."
                        }
                        tempRegisteredResponse != null || isWaitingForReconnect -> {
                            "Reconnecting to Calis Device...\nConfirming classroom configuration."
                        }
                        else -> {
                            "Configuring Calis Device for $clsName...\nPlease wait while the device updates."
                        }
                    }
                    Text(
                        text = subtitleText,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            // REGISTRATION / CONNECTING UI
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = "Device Setup",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Connect Calis Device",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Pair your hardware receiver now or skip for later.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
                )

                // Step-by-Step Connection Requirements Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                    ),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Connection Requirements",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        val permTitle = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            "1. Nearby Devices Permission"
                        } else {
                            "1. Location Permission"
                        }

                        // Requirement 1: Permission
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(permTitle, fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.titleMedium)
                            }
                            if (hasPermissions) {
                                Surface(
                                    shape = RoundedCornerShape(50),
                                    color = Color(0xFFE11D48).copy(alpha = 0.2f)
                                ) {
                                    Text(
                                        "Granted",
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFE11D48)
                                    )
                                }
                            } else {
                                Button(
                                    onClick = {
                                        val act = BlePermissionUtils.findActivity(context) ?: activity
                                        act?.let { a ->
                                            ActivityCompat.requestPermissions(a, BlePermissionUtils.getRequiredPermissions(), 101)
                                        }
                                    },
                                    modifier = Modifier.testTag("allow_ble_permission_setup_button"),
                                    shape = RoundedCornerShape(10.dp),
                                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                                ) {
                                    Text("Allow", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                                }
                            }
                        }

                        Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                        // Requirement 2: Enable Bluetooth
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("2. Bluetooth", fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.titleMedium)
                            }
                            if (isBtEnabled) {
                                Surface(
                                    shape = RoundedCornerShape(50),
                                    color = Color(0xFFE11D48).copy(alpha = 0.2f)
                                ) {
                                    Text(
                                        "Enabled",
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFE11D48)
                                    )
                                }
                            } else {
                                Button(
                                    onClick = {
                                        isBtEnabling = true
                                        BlePermissionUtils.requestEnableBluetooth(
                                            activity = activity,
                                            launcher = enableBtLauncher,
                                            onEnabling = { isBtEnabling = true }
                                        )
                                    },
                                    enabled = !isBtEnabling,
                                    modifier = Modifier.testTag("enable_bluetooth_setup_button"),
                                    shape = RoundedCornerShape(10.dp),
                                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                                ) {
                                    if (isBtEnabling) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.size(14.dp),
                                                color = Color.White,
                                                strokeWidth = 2.dp
                                            )
                                            Text("Enabling...", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                                        }
                                    } else {
                                        Text("Turn On", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                                    }
                                }
                            }
                        }

                        Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                        // Requirement 3: Allow Notifications
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("3. Allow Notifications", fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.titleMedium)
                            }
                            if (isNotificationGranted) {
                                Surface(
                                    shape = RoundedCornerShape(50),
                                    color = Color(0xFFE11D48).copy(alpha = 0.2f)
                                ) {
                                    Text(
                                        "Granted",
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFE11D48)
                                    )
                                }
                            } else {
                                Button(
                                    onClick = {
                                        BlePermissionUtils.requestNotificationPermission(context, notificationPermissionLauncher)
                                    },
                                    modifier = Modifier.testTag("allow_notification_setup_button"),
                                    shape = RoundedCornerShape(10.dp),
                                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                                ) {
                                    Text("Allow", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                                }
                            }
                        }

                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                            Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                            // Requirement 4: Enable Location Services
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("4. Enable Location", fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.titleMedium)
                                }
                                if (isLocationEnabled) {
                                    Surface(
                                        shape = RoundedCornerShape(50),
                                        color = Color(0xFFE11D48).copy(alpha = 0.2f)
                                    ) {
                                        Text(
                                            "Enabled",
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFE11D48)
                                        )
                                    }
                                } else {
                                    Button(
                                        onClick = {
                                            try {
                                                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                                                context.startActivity(intent)
                                            } catch (e: Exception) {
                                                isLocationEnabled = BlePermissionUtils.isLocationEnabled(context)
                                            }
                                        },
                                        modifier = Modifier.testTag("enable_location_setup_button"),
                                        shape = RoundedCornerShape(10.dp),
                                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                                    ) {
                                        Text("Turn On", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // DISCOVERING CALIS DEVICES TILE (BLE Service UUID & 0xFFFF Factory Mode)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                    ),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Discovering Calis Devices",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "Searching for nearby devices...",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            if (connectionState is TeacherBleManager.ConnectionState.Scanning || isWaitingForReconnect) {
                                CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                            }
                        }

                        if (scannedDevices.isEmpty()) {
                            Text(
                                text = "Searching for nearby Calis devices... Make sure your device is powered on.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(vertical = 12.dp)
                            )
                        } else {
                            scannedDevices.forEach { dev ->
                                val isThisRegistering = (registeringAddress == dev.address && !isSuccessfullyRegistered)
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = MaterialTheme.colorScheme.surface,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(dev.name, fontWeight = FontWeight.Bold)
                                            Text(dev.address, style = MaterialTheme.typography.bodySmall)
                                            Text(
                                                text = if (isThisRegistering) "Registering" else dev.state.displayName,
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = if (isThisRegistering) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
                                            )
                                        }

                                        if (isThisRegistering) {
                                            Surface(
                                                shape = RoundedCornerShape(50),
                                                color = MaterialTheme.colorScheme.tertiaryContainer
                                            ) {
                                                Row(
                                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    CircularProgressIndicator(
                                                        modifier = Modifier.size(12.dp),
                                                        strokeWidth = 2.dp,
                                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                                    )
                                                    Spacer(modifier = Modifier.width(6.dp))
                                                    Text(
                                                        "Registering",
                                                        fontSize = 12.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                                    )
                                                }
                                            }
                                        } else {
                                            Button(
                                                onClick = {
                                                    Log.d("CALIS", "[REG] Selected device")
                                                    savedMacAddress = dev.address
                                                    registeringAddress = dev.address
                                                    bleManager.stopScan()
                                                    bleManager.connect(dev.address)
                                                },
                                                modifier = Modifier.testTag("connect_device_setup_button")
                                            ) {
                                                Text("Register")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bottom Controls
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = onBack,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .height(48.dp)
                            .weight(1f)
                            .testTag("back_device_setup_button")
                    ) {
                        Text("Back")
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = if (tempRegisteredResponse != null || isSuccessfullyRegistered) onFinish else onSkip,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .height(48.dp)
                            .weight(1.2f)
                            .testTag("finish_device_setup_button")
                    ) {
                        Text(
                            text = if (tempRegisteredResponse != null || isSuccessfullyRegistered) "Finish Setup" else "Setup Later",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
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

private fun parseHourMinute(timeStr: String, defaultHour: Int, defaultMinute: Int): Pair<Int, Int> {
    return try {
        if (timeStr.contains("MINS", ignoreCase = true)) {
            val mins = timeStr.replace(Regex("[^0-9]"), "").toIntOrNull() ?: defaultMinute
            Pair(0, mins)
        } else {
            val sdf12 = java.text.SimpleDateFormat("hh:mm a", java.util.Locale.US)
            val sdf24 = java.text.SimpleDateFormat("HH:mm", java.util.Locale.US)
            val date = try {
                sdf12.parse(timeStr)
            } catch (e: Exception) {
                try {
                    sdf24.parse(timeStr)
                } catch (e2: Exception) {
                    null
                }
            }
            if (date != null) {
                val cal = java.util.Calendar.getInstance().apply { time = date }
                Pair(cal.get(java.util.Calendar.HOUR_OF_DAY), cal.get(java.util.Calendar.MINUTE))
            } else {
                Pair(defaultHour, defaultMinute)
            }
        }
    } catch (e: Exception) {
        Pair(defaultHour, defaultMinute)
    }
}

@Composable
private fun ConnectionRequirementsPage(
    selectedRole: String,
    hasRequiredPermission: Boolean,
    onRequestPermissions: () -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit
) {
    val context = LocalContext.current
    val activity = remember(context) {
        var currentContext = context
        while (currentContext is android.content.ContextWrapper) {
            if (currentContext is android.app.Activity) break
            currentContext = currentContext.baseContext
        }
        currentContext as? android.app.Activity
    }
    val isAndroid12Plus = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    var isBtEnabled by remember { mutableStateOf(BlePermissionUtils.isBluetoothEnabled(context)) }
    var isBtEnabling by remember { mutableStateOf(false) }
    var isNotificationGranted by remember { mutableStateOf(BlePermissionUtils.hasNotificationPermission(context)) }
    var isLocationEnabled by remember { mutableStateOf(BlePermissionUtils.isLocationEnabled(context)) }

    val enableBtLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        val enabled = BlePermissionUtils.isBluetoothEnabled(context)
        isBtEnabled = enabled
        if (enabled) isBtEnabling = false
    }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        isNotificationGranted = isGranted || BlePermissionUtils.hasNotificationPermission(context)
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
        val filter = android.content.IntentFilter().apply {
            addAction(android.bluetooth.BluetoothAdapter.ACTION_STATE_CHANGED)
            addAction(android.location.LocationManager.PROVIDERS_CHANGED_ACTION)
        }
        val receiver = object : android.content.BroadcastReceiver() {
            override fun onReceive(ctx: android.content.Context, intent: android.content.Intent) {
                when (intent.action) {
                    android.bluetooth.BluetoothAdapter.ACTION_STATE_CHANGED -> {
                        val state = intent.getIntExtra(android.bluetooth.BluetoothAdapter.EXTRA_STATE, android.bluetooth.BluetoothAdapter.ERROR)
                        if (state == android.bluetooth.BluetoothAdapter.STATE_TURNING_ON) {
                            isBtEnabling = true
                        } else if (state == android.bluetooth.BluetoothAdapter.STATE_OFF || state == android.bluetooth.BluetoothAdapter.STATE_TURNING_OFF) {
                            isBtEnabling = false
                            isBtEnabled = false
                        } else if (state == android.bluetooth.BluetoothAdapter.STATE_ON) {
                            isBtEnabling = false
                            isBtEnabled = true
                        } else {
                            val enabled = BlePermissionUtils.isBluetoothEnabled(context)
                            isBtEnabled = enabled
                            if (enabled) isBtEnabling = false
                        }
                    }
                    android.location.LocationManager.PROVIDERS_CHANGED_ACTION -> {
                        isLocationEnabled = BlePermissionUtils.isLocationEnabled(context)
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
                val enabled = BlePermissionUtils.isBluetoothEnabled(context)
                isBtEnabled = enabled
                if (enabled) isBtEnabling = false
                isNotificationGranted = BlePermissionUtils.hasNotificationPermission(context)
                isLocationEnabled = BlePermissionUtils.isLocationEnabled(context)
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
            ) {
                Text(
                    text = "CONNECTION SETUP",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Connection Requirements",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                ),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // STEP 1: Permission
                    val permTitle = if (isAndroid12Plus) "1. Nearby Devices Permission" else "1. Location Permission"

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (hasRequiredPermission) {
                                            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                        } else {
                                            MaterialTheme.colorScheme.error.copy(alpha = 0.15f)
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = if (isAndroid12Plus) Icons.Default.Bluetooth else Icons.Default.Fingerprint,
                                    contentDescription = null,
                                    tint = if (hasRequiredPermission) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Column {
                                Text(
                                    text = permTitle,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        if (!hasRequiredPermission) {
                            Button(
                                onClick = {
                                    onRequestPermissions()
                                },
                                shape = RoundedCornerShape(10.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                                modifier = Modifier.testTag("grant_permission_button")
                            ) {
                                Text("Grant", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
                            }
                        } else {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Granted",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(28.dp)
                                    .padding(end = 4.dp)
                            )
                        }
                    }

                    Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                    // STEP 2: Enable Bluetooth
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (isBtEnabled) {
                                            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                        } else {
                                            MaterialTheme.colorScheme.error.copy(alpha = 0.15f)
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Bluetooth,
                                    contentDescription = null,
                                    tint = if (isBtEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Column {
                                Text(
                                    text = "2. Enable Bluetooth",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        if (!isBtEnabled) {
                            Button(
                                onClick = {
                                    isBtEnabling = true
                                    BlePermissionUtils.requestEnableBluetooth(
                                        activity = activity,
                                        launcher = enableBtLauncher,
                                        onEnabling = { isBtEnabling = true }
                                    )
                                },
                                enabled = !isBtEnabling,
                                shape = RoundedCornerShape(10.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                                modifier = Modifier.testTag("enable_bluetooth_button")
                            ) {
                                if (isBtEnabling) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(14.dp),
                                            color = Color.White,
                                            strokeWidth = 2.dp
                                        )
                                        Text("Enabling...", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
                                    }
                                } else {
                                    Text("Turn On", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
                                }
                            }
                        } else {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Enabled",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(28.dp)
                                    .padding(end = 4.dp)
                            )
                        }
                    }

                    Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                    // STEP 3: Allow Notifications
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (isNotificationGranted) {
                                            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                        } else {
                                            MaterialTheme.colorScheme.error.copy(alpha = 0.15f)
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = null,
                                    tint = if (isNotificationGranted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Column {
                                Text(
                                    text = "3. Allow Notifications",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        if (!isNotificationGranted) {
                            Button(
                                onClick = {
                                    BlePermissionUtils.requestNotificationPermission(context, notificationPermissionLauncher)
                                },
                                shape = RoundedCornerShape(10.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                                modifier = Modifier.testTag("allow_notifications_button")
                            ) {
                                Text("Allow", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
                            }
                        } else {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Granted",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(28.dp)
                                    .padding(end = 4.dp)
                            )
                        }
                    }

                    // STEP 4: Location Services (only if older than Android 12)
                    if (!isAndroid12Plus) {
                        Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isLocationEnabled) {
                                                MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                            } else {
                                                MaterialTheme.colorScheme.error.copy(alpha = 0.15f)
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Fingerprint,
                                        contentDescription = null,
                                        tint = if (isLocationEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                Column {
                                    Text(
                                        text = "4. Enable Location Services",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }

                            if (!isLocationEnabled) {
                                Button(
                                    onClick = {
                                        try {
                                            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                                            context.startActivity(intent)
                                        } catch (e: Exception) {
                                            isLocationEnabled = BlePermissionUtils.isLocationEnabled(context)
                                        }
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    ),
                                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                                    modifier = Modifier.testTag("enable_location_button")
                                ) {
                                    Text("Turn On", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
                                }
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Enabled",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .size(28.dp)
                                        .padding(end = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

        val allRequirementsMet = hasRequiredPermission && isBtEnabled && isNotificationGranted && (isAndroid12Plus || isLocationEnabled)
        val isStudent = selectedRole == "STUDENT"
        val showNextButton = true
        val nextText = if (isStudent) "Finish" else (if (allRequirementsMet) "Finish" else "Finish Later")
        val nextEnabled = if (isStudent) allRequirementsMet else true

        BottomNavigationRow(
            onBack = onBack,
            onNext = onFinish,
            nextText = nextText,
            nextEnabled = nextEnabled,
            nextTag = "finish_connection_requirements_button"
        )
    }
}

