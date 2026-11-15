package com.example

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.example.ble.TeacherBleManager
import com.example.ble.StudentBleManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalisApplication : Application(), Application.ActivityLifecycleCallbacks {

    companion object {
        var isAppInForeground: Boolean = false
            private set
    }

    private var activityCount = 0

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        Log.d("CalisApplication", "Application onCreate: initializing Calis")

        val prefs = getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        if (!prefs.contains("remind_start_session")) editor.putBoolean("remind_start_session", true)
        if (!prefs.contains("remind_task_deadline")) editor.putBoolean("remind_task_deadline", true)
        if (!prefs.contains("remind_check_in") || !prefs.getBoolean("remind_check_in_default_restored_v1", false)) {
            editor.putBoolean("remind_check_in", true)
            editor.putBoolean("remind_check_in_default_restored_v1", true)
        }
        if (!prefs.contains("auto_startup_enabled")) editor.putBoolean("auto_startup_enabled", true)
        editor.apply()

        // Perform clean startup BLE state reset ONLY on fresh startup for both TC and ST
        val teacherBleManager = TeacherBleManager.getInstance(this)
        teacherBleManager.resetBleStateOnStartup(force = false)
        val studentBleManager = StudentBleManager.getInstance(this)
        studentBleManager.resetBleStateOnStartup(force = false)

        // Start reliable background reminder checker via WorkManager
        com.example.util.ReminderWorker.schedule(this)
        
        // Ensure exact alarms are scheduled immediately on startup
        CoroutineScope(Dispatchers.IO).launch {
            com.example.util.ReminderNotificationHelper.rescheduleSessionRemindersSync(this@CalisApplication)
            com.example.util.ReminderNotificationHelper.rescheduleCheckInRemindersSync(this@CalisApplication)
        }

        // Start real-time background reminder checker (for UI/foreground responsiveness)
        com.example.util.ReminderNotificationHelper.startPeriodicChecker(this)
    }

    override fun onActivityStarted(activity: Activity) {
        activityCount++
        isAppInForeground = activityCount > 0
    }

    override fun onActivityStopped(activity: Activity) {
        activityCount = maxOf(0, activityCount - 1)
        isAppInForeground = activityCount > 0
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {}
}

