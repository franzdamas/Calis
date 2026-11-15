package com.example.util

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.ExistingPeriodicWorkPolicy
import java.util.concurrent.TimeUnit

class ReminderWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.d(TAG, "ReminderWorker executing background checks")
        try {
            val prefs = applicationContext.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
            val role = prefs.getString("user_role", null)
            val db = com.example.data.db.CalisDatabase.getDatabase(applicationContext)
            val teacher = db.teacherDao().getTeacherOnce()
            val student = db.studentProfileDao().getStudentProfileOnce()

            val isTeacher = role == "TEACHER" || (role.isNullOrBlank() && teacher != null)
            val isStudent = role == "STUDENT" || (role.isNullOrBlank() && student != null)

            if (isTeacher) {
                ReminderNotificationHelper.checkAndPostSessionReminder(applicationContext)
                ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(applicationContext)
                ReminderNotificationHelper.rescheduleSessionRemindersSync(applicationContext)
            }
            if (isStudent) {
                ReminderNotificationHelper.checkAndPostTaskDeadlineReminder(applicationContext)
                ReminderNotificationHelper.checkAndPostCheckInReminder(applicationContext)
                ReminderNotificationHelper.rescheduleCheckInRemindersSync(applicationContext)
            }
            
            return Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error in ReminderWorker", e)
            return Result.retry()
        }
    }

    companion object {
        private const val TAG = "ReminderWorker"
        private const val WORK_NAME = "com.example.REMINDER_WORK"

        fun schedule(context: Context) {
            try {
                val request = PeriodicWorkRequestBuilder<ReminderWorker>(15, TimeUnit.MINUTES)
                    .addTag(TAG)
                    .build()

                WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                    WORK_NAME,
                    ExistingPeriodicWorkPolicy.KEEP, // Use KEEP to avoid resetting the 15m timer every app start
                    request
                )
                Log.d(TAG, "ReminderWorker unique periodic work scheduled (15m interval)")
            } catch (e: Throwable) {
                Log.w(TAG, "WorkManager not available for scheduling: ${e.message}")
            }
        }
    }
}
