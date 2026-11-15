package com.example.ui.viewmodel
import android.util.Log
import com.example.util.BlePermissionUtils

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ble.StudentBleManager
import com.example.ble.BlePacket
import com.example.ble.DeviceState
import com.example.data.db.ApprovedStudentEntity
import com.example.data.db.AttendanceEntity
import com.example.data.db.CalisDatabase
import com.example.data.db.ClassroomEntity
import com.example.data.db.StudentProfileEntity
import com.example.data.db.TaskEntity
import com.example.data.db.TaskSubmissionEntity
import com.example.data.repository.CalisRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

enum class StudentRegDialogStep {
    DISCOVERING,
    CONNECTING,
    REGISTERING,
    SYNCHRONIZING,
    SUCCESS,
    REJECTED
}

enum class StudentSyncState {
    IDLE,
    SCANNING,
    BUSY,
    CONNECTING,
    SYNCING,
    SUCCESS,
    ERROR
}

enum class StudentCheckInState {
    IDLE,
    DISCOVERING,
    CONNECTING,
    CHECKING_IN,
    WAITING_FOR_ACCEPT,
    RECEIVING_TASKS,
    SUCCESS,
    FAILED
}

enum class StudentTaskSubmitState {
    IDLE,
    DISCOVERING,
    CONNECTING,
    SUBMITTING,
    WAITING_FOR_MARKED,
    SUCCESS,
    FAILED
}

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CalisRepository
    val bleManager: StudentBleManager = StudentBleManager.getInstance(application)

    val currentProfile: StateFlow<StudentProfileEntity?>

    val connectionState = bleManager.connectionState
    val deviceState = bleManager.deviceState
    val scannedDevices = bleManager.scannedDevices
    val lastTxResponse = bleManager.lastTxResponse

    private val _registeredClassroom = MutableStateFlow<ClassroomEntity?>(null)
    val registeredClassroom: StateFlow<ClassroomEntity?> = _registeredClassroom.asStateFlow()

    private val _isClassroomLoaded = MutableStateFlow(false)
    val isClassroomLoaded: StateFlow<Boolean> = _isClassroomLoaded.asStateFlow()

    private val _registrationStatus = MutableStateFlow<String>("NOT_REGISTERED") // NOT_REGISTERED, PENDING, APPROVED_WAITING_TASKS, RECEIVING_TASKS, APPROVED, REJECTED
    val registrationStatus: StateFlow<String> = _registrationStatus.asStateFlow()

    private val _joinErrorMessage = MutableStateFlow<String?>(null)
    val joinErrorMessage: StateFlow<String?> = _joinErrorMessage.asStateFlow()

    private var joinTimeoutJob: Job? = null
    private var joinConnectionStartTime: Long = 0L

    fun clearJoinErrorMessage() {
        _joinErrorMessage.value = null
    }

    private val _registrationTasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val registrationTasks: StateFlow<List<TaskEntity>> = _registrationTasks.asStateFlow()

    private val _regDialogStep = MutableStateFlow<StudentRegDialogStep>(StudentRegDialogStep.DISCOVERING)
    val regDialogStep: StateFlow<StudentRegDialogStep> = _regDialogStep.asStateFlow()

    private val _registrationDeviceAddress = MutableStateFlow("")
    val registrationDeviceAddress: StateFlow<String> = _registrationDeviceAddress.asStateFlow()

    private val _registrationDeviceName = MutableStateFlow("")
    val registrationDeviceName: StateFlow<String> = _registrationDeviceName.asStateFlow()

    private val _syncState = MutableStateFlow<StudentSyncState>(StudentSyncState.IDLE)
    val syncState: StateFlow<StudentSyncState> = _syncState.asStateFlow()

    private val _syncMessage = MutableStateFlow<String>("")
    val syncMessage: StateFlow<String> = _syncMessage.asStateFlow()

    private val _registeredDeviceState = MutableStateFlow<DeviceState>(DeviceState.UNKNOWN)
    val registeredDeviceState: StateFlow<DeviceState> = _registeredDeviceState.asStateFlow()

    private val _isRegisteredDeviceInRange = MutableStateFlow(false)
    val isRegisteredDeviceInRange: StateFlow<Boolean> = _isRegisteredDeviceInRange.asStateFlow()

    private val _registeredDeviceRawHex = MutableStateFlow<String>("")
    val registeredDeviceRawHex: StateFlow<String> = _registeredDeviceRawHex.asStateFlow()

    private val _isRegisteredSessionActive = MutableStateFlow(false)
    val isRegisteredSessionActive: StateFlow<Boolean> = _isRegisteredSessionActive.asStateFlow()

    private val _isClassroomExpired = MutableStateFlow(false)
    val isClassroomExpired: StateFlow<Boolean> = _isClassroomExpired.asStateFlow()

    private val _showClassroomExpiredDialog = MutableStateFlow(false)
    val showClassroomExpiredDialog: StateFlow<Boolean> = _showClassroomExpiredDialog.asStateFlow()

    fun dismissClassroomExpired() {
        _showClassroomExpiredDialog.value = false
    }

    private var syncScanJob: Job? = null
    private var checkInJob: Job? = null
    @Volatile private var isManualSyncActive = false
    private var backgroundScanJob: Job? = null

    val myAttendanceLogs: StateFlow<List<AttendanceEntity>>
    val hasCheckedInToday: StateFlow<Boolean>
    val mySubmissions: StateFlow<List<TaskSubmissionEntity>>
    val classroomApprovedStudents: StateFlow<List<ApprovedStudentEntity>>
    val classroomAttendanceLogs: StateFlow<List<AttendanceEntity>>
    val classroomTasks: StateFlow<List<TaskEntity>>

    private val _userMessage = MutableSharedFlow<String>()
    val userMessage: SharedFlow<String> = _userMessage.asSharedFlow()

    private val _checkInState = MutableStateFlow(StudentCheckInState.IDLE)
    val checkInState: StateFlow<StudentCheckInState> = _checkInState.asStateFlow()

    private val _checkInMessage = MutableStateFlow("")
    val checkInMessage: StateFlow<String> = _checkInMessage.asStateFlow()

    private val _checkInTasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val checkInTasks: StateFlow<List<TaskEntity>> = _checkInTasks.asStateFlow()

    private val _checkInLogs = MutableStateFlow<List<String>>(emptyList())
    val checkInLogs: StateFlow<List<String>> = _checkInLogs.asStateFlow()

    fun resetCheckInState() {
        checkInJob?.cancel()
        checkInJob = null
        _checkInState.value = StudentCheckInState.IDLE
        _checkInMessage.value = ""
        _checkInTasks.value = emptyList()
        _checkInLogs.value = emptyList()
        bleManager.stopScan()
        bleManager.clearBleObject()
    }

    fun isCheckedInTodayForClassroom(classroomId: String? = null): Boolean {
        val cid = classroomId ?: _registeredClassroom.value?.cid ?: ""
        val todayYmd = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date())
        val todayMdy = java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date())
        val prefs = getApplication<android.app.Application>().getSharedPreferences("calis_auth_prefs", android.content.Context.MODE_PRIVATE)

        val prefCheckedIn = (cid.isNotBlank() && prefs.getBoolean("checked_in_today_${cid}_$todayYmd", false)) ||
                            prefs.getBoolean("checked_in_today_global_$todayYmd", false)

        if (prefCheckedIn) return true

        return myAttendanceLogs.value.any { log ->
            val matchesClassroom = cid.isBlank() || log.classroomId == cid
            matchesClassroom && log.date.startsWith(todayMdy)
        }
    }

    private val _taskSubmitState = MutableStateFlow(StudentTaskSubmitState.IDLE)
    val taskSubmitState: StateFlow<StudentTaskSubmitState> = _taskSubmitState.asStateFlow()

    private val _taskSubmitMessage = MutableStateFlow("")
    val taskSubmitMessage: StateFlow<String> = _taskSubmitMessage.asStateFlow()

    private val _taskSubmitLogs = MutableStateFlow<List<String>>(emptyList())
    val taskSubmitLogs: StateFlow<List<String>> = _taskSubmitLogs.asStateFlow()

    private val _taskSubmitTargetTask = MutableStateFlow<TaskEntity?>(null)
    val taskSubmitTargetTask: StateFlow<TaskEntity?> = _taskSubmitTargetTask.asStateFlow()

    private val _taskSubmitScore = MutableStateFlow<Int>(0)
    val taskSubmitScore: StateFlow<Int> = _taskSubmitScore.asStateFlow()

    private val _taskSubmitMarkedTimestamp = MutableStateFlow<Long?>(null)
    val taskSubmitMarkedTimestamp: StateFlow<Long?> = _taskSubmitMarkedTimestamp.asStateFlow()

    fun resetTaskSubmitState() {
        _taskSubmitState.value = StudentTaskSubmitState.IDLE
        _taskSubmitMessage.value = ""
        _taskSubmitLogs.value = emptyList()
        _taskSubmitTargetTask.value = null
        _taskSubmitScore.value = 0
        _taskSubmitMarkedTimestamp.value = null
        bleManager.stopScan()
        bleManager.clearBleObject()
    }

    init {
        // Ensure clean BLE state/objects on student startup
        bleManager.resetBleStateOnStartup(force = false)

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

        currentProfile = repository.currentStudentProfile
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)

        myAttendanceLogs = currentProfile
            .flatMapLatest { profile ->
                if (profile != null) repository.getAttendanceForStudent(profile.sid)
                else flowOf(emptyList())
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

        hasCheckedInToday = kotlinx.coroutines.flow.combine(myAttendanceLogs, _registeredClassroom) { logs, classroom ->
            val todayYmd = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date())
            val todayMdy = java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date())
            val prefs = getApplication<android.app.Application>().getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
            val classroomId = classroom?.cid ?: ""
            
            val prefCheckedIn = (classroomId.isNotBlank() && prefs.getBoolean("checked_in_today_${classroomId}_$todayYmd", false)) ||
                                prefs.getBoolean("checked_in_today_global_$todayYmd", false)

            prefCheckedIn || logs.any { log ->
                val matchesClassroom = classroomId.isBlank() || log.classroomId == classroomId
                matchesClassroom && (log.date.startsWith(todayMdy) || log.date.startsWith(todayYmd)) &&
                    (log.status.equals("PRESENT", ignoreCase = true) || log.status.equals("LATE", ignoreCase = true))
            }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

        mySubmissions = currentProfile
            .flatMapLatest { profile ->
                if (profile != null) repository.getSubmissionsForStudent(profile.sid)
                else flowOf(emptyList())
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        classroomApprovedStudents = _registeredClassroom
            .flatMapLatest { classroom ->
                if (classroom != null) repository.getApprovedStudents(classroom.cid)
                else flowOf(emptyList())
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        classroomAttendanceLogs = _registeredClassroom
            .flatMapLatest { classroom ->
                if (classroom != null) repository.getAttendanceForClassroom(classroom.cid)
                else flowOf(emptyList())
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        classroomTasks = _registeredClassroom
            .flatMapLatest { classroom ->
                if (classroom != null) repository.getTasksForClassroom(classroom.cid)
                else flowOf(emptyList())
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        viewModelScope.launch {
            repository.activeBoundClassroom.collect { classroom ->
                _registeredClassroom.value = classroom
                _isClassroomLoaded.value = true
                if (classroom != null) {
                    _isClassroomExpired.value = false
                    _showClassroomExpiredDialog.value = false
                    if (_registrationStatus.value == "NOT_REGISTERED") {
                        _registrationStatus.value = "APPROVED"
                    }
                    val prefs = getApplication<android.app.Application>().getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                    if (!prefs.contains("classroom_joined_timestamp_${classroom.cid}")) {
                        prefs.edit().putLong("classroom_joined_timestamp_${classroom.cid}", System.currentTimeMillis()).apply()
                    }
                    if (prefs.getBoolean("remind_check_in", true)) {
                        com.example.util.ReminderNotificationHelper.checkAndPostCheckInReminder(getApplication())
                    }
                } else {
                    _registeredClassroom.value = null
                    _registrationStatus.value = "NOT_REGISTERED"
                    _registrationTasks.value = emptyList()
                }
            }
        }

        viewModelScope.launch {
            repository.currentStudentProfile.collect { profile ->
                if (profile == null) {
                    _registeredClassroom.value = null
                    _registrationStatus.value = "NOT_REGISTERED"
                    _registrationTasks.value = emptyList()
                    _regDialogStep.value = StudentRegDialogStep.DISCOVERING
                }
            }
        }

        viewModelScope.launch {
            bleManager.lastTxResponse.collect { payload ->
                if (payload.isNotBlank()) {
                    val currentLogs = _checkInLogs.value
                    if (currentLogs.isEmpty() || currentLogs.last() != payload) {
                        _checkInLogs.value = currentLogs + payload
                    }
                    if (_taskSubmitState.value != StudentTaskSubmitState.IDLE) {
                        val submitLogs = _taskSubmitLogs.value
                        if (submitLogs.isEmpty() || submitLogs.last() != payload) {
                            _taskSubmitLogs.value = submitLogs + payload
                        }
                    }
                }
            }
        }

        observeIncomingPackets()
        observeConnectionState()
        startContinuousScanning()
        observeRegisteredDeviceAvailability()
    }

    private fun startContinuousScanning() {
        viewModelScope.launch {
            var lastForceRestart = 0L
            while (isActive) {
                val hasPerms = BlePermissionUtils.hasPermissions(getApplication())
                val isBt = bleManager.isBluetoothEnabled()
                val isLoc = BlePermissionUtils.isLocationEnabled(getApplication())
                
                if (hasPerms && isBt && isLoc) {
                    val conn = bleManager.connectionState.value
                    // Skip background scan logic if a manual transaction is already in progress
                    val isBusy = isManualSyncActive || 
                                 _syncState.value != StudentSyncState.IDLE || 
                                 _checkInState.value != StudentCheckInState.IDLE || 
                                 _taskSubmitState.value != StudentTaskSubmitState.IDLE

                    if (!isBusy && (conn is StudentBleManager.ConnectionState.Disconnected || conn is StudentBleManager.ConnectionState.Scanning)) {
                        val now = System.currentTimeMillis()
                        // Ensure scan is active and force a clean restart every 6 seconds to prevent stalling
                        val force = (now - lastForceRestart > 6000L) || !bleManager.isScanning
                        
                        if (force || !bleManager.isScanning) {
                            Log.d("StudentViewModel", "Continuous scan loop: Ensuring active scan (force=$force)")
                            bleManager.startScan(forceRestart = force)
                            if (force) lastForceRestart = now
                        }
                    }
                } else {
                    if (bleManager.isScanning) {
                        bleManager.stopScan()
                    }
                }
                delay(1000)
            }
        }

        // Action-based scan refresh for student: Re-scan immediately if target classroom or registration criteria changes
        viewModelScope.launch {
            combine(
                _registrationDeviceName,
                _registrationDeviceAddress,
                _registeredClassroom
            ) { name, addr, classroom -> Triple(name, addr, classroom) }
                .collect {
                    Log.d("StudentViewModel", "Student action criteria changed. Refreshing scan results.")
                    if (bleManager.isBluetoothEnabled()) {
                        bleManager.startScan(forceRestart = true)
                    }
                }
        }
    }

    fun triggerManualRefresh() {
        viewModelScope.launch {
            if (bleManager.isBluetoothEnabled()) {
                bleManager.stopScan()
                delay(300)
                bleManager.startScan()
            }
        }
    }

    private fun observeRegisteredDeviceAvailability() {
        viewModelScope.launch {
            bleManager.scannedDevices.collectLatest { devices ->
                val classroom = _registeredClassroom.value
                val targetMac = classroom?.deviceAddress
                val targetName = classroom?.classroomName

                if (!targetMac.isNullOrBlank()) {
                    val deviceByMac = devices.find { d -> d.address.equals(targetMac, ignoreCase = true) }
                    if (deviceByMac != null) {
                        val nameMismatch = deviceByMac.name != null && 
                                !targetName.isNullOrBlank() && 
                                !deviceByMac.name.equals(targetName, ignoreCase = true) && 
                                !deviceByMac.name.contains(targetName, ignoreCase = true)
                        val isFactory = deviceByMac.state == DeviceState.FACTORY_MODE
                        if (isFactory || nameMismatch) {
                            if (!_isClassroomExpired.value) {
                                _isClassroomExpired.value = true
                                _showClassroomExpiredDialog.value = true
                            }
                            if (bleManager.connectionState.value is StudentBleManager.ConnectionState.Connected) {
                                bleManager.disconnect()
                            }
                        } else {
                            // Device is in range and valid (re-registered or normal), reset expiration
                            _isClassroomExpired.value = false
                            _showClassroomExpiredDialog.value = false
                        }
                    }
                }

                val foundDevice = devices.find { d ->
                    val macMatches = !targetMac.isNullOrBlank() && d.address.equals(targetMac, ignoreCase = true)
                    val nameMatches = !targetName.isNullOrBlank() && (
                        d.name.equals(targetName, ignoreCase = true) ||
                        d.name.contains(targetName, ignoreCase = true)
                    )
                    val uuidMatches = d.hasServiceUuid

                    macMatches || nameMatches || uuidMatches
                }

                if (foundDevice != null) {
                    _isRegisteredDeviceInRange.value = true
                    _registeredDeviceState.value = foundDevice.state
                    val isSessionActive = (foundDevice.state == DeviceState.SESSION_ACTIVE_OPEN || foundDevice.state == DeviceState.SESSION_ACTIVE_BUSY)
                    _isRegisteredSessionActive.value = isSessionActive
                    _registeredDeviceRawHex.value = foundDevice.state.manufacturerHex

                    if (classroom != null) {
                        val checkedIn = isCheckedInTodayForClassroom(classroom.cid)
                        if (checkedIn) {
                            // Already checked in today! Ensure finding_calis flag is cleared and scan loop stopped.
                            val prefs = getApplication<android.app.Application>().getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                            if (prefs.getBoolean("finding_calis_${classroom.cid}", false)) {
                                prefs.edit().putBoolean("finding_calis_${classroom.cid}", false).apply()
                                com.example.util.ReminderNotificationHelper.stopFindingCalisScanLoop(getApplication())
                            }
                        } else if (isSessionActive) {
                            val prefs = getApplication<android.app.Application>().getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                            val isFindingCalis = prefs.getBoolean("finding_calis_${classroom.cid}", false)

                            // Auto check-in MUST ONLY occur during the "Finding Calis Device" stage of the Check-In reminder,
                            // and MUST NOT run if the user is syncing (manual refresh), submitting a task, or registering.
                            val isIdleForAutoCheckIn = !isManualSyncActive &&
                                                       _syncState.value == StudentSyncState.IDLE &&
                                                       _taskSubmitState.value == StudentTaskSubmitState.IDLE &&
                                                       _registrationStatus.value != "PENDING" &&
                                                       _checkInState.value == StudentCheckInState.IDLE

                            if (isFindingCalis && isIdleForAutoCheckIn) {
                                Log.d("StudentViewModel", "Active session detected for ${classroom.classroomName} during Finding Calis Device stage. Automatically performing check-in process!")
                                checkIn()
                            }
                        }
                    }
                } else {
                    // Use a 5-second grace period before considering device out of range
                    val now = System.currentTimeMillis()
                    val targetMac = classroom?.deviceAddress
                    val targetName = classroom?.classroomName
                    
                    val stillInRange = devices.any { d ->
                        val macMatches = !targetMac.isNullOrBlank() && d.address.equals(targetMac, ignoreCase = true)
                        val nameMatches = !targetName.isNullOrBlank() && (
                            d.name.equals(targetName, ignoreCase = true) ||
                            d.name.contains(targetName, ignoreCase = true)
                        )
                        val isRecent = (now - d.lastSeenTimestamp) <= 15000L
                        (macMatches || nameMatches || d.hasServiceUuid) && isRecent
                    }

                    if (!stillInRange) {
                        _isRegisteredDeviceInRange.value = false
                        _registeredDeviceState.value = DeviceState.UNKNOWN
                        _isRegisteredSessionActive.value = false
                        _registeredDeviceRawHex.value = ""
                    }
                }
            }
        }
    }

    private fun observeConnectionState() {
        viewModelScope.launch {
            bleManager.connectionState.collect { conn ->
                viewModelScope.launch {
                    Log.d("CALIS", "StudentViewModel connectionState observed concurrently: $conn, regDialogStep=${_regDialogStep.value}, syncState=${_syncState.value}")
                    if (conn is StudentBleManager.ConnectionState.Connected) {
                        joinTimeoutJob?.cancel()
                        if (_regDialogStep.value == StudentRegDialogStep.CONNECTING) {
                            _regDialogStep.value = StudentRegDialogStep.REGISTERING
                            registerToClassroom()
                        } else if (_syncState.value == StudentSyncState.CONNECTING || isManualSyncActive) {
                            _syncState.value = StudentSyncState.SYNCING
                            _syncMessage.value = "Connected. Transmitting REQ_UPD|${currentProfile.value?.sid}..."
                            val profile = currentProfile.value
                            if (profile != null) {
                                val reqPacket = BlePacket.RequestUpdateRequest(sid = profile.sid)
                                bleManager.sendPacket(reqPacket) { success ->
                                    if (success) {
                                        viewModelScope.launch {
                                            _syncMessage.value = "REQ_UPD sent. Waiting for schedule & tasks..."
                                        }
                                    }
                                }
                            }
                        } else if (_checkInState.value == StudentCheckInState.CONNECTING) {
                            _checkInState.value = StudentCheckInState.CHECKING_IN
                            _checkInMessage.value = "Requesting MTU 255 & enabling RX/TX notifications..."
                            _checkInLogs.value = _checkInLogs.value + "GATT: Requesting MTU 255 & enabling RX/TX notifications..."
                            delay(600)
                            _checkInMessage.value = "Connected. Sending Check-In request..."
                            _checkInLogs.value = _checkInLogs.value + "GATT: Connected successfully."
                            val profile = currentProfile.value
                            if (profile != null) {
                                val chkInPacket = BlePacket.CheckInRequest(sid = profile.sid)
                                _checkInLogs.value = _checkInLogs.value + "RX <- Transmitting packet: ${chkInPacket.encodeForDevice()}"
                                bleManager.sendPacket(chkInPacket) { success ->
                                    if (success) {
                                        _checkInState.value = StudentCheckInState.WAITING_FOR_ACCEPT
                                        _checkInMessage.value = "Check-In request sent. Waiting for confirmation..."
                                        _checkInLogs.value = _checkInLogs.value + "RX -> Packet transmitted. Waiting for TX notifications..."
                                    } else {
                                        _checkInState.value = StudentCheckInState.FAILED
                                        _checkInMessage.value = "Failed to send check-in packet."
                                        _checkInLogs.value = _checkInLogs.value + "Error: Failed to transmit packet on RX."
                                    }
                                }
                            }
                        } else if (_taskSubmitState.value == StudentTaskSubmitState.CONNECTING) {
                            _taskSubmitState.value = StudentTaskSubmitState.SUBMITTING
                            _taskSubmitMessage.value = "Requesting MTU 255 & enabling RX/TX notifications..."
                            _taskSubmitLogs.value = _taskSubmitLogs.value + "GATT: Requesting MTU 255 & enabling RX/TX notifications..."
                            delay(600)
                            _taskSubmitMessage.value = "Connected. Sending Task Submission (TK_SBM)..."
                            _taskSubmitLogs.value = _taskSubmitLogs.value + "GATT: Connected successfully."
                            val profile = currentProfile.value
                            val targetTask = _taskSubmitTargetTask.value
                            val score = _taskSubmitScore.value
                            if (profile != null && targetTask != null) {
                                val tkSbmPacket = BlePacket.TaskSubmissionRequest(
                                    sid = profile.sid,
                                    tkid = targetTask.tkid,
                                    score = score.toString()
                                )
                                val packetStr = tkSbmPacket.encodeForDevice()
                                _taskSubmitLogs.value = _taskSubmitLogs.value + "RX <- Transmitting packet: $packetStr"
                                bleManager.sendPacket(tkSbmPacket) { success ->
                                    if (success) {
                                        _taskSubmitState.value = StudentTaskSubmitState.WAITING_FOR_MARKED
                                        _taskSubmitMessage.value = "TK_SBM packet sent. Waiting for Teacher's evaluation..."
                                        _taskSubmitLogs.value = _taskSubmitLogs.value + "RX -> Packet transmitted. Waiting for TX notifications..."
                                    } else {
                                        _taskSubmitState.value = StudentTaskSubmitState.FAILED
                                        _taskSubmitMessage.value = "Failed to send TK_SBM packet."
                                        _taskSubmitLogs.value = _taskSubmitLogs.value + "Error: Failed to transmit packet on RX."
                                    }
                                }
                            }
                        }
                    } else if (conn is StudentBleManager.ConnectionState.Error) {
                        if (_syncState.value == StudentSyncState.CONNECTING || _syncState.value == StudentSyncState.SYNCING) {
                            _syncState.value = StudentSyncState.ERROR
                            _syncMessage.value = "Failed to communicate with classroom device. Try again later."
                            isManualSyncActive = false
                            bleManager.clearBleObject()
                        }
                        if (_taskSubmitState.value == StudentTaskSubmitState.SUBMITTING) {
                            _taskSubmitState.value = StudentTaskSubmitState.FAILED
                            _taskSubmitMessage.value = "Failed to communicate with classroom device. Try again later."
                            _taskSubmitLogs.value = _taskSubmitLogs.value + "Error: BLE Connection error."
                            bleManager.clearBleObject()
                        }
                        if (_regDialogStep.value == StudentRegDialogStep.CONNECTING) {
                            val elapsed = System.currentTimeMillis() - joinConnectionStartTime
                            if (elapsed >= 10000L) {
                                handleJoinConnectionFailure("Failed to connect. Try again later.")
                            } else {
                                delay(1000)
                                if (_regDialogStep.value == StudentRegDialogStep.CONNECTING &&
                                    _registrationStatus.value == "NOT_REGISTERED" &&
                                    _registrationDeviceAddress.value.isNotBlank()
                                ) {
                                    bleManager.connect(_registrationDeviceAddress.value)
                                }
                            }
                        }
                    } else if (conn is StudentBleManager.ConnectionState.Disconnected) {
                        if (_syncState.value == StudentSyncState.CONNECTING) {
                            _syncState.value = StudentSyncState.ERROR
                            _syncMessage.value = "Failed to connect to classroom device."
                            isManualSyncActive = false
                        } else if (_syncState.value == StudentSyncState.SYNCING) {
                            _syncState.value = StudentSyncState.ERROR
                            _syncMessage.value = "Disconnected from classroom device."
                            isManualSyncActive = false
                        }
                        if (_regDialogStep.value == StudentRegDialogStep.CONNECTING) {
                            val elapsed = System.currentTimeMillis() - joinConnectionStartTime
                            if (elapsed >= 10000L) {
                                handleJoinConnectionFailure("Failed to connect. Try again later.")
                            } else {
                                delay(1000)
                                if (_regDialogStep.value == StudentRegDialogStep.CONNECTING &&
                                    _registrationStatus.value == "NOT_REGISTERED" &&
                                    _registrationDeviceAddress.value.isNotBlank()
                                ) {
                                    bleManager.connect(_registrationDeviceAddress.value)
                                }
                            }
                        } else if (_regDialogStep.value == StudentRegDialogStep.REGISTERING || _registrationStatus.value == "PENDING") {
                            handleJoinConnectionFailure("Disconnected from classroom device. Registration not completed.")
                        } else {
                            // After the user is disconnected, clear BLE state/object and scan continuously for the registered Calis Device as long as Bluetooth is enabled
                            bleManager.clearBleObject()
                            if (bleManager.isBluetoothEnabled()) {
                                bleManager.startScan()
                            }
                        }
                    }
                }
            }
        }
    }

    fun startBleScan(forceRestart: Boolean = true) {
        bleManager.startScan(forceRestart = forceRestart)
    }

    private var isManualConnectAllowed = false

    fun stopBleScan() {
        bleManager.stopScan()
    }

    fun connectToDevice(address: String) {
        if (!isManualConnectAllowed) {
            Log.e("CALIS", "Automatic connection attempt blocked! Connection only allowed via manual refresh.")
            return
        }
        bleManager.connect(address)
        isManualConnectAllowed = false // Reset after attempt
    }

    fun disconnectDevice() {
        syncScanJob?.cancel()
        isManualSyncActive = false
        _syncState.value = StudentSyncState.IDLE
        bleManager.stopScan()
        bleManager.clearBleObject()
    }

    // --- STUDENT REGISTRATION DIALOG FLOW ---
    fun startJoinDialog() {
        _joinErrorMessage.value = null
        if (_registeredClassroom.value == null) {
            _regDialogStep.value = StudentRegDialogStep.DISCOVERING
            _registrationStatus.value = "NOT_REGISTERED"
            _registrationDeviceAddress.value = ""
            _registrationDeviceName.value = ""
            _registrationTasks.value = emptyList()
        }
        startBleScan()
    }

    fun initiateJoinClassroom(address: String, name: String) {
        joinTimeoutJob?.cancel()
        _joinErrorMessage.value = null
        joinConnectionStartTime = System.currentTimeMillis()
        _registrationDeviceAddress.value = address
        _registrationDeviceName.value = name
        _regDialogStep.value = StudentRegDialogStep.CONNECTING
        _registrationStatus.value = "NOT_REGISTERED"
        _registrationTasks.value = emptyList()
        bleManager.stopScan()
        bleManager.connect(address)

        joinTimeoutJob = viewModelScope.launch {
            delay(10000L)
            if (_regDialogStep.value == StudentRegDialogStep.CONNECTING && _registrationStatus.value == "NOT_REGISTERED") {
                Log.w("CALIS", "Join classroom connection timed out after 10s")
                handleJoinConnectionFailure("Failed to connect. Try again later.")
            }
        }
    }

    fun handleJoinConnectionFailure(errorMessage: String = "Failed to connect. Try again later.") {
        joinTimeoutJob?.cancel()
        _joinErrorMessage.value = errorMessage
        _regDialogStep.value = StudentRegDialogStep.DISCOVERING
        _registrationStatus.value = "NOT_REGISTERED"
        _registrationDeviceAddress.value = ""
        _registrationDeviceName.value = ""
        _registrationTasks.value = emptyList()
        bleManager.clearBleObject()
        startBleScan()
    }

    fun stopRegistrationFlow() {
        joinTimeoutJob?.cancel()
        _joinErrorMessage.value = null
        _regDialogStep.value = StudentRegDialogStep.DISCOVERING
        _registrationStatus.value = "NOT_REGISTERED"
        _registrationDeviceAddress.value = ""
        _registrationDeviceName.value = ""
        _registrationTasks.value = emptyList()
        bleManager.stopScan()
        bleManager.clearBleObject()
    }

    fun registerToClassroom() {
        Log.d("CALIS", "registerToClassroom called, profile: ${currentProfile.value}, status: ${_registrationStatus.value}")
        if (_registrationStatus.value != "NOT_REGISTERED") {
            Log.d("CALIS", "registerToClassroom skipped: registration already in progress (${_registrationStatus.value})")
            return
        }
        val profile = currentProfile.value ?: return
        _registrationStatus.value = "PENDING"
        _registrationTasks.value = emptyList()
        val regPacket = BlePacket.StudentRegRequest(
            sid = profile.sid,
            st = profile.fullName,
            sex = profile.sex,
            lrn = profile.lrn,
            birth = profile.birth
        )
        bleManager.sendPacket(regPacket) { success ->
            if (success) {
                viewModelScope.launch {
                    _userMessage.emit("Request sent!")
                }
            } else {
                _registrationStatus.value = "NOT_REGISTERED"
            }
        }
    }

    fun resetRegistrationState() {
        joinTimeoutJob?.cancel()
        _joinErrorMessage.value = null
        _registrationStatus.value = "NOT_REGISTERED"
        _regDialogStep.value = StudentRegDialogStep.DISCOVERING
        _registrationDeviceAddress.value = ""
        _registrationDeviceName.value = ""
        _registrationTasks.value = emptyList()
        bleManager.stopScan()
        bleManager.clearBleObject()
    }

    // Complete state reset on student account deletion
    fun resetAllState() {
        joinTimeoutJob?.cancel()
        _joinErrorMessage.value = null
        _registeredClassroom.value = null
        _registrationStatus.value = "NOT_REGISTERED"
        _registrationTasks.value = emptyList()
        _regDialogStep.value = StudentRegDialogStep.DISCOVERING
        _registrationDeviceAddress.value = ""
        _registrationDeviceName.value = ""
        checkInJob?.cancel()
        checkInJob = null
        isManualSyncActive = false
        _syncState.value = StudentSyncState.IDLE
        _syncMessage.value = ""
        syncScanJob?.cancel()
        bleManager.stopScan()
        bleManager.clearBleObject()
    }

    // --- CHECK IN (ATTENDANCE) ---
    fun checkIn() {
        val classroom = _registeredClassroom.value
        val profile = currentProfile.value
        if (classroom == null || profile == null) {
            viewModelScope.launch {
                _userMessage.emit("Join a classroom first")
            }
            return
        }

        if (isCheckedInTodayForClassroom(classroom.cid)) {
            viewModelScope.launch {
                _userMessage.emit("You have already checked-in for today")
            }
            return
        }

        if (isManualSyncActive || _syncState.value != StudentSyncState.IDLE) {
            Log.d("StudentViewModel", "checkIn skipped: manual sync is currently in progress")
            return
        }

        if (!bleManager.isBluetoothEnabled()) {
            viewModelScope.launch {
                _userMessage.emit("Enable Bluetooth first")
            }
            return
        }

        _checkInTasks.value = emptyList()
        _checkInState.value = StudentCheckInState.DISCOVERING
        _checkInMessage.value = "Searching for classroom: ${classroom.classroomName}..."

        bleManager.stopScan()
        bleManager.startScan()

        // Start a job to scan and automatically connect when found
        checkInJob?.cancel()
        checkInJob = viewModelScope.launch {
            if (isCheckedInTodayForClassroom(classroom.cid)) {
                _checkInState.value = StudentCheckInState.IDLE
                _checkInMessage.value = ""
                return@launch
            }
            if (isManualSyncActive || _syncState.value != StudentSyncState.IDLE) {
                _checkInState.value = StudentCheckInState.IDLE
                _checkInMessage.value = ""
                return@launch
            }
            val targetMac = classroom.deviceAddress
            val targetName = classroom.classroomName
            var foundAddress: String? = null

            // Poll for scanned devices for up to 15 seconds with a 2-second scan refresh
            val startTime = System.currentTimeMillis()
            var lastScanRestart = startTime
            while (System.currentTimeMillis() - startTime < 15000 && _checkInState.value == StudentCheckInState.DISCOVERING) {
                if (System.currentTimeMillis() - lastScanRestart > 8000) {
                    Log.d("StudentViewModel", "Discovery loop: Refreshing BLE scan (8 seconds refresh rule to avoid throttling)")
                    bleManager.startScan(forceRestart = true)
                    lastScanRestart = System.currentTimeMillis()
                }
                val devices = bleManager.scannedDevices.value
                val found = devices.find { d ->
                    val macMatches = !targetMac.isNullOrBlank() && d.address.equals(targetMac, ignoreCase = true)
                    val nameMatches = !targetName.isNullOrBlank() && (
                        d.name.equals(targetName, ignoreCase = true) ||
                        d.name.contains(targetName, ignoreCase = true)
                    )
                    val serviceMatches = d.hasServiceUuid && (macMatches || nameMatches || targetMac.isNullOrBlank())
                    macMatches || nameMatches || serviceMatches
                }
                if (found != null) {
                    foundAddress = found.address
                    break
                }
                delay(400)
            }

            if (foundAddress != null && _checkInState.value == StudentCheckInState.DISCOVERING) {
                var connected = false
                for (attempt in 1..2) {
                    _checkInState.value = StudentCheckInState.CONNECTING
                    _checkInMessage.value = if (attempt == 1) {
                        "Device found. Connecting to ${classroom.classroomName}..."
                    } else {
                        "Retrying connection (Attempt 2) to ${classroom.classroomName}..."
                    }
                    bleManager.stopScan()
                    
                    // Always start with a clean BLE object state to prevent stale GATT handles
                    bleManager.clearBleObject()
                    if (attempt > 1) delay(300)
                    
                    bleManager.connect(foundAddress)

                    val connStart = System.currentTimeMillis()
                    // Increase timeout to 10 seconds to allow for MTU exchange and service discovery
                    while (System.currentTimeMillis() - connStart < 10000) {
                        if (bleManager.connectionState.value is StudentBleManager.ConnectionState.Connected) {
                            connected = true
                            break
                        }
                        delay(200)
                    }
                    if (connected) break

                    // If still connecting after 5 seconds, clear BLE object state before 2nd attempt
                    Log.w("StudentViewModel", "Connection attempt $attempt still connecting after 5 seconds. Clearing BLE object state.")
                    bleManager.clearBleObject()
                }

                if (connected) {
                    val chkStart = System.currentTimeMillis()
                    while (System.currentTimeMillis() - chkStart < 15000 &&
                           (_checkInState.value == StudentCheckInState.CHECKING_IN ||
                            _checkInState.value == StudentCheckInState.WAITING_FOR_ACCEPT ||
                            _checkInState.value == StudentCheckInState.RECEIVING_TASKS)) {
                        delay(200)
                    }
                    if (_checkInState.value == StudentCheckInState.CHECKING_IN || _checkInState.value == StudentCheckInState.WAITING_FOR_ACCEPT) {
                        _checkInState.value = StudentCheckInState.FAILED
                        _checkInMessage.value = "Check-in timed out waiting for teacher acceptance."
                        bleManager.clearBleObject()
                    }
                } else {
                    _checkInState.value = StudentCheckInState.FAILED
                    _checkInMessage.value = "Failed to connect to classroom device. Try again later."
                    bleManager.clearBleObject()
                    _userMessage.emit("Error: Failed to establish BLE connection with Calis Device after retry")
                }
            } else if (_checkInState.value == StudentCheckInState.DISCOVERING) {
                _checkInState.value = StudentCheckInState.FAILED
                _checkInMessage.value = "Classroom device not found. Make sure the device is in range and session is active."
                bleManager.stopScan()
                bleManager.clearBleObject()
            }

            if (_checkInState.value == StudentCheckInState.FAILED) {
                viewModelScope.launch {
                    delay(5000)
                    if (_checkInState.value == StudentCheckInState.FAILED) {
                        _checkInState.value = StudentCheckInState.IDLE
                        bleManager.startScan()
                    }
                }
            }
        }
    }

    // --- SUBMIT TASK SCORE ---
    fun submitTaskScore(taskId: String, score: Int) {
        val profile = currentProfile.value ?: return
        val classroom = _registeredClassroom.value
        val timestamp = System.currentTimeMillis() / 1000
        viewModelScope.launch {
            repository.recordTaskSubmission(
                taskId = taskId,
                studentId = profile.sid,
                studentName = profile.fullName,
                score = score,
                timestamp = timestamp
            )
            // Ensure attendance record exists for this date in the current classroom
            if (classroom != null) {
                val todayDateStr = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date())
                val existingLogs = repository.getAttendanceForClassroom(classroom.cid).first()
                val hasTodayLog = existingLogs.any { 
                    it.studentId == profile.sid && it.date.startsWith(todayDateStr)
                }
                if (!hasTodayLog) {
                    repository.recordStudentAttendance(
                        classroomId = classroom.cid,
                        studentId = profile.sid,
                        studentName = profile.fullName,
                        status = "PRESENT",
                        timestamp = timestamp
                    )
                }
                // Clear finding_calis flag and mark today's check-in preference persistently
                val prefs = getApplication<android.app.Application>().getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                prefs.edit()
                    .putBoolean("checked_in_today_${classroom.cid}_$todayDateStr", true)
                    .putBoolean("finding_calis_${classroom.cid}", false)
                    .apply()
                com.example.util.ReminderNotificationHelper.stopFindingCalisScanLoop(getApplication())
                com.example.util.ReminderNotificationHelper.cancelCheckInAndLateNotifications(getApplication(), classroom.cid)
            }
            com.example.util.ReminderNotificationHelper.cancelTaskNotification(getApplication(), taskId)
            com.example.util.ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(getApplication())
        }
    }

    // --- SUBMIT TASK SCORE VIA BLE (TK_SBM) ---
    fun submitTaskScoreBle(task: TaskEntity, score: Int) {
        val classroom = _registeredClassroom.value
        val profile = currentProfile.value
        if (classroom == null || profile == null) {
            viewModelScope.launch {
                _userMessage.emit("Join a classroom first")
            }
            return
        }

        if (!bleManager.isBluetoothEnabled()) {
            viewModelScope.launch {
                _userMessage.emit("Enable Bluetooth first")
            }
            return
        }

        if (!_isRegisteredSessionActive.value) {
            viewModelScope.launch {
                _userMessage.emit("Calis Device session is currently inactive or device not detected")
            }
            return
        }

        _taskSubmitTargetTask.value = task
        _taskSubmitScore.value = score
        _taskSubmitMarkedTimestamp.value = null
        _taskSubmitState.value = StudentTaskSubmitState.DISCOVERING
        _taskSubmitMessage.value = "Searching for classroom: ${classroom.classroomName}..."
        _taskSubmitLogs.value = listOf(
            "Task submission initiated for \"${task.title}\" (ID: ${task.tkid})",
            "Score to submit: $score",
            "Searching for classroom ${classroom.classroomName}..."
        )

        bleManager.stopScan()
        bleManager.startScan()

        viewModelScope.launch {
            val targetMac = classroom.deviceAddress
            val targetName = classroom.classroomName
            var foundAddress: String? = null

            val startTime = System.currentTimeMillis()
            while (System.currentTimeMillis() - startTime < 15000 && _taskSubmitState.value == StudentTaskSubmitState.DISCOVERING) {
                val devices = bleManager.scannedDevices.value
                val found = devices.find { d ->
                    (!targetMac.isNullOrBlank() && d.address.equals(targetMac, ignoreCase = true)) ||
                    (!targetName.isNullOrBlank() && (d.name.equals(targetName, ignoreCase = true) || d.name.contains(targetName, ignoreCase = true))) ||
                    d.hasServiceUuid
                }
                if (found != null) {
                    foundAddress = found.address
                    break
                }
                delay(500)
            }

            if (foundAddress != null && _taskSubmitState.value == StudentTaskSubmitState.DISCOVERING) {
                var connected = false
                for (attempt in 1..2) {
                    _taskSubmitState.value = StudentTaskSubmitState.CONNECTING
                    if (attempt == 1) {
                        _taskSubmitMessage.value = "Device found. Connecting to ${classroom.classroomName}..."
                        _taskSubmitLogs.value = _taskSubmitLogs.value + "Device found ($foundAddress). Connecting..."
                    } else {
                        _taskSubmitMessage.value = "Retrying connection (Attempt 2)..."
                        _taskSubmitLogs.value = _taskSubmitLogs.value + "Retrying connection (Attempt 2)..."
                    }
                    bleManager.stopScan()
                    
                    // Always start with a clean BLE object state to prevent stale GATT handles
                    bleManager.clearBleObject()
                    if (attempt > 1) delay(300)
                    
                    bleManager.connect(foundAddress)

                    val connStart = System.currentTimeMillis()
                    // Increase timeout to 10 seconds to allow for MTU exchange and service discovery
                    while (System.currentTimeMillis() - connStart < 10000) {
                        if (bleManager.connectionState.value is StudentBleManager.ConnectionState.Connected) {
                            connected = true
                            break
                        }
                        delay(200)
                    }
                    if (connected) break
                }

                if (connected) {
                    val subStart = System.currentTimeMillis()
                    while (System.currentTimeMillis() - subStart < 15000 && 
                           (_taskSubmitState.value == StudentTaskSubmitState.CONNECTING ||
                            _taskSubmitState.value == StudentTaskSubmitState.SUBMITTING || 
                            _taskSubmitState.value == StudentTaskSubmitState.WAITING_FOR_MARKED)) {
                        delay(200)
                    }
                    if (_taskSubmitState.value == StudentTaskSubmitState.SUBMITTING || _taskSubmitState.value == StudentTaskSubmitState.WAITING_FOR_MARKED) {
                        _taskSubmitState.value = StudentTaskSubmitState.FAILED
                        _taskSubmitMessage.value = "Transaction timed out waiting for acknowledgment."
                        _taskSubmitLogs.value = _taskSubmitLogs.value + "Error: Transaction timed out."
                        bleManager.clearBleObject()
                    }
                } else {
                    _taskSubmitState.value = StudentTaskSubmitState.FAILED
                    _taskSubmitMessage.value = "Failed to connect to classroom device. Try again later."
                    _taskSubmitLogs.value = _taskSubmitLogs.value + "Error: Failed to connect after retry."
                    bleManager.clearBleObject()
                    _userMessage.emit("Error: Failed to establish BLE connection for task submission after retry")
                }
            } else if (_taskSubmitState.value == StudentTaskSubmitState.DISCOVERING) {
                _taskSubmitState.value = StudentTaskSubmitState.FAILED
                _taskSubmitMessage.value = "Classroom device not found. Make sure the device is in range and session is active."
                _taskSubmitLogs.value = _taskSubmitLogs.value + "Error: Classroom device not found in range."
                bleManager.stopScan()
                bleManager.clearBleObject()
            }
        }
    }

    // --- MANUAL REFRESH / SYNCHRONIZATION FLOW ---
    fun manualRefresh() {
        val classroom = _registeredClassroom.value
        if (classroom == null) {
            viewModelScope.launch {
                _userMessage.emit("Join a classroom first")
            }
            return
        }
        val targetMac = classroom.deviceAddress
        val targetName = classroom.classroomName

        if (!bleManager.isBluetoothEnabled()) {
            viewModelScope.launch {
                _userMessage.emit("Enable Bluetooth first")
            }
            return
        }

        // Cancel any pending check-in job and reset check-in state
        checkInJob?.cancel()
        checkInJob = null
        if (_checkInState.value != StudentCheckInState.IDLE && _checkInState.value != StudentCheckInState.SUCCESS) {
            _checkInState.value = StudentCheckInState.IDLE
            _checkInMessage.value = ""
            _checkInTasks.value = emptyList()
            _checkInLogs.value = emptyList()
        }

        // Cancel any pending sync job and prioritize user's manual sync
        syncScanJob?.cancel()

        // Set manual sync flag and sync state immediately to establish sync precedence and prevent auto check-in
        isManualSyncActive = true
        _syncState.value = StudentSyncState.CONNECTING
        _syncMessage.value = "Searching for ${classroom.classroomName} to sync..."

        syncScanJob = viewModelScope.launch {
            bleManager.stopScan()
            bleManager.startScan()

            var device: StudentBleManager.ScannedDevice? = null
            val startWait = System.currentTimeMillis()
            while (System.currentTimeMillis() - startWait < 10000 && _syncState.value == StudentSyncState.CONNECTING) {
                val devices = bleManager.scannedDevices.value
                device = devices.find { d ->
                    (!targetMac.isNullOrBlank() && d.address.equals(targetMac, ignoreCase = true)) ||
                    (!targetName.isNullOrBlank() && (d.name.equals(targetName, ignoreCase = true) || d.name.contains(targetName, ignoreCase = true))) ||
                    d.hasServiceUuid
                }
                if (device != null) break
                delay(250)
            }

            if (device == null) {
                _syncState.value = StudentSyncState.ERROR
                _syncMessage.value = "Classroom device not found. Move closer to Calis Device."
                _userMessage.emit("Please move closer to the Calis Device")
                isManualSyncActive = false
                bleManager.stopScan()
                bleManager.clearBleObject()
                return@launch
            }

            // Check if the saved Calis Device is detected to no longer be named as its section/classroom name or is in Factory Mode
            val isExpired = !targetMac.isNullOrBlank() && device.address.equals(targetMac, ignoreCase = true) &&
                    (device.state == DeviceState.FACTORY_MODE || (device.name != null && !device.name.equals(targetName, ignoreCase = true)))

            if (isExpired) {
                _syncState.value = StudentSyncState.ERROR
                _syncMessage.value = "Classroom has Expired"
                if (!_isClassroomExpired.value) {
                    _isClassroomExpired.value = true
                    _showClassroomExpiredDialog.value = true
                }
                _userMessage.emit("Classroom has Expired")
                isManualSyncActive = false
                bleManager.disconnect()
                return@launch
            }

            // Check manufacturer data state: MUST be in an active session (SESSION_ACTIVE_OPEN or SESSION_ACTIVE_BUSY)
            val isSessionActive = device.state == DeviceState.SESSION_ACTIVE_OPEN || device.state == DeviceState.SESSION_ACTIVE_BUSY
            if (!isSessionActive) {
                _syncState.value = StudentSyncState.ERROR
                val stateDescription = when (device.state) {
                    DeviceState.SESSION_INACTIVE -> "Session Inactive (0xFF01) — Waiting for teacher to start session"
                    DeviceState.AUTHENTICATE -> "Authenticate/Idle (0xFF00) — Teacher authentication pending"
                    DeviceState.REGISTERING -> "Registering (0xF1FF)"
                    DeviceState.FACTORY_MODE -> "Factory Mode (0xFFFF)"
                    else -> "State ${device.state.displayName}"
                }
                _syncMessage.value = "Cannot sync: Calis Device is in $stateDescription"
                _userMessage.emit("Cannot sync: Calis Device is in $stateDescription")
                isManualSyncActive = false
                bleManager.disconnect()
                return@launch
            }

            // Device is verified in Session Active state - proceed with manual connection to sync
            // Prefer the address of the device we just found over the potentially stale targetMac
            val connectAddress = device.address
            _syncMessage.value = "Device found. Connecting to ${device.name} to sync..."
            _userMessage.emit("Syncing")

            var connected = false
            for (attempt in 1..2) {
                _syncState.value = StudentSyncState.CONNECTING
                _syncMessage.value = if (attempt == 1) {
                    "Connecting to ${classroom.classroomName} to sync..."
                } else {
                    "Retrying sync connection (Attempt 2) to ${classroom.classroomName}..."
                }
                bleManager.stopScan()
                isManualConnectAllowed = true
                
                // Always start with a clean BLE object state to prevent stale GATT handles (status 133)
                bleManager.clearBleObject()
                delay(300)
                
                bleManager.connect(connectAddress)

                val connStart = System.currentTimeMillis()
                while (System.currentTimeMillis() - connStart < 10000) { // 10s timeout
                    if (bleManager.connectionState.value is StudentBleManager.ConnectionState.Connected) {
                        connected = true
                        break
                    }
                    delay(250)
                }
                if (connected) break

                Log.w("StudentViewModel", "Sync attempt $attempt timed out after 10 seconds. Retrying if possible.")
                bleManager.clearBleObject()
            }

            if (!connected) {
                _syncState.value = StudentSyncState.ERROR
                _syncMessage.value = "Failed to connect to classroom device. Try again later."
                _userMessage.emit("Failed to establish BLE connection with Calis Device")
                isManualSyncActive = false
                bleManager.clearBleObject()
                return@launch
            }

            // Connection established! observeConnectionState sends REQ_UPD.
            // Wait for synchronization to complete (SchedResponse, TaskCommands, DoneResponse) up to 15 seconds.
            val syncStart = System.currentTimeMillis()
            while (System.currentTimeMillis() - syncStart < 15000 &&
                   (_syncState.value == StudentSyncState.CONNECTING || _syncState.value == StudentSyncState.SYNCING)) {
                delay(200)
            }

            if (_syncState.value == StudentSyncState.CONNECTING || _syncState.value == StudentSyncState.SYNCING) {
                _syncState.value = StudentSyncState.ERROR
                _syncMessage.value = "Synchronization timed out waiting for classroom response."
                _userMessage.emit("Sync timed out")
                isManualSyncActive = false
                bleManager.clearBleObject()
            } else {
                isManualSyncActive = false
            }
        }
    }

    fun dismissSyncBanner() {
        syncScanJob?.cancel()
        isManualSyncActive = false
        _syncState.value = StudentSyncState.IDLE
        _syncMessage.value = ""
        bleManager.clearBleObject()
    }

    // --- PACKET OBSERVER ---
    private fun observeIncomingPackets() {
        viewModelScope.launch {
            bleManager.incomingPackets.collect { packet ->
                viewModelScope.launch {
                    when (packet) {
                        is BlePacket.RegApprovedResponse -> {
                            _registrationStatus.value = "APPROVED_WAITING_TASKS"
                            _regDialogStep.value = StudentRegDialogStep.SYNCHRONIZING
                            // Save teacher first to satisfy foreign key constraint
                            repository.saveTeacher(com.example.data.db.TeacherEntity("TEACHER_${packet.cid}", packet.tc))
                            val classroom = ClassroomEntity(
                                cid = packet.cid,
                                teacherId = "TEACHER_${packet.cid}",
                                classroomName = packet.cls,
                                teacherName = packet.tc,
                                days = packet.days,
                                startTime = packet.start,
                                graceTime = packet.grace,
                                endTime = packet.end,
                                isBound = true,
                                deviceAddress = _registrationDeviceAddress.value
                            )
                            _registeredClassroom.value = classroom
                            repository.saveClassroom(classroom)

                            val todayDateStr = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date())
                            val profile = currentProfile.value
                            if (profile != null) {
                                val mySid = profile.sid
                                val existingLogs = repository.getAttendanceForClassroom(packet.cid).first()
                                val alreadyCheckedIn = existingLogs.any { it.date == todayDateStr && it.studentId == mySid }
                                if (!alreadyCheckedIn) {
                                    repository.recordStudentAttendance(
                                        classroomId = packet.cid,
                                        studentId = mySid,
                                        studentName = profile.fullName,
                                        status = "PRESENT",
                                        timestamp = System.currentTimeMillis() / 1000L
                                    )
                                    com.example.util.ReminderNotificationHelper.cancelCheckInAndLateNotifications(getApplication(), packet.cid)
                                }
                            }
                            _userMessage.emit("Registration Approved!")
                        }

                        is BlePacket.RegRejectedResponse -> {
                            _registrationStatus.value = "REJECTED"
                            _regDialogStep.value = StudentRegDialogStep.REJECTED
                            if (_checkInState.value != StudentCheckInState.IDLE) {
                                _checkInState.value = StudentCheckInState.FAILED
                                _checkInMessage.value = "Registration was rejected/not approved by the Teacher."
                            }
                            _userMessage.emit("Registration failed")
                            delay(400)
                            bleManager.clearBleObject()
                        }

                        is BlePacket.AtdAcceptedResponse -> {
                            val profile = currentProfile.value ?: repository.getStudentProfileOnce()
                            var classroom = _registeredClassroom.value
                            if (classroom == null) {
                                classroom = repository.getActiveBoundClassroomSync()
                                if (classroom != null) {
                                    _registeredClassroom.value = classroom
                                }
                            }
                            if (profile != null && classroom != null) {
                                val graceTimeStr = classroom.graceTime
                                val graceMins = parseTimeToMinutes(graceTimeStr)
                                val calendar = java.util.Calendar.getInstance()
                                val packetMillis = if (packet.timestamp > 1_000_000_000_000L) packet.timestamp else if (packet.timestamp > 0L) packet.timestamp * 1000L else System.currentTimeMillis()
                                calendar.timeInMillis = packetMillis
                                val currentMinutes = calendar.get(java.util.Calendar.HOUR_OF_DAY) * 60 + calendar.get(java.util.Calendar.MINUTE)

                                val attendanceStatus = if (graceMins != -1 && currentMinutes > graceMins) {
                                    "LATE"
                                } else {
                                    "PRESENT"
                                }

                                if (_checkInState.value == StudentCheckInState.WAITING_FOR_ACCEPT || _checkInState.value == StudentCheckInState.CHECKING_IN) {
                                    _checkInState.value = StudentCheckInState.RECEIVING_TASKS
                                    _checkInMessage.value = "Check-In Accepted by Teacher! Syncing tasks..."
                                }
                                repository.recordStudentAttendance(
                                    classroomId = classroom.cid,
                                    studentId = profile.sid,
                                    studentName = profile.fullName,
                                    status = attendanceStatus,
                                    timestamp = packet.timestamp
                                )

                                // Mark checked-in flags persistently and clear Finding Calis scan loop
                                val todayYmd = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date())
                                val prefs = getApplication<android.app.Application>().getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                                prefs.edit()
                                    .putBoolean("checked_in_today_${classroom.cid}_$todayYmd", true)
                                    .putBoolean("checked_in_today_global_$todayYmd", true)
                                    .putBoolean("finding_calis_${classroom.cid}", false)
                                    .apply()
                                com.example.util.ReminderNotificationHelper.stopFindingCalisScanLoop(getApplication())

                                com.example.util.ReminderNotificationHelper.updateNotificationToCheckedInSuccess(
                                    getApplication(),
                                    classroom.cid,
                                    classroom.classroomName
                                )
                            }
                            _userMessage.emit("Checked-in")
                        }

                        is BlePacket.AtdRejectedResponse -> {
                            if (_checkInState.value == StudentCheckInState.WAITING_FOR_ACCEPT || _checkInState.value == StudentCheckInState.CHECKING_IN) {
                                _checkInState.value = StudentCheckInState.FAILED
                                _checkInMessage.value = "The classroom has now been deleted"
                            }
                            _userMessage.emit("Classroom has expired")
                            val classroom = _registeredClassroom.value
                            if (classroom != null) {
                                val prefs = getApplication<android.app.Application>().getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                                prefs.edit().putBoolean("finding_calis_${classroom.cid}", false).apply()
                                com.example.util.ReminderNotificationHelper.stopFindingCalisScanLoop(getApplication())
                            }
                            delay(400)
                            bleManager.clearBleObject()
                        }

                        is BlePacket.SchedResponse -> {
                            val current = _registeredClassroom.value
                            if (current != null) {
                                val updated = current.copy(
                                    days = packet.days,
                                    startTime = packet.start,
                                    graceTime = packet.grace,
                                    endTime = packet.end
                                )
                                _registeredClassroom.value = updated
                                repository.saveClassroom(updated)
                            }
                            if (_syncState.value == StudentSyncState.SYNCING) {
                                _syncMessage.value = "Classroom schedule updated (${packet.days})."
                            }
                        }

                        is BlePacket.TaskCommand -> {
                            var classroom = _registeredClassroom.value
                            if (classroom == null) {
                                classroom = repository.getActiveBoundClassroomSync()
                                if (classroom != null) {
                                    _registeredClassroom.value = classroom
                                }
                            }
                            if (classroom != null) {
                                val taskDesc = if (packet.desc.equals(packet.title, ignoreCase = true)) "" else packet.desc
                                val task = TaskEntity(
                                    tkid = packet.tkid,
                                    classroomId = classroom.cid,
                                    title = packet.title,
                                    description = taskDesc,
                                    deadline = packet.deadline,
                                    datePosted = packet.datePosted,
                                    maxScore = packet.score.toIntOrNull() ?: 100
                                )
                                repository.saveTask(task)
                                com.example.util.ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(getApplication())
                                if (_registrationStatus.value == "APPROVED_WAITING_TASKS" || _registrationStatus.value == "RECEIVING_TASKS" || _registrationStatus.value == "APPROVED_WAITING_DONE") {
                                    _registrationStatus.value = "RECEIVING_TASKS"
                                    if (!_registrationTasks.value.any { it.tkid == task.tkid }) {
                                        _registrationTasks.value = _registrationTasks.value + task
                                    }
                                } else if (_registrationStatus.value == "PENDING") {
                                    if (!_registrationTasks.value.any { it.tkid == task.tkid }) {
                                        _registrationTasks.value = _registrationTasks.value + task
                                    }
                                }
                                if (_checkInState.value == StudentCheckInState.RECEIVING_TASKS) {
                                    if (!_checkInTasks.value.any { it.tkid == task.tkid }) {
                                        _checkInTasks.value = _checkInTasks.value + task
                                    }
                                    _checkInMessage.value = "Received task: ${packet.title}"
                                } else if (_checkInState.value == StudentCheckInState.WAITING_FOR_ACCEPT || _checkInState.value == StudentCheckInState.CHECKING_IN) {
                                    if (!_checkInTasks.value.any { it.tkid == task.tkid }) {
                                        _checkInTasks.value = _checkInTasks.value + task
                                    }
                                }
                                if (_syncState.value == StudentSyncState.SYNCING) {
                                    _syncMessage.value = "Received task: ${packet.title}"
                                }
                                _userMessage.emit("New Task Received")
                            }
                        }

                        is BlePacket.MarkedResponse -> {
                            val epochSeconds = if (packet.timestamp <= 0L) System.currentTimeMillis() / 1000L else packet.timestamp
                            _taskSubmitLogs.value = _taskSubmitLogs.value + "TX -> MARKED|$epochSeconds received from Teacher!"
                            val targetTask = _taskSubmitTargetTask.value
                            val score = _taskSubmitScore.value
                            val profile = currentProfile.value
                            val classroom = _registeredClassroom.value
                            if (targetTask != null && profile != null) {
                                repository.recordTaskSubmission(
                                    taskId = targetTask.tkid,
                                    studentId = profile.sid,
                                    studentName = profile.fullName,
                                    score = score,
                                    timestamp = epochSeconds
                                )
                                if (classroom != null) {
                                    val todayDateStr = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date(epochSeconds * 1000L))
                                    val existingLogs = repository.getAttendanceForClassroom(classroom.cid).first()
                                    val hasTodayLog = existingLogs.any {
                                        it.studentId == profile.sid && it.date.startsWith(todayDateStr)
                                    }
                                    if (!hasTodayLog) {
                                        repository.recordStudentAttendance(
                                            classroomId = classroom.cid,
                                            studentId = profile.sid,
                                            studentName = profile.fullName,
                                            status = "PRESENT",
                                            timestamp = epochSeconds
                                        )
                                    }
                                    // Clear finding_calis flag and mark today's check-in preference persistently
                                    val prefs = getApplication<android.app.Application>().getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                                    prefs.edit()
                                        .putBoolean("checked_in_today_${classroom.cid}_$todayDateStr", true)
                                        .putBoolean("finding_calis_${classroom.cid}", false)
                                        .apply()
                                    com.example.util.ReminderNotificationHelper.stopFindingCalisScanLoop(getApplication())
                                    com.example.util.ReminderNotificationHelper.cancelCheckInAndLateNotifications(getApplication(), classroom.cid)
                                }
                                com.example.util.ReminderNotificationHelper.cancelTaskNotification(getApplication(), targetTask.tkid)
                                com.example.util.ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(getApplication())
                            }
                            _taskSubmitMarkedTimestamp.value = epochSeconds
                            _taskSubmitState.value = StudentTaskSubmitState.IDLE
                            _taskSubmitMessage.value = ""
                            _taskSubmitLogs.value = _taskSubmitLogs.value + "Task successfully marked by Teacher."
                            _userMessage.emit("Task Submitted")
                            delay(400)
                            bleManager.clearBleObject()
                        }

                        is BlePacket.DoneResponse -> {
                            if (_registrationStatus.value == "APPROVED_WAITING_TASKS" || _registrationStatus.value == "RECEIVING_TASKS" || _registrationStatus.value == "APPROVED_WAITING_DONE") {
                                _registrationStatus.value = "APPROVED"
                                _regDialogStep.value = StudentRegDialogStep.SUCCESS
                                delay(400)
                                bleManager.clearBleObject()
                            } else if (_registrationStatus.value == "PENDING") {
                                handleJoinConnectionFailure("Registration was not approved by Teacher.")
                            }
                            if (_checkInState.value == StudentCheckInState.RECEIVING_TASKS || _checkInState.value == StudentCheckInState.WAITING_FOR_ACCEPT || _checkInState.value == StudentCheckInState.CHECKING_IN) {
                                _checkInState.value = StudentCheckInState.SUCCESS
                                if (_checkInTasks.value.isEmpty()) {
                                    _checkInMessage.value = "Attendance Marked! Finished taking attendance."
                                } else {
                                    _checkInMessage.value = "Attendance Marked! Finished taking attendance. (${_checkInTasks.value.size} task${if (_checkInTasks.value.size > 1) "s" else ""} synced)"
                                }
                                delay(400)
                                bleManager.clearBleObject()
                            }
                            if (_syncState.value == StudentSyncState.SYNCING) {
                                _syncState.value = StudentSyncState.SUCCESS
                                _syncMessage.value = "Synchronization completed successfully!"
                                _userMessage.emit("Synced Successfully!")
                                delay(400)
                                bleManager.clearBleObject()
                            }
                            if (_taskSubmitState.value == StudentTaskSubmitState.SUBMITTING || _taskSubmitState.value == StudentTaskSubmitState.SUCCESS || _taskSubmitState.value == StudentTaskSubmitState.WAITING_FOR_MARKED) {
                                delay(400)
                                bleManager.clearBleObject()
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}

private fun parseTimeToMinutes(raw: String): Int {
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
    } else {
        hour = clean.toIntOrNull() ?: 0
    }
    
    if (isPM && hour < 12) hour += 12
    if (isAM && hour == 12) hour = 0
    
    return hour * 60 + min
}

