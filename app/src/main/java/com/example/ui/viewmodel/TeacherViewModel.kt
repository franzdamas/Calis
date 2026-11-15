package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ble.TeacherBleManager
import com.example.ble.BlePacket
import com.example.ble.CalisBleService
import com.example.ble.DeviceState
import com.example.data.db.ApprovedStudentEntity
import com.example.data.db.AttendanceEntity
import com.example.data.db.CalisDatabase
import com.example.data.db.ClassroomEntity
import com.example.data.db.PendingRegistrationEntity
import com.example.data.db.TaskEntity
import com.example.data.db.TaskSubmissionEntity
import com.example.data.db.TeacherEntity
import com.example.data.repository.CalisRepository
import com.example.util.CsvExportUtils
import com.example.util.IdGenerator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalCoroutinesApi::class)
class TeacherViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CalisRepository
    val bleManager = TeacherBleManager.getInstance(application)

    val currentTeacher: StateFlow<TeacherEntity?>
    val activeClassroom: StateFlow<ClassroomEntity?>
    val currentClassroom: StateFlow<ClassroomEntity?>
    val targetClassroom: StateFlow<ClassroomEntity?>
    val pendingRegistrations: StateFlow<List<PendingRegistrationEntity>>
    val approvedStudents: StateFlow<List<ApprovedStudentEntity>>
    val attendanceLogs: StateFlow<List<AttendanceEntity>>
    val tasks: StateFlow<List<TaskEntity>>

    val connectionState: StateFlow<TeacherBleManager.ConnectionState> = bleManager.connectionState
    val deviceState: StateFlow<DeviceState> = bleManager.deviceState
    val scannedDevices: StateFlow<List<TeacherBleManager.ScannedDevice>> = bleManager.scannedDevices
    val lastTxResponse: StateFlow<String> = bleManager.lastTxResponse

    private val _selectedTaskForSubmissions = MutableStateFlow<TaskEntity?>(null)
    val selectedTaskForSubmissions: StateFlow<TaskEntity?> = _selectedTaskForSubmissions.asStateFlow()

    private val _isSubmissionsLoading = MutableStateFlow(false)
    val isSubmissionsLoading: StateFlow<Boolean> = _isSubmissionsLoading.asStateFlow()

    val currentSubmissions: StateFlow<List<TaskSubmissionEntity>>
    val currentDeliveries: StateFlow<List<com.example.data.db.TaskDeliveryEntity>>

    private val _userMessage = MutableSharedFlow<String>(extraBufferCapacity = 10)
    val userMessage: SharedFlow<String> = _userMessage.asSharedFlow()

    val isAuthenticated: StateFlow<Boolean> = bleManager.isAuthenticated

    private var hasAttemptedStartupAutoConnect = false

    private val _isUserDisconnected = MutableStateFlow(false)
    val isUserDisconnected: StateFlow<Boolean> = _isUserDisconnected.asStateFlow()

    private val _isDisconnecting = MutableStateFlow(false)
    val isDisconnecting: StateFlow<Boolean> = _isDisconnecting.asStateFlow()

    private val prefs = application.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)

    private val _autoApproveEnabled = MutableStateFlow(prefs.getBoolean("auto_approve_enabled", true))
    val autoApproveEnabled: StateFlow<Boolean> = _autoApproveEnabled.asStateFlow()

    fun setAutoApproveEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("auto_approve_enabled", enabled).apply()
        _autoApproveEnabled.value = enabled
    }

    private val _isWaitingForReconnect = MutableStateFlow(false)
    val isWaitingForReconnectFlow: StateFlow<Boolean> = _isWaitingForReconnect.asStateFlow()
    var isWaitingForReconnect: Boolean
        get() = _isWaitingForReconnect.value
        set(value) {
            _isWaitingForReconnect.value = value
        }

    private val _savedMacAddress = MutableStateFlow<String?>(null)
    val savedMacAddress: StateFlow<String?> = _savedMacAddress.asStateFlow()

    private var lastExplicitSessionEndTimestamp: Long = 0L
    private var lastExplicitSessionStartTimestamp: Long = 0L
    private var hasAutoStartedForCurrentAuth: Boolean = false
    private var hasTriedPostRegistrationScanRetry: Boolean = false
    private var postRegScanRetryJob: kotlinx.coroutines.Job? = null

    private fun triggerPostRegistrationRetryIfNeeded() {
        if (hasTriedPostRegistrationScanRetry) return
        postRegScanRetryJob?.cancel()
        postRegScanRetryJob = viewModelScope.launch {
            kotlinx.coroutines.delay(2000L)
            if (_regDialogStep.value == RegDialogStep.AUTHENTICATING &&
                !hasTriedPostRegistrationScanRetry &&
                !bleManager.isAuthenticated.value &&
                bleManager.connectionState.value !is TeacherBleManager.ConnectionState.Connected
            ) {
                hasTriedPostRegistrationScanRetry = true
                Log.d("CALIS", "Post-registration scan stuck after 2s wait. Re-clearing BLE object state and triggering re-scanning and connect-auth...")
                _registrationButtonText.value = "Re-scanning registered device..."
                bleManager.clearBleObject(triggerScan = false)
                kotlinx.coroutines.delay(100L)
                bleManager.startScan(forceRestart = true)

                val targetClsName = _registrationClassroomName.value
                val targetMac = _savedMacAddress.value ?: _registrationDeviceAddress.value
                val device = bleManager.scannedDevices.value.find { dev ->
                    val macMatches = !targetMac.isNullOrBlank() && dev.address.equals(targetMac, ignoreCase = true)
                    val nameMatches = (targetClsName.isNotBlank() && (dev.name.equals(targetClsName, ignoreCase = true) || dev.name.contains(targetClsName, ignoreCase = true))) ||
                            dev.name.equals("Calis Classroom", ignoreCase = true) ||
                            dev.name.contains("Calis", ignoreCase = true) ||
                            dev.name.contains("Setup", ignoreCase = true)
                    macMatches || (dev.hasServiceUuid && (nameMatches || dev.state == DeviceState.AUTHENTICATE || dev.state == DeviceState.SESSION_INACTIVE))
                }
                if (device != null) {
                    _registrationButtonText.value = "Connecting to registered device..."
                    bleManager.stopScan()
                    bleManager.connect(device.address)
                }
            }
        }
    }

    private val _isClassroomLoaded = MutableStateFlow(false)
    val isClassroomLoaded: StateFlow<Boolean> = _isClassroomLoaded.asStateFlow()

    private val _unviewedSubmittedTaskIds = MutableStateFlow<Set<String>>(emptySet())
    val unviewedSubmittedTaskIds: StateFlow<Set<String>> = _unviewedSubmittedTaskIds.asStateFlow()

    private val submissionMutex = kotlinx.coroutines.sync.Mutex()

    private val _taskUnviewedSubmissionCounts = MutableStateFlow<Map<String, Int>>(emptyMap())
    val taskUnviewedSubmissionCounts: StateFlow<Map<String, Int>> = _taskUnviewedSubmissionCounts.asStateFlow()

    val unviewedTaskSubmissionsCount: StateFlow<Int> = _unviewedSubmittedTaskIds
        .map { it.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun markTaskSubmissionsAsViewed(taskId: String) {
        _unviewedSubmittedTaskIds.value = _unviewedSubmittedTaskIds.value - taskId
        val currentCounts = _taskUnviewedSubmissionCounts.value.toMutableMap()
        currentCounts[taskId] = 0
        _taskUnviewedSubmissionCounts.value = currentCounts
    }

    fun clearAllUnviewedSubmissions() {
        _unviewedSubmittedTaskIds.value = emptySet()
        _taskUnviewedSubmissionCounts.value = emptyMap()
    }

    private val _pendingNavigationTaskId = MutableStateFlow<String?>(null)
    val pendingNavigationTaskId: StateFlow<String?> = _pendingNavigationTaskId.asStateFlow()

    fun setPendingNavigationTaskId(taskId: String?) {
        _pendingNavigationTaskId.value = taskId
    }

    // Controls enabled condition:
    // 1. Connected
    // 2. Authenticated (AUTH_OK received from TX)
    // 3. Device state in session inactive/active/busy/authenticate
    val isControlsEnabled: StateFlow<Boolean> = combine(
        bleManager.connectionState,
        bleManager.isAuthenticated,
        bleManager.deviceState
    ) { conn, auth, devState ->
        (conn is TeacherBleManager.ConnectionState.Connected) &&
                auth &&
                (devState == DeviceState.SESSION_INACTIVE ||
                        devState == DeviceState.SESSION_ACTIVE_OPEN ||
                        devState == DeviceState.SESSION_ACTIVE_BUSY ||
                        devState == DeviceState.AUTHENTICATE)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    // Registration Dialog Flow State
    enum class RegDialogStep {
        DISCOVERING,
        CONNECTING,
        REGISTERING,
        AUTHENTICATING,
        SUCCESS
    }

    private val _regDialogStep = MutableStateFlow<RegDialogStep?>(null)
    val regDialogStep: StateFlow<RegDialogStep?> = _regDialogStep.asStateFlow()

    private val _registrationClassroomName = MutableStateFlow("")
    val registrationClassroomName: StateFlow<String> = _registrationClassroomName.asStateFlow()

    private val _registrationDeviceAddress = MutableStateFlow<String?>(null)
    val registrationDeviceAddress: StateFlow<String?> = _registrationDeviceAddress.asStateFlow()

    private val _registrationDeviceName = MutableStateFlow<String?>(null)
    val registrationDeviceName: StateFlow<String?> = _registrationDeviceName.asStateFlow()

    private val _registrationButtonText = MutableStateFlow("Search Setup Calis")
    val registrationButtonText: StateFlow<String> = _registrationButtonText.asStateFlow()

    var tempClassroomToRegister: ClassroomEntity? = null

    private val _registrationError = MutableStateFlow<String?>(null)
    val registrationError: StateFlow<String?> = _registrationError.asStateFlow()

    fun resetRegistrationToDiscovering() {
        hasTriedPostRegistrationScanRetry = false
        postRegScanRetryJob?.cancel()
        _registrationError.value = null
        _regDialogStep.value = RegDialogStep.DISCOVERING
        _registrationButtonText.value = "Search Setup Calis"
        bleManager.clearBleObject()
        bleManager.startScan()
    }

    fun retryRegistration() {
        hasTriedPostRegistrationScanRetry = false
        postRegScanRetryJob?.cancel()
        _registrationError.value = null
        val addr = _registrationDeviceAddress.value
        if (!addr.isNullOrBlank()) {
            _regDialogStep.value = RegDialogStep.CONNECTING
            _registrationButtonText.value = "Connecting"
            bleManager.connect(addr)
        } else {
            resetRegistrationToDiscovering()
        }
    }

    fun startRegistrationDialog(initialClassroomName: String = "") {
        hasTriedPostRegistrationScanRetry = false
        postRegScanRetryJob?.cancel()
        _registrationError.value = null
        _registrationClassroomName.value = initialClassroomName
        _registrationDeviceAddress.value = null
        _registrationDeviceName.value = null
        _regDialogStep.value = RegDialogStep.DISCOVERING
        _registrationButtonText.value = "Search Setup Calis"
        bleManager.startScan()
    }

    fun cancelRegistrationDialog() {
        postRegScanRetryJob?.cancel()
        if (activeClassroom.value?.isBound != true) {
            bleManager.clearBleObject()
        }
        _regDialogStep.value = null
        _registrationDeviceAddress.value = null
        _registrationDeviceName.value = null
        tempClassroomToRegister = null
    }

    fun stopRegistrationFlow() {
        cancelRegistrationDialog()
    }

    fun setRegistrationClassroomName(name: String) {
        _registrationClassroomName.value = name
    }

    fun setSavedMacAddress(address: String?) {
        _savedMacAddress.value = address
    }

    fun setRegistrationSelectedDevice(name: String, address: String) {
        _registrationDeviceName.value = name
        _registrationDeviceAddress.value = address
    }

    fun onRegistrationNextClicked() {
        val step = _regDialogStep.value ?: return
        when (step) {
            RegDialogStep.DISCOVERING -> {
                val selectedAddress = _registrationDeviceAddress.value
                if (!selectedAddress.isNullOrBlank()) {
                    _regDialogStep.value = RegDialogStep.CONNECTING
                    _registrationButtonText.value = "Connecting"
                    bleManager.connect(selectedAddress)
                } else {
                    bleManager.startScan()
                }
            }
            RegDialogStep.CONNECTING -> {
                val selectedAddress = _registrationDeviceAddress.value ?: return
                bleManager.connect(selectedAddress)
            }
            RegDialogStep.REGISTERING -> {}
            RegDialogStep.AUTHENTICATING -> {}
            RegDialogStep.SUCCESS -> {
                _regDialogStep.value = null
                _userMessage.tryEmit("Classroom registered!")
            }
        }
    }

    fun startRegistrationFlow(classroomName: String = "", address: String = "") {
        hasTriedPostRegistrationScanRetry = false
        postRegScanRetryJob?.cancel()
        _registrationDeviceAddress.value = address.ifBlank { null }
        _registrationClassroomName.value = classroomName
        if (address.isNotBlank()) {
            _regDialogStep.value = RegDialogStep.CONNECTING
            _registrationButtonText.value = "Connecting"
            bleManager.connect(address)
        } else {
            _regDialogStep.value = RegDialogStep.DISCOVERING
            _registrationButtonText.value = "Search Setup Calis"
            bleManager.startScan()
        }
    }

    fun initiateDeviceRegistration(address: String, name: String, classroomName: String = "") {
        hasTriedPostRegistrationScanRetry = false
        postRegScanRetryJob?.cancel()
        _registrationDeviceName.value = name
        _registrationDeviceAddress.value = address
        if (classroomName.isNotBlank()) {
            _registrationClassroomName.value = classroomName
        }
        _regDialogStep.value = RegDialogStep.CONNECTING
        _registrationButtonText.value = "Connecting"
        bleManager.connect(address)
    }

    fun initiateDeviceRegistration(selectedDevice: TeacherBleManager.ScannedDevice, classroomName: String = "") {
        hasTriedPostRegistrationScanRetry = false
        postRegScanRetryJob?.cancel()
        _registrationDeviceName.value = selectedDevice.name
        _registrationDeviceAddress.value = selectedDevice.address
        if (classroomName.isNotBlank()) {
            _registrationClassroomName.value = classroomName
        }
        _regDialogStep.value = RegDialogStep.CONNECTING
        _registrationButtonText.value = "Connecting"
        bleManager.connect(selectedDevice.address)
    }

    init {
        // Ensure clean BLE state/objects on teacher startup
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

        currentTeacher = repository.currentTeacher
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)

        activeClassroom = repository.activeBoundClassroom
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)

        currentClassroom = currentTeacher
            .flatMapLatest { teacher ->
                if (teacher != null) repository.getClassroomForTeacher(teacher.tid)
                else flowOf(null)
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)

        val targetClassroomFlow = activeClassroom.combine(currentClassroom) { active, current ->
            active ?: current
        }

        targetClassroom = targetClassroomFlow
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)

        viewModelScope.launch {
            targetClassroomFlow.collect { classroom ->
                _isClassroomLoaded.value = true
                if (classroom != null) {
                    if (!classroom.deviceAddress.isNullOrBlank()) {
                        _savedMacAddress.value = classroom.deviceAddress
                        bleManager.setRegisteredDevice(classroom.deviceAddress, classroom.classroomName)
                    }
                    bleManager.setVirtualClassroomInfo(classroom.classroomName, classroom.isBound)
                    if (classroom.isBound) {
                        if (com.example.util.BlePermissionUtils.hasPermissions(application)) {
                            CalisBleService.startService(application)
                        } else {
                            Log.d("CALIS", "startService skipped in TeacherViewModel: Bluetooth permissions not granted yet")
                        }
                        if (bleManager.isBluetoothEnabled() &&
                            bleManager.connectionState.value is TeacherBleManager.ConnectionState.Disconnected &&
                            !bleManager.isExplicitlyDisconnected
                        ) {
                            Log.d("CALIS", "Classroom is bound. Initial auto-scan/connect to registered device...")
                            bleManager.startScan(forceRestart = false)
                        }
                        checkAndTriggerAutoConnect()
                    } else {
                        CalisBleService.stopService(application)
                    }
                } else {
                    CalisBleService.stopService(application)
                }
            }
        }

        pendingRegistrations = targetClassroomFlow
            .flatMapLatest { classroom ->
                if (classroom != null) repository.getPendingRegistrations(classroom.cid)
                else flowOf(emptyList())
            }
            .map { list -> list.distinctBy { it.studentId } }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        approvedStudents = targetClassroomFlow
            .flatMapLatest { classroom ->
                if (classroom != null) repository.getApprovedStudents(classroom.cid)
                else flowOf(emptyList())
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        attendanceLogs = targetClassroomFlow
            .flatMapLatest { classroom ->
                if (classroom != null) repository.getAttendanceForClassroom(classroom.cid)
                else flowOf(emptyList())
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        tasks = targetClassroomFlow
            .flatMapLatest { classroom ->
                if (classroom != null) repository.getTasksForClassroom(classroom.cid)
                else flowOf(emptyList())
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        currentSubmissions = _selectedTaskForSubmissions
            .flatMapLatest { task ->
                if (task != null) {
                    _isSubmissionsLoading.value = true
                    repository.getSubmissionsForTask(task.tkid).onEach {
                        _isSubmissionsLoading.value = false
                    }
                } else {
                    _isSubmissionsLoading.value = false
                    flowOf(emptyList())
                }
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        currentDeliveries = _selectedTaskForSubmissions
            .flatMapLatest { task ->
                if (task != null) {
                    _isSubmissionsLoading.value = true
                    repository.getDeliveriesForTask(task.tkid).onEach {
                        _isSubmissionsLoading.value = false
                    }
                } else {
                    _isSubmissionsLoading.value = false
                    flowOf(emptyList())
                }
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        // Reset BLE state on cold startup if not already running in background or connected
        bleManager.resetBleStateOnStartup(force = false)

        // Reset any stale session state on cold startup ONLY (not when running in background or connected)
        viewModelScope.launch {
            val isConnected = bleManager.connectionState.value is TeacherBleManager.ConnectionState.Connected
            val isServiceActive = CalisBleService.isServiceRunning()
            if (!isConnected && !isServiceActive) {
                val classroom = activeClassroom.value ?: currentClassroom.value
                if (classroom != null && classroom.isActiveSession) {
                    repository.updateClassroomSession(classroom.cid, false)
                    CalisBleService.setSessionActive(getApplication(), false)
                }
            }
        }

        // Observe activeClassroom to set registered target on BleManager and auto-scan
        viewModelScope.launch {
            activeClassroom.collect { cls ->
                if (cls != null) {
                    _savedMacAddress.value = cls.deviceAddress
                    bleManager.setRegisteredTarget(cls.deviceAddress, cls.classroomName)
                    if (cls.isBound && bleManager.isBluetoothEnabled() &&
                        bleManager.connectionState.value !is TeacherBleManager.ConnectionState.Connected &&
                        bleManager.connectionState.value !is TeacherBleManager.ConnectionState.Connecting &&
                        bleManager.connectionState.value !is TeacherBleManager.ConnectionState.Disconnecting
                    ) {
                        bleManager.startScan(forceRestart = false)
                        checkAndTriggerAutoConnect()
                    }
                }
            }
        }

        // Observe currentTeacher to trigger scan if Bluetooth is enabled
        viewModelScope.launch {
            currentTeacher.collect { teacher ->
                if (teacher != null && bleManager.isBluetoothEnabled() &&
                    bleManager.connectionState.value !is TeacherBleManager.ConnectionState.Connected &&
                    bleManager.connectionState.value !is TeacherBleManager.ConnectionState.Connecting &&
                    bleManager.connectionState.value !is TeacherBleManager.ConnectionState.Disconnecting
                ) {
                    bleManager.startScan(forceRestart = false)
                }
            }
        }


        // Observe authentication status and auto-start session if enabled
        viewModelScope.launch {
            bleManager.isAuthenticated.collect { authenticated ->
                if (!authenticated) {
                    hasAutoStartedForCurrentAuth = false
                } else if (_regDialogStep.value == null && !hasAutoStartedForCurrentAuth) {
                    hasAutoStartedForCurrentAuth = true
                    val prefs = application.getSharedPreferences("calis_auth_prefs", android.content.Context.MODE_PRIVATE)
                    val autoStart = prefs.getBoolean("auto_start_session", true)
                    if (autoStart) {
                        val classroom = activeClassroom.value ?: currentClassroom.value
                        if (classroom != null && !classroom.isActiveSession) {
                            android.util.Log.d("CALIS", "Auto-starting session because auto_start_session is enabled and TC is authenticated")
                            startSession()
                        }
                    }
                }
            }
        }

        observeIncomingPackets()

        // Handle pending actions dispatched from notification
        viewModelScope.launch {
            bleManager.pendingAction.collect { action ->
                if (action != null) {
                    when (action) {
                        "CONNECT" -> {
                            val classroom = activeClassroom.value ?: currentClassroom.value
                            val targetMac = classroom?.deviceAddress ?: _savedMacAddress.value
                            if (!targetMac.isNullOrBlank()) {
                                connectToDevice(targetMac)
                            } else {
                                val found = bleManager.scannedDevices.value.find { dev ->
                                    val clsName = classroom?.classroomName ?: ""
                                    (clsName.isNotBlank() && dev.name.contains(clsName, ignoreCase = true)) ||
                                            dev.name.contains("Calis", ignoreCase = true)
                                }
                                if (found != null) {
                                    connectToDevice(found.address)
                                } else {
                                    startBleScan()
                                }
                            }
                        }
                        "DISCONNECT" -> {
                            disconnectDevice()
                        }
                        "START_SESSION" -> {
                            if (isControlsEnabled.value) {
                                startSession()
                            }
                        }
                        "END_SESSION" -> {
                            if (isControlsEnabled.value) {
                                endSession()
                            }
                        }
                    }
                    bleManager.setPendingAction(null)
                }
            }
        }

        // Connection state observer
        viewModelScope.launch {
            var previousState: TeacherBleManager.ConnectionState? = null
            bleManager.connectionState.collect { state ->
                val teacher = currentTeacher.value
                val classroom = activeClassroom.value ?: currentClassroom.value
                val devState = bleManager.deviceState.value

                if (state is TeacherBleManager.ConnectionState.Connected) {
                    _isUserDisconnected.value = false
                    if (_regDialogStep.value == RegDialogStep.CONNECTING) {
                        val existingClassroom = activeClassroom.value ?: currentClassroom.value
                        val cid = existingClassroom?.cid ?: IdGenerator.generateCID()
                        val clsName = if (_registrationClassroomName.value.isNotBlank()) {
                            _registrationClassroomName.value
                        } else {
                            existingClassroom?.classroomName ?: "My Classroom"
                        }

                        val tempClassroom = existingClassroom?.copy(
                            classroomName = clsName,
                            isBound = false
                        ) ?: ClassroomEntity(
                            cid = cid,
                            teacherId = teacher?.tid ?: "T001",
                            classroomName = clsName,
                            teacherName = teacher?.fullName ?: "Teacher Account",
                            days = "MON-FRI",
                            startTime = "0800",
                            graceTime = "0815",
                            endTime = "1700",
                            isBound = false
                        )
                        tempClassroomToRegister = tempClassroom

                        val regPacket = BlePacket.RegCommand(
                            cid = tempClassroom.cid,
                            tid = teacher?.tid ?: tempClassroom.teacherId,
                            cls = tempClassroom.classroomName,
                            tc = com.example.util.BlePermissionUtils.formatTeacherNameWithMiddleInitial(
                                teacher?.fullName ?: tempClassroom.teacherName
                            )
                        )
                        _regDialogStep.value = RegDialogStep.REGISTERING
                        _registrationButtonText.value = "Registering"
                        bleManager.sendPacket(regPacket)
                    } else if (_regDialogStep.value == RegDialogStep.AUTHENTICATING) {
                        postRegScanRetryJob?.cancel()
                        val cidToAuth = tempClassroomToRegister?.cid
                            ?: (activeClassroom.value ?: currentClassroom.value)?.cid
                            ?: ""
                        val authPacket = BlePacket.AuthCommand(
                            tid = teacher?.tid ?: "T001",
                            cid = cidToAuth
                        )
                        _registrationButtonText.value = "Authenticating..."
                        Log.d("CALIS", "Sending AUTH packet in dialog flow after reconnect: TID=${teacher?.tid}, CID=$cidToAuth")
                        bleManager.sendPacket(authPacket)
                    } else if (_regDialogStep.value == null) {
                        if (classroom != null && classroom.isBound) {
                            isWaitingForReconnect = false
                            if (!bleManager.isAuthenticated.value) {
                                authenticateDevice()
                            }
                        } else if ((devState == DeviceState.FACTORY_MODE || devState == DeviceState.REGISTERING || classroom == null || !classroom.isBound) && teacher != null && classroom != null) {
                            val regPacket = BlePacket.RegCommand(
                                cid = classroom.cid,
                                tid = teacher.tid,
                                cls = classroom.classroomName,
                                tc = com.example.util.BlePermissionUtils.formatTeacherNameWithMiddleInitial(teacher.fullName)
                            )
                            bleManager.sendPacket(regPacket)
                        }
                    }
                } else if (state is TeacherBleManager.ConnectionState.Disconnected) {
                    if (previousState is TeacherBleManager.ConnectionState.Connected && !bleManager.isExplicitlyDisconnected && !_isDisconnecting.value) {
                        _userMessage.tryEmit("Disconnected")
                    }
                    bleManager.setAuthenticated(false)
                    if (!bleManager.isExplicitlyDisconnected) {
                        _isUserDisconnected.value = false
                    }
                    CalisBleService.updateNotification(getApplication())
                    if (_regDialogStep.value == RegDialogStep.REGISTERING || _regDialogStep.value == RegDialogStep.AUTHENTICATING) {
                        _regDialogStep.value = RegDialogStep.AUTHENTICATING
                        _registrationButtonText.value = "Waiting for Calis Device..."
                        // Teardown and reset any BLE object states upon sudden disconnection
                        bleManager.clearBleObject()
                        triggerPostRegistrationRetryIfNeeded()
                    } else if (_regDialogStep.value == RegDialogStep.CONNECTING) {
                        if (bleManager.isBluetoothEnabled()) {
                            bleManager.startScan()
                        }
                    } else {
                        // After the user is disconnected, clear BLE state/object and scan continuously for the registered Calis Device as long as Bluetooth is enabled
                        bleManager.clearBleObject()
                    }
                }
                previousState = state
            }
        }

        // Continuous search / proximity scan loop (keeps scanning if device is not connected)
        viewModelScope.launch {
            var lastForceRestart = 0L
            while (isActive) {
                if (bleManager.isBluetoothEnabled()) {
                    val connState = bleManager.connectionState.value
                    if (connState !is TeacherBleManager.ConnectionState.Connected &&
                        connState !is TeacherBleManager.ConnectionState.Connecting &&
                        connState !is TeacherBleManager.ConnectionState.Disconnecting
                    ) {
                        val now = System.currentTimeMillis()
                        // Ensure scan is active and force a clean restart every 6 seconds to prevent stalling
                        val force = (now - lastForceRestart > 6000L) || !bleManager.isScanning
                        bleManager.startScan(forceRestart = force)
                        if (force) lastForceRestart = now
                    }
                }
                delay(1000)
            }
        }

        // Action-based scan refresh: Re-scan immediately if registration criteria or target classroom changes
        viewModelScope.launch {
            combine(
                _registrationDeviceAddress,
                _registrationClassroomName,
                _savedMacAddress
            ) { addr, name, saved -> Triple(addr, name, saved) }
                .collect {
                    Log.d("TeacherViewModel", "Registration criteria changed. Refreshing scan results.")
                    val connState = bleManager.connectionState.value
                    if (bleManager.isBluetoothEnabled() &&
                        connState !is TeacherBleManager.ConnectionState.Connected &&
                        connState !is TeacherBleManager.ConnectionState.Connecting &&
                        connState !is TeacherBleManager.ConnectionState.Disconnecting
                    ) {
                        bleManager.startScan(forceRestart = true)
                    }
                }
        }

        // Scanned devices auto-connect handler (ONLY if !bleManager.isExplicitlyDisconnected)
        viewModelScope.launch {
            bleManager.scannedDevices.collect { devices ->
                if (_regDialogStep.value == RegDialogStep.AUTHENTICATING) {
                    val targetClsName = _registrationClassroomName.value
                    val targetMac = _savedMacAddress.value ?: _registrationDeviceAddress.value
                    val device = devices.find { dev ->
                        val macMatches = !targetMac.isNullOrBlank() && dev.address.equals(targetMac, ignoreCase = true)
                        val nameMatches = (targetClsName.isNotBlank() && (dev.name.equals(targetClsName, ignoreCase = true) || dev.name.contains(targetClsName, ignoreCase = true))) ||
                                dev.name.equals("Calis Classroom", ignoreCase = true) ||
                                dev.name.contains("Calis", ignoreCase = true) ||
                                dev.name.contains("Setup", ignoreCase = true)
                        macMatches || (dev.hasServiceUuid && (nameMatches || dev.state == DeviceState.AUTHENTICATE || dev.state == DeviceState.SESSION_INACTIVE))
                    }
                    if (device != null) {
                        val connState = bleManager.connectionState.value
                        if (connState is TeacherBleManager.ConnectionState.Disconnected || connState is TeacherBleManager.ConnectionState.Scanning) {
                            _registrationButtonText.value = "Connecting to registered device..."
                            bleManager.stopScan()
                            bleManager.connect(device.address)
                        }
                    }
                } else if (_regDialogStep.value == RegDialogStep.CONNECTING) {
                    val targetMac = _registrationDeviceAddress.value ?: _savedMacAddress.value
                    val connState = bleManager.connectionState.value
                    if (connState is TeacherBleManager.ConnectionState.Disconnected || connState is TeacherBleManager.ConnectionState.Scanning) {
                        val device = devices.find { dev ->
                            (!targetMac.isNullOrBlank() && dev.address.equals(targetMac, ignoreCase = true)) ||
                                    (dev.hasServiceUuid && dev.name.contains("Setup", ignoreCase = true))
                        }
                        if (device != null) {
                            bleManager.stopScan()
                            bleManager.connect(device.address)
                        }
                    }
                } else if (_regDialogStep.value == null) {
                    val teacher = currentTeacher.value
                    val classroom = activeClassroom.value ?: currentClassroom.value ?: targetClassroom.value

                    if (classroom != null && classroom.isBound && teacher != null) {
                        val targetMac = _savedMacAddress.value ?: classroom.deviceAddress
                        val device = devices.find { dev ->
                            val macMatches = !targetMac.isNullOrBlank() && dev.address.equals(targetMac, ignoreCase = true)
                            val nameMatches = classroom.classroomName.isNotBlank() && (
                                dev.name.equals(classroom.classroomName, ignoreCase = true) ||
                                dev.name.contains(classroom.classroomName, ignoreCase = true)
                            )
                            macMatches || nameMatches || dev.hasServiceUuid
                        }
                        // Automatically connect once as soon as registered CD is found on startup
                        checkAndTriggerAutoConnect(devices)
                    }
                }
            }
        }
    }

    /**
     * Automatically connects once on startup as soon as the registered Calis Device is found.
     * Respects manual disconnection so user can disconnect without triggering reconnection.
     */
    fun checkAndTriggerAutoConnect(devices: List<TeacherBleManager.ScannedDevice> = bleManager.scannedDevices.value) {
        val teacher = currentTeacher.value ?: return
        val classroom = activeClassroom.value ?: currentClassroom.value ?: targetClassroom.value ?: return
        if (!classroom.isBound) return

        if (bleManager.isExplicitlyDisconnected || _isUserDisconnected.value || _isDisconnecting.value) {
            return
        }

        val autoConnectEnabled = prefs.getBoolean("auto_connect_on_startup", true)
        if (!autoConnectEnabled) return

        if (hasAttemptedStartupAutoConnect) return

        val connState = bleManager.connectionState.value
        if (connState is TeacherBleManager.ConnectionState.Connected ||
            connState is TeacherBleManager.ConnectionState.Connecting ||
            bleManager.isAuthenticated.value
        ) {
            return
        }

        val targetMac = _savedMacAddress.value ?: classroom.deviceAddress
        val targetClsName = classroom.classroomName

        val device = devices.find { dev ->
            val macMatches = !targetMac.isNullOrBlank() && dev.address.equals(targetMac, ignoreCase = true)
            val nameMatches = targetClsName.isNotBlank() && (
                dev.name.equals(targetClsName, ignoreCase = true) ||
                dev.name.contains(targetClsName, ignoreCase = true)
            )
            val isNotSetup = !dev.name.contains("Setup", ignoreCase = true) && dev.state != DeviceState.FACTORY_MODE

            if (!targetMac.isNullOrBlank()) {
                macMatches || (isNotSetup && nameMatches && (dev.hasServiceUuid || dev.state != DeviceState.UNKNOWN))
            } else {
                isNotSetup && (nameMatches || (dev.name.contains("Calis", ignoreCase = true) && dev.hasServiceUuid))
            }
        }

        if (device != null) {
            hasAttemptedStartupAutoConnect = true
            Log.d("CALIS", "Auto-connecting once on startup to registered CD '${device.name}' (${device.address}) [State: ${device.state.displayName}]...")
            bleManager.stopScan()
            bleManager.connect(device.address)
        } else {
            if (bleManager.isBluetoothEnabled() && !bleManager.isScanning && (connState is TeacherBleManager.ConnectionState.Disconnected)) {
                bleManager.startScan(forceRestart = false)
            }
        }
    }

    fun selectTaskForSubmissions(task: TaskEntity?) {
        _selectedTaskForSubmissions.value = task
        if (task != null) {
            markTaskSubmissionsAsViewed(task.tkid)
            CalisBleService.dismissTaskSubmissionNotification(getApplication())
        }
    }

    // --- BLE OPERATIONS ---
    fun startBleScan(forceRestart: Boolean = true) {
        bleManager.startScan(forceRestart = forceRestart)
    }

    fun stopBleScan() {
        bleManager.stopScan()
    }

    fun connectToDevice(address: String) {
        hasAttemptedStartupAutoConnect = true
        bleManager.isExplicitlyDisconnected = false
        _isUserDisconnected.value = false
        _savedMacAddress.value = address

        val classroom = activeClassroom.value ?: currentClassroom.value
        if (classroom != null && classroom.isActiveSession) {
            viewModelScope.launch {
                repository.updateClassroomSession(classroom.cid, false)
            }
        }
        bleManager.stopScan()
        bleManager.connect(address)
    }

    fun disconnectDevice() {
        Log.d("CALIS", "User clicked Disconnect in app")
        viewModelScope.launch {
            _isDisconnecting.value = true
            hasAttemptedStartupAutoConnect = true
            _isUserDisconnected.value = true
            isWaitingForReconnect = false
            bleManager.isExplicitlyDisconnected = true

            val classroom = activeClassroom.value ?: currentClassroom.value
            if (classroom != null && classroom.isActiveSession) {
                repository.updateClassroomSession(classroom.cid, false)
            }
            CalisBleService.setSessionActive(getApplication(), false)

            // Teardown BLE object state
            bleManager.clearBleObject()

            // Maintain 3-second Disconnecting state text on button before returning to Connect / Searching
            kotlinx.coroutines.delay(3000L)

            _isDisconnecting.value = false
            _userMessage.tryEmit("Disconnected")
        }
    }

    fun authenticateDevice() {
        viewModelScope.launch {
            kotlinx.coroutines.delay(500)
            if (bleManager.isAuthenticated.value) return@launch
            val teacher = currentTeacher.value ?: return@launch
            val classroom = activeClassroom.value ?: currentClassroom.value ?: return@launch
            val authPacket = BlePacket.AuthCommand(tid = teacher.tid, cid = classroom.cid)
            Log.d("CALIS", "Sending AUTH packet: TID=${teacher.tid}, CID=${classroom.cid}")
            bleManager.sendPacket(authPacket) { success ->
                if (success) {
                    _userMessage.tryEmit("Authenticating")
                }
            }
        }
    }

    fun checkAndAutoAuthenticate() {
        val prefs = getApplication<Application>().getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val currentRole = prefs.getString("user_role", null)
        if (currentRole != "TEACHER") return

        val connState = bleManager.connectionState.value
        val teacher = currentTeacher.value
        val classroom = activeClassroom.value ?: currentClassroom.value ?: targetClassroom.value
        if (connState is TeacherBleManager.ConnectionState.Connected && teacher != null && classroom != null) {
            if (!isAuthenticated.value && _regDialogStep.value == null && !isWaitingForReconnect) {
                authenticateDevice()
            }
        } else if (connState is TeacherBleManager.ConnectionState.Disconnected && !bleManager.isExplicitlyDisconnected && classroom?.isBound == true) {
            bleManager.startScan()
        }
    }

    fun startSession() {
        val classroom = activeClassroom.value ?: currentClassroom.value ?: return
        if (bleManager.connectionState.value !is TeacherBleManager.ConnectionState.Connected) {
            _userMessage.tryEmit("Connect to Calis Device first")
            return
        }
        if (!isAuthenticated.value) {
            _userMessage.tryEmit("Connection Failed: Try again later")
            return
        }
        lastExplicitSessionStartTimestamp = System.currentTimeMillis()
        lastExplicitSessionEndTimestamp = 0L
        viewModelScope.launch {
            kotlinx.coroutines.delay(200L)
            bleManager.sendPacket(BlePacket.StartSessionCommand) { success ->
                if (success) {
                    viewModelScope.launch {
                        repository.updateClassroomSession(classroom.cid, true)
                        CalisBleService.setSessionActive(getApplication(), true)
                        _userMessage.emit("Session started!")
                    }
                }
            }
        }
    }

    fun endSession() {
        val classroom = activeClassroom.value ?: currentClassroom.value ?: return
        lastExplicitSessionEndTimestamp = System.currentTimeMillis()
        lastExplicitSessionStartTimestamp = 0L
        bleManager.sendPacket(BlePacket.EndSessionCommand) { success ->
            if (success) {
                viewModelScope.launch {
                    repository.updateClassroomSession(classroom.cid, false)
                    CalisBleService.setSessionActive(getApplication(), false)
                    _userMessage.emit("Session ended")
                }
            }
        }
    }

    fun unregisterDevice() {
        viewModelScope.launch {
            _userMessage.emit("Unregistering Calis Device")
        }
        bleManager.sendPacket(BlePacket.UnregCommand)
    }

    // --- STUDENT APPROVAL / REJECTION ---
    fun approveStudent(pending: PendingRegistrationEntity) {
        val teacher = currentTeacher.value
        viewModelScope.launch {
            val classroom = activeClassroom.value ?: currentClassroom.value ?: repository.getClassroomById(pending.classroomId)
            val targetCid = classroom?.cid ?: pending.classroomId
            val updatedPending = if (pending.classroomId != targetCid && targetCid.isNotBlank()) pending.copy(classroomId = targetCid) else pending
            repository.approveStudentRegistration(updatedPending)

            // Automatically mark that student as Present in the Masterlist (Students tab) regardless if approved beyond the Grace Period
            val currentTimestamp = System.currentTimeMillis() / 1000
            repository.recordAttendance(
                classroomId = targetCid,
                studentId = updatedPending.studentId,
                studentName = updatedPending.studentName,
                status = "PRESENT",
                timestamp = currentTimestamp
            )

            val regApprovedPacket = BlePacket.RegApprovedResponse(
                cid = classroom?.cid ?: "",
                cls = classroom?.classroomName ?: "",
                tc = com.example.util.BlePermissionUtils.formatTeacherNameWithMiddleInitial(teacher?.fullName ?: classroom?.teacherName ?: "Teacher"),
                days = classroom?.days ?: "M-T-W-TH-F",
                start = classroom?.startTime ?: "08:00",
                grace = classroom?.graceTime ?: "08:15",
                end = classroom?.endTime ?: "17:00",
                rid = pending.rid
            )
            bleManager.sendPacket(regApprovedPacket)

            // If there are tasks, transmit them; otherwise, directly send DONE
            val classroomId = pending.classroomId
            val currentTasks = if (classroomId.isNotBlank()) {
                repository.getTasksForClassroomSync(classroomId)
            } else {
                emptyList()
            }

            val sortedTasks = currentTasks.sortedBy { it.datePosted }

            if (sortedTasks.isNotEmpty()) {
                kotlinx.coroutines.delay(200L)
                for (task in sortedTasks) {
                    val alreadyReceived = repository.hasStudentReceivedTask(task.tkid, pending.studentId)
                    if (alreadyReceived) {
                        continue
                    }

                    val scoreStr = if (task.maxScore > 0) String.format(Locale.US, "%03d", task.maxScore) else "000"
                    val tkPacket = BlePacket.TaskCommand(
                        tkid = task.tkid,
                        title = task.title,
                        desc = task.description.ifBlank { task.title },
                        deadline = task.deadline,
                        datePosted = task.datePosted,
                        score = scoreStr,
                        rid = pending.rid
                    )
                    bleManager.sendPacket(tkPacket)
                    repository.markTaskAsReceived(task.tkid, pending.studentId)
                    kotlinx.coroutines.delay(200L)
                }
            } else {
                kotlinx.coroutines.delay(200L)
            }

            // Always send DONE with the same RID to indicate transaction completion
            bleManager.sendPacket(BlePacket.DoneResponse(pending.rid)) {
                bleManager.clearTransactionState()
            }

            _userMessage.emit("Approved ${pending.studentName}")
        }
    }

    fun rejectStudent(pending: PendingRegistrationEntity) {
        viewModelScope.launch {
            repository.rejectStudentRegistration(pending)
            bleManager.sendPacket(BlePacket.RegRejectedResponse(pending.rid))
            kotlinx.coroutines.delay(100L)
            // Always send DONE with the same RID so Calis Device clears the temporary student RID connection
            bleManager.sendPacket(BlePacket.DoneResponse(pending.rid)) {
                bleManager.clearTransactionState()
            }
            _userMessage.emit("Rejected ${pending.studentName}")
        }
    }

    // --- ATTENDANCE MANAGEMENT ---

    fun updateAttendanceStatus(
        studentId: String,
        dateStr: String,
        newStatus: String
    ) {
        val classroom = activeClassroom.value ?: currentClassroom.value ?: return
        viewModelScope.launch {
            repository.updateAttendanceStatus(classroom.cid, studentId, dateStr, newStatus)
            _userMessage.emit("Attendance updated for student: $newStatus")
        }
    }

    // --- TASK MANAGEMENT ---
    fun createTask(title: String, description: String, deadline: Long, maxScore: Int) {
        val classroom = activeClassroom.value ?: currentClassroom.value ?: return
        viewModelScope.launch {
            val task = TaskEntity(
                tkid = IdGenerator.generateTKID(),
                classroomId = classroom.cid,
                title = title,
                description = description,
                datePosted = System.currentTimeMillis() / 1000,
                deadline = deadline,
                maxScore = maxScore
            )
            repository.saveTask(task)

            // Broadcast task packet if session is active
            val scoreStr = if (maxScore > 0) String.format(Locale.US, "%03d", maxScore) else "000"
            val tkPacket = BlePacket.TaskCommand(
                tkid = task.tkid,
                title = task.title,
                desc = task.description.ifBlank { task.title },
                deadline = task.deadline,
                datePosted = task.datePosted,
                score = scoreStr,
                rid = 0
            )
            if (tkPacket.rid != 0) {
                bleManager.sendPacket(tkPacket)
            }
            _userMessage.emit("Task created and queued for broadcast!")
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(task.tkid)
            if (_selectedTaskForSubmissions.value?.tkid == task.tkid) {
                _selectedTaskForSubmissions.value = null
            }
            _userMessage.emit("Task '${task.title}' deleted.")
        }
    }

    fun deleteTask(tkid: String) {
        viewModelScope.launch {
            repository.deleteTask(tkid)
            if (_selectedTaskForSubmissions.value?.tkid == tkid) {
                _selectedTaskForSubmissions.value = null
            }
            _userMessage.emit("Task deleted.")
        }
    }

    // --- CSV EXPORT ---
    fun exportAttendanceData(onComplete: (String) -> Unit) {
        viewModelScope.launch {
            val classroom = activeClassroom.value ?: currentClassroom.value
            val logs = attendanceLogs.value
            val csv = if (classroom != null) {
                repository.exportAttendanceCsv(classroom.cid, logs)
            } else {
                "Date,Classroom ID,Student ID,Student Name,Status,Timestamp\n"
            }
            onComplete(csv)
        }
    }

    fun exportCustomAttendanceCsv(
        config: CsvExportUtils.AttendanceExportConfig,
        onComplete: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val classroom = activeClassroom.value ?: currentClassroom.value
                val rawApproved = approvedStudents.value ?: emptyList()
                val approved = rawApproved.map { st ->
                    if (st.lrn.isBlank()) {
                        val prof = repository.getStudentProfileById(st.studentId)
                        if (prof != null && prof.lrn.isNotBlank()) st.copy(lrn = prof.lrn) else st
                    } else st
                }
                val logs = attendanceLogs.value ?: emptyList()
                val csv = CsvExportUtils.generateAttendanceCsv(classroom, approved, logs, config)
                onComplete(csv)
            } catch (e: Exception) {
                Log.e("TeacherViewModel", "Error generating attendance CSV", e)
                _userMessage.emit("Failed to generate CSV: ${e.localizedMessage}")
            }
        }
    }

    fun exportTaskSubmissionsData(task: TaskEntity, onComplete: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val submissions = repository.getSubmissionsForTask(task.tkid).firstOrNull() ?: emptyList()
                val csv = repository.exportTaskSubmissionsCsv(task, submissions)
                onComplete(csv)
            } catch (e: Exception) {
                Log.e("TeacherViewModel", "Error exporting task submissions CSV", e)
                _userMessage.emit("Failed to generate task CSV: ${e.localizedMessage}")
            }
        }
    }

    fun exportCustomTasksCsv(
        selectedTasks: List<TaskEntity>,
        config: CsvExportUtils.TasksExportConfig,
        onComplete: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val classroom = activeClassroom.value ?: currentClassroom.value
                val rawApproved = approvedStudents.value ?: emptyList()
                val approved = rawApproved.map { st ->
                    if (st.lrn.isBlank()) {
                        val prof = repository.getStudentProfileById(st.studentId)
                        if (prof != null && prof.lrn.isNotBlank()) st.copy(lrn = prof.lrn) else st
                    } else st
                }
                val submissionsMap = mutableMapOf<String, List<TaskSubmissionEntity>>()
                for (task in selectedTasks) {
                    val subs = repository.getSubmissionsForTask(task.tkid).firstOrNull() ?: emptyList()
                    submissionsMap[task.tkid] = subs
                }
                val csv = CsvExportUtils.generateTasksCsv(classroom, approved, selectedTasks, submissionsMap, config)
                onComplete(csv)
            } catch (e: Exception) {
                Log.e("TeacherViewModel", "Error generating tasks CSV", e)
                _userMessage.emit("Failed to generate tasks CSV: ${e.localizedMessage}")
            }
        }
    }

    fun resetAllState() {
        disconnectDevice()
        viewModelScope.launch {
            repository.deleteAllData()
        }
    }

    private fun observeIncomingPackets() {
        viewModelScope.launch {
            bleManager.incomingPackets.collect { packet ->
                viewModelScope.launch {
                    Log.d("CALIS", "TeacherViewModel processing packet concurrently: $packet")
                when (packet) {
                    is BlePacket.RegisteredResponse -> {
                        val teacher = currentTeacher.value
                        val cls = tempClassroomToRegister
                        if (cls != null && teacher != null) {
                            val savedCls = cls.copy(
                                isBound = true,
                                deviceAddress = bleManager.scannedDevices.value.find {
                                    it.name.contains(cls.classroomName, ignoreCase = true) ||
                                            it.name.contains("Calis", ignoreCase = true)
                                }?.address ?: _registrationDeviceAddress.value ?: ""
                            )
                            viewModelScope.launch {
                                repository.saveClassroom(savedCls)
                                com.example.util.ReminderNotificationHelper.checkAndPostSessionReminder(getApplication())
                            }
                            _savedMacAddress.value = savedCls.deviceAddress
                        }
                        isWaitingForReconnect = true
                        _regDialogStep.value = RegDialogStep.AUTHENTICATING
                        _registrationButtonText.value = "Waiting for Calis Device reboot..."
                        triggerPostRegistrationRetryIfNeeded()
                    }

                    is BlePacket.UnregisteredResponse -> {
                        val classroom = activeClassroom.value ?: currentClassroom.value
                        if (classroom != null) {
                            viewModelScope.launch {
                                repository.unregisterClassroom(classroom.cid)
                            }
                        }
                        _savedMacAddress.value = null
                        _registrationDeviceAddress.value = null
                        bleManager.setAuthenticated(false)
                        CalisBleService.updateNotification(getApplication())
                        _userMessage.tryEmit("Classroom unregistered")
                    }

                    is BlePacket.AuthOkResponse -> {
                        postRegScanRetryJob?.cancel()
                        viewModelScope.launch {
                            // Trust the existing database session state or the state from scan record, do not reset it on every AuthOk
                            // val classroom = activeClassroom.value ?: currentClassroom.value
                            // if (classroom != null && classroom.isActiveSession) {
                            //     repository.updateClassroomSession(classroom.cid, false)
                            //     kotlinx.coroutines.delay(150)
                            // }

                            bleManager.setAuthenticated(true)

                            if (_regDialogStep.value == RegDialogStep.AUTHENTICATING) {
                                _regDialogStep.value = RegDialogStep.SUCCESS
                                isWaitingForReconnect = false
                                tempClassroomToRegister?.let {
                                    val savedCls = it.copy(isBound = true)
                                    repository.saveClassroom(savedCls)
                                    com.example.util.ReminderNotificationHelper.checkAndPostSessionReminder(getApplication())
                                    // Slight delay to allow database/flow to propagate
                                    kotlinx.coroutines.delay(250)
                                    val prefs = getApplication<Application>().getSharedPreferences("calis_auth_prefs", android.content.Context.MODE_PRIVATE)
                                    val autoStart = prefs.getBoolean("auto_start_session", true)
                                    if (autoStart) {
                                        android.util.Log.d("CALIS", "Auto-starting session after successful registration and auth")
                                        startSession()
                                    }
                                }
                                _userMessage.tryEmit("Classroom registered!")
                            } else {
                                isWaitingForReconnect = false
                                _userMessage.tryEmit("Device ready!")
                            }
                            CalisBleService.updateNotification(getApplication())
                        }
                    }

                    is BlePacket.AuthFailedResponse -> {
                        bleManager.setAuthenticated(false)
                        _userMessage.tryEmit("Authentication failed")
                        CalisBleService.updateNotification(getApplication())
                    }

                    is BlePacket.StudentRegRequest -> {
                        val classroom = activeClassroom.value ?: currentClassroom.value ?: return@launch
                        val pending = PendingRegistrationEntity(
                            classroomId = classroom.cid,
                            studentId = packet.sid,
                            studentName = packet.st,
                            sex = packet.sex,
                            lrn = packet.lrn,
                            birth = packet.birth,
                            rid = packet.rid
                        )
                        if (_autoApproveEnabled.value) {
                            approveStudent(pending)
                        } else {
                            repository.addPendingRegistration(pending)
                            _userMessage.emit("${packet.st} wants to join the classroom!")
                        }
                    }

                    is BlePacket.CheckInRequest -> {
                        Log.d("CALIS", "Processing CheckInRequest for SID: ${packet.sid} with RID: ${packet.rid}")
                        val classroom = activeClassroom.value ?: currentClassroom.value
                        val studentEntry = (if (classroom != null) repository.getApprovedStudent(classroom.cid, packet.sid) else null)
                            ?: repository.getApprovedStudentGlobally(packet.sid)

                        if (studentEntry != null) {
                            val targetCid = studentEntry.classroomId
                            val timestamp = System.currentTimeMillis() / 1000

                            val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US)
                            val todayDateStr = dateFormat.format(java.util.Date(timestamp * 1000))
                            val existingRecord = repository.getAttendanceRecord(targetCid, packet.sid, todayDateStr)

                            val graceTimeStr = classroom?.graceTime ?: ""
                            val graceMins = parseTimeToMinutes(graceTimeStr)
                            val calendar = java.util.Calendar.getInstance()
                            val currentMinutes = calendar.get(java.util.Calendar.HOUR_OF_DAY) * 60 + calendar.get(java.util.Calendar.MINUTE)

                            val attendanceStatus = if (existingRecord?.status == "PRESENT") {
                                "PRESENT"
                            } else if (graceMins != -1 && currentMinutes > graceMins) {
                                "LATE"
                            } else {
                                "PRESENT"
                            }

                            repository.recordAttendance(
                                classroomId = targetCid,
                                studentId = packet.sid,
                                studentName = studentEntry.studentName,
                                status = attendanceStatus,
                                timestamp = timestamp
                            )

                            // Step 1: Send ATD_ACCEPTED with timestamp and exact matching RID from CHK_IN
                            val acceptPacket = BlePacket.AtdAcceptedResponse(timestamp, packet.rid)
                            Log.d("CALIS", "Sending ATD_ACCEPTED: ${acceptPacket.encode()} for RID: ${packet.rid}")
                            bleManager.sendPacket(acceptPacket)

                            // Step 2: Delay before sending any TK packets to prevent packet collision over BLE
                            kotlinx.coroutines.delay(250L)

                            // Step 3: Fetch tasks directly and send all unsynchronized tasks with the same RID
                            val currentTasks = repository.getTasksForClassroomSync(targetCid)
                            val sortedTasks = currentTasks.sortedBy { it.datePosted }
                            for (task in sortedTasks) {
                                val alreadyReceived = repository.hasStudentReceivedTask(task.tkid, packet.sid)
                                if (alreadyReceived) {
                                    continue
                                }

                                val scoreStr = if (task.maxScore > 0) String.format(Locale.US, "%03d", task.maxScore) else "000"
                                val tkPacket = BlePacket.TaskCommand(
                                    tkid = task.tkid,
                                    title = task.title,
                                    desc = task.description,
                                    deadline = task.deadline,
                                    datePosted = task.datePosted,
                                    score = scoreStr,
                                    rid = packet.rid
                                )
                                Log.d("CALIS", "Sending TaskCommand to Student SID: ${packet.sid} -> ${tkPacket.encode()} for RID: ${packet.rid}")
                                bleManager.sendPacket(tkPacket)
                                repository.markTaskAsReceived(task.tkid, packet.sid)
                                kotlinx.coroutines.delay(200L)
                            }

                            // Step 4: Send DONE packet with same RID
                            kotlinx.coroutines.delay(100L)
                            Log.d("CALIS", "Sending DONE for CheckIn RID: ${packet.rid}")
                            bleManager.sendPacket(BlePacket.DoneResponse(packet.rid)) { success ->
                                bleManager.clearTransactionState()
                                viewModelScope.launch {
                                    _userMessage.emit("Attendance Marked for ${studentEntry.studentName}")
                                }
                            }
                        } else {
                            Log.d("CALIS", "Student ${packet.sid} not in masterlist. Sending ATD_REJECTED and DONE for RID: ${packet.rid}")
                            bleManager.sendPacket(BlePacket.AtdRejectedResponse(packet.rid))
                            kotlinx.coroutines.delay(200L)
                            bleManager.sendPacket(BlePacket.DoneResponse(packet.rid)) { success ->
                                bleManager.clearTransactionState()
                                viewModelScope.launch {
                                    _userMessage.emit("Attendance request from unregistered SID ${packet.sid} rejected.")
                                }
                            }
                        }
                    }

                    is BlePacket.TaskSubmissionRequest -> {
                        val classroom = activeClassroom.value ?: currentClassroom.value ?: targetClassroom.value ?: return@launch
                        val isApproved = repository.isStudentApproved(classroom.cid, packet.sid)

                        if (isApproved) {
                            val scoreInt = packet.score.toIntOrNull() ?: 0
                            val timestamp = System.currentTimeMillis() / 1000
                            val student = repository.getApprovedStudent(classroom.cid, packet.sid)
                            val studentDisplayName = student?.studentName ?: "Student ${packet.sid}"
                            val taskList = repository.getTasksForClassroomSync(classroom.cid)
                            val taskEntity = taskList.find { it.tkid == packet.tkid }
                            val taskTitle = taskEntity?.title ?: "Task ${packet.tkid}"
                            val taskMaxScore = taskEntity?.maxScore ?: 0

                            submissionMutex.withLock {
                                val existingSub = repository.getSubmission(packet.tkid, packet.sid)

                                repository.recordTaskSubmission(
                                    taskId = packet.tkid,
                                    studentId = packet.sid,
                                    studentName = studentDisplayName,
                                    score = scoreInt,
                                    timestamp = timestamp
                                )

                                val isInsideCurrentTask = _selectedTaskForSubmissions.value?.tkid == packet.tkid
                                val isAppInForeground = com.example.CalisApplication.isAppInForeground

                                if (isAppInForeground) {
                                    // When user is already inside the app, cancel task submission system notification
                                    // since there's already a top verbose log overlay message
                                    CalisBleService.dismissTaskSubmissionNotification(getApplication())
                                    if (isInsideCurrentTask) {
                                        markTaskSubmissionsAsViewed(packet.tkid)
                                    } else if (existingSub == null) {
                                        _unviewedSubmittedTaskIds.value = _unviewedSubmittedTaskIds.value + packet.tkid
                                        val currentCounts = _taskUnviewedSubmissionCounts.value.toMutableMap()
                                        val count = currentCounts[packet.tkid] ?: 0
                                        currentCounts[packet.tkid] = count + 1
                                        _taskUnviewedSubmissionCounts.value = currentCounts
                                    }
                                } else {
                                    if (isInsideCurrentTask) {
                                        // Dismiss batch notification when already inside the task entry
                                        markTaskSubmissionsAsViewed(packet.tkid)
                                        CalisBleService.dismissTaskSubmissionNotification(getApplication())
                                    } else {
                                        if (existingSub == null) {
                                            // Track unviewed task submission for batch notification only on first unique submission
                                            _unviewedSubmittedTaskIds.value = _unviewedSubmittedTaskIds.value + packet.tkid
                                            val currentCounts = _taskUnviewedSubmissionCounts.value.toMutableMap()
                                            val count = currentCounts[packet.tkid] ?: 0
                                            currentCounts[packet.tkid] = count + 1
                                            _taskUnviewedSubmissionCounts.value = currentCounts

                                            // Post temporary system notification for teacher when app is in background
                                            CalisBleService.showTaskSubmissionNotification(
                                                context = getApplication(),
                                                studentName = studentDisplayName,
                                                taskTitle = taskTitle,
                                                score = scoreInt,
                                                maxScore = taskMaxScore,
                                                taskId = packet.tkid
                                            )
                                        }
                                    }
                                }
                            }

                            val markedPacket = BlePacket.MarkedResponse(timestamp, packet.rid)
                            bleManager.sendPacket(markedPacket)
                            val scoreMsg = if (taskMaxScore > 0) "Score: $scoreInt/$taskMaxScore" else "Complete"
                            _userMessage.emit("$studentDisplayName submitted \"$taskTitle\" ($scoreMsg)")
                            kotlinx.coroutines.delay(100L)
                            bleManager.sendPacket(BlePacket.DoneResponse(packet.rid)) {
                                bleManager.clearTransactionState()
                            }
                        } else {
                            bleManager.sendPacket(BlePacket.DoneResponse(packet.rid)) {
                                bleManager.clearTransactionState()
                            }
                        }
                    }

                    is BlePacket.RequestUpdateRequest -> {
                        val classroom = activeClassroom.value ?: currentClassroom.value ?: return@launch
                        val isApproved = repository.isStudentApproved(classroom.cid, packet.sid)

                        if (isApproved) {
                            val currentTasks = repository.getTasksForClassroomSync(classroom.cid)
                            val sortedTasks = currentTasks.sortedBy { it.datePosted }
                            for (task in sortedTasks) {
                                val alreadyReceived = repository.hasStudentReceivedTask(task.tkid, packet.sid)
                                if (alreadyReceived) {
                                    continue
                                }

                                val scoreStr = if (task.maxScore > 0) String.format(Locale.US, "%03d", task.maxScore) else "000"
                                val tkPacket = BlePacket.TaskCommand(
                                    tkid = task.tkid,
                                    title = task.title,
                                    desc = task.description,
                                    deadline = task.deadline,
                                    datePosted = task.datePosted,
                                    score = scoreStr,
                                    rid = packet.rid
                                )
                                bleManager.sendPacket(tkPacket)
                                repository.markTaskAsReceived(task.tkid, packet.sid)
                                kotlinx.coroutines.delay(200L)
                            }
                        }
                        // Always send DONE with the same RID to terminate student connection
                        kotlinx.coroutines.delay(100L)
                        bleManager.sendPacket(BlePacket.DoneResponse(packet.rid)) {
                            bleManager.clearTransactionState()
                        }
                    }
                    else -> {}
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
}
