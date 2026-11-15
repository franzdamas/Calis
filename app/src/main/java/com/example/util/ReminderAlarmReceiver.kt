package com.example.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReminderAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent?.action ?: return
        Log.d(TAG, "ReminderAlarmReceiver triggered with action: $action")
        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                when (action) {
                    ACTION_TC_SESSION_REMINDER -> {
                        ReminderNotificationHelper.handleSessionReminderAlarmTrigger(context, intent)
                    }
                    ACTION_ST_CHECKIN_REMINDER -> {
                        ReminderNotificationHelper.checkAndPostCheckInReminder(context)
                        ReminderNotificationHelper.rescheduleCheckInRemindersSync(context)
                    }
                    ACTION_ST_TASK_REMINDER -> {
                        ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(context)
                    }
                    Intent.ACTION_BOOT_COMPLETED,
                    "android.intent.action.LOCKED_BOOT_COMPLETED",
                    "android.intent.action.QUICKBOOT_POWERON",
                    "com.htc.intent.action.QUICKBOOT_POWERON",
                    Intent.ACTION_MY_PACKAGE_REPLACED,
                    Intent.ACTION_TIME_CHANGED,
                    "android.intent.action.TIME_SET",
                    Intent.ACTION_DATE_CHANGED,
                    Intent.ACTION_TIMEZONE_CHANGED,
                    "android.bluetooth.adapter.action.STATE_CHANGED",
                    "android.location.PROVIDERS_CHANGED" -> {
                        Log.d(TAG, "System state change ($action) received - re-evaluating reminders")
                        if (action == Intent.ACTION_TIME_CHANGED || action == "android.intent.action.TIME_SET" || action == Intent.ACTION_DATE_CHANGED || action == Intent.ACTION_TIMEZONE_CHANGED) {
                            ReminderNotificationHelper.clearSessionReminderKeys(context)
                            ReminderNotificationHelper.clearTaskReminderKeys(context)
                            ReminderNotificationHelper.clearCheckInReminderKeys(context)
                        }
                        ReminderNotificationHelper.rescheduleSessionRemindersSync(context)
                        ReminderNotificationHelper.rescheduleCheckInRemindersSync(context)
                        ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(context)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error in ReminderAlarmReceiver", e)
            } finally {
                pendingResult.finish()
            }
        }
    }

    companion object {
        private const val TAG = "ReminderAlarmReceiver"
        const val ACTION_TC_SESSION_REMINDER = "com.example.ACTION_TC_SESSION_REMINDER"
        const val ACTION_ST_CHECKIN_REMINDER = "com.example.ACTION_ST_CHECKIN_REMINDER"
        const val ACTION_ST_TASK_REMINDER = "com.example.ACTION_ST_TASK_REMINDER"
    }
}
