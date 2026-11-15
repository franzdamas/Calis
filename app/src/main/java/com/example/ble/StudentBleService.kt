package com.example.ble

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import android.os.PowerManager
import android.os.SystemClock
import android.util.Log
import com.example.R
import com.example.data.db.CalisDatabase
import com.example.data.db.ClassroomEntity
import com.example.data.db.StudentProfileEntity
import com.example.data.repository.CalisRepository
import com.example.util.BlePermissionUtils
import androidx.core.content.ContextCompat
import com.example.util.ReminderNotificationHelper
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class StudentBleService : Service() {

    companion object {
        private const val TAG = "StudentBleService"
        @Volatile
        private var INSTANCE: StudentBleService? = null

        fun isServiceRunning(): Boolean = INSTANCE != null

        fun startService(context: Context, classroomId: String? = null) {
            try {
                val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                val userRole = prefs.getString("user_role", null)
                if (userRole != "STUDENT") return

                if (!BlePermissionUtils.hasPermissions(context)) return

                val intent = Intent(context, StudentBleService::class.java).apply {
                    if (!classroomId.isNullOrBlank()) {
                        putExtra("classroom_id", classroomId)
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    try {
                        context.startForegroundService(intent)
                    } catch (e: Throwable) {
                        try {
                            context.startService(intent)
                        } catch (e2: Throwable) {
                            Log.e(TAG, "Failed to start StudentBleService safely", e2)
                        }
                    }
                } else {
                    context.startService(intent)
                }
            } catch (e: Throwable) {
                Log.e(TAG, "Error starting StudentBleService", e)
            }
        }

        fun stopService(context: Context) {
            try {
                val service = INSTANCE
                if (service != null) {
                    service.stopForeground(STOP_FOREGROUND_REMOVE)
                    service.stopSelf()
                    INSTANCE = null
                } else {
                    val intent = Intent(context, StudentBleService::class.java)
                    context.stopService(intent)
                }
            } catch (e: Throwable) {
                Log.e(TAG, "Error stopping StudentBleService", e)
            }
        }

        fun updateNotification(context: Context, isFound: Boolean = false, isCheckingIn: Boolean = false, scanCountdown: Int? = null) {
            val service = INSTANCE
            if (service != null) {
                service.updateFindingCalisNotification(isFound, isCheckingIn, scanCountdown)
            }
        }
    }

    private val serviceScope = CoroutineScope(Dispatchers.Main + Job())
    private lateinit var bleManager: StudentBleManager
    private lateinit var repository: CalisRepository
    private var currentProfile: StudentProfileEntity? = null
    private var registeredClassroom: ClassroomEntity? = null
    private var isFindingCalis = false
    private var connectionAttemptJob: Job? = null
    private var isConnectingOrConnected = false
    private var isCurrentlyCheckingIn = false
    private var currentIsFound = false
    private var lastNearbyDeviceSeenTime = 0L

    private var scanCountdownJob: Job? = null
    private var scanCountdownSeconds = 5

    // Debouncing and rate-limiting to strictly prevent OS notification throttle terminations
    private var lastPostedFingerprint: String? = null
    private var lastNotificationPostTime = 0L
    private var pendingNotificationJob: Job? = null
    private val NOTIFICATION_MIN_INTERVAL_MS = 600L

    private var wakeLock: PowerManager.WakeLock? = null

    private val bluetoothReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == BluetoothAdapter.ACTION_STATE_CHANGED) {
                val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                Log.d(TAG, "BluetoothAdapter state changed: $state")
                if (state == BluetoothAdapter.STATE_OFF || state == BluetoothAdapter.STATE_TURNING_OFF) {
                    handleBluetoothDisabled()
                } else if (state == BluetoothAdapter.STATE_ON) {
                    updateFindingCalisStatus()
                    if (isFindingCalis) {
                        bleManager.startScan(forceRestart = true)
                        updateFindingCalisNotification(isFound = false, isCheckingIn = false)
                    }
                }
            }
        }
    }

    private val screenReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                Intent.ACTION_SCREEN_OFF -> {
                    Log.d(TAG, "Screen OFF detected while finding Calis device. Ensuring WakeLock...")
                    updateWakeLock()
                }
                Intent.ACTION_SCREEN_ON -> {
                    Log.d(TAG, "Screen ON detected.")
                    updateWakeLock()
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        val prefs = getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val userRole = prefs.getString("user_role", null)
        if (userRole != "STUDENT") {
            Log.d(TAG, "StudentBleService created with non-STUDENT role: $userRole. Stopping self.")
            stopSelf()
            return
        }

        INSTANCE = this
        bleManager = StudentBleManager.getInstance(applicationContext)
        val db = CalisDatabase.getDatabase(applicationContext)
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

        try {
            val filter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
            ContextCompat.registerReceiver(this, bluetoothReceiver, filter, ContextCompat.RECEIVER_EXPORTED)

            val screenFilter = IntentFilter().apply {
                addAction(Intent.ACTION_SCREEN_OFF)
                addAction(Intent.ACTION_SCREEN_ON)
            }
            ContextCompat.registerReceiver(this, screenReceiver, screenFilter, ContextCompat.RECEIVER_EXPORTED)
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to register receivers", e)
        }

        updateWakeLock()

        // Resolve classroom
        registeredClassroom = runBlocking(Dispatchers.IO) {
            db.classroomDao().getActiveBoundClassroomSync() ?: db.classroomDao().getAnyClassroomSync()
        }

        updateFindingCalisStatus()
        startForegroundNotification(isFound = false, isCheckingIn = false)

        observeData()
        startBackgroundScanLoop()
        startScanCountdownLoop()

        if (isFindingCalis && bleManager.isBluetoothEnabled()) {
            bleManager.startScan(forceRestart = true)
            checkScannedDevicesForAutoCheckIn(bleManager.scannedDevices.value)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val cidFromIntent = intent?.getStringExtra("classroom_id")
        if (!cidFromIntent.isNullOrBlank() && registeredClassroom?.cid != cidFromIntent) {
            serviceScope.launch {
                val db = CalisDatabase.getDatabase(applicationContext)
                val cls = withContext(Dispatchers.IO) { db.classroomDao().getClassroomById(cidFromIntent) }
                if (cls != null) {
                    registeredClassroom = cls
                    updateFindingCalisStatus()
                    startForegroundNotification(isFound = currentIsFound, isCheckingIn = isCurrentlyCheckingIn)
                    if (isFindingCalis && bleManager.isBluetoothEnabled()) {
                        bleManager.startScan(forceRestart = true)
                        checkScannedDevicesForAutoCheckIn(bleManager.scannedDevices.value)
                    }
                }
            }
        }
        updateWakeLock()
        updateFindingCalisStatus()
        if (isFindingCalis && bleManager.isBluetoothEnabled()) {
            bleManager.startScan(forceRestart = true)
            checkScannedDevicesForAutoCheckIn(bleManager.scannedDevices.value)
        }
        return START_STICKY
    }

    private fun startForegroundNotification(isFound: Boolean, isCheckingIn: Boolean) {
        try {
            val cid = registeredClassroom?.cid ?: ""
            val notifId = ReminderNotificationHelper.getTaskNotificationId("check_in_$cid")
            val notification = ReminderNotificationHelper.buildFindingCalisNotification(
                this,
                cid,
                registeredClassroom?.classroomName,
                isFound = isFound,
                isCheckingIn = isCheckingIn,
                scanCountdown = if (!isFound && !isCheckingIn) scanCountdownSeconds else null
            )
            lastPostedFingerprint = "$cid|$isFound|$isCheckingIn|${scanCountdownSeconds}|${registeredClassroom?.classroomName}"
            lastNotificationPostTime = SystemClock.elapsedRealtime()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                try {
                    startForeground(notifId, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_CONNECTED_DEVICE)
                } catch (e: Throwable) {
                    try {
                        startForeground(notifId, notification)
                    } catch (e2: Throwable) {
                        Log.e(TAG, "Fallback startForeground failed", e2)
                    }
                }
            } else {
                try {
                    startForeground(notifId, notification)
                } catch (e: Throwable) {
                    Log.e(TAG, "Legacy startForeground failed", e)
                }
            }

            val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            try {
                notifManager?.notify(notifId, notification)
            } catch (_: Throwable) {}
        } catch (e: Throwable) {
            Log.e(TAG, "Error in startForegroundNotification", e)
        }
    }

    fun updateFindingCalisNotification(isFound: Boolean, isCheckingIn: Boolean = false, scanCountdown: Int? = null, progressStep: String? = null) {
        currentIsFound = isFound
        isCurrentlyCheckingIn = isCheckingIn

        val now = SystemClock.elapsedRealtime()
        val timeSinceLast = now - lastNotificationPostTime
        if (timeSinceLast >= NOTIFICATION_MIN_INTERVAL_MS) {
            pendingNotificationJob?.cancel()
            pendingNotificationJob = null
            postNotificationNow(isFound, isCheckingIn, scanCountdown, progressStep)
        } else {
            if (pendingNotificationJob == null || pendingNotificationJob?.isActive != true) {
                val delayMs = NOTIFICATION_MIN_INTERVAL_MS - timeSinceLast
                pendingNotificationJob = serviceScope.launch {
                    delay(delayMs)
                    postNotificationNow(currentIsFound, isCurrentlyCheckingIn, scanCountdown, progressStep)
                }
            }
        }
    }

    private fun postNotificationNow(isFound: Boolean, isCheckingIn: Boolean, scanCountdown: Int? = null, progressStep: String? = null) {
        val cid = registeredClassroom?.cid ?: ""
        val effectiveCountdown = scanCountdown ?: if (!isFound && !isCheckingIn) scanCountdownSeconds else null
        val fingerprint = "$cid|$isFound|$isCheckingIn|$effectiveCountdown|$progressStep|${registeredClassroom?.classroomName}"
        if (fingerprint == lastPostedFingerprint) return
        lastPostedFingerprint = fingerprint
        lastNotificationPostTime = SystemClock.elapsedRealtime()

        val notifId = ReminderNotificationHelper.getTaskNotificationId("check_in_$cid")
        val notification = ReminderNotificationHelper.buildFindingCalisNotification(
            this,
            cid,
            registeredClassroom?.classroomName,
            isFound = isFound,
            isCheckingIn = isCheckingIn,
            scanCountdown = effectiveCountdown,
            progressStep = progressStep
        )
        val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        try {
            notifManager?.notify(notifId, notification)
        } catch (e: Throwable) {
            Log.e(TAG, "Error posting notification: ${e.message}", e)
        }
    }

    private fun startScanCountdownLoop() {
        scanCountdownJob?.cancel()
        scanCountdownJob = serviceScope.launch {
            while (isActive) {
                if (isFindingCalis && !currentIsFound && !isConnectingOrConnected && !isCurrentlyCheckingIn) {
                    updateFindingCalisNotification(isFound = false, isCheckingIn = false, scanCountdown = scanCountdownSeconds)
                    delay(1000L)
                    scanCountdownSeconds--
                    if (scanCountdownSeconds < 0) {
                        scanCountdownSeconds = 5
                        if (bleManager.isBluetoothEnabled()) {
                            Log.d(TAG, "Scan countdown reached 0s. Refreshing BLE scan...")
                            bleManager.startScan(forceRestart = true)
                        }
                    }
                } else {
                    scanCountdownSeconds = 5
                    delay(1000L)
                }
            }
        }
    }

    private fun observeData() {
        serviceScope.launch {
            repository.currentStudentProfile.collect { profile ->
                currentProfile = profile
            }
        }
        serviceScope.launch {
            repository.activeBoundClassroom.collect { classroom ->
                if (classroom != null) {
                    registeredClassroom = classroom
                    updateFindingCalisStatus()
                }
            }
        }
        serviceScope.launch {
            bleManager.incomingPackets.collect { packet ->
                handlePacket(packet)
            }
        }
        serviceScope.launch {
            var lastState: StudentBleManager.ConnectionState? = null
            bleManager.connectionState.collect { state ->
                if (state == lastState) return@collect
                lastState = state

                if (state is StudentBleManager.ConnectionState.Connected) {
                    Log.d(TAG, "BLE ConnectionState is Connected")
                } else if (state is StudentBleManager.ConnectionState.Disconnected || state is StudentBleManager.ConnectionState.Error) {
                    val classroom = registeredClassroom
                    val cid = classroom?.cid ?: ""
                    val todayYmd = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
                    val prefs = getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                    val isCheckedIn = (cid.isNotBlank() && prefs.getBoolean("checked_in_today_${cid}_$todayYmd", false)) ||
                            prefs.getBoolean("checked_in_today_global_$todayYmd", false)

                    if (!isCheckedIn && cid.isNotBlank() && isFindingCalis && !isConnectingOrConnected) {
                        Log.d(TAG, "BLE state is ${state.javaClass.simpleName}. Resuming scan for check-in...")
                        updateFindingCalisNotification(isFound = currentIsFound, isCheckingIn = false)
                        if (bleManager.isBluetoothEnabled()) {
                            bleManager.startScan()
                        }
                    }
                }
            }
        }
    }

    private fun updateFindingCalisStatus() {
        val classroom = registeredClassroom
        if (classroom == null) {
            isFindingCalis = false
            return
        }
        val prefs = getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val todayYmd = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        val isCheckedIn = prefs.getBoolean("checked_in_today_${classroom.cid}_$todayYmd", false) ||
                prefs.getBoolean("checked_in_today_global_$todayYmd", false)

        if (isCheckedIn) {
            isFindingCalis = false
        } else {
            isFindingCalis = true
            prefs.edit().putBoolean("finding_calis_${classroom.cid}", true).apply()
        }
    }

    private fun updateWakeLock() {
        try {
            if (wakeLock == null) {
                val powerManager = getSystemService(Context.POWER_SERVICE) as? PowerManager
                wakeLock = powerManager?.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Calis:StudentCheckInWakeLock")?.apply {
                    setReferenceCounted(false)
                }
            }
            if (wakeLock?.isHeld == false) {
                wakeLock?.acquire(2 * 60 * 60 * 1000L) // 2 hour max failsafe timeout
                Log.d(TAG, "Acquired PARTIAL_WAKE_LOCK for background check-in discovery")
            }
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to acquire wakeLock: ${e.message}")
        }
    }

    private fun releaseWakeLock() {
        try {
            if (wakeLock?.isHeld == true) {
                wakeLock?.release()
                Log.d(TAG, "Released PARTIAL_WAKE_LOCK")
            }
        } catch (_: Throwable) {}
    }

    private fun checkScannedDevicesForAutoCheckIn(devices: List<StudentBleManager.ScannedDevice>) {
        val classroom = registeredClassroom ?: return

        val prefs = getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val todayYmd = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        val todayMdy = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(Date())
        val isAlreadyCheckedIn = prefs.getBoolean("checked_in_today_${classroom.cid}_$todayYmd", false) ||
                prefs.getBoolean("checked_in_today_${classroom.cid}_$todayMdy", false) ||
                prefs.getBoolean("checked_in_today_global_$todayYmd", false)

        if (isAlreadyCheckedIn) {
            prefs.edit().putBoolean("finding_calis_${classroom.cid}", false).apply()
            isFindingCalis = false
            stopForeground(STOP_FOREGROUND_REMOVE)
            releaseWakeLock()
            stopSelf()
            return
        }

        updateFindingCalisStatus()
        if (!isFindingCalis || !bleManager.isBluetoothEnabled()) return

        val now = System.currentTimeMillis()
        val targetMac = classroom.deviceAddress
        val targetName = classroom.classroomName

        val nearbyDevice = devices.find { d ->
            val macMatches = !targetMac.isNullOrBlank() && d.address.equals(targetMac, ignoreCase = true)
            val nameMatches = !targetName.isNullOrBlank() && (
                d.name.equals(targetName, ignoreCase = true) ||
                d.name.contains(targetName, ignoreCase = true)
            )
            val isCalisDeviceName = d.name.contains("Calis", ignoreCase = true) ||
                    d.name.contains("Classroom", ignoreCase = true) ||
                    d.name.contains("Setup", ignoreCase = true) ||
                    d.name.contains("CD", ignoreCase = true) ||
                    d.name.contains("ESP32", ignoreCase = true)
            val serviceMatches = d.hasServiceUuid
            macMatches || nameMatches || isCalisDeviceName || serviceMatches
        }

        val isRecentlyActive = nearbyDevice != null && (now - nearbyDevice.lastSeenTimestamp <= 10000L)

        if (isRecentlyActive && nearbyDevice != null) {
            lastNearbyDeviceSeenTime = nearbyDevice.lastSeenTimestamp
            currentIsFound = true
            if (!isConnectingOrConnected && !isCurrentlyCheckingIn) {
                updateFindingCalisNotification(isFound = true, isCheckingIn = false, progressStep = "Connecting")
                Log.d(TAG, "Calis Device detected (${nearbyDevice.address})! Executing in-app check-in progress pipeline...")
                initiateCheckInConnection(nearbyDevice.address, classroom)
            }
        } else {
            currentIsFound = false
            if (!isConnectingOrConnected && !isCurrentlyCheckingIn) {
                updateFindingCalisNotification(isFound = false, isCheckingIn = false)
            }
        }
    }

    private fun initiateCheckInConnection(targetAddress: String, classroom: ClassroomEntity) {
        if (isConnectingOrConnected) return
        isConnectingOrConnected = true
        isCurrentlyCheckingIn = true

        connectionAttemptJob?.cancel()
        connectionAttemptJob = serviceScope.launch {
            val cid = classroom.cid
            Log.d(TAG, "Initiating check-in connection to registered Calis Device ($targetAddress) for classroom $cid...")

            bleManager.setRegisteredDevice(targetAddress, classroom.classroomName)
            var connected = false

            // Mirror StudentViewModel retry connection loop (up to 2 attempts with clearBleObject)
            for (attempt in 1..2) {
                updateFindingCalisNotification(isFound = true, isCheckingIn = true, progressStep = "Connecting")
                bleManager.stopScan()
                if (attempt > 1 || bleManager.connectionState.value !is StudentBleManager.ConnectionState.Connected) {
                    bleManager.clearBleObject(triggerScan = false)
                    delay(300)
                }
                bleManager.connect(targetAddress)

                val startMs = System.currentTimeMillis()
                while (isActive && System.currentTimeMillis() - startMs < 5000L) {
                    delay(200L)
                    val todayYmd = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
                    val prefs = getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                    val isCheckedIn = prefs.getBoolean("checked_in_today_${cid}_$todayYmd", false) ||
                            prefs.getBoolean("checked_in_today_global_$todayYmd", false)
                    if (isCheckedIn) {
                        connected = true
                        break
                    }

                    val connState = bleManager.connectionState.value
                    if (connState is StudentBleManager.ConnectionState.Connected) {
                        connected = true
                        break
                    }
                    if (connState is StudentBleManager.ConnectionState.Error) {
                        Log.w(TAG, "Connection attempt $attempt failed with state: $connState")
                        break
                    }
                }
                if (connected) break
                bleManager.clearBleObject(triggerScan = false)
            }

            if (connected) {
                val profile = currentProfile ?: withContext(Dispatchers.IO) { repository.getStudentProfileOnce() }
                if (profile != null) {
                    updateFindingCalisNotification(isFound = true, isCheckingIn = true, progressStep = "Transmitting")
                    delay(500) // Brief delay for GATT notifications to settle
                    Log.d(TAG, "Connected! Transmitting Check-In Request packet over BLE GATT...")
                    val chkInPacket = BlePacket.CheckInRequest(sid = profile.sid)
                    bleManager.sendPacket(chkInPacket) { success ->
                        if (success) {
                            Log.d(TAG, "Check-In Request packet successfully delivered over BLE GATT!")
                            serviceScope.launch {
                                updateFindingCalisNotification(isFound = true, isCheckingIn = true, progressStep = "Checked-In")
                                val graceTimeStr = classroom.graceTime
                                val graceMins = ReminderNotificationHelper.parseTimeToMinutes(graceTimeStr)
                                val calendar = Calendar.getInstance()
                                val currentMinutes = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE)
                                val attendanceStatus = if (graceMins != -1 && currentMinutes > graceMins) "LATE" else "PRESENT"
                                val currentTs = System.currentTimeMillis() / 1000L

                                try {
                                    repository.recordStudentAttendance(
                                        classroomId = classroom.cid,
                                        studentId = profile.sid,
                                        studentName = profile.fullName,
                                        status = attendanceStatus,
                                        timestamp = currentTs
                                    )
                                } catch (e: Exception) {
                                    Log.e(TAG, "Error recording attendance in DB: ${e.message}")
                                }

                                val todayYmd = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
                                val todayMdy = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(Date())
                                val prefs = getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                                prefs.edit()
                                    .putBoolean("checked_in_today_${classroom.cid}_$todayYmd", true)
                                    .putBoolean("checked_in_today_${classroom.cid}_$todayMdy", true)
                                    .putBoolean("checked_in_today_global_$todayYmd", true)
                                    .putBoolean("checked_in_today_global_$todayMdy", true)
                                    .putBoolean("finding_calis_${classroom.cid}", false)
                                    .apply()

                                updateFindingCalisStatus()
                                ReminderNotificationHelper.updateNotificationToCheckedInSuccess(applicationContext, classroom.cid)

                                delay(1000)
                                isConnectingOrConnected = false
                                isCurrentlyCheckingIn = false
                                stopForeground(STOP_FOREGROUND_REMOVE)
                                releaseWakeLock()
                                stopSelf()
                            }
                        } else {
                            Log.e(TAG, "Failed to transmit check-in packet over BLE.")
                        }
                    }

                    // Await response or confirmation with timeout
                    val waitStart = System.currentTimeMillis()
                    while (isActive && System.currentTimeMillis() - waitStart < 12000L) {
                        delay(300L)
                        val todayYmd = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
                        val prefs = getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                        if (prefs.getBoolean("checked_in_today_${cid}_$todayYmd", false) ||
                            prefs.getBoolean("checked_in_today_global_$todayYmd", false)
                        ) {
                            return@launch
                        }
                    }
                }
            }

            // Connection or response timed out
            Log.w(TAG, "Check-in attempt ended without completion. Resetting for re-scan...")
            isConnectingOrConnected = false
            isCurrentlyCheckingIn = false
            bleManager.clearBleObject(triggerScan = bleManager.isBluetoothEnabled())
            updateFindingCalisNotification(isFound = false, isCheckingIn = false)
            if (bleManager.isBluetoothEnabled()) {
                bleManager.startScan(forceRestart = true)
            }
        }
    }

    private fun startBackgroundScanLoop() {
        serviceScope.launch {
            bleManager.scannedDevices.collect { devices ->
                checkScannedDevicesForAutoCheckIn(devices)
            }
        }

        // Periodic maintenance and stale timeout loop (every 2 seconds)
        serviceScope.launch {
            var inactiveCycleCount = 0
            while (isActive) {
                delay(2000L)
                val classroom = registeredClassroom
                updateFindingCalisStatus()

                val hasPerms = BlePermissionUtils.hasPermissions(applicationContext)
                val isBt = bleManager.isBluetoothEnabled()
                val isLoc = BlePermissionUtils.isLocationEnabled(applicationContext)

                if (classroom != null && isBt && hasPerms && isLoc) {
                    val prefs = getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                    val todayYmd = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
                    val isCheckedIn = prefs.getBoolean("checked_in_today_${classroom.cid}_$todayYmd", false) ||
                            prefs.getBoolean("checked_in_today_global_$todayYmd", false)

                    if (isCheckedIn) {
                        prefs.edit().putBoolean("finding_calis_${classroom.cid}", false).apply()
                        stopForeground(STOP_FOREGROUND_REMOVE)
                        releaseWakeLock()
                        stopSelf()
                        break
                    }

                    if (isFindingCalis) {
                        inactiveCycleCount = 0
                        val now = System.currentTimeMillis()
                        if (currentIsFound && (now - lastNearbyDeviceSeenTime > 6000L)) {
                            currentIsFound = false
                            if (!isConnectingOrConnected && !isCurrentlyCheckingIn) {
                                Log.d(TAG, "Stale device timeout (6000ms exceeded). Transitioning notification to Scanning...")
                                updateFindingCalisNotification(isFound = false, isCheckingIn = false)
                            }
                        }

                        val connState = bleManager.connectionState.value
                        if ((connState is StudentBleManager.ConnectionState.Disconnected || connState is StudentBleManager.ConnectionState.Scanning) && !isConnectingOrConnected) {
                            if (!bleManager.isScanning) {
                                bleManager.startScan()
                            }
                            checkScannedDevicesForAutoCheckIn(bleManager.scannedDevices.value)
                        }
                    } else {
                        inactiveCycleCount++
                        if (inactiveCycleCount > 15) {
                            Log.d(TAG, "finding_calis remains false, stopping StudentBleService cleanly.")
                            stopForeground(STOP_FOREGROUND_REMOVE)
                            releaseWakeLock()
                            stopSelf()
                            break
                        }
                    }
                } else if (!isBt || !hasPerms || !isLoc) {
                    handleBluetoothDisabled()
                    break
                }
            }
        }
    }

    private fun handlePacket(packet: BlePacket) {
        when (packet) {
            is BlePacket.AtdAcceptedResponse -> {
                Log.d(TAG, "AtdAcceptedResponse received! Check-In complete!")
                connectionAttemptJob?.cancel()
                isConnectingOrConnected = false
                isCurrentlyCheckingIn = false

                serviceScope.launch {
                    val db = CalisDatabase.getDatabase(applicationContext)
                    val profile = currentProfile ?: withContext(Dispatchers.IO) { repository.getStudentProfileOnce() } ?: return@launch
                    val classroom = registeredClassroom ?: withContext(Dispatchers.IO) {
                        db.classroomDao().getActiveBoundClassroomSync() ?: db.classroomDao().getAnyClassroomSync()
                    } ?: return@launch

                    val graceTimeStr = classroom.graceTime
                    val graceMins = ReminderNotificationHelper.parseTimeToMinutes(graceTimeStr)
                    val calendar = Calendar.getInstance()
                    val packetMillis = if (packet.timestamp > 1_000_000_000_000L) packet.timestamp else if (packet.timestamp > 0L) packet.timestamp * 1000L else System.currentTimeMillis()
                    calendar.timeInMillis = packetMillis
                    val currentMinutes = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE)
                    val attendanceStatus = if (graceMins != -1 && currentMinutes > graceMins) "LATE" else "PRESENT"

                    repository.recordStudentAttendance(
                        classroomId = classroom.cid,
                        studentId = profile.sid,
                        studentName = profile.fullName,
                        status = attendanceStatus,
                        timestamp = packet.timestamp
                    )

                    val todayYmd = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
                    val todayMdy = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(Date())
                    val prefs = getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                    prefs.edit()
                        .putBoolean("checked_in_today_${classroom.cid}_$todayYmd", true)
                        .putBoolean("checked_in_today_${classroom.cid}_$todayMdy", true)
                        .putBoolean("checked_in_today_global_$todayYmd", true)
                        .putBoolean("checked_in_today_global_$todayMdy", true)
                        .putBoolean("finding_calis_${classroom.cid}", false)
                        .apply()

                    stopForeground(STOP_FOREGROUND_DETACH)

                    ReminderNotificationHelper.updateNotificationToCheckedInSuccess(
                        applicationContext,
                        classroom.cid,
                        classroom.classroomName
                    )

                    bleManager.clearBleObject()
                    releaseWakeLock()
                    delay(500)
                    stopSelf()
                    INSTANCE = null
                }
            }
            is BlePacket.DoneResponse -> {
                Log.d(TAG, "DoneResponse received in background")
                stopForeground(STOP_FOREGROUND_REMOVE)
                releaseWakeLock()
                stopSelf()
                INSTANCE = null
            }
            else -> {}
        }
    }

    private fun handleBluetoothDisabled() {
        isConnectingOrConnected = false
        isCurrentlyCheckingIn = false
        currentIsFound = false
        connectionAttemptJob?.cancel()
        pendingNotificationJob?.cancel()
        val c = registeredClassroom
        if (c != null) {
            val prefs = getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
            val cid = c.cid
            val todayYmd = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
            prefs.edit()
                .putBoolean("finding_calis_$cid", false)
                .putBoolean("check_in_reminded_${cid}_$todayYmd", false)
                .apply()
            ReminderNotificationHelper.checkAndPostCheckInReminder(applicationContext)
        }
        stopForeground(STOP_FOREGROUND_REMOVE)
        releaseWakeLock()
        stopSelf()
        INSTANCE = null
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        if (INSTANCE == this) {
            INSTANCE = null
        }
        connectionAttemptJob?.cancel()
        pendingNotificationJob?.cancel()
        try {
            serviceScope.cancel()
        } catch (_: Throwable) {}
        try {
            unregisterReceiver(bluetoothReceiver)
        } catch (_: Throwable) {}
        try {
            unregisterReceiver(screenReceiver)
        } catch (_: Throwable) {}
        releaseWakeLock()
        super.onDestroy()
    }
}
