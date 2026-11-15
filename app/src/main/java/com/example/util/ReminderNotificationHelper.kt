package com.example.util

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import com.example.R
import com.example.data.db.CalisDatabase
import com.example.data.db.ClassroomEntity
import com.example.data.db.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import com.example.ble.StudentBleService
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object ReminderNotificationHelper {

    private const val TAG = "ReminderNotification"
    const val CHANNEL_ID = "calis_reminders_v3"
    private const val NOTIF_ID_SESSION_REMINDER = 5001
    private const val NOTIF_ID_TASK_DEADLINE = 5002
    private const val NOTIF_ID_ST_CHECKIN = 5003

    private val lastPostedFingerprints = mutableMapOf<Int, String>()

    private const val REQ_CODE_TC_ALARM = 8001
    private const val REQ_CODE_ST_ALARM = 8002

    const val EXTRA_CLASSROOM_ID = "classroom_id"
    const val EXTRA_CLASSROOM_NAME = "classroom_name"
    const val EXTRA_CLASSROOM_START_TIME = "classroom_start_time"
    const val EXTRA_REMINDER_MINUTES = "reminder_minutes"
    const val EXTRA_SCHEDULED_TRIGGER_MS = "scheduled_trigger_ms"

    private var periodicJob: Job? = null

    fun hasExactAlarmPermission(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
            // canScheduleExactAlarms() returns true if either SCHEDULE_EXACT_ALARM or USE_EXACT_ALARM is granted/present.
            // Starting from Android 14 (API 34), apps using USE_EXACT_ALARM do not need to ask for permission.
            return alarmManager?.canScheduleExactAlarms() ?: false
        }
        return true
    }

    private fun informUserExactAlarmUnavailable(context: Context) {
        try {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(
                    context,
                    "Exact alarm permission is unavailable. Reminders will use standard scheduling.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: Exception) {
            Log.w(TAG, "Could not show exact alarm permission toast", e)
        }
    }

    fun getSessionAlarmRequestCode(cid: String = ""): Int {
        if (cid.isBlank()) return REQ_CODE_TC_ALARM
        val hash = Math.abs(cid.hashCode()) % 10000
        return if (hash == 0) REQ_CODE_TC_ALARM else (REQ_CODE_TC_ALARM + hash)
    }

    fun getSessionNotificationId(cid: String = ""): Int {
        return NOTIF_ID_SESSION_REMINDER
    }

    fun getTaskAlarmRequestCode(tkid: String = ""): Int {
        if (tkid.isBlank()) return REQ_CODE_ST_ALARM
        val hash = Math.abs(tkid.hashCode()) % 10000
        return if (hash == 0) REQ_CODE_ST_ALARM else (REQ_CODE_ST_ALARM + hash)
    }

    fun getTaskNotificationId(tkid: String = ""): Int {
        if (tkid.startsWith("check_in") || tkid.startsWith("late") || tkid.startsWith("finding") || tkid.startsWith("checking")) {
            return NOTIF_ID_ST_CHECKIN
        }
        if (tkid.isBlank()) return NOTIF_ID_TASK_DEADLINE
        val hash = Math.abs(tkid.hashCode()) % 10000
        return if (hash == 0) NOTIF_ID_TASK_DEADLINE else (NOTIF_ID_TASK_DEADLINE + hash)
    }

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager ?: return
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Calis Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Reminders to start session, check-in, and upcoming task deadlines"
                enableVibration(true)
                vibrationPattern = longArrayOf(0, 500, 250, 500)
                setShowBadge(true)
                lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            }
            manager.createNotificationChannel(channel)
        }
    }

    fun parseTimeToMinutes(raw: String): Int {
        val trimmed = raw.trim().uppercase()
        if (trimmed.isEmpty() || trimmed == "N/A" || trimmed == "--:--") return -1

        val isPM = trimmed.contains("PM")
        val isAM = trimmed.contains("AM")
        val clean = trimmed.replace("AM", "").replace("PM", "").trim()

        val parts = clean.split(":")
        var hour = 0
        var min = 0
        if (parts.size >= 2) {
            hour = parts[0].trim().toIntOrNull() ?: 0
            min = parts[1].trim().toIntOrNull() ?: 0
        } else if (clean.length == 4 && clean.all { it.isDigit() }) {
            hour = clean.substring(0, 2).toIntOrNull() ?: 0
            min = clean.substring(2, 4).toIntOrNull() ?: 0
        } else if (clean.length == 3 && clean.all { it.isDigit() }) {
            hour = clean.substring(0, 1).toIntOrNull() ?: 0
            min = clean.substring(1, 3).toIntOrNull() ?: 0
        } else {
            hour = clean.toIntOrNull() ?: 0
        }

        if (isPM && hour < 12) hour += 12
        if (isAM && hour == 12) hour = 0

        return hour * 60 + min
    }

    fun calculateNextSessionReminderTime(
        classroom: ClassroomEntity,
        reminderMinutes: Int,
        currentTimeMillis: Long = System.currentTimeMillis()
    ): Long? {
        if (reminderMinutes <= 0) return null
        val startMins = parseTimeToMinutes(classroom.startTime)
        if (startMins < 0) return null
        val startHour = startMins / 60
        val startMinute = startMins % 60

        // Search through upcoming days starting today (offset 0) up to 14 days
        for (dayOffset in 0..14) {
            val classCal = Calendar.getInstance().apply {
                timeInMillis = currentTimeMillis
                add(Calendar.DAY_OF_YEAR, dayOffset)
            }

            if (!classroom.days.isBlank() && !ScheduleUtils.isDayInSchedule(classCal, classroom.days)) {
                continue
            }

            classCal.set(Calendar.HOUR_OF_DAY, startHour)
            classCal.set(Calendar.MINUTE, startMinute)
            classCal.set(Calendar.SECOND, 0)
            classCal.set(Calendar.MILLISECOND, 0)

            val classStartTimeMillis = classCal.timeInMillis
            val reminderTimeMillis = classStartTimeMillis - (reminderMinutes.toLong() * 60_000L)

            // Reminder time must be strictly in the future.
            // If the calculated reminder time has already passed,
            // do not immediately post a stale reminder; find next valid occurrence.
            if (reminderTimeMillis > currentTimeMillis) {
                return reminderTimeMillis
            }
        }
        return null
    }

    fun scheduleSessionAlarm(
        context: Context,
        classroom: ClassroomEntity,
        triggerAtMillis: Long,
        reminderMinutes: Int
    ) {
        if (triggerAtMillis <= 0L) return
        try {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
            val intent = Intent(context, ReminderAlarmReceiver::class.java).apply {
                action = ReminderAlarmReceiver.ACTION_TC_SESSION_REMINDER
                data = Uri.parse("calis://session_reminder/${classroom.cid}")
                putExtra(EXTRA_CLASSROOM_ID, classroom.cid)
                putExtra(EXTRA_CLASSROOM_NAME, classroom.classroomName)
                putExtra(EXTRA_CLASSROOM_START_TIME, classroom.startTime)
                putExtra(EXTRA_REMINDER_MINUTES, reminderMinutes)
                putExtra(EXTRA_SCHEDULED_TRIGGER_MS, triggerAtMillis)
            }
            val reqCode = getSessionAlarmRequestCode(classroom.cid)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                reqCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val showIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            val showPendingIntent = PendingIntent.getActivity(
                context,
                reqCode,
                showIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            try {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
            } catch (e: Exception) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
            }
            Log.d(TAG, "Scheduled session reminder exact alarm for ${classroom.classroomName} (${classroom.cid}) at ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date(triggerAtMillis))}")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to schedule session reminder alarm", e)
        }
    }

    fun cancelSessionAlarm(context: Context, classroom: ClassroomEntity) {
        try {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
            val intent = Intent(context, ReminderAlarmReceiver::class.java).apply {
                action = ReminderAlarmReceiver.ACTION_TC_SESSION_REMINDER
                data = Uri.parse("calis://session_reminder/${classroom.cid}")
            }
            val reqCode = getSessionAlarmRequestCode(classroom.cid)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                reqCode,
                intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
            if (pendingIntent != null) {
                alarmManager.cancel(pendingIntent)
                pendingIntent.cancel()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to cancel session alarm for ${classroom.cid}", e)
        }
    }

    private fun scheduleExactAlarm(context: Context, triggerAtMillis: Long, action: String, requestCode: Int) {
        if (triggerAtMillis <= System.currentTimeMillis()) return
        try {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
            val intent = Intent(context, ReminderAlarmReceiver::class.java).apply {
                this.action = action
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            val showIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            val showPendingIntent = PendingIntent.getActivity(
                context,
                requestCode,
                showIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            try {
                alarmManager.setAlarmClock(
                    AlarmManager.AlarmClockInfo(triggerAtMillis, showPendingIntent),
                    pendingIntent
                )
            } catch (e: Exception) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
            }
            Log.d(TAG, "Scheduled alarm clock for $action at ${SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US).format(Date(triggerAtMillis))}")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to schedule alarm for $action", e)
        }
    }

    private fun cancelAlarm(context: Context, action: String, requestCode: Int) {
        try {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
            val intent = Intent(context, ReminderAlarmReceiver::class.java).apply {
                this.action = action
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
            if (pendingIntent != null) {
                alarmManager.cancel(pendingIntent)
                pendingIntent.cancel()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to cancel alarm for $action", e)
        }
    }

    fun startPeriodicChecker(context: Context) {
        if (periodicJob?.isActive == true) return
        periodicJob = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                try {
                    val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                    val role = prefs.getString("user_role", null)
                    if (role == "TEACHER") {
                        checkAndPostSessionReminder(context)
                        checkAndPostTaskDeadlineReminder(context)
                    } else if (role == "STUDENT") {
                        checkAndPostTaskDeadlineReminder(context)
                        checkAndPostCheckInReminder(context)
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error in periodic reminder check", e)
                }
                delay(30_000L) // Check every 30 seconds
            }
        }
    }

    suspend fun rescheduleSessionRemindersSync(context: Context) {
        val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val role = prefs.getString("user_role", null)
        val db = CalisDatabase.getDatabase(context)
        val teacherProfile = db.teacherDao().getTeacherOnce()
        val isTeacher = role == "TEACHER" || (role.isNullOrBlank() && teacherProfile != null)
        if (!isTeacher) {
            cancelSessionReminder(context)
            return
        }

        val isEnabled = prefs.getBoolean("remind_start_session", true)
        if (!isEnabled) {
            cancelSessionReminder(context)
            return
        }

        val reminderMinutes = prefs.getInt("remind_start_session_minutes", 15)
        if (reminderMinutes <= 0) return

        val allClassrooms = try {
            db.classroomDao().getAllClassroomsSync()
        } catch (e: Exception) {
            emptyList()
        }
        val classrooms = if (allClassrooms.isNotEmpty()) {
            allClassrooms
        } else {
            listOfNotNull(
                db.classroomDao().getActiveBoundClassroomSync()
                    ?: db.classroomDao().getAnyClassroomSync()
            )
        }

        if (classrooms.isEmpty()) return

        // Cancel legacy PendingIntent so it does not accumulate
        cancelAlarm(context, ReminderAlarmReceiver.ACTION_TC_SESSION_REMINDER, REQ_CODE_TC_ALARM)

        val now = System.currentTimeMillis()
        val todayCal = Calendar.getInstance().apply { timeInMillis = now }
        val todayDateStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date(now))

        for (classroom in classrooms) {
            // Cancel old alarm for this classroom so duplicate alarms do not accumulate
            cancelSessionAlarm(context, classroom)

            val startMins = parseTimeToMinutes(classroom.startTime)
            if (startMins < 0) {
                Log.w(TAG, "TC Reminder: Skipping classroom ${classroom.cid} due to invalid start time: ${classroom.startTime}")
                continue
            }

            val isTodayInSched = ScheduleUtils.isDayInSchedule(todayCal, classroom.days)

            val todayClassStartCal = Calendar.getInstance().apply {
                timeInMillis = now
                set(Calendar.HOUR_OF_DAY, startMins / 60)
                set(Calendar.MINUTE, startMins % 60)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            val todayClassStartMs = todayClassStartCal.timeInMillis
            val todayReminderMs = todayClassStartMs - (reminderMinutes.toLong() * 60_000L)
            var handledToday = false

            Log.d(TAG, "TC Reminder Eval: cid=${classroom.cid}, now=${now}, reminderMs=${todayReminderMs}, startMs=${todayClassStartMs}, isTodayInSched=$isTodayInSched")

            // Case A: Reminder time is strictly in the future today AND it's a class day
            // We use a small 5-second window to avoid missing the alarm due to tiny millisecond offsets
            if (isTodayInSched && todayReminderMs > (now + 2000L)) {
                scheduleSessionAlarm(context, classroom, todayReminderMs, reminderMinutes)
                handledToday = true
                Log.d(TAG, "TC Reminder (Case A): Scheduled for today at ${SimpleDateFormat("HH:mm:ss", Locale.US).format(Date(todayReminderMs))}")
            }
            // Case B: Current time is inside the pre-session reminder window today AND it's a class day
            // This triggers immediately if the user opens the app or changes time into the window
            else if (isTodayInSched && now >= todayReminderMs && now < todayClassStartMs && !classroom.isActiveSession) {
                val posted = postSessionReminderNotification(context, classroom, reminderMinutes)
                handledToday = true
                Log.d(TAG, "TC Reminder (Case B): Posting immediate notification (inside window). posted=$posted")

                // Even if we posted immediately, we must schedule the NEXT occurrence (e.g. tomorrow)
                val nextOccurrenceTrigger = calculateNextSessionReminderTime(
                    classroom = classroom,
                    reminderMinutes = reminderMinutes,
                    currentTimeMillis = todayClassStartMs + 1000L // Ensure we skip today
                )
                if (nextOccurrenceTrigger != null) {
                    scheduleSessionAlarm(context, classroom, nextOccurrenceTrigger, reminderMinutes)
                }
            }

            // Case C: Today's class start time has already passed, or today is not a class day
            // Find next valid scheduled occurrence
            if (!handledToday) {
                val nextTrigger = calculateNextSessionReminderTime(classroom, reminderMinutes, now)
                if (nextTrigger != null) {
                    Log.d(TAG, "TC Reminder (Case C): Scheduled for next occurrence: ${SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US).format(Date(nextTrigger))}")
                    scheduleSessionAlarm(context, classroom, nextTrigger, reminderMinutes)
                } else {
                    Log.w(TAG, "TC Reminder (Case C): No future trigger found for classroom ${classroom.cid}")
                }
            }
        }
    }

    fun checkAndPostSessionReminder(context: Context) {
        val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val isEnabled = prefs.getBoolean("remind_start_session", true)
        if (!isEnabled) {
            cancelSessionReminder(context)
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val role = prefs.getString("user_role", null)
                val db = CalisDatabase.getDatabase(context)
                val teacherProfile = db.teacherDao().getTeacherOnce()
                val isTeacher = role == "TEACHER" || (role.isNullOrBlank() && teacherProfile != null)
                if (!isTeacher) {
                    cancelSessionReminder(context)
                    return@launch
                }
                rescheduleSessionRemindersSync(context)
            } catch (e: Exception) {
                Log.e(TAG, "Error in checkAndPostSessionReminder", e)
            }
        }
    }

    fun clearSessionReminderKeys(context: Context) {
        try {
            val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            for (key in prefs.all.keys) {
                if (key.startsWith("last_session_reminded_key_") || key == "last_session_reminded_date" || key.startsWith("session_reminded_")) {
                    editor.remove(key)
                }
            }
            editor.apply()
        } catch (e: Exception) {
            Log.e(TAG, "Error clearing session reminder keys", e)
        }
    }

    fun clearTaskReminderKeys(context: Context) {
        try {
            val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            for (key in prefs.all.keys) {
                if (key.startsWith("task_reminded_") || key == "task_deadline_dismissed_date") {
                    editor.remove(key)
                }
            }
            editor.apply()
            Log.d(TAG, "Cleared task reminder keys")
        } catch (e: Exception) {
            Log.e(TAG, "Error clearing task reminder keys", e)
        }
    }

    fun clearCheckInReminderKeys(context: Context) {
        try {
            val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            for (key in prefs.all.keys) {
                if (key.startsWith("check_in_reminded_") || key.startsWith("late_warn_reminded_")) {
                    editor.remove(key)
                }
            }
            editor.apply()
            Log.d(TAG, "Cleared check-in reminder keys")
        } catch (e: Exception) {
            Log.e(TAG, "Error clearing check-in reminder keys", e)
        }
    }

    suspend fun rescheduleCheckInRemindersSync(context: Context) {
        try {
            val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
            val role = prefs.getString("user_role", null)
            val db = CalisDatabase.getDatabase(context)
            val studentProfile = db.studentProfileDao().getStudentProfileOnce()
            val isStudent = role == "STUDENT" || (role.isNullOrBlank() && studentProfile != null)
            if (!isStudent) {
                cancelCheckInReminder(context)
                return
            }

            val isEnabled = prefs.getBoolean("remind_check_in", true)
            if (!isEnabled) {
                cancelCheckInReminder(context)
                return
            }

            val reminderMinutes = prefs.getInt("remind_check_in_minutes", 15)
            if (reminderMinutes <= 0) return

            val classroom = db.classroomDao().getActiveBoundClassroomSync()
                ?: db.classroomDao().getAnyClassroomSync()
                ?: return

            val now = System.currentTimeMillis()
            val startMins = parseTimeToMinutes(classroom.startTime)
            if (startMins < 0) return

            val todayClassStartCal = Calendar.getInstance().apply {
                timeInMillis = now
                set(Calendar.HOUR_OF_DAY, startMins / 60)
                set(Calendar.MINUTE, startMins % 60)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            val todayClassStartMs = todayClassStartCal.timeInMillis
            val todayReminderMs = todayClassStartMs - (reminderMinutes.toLong() * 60_000L)

            // Evaluate and post check-in reminder right now if inside reminder window
            checkAndPostCheckInReminder(context)

            // Schedule exact AlarmManager alarm
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
            val intent = Intent(context, ReminderAlarmReceiver::class.java).apply {
                action = ReminderAlarmReceiver.ACTION_ST_CHECKIN_REMINDER
                putExtra("EXTRA_CLASSROOM_ID", classroom.cid)
            }
            val reqCode = getTaskNotificationId("check_in_alarm_${classroom.cid}")
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                reqCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val triggerMs = if (todayReminderMs > now) todayReminderMs else calculateNextCheckInReminderTime(classroom, reminderMinutes, now)
            if (triggerMs != null && triggerMs > now) {
                val showIntent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                val showPendingIntent = PendingIntent.getActivity(
                    context,
                    reqCode,
                    showIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                try {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerMs, pendingIntent)
                } catch (e: Exception) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, triggerMs, pendingIntent)
                }
                Log.d(TAG, "Scheduled AlarmManager exact alarm for ST check-in reminder at $triggerMs")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in rescheduleCheckInRemindersSync", e)
        }
    }

    fun calculateNextCheckInReminderTime(classroom: ClassroomEntity, reminderMinutes: Int, currentTimeMillis: Long): Long? {
        val startMins = parseTimeToMinutes(classroom.startTime)
        if (startMins < 0) return null

        val cal = Calendar.getInstance().apply { timeInMillis = currentTimeMillis }
        for (dayOffset in 0..7) {
            val testCal = (cal.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, dayOffset) }
            if (ScheduleUtils.isDayInSchedule(testCal, classroom.days)) {
                testCal.set(Calendar.HOUR_OF_DAY, startMins / 60)
                testCal.set(Calendar.MINUTE, startMins % 60)
                testCal.set(Calendar.SECOND, 0)
                testCal.set(Calendar.MILLISECOND, 0)
                val reminderMs = testCal.timeInMillis - (reminderMinutes.toLong() * 60_000L)
                if (reminderMs > currentTimeMillis) {
                    return reminderMs
                }
            }
        }
        return null
    }

    fun cancelCheckInReminder(context: Context) {
        try {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
            val intent = Intent(context, ReminderAlarmReceiver::class.java).apply {
                action = ReminderAlarmReceiver.ACTION_ST_CHECKIN_REMINDER
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                REQ_CODE_ST_ALARM,
                intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
            if (pendingIntent != null) {
                alarmManager.cancel(pendingIntent)
                pendingIntent.cancel()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to cancel check-in reminder alarm", e)
        }
    }

    suspend fun handleSessionReminderAlarmTrigger(context: Context, intent: Intent?) {
        val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val isEnabled = prefs.getBoolean("remind_start_session", true)
        if (!isEnabled) {
            cancelSessionReminder(context)
            return
        }

        val targetCid = intent?.getStringExtra(EXTRA_CLASSROOM_ID)
            ?: intent?.data?.lastPathSegment
            ?: ""
        val classroomNameExtra = intent?.getStringExtra(EXTRA_CLASSROOM_NAME) ?: ""
        val classroomStartTimeExtra = intent?.getStringExtra(EXTRA_CLASSROOM_START_TIME) ?: ""
        val reminderMinutesExtra = intent?.getIntExtra(EXTRA_REMINDER_MINUTES, -1) ?: -1
        val reminderMinutes = if (reminderMinutesExtra > 0) {
            reminderMinutesExtra
        } else {
            prefs.getInt("remind_start_session_minutes", 15)
        }

        try {
            val db = CalisDatabase.getDatabase(context)
            val classroom = (if (targetCid.isNotBlank()) {
                db.classroomDao().getClassroomById(targetCid)
            } else null)
                ?: db.classroomDao().getActiveBoundClassroomSync()
                ?: db.classroomDao().getAnyClassroomSync()
                ?: if (targetCid.isNotBlank() && classroomNameExtra.isNotBlank() && classroomStartTimeExtra.isNotBlank()) {
                    ClassroomEntity(
                        cid = targetCid,
                        teacherId = "",
                        classroomName = classroomNameExtra,
                        teacherName = "",
                        days = "",
                        startTime = classroomStartTimeExtra,
                        graceTime = "",
                        endTime = ""
                    )
                } else null

            val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
            val lastStartedDate = if (classroom != null) prefs.getString("last_session_started_at_date_${classroom.cid}", "") else ""
            val isSessionStartedToday = lastStartedDate == todayStr
            val isReallyActiveToday = classroom?.isActiveSession == true && isSessionStartedToday

            if (classroom != null && !isReallyActiveToday) {
                val now = System.currentTimeMillis()
                val startMins = parseTimeToMinutes(classroom.startTime)
                val isWithinWindow = if (startMins >= 0) {
                    val todayCal = Calendar.getInstance().apply { timeInMillis = now }
                    val isTodayClassDay = ScheduleUtils.isDayInSchedule(todayCal, classroom.days)
                    val classStartMs = Calendar.getInstance().apply {
                        timeInMillis = now
                        set(Calendar.HOUR_OF_DAY, startMins / 60)
                        set(Calendar.MINUTE, startMins % 60)
                        set(Calendar.SECOND, 0)
                        set(Calendar.MILLISECOND, 0)
                    }.timeInMillis
                    val reminderMs = classStartMs - (reminderMinutes.toLong() * 60_000L)

                    isTodayClassDay && (now >= reminderMs - 60_000L) && (now < classStartMs)
                } else false

                if (isWithinWindow) {
                    val posted = postSessionReminderNotification(context, classroom, reminderMinutes)
                    if (posted) {
                        val currentKey = "${todayStr}_${startMins}_$reminderMinutes"
                        prefs.edit().putString("last_session_reminded_key_${classroom.cid}", currentKey).apply()
                    }
                }

                // Reschedule for next upcoming occurrence after this one
                val nowMs = System.currentTimeMillis()
                val nextTrigger = calculateNextSessionReminderTime(classroom, reminderMinutes, nowMs + 60_000L)
                if (nextTrigger != null) {
                    scheduleSessionAlarm(context, classroom, nextTrigger, reminderMinutes)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in handleSessionReminderAlarmTrigger", e)
        }
    }

    fun postSessionReminderNotification(
        context: Context,
        classroom: ClassroomEntity,
        reminderMinutes: Int
    ): Boolean {
        if (!BlePermissionUtils.hasNotificationPermission(context)) {
            Log.w(TAG, "Cannot post session reminder notification: POST_NOTIFICATIONS permission not granted")
            return false
        }

        val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        val startMins = parseTimeToMinutes(classroom.startTime)
        
        // Safety check: Don't post if the class has already started (now > start time)
        val now = System.currentTimeMillis()
        val classStartCal = Calendar.getInstance().apply {
            timeInMillis = now
            set(Calendar.HOUR_OF_DAY, startMins / 60)
            set(Calendar.MINUTE, startMins % 60)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        if (now >= classStartCal.timeInMillis) {
            Log.d(TAG, "postSessionReminderNotification: Skipping because class already started (now=${now}, start=${classStartCal.timeInMillis})")
            return false
        }
        
        // Don't post if session is already active
        if (classroom.isActiveSession) {
            Log.d(TAG, "postSessionReminderNotification: Skipping because session is already active")
            return false
        }

        val reminderKey = "session_reminded_${classroom.cid}_${todayStr}_${startMins}"
        val alreadyReminded = prefs.getBoolean(reminderKey, false)
        if (alreadyReminded) {
            return false // Trigger once per session/day
        }

        prefs.edit()
            .putBoolean(reminderKey, true)
            .putString("last_session_reminded_key_${classroom.cid}", "${todayStr}_${startMins}_$reminderMinutes")
            .apply()

        createNotificationChannel(context)
        val title = "Start Session Reminder"
        val body = if (classroom.classroomName.isNotBlank() && classroom.startTime.isNotBlank()) {
            "Class \"${classroom.classroomName}\" scheduled at ${classroom.startTime}. Starts in $reminderMinutes min. Tap to open app and start session."
        } else {
            "Classroom session starts in $reminderMinutes minutes. Tap to open app and start session."
        }

        val notifId = getSessionNotificationId(classroom.cid)
        val fingerprint = "$title|$body|$notifId"
        if (lastPostedFingerprints[notifId] == fingerprint) return false
        lastPostedFingerprints[notifId] = fingerprint

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            notifId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_notify_chat)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_SOUND or NotificationCompat.DEFAULT_VIBRATE)
            .setVibrate(longArrayOf(0, 500, 250, 500))
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(pendingIntent)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager ?: return false
        manager.notify(notifId, notification)

        prefs.edit().putBoolean(reminderKey, true).apply()
        Log.i(TAG, "TC Session Reminder notification posted once for cid=${classroom.cid}: $body")
        return true
    }

    fun cancelSessionReminder(context: Context) {
        try {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            manager?.cancel(NOTIF_ID_SESSION_REMINDER)

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return

            // Cancel legacy/base PendingIntent
            val legacyIntent = Intent(context, ReminderAlarmReceiver::class.java).apply {
                action = ReminderAlarmReceiver.ACTION_TC_SESSION_REMINDER
            }
            val legacyPending = PendingIntent.getBroadcast(
                context,
                REQ_CODE_TC_ALARM,
                legacyIntent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
            if (legacyPending != null) {
                alarmManager.cancel(legacyPending)
                legacyPending.cancel()
            }

            // Also cancel per-classroom alarms
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val db = CalisDatabase.getDatabase(context)
                    val classrooms = db.classroomDao().getAllClassroomsSync()
                    for (c in classrooms) {
                        cancelSessionAlarm(context, c)
                        manager?.cancel(getSessionNotificationId(c.cid))
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to cancel all classroom alarms", e)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to cancel session reminder", e)
        }
    }

    fun checkAndPostTaskDeadlineReminder(context: Context) {
        val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val isEnabled = prefs.getBoolean("remind_task_deadline", true)
        if (!isEnabled) {
            cancelTaskDeadlineReminder(context)
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val db = CalisDatabase.getDatabase(context)
                val studentProfile = db.studentProfileDao().getStudentProfileOnce()
                val studentId = studentProfile?.sid ?: ""

                val role = prefs.getString("user_role", null)
                val isStudent = role == "STUDENT" || (role.isNullOrBlank() && studentProfile != null)
                if (!isStudent) {
                    cancelTaskDeadlineReminder(context)
                    return@launch
                }

                val allTasks = db.taskDao().getAllTasksSync()
                val now = System.currentTimeMillis()
                val days = prefs.getInt("remind_task_deadline_days", 1).coerceAtLeast(0)
                val hour = prefs.getInt("remind_task_deadline_time_hour", 8)
                val minute = prefs.getInt("remind_task_deadline_time_minute", 0)

                // Helper to normalize timestamp: if stored as epoch seconds (< 100 billion), convert to millis
                fun toMillis(timestamp: Long): Long =
                    if (timestamp > 100_000_000_000L) timestamp else timestamp * 1000L

                val upcomingTasks = allTasks.filter { toMillis(it.deadline) > now }
                    .sortedBy { toMillis(it.deadline) }

                // Only consider tasks that have NOT been submitted by this student (pending tasks)
                val pendingTasks = mutableListOf<TaskEntity>()
                for (task in upcomingTasks) {
                    val submission = if (studentId.isNotBlank()) db.taskSubmissionDao().getSubmission(task.tkid, studentId) else null
                    if (submission == null) {
                        pendingTasks.add(task)
                    }
                }

                if (pendingTasks.isEmpty()) {
                    // No pending tasks! Cancel any existing deadline notification
                    cancelTaskDeadlineReminder(context)
                    return@launch
                }

                val dueSoonTasks = mutableListOf<Pair<TaskEntity, Long>>()
                val futureTasks = mutableListOf<Pair<TaskEntity, Long>>()

                for (task in pendingTasks) {
                    val deadlineMs = toMillis(task.deadline)
                    val cal = Calendar.getInstance().apply {
                        timeInMillis = deadlineMs
                        add(Calendar.DAY_OF_YEAR, -days)
                        set(Calendar.HOUR_OF_DAY, hour)
                        set(Calendar.MINUTE, minute)
                        set(Calendar.SECOND, 0)
                        set(Calendar.MILLISECOND, 0)
                    }
                    val triggerTime = if (cal.timeInMillis >= deadlineMs) {
                        deadlineMs - (15L * 60L * 1000L)
                    } else {
                        cal.timeInMillis
                    }

                    if (now >= triggerTime && now < deadlineMs) {
                        dueSoonTasks.add(task to triggerTime)
                    } else if (triggerTime > now) {
                        futureTasks.add(task to triggerTime)
                    }
                }

                val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date(now))
                val notifManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

                for (pair in dueSoonTasks) {
                    val task = pair.first
                    val taskReminderKey = "task_reminded_${task.tkid}_$todayStr"
                    val alreadyRemindedForTask = prefs.getBoolean(taskReminderKey, false)
                    if (alreadyRemindedForTask) {
                        continue // Trigger once each for task that isn't submitted yet
                    }

                    if (BlePermissionUtils.hasNotificationPermission(context)) {
                        createNotificationChannel(context)
                        val deadlineMs = toMillis(task.deadline)
                        val diff = (deadlineMs - now).coerceAtLeast(0L)
                        val daysLeft = (diff / (24L * 60L * 60L * 1000L)).toInt()
                        val hoursLeft = ((diff % (24L * 60L * 60L * 1000L)) / (60L * 60L * 1000L)).toInt()
                        val minsLeft = ((diff % (60L * 1000L)) / (60L * 1000L)).toInt()
                        val timeLeftStr = when {
                            daysLeft > 0 -> "$daysLeft day(s) left"
                            hoursLeft > 0 -> "$hoursLeft hour(s) left"
                            else -> "$minsLeft min left"
                        }
                        val sdf = SimpleDateFormat("MMM d, h:mm a", Locale.US)
                        val dueFormatted = sdf.format(Date(deadlineMs))

                        val title = "Task Deadline: ${task.title}"
                        val summary = "Due in $timeLeftStr ($dueFormatted). Tap to open app."
                        val bigText = "Task \"${task.title}\" is due in $timeLeftStr ($dueFormatted).\nOpen Calis Mobile to review and submit before deadline."

                        val intent = Intent(context, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        }
                        val notifId = getTaskNotificationId(task.tkid)
                        val pendingIntent = PendingIntent.getActivity(
                            context,
                            notifId,
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                        )

                        val deleteIntent = Intent(context, TaskReminderDismissReceiver::class.java).apply {
                            action = TaskReminderDismissReceiver.ACTION_DISMISS_TASK_REMINDER
                            putExtra("task_id", task.tkid)
                        }
                        val deletePendingIntent = PendingIntent.getBroadcast(
                            context,
                            notifId,
                            deleteIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                        )

                        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                            .setSmallIcon(android.R.drawable.stat_notify_chat)
                            .setContentTitle(title)
                            .setContentText(summary)
                            .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setDefaults(NotificationCompat.DEFAULT_SOUND or NotificationCompat.DEFAULT_VIBRATE)
                            .setVibrate(longArrayOf(0, 500, 250, 500))
                            .setAutoCancel(true)
                            .setOngoing(false)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                            .setContentIntent(pendingIntent)
                            .setDeleteIntent(deletePendingIntent)
                            .build()

                        notifManager?.notify(notifId, notification)
                        prefs.edit().putBoolean(taskReminderKey, true).apply()
                        Log.d(TAG, "ST Pending Task Deadline Reminder posted once for task ${task.tkid}: $summary")
                    }
                }

                // If future pending tasks exist that haven't entered the window yet, schedule alarm for when earliest pending task enters window
                if (futureTasks.isNotEmpty()) {
                    val earliestFutureTrigger = futureTasks.minOf { it.second }
                    scheduleExactAlarm(context, earliestFutureTrigger, ReminderAlarmReceiver.ACTION_ST_TASK_REMINDER, REQ_CODE_ST_ALARM)
                }

            } catch (e: Exception) {
                Log.e(TAG, "Error in checkAndPostTaskDeadlineReminder", e)
            }
        }
    }

    fun cancelTaskNotification(context: Context, tkid: String) {
        try {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            manager?.cancel(getTaskNotificationId(tkid))
        } catch (e: Exception) {
            Log.e(TAG, "Failed to cancel notification for task $tkid", e)
        }
    }

    fun cancelTaskDeadlineReminder(context: Context) {
        try {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            manager?.cancel(NOTIF_ID_TASK_DEADLINE)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val db = CalisDatabase.getDatabase(context)
                    val tasks = db.taskDao().getAllTasksSync()
                    for (t in tasks) {
                        manager?.cancel(getTaskNotificationId(t.tkid))
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to cancel individual task notifications", e)
                }
            }
            cancelAlarm(context, ReminderAlarmReceiver.ACTION_ST_TASK_REMINDER, REQ_CODE_ST_ALARM)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to cancel task deadline reminder", e)
        }
    }

    fun checkAndPostCheckInReminder(context: Context) {
        val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val role = prefs.getString("user_role", null)

        val isEnabled = prefs.getBoolean("remind_check_in", true)
        if (!isEnabled) return

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val db = CalisDatabase.getDatabase(context)
                val studentProfile = db.studentProfileDao().getStudentProfileOnce()
                val isStudent = role == "STUDENT" || (role.isNullOrBlank() && studentProfile != null)
                if (!isStudent) return@launch

                val studentId = studentProfile?.sid ?: ""
                val classrooms = db.classroomDao().getAllClassroomsSync().ifEmpty {
                    listOfNotNull(
                        db.classroomDao().getActiveBoundClassroomSync()
                            ?: db.classroomDao().getAnyClassroomSync()
                    )
                }
                if (classrooms.isEmpty()) return@launch

                val now = System.currentTimeMillis()
                val todayCal = Calendar.getInstance().apply { timeInMillis = now }
                    val todayDateStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date(now))
                    val todayMdy = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(Date(now))
                    val notifManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager ?: return@launch

                    for (classroom in classrooms) {
                        // Check if student joined/registered for this classroom today.
                        // Notifications for this classroom start starting the next day after registration.
                        val approvedStudent = try {
                            db.approvedStudentDao().getApprovedStudent(classroom.cid, studentId)
                                ?: db.approvedStudentDao().getApprovedStudentGlobally(studentId)
                        } catch (_: Exception) { null }

                        val registeredAtMs = approvedStudent?.approvedAt ?: prefs.getLong("classroom_joined_timestamp_${classroom.cid}", 0L)
                        if (registeredAtMs > 0) {
                            val registeredDateStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date(registeredAtMs))
                            if (registeredDateStr == todayDateStr) {
                                Log.d(TAG, "Classroom ${classroom.cid} was joined/registered today ($todayDateStr). Check-in notifications start tomorrow.")
                                continue
                            }
                        }

                        if (!classroom.days.isBlank() && !ScheduleUtils.isDayInSchedule(todayCal, classroom.days)) {
                            continue
                        }

                        val startMins = parseTimeToMinutes(classroom.startTime)
                        val parsedGrace = parseTimeToMinutes(classroom.graceTime)
                        val graceMins = when {
                            parsedGrace < 0 -> if (startMins >= 0) startMins + 15 else 15
                            startMins >= 0 && parsedGrace <= startMins -> startMins + parsedGrace
                            else -> parsedGrace
                        }

                        // Check if already checked in today (via persistent preference or database records)
                        val isAlreadyCheckedInPref = prefs.getBoolean("checked_in_today_${classroom.cid}_$todayDateStr", false) ||
                                prefs.getBoolean("checked_in_today_global_$todayDateStr", false)

                        val attendanceList = try {
                            db.attendanceDao().getAttendanceForClassroomSync(classroom.cid)
                        } catch (e: Exception) {
                            emptyList()
                        }

                        val todayRecord = if (isAlreadyCheckedInPref) {
                            attendanceList.firstOrNull() ?: com.example.data.db.AttendanceEntity(
                                classroomId = classroom.cid,
                                studentId = studentId,
                                studentName = studentProfile?.fullName ?: "",
                                date = todayMdy, // Use MDY for record storage
                                status = "PRESENT",
                                timestamp = now / 1000L
                            )
                        } else {
                            attendanceList.find { log ->
                                log.date.startsWith(todayMdy) &&
                                (studentId.isBlank() || log.studentId == studentId)
                            }
                        }

                    if (todayRecord != null) {
                        val recordMillis = if (todayRecord.timestamp > 1_000_000_000_000L) todayRecord.timestamp else todayRecord.timestamp * 1000L
                        val recordCal = Calendar.getInstance().apply { timeInMillis = recordMillis }
                        val checkInMinutes = recordCal.get(Calendar.HOUR_OF_DAY) * 60 + recordCal.get(Calendar.MINUTE)
                        val isCheckedInBeforeGrace = todayRecord.status.equals("PRESENT", ignoreCase = true) ||
                                (graceMins >= 0 && checkInMinutes <= graceMins)

                        // Clear finding_calis flag and stop fast scan loop when already checked in
                        prefs.edit()
                            .putBoolean("checked_in_today_${classroom.cid}_$todayDateStr", true)
                            .putBoolean("finding_calis_${classroom.cid}", false)
                            .apply()
                        stopFindingCalisScanLoop(context)

                        if (isCheckedInBeforeGrace) {
                            // Suppress late warning notification if checked in before grace period
                            notifManager.cancel(getTaskNotificationId("late_${classroom.cid}"))
                            prefs.edit()
                                .putBoolean("check_in_reminded_${classroom.cid}_$todayDateStr", true)
                                .putBoolean("late_warn_reminded_${classroom.cid}_$todayDateStr", true)
                                .apply()
                            Log.d(TAG, "Student already checked in ($checkInMinutes <= $graceMins). Check-in notification suppressed.")
                            continue
                        } else {
                            prefs.edit().putBoolean("check_in_reminded_${classroom.cid}_$todayDateStr", true).apply()
                            continue
                        }
                    }

                    if (startMins < 0) continue

                    val currentMinutes = todayCal.get(Calendar.HOUR_OF_DAY) * 60 + todayCal.get(Calendar.MINUTE)
                    val remindMinutes = prefs.getInt("remind_check_in_minutes", 15)

                    // Check-in reminder: triggers when ST user HAS NOT checked-in yet and current time is inside reminder window
                    val remindStartMins = (startMins - remindMinutes).coerceAtLeast(0)
                    val isFindingCalis = prefs.getBoolean("finding_calis_${classroom.cid}", false)
                    val hasPermissions = BlePermissionUtils.hasNotificationPermission(context) && BlePermissionUtils.hasPermissions(context)
                    val isBtEnabled = BlePermissionUtils.isBluetoothEnabled(context)
                    val isLocEnabled = BlePermissionUtils.isLocationEnabled(context)
                    val isOlderThanAndroid12 = Build.VERSION.SDK_INT < Build.VERSION_CODES.S
                    val allRequirementsMet = hasPermissions && isBtEnabled && (!isOlderThanAndroid12 || isLocEnabled)

                    val remindKey = "check_in_reminded_${classroom.cid}_$todayDateStr"

                    var effectiveFindingCalis = isFindingCalis
                    if (isFindingCalis && !allRequirementsMet) {
                        // Connection requirement got disabled while finding Calis device!
                        // Transition BACK to Check-In Reminder.
                        stopFindingCalisScanLoop(context)
                        prefs.edit()
                            .putBoolean("finding_calis_${classroom.cid}", false)
                            .putBoolean(remindKey, false)
                            .apply()
                        effectiveFindingCalis = false
                        Log.d(TAG, "Connection requirement disabled while finding Calis device for classroom ${classroom.cid}. Transitioning back to Check-In Reminder.")
                    }

                    if (currentMinutes >= remindStartMins) {
                        if (allRequirementsMet) {
                            if (!effectiveFindingCalis) {
                                // Transition to Finding Calis Device automatically in real-time in the background!
                                updateNotificationToFindingCalisDevice(context, classroom.cid)
                                effectiveFindingCalis = true
                                Log.d(TAG, "All requirements met. Automatically transitioned to Finding Calis Device for classroom ${classroom.cid}")
                            } else {
                                StudentBleService.startService(context.applicationContext, classroom.cid)
                            }
                        } else {
                            val alreadyReminded = prefs.getBoolean(remindKey, false)
                            if (!alreadyReminded && !effectiveFindingCalis && BlePermissionUtils.hasNotificationPermission(context)) {
                                createNotificationChannel(context)
                                val title = "Check-In Reminder"
                                val body = "Class \"${classroom.classroomName}\" scheduled at ${classroom.startTime}. You haven't checked in yet. Tap to check in."
                                val intent = Intent(context, ConnectionRequirementActivity::class.java).apply {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                                    putExtra("check_in_reminder_tap", true)
                                    putExtra("classroom_id", classroom.cid)
                                }
                                val notifId = getTaskNotificationId("check_in_${classroom.cid}")
                                val pendingIntent = PendingIntent.getActivity(context, notifId, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

                                val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                                    .setSmallIcon(R.drawable.ic_notification_logo)
                                    .setContentTitle(title)
                                    .setContentText(body)
                                    .setStyle(NotificationCompat.BigTextStyle().bigText(body))
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    .setDefaults(NotificationCompat.DEFAULT_SOUND or NotificationCompat.DEFAULT_VIBRATE)
                                    .setVibrate(longArrayOf(0, 500, 250, 500))
                                    .setAutoCancel(false)
                                    .setOnlyAlertOnce(true)
                                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                                    .setContentIntent(pendingIntent)
                                    .build()

                                notifManager.notify(notifId, notification)
                                prefs.edit().putBoolean(remindKey, true).apply()
                                Log.d(TAG, "Check-in reminder posted once for classroom ${classroom.cid}")
                            }
                        }
                    }

                    // Late warning notification: triggers if ST user hasn't checked in and grace period passed
                    if (currentMinutes >= graceMins) {
                        val lateKey = "late_warn_reminded_${classroom.cid}_$todayDateStr"
                        if (!prefs.getBoolean(lateKey, false)) {
                            if (BlePermissionUtils.hasNotificationPermission(context)) {
                                createNotificationChannel(context)
                                val title = "Late Attendance Warning"
                                val body = "You missed the grace period for \"${classroom.classroomName}\" (grace period was ${classroom.graceTime}). Please check in immediately!"
                                val intent = Intent(context, ConnectionRequirementActivity::class.java).apply {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                                    putExtra("check_in_reminder_tap", true)
                                    putExtra("classroom_id", classroom.cid)
                                }
                                val notifId = getTaskNotificationId("late_${classroom.cid}")
                                val pendingIntent = PendingIntent.getActivity(context, notifId, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

                                val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                                    .setSmallIcon(android.R.drawable.stat_notify_error)
                                    .setContentTitle(title)
                                    .setContentText(body)
                                    .setStyle(NotificationCompat.BigTextStyle().bigText(body))
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    .setDefaults(NotificationCompat.DEFAULT_SOUND or NotificationCompat.DEFAULT_VIBRATE)
                                    .setVibrate(longArrayOf(0, 500, 500, 500))
                                    .setAutoCancel(false)
                                    .setOnlyAlertOnce(true)
                                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                                    .setContentIntent(pendingIntent)
                                    .build()

                                notifManager.notify(notifId, notification)
                                prefs.edit().putBoolean(lateKey, true).apply()
                                Log.d(TAG, "Late warning reminder posted for classroom ${classroom.cid}")
                            }
                        }
                    }
                }

            } catch (e: Exception) {
                Log.e(TAG, "Error in checkAndPostCheckInReminder", e)
            }
        }
    }

    fun buildFindingCalisNotification(
        context: Context,
        classroomId: String,
        classroomName: String?,
        isFound: Boolean = false,
        isCheckingIn: Boolean = false,
        scanCountdown: Int? = null,
        progressStep: String? = null
    ): Notification {
        createNotificationChannel(context)

        val clsNameStr = if (!classroomName.isNullOrBlank()) "\"$classroomName\"" else "Classroom"
        val title: String
        val body: String

        if (progressStep != null) {
            title = "Check-In Progress - $progressStep"
            body = when (progressStep) {
                "Connecting" -> "Calis Device detected. Establishing BLE GATT connection..."
                "Transmitting" -> "Connected to Calis Device! Delivering student check-in packet..."
                "Checked-In" -> "Check-In Complete for $clsNameStr! Attendance marked."
                else -> "Processing check-in for $clsNameStr..."
            }
        } else {
            val suffix = when {
                isCheckingIn -> "Connecting..."
                isFound -> "Calis Device Found!"
                scanCountdown != null -> "Scanning... (${scanCountdown}s)"
                else -> "Scanning..."
            }
            title = "Finding Calis Device - $suffix"
            body = when {
                isCheckingIn -> "Connected! Transmitting check-in packet to Calis Device..."
                isFound -> "Connecting to $clsNameStr Calis Device to check-in..."
                else -> "Searching for $clsNameStr Calis Device to automatically check-in"
            }
        }

        val openAppIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("EXTRA_NAVIGATE_TARGET", "CHECKIN")
            putExtra("classroom_id", classroomId)
        }
        val notifId = getTaskNotificationId("check_in_$classroomId")
        val pendingIntent = PendingIntent.getActivity(
            context,
            notifId,
            openAppIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification_logo)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(pendingIntent)
            .build()
    }

    fun updateNotificationToFindingCalisDevice(context: Context, classroomId: String, isFound: Boolean = false) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (!BlePermissionUtils.hasNotificationPermission(context)) return@launch
                val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                prefs.edit().putBoolean("finding_calis_$classroomId", true).apply()

                StudentBleService.startService(context, classroomId)
                if (StudentBleService.isServiceRunning()) {
                    StudentBleService.updateNotification(context, isFound = isFound, isCheckingIn = false)
                    return@launch
                }

                val notifManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager ?: return@launch
                val db = CalisDatabase.getDatabase(context)
                val cls = db.classroomDao().getClassroomById(classroomId)
                val notification = buildFindingCalisNotification(context, classroomId, cls?.classroomName, isFound = isFound, isCheckingIn = false)

                val notifId = getTaskNotificationId("check_in_$classroomId")
                val fingerprint = "FindingCalis|$isFound|false|$notifId"
                if (lastPostedFingerprints[notifId] == fingerprint) return@launch
                lastPostedFingerprints[notifId] = fingerprint

                notifManager.notify(notifId, notification)
                Log.d(TAG, "Notification updated to Finding Calis Device for classroom $classroomId (isFound=$isFound)")
            } catch (e: Exception) {
                Log.e(TAG, "Error updating notification to Finding Calis Device", e)
            }
        }
    }

    fun updateNotificationToCheckingIn(context: Context, classroomId: String) {
        try {
            if (!BlePermissionUtils.hasNotificationPermission(context)) return
            if (StudentBleService.isServiceRunning()) {
                StudentBleService.updateNotification(context, isFound = true, isCheckingIn = true)
                return
            }
            val notifManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager ?: return
            val notifId = getTaskNotificationId("check_in_$classroomId")
            val fingerprint = "CheckingIn|$notifId"
            if (lastPostedFingerprints[notifId] == fingerprint) return
            lastPostedFingerprints[notifId] = fingerprint

            val notification = buildFindingCalisNotification(context, classroomId, null, isFound = true, isCheckingIn = true)
            notifManager.notify(notifId, notification)
            Log.d(TAG, "Notification updated to Checking-in for classroom $classroomId")
        } catch (e: Exception) {
            Log.e(TAG, "Error updating notification to Checking-in", e)
        }
    }

    fun getCheckInNotification(context: Context, classroomId: String, title: String, body: String): Notification {
        createNotificationChannel(context)
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(android.R.drawable.stat_notify_sync)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(true)
            .setContentIntent(pendingIntent)
            .build()
    }

    private var findingCalisScanJob: Job? = null

    fun startFindingCalisScanLoop(context: Context) {
        StudentBleService.startService(context)
    }

    fun stopFindingCalisScanLoop(context: Context) {
        StudentBleService.stopService(context)
    }

    fun updateNotificationToCheckedInSuccess(context: Context, classroomId: String, classroomName: String? = null) {
        try {
            stopFindingCalisScanLoop(context)
            val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
            prefs.edit().putBoolean("finding_calis_$classroomId", false).apply()
            if (!BlePermissionUtils.hasNotificationPermission(context)) return
            val notifManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager ?: return
            createNotificationChannel(context)
            val title = "Checked-in successfully!"
            val body = if (!classroomName.isNullOrBlank()) {
                "Your attendance has been recorded for \"$classroomName\"."
            } else {
                "Your attendance has been recorded."
            }
            
            val notifId = getTaskNotificationId("check_in_$classroomId")
            val fingerprint = "$title|$body|$notifId"
            if (lastPostedFingerprints[notifId] == fingerprint) return
            lastPostedFingerprints[notifId] = fingerprint

            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            val pendingIntent = PendingIntent.getActivity(context, notifId, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_logo)
                .setContentTitle(title)
                .setContentText(body)
                .setStyle(NotificationCompat.BigTextStyle().bigText(body))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setOngoing(false)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setContentIntent(pendingIntent)
                .build()

            notifManager.notify(notifId, notification)
            Log.d(TAG, "Notification updated to Checked-in successfully! for classroom $classroomId")
        } catch (e: Exception) {
            Log.e(TAG, "Error updating notification to Checked-in successfully!", e)
        }
    }

    fun cancelCheckInAndLateNotifications(context: Context, classroomId: String) {
        try {
            stopFindingCalisScanLoop(context)
            val notifManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            notifManager?.cancel(getTaskNotificationId("check_in_$classroomId"))
            notifManager?.cancel(getTaskNotificationId("late_$classroomId"))
            val todayDateStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
            val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
            prefs.edit()
                .putBoolean("finding_calis_${classroomId}", false)
                .putBoolean("checked_in_today_${classroomId}_$todayDateStr", true)
                .apply()
        } catch (e: Exception) {
            Log.e(TAG, "Error cancelling check-in and late notifications: ${e.message}")
        }
    }
}
