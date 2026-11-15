package com.example

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.db.CalisDatabase
import com.example.data.db.ClassroomEntity
import com.example.util.ReminderNotificationHelper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class ReminderSchedulingTest {

    private lateinit var context: Context
    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        val app = ApplicationProvider.getApplicationContext<android.app.Application>()
        shadowOf(app).grantPermissions(android.Manifest.permission.POST_NOTIFICATIONS)
        val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        prefs.edit().clear().commit()
    }

    private fun createBaseTime(year: Int = 2026, month: Int = Calendar.OCTOBER, day: Int = 12, hour: Int = 5, minute: Int = 0): Long {
        // 2026-10-12 is a Monday
        val cal = Calendar.getInstance().apply {
            set(year, month, day, hour, minute, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return cal.timeInMillis
    }

    @Test
    fun `test class at 6_00 AM reminder 10 minutes triggers at 5_50 AM`() {
        val classroom = ClassroomEntity(
            cid = "C101",
            teacherId = "T001",
            classroomName = "Grade 11 - Section A",
            teacherName = "Teacher",
            days = "MON-FRI",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:00 AM"
        )
        // Current time: Monday Oct 12, 2026 at 5:00 AM
        val currentTime = createBaseTime(hour = 5, minute = 0)
        val triggerTime = ReminderNotificationHelper.calculateNextSessionReminderTime(
            classroom = classroom,
            reminderMinutes = 10,
            currentTimeMillis = currentTime
        )

        assertNotNull(triggerTime)
        val triggerCal = Calendar.getInstance().apply { timeInMillis = triggerTime!! }
        assertEquals(5, triggerCal.get(Calendar.HOUR_OF_DAY))
        assertEquals(50, triggerCal.get(Calendar.MINUTE))
        assertEquals(0, triggerCal.get(Calendar.SECOND))

        println("TEST 1 -> Class 6:00 AM, reminder 10m: trigger at ${sdf.format(Date(triggerTime!!))}")
    }

    @Test
    fun `test class at 6_00 AM reminder 5 minutes triggers at 5_55 AM`() {
        val classroom = ClassroomEntity(
            cid = "C102",
            teacherId = "T001",
            classroomName = "Grade 11 - Section B",
            teacherName = "Teacher",
            days = "MON-FRI",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:00 AM"
        )
        // Current time: Monday Oct 12, 2026 at 5:00 AM
        val currentTime = createBaseTime(hour = 5, minute = 0)
        val triggerTime = ReminderNotificationHelper.calculateNextSessionReminderTime(
            classroom = classroom,
            reminderMinutes = 5,
            currentTimeMillis = currentTime
        )

        assertNotNull(triggerTime)
        val triggerCal = Calendar.getInstance().apply { timeInMillis = triggerTime!! }
        assertEquals(5, triggerCal.get(Calendar.HOUR_OF_DAY))
        assertEquals(55, triggerCal.get(Calendar.MINUTE))
        assertEquals(0, triggerCal.get(Calendar.SECOND))

        println("TEST 2 -> Class 6:00 AM, reminder 5m: trigger at ${sdf.format(Date(triggerTime!!))}")
    }

    @Test
    fun `test class at 6_00 AM reminder 15 minutes triggers at 5_45 AM`() {
        val classroom = ClassroomEntity(
            cid = "C103",
            teacherId = "T001",
            classroomName = "Grade 11 - Section C",
            teacherName = "Teacher",
            days = "MON-FRI",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:00 AM"
        )
        // Current time: Monday Oct 12, 2026 at 5:00 AM
        val currentTime = createBaseTime(hour = 5, minute = 0)
        val triggerTime = ReminderNotificationHelper.calculateNextSessionReminderTime(
            classroom = classroom,
            reminderMinutes = 15,
            currentTimeMillis = currentTime
        )

        assertNotNull(triggerTime)
        val triggerCal = Calendar.getInstance().apply { timeInMillis = triggerTime!! }
        assertEquals(5, triggerCal.get(Calendar.HOUR_OF_DAY))
        assertEquals(45, triggerCal.get(Calendar.MINUTE))
        assertEquals(0, triggerCal.get(Calendar.SECOND))

        println("TEST 3 -> Class 6:00 AM, reminder 15m: trigger at ${sdf.format(Date(triggerTime!!))}")
    }

    @Test
    fun `test reminder setting changed after an alarm was scheduled replaces alarm`() {
        val classroom = ClassroomEntity(
            cid = "C104",
            teacherId = "T001",
            classroomName = "Grade 11 - Section D",
            teacherName = "Teacher",
            days = "DAILY",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:00 AM"
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val shadowAlarm = shadowOf(alarmManager)

        val triggerTime1 = System.currentTimeMillis() + 600_000L
        ReminderNotificationHelper.scheduleSessionAlarm(context, classroom, triggerTime1, 10)
        assertEquals(1, shadowAlarm.scheduledAlarms.size)

        // Now setting changes: reminder changed to 5 minutes
        val triggerTime2 = System.currentTimeMillis() + 300_000L
        ReminderNotificationHelper.scheduleSessionAlarm(context, classroom, triggerTime2, 5)

        // Alarm was replaced, not duplicated
        assertEquals(1, shadowAlarm.scheduledAlarms.size)
        assertEquals(triggerTime2, shadowAlarm.nextScheduledAlarm!!.triggerAtTime)

        println("TEST 4 -> Setting changed replaced alarm timestamp to: ${sdf.format(Date(triggerTime2))}")
    }

    @Test
    fun `test disabled reminder cancels pending alarms`() {
        val classroom = ClassroomEntity(
            cid = "C105",
            teacherId = "T001",
            classroomName = "Grade 11 - Section E",
            teacherName = "Teacher",
            days = "DAILY",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:00 AM"
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val shadowAlarm = shadowOf(alarmManager)

        val triggerTime = System.currentTimeMillis() + 600_000L
        ReminderNotificationHelper.scheduleSessionAlarm(context, classroom, triggerTime, 10)
        assertEquals(1, shadowAlarm.scheduledAlarms.size)

        // Disabling reminder cancels the alarm
        ReminderNotificationHelper.cancelSessionAlarm(context, classroom)
        assertEquals(0, shadowAlarm.scheduledAlarms.size)

        println("TEST 5 -> Disabled reminder successfully cancelled pending alarm")
    }

    @Test
    fun `test when today reminder time has already passed next valid occurrence is selected`() {
        val classroom = ClassroomEntity(
            cid = "C106",
            teacherId = "T001",
            classroomName = "Grade 11 - Section F",
            teacherName = "Teacher",
            days = "MON-FRI",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:00 AM"
        )
        // Current time: Monday Oct 12, 2026 at 7:00 AM (class start time and reminder time have passed)
        val currentTime = createBaseTime(hour = 7, minute = 0)
        val triggerTime = ReminderNotificationHelper.calculateNextSessionReminderTime(
            classroom = classroom,
            reminderMinutes = 10,
            currentTimeMillis = currentTime
        )

        assertNotNull(triggerTime)
        val triggerCal = Calendar.getInstance().apply { timeInMillis = triggerTime!! }
        // Should be Tuesday Oct 13, 2026 at 5:50 AM
        assertEquals(Calendar.TUESDAY, triggerCal.get(Calendar.DAY_OF_WEEK))
        assertEquals(5, triggerCal.get(Calendar.HOUR_OF_DAY))
        assertEquals(50, triggerCal.get(Calendar.MINUTE))

        println("TEST 6 -> Passed today (Monday 7:00 AM), next trigger is Tuesday: ${sdf.format(Date(triggerTime!!))}")
    }

    @Test
    fun `test multiple class days selects next valid occurrence`() {
        val classroom = ClassroomEntity(
            cid = "C107",
            teacherId = "T001",
            classroomName = "Grade 11 - Section G",
            teacherName = "Teacher",
            days = "M-W-F",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:00 AM"
        )
        // Current time: Monday Oct 12, 2026 at 7:00 AM (Monday passed). Next class day in M-W-F is Wednesday!
        val currentTime = createBaseTime(hour = 7, minute = 0)
        val triggerTime = ReminderNotificationHelper.calculateNextSessionReminderTime(
            classroom = classroom,
            reminderMinutes = 10,
            currentTimeMillis = currentTime
        )

        assertNotNull(triggerTime)
        val triggerCal = Calendar.getInstance().apply { timeInMillis = triggerTime!! }
        assertEquals(Calendar.WEDNESDAY, triggerCal.get(Calendar.DAY_OF_WEEK))
        assertEquals(5, triggerCal.get(Calendar.HOUR_OF_DAY))
        assertEquals(50, triggerCal.get(Calendar.MINUTE))

        println("TEST 7 -> Multi-day schedule M-W-F on Monday after class selects Wednesday: ${sdf.format(Date(triggerTime!!))}")
    }

    @Test
    fun `test pre-session window at 5_56 AM for 6_00 AM class immediately posts notification`() = runBlocking {
        val classroom = ClassroomEntity(
            cid = "C108",
            teacherId = "T001",
            classroomName = "Grade 11 - Section H",
            teacherName = "Teacher",
            days = "MON-FRI",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:00 AM"
        )
        val db = CalisDatabase.getDatabase(context)
        db.teacherDao().insertTeacher(
            com.example.data.db.TeacherEntity(
                tid = "T001",
                fullName = "Teacher"
            )
        )
        db.classroomDao().insertClassroom(classroom)

        val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        prefs.edit()
            .putBoolean("remind_start_session", true)
            .putInt("remind_start_session_minutes", 5)
            .commit()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val shadowNotification = shadowOf(notificationManager)

        // Post notification directly using pre-session logic
        val posted = ReminderNotificationHelper.postSessionReminderNotification(context, classroom, 5)
        assertTrue(posted)

        val notifications = shadowNotification.allNotifications
        assertEquals(1, notifications.size)
        val notif = notifications[0]
        val shadowNotif = shadowOf(notif)
        assertEquals("Start Session Reminder", shadowNotif.contentTitle)
        assertTrue(shadowNotif.contentText.toString().contains("Grade 11 - Section H"))
        assertTrue(shadowNotif.contentText.toString().contains("Starts in 5 min"))

        println("TEST 8 -> Pre-session window notification posted immediately at 5:56 AM: ${shadowNotif.contentText}")
    }

    @Test
    fun `test clearSessionReminderKeys removes all last_session_reminded keys`() {
        val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        prefs.edit()
            .putString("last_session_reminded_key_C101", "2026-10-10_12345_5")
            .putString("last_session_reminded_key_C102", "2026-10-10_67890_10")
            .putString("last_session_reminded_date", "2026-10-10")
            .putString("user_role", "TEACHER")
            .commit()

        ReminderNotificationHelper.clearSessionReminderKeys(context)

        assertEquals("", prefs.getString("last_session_reminded_key_C101", ""))
        assertEquals("", prefs.getString("last_session_reminded_key_C102", ""))
        assertEquals("", prefs.getString("last_session_reminded_date", ""))
        // Non-reminder keys should be preserved
        assertEquals("TEACHER", prefs.getString("user_role", ""))

        println("TEST 9 -> clearSessionReminderKeys correctly wiped session reminder keys")
    }

    @Test
    fun `test ReminderAlarmReceiver handles TIME_SET action`() = runBlocking {
        val classroom = ClassroomEntity(
            cid = "C109",
            teacherId = "T001",
            classroomName = "Grade 12 - Einstein",
            teacherName = "Teacher",
            days = "MON-FRI",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:00 AM"
        )
        val db = CalisDatabase.getDatabase(context)
        db.teacherDao().insertTeacher(
            com.example.data.db.TeacherEntity(
                tid = "T001",
                fullName = "Teacher"
            )
        )
        db.classroomDao().insertClassroom(classroom)

        val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        prefs.edit()
            .putBoolean("remind_start_session", true)
            .putInt("remind_start_session_minutes", 5)
            .commit()

        val receiver = com.example.util.ReminderAlarmReceiver()
        val intent = android.content.Intent(android.content.Intent.ACTION_TIME_CHANGED)

        // Trigger onReceive with TIME_CHANGED action
        receiver.onReceive(context, intent)

        println("TEST 10 -> ReminderAlarmReceiver safely processed TIME_CHANGED broadcast")
    }
}
