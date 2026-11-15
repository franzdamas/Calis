package com.example.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskReminderDismissReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        try {
            val prefs = context.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
            val today = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Date())
            prefs.edit().putString("task_deadline_dismissed_date", today).apply()
            Log.d("TaskReminderDismiss", "Task deadline reminder dismissed for today: $today")
        } catch (e: Exception) {
            Log.e("TaskReminderDismiss", "Error saving dismiss state", e)
        }
    }

    companion object {
        const val ACTION_DISMISS_TASK_REMINDER = "com.example.ACTION_DISMISS_TASK_REMINDER"
    }
}
