package com.example

import android.app.AlarmManager
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.db.ClassroomEntity
import com.example.util.ReminderNotificationHelper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowAlarmManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class SessionReminderSchedulingTest {

    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager
    private lateinit var shadowAlarmManager: ShadowAlarmManager

    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    private val timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        shadowAlarmManager = Shadows.shadowOf(alarmManager)
    }

    private fun createCalendarAt(
        year: Int,
        month: Int,
        day: Int,
        hourOfDay: Int,
        minute: Int,
        second: Int = 0
    ): Calendar {
        return Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, second)
            set(Calendar.MILLISECOND, 0)
        }
    }

    @Test
    fun testClassAt6AM_reminder10Minutes_triggersAt550AM() {
        // Class at 6:00 AM, reminder 10 minutes: trigger at 5:50 AM.
        // Given current time is Monday Oct 12, 2026, 05:00:00 AM
        val currentCal = createCalendarAt(2026, Calendar.OCTOBER, 12, 5, 0) // Monday
        assertEquals(Calendar.MONDAY, currentCal.get(Calendar.DAY_OF_WEEK))

        val classroom = ClassroomEntity(
            cid = "c_math101",
            teacherId = "t_001",
            classroomName = "Math 101",
            teacherName = "Prof. Smith",
            days = "MON-FRI",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:30 AM",
            isBound = true
        )

        val triggerTimeNullable = ReminderNotificationHelper.calculateNextSessionReminderTime(
            classroom = classroom,
            reminderMinutes = 10,
            currentTimeMillis = currentCal.timeInMillis
        )

        assertNotNull("Trigger time should not be null", triggerTimeNullable)
        val triggerTimeMs = checkNotNull(triggerTimeNullable)
        val triggerCal = Calendar.getInstance().apply { timeInMillis = triggerTimeMs }
        assertEquals(5, triggerCal.get(Calendar.HOUR_OF_DAY))
        assertEquals(50, triggerCal.get(Calendar.MINUTE))
        assertEquals(0, triggerCal.get(Calendar.SECOND))
        assertEquals(12, triggerCal.get(Calendar.DAY_OF_MONTH))

        // Schedule with AlarmManager and verify
        ReminderNotificationHelper.scheduleSessionAlarm(context, classroom, triggerTimeMs, 10)
        val scheduledAlarms = shadowAlarmManager.scheduledAlarms
        assertTrue(scheduledAlarms.isNotEmpty())
        val alarm = scheduledAlarms.first()
        assertEquals(triggerTimeMs, alarm.triggerAtTime)

        println("[TEST 1 PASSED] Class at 6:00 AM, Reminder: 10 min -> Computed Trigger: ${sdf.format(Date(triggerTimeMs))}, Alarm Timestamp: ${alarm.triggerAtTime} (${timeFormat.format(Date(alarm.triggerAtTime))})")
    }

    @Test
    fun testClassAt6AM_reminder5Minutes_triggersAt555AM() {
        // Class at 6:00 AM, reminder 5 minutes: trigger at 5:55 AM.
        val currentCal = createCalendarAt(2026, Calendar.OCTOBER, 12, 5, 0) // Monday
        val classroom = ClassroomEntity(
            cid = "c_chem201",
            teacherId = "t_001",
            classroomName = "Chemistry 201",
            teacherName = "Prof. Smith",
            days = "MON-FRI",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:30 AM",
            isBound = true
        )

        val triggerTimeNullable = ReminderNotificationHelper.calculateNextSessionReminderTime(
            classroom = classroom,
            reminderMinutes = 5,
            currentTimeMillis = currentCal.timeInMillis
        )

        assertNotNull("Trigger time should not be null", triggerTimeNullable)
        val triggerTimeMs = checkNotNull(triggerTimeNullable)
        val triggerCal = Calendar.getInstance().apply { timeInMillis = triggerTimeMs }
        assertEquals(5, triggerCal.get(Calendar.HOUR_OF_DAY))
        assertEquals(55, triggerCal.get(Calendar.MINUTE))
        assertEquals(0, triggerCal.get(Calendar.SECOND))
        assertEquals(12, triggerCal.get(Calendar.DAY_OF_MONTH))

        ReminderNotificationHelper.scheduleSessionAlarm(context, classroom, triggerTimeMs, 5)
        val alarm = shadowAlarmManager.scheduledAlarms.first()
        assertEquals(triggerTimeMs, alarm.triggerAtTime)

        println("[TEST 2 PASSED] Class at 6:00 AM, Reminder: 5 min -> Computed Trigger: ${sdf.format(Date(triggerTimeMs))}, Alarm Timestamp: ${alarm.triggerAtTime} (${timeFormat.format(Date(alarm.triggerAtTime))})")
    }

    @Test
    fun testClassAt6AM_reminder15Minutes_triggersAt545AM() {
        // Class at 6:00 AM, reminder 15 minutes: trigger at 5:45 AM.
        val currentCal = createCalendarAt(2026, Calendar.OCTOBER, 12, 5, 0) // Monday
        val classroom = ClassroomEntity(
            cid = "c_bio301",
            teacherId = "t_001",
            classroomName = "Biology 301",
            teacherName = "Prof. Smith",
            days = "MON-FRI",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:30 AM",
            isBound = true
        )

        val triggerTimeNullable = ReminderNotificationHelper.calculateNextSessionReminderTime(
            classroom = classroom,
            reminderMinutes = 15,
            currentTimeMillis = currentCal.timeInMillis
        )

        assertNotNull("Trigger time should not be null", triggerTimeNullable)
        val triggerTimeMs = checkNotNull(triggerTimeNullable)
        val triggerCal = Calendar.getInstance().apply { timeInMillis = triggerTimeMs }
        assertEquals(5, triggerCal.get(Calendar.HOUR_OF_DAY))
        assertEquals(45, triggerCal.get(Calendar.MINUTE))
        assertEquals(0, triggerCal.get(Calendar.SECOND))
        assertEquals(12, triggerCal.get(Calendar.DAY_OF_MONTH))

        ReminderNotificationHelper.scheduleSessionAlarm(context, classroom, triggerTimeMs, 15)
        val alarm = shadowAlarmManager.scheduledAlarms.first()
        assertEquals(triggerTimeMs, alarm.triggerAtTime)

        println("[TEST 3 PASSED] Class at 6:00 AM, Reminder: 15 min -> Computed Trigger: ${sdf.format(Date(triggerTimeMs))}, Alarm Timestamp: ${alarm.triggerAtTime} (${timeFormat.format(Date(alarm.triggerAtTime))})")
    }

    @Test
    fun testReminderSettingChangedAfterAlarmScheduled() {
        // Test a reminder setting changed after an alarm was scheduled.
        // Initially scheduled at 15 minutes (5:45 AM), then changed to 10 minutes (5:50 AM).
        val currentCal = createCalendarAt(2026, Calendar.OCTOBER, 12, 5, 0) // Monday
        val classroom = ClassroomEntity(
            cid = "c_cs401",
            teacherId = "t_001",
            classroomName = "CS 401",
            teacherName = "Prof. Smith",
            days = "MON-FRI",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:30 AM",
            isBound = true
        )

        // 1. Initial 15-minute alarm
        val initialTriggerMs = checkNotNull(
            ReminderNotificationHelper.calculateNextSessionReminderTime(
                classroom = classroom,
                reminderMinutes = 15,
                currentTimeMillis = currentCal.timeInMillis
            )
        )
        ReminderNotificationHelper.scheduleSessionAlarm(context, classroom, initialTriggerMs, 15)
        assertEquals(1, shadowAlarmManager.scheduledAlarms.size)
        assertEquals(initialTriggerMs, shadowAlarmManager.scheduledAlarms.first().triggerAtTime)

        // 2. Setting changed to 10 minutes: cancel old alarm and schedule new alarm
        ReminderNotificationHelper.cancelSessionAlarm(context, classroom)
        val newTriggerMs = checkNotNull(
            ReminderNotificationHelper.calculateNextSessionReminderTime(
                classroom = classroom,
                reminderMinutes = 10,
                currentTimeMillis = currentCal.timeInMillis
            )
        )
        ReminderNotificationHelper.scheduleSessionAlarm(context, classroom, newTriggerMs, 10)

        // Verify old alarm is replaced and only 1 alarm exists at 5:50 AM
        assertEquals(1, shadowAlarmManager.scheduledAlarms.size)
        val updatedAlarm = shadowAlarmManager.scheduledAlarms.first()
        assertEquals(newTriggerMs, updatedAlarm.triggerAtTime)

        val newCal = Calendar.getInstance().apply { timeInMillis = newTriggerMs }
        assertEquals(5, newCal.get(Calendar.HOUR_OF_DAY))
        assertEquals(50, newCal.get(Calendar.MINUTE))

        println("[TEST 4 PASSED] Reminder changed from 15 min to 10 min -> Old Alarm (5:45 AM) replaced with: ${sdf.format(Date(newTriggerMs))} (Timestamp: ${updatedAlarm.triggerAtTime})")
    }

    @Test
    fun testDisabledReminderCancelsPendingAlarms() {
        // Test a disabled reminder cancels its pending alarms.
        val currentCal = createCalendarAt(2026, Calendar.OCTOBER, 12, 5, 0)
        val classroom = ClassroomEntity(
            cid = "c_phy501",
            teacherId = "t_001",
            classroomName = "Physics 501",
            teacherName = "Prof. Smith",
            days = "MON-FRI",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:30 AM",
            isBound = true
        )

        val triggerTimeMs = checkNotNull(
            ReminderNotificationHelper.calculateNextSessionReminderTime(
                classroom = classroom,
                reminderMinutes = 10,
                currentTimeMillis = currentCal.timeInMillis
            )
        )
        ReminderNotificationHelper.scheduleSessionAlarm(context, classroom, triggerTimeMs, 10)
        assertEquals(1, shadowAlarmManager.scheduledAlarms.size)

        // Disable reminder
        ReminderNotificationHelper.cancelSessionAlarm(context, classroom)
        assertEquals(0, shadowAlarmManager.scheduledAlarms.size)

        println("[TEST 5 PASSED] Disabled reminder cancelled pending alarm successfully. Scheduled alarms count: ${shadowAlarmManager.scheduledAlarms.size}")
    }

    @Test
    fun testWhenTodaysReminderTimeHasAlreadyPassed() {
        // Test when today's reminder time has already passed.
        // Class at 6:00 AM, reminder 10 minutes (reminder time was 5:50 AM).
        // Current time is Monday 5:55 AM (5:50 AM has already passed).
        // It must NOT post a stale reminder or schedule for today; it must select Tuesday 5:50 AM.
        val currentCal = createCalendarAt(2026, Calendar.OCTOBER, 12, 5, 55) // Monday 5:55 AM
        val classroom = ClassroomEntity(
            cid = "c_history101",
            teacherId = "t_001",
            classroomName = "History 101",
            teacherName = "Prof. Smith",
            days = "MON-FRI",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:30 AM",
            isBound = true
        )

        val nextTriggerNullable = ReminderNotificationHelper.calculateNextSessionReminderTime(
            classroom = classroom,
            reminderMinutes = 10,
            currentTimeMillis = currentCal.timeInMillis
        )

        assertNotNull("Next occurrence trigger should be found", nextTriggerNullable)
        val nextTriggerMs = checkNotNull(nextTriggerNullable)
        val nextCal = Calendar.getInstance().apply { timeInMillis = nextTriggerMs }

        // Must be TUESDAY Oct 13 at 5:50 AM!
        assertEquals(Calendar.TUESDAY, nextCal.get(Calendar.DAY_OF_WEEK))
        assertEquals(13, nextCal.get(Calendar.DAY_OF_MONTH))
        assertEquals(5, nextCal.get(Calendar.HOUR_OF_DAY))
        assertEquals(50, nextCal.get(Calendar.MINUTE))
        assertEquals(0, nextCal.get(Calendar.SECOND))

        ReminderNotificationHelper.scheduleSessionAlarm(context, classroom, nextTriggerMs, 10)
        val alarm = shadowAlarmManager.scheduledAlarms.first()
        assertEquals(nextTriggerMs, alarm.triggerAtTime)

        println("[TEST 6 PASSED] Today's reminder (5:50 AM) passed at 5:55 AM -> Next valid occurrence: ${sdf.format(Date(nextTriggerMs))} (Timestamp: ${alarm.triggerAtTime}) on Tuesday")
    }

    @Test
    fun testMultipleClassDays_selectsNextValidOccurrence() {
        // Test multiple class days and verify that the next valid occurrence is selected.
        // Schedule: "MON, WED, FRI"
        val classroom = ClassroomEntity(
            cid = "c_art101",
            teacherId = "t_001",
            classroomName = "Art 101",
            teacherName = "Prof. Smith",
            days = "MON, WED, FRI",
            startTime = "06:00 AM",
            graceTime = "06:15 AM",
            endTime = "07:30 AM",
            isBound = true
        )

        // Case A: Current time is Monday Oct 12, 2026, 10:00 AM (Monday class reminder passed).
        // Tuesday is NOT in schedule. Next valid occurrence must be Wednesday Oct 14 at 5:50 AM.
        val mondayAfternoon = createCalendarAt(2026, Calendar.OCTOBER, 12, 10, 0)
        val triggerWedNullable = ReminderNotificationHelper.calculateNextSessionReminderTime(
            classroom = classroom,
            reminderMinutes = 10,
            currentTimeMillis = mondayAfternoon.timeInMillis
        )
        assertNotNull(triggerWedNullable)
        val triggerWedMs = checkNotNull(triggerWedNullable)
        val wedCal = Calendar.getInstance().apply { timeInMillis = triggerWedMs }
        assertEquals(Calendar.WEDNESDAY, wedCal.get(Calendar.DAY_OF_WEEK))
        assertEquals(14, wedCal.get(Calendar.DAY_OF_MONTH))
        assertEquals(5, wedCal.get(Calendar.HOUR_OF_DAY))
        assertEquals(50, wedCal.get(Calendar.MINUTE))

        // Case B: Current time is Friday Oct 16, 2026, 10:00 AM (Friday class reminder passed).
        // Saturday and Sunday are NOT in schedule. Next valid occurrence must be next Monday Oct 19 at 5:50 AM.
        val fridayAfternoon = createCalendarAt(2026, Calendar.OCTOBER, 16, 10, 0)
        val triggerNextMonNullable = ReminderNotificationHelper.calculateNextSessionReminderTime(
            classroom = classroom,
            reminderMinutes = 10,
            currentTimeMillis = fridayAfternoon.timeInMillis
        )
        assertNotNull(triggerNextMonNullable)
        val triggerNextMonMs = checkNotNull(triggerNextMonNullable)
        val nextMonCal = Calendar.getInstance().apply { timeInMillis = triggerNextMonMs }
        assertEquals(Calendar.MONDAY, nextMonCal.get(Calendar.DAY_OF_WEEK))
        assertEquals(19, nextMonCal.get(Calendar.DAY_OF_MONTH))
        assertEquals(5, nextMonCal.get(Calendar.HOUR_OF_DAY))
        assertEquals(50, nextMonCal.get(Calendar.MINUTE))

        ReminderNotificationHelper.scheduleSessionAlarm(context, classroom, triggerWedMs, 10)
        val alarmWed = shadowAlarmManager.scheduledAlarms.first()
        assertEquals(triggerWedMs, alarmWed.triggerAtTime)

        println("[TEST 7 PASSED] Multi-day schedule (MON, WED, FRI):")
        println("  From Monday 10:00 AM -> Next is Wednesday: ${sdf.format(Date(triggerWedMs))} (Timestamp: ${triggerWedMs})")
        println("  From Friday 10:00 AM -> Next is Monday: ${sdf.format(Date(triggerNextMonMs))} (Timestamp: ${triggerNextMonMs})")
    }

    @Test
    fun testCrossingMidnightSchedule() {
        // Class at 00:05 (12:05 AM) on Tuesday, reminder 10 minutes.
        // Scheduled class start is Tuesday 00:05:00.
        // reminderTime = Tuesday 00:05:00 - 10 minutes = Monday 23:55:00.
        val classroom = ClassroomEntity(
            cid = "c_night_owl",
            teacherId = "t_001",
            classroomName = "Astronomy Night Class",
            teacherName = "Prof. Galileo",
            days = "TUE",
            startTime = "00:05",
            graceTime = "00:15",
            endTime = "02:00",
            isBound = true
        )

        // Monday Oct 12, 2026, 23:30:00 (11:30 PM)
        val mondayNight = createCalendarAt(2026, Calendar.OCTOBER, 12, 23, 30)
        val triggerNullable = ReminderNotificationHelper.calculateNextSessionReminderTime(
            classroom = classroom,
            reminderMinutes = 10,
            currentTimeMillis = mondayNight.timeInMillis
        )
        assertNotNull(triggerNullable)
        val triggerMs = checkNotNull(triggerNullable)
        val triggerCal = Calendar.getInstance().apply { timeInMillis = triggerMs }
        assertEquals(Calendar.MONDAY, triggerCal.get(Calendar.DAY_OF_WEEK))
        assertEquals(12, triggerCal.get(Calendar.DAY_OF_MONTH))
        assertEquals(23, triggerCal.get(Calendar.HOUR_OF_DAY))
        assertEquals(55, triggerCal.get(Calendar.MINUTE))
        assertEquals(0, triggerCal.get(Calendar.SECOND))

        println("[TEST 8 PASSED] Midnight crossing: Class Tuesday 00:05, reminder 10 min -> Trigger: ${sdf.format(Date(triggerMs))} (Monday 23:55:00, Timestamp: ${triggerMs})")
    }
}
