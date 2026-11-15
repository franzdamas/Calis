package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ble.CalisBleService
import com.example.data.db.CalisDatabase
import com.example.data.db.ClassroomEntity
import com.example.data.db.StudentProfileEntity
import com.example.data.db.TeacherEntity
import com.example.data.repository.CalisRepository
import com.example.util.IdGenerator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CalisRepository
    private val prefs = application.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)

    init {
        com.example.util.BlePermissionUtils.appContext = application.applicationContext
    }

    private val _isDbLoaded = MutableStateFlow(false)
    val isDbLoaded: StateFlow<Boolean> = _isDbLoaded.asStateFlow()

    private val _hasAccount = MutableStateFlow(false)
    val hasAccount: StateFlow<Boolean> = _hasAccount.asStateFlow()

    private val _saveLogin = MutableStateFlow(prefs.getBoolean("save_login", true))
    val saveLogin: StateFlow<Boolean> = _saveLogin.asStateFlow()

    private val _enableBiometrics = MutableStateFlow(prefs.getBoolean("enable_biometrics", false))
    val enableBiometrics: StateFlow<Boolean> = _enableBiometrics.asStateFlow()

    private val _autoStartSession = MutableStateFlow(prefs.getBoolean("auto_start_session", true))
    val autoStartSession: StateFlow<Boolean> = _autoStartSession.asStateFlow()

    fun setAutoStartSession(enabled: Boolean) {
        prefs.edit().putBoolean("auto_start_session", enabled).apply()
        _autoStartSession.value = enabled
    }

    // TC: Remind Me to Start Session
    private val _remindStartSession = MutableStateFlow(prefs.getBoolean("remind_start_session", true))
    val remindStartSession: StateFlow<Boolean> = _remindStartSession.asStateFlow()

    // TC: Auto-Connect on Startup
    private val _autoConnectOnStartup = MutableStateFlow(prefs.getBoolean("auto_connect_on_startup", true))
    val autoConnectOnStartup: StateFlow<Boolean> = _autoConnectOnStartup.asStateFlow()

    fun setAutoConnectOnStartup(enabled: Boolean) {
        prefs.edit().putBoolean("auto_connect_on_startup", enabled).apply()
        _autoConnectOnStartup.value = enabled
    }

    private val _remindStartSessionMinutes = MutableStateFlow(prefs.getInt("remind_start_session_minutes", 15))
    val remindStartSessionMinutes: StateFlow<Int> = _remindStartSessionMinutes.asStateFlow()

    fun setRemindStartSession(enabled: Boolean) {
        prefs.edit().putBoolean("remind_start_session", enabled).apply()
        _remindStartSession.value = enabled
        if (enabled) {
            val ctx = getApplication<Application>().applicationContext
            com.example.util.ReminderNotificationHelper.clearSessionReminderKeys(ctx)
            viewModelScope.launch {
                com.example.util.ReminderNotificationHelper.checkAndPostSessionReminder(ctx)
            }
        } else {
            com.example.util.ReminderNotificationHelper.cancelSessionReminder(
                getApplication<Application>().applicationContext
            )
        }
    }

    fun setRemindStartSessionMinutes(minutes: Int) {
        prefs.edit().putInt("remind_start_session_minutes", minutes).apply()
        _remindStartSessionMinutes.value = minutes
        if (_remindStartSession.value) {
            val ctx = getApplication<Application>().applicationContext
            com.example.util.ReminderNotificationHelper.clearSessionReminderKeys(ctx)
            viewModelScope.launch {
                com.example.util.ReminderNotificationHelper.checkAndPostSessionReminder(ctx)
            }
        }
    }

    // ST: Remind Me of Task's Deadline
    private val _remindTaskDeadline = MutableStateFlow(prefs.getBoolean("remind_task_deadline", true))
    val remindTaskDeadline: StateFlow<Boolean> = _remindTaskDeadline.asStateFlow()

    private val _remindTaskDeadlineDays = MutableStateFlow(prefs.getInt("remind_task_deadline_days", 1))
    val remindTaskDeadlineDays: StateFlow<Int> = _remindTaskDeadlineDays.asStateFlow()

    private val _remindTaskDeadlineHour = MutableStateFlow(prefs.getInt("remind_task_deadline_time_hour", 8))
    val remindTaskDeadlineHour: StateFlow<Int> = _remindTaskDeadlineHour.asStateFlow()

    private val _remindTaskDeadlineMinute = MutableStateFlow(prefs.getInt("remind_task_deadline_time_minute", 0))
    val remindTaskDeadlineMinute: StateFlow<Int> = _remindTaskDeadlineMinute.asStateFlow()

    private val _remindTaskDeadlineTime = MutableStateFlow(
        prefs.getString("remind_task_deadline_time", "8:00 AM") ?: "8:00 AM"
    )
    val remindTaskDeadlineTime: StateFlow<String> = _remindTaskDeadlineTime.asStateFlow()

    private val _autoStartupEnabled = MutableStateFlow(prefs.getBoolean("auto_startup_enabled", true))
    val autoStartupEnabled: StateFlow<Boolean> = _autoStartupEnabled.asStateFlow()

    // ST: Remind Me to Check-In
    private val _remindCheckIn = MutableStateFlow(prefs.getBoolean("remind_check_in", true))
    val remindCheckIn: StateFlow<Boolean> = _remindCheckIn.asStateFlow()

    private val _remindCheckInMinutes = MutableStateFlow(prefs.getInt("remind_check_in_minutes", 15))
    val remindCheckInMinutes: StateFlow<Int> = _remindCheckInMinutes.asStateFlow()

    fun setRemindCheckIn(enabled: Boolean) {
        prefs.edit().putBoolean("remind_check_in", enabled).apply()
        _remindCheckIn.value = enabled
        val ctx = getApplication<Application>().applicationContext
        if (enabled) {
            viewModelScope.launch {
                com.example.util.ReminderNotificationHelper.checkAndPostCheckInReminder(ctx)
            }
        }
    }

    fun setRemindCheckInMinutes(minutes: Int) {
        prefs.edit().putInt("remind_check_in_minutes", minutes).apply()
        _remindCheckInMinutes.value = minutes
        val ctx = getApplication<Application>().applicationContext
        if (_remindCheckIn.value) {
            viewModelScope.launch {
                com.example.util.ReminderNotificationHelper.checkAndPostCheckInReminder(ctx)
            }
        }
    }

    fun setAutoStartupEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("auto_startup_enabled", enabled).apply()
        _autoStartupEnabled.value = enabled
    }

    fun setRemindTaskDeadline(enabled: Boolean) {
        prefs.edit().putBoolean("remind_task_deadline", enabled).apply()
        _remindTaskDeadline.value = enabled
        val ctx = getApplication<Application>().applicationContext
        if (enabled) {
            com.example.util.ReminderNotificationHelper.clearTaskReminderKeys(ctx)
            viewModelScope.launch {
                com.example.util.ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(ctx)
            }
        } else {
            com.example.util.ReminderNotificationHelper.cancelTaskDeadlineReminder(ctx)
        }
    }

    fun setRemindTaskDeadlineDays(days: Int) {
        prefs.edit().putInt("remind_task_deadline_days", days).apply()
        _remindTaskDeadlineDays.value = days
        if (_remindTaskDeadline.value) {
            val ctx = getApplication<Application>().applicationContext
            com.example.util.ReminderNotificationHelper.clearTaskReminderKeys(ctx)
            viewModelScope.launch {
                com.example.util.ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(ctx)
            }
        }
    }

    fun setRemindTaskDeadlineTime(hour: Int, minute: Int) {
        val amPm = if (hour >= 12) "PM" else "AM"
        val h12 = when (val h = hour % 12) {
            0 -> 12
            else -> h
        }
        val formatted = String.format(java.util.Locale.US, "%d:%02d %s", h12, minute, amPm)
        prefs.edit()
            .putInt("remind_task_deadline_time_hour", hour)
            .putInt("remind_task_deadline_time_minute", minute)
            .putString("remind_task_deadline_time", formatted)
            .apply()
        _remindTaskDeadlineHour.value = hour
        _remindTaskDeadlineMinute.value = minute
        _remindTaskDeadlineTime.value = formatted
        if (_remindTaskDeadline.value) {
            val ctx = getApplication<Application>().applicationContext
            com.example.util.ReminderNotificationHelper.clearTaskReminderKeys(ctx)
            viewModelScope.launch {
                com.example.util.ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(ctx)
            }
        }
    }

    private val _savedPin = MutableStateFlow(prefs.getString("saved_pin", "") ?: "")
    val savedPin: StateFlow<String> = _savedPin.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(prefs.getBoolean("is_logged_in", false))
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _userRole = MutableStateFlow(prefs.getString("user_role", null))
    val userRole: StateFlow<String?> = _userRole.asStateFlow()

    init {
        val db = CalisDatabase.getDatabase(application)
        repository = CalisRepository(
            database = db,
            teacherDao = db.teacherDao(),
            studentProfileDao = db.studentProfileDao(),
            classroomDao = db.classroomDao(),
            approvedStudentDao = db.approvedStudentDao(),
            pendingRegistrationDao = db.pendingRegistrationDao(),
            attendanceDao = db.attendanceDao(),
            taskDao = db.taskDao(),
            taskSubmissionDao = db.taskSubmissionDao(),
            syncRecordDao = db.syncRecordDao()
        )

        viewModelScope.launch(Dispatchers.IO) {
            val savedRole = prefs.getString("user_role", null)
            val student = repository.getStudentProfileOnce()
            val teacher = repository.getTeacherOnce()

            val determinedRole = when {
                savedRole == "STUDENT" && student != null -> "STUDENT"
                savedRole == "TEACHER" && teacher != null -> "TEACHER"
                student != null -> "STUDENT"
                teacher != null -> "TEACHER"
                savedRole != null -> savedRole
                else -> null
            }
            if (determinedRole != null) {
                prefs.edit().putString("user_role", determinedRole).apply()
                _userRole.value = determinedRole
            }
            val hasActiveAccount = when (determinedRole) {
                "STUDENT" -> student != null
                "TEACHER" -> teacher != null
                else -> (student != null || teacher != null)
            }

            // Prevent resetting if already logged in but DB appears empty (e.g. destructive migration)
            if (_isLoggedIn.value && !hasActiveAccount) {
                _hasAccount.value = true
            } else {
                _hasAccount.value = hasActiveAccount
            }
            _isDbLoaded.value = true
        }

        viewModelScope.launch {
            kotlinx.coroutines.flow.combine(repository.currentTeacher, repository.currentStudentProfile) { teacher, student ->
                val currentRole = _userRole.value ?: prefs.getString("user_role", null)
                when (currentRole) {
                    "STUDENT" -> student != null
                    "TEACHER" -> teacher != null
                    else -> (student != null || teacher != null)
                }
            }.collect { accountExists ->
                if (_isDbLoaded.value) {
                    _hasAccount.value = accountExists
                }
            }
        }
    }

    val currentTeacher: StateFlow<TeacherEntity?> = repository.currentTeacher
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val currentStudentProfile: StateFlow<StudentProfileEntity?> = repository.currentStudentProfile
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val _selectedRole = MutableStateFlow<String?>(null) // "TEACHER" or "STUDENT"
    val selectedRole: StateFlow<String?> = _selectedRole.asStateFlow()

    fun selectRole(role: String) {
        _selectedRole.value = role
    }

    fun saveAuthCredentials(pin: String, biometrics: Boolean, saveLogin: Boolean) {
        prefs.edit()
            .putString("saved_pin", pin)
            .putBoolean("enable_biometrics", biometrics)
            .putBoolean("save_login", saveLogin)
            .putBoolean("is_logged_in", true)
            .apply()
        _savedPin.value = pin
        _enableBiometrics.value = biometrics
        _saveLogin.value = saveLogin
        _isLoggedIn.value = true
    }

    fun setSaveLogin(enabled: Boolean) {
        prefs.edit().putBoolean("save_login", enabled).apply()
        _saveLogin.value = enabled
    }

    fun setEnableBiometrics(enabled: Boolean) {
        prefs.edit().putBoolean("enable_biometrics", enabled).apply()
        _enableBiometrics.value = enabled
    }

    fun setLoggedIn(loggedIn: Boolean) {
        prefs.edit().putBoolean("is_logged_in", loggedIn).apply()
        _isLoggedIn.value = loggedIn
    }

    fun logout(onComplete: () -> Unit) {
        prefs.edit().putBoolean("is_logged_in", false).apply()
        _isLoggedIn.value = false
        CalisBleService.stopService(getApplication<Application>())
        onComplete()
    }

    fun deleteAccountAndAllData(onComplete: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
            prefs.edit().clear().apply()
            _savedPin.value = ""
            _enableBiometrics.value = false
            _saveLogin.value = false
            _isLoggedIn.value = false
            _selectedRole.value = null
            _userRole.value = null
            _remindStartSession.value = false
            _remindTaskDeadline.value = false
            com.example.util.ReminderNotificationHelper.cancelSessionReminder(getApplication<Application>().applicationContext)
            com.example.util.ReminderNotificationHelper.cancelTaskDeadlineReminder(getApplication<Application>().applicationContext)
            CalisBleService.stopService(getApplication<Application>())
            withContext(Dispatchers.Main) {
                onComplete()
            }
        }
    }

    fun createTeacherProfile(fullName: String, sex: String = "M", onComplete: () -> Unit) {
        viewModelScope.launch {
            val tid = IdGenerator.generateTID()
            val teacher = TeacherEntity(
                tid = tid,
                fullName = fullName.trim(),
                sex = sex
            )
            repository.saveTeacher(teacher)
            prefs.edit().putString("user_role", "TEACHER").apply()
            _userRole.value = "TEACHER"
            _selectedRole.value = "TEACHER"
            onComplete()
        }
    }

    fun createTeacherProfileWithClassroom(
        fullName: String,
        sex: String = "M",
        classroomName: String,
        days: String,
        startTime: String,
        graceTime: String,
        endTime: String,
        tid: String? = null,
        cid: String? = null,
        firstName: String? = null,
        middleName: String? = null,
        surname: String? = null,
        honorific: String? = null,
        onComplete: () -> Unit
    ) {
        viewModelScope.launch {
            if (!firstName.isNullOrBlank() || !surname.isNullOrBlank()) {
                prefs.edit()
                    .putString("teacher_first_name", firstName ?: "")
                    .putString("teacher_middle_name", middleName ?: "")
                    .putString("teacher_surname", surname ?: "")
                    .putString("teacher_honorific", honorific ?: "")
                    .apply()
            }
            val teacherTid = tid ?: IdGenerator.generateTID()
            val teacher = TeacherEntity(
                tid = teacherTid,
                fullName = fullName.trim(),
                sex = sex
            )
            repository.saveTeacher(teacher)

            val classroomCid = cid ?: IdGenerator.generateCID()
            val classroom = ClassroomEntity(
                cid = classroomCid,
                teacherId = teacherTid,
                classroomName = classroomName.trim(),
                teacherName = teacher.fullName,
                days = days,
                startTime = startTime,
                graceTime = graceTime,
                endTime = endTime,
                isBound = false
            )
            repository.saveClassroom(classroom)
            com.example.util.ReminderNotificationHelper.checkAndPostSessionReminder(getApplication())

            prefs.edit().putString("user_role", "TEACHER").apply()
            _userRole.value = "TEACHER"
            _selectedRole.value = "TEACHER"
            onComplete()
        }
    }

    fun createStudentProfile(
        fullName: String,
        sex: String,
        lrn: String,
        birthDate: Long,
        onComplete: () -> Unit
    ) {
        viewModelScope.launch {
            val sid = IdGenerator.generateSID()
            val profile = StudentProfileEntity(
                sid = sid,
                fullName = fullName.trim(),
                sex = sex,
                lrn = lrn.trim(),
                birth = birthDate
            )
            repository.saveStudentProfile(profile)
            prefs.edit().putString("user_role", "STUDENT").apply()
            _userRole.value = "STUDENT"
            _selectedRole.value = "STUDENT"
            CalisBleService.stopService(getApplication<Application>())
            onComplete()
        }
    }
}
