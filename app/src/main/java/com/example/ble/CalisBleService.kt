package com.example.ble

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.MainActivity
import com.example.R
import com.example.data.db.CalisDatabase
import com.example.data.db.ClassroomEntity
import com.example.data.db.TeacherEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.isActive
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CalisBleService : Service() {

    companion object {
        const val TAG = "CalisBleService"
        const val CHANNEL_ID = "calis_tc_session_channel"
        const val NOTIFICATION_ID = 1001

        const val ACTION_START_SESSION = "com.example.calis.ACTION_START_SESSION"
        const val ACTION_END_SESSION = "com.example.calis.ACTION_END_SESSION"
        const val ACTION_CONNECT = "com.example.calis.ACTION_CONNECT"
        const val ACTION_DISCONNECT = "com.example.calis.ACTION_DISCONNECT"
        const val ACTION_ENABLE_AND_START_SESSION = "com.example.calis.ACTION_ENABLE_AND_START_SESSION"
        const val ACTION_UPDATE_NOTIFICATION = "com.example.calis.ACTION_UPDATE_NOTIFICATION"
        const val ACTION_STOP_SERVICE = "com.example.calis.ACTION_STOP_SERVICE"

        @Volatile
        private var INSTANCE: CalisBleService? = null

        fun isServiceRunning(): Boolean = INSTANCE != null

        fun startService(context: Context) {
            try {
                val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                val userRole = prefs.getString("user_role", null)

                if (userRole != "TEACHER") {
                    Log.d(TAG, "startService skipped: user is not TEACHER (role: $userRole)")
                    return
                }

                // Protect against background starts and Missing Permission SecurityExceptions on Android 12/14+
                if (!com.example.util.BlePermissionUtils.hasPermissions(context)) {
                    Log.d(TAG, "startService skipped: Bluetooth permissions not granted yet")
                    return
                }

                val intent = Intent(context, CalisBleService::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(intent)
                } else {
                    context.startService(intent)
                }
            } catch (e: Throwable) {
                Log.e(TAG, "Failed to start CalisBleService: ${e.message}", e)
            }
        }

        fun setSessionActive(context: Context, isActive: Boolean) {
            val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
            val userRole = prefs.getString("user_role", null)
            if (userRole != "TEACHER") return

            val service = INSTANCE
            if (service != null) {
                service.setSessionActiveState(isActive)
            }
        }

        fun updateNotification(context: Context, immediate: Boolean = false) {
            val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
            val userRole = prefs.getString("user_role", null)
            // If the user is NOT a teacher, ensure any existing service and notification are completely removed
            if (userRole != "TEACHER") {
                val service = INSTANCE
                if (service != null) {
                    service.stopForeground(STOP_FOREGROUND_REMOVE)
                    service.stopSelf()
                    INSTANCE = null
                }
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
                notificationManager?.cancel(NOTIFICATION_ID)
                return
            }

            val service = INSTANCE
            if (service != null) {
                if (immediate) {
                    service.updateToolbarNotificationImmediate()
                } else {
                    service.updateToolbarNotification()
                }
                return
            }
        }

        private val activeTaskSubmissionNotifIds = java.util.concurrent.ConcurrentHashMap.newKeySet<Int>()
        private const val COMBINED_NOTIF_ID = 9999
        private var combinedNotificationCount = 0
        private var updateRunnable: Runnable? = null
        private val handler = android.os.Handler(android.os.Looper.getMainLooper())

        fun showTaskSubmissionNotification(
            context: Context,
            studentName: String,
            taskTitle: String,
            score: Int,
            maxScore: Int,
            taskId: String? = null
        ) {
            try {
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager ?: return
                val channelId = "calis_task_submissions"
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val existingChannel = notificationManager.getNotificationChannel(channelId)
                    if (existingChannel == null) {
                        val channel = NotificationChannel(
                            channelId,
                            "CALIS Task Submissions",
                            NotificationManager.IMPORTANCE_HIGH
                        ).apply {
                            description = "Notifications for student task submissions and scores"
                            setShowBadge(true)
                            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                        }
                        notificationManager.createNotificationChannel(channel)
                    }
                }

                val scoreText = if (maxScore > 0) "$score / $maxScore pts" else "Complete"
                val intent = Intent(context, com.example.MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    putExtra("EXTRA_NAVIGATE_TARGET", "TASK_SUBMISSION")
                    if (!taskId.isNullOrEmpty()) {
                        putExtra("EXTRA_OPEN_TASK_ID", taskId)
                    }
                }

                if (combinedNotificationCount < 3) {
                    val notifId = (System.currentTimeMillis() % 100000).toInt() + 2000
                    activeTaskSubmissionNotifIds.add(notifId)
                    combinedNotificationCount++

                    val pendingIntent = PendingIntent.getActivity(
                        context,
                        notifId,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )

                    val notification = NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(android.R.drawable.stat_notify_chat)
                        .setContentTitle("Task Submission: $taskTitle")
                        .setContentText("$studentName submitted \"$taskTitle\" ($scoreText)")
                        .setStyle(
                            NotificationCompat.BigTextStyle()
                                .bigText("Student $studentName submitted \"$taskTitle\".\nResult: $scoreText")
                        )
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .build()

                    notificationManager.notify(notifId, notification)
                } else {
                    // Combine into one
                    combinedNotificationCount++

                    // Cancel previous individual ones
                    for (id in activeTaskSubmissionNotifIds) {
                        notificationManager.cancel(id)
                    }
                    activeTaskSubmissionNotifIds.clear()

                    val pendingIntent = PendingIntent.getActivity(
                        context,
                        COMBINED_NOTIF_ID,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )

                    // 1. Initially (temporarily for 5s) label with the current student's info
                    val notification = NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(android.R.drawable.stat_notify_chat)
                        .setContentTitle("Multiple Submissions ($combinedNotificationCount)")
                        .setContentText("$studentName submitted \"$taskTitle\" ($scoreText)")
                        .setStyle(
                            NotificationCompat.BigTextStyle()
                                .bigText("Student $studentName submitted \"$taskTitle\".\nResult: $scoreText")
                        )
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .build()

                    notificationManager.notify(COMBINED_NOTIF_ID, notification)

                    // Cancel any previous update task
                    updateRunnable?.let { handler.removeCallbacks(it) }

                    // Create and post a new update runnable to change label after 5s
                    val run = Runnable {
                        val finalNotif = NotificationCompat.Builder(context, channelId)
                            .setSmallIcon(android.R.drawable.stat_notify_chat)
                            .setContentTitle("Multiple Submissions ($combinedNotificationCount)")
                            .setContentText("student submitted, open app")
                            .setStyle(
                                NotificationCompat.BigTextStyle()
                                    .bigText("$combinedNotificationCount student submissions received.\nOpen the app to view the results.")
                            )
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .build()
                        notificationManager.notify(COMBINED_NOTIF_ID, finalNotif)
                    }
                    updateRunnable = run
                    handler.postDelayed(run, 5000)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error displaying task submission notification: ${e.message}")
            }
        }

        fun dismissTaskSubmissionNotification(context: Context) {
            try {
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager ?: return
                for (id in activeTaskSubmissionNotifIds) {
                    notificationManager.cancel(id)
                }
                activeTaskSubmissionNotifIds.clear()
                notificationManager.cancel(COMBINED_NOTIF_ID)
                combinedNotificationCount = 0
                updateRunnable?.let { handler.removeCallbacks(it) }
                updateRunnable = null
            } catch (e: Exception) {
                Log.e(TAG, "Error dismissing task submission notifications: ${e.message}")
            }
        }

        fun stopService(context: Context) {
            try {
                val service = INSTANCE
                if (service != null) {
                    service.stopForeground(STOP_FOREGROUND_REMOVE)
                    service.stopSelf()
                    INSTANCE = null
                }
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
                notificationManager?.cancel(NOTIFICATION_ID)

                val intent = Intent(context, CalisBleService::class.java)
                context.stopService(intent)
            } catch (e: Exception) {
                Log.e(TAG, "Error stopping CalisBleService: ${e.message}")
            }
        }
    }

    private val serviceScope = CoroutineScope(Dispatchers.Main + Job())
    private lateinit var bleManager: TeacherBleManager
    private lateinit var database: CalisDatabase
    private var currentTeacher: TeacherEntity? = null
    private var currentClassroom: ClassroomEntity? = null
    private var currentConnectionState: TeacherBleManager.ConnectionState = TeacherBleManager.ConnectionState.Disconnected
    private var currentDeviceState: DeviceState = DeviceState.UNKNOWN
    private var scannedDevicesList: List<TeacherBleManager.ScannedDevice> = emptyList()
    private var currentIsAuthenticated: Boolean = false
    private var isSessionActiveOverride: Boolean? = null

    // Rate-limiting and debouncing variables to respect OS notification throttle mechanism
    private var lastPostedFingerprint: String? = null
    private var lastNotificationPostTime = 0L
    private var pendingNotificationJob: Job? = null
    private val NOTIFICATION_MIN_INTERVAL_MS = 600L

    private var wakeLock: PowerManager.WakeLock? = null

    private val screenReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                Intent.ACTION_SCREEN_OFF -> {
                    Log.d(TAG, "Screen OFF detected while classroom session is active. Ensuring background BLE execution...")
                    updateWakeLock()
                }
                Intent.ACTION_SCREEN_ON -> {
                    Log.d(TAG, "Screen ON detected.")
                    updateWakeLock()
                }
            }
        }
    }

    private val bluetoothReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == BluetoothAdapter.ACTION_STATE_CHANGED) {
                val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                Log.d(TAG, "BluetoothAdapter state changed: $state")
                bleManager.onBluetoothStateChanged(state)
                updateToolbarNotificationImmediate()
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        val prefs = applicationContext.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val userRole = prefs.getString("user_role", null)
        if (userRole != "TEACHER") {
            Log.d(TAG, "CalisBleService created but user is not TEACHER (role: $userRole). Stopping immediately.")
            stopSelf()
            return
        }

        INSTANCE = this
        bleManager = TeacherBleManager.getInstance(applicationContext)
        database = CalisDatabase.getDatabase(applicationContext)

        val filter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        ContextCompat.registerReceiver(this, bluetoothReceiver, filter, ContextCompat.RECEIVER_EXPORTED)

        val screenFilter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_SCREEN_ON)
        }
        ContextCompat.registerReceiver(this, screenReceiver, screenFilter, ContextCompat.RECEIVER_EXPORTED)

        createNotificationChannel()
        startForegroundWithToolbar()
        observeStateChanges()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val prefs = applicationContext.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val userRole = prefs.getString("user_role", null)
        if (userRole != "TEACHER") {
            Log.d(TAG, "CalisBleService onStartCommand with non-TEACHER role: $userRole. Stopping foreground and self.")
            stopForeground(STOP_FOREGROUND_REMOVE)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            notificationManager?.cancel(NOTIFICATION_ID)
            stopSelf()
            return START_NOT_STICKY
        }

        createNotificationChannel()
        startForegroundWithToolbar()

        val action = intent?.action
        if (action != null) {
            handleAction(action)
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        if (INSTANCE == this) {
            INSTANCE = null
        }
        pendingNotificationJob?.cancel()
        pendingNotificationJob = null
        try {
            serviceScope.cancel()
        } catch (e: Exception) {
            Log.e(TAG, "Error cancelling serviceScope: ${e.message}")
        }
        try {
            if (::bleManager.isInitialized) {
                bleManager.stopScan()
                bleManager.disconnect()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error stopping BLE in service onDestroy: ${e.message}")
        }
        try {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            notificationManager?.cancel(NOTIFICATION_ID)
            notificationManager?.cancel(COMBINED_NOTIF_ID)
        } catch (e: Exception) {
            Log.e(TAG, "Error cancelling notifications in onDestroy: ${e.message}")
        }
        try {
            unregisterReceiver(bluetoothReceiver)
        } catch (e: Exception) {
            Log.e(TAG, "Error unregistering bluetoothReceiver: ${e.message}")
        }
        try {
            unregisterReceiver(screenReceiver)
        } catch (e: Exception) {
            Log.e(TAG, "Error unregistering screenReceiver: ${e.message}")
        }
        releaseWakeLock()
        super.onDestroy()
    }

    private fun updateWakeLock() {
        val isConnected = currentConnectionState is TeacherBleManager.ConnectionState.Connected
        val isSessionActive = isSessionActiveOverride == true || currentClassroom?.isActiveSession == true
        val shouldHoldWakeLock = isConnected && isSessionActive

        if (shouldHoldWakeLock) {
            try {
                if (wakeLock == null) {
                    val powerManager = getSystemService(Context.POWER_SERVICE) as? PowerManager
                    wakeLock = powerManager?.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Calis:TeacherSessionWakeLock")?.apply {
                        setReferenceCounted(false)
                    }
                }
                if (wakeLock?.isHeld == false) {
                    wakeLock?.acquire(2 * 60 * 60 * 1000L) // 2 hour max failsafe timeout
                    Log.d(TAG, "Acquired PARTIAL_WAKE_LOCK: Keeping app running in background even while screen is off during active session")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error acquiring wake lock: ${e.message}")
            }
        } else {
            releaseWakeLock()
        }
    }

    private fun releaseWakeLock() {
        try {
            if (wakeLock?.isHeld == true) {
                wakeLock?.release()
                Log.d(TAG, "Released PARTIAL_WAKE_LOCK")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error releasing wake lock: ${e.message}")
        }
    }

    private var isDisconnectingState = false
    private var disconnectTimeoutJob: kotlinx.coroutines.Job? = null

    private fun observeStateChanges() {
        serviceScope.launch {
            bleManager.connectionState.collect { state ->
                currentConnectionState = state
                if (state is TeacherBleManager.ConnectionState.Disconnecting) {
                    isDisconnectingState = true
                } else if (state is TeacherBleManager.ConnectionState.Disconnected && isDisconnectingState) {
                    disconnectTimeoutJob?.cancel()
                    disconnectTimeoutJob = serviceScope.launch {
                        kotlinx.coroutines.delay(3000L)
                        isDisconnectingState = false
                        updateToolbarNotificationImmediate()
                    }
                } else if (state is TeacherBleManager.ConnectionState.Connected) {
                    isDisconnectingState = false
                    disconnectTimeoutJob?.cancel()
                }
                updateToolbarNotificationImmediate()
                updateWakeLock()
            }
        }

        serviceScope.launch {
            bleManager.isAuthenticated.collect { auth ->
                currentIsAuthenticated = auth
                updateToolbarNotificationImmediate()
                updateWakeLock()
            }
        }

        serviceScope.launch {
            bleManager.deviceState.collect { dState ->
                currentDeviceState = dState
                if (dState == DeviceState.SESSION_ACTIVE_OPEN || dState == DeviceState.SESSION_ACTIVE_BUSY) {
                    isSessionActiveOverride = true
                    updateToolbarNotificationImmediate()
                } else if (dState == DeviceState.SESSION_INACTIVE) {
                    if (isSessionActiveOverride == true && currentClassroom?.isActiveSession != true) {
                        isSessionActiveOverride = false
                        updateToolbarNotificationImmediate()
                    } else {
                        updateToolbarNotification()
                    }
                } else {
                    updateToolbarNotification()
                }
                updateWakeLock()
            }
        }

        serviceScope.launch {
            bleManager.scannedDevices.collect { devices ->
                scannedDevicesList = devices
                if (currentConnectionState !is TeacherBleManager.ConnectionState.Connected) {
                    updateToolbarNotification()
                }
            }
        }

        serviceScope.launch {
            database.teacherDao().getTeacher().collect { teacher ->
                currentTeacher = teacher
                updateToolbarNotification()
            }
        }

        serviceScope.launch {
            database.classroomDao().getActiveBoundClassroom().combine(
                database.classroomDao().getAnyClassroom()
            ) { active, any ->
                active ?: any
            }.collect { classroom ->
                currentClassroom = classroom
                if (isSessionActiveOverride != null && classroom?.isActiveSession == isSessionActiveOverride) {
                    isSessionActiveOverride = null
                }
                updateToolbarNotificationImmediate()
                updateWakeLock()
            }
        }

        // Keep searching for Calis Device when disconnected and periodically verify the 8-second scan timing
        serviceScope.launch {
            delay(200) // Fast initial start
            while (isActive) {
                if (bleManager.isBluetoothEnabled()) {
                    val connState = bleManager.connectionState.value
                    if (connState is TeacherBleManager.ConnectionState.Disconnected || connState is TeacherBleManager.ConnectionState.Error) {
                        // Request a standard scan refresh
                        bleManager.startScan(forceRestart = false)
                        // Periodic check to gracefully transition stale timing to 'Searching for Device...'
                        updateToolbarNotification()
                    }
                }
                kotlinx.coroutines.delay(2000)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun handleAction(action: String) {
        Log.d(TAG, "Handling intent action: $action")
        when (action) {
            ACTION_ENABLE_AND_START_SESSION -> {
                val btManager = getSystemService(Context.BLUETOOTH_SERVICE) as? BluetoothManager
                val adapter = btManager?.adapter
                try {
                    if (adapter?.isEnabled != true) {
                        adapter?.enable()
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error enabling bluetooth adapter: ${e.message}")
                }
                bleManager.setPendingAction("START_SESSION")
                updateToolbarNotificationImmediate()
            }

            ACTION_START_SESSION -> {
                isSessionActiveOverride = true
                bleManager.setPendingAction("START_SESSION")
                updateToolbarNotificationImmediate()
                serviceScope.launch {
                    val teacher = database.teacherDao().getTeacherOnce()
                    val classroom = database.classroomDao().getActiveBoundClassroomSync() ?: database.classroomDao().getAnyClassroomSync()
                    if (teacher != null && classroom != null && bleManager.isAuthenticated.value) {
                        kotlinx.coroutines.delay(200L)
                        bleManager.sendPacket(BlePacket.StartSessionCommand) { success ->
                            serviceScope.launch {
                                if (success) {
                                    database.classroomDao().updateClassroom(classroom.copy(isActiveSession = true))
                                    val prefs = applicationContext.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                                    val todayStr = java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.US).format(java.util.Date())
                                    prefs.edit().putString("last_session_started_at_date_${classroom.cid}", todayStr).apply()
                                } else {
                                    isSessionActiveOverride = null
                                }
                                updateToolbarNotificationImmediate()
                            }
                        }
                    }
                }
            }

            ACTION_END_SESSION -> {
                isSessionActiveOverride = false
                bleManager.setPendingAction("END_SESSION")
                updateToolbarNotificationImmediate()
                serviceScope.launch {
                    val teacher = database.teacherDao().getTeacherOnce()
                    val classroom = database.classroomDao().getActiveBoundClassroomSync() ?: database.classroomDao().getAnyClassroomSync()
                    if (teacher != null && classroom != null) {
                        bleManager.sendPacket(BlePacket.EndSessionCommand) { success ->
                            serviceScope.launch {
                                if (success) {
                                    database.classroomDao().updateClassroom(classroom.copy(isActiveSession = false))
                                } else {
                                    isSessionActiveOverride = null
                                }
                                updateToolbarNotificationImmediate()
                            }
                        }
                    }
                }
            }

            ACTION_CONNECT -> {
                bleManager.setPendingAction("CONNECT")
                bleManager.isExplicitlyDisconnected = false
                currentConnectionState = TeacherBleManager.ConnectionState.Connecting
                updateToolbarNotificationImmediate()
                val classroom = currentClassroom
                val targetMac = classroom?.deviceAddress
                val found = bleManager.scannedDevices.value.find { dev ->
                    (targetMac != null && dev.address.equals(targetMac, ignoreCase = true)) ||
                    (classroom?.classroomName?.isNotBlank() == true && dev.name.contains(classroom.classroomName, ignoreCase = true)) ||
                    dev.name.contains("Calis", ignoreCase = true)
                }
                if (found != null) {
                    bleManager.connect(found.address)
                } else if (!targetMac.isNullOrBlank()) {
                    bleManager.connect(targetMac)
                }
                currentConnectionState = bleManager.connectionState.value
                updateToolbarNotificationImmediate()
            }

            ACTION_DISCONNECT -> {
                isDisconnectingState = true
                bleManager.setPendingAction("DISCONNECT")
                bleManager.isExplicitlyDisconnected = true
                isSessionActiveOverride = false
                bleManager.disconnect()
                bleManager.clearBleObject(triggerScan = true)

                val classroom = currentClassroom
                if (classroom != null) {
                    bleManager.setRegisteredDevice(classroom.deviceAddress, classroom.classroomName)
                }
                if (bleManager.isBluetoothEnabled()) {
                    bleManager.startScan(forceRestart = true)
                }

                currentConnectionState = bleManager.connectionState.value
                updateToolbarNotificationImmediate()

                serviceScope.launch {
                    if (classroom != null && classroom.isActiveSession) {
                        database.classroomDao().updateClassroom(classroom.copy(isActiveSession = false))
                    }
                    kotlinx.coroutines.delay(3000L)
                    isDisconnectingState = false
                    updateToolbarNotificationImmediate()
                }
            }

            ACTION_UPDATE_NOTIFICATION -> {
                updateToolbarNotificationImmediate()
            }

            ACTION_STOP_SERVICE -> {
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
                // Force close the app/process completely
                android.os.Process.killProcess(android.os.Process.myPid())
                System.exit(0)
            }
        }
    }

    private fun startForegroundWithToolbar() {
        val prefs = applicationContext.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val userRole = prefs.getString("user_role", null)

        // Must have valid Bluetooth permission to start connectedDevice service
        if (!com.example.util.BlePermissionUtils.hasPermissions(applicationContext)) {
            Log.e(TAG, "Cannot start foreground service: BLUETOOTH_CONNECT permission is missing.")
            stopSelf()
            return
        }

        try {
            val (notification, fingerprint) = buildToolbarNotificationWithFingerprint()
            lastPostedFingerprint = fingerprint
            lastNotificationPostTime = android.os.SystemClock.elapsedRealtime()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                try {
                    startForeground(
                        NOTIFICATION_ID,
                        notification,
                        ServiceInfo.FOREGROUND_SERVICE_TYPE_CONNECTED_DEVICE
                    )
                } catch (e: Throwable) {
                    Log.e(TAG, "Failed startForeground with type connectedDevice: ${e.message}", e)
                    try {
                        startForeground(NOTIFICATION_ID, notification)
                    } catch (e2: Throwable) {
                        Log.e(TAG, "Failed fallback startForeground: ${e2.message}", e2)
                    }
                }
            } else {
                try {
                    startForeground(NOTIFICATION_ID, notification)
                } catch (e: Throwable) {
                    Log.e(TAG, "Failed startForeground legacy: ${e.message}", e)
                }
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            notificationManager?.notify(NOTIFICATION_ID, notification)
        } catch (e: Throwable) {
            Log.e(TAG, "Error in startForegroundWithToolbar: ${e.message}", e)
        }
    }

    fun setSessionActiveState(isActive: Boolean) {
        isSessionActiveOverride = isActive
        updateToolbarNotificationImmediate()
        updateWakeLock()
    }

    fun updateToolbarNotificationImmediate() {
        pendingNotificationJob?.cancel()
        pendingNotificationJob = null
        postNotificationNow()
    }

    fun updateToolbarNotification() {
        val now = android.os.SystemClock.elapsedRealtime()
        val timeSinceLast = now - lastNotificationPostTime
        if (timeSinceLast >= NOTIFICATION_MIN_INTERVAL_MS) {
            pendingNotificationJob?.cancel()
            pendingNotificationJob = null
            postNotificationNow()
        } else {
            if (pendingNotificationJob == null || pendingNotificationJob?.isActive != true) {
                val delayMs = NOTIFICATION_MIN_INTERVAL_MS - timeSinceLast
                pendingNotificationJob = serviceScope.launch {
                    kotlinx.coroutines.delay(delayMs)
                    postNotificationNow()
                }
            }
        }
    }

    private fun postNotificationNow() {
        val prefs = applicationContext.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val userRole = prefs.getString("user_role", null)
        if (userRole != "TEACHER") return

        try {
            val (notification, fingerprint) = buildToolbarNotificationWithFingerprint()
            // If the visible UI representation and actions did not change, do NOT re-post to NotificationManager.
            // This strictly respects the OS throttle mechanism and keeps notification queues empty.
            if (fingerprint == lastPostedFingerprint) {
                return
            }
            lastPostedFingerprint = fingerprint
            lastNotificationPostTime = android.os.SystemClock.elapsedRealtime()

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            notificationManager?.notify(NOTIFICATION_ID, notification)
        } catch (e: Throwable) {
            Log.e(TAG, "Error in postNotificationNow: ${e.message}", e)
        }
    }

    private fun isBluetoothHardwareEnabled(): Boolean {
        val btManager = getSystemService(Context.BLUETOOTH_SERVICE) as? BluetoothManager
        val adapter = btManager?.adapter
        return adapter?.isEnabled == true || bleManager.isBluetoothEnabled()
    }

    private fun buildToolbarNotification(): Notification {
        return buildToolbarNotificationWithFingerprint().first
    }

    private fun buildToolbarNotificationWithFingerprint(): Pair<Notification, String> {
        val classroom = currentClassroom
        val isBluetoothEnabled = isBluetoothHardwareEnabled()
        val connState = currentConnectionState
        val isConnected = connState is TeacherBleManager.ConnectionState.Connected
        val isScanning = connState is TeacherBleManager.ConnectionState.Scanning
        val isConnecting = connState is TeacherBleManager.ConnectionState.Connecting
        val isDisconnecting = isDisconnectingState || connState is TeacherBleManager.ConnectionState.Disconnecting
        val isAuth = currentIsAuthenticated
        val devState = currentDeviceState
        val scannedList = bleManager.scannedDevices.value
        val classroomName = classroom?.classroomName ?: "Calis Classroom"
        val targetMac = classroom?.deviceAddress

        val nearbyDevice = scannedList.find { dev ->
            val macMatches = !targetMac.isNullOrBlank() && dev.address.equals(targetMac, ignoreCase = true)
            val nameMatches = classroomName.isNotBlank() && (dev.name.equals(classroomName, ignoreCase = true) || dev.name.contains(classroomName, ignoreCase = true))
            val isCalisDeviceName = dev.name.contains("Calis", ignoreCase = true) ||
                    dev.name.contains("Classroom", ignoreCase = true) ||
                    dev.name.contains("Setup", ignoreCase = true) ||
                    dev.name.contains("CD", ignoreCase = true) ||
                    dev.name.contains("ESP32", ignoreCase = true)
            val serviceMatches = dev.hasServiceUuid
            macMatches || nameMatches || isCalisDeviceName || serviceMatches
        }

        val now = System.currentTimeMillis()
        val isRecentlyActive = nearbyDevice != null && (now - nearbyDevice.lastSeenTimestamp <= 15000L)

        val isSessionActive = (classroom?.isActiveSession == true) && (isConnected || isRecentlyActive)

        // Controls are ONLY enabled when connected, authenticated (AUTH_OK received), and valid session state
        val isControlsEnabled = isBluetoothEnabled &&
                isConnected &&
                isAuth &&
                (devState == DeviceState.SESSION_INACTIVE ||
                        devState == DeviceState.SESSION_ACTIVE_OPEN ||
                        devState == DeviceState.SESSION_ACTIVE_BUSY ||
                        devState == DeviceState.AUTHENTICATE)

        val isCalisDeviceReady = isRecentlyActive
        val isUserDisconnected = bleManager.isExplicitlyDisconnected

        val rawDeviceName = when {
            isConnected && connState is TeacherBleManager.ConnectionState.Connected -> connState.deviceName
            nearbyDevice != null -> nearbyDevice.name
            else -> ""
        }

        val isFactoryDefaultName = rawDeviceName.isBlank() ||
                rawDeviceName.equals("Setup Calis", ignoreCase = true) ||
                rawDeviceName.equals("Setup Calis (Virtual)", ignoreCase = true) ||
                rawDeviceName.equals("Calis Device", ignoreCase = true) ||
                rawDeviceName.equals("Calis Classroom", ignoreCase = true) ||
                rawDeviceName.equals("Calis Active Classroom", ignoreCase = true)

        val receiverDisplayName = when {
            classroom?.isBound == true && !classroom.classroomName.isNullOrBlank() -> classroom.classroomName
            !isFactoryDefaultName -> rawDeviceName
            !classroom?.classroomName.isNullOrBlank() -> classroom.classroomName
            else -> "Calis Device"
        }

        // Status text and availability indication
        val titleText = "CALIS TC • $receiverDisplayName"
        val availabilityText: String
        val sessionStatusText = if (isSessionActive) "Session Active" else "Session Inactive"

        if (!isBluetoothEnabled) {
            availabilityText = "Bluetooth Disabled"
        } else if (isConnected) {
            availabilityText = if (isAuth) "Connected" else "Authenticating..."
        } else if (isConnecting) {
            availabilityText = "Connecting..."
        } else if (isDisconnecting) {
            availabilityText = "Disconnecting..."
        } else if (isUserDisconnected && isCalisDeviceReady) {
            availabilityText = "Active"
        } else if (isCalisDeviceReady) {
            availabilityText = "Active (Advertising)"
        } else {
            availabilityText = "Scanning for Device..."
        }

        val subText = when {
            !isBluetoothEnabled -> "Bluetooth Off"
            isConnected -> if (isAuth) "Connected" else "Authenticating..."
            isConnecting -> "Connecting..."
            isDisconnecting -> "Disconnecting..."
            isCalisDeviceReady -> "Device Available"
            else -> "Scanning..."
        }

        // Pending Intents
        val openAppIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val openAppPendingIntent = PendingIntent.getActivity(
            this,
            0,
            openAppIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_UPDATE_CURRENT
        )

        val enableAndStartIntent = Intent(this, CalisBleService::class.java).apply { action = ACTION_ENABLE_AND_START_SESSION }
        val enableAndStartPendingIntent = PendingIntent.getService(
            this,
            10,
            enableAndStartIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_UPDATE_CURRENT
        )

        val startSessionIntent = Intent(this, CalisBleService::class.java).apply { action = ACTION_START_SESSION }
        val startSessionPendingIntent = PendingIntent.getService(
            this,
            1,
            startSessionIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_UPDATE_CURRENT
        )

        val endSessionIntent = Intent(this, CalisBleService::class.java).apply { action = ACTION_END_SESSION }
        val endSessionPendingIntent = PendingIntent.getService(
            this,
            2,
            endSessionIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_UPDATE_CURRENT
        )

        val connectIntent = Intent(this, CalisBleService::class.java).apply { action = ACTION_CONNECT }
        val connectPendingIntent = PendingIntent.getService(
            this,
            3,
            connectIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_UPDATE_CURRENT
        )

        val disconnectIntent = Intent(this, CalisBleService::class.java).apply { action = ACTION_DISCONNECT }
        val disconnectPendingIntent = PendingIntent.getService(
            this,
            4,
            disconnectIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_UPDATE_CURRENT
        )

        val stopServiceIntent = Intent(this, CalisBleService::class.java).apply { action = ACTION_STOP_SERVICE }
        val stopServicePendingIntent = PendingIntent.getService(
            this,
            5,
            stopServiceIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_UPDATE_CURRENT
        )

        val compactViews = android.widget.RemoteViews(packageName, R.layout.notification_calis_toolbar_compact)
        val expandedViews = android.widget.RemoteViews(packageName, R.layout.notification_calis_toolbar_expanded)

        // Title and Subtitle binding
        val displayTitle = "Calis Device"
        compactViews.setTextViewText(R.id.noti_title, displayTitle)
        compactViews.setTextViewText(R.id.noti_subtitle, "${receiverDisplayName ?: "None"} • $availabilityText")
        compactViews.setTextViewText(R.id.noti_status_badge, if (isSessionActive) "Active" else "Inactive")

        expandedViews.setTextViewText(R.id.noti_exp_title, displayTitle)
        expandedViews.setTextViewText(R.id.noti_exp_classroom, receiverDisplayName ?: "None")
        expandedViews.setTextViewText(R.id.noti_exp_details, availabilityText)
        expandedViews.setTextViewText(R.id.noti_exp_status_badge, if (isSessionActive) "Active" else "Inactive")

        // Calculate exact colors and states matching the CD controls section of the app
        // Session Indicator Styling (Top-right badge)
        val badgeColorInt = if (isSessionActive) {
            android.graphics.Color.parseColor("#10B981") // StatusPresentGreen
        } else {
            android.graphics.Color.parseColor("#64748B") // Gray
        }

        val badgeBgRes = if (isSessionActive) {
            R.drawable.bg_noti_badge_green
        } else {
            R.drawable.bg_noti_badge_gray
        }

        val wavesColorInt = if (isSessionActive && isConnected) {
            android.graphics.Color.parseColor("#0284C7") // Sky Blue (CalisPrimaryBlue)
        } else {
            android.graphics.Color.parseColor("#CBD5E1") // Muted Gray
        }

        val isDeviceActive = isBluetoothEnabled && (
            isConnected ||
            isConnecting ||
            (isUserDisconnected && isRecentlyActive) ||
            isCalisDeviceReady ||
            isRecentlyActive
        ) && !(isUserDisconnected && !isRecentlyActive)

        val espColorInt = if (isDeviceActive) {
            android.graphics.Color.parseColor("#0284C7") // Sky Blue (CalisPrimaryBlue)
        } else {
            android.graphics.Color.parseColor("#CBD5E1") // Muted Gray
        }

        val dottedLineColorInt = if (isConnected || isConnecting) {
            android.graphics.Color.parseColor("#0284C7") // Sky Blue (CalisPrimaryBlue)
        } else {
            android.graphics.Color.parseColor("#CBD5E1") // Muted Gray
        }

        // Apply dynamic badge backgrounds and content tints
        compactViews.setInt(R.id.noti_badge_container, "setBackgroundResource", badgeBgRes)
        expandedViews.setInt(R.id.noti_exp_badge_container, "setBackgroundResource", badgeBgRes)

        compactViews.setInt(R.id.noti_badge_dot, "setColorFilter", badgeColorInt)
        compactViews.setTextColor(R.id.noti_status_badge, badgeColorInt)
        compactViews.setInt(R.id.noti_waves_icon, "setColorFilter", wavesColorInt)
        compactViews.setInt(R.id.noti_esp32_icon, "setColorFilter", espColorInt)
        compactViews.setInt(R.id.noti_dotted_line, "setColorFilter", dottedLineColorInt)

        expandedViews.setInt(R.id.noti_exp_badge_dot, "setColorFilter", badgeColorInt)
        expandedViews.setTextColor(R.id.noti_exp_status_badge, badgeColorInt)
        expandedViews.setInt(R.id.noti_exp_waves_icon, "setColorFilter", wavesColorInt)
        expandedViews.setInt(R.id.noti_exp_esp32_icon, "setColorFilter", espColorInt)
        expandedViews.setInt(R.id.noti_exp_dotted_line, "setColorFilter", dottedLineColorInt)

        // Set up Disconnect vs Disconnecting... styling dynamically
        val disconnectButtonText = when {
            isDisconnecting -> "Disconnecting..."
            isConnecting -> "Connecting..."
            else -> "Disconnect"
        }
        val disconnectBgRes = if (isDisconnecting || isConnecting) R.drawable.bg_noti_btn_outlined_disabled else R.drawable.bg_noti_btn_outlined_blue
        val disconnectTextColor = if (isDisconnecting || isConnecting) android.graphics.Color.parseColor("#9E9E9E") else android.graphics.Color.parseColor("#0284C7")

        compactViews.setTextViewText(R.id.noti_btn_disconnect_text, disconnectButtonText)
        compactViews.setInt(R.id.noti_btn_disconnect, "setBackgroundResource", disconnectBgRes)
        compactViews.setTextColor(R.id.noti_btn_disconnect_text, disconnectTextColor)

        expandedViews.setTextViewText(R.id.noti_exp_btn_disconnect_text, disconnectButtonText)
        expandedViews.setInt(R.id.noti_exp_btn_disconnect, "setBackgroundResource", disconnectBgRes)
        expandedViews.setTextColor(R.id.noti_exp_btn_disconnect_text, disconnectTextColor)

        // Set up Connect button styling dynamically (grayed out when device is not ready/in range)
        val connectBgRes = if (isCalisDeviceReady) R.drawable.bg_noti_btn_filled_blue else R.drawable.bg_noti_btn_filled_disabled
        val connectTextColor = if (isCalisDeviceReady) android.graphics.Color.parseColor("#FFFFFF") else android.graphics.Color.parseColor("#9E9E9E")
        val connectPending = if (isCalisDeviceReady) connectPendingIntent else null
        val connectBtnText = if (isCalisDeviceReady) "Connect" else "Scanning..."

        compactViews.setTextViewText(R.id.noti_btn_connect_text, connectBtnText)
        compactViews.setInt(R.id.noti_btn_connect, "setBackgroundResource", connectBgRes)
        compactViews.setTextColor(R.id.noti_btn_connect_text, connectTextColor)

        expandedViews.setTextViewText(R.id.noti_exp_btn_connect_text, connectBtnText)
        expandedViews.setInt(R.id.noti_exp_btn_connect, "setBackgroundResource", connectBgRes)
        expandedViews.setTextColor(R.id.noti_exp_btn_connect_text, connectTextColor)

        // Set up Session button styling dynamically (grayed out when controls are disabled)
        val startSessionBg = if (isControlsEnabled) R.drawable.bg_noti_btn_filled_blue else R.drawable.bg_noti_btn_filled_disabled
        val startSessionTextColor = if (isControlsEnabled) android.graphics.Color.parseColor("#FFFFFF") else android.graphics.Color.parseColor("#9E9E9E")
        val startSessionIconColor = if (isControlsEnabled) android.graphics.Color.parseColor("#FFFFFF") else android.graphics.Color.parseColor("#9E9E9E")
        val startSessionPending = if (isControlsEnabled) startSessionPendingIntent else null

        val endSessionBg = if (isControlsEnabled) R.drawable.bg_noti_btn_outlined_blue else R.drawable.bg_noti_btn_outlined_disabled
        val endSessionTextColor = if (isControlsEnabled) android.graphics.Color.parseColor("#0284C7") else android.graphics.Color.parseColor("#9E9E9E")
        val endSessionIconColor = if (isControlsEnabled) android.graphics.Color.parseColor("#0284C7") else android.graphics.Color.parseColor("#9E9E9E")
        val endSessionPending = if (isControlsEnabled) endSessionPendingIntent else null

        expandedViews.setInt(R.id.noti_exp_btn_session_start, "setBackgroundResource", startSessionBg)
        expandedViews.setTextColor(R.id.noti_exp_btn_session_start_text, startSessionTextColor)
        expandedViews.setInt(R.id.noti_exp_btn_session_start_icon, "setColorFilter", startSessionIconColor)

        expandedViews.setInt(R.id.noti_exp_btn_session_end, "setBackgroundResource", endSessionBg)
        expandedViews.setTextColor(R.id.noti_exp_btn_session_end_text, endSessionTextColor)
        expandedViews.setInt(R.id.noti_exp_btn_session_end_icon, "setColorFilter", endSessionIconColor)

        // Set Click Listeners
        compactViews.setOnClickPendingIntent(R.id.noti_btn_connect, connectPending)
        compactViews.setOnClickPendingIntent(R.id.noti_btn_disconnect, if (isConnected && !isDisconnecting && !isConnecting) disconnectPendingIntent else null)
        compactViews.setOnClickPendingIntent(R.id.noti_btn_enable, enableAndStartPendingIntent)

        expandedViews.setOnClickPendingIntent(R.id.noti_exp_btn_connect, connectPending)
        expandedViews.setOnClickPendingIntent(R.id.noti_exp_btn_disconnect, if (isConnected && !isDisconnecting && !isConnecting) disconnectPendingIntent else null)
        expandedViews.setOnClickPendingIntent(R.id.noti_exp_btn_enable, enableAndStartPendingIntent)
        expandedViews.setOnClickPendingIntent(R.id.noti_exp_btn_session_start, startSessionPending)
        expandedViews.setOnClickPendingIntent(R.id.noti_exp_btn_session_end, endSessionPending)
        expandedViews.setOnClickPendingIntent(R.id.noti_exp_btn_terminate, stopServicePendingIntent)

        // Helpers for visibility
        fun setViewVisible(rv: android.widget.RemoteViews, viewId: Int, visible: Boolean) {
            rv.setViewVisibility(viewId, if (visible) android.view.View.VISIBLE else android.view.View.GONE)
        }

        val prefs = applicationContext.getSharedPreferences("calis_auth_prefs", android.content.Context.MODE_PRIVATE)
        val uRole = prefs.getString("user_role", null)

        // Hide status dot in the session badge to match in-app UI
        setViewVisible(compactViews, R.id.noti_badge_dot, false)
        setViewVisible(expandedViews, R.id.noti_exp_badge_dot, false)

        if (!isBluetoothEnabled) {
            setViewVisible(compactViews, R.id.noti_btn_connect, false)
            setViewVisible(compactViews, R.id.noti_btn_disconnect, false)
            setViewVisible(compactViews, R.id.noti_btn_enable, true)

            setViewVisible(expandedViews, R.id.noti_exp_btn_connect, false)
            setViewVisible(expandedViews, R.id.noti_exp_btn_disconnect, false)
            setViewVisible(expandedViews, R.id.noti_exp_btn_enable, true)
            setViewVisible(expandedViews, R.id.noti_exp_btn_session_start, false)
            setViewVisible(expandedViews, R.id.noti_exp_btn_session_end, false)
        } else {
            setViewVisible(compactViews, R.id.noti_btn_enable, false)
            setViewVisible(expandedViews, R.id.noti_exp_btn_enable, false)

            val isDisconnectVisible = isConnected || isConnecting || isDisconnecting
            val isConnectVisible = !isDisconnectVisible

            setViewVisible(compactViews, R.id.noti_btn_connect, isConnectVisible)
            setViewVisible(compactViews, R.id.noti_btn_disconnect, isDisconnectVisible)

            setViewVisible(expandedViews, R.id.noti_exp_btn_connect, isConnectVisible)
            setViewVisible(expandedViews, R.id.noti_exp_btn_disconnect, isDisconnectVisible)

            val showSessionControls = uRole == "TEACHER"
            val isStartSessionVisible = showSessionControls && !isSessionActive
            val isEndSessionVisible = showSessionControls && isSessionActive

            setViewVisible(expandedViews, R.id.noti_exp_btn_session_start, isStartSessionVisible)
            setViewVisible(expandedViews, R.id.noti_exp_btn_session_end, isEndSessionVisible)
        }

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification_logo)
            .setContentTitle(titleText)
            .setContentText("$availabilityText • $sessionStatusText")
            .setSubText(subText)
            .setCustomContentView(compactViews)
            .setCustomBigContentView(expandedViews)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setContentIntent(openAppPendingIntent)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        val fingerprint = StringBuilder().apply {
            append(isBluetoothEnabled).append('|')
            append(connState.javaClass.simpleName).append('|')
            append(isAuth).append('|')
            append(isSessionActive).append('|')
            append(isCalisDeviceReady).append('|')
            append(isControlsEnabled).append('|')
            append(receiverDisplayName).append('|')
            append(availabilityText).append('|')
            append(sessionStatusText).append('|')
            append(disconnectButtonText)
        }.toString()

        return Pair(builder.build(), fingerprint)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val existingChannel = manager.getNotificationChannel(CHANNEL_ID)
            if (existingChannel == null) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "CALIS TC Session Toolbar",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Quick access toolbar for Teacher Controller (TC) to manage classroom sessions and Calis device connection"
                    setShowBadge(false)
                    lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                    setSound(null, null)
                    enableVibration(false)
                }
                manager.createNotificationChannel(channel)
            }
        }
    }
}
