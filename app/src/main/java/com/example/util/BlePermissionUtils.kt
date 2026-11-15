package com.example.util

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object BlePermissionUtils {
    @Volatile
    var appContext: Context? = null

    fun findActivity(context: Context?): Activity? {
        var currentContext: Context? = context ?: appContext
        while (currentContext is ContextWrapper) {
            if (currentContext is Activity) return currentContext
            currentContext = currentContext.baseContext
        }
        return currentContext as? Activity
    }

    fun getRequiredPermissions(): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            arrayOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT
            )
        } else {
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        }
    }

    fun hasPermissions(context: Context): Boolean {
        val permissions = getRequiredPermissions()
        return permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun isBluetoothEnabled(context: Context): Boolean {
        return try {
            val manager = context.getSystemService(Context.BLUETOOTH_SERVICE) as? BluetoothManager
            manager?.adapter?.isEnabled == true
        } catch (e: Throwable) {
            false
        }
    }

    fun isLocationEnabled(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) return true
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? android.location.LocationManager
        return locationManager?.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER) == true ||
                locationManager?.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER) == true
    }

    fun hasNotificationPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            androidx.core.app.NotificationManagerCompat.from(context).areNotificationsEnabled()
        }
    }

    /**
     * Request notification permission on Android.
     * On Android 13+ (API 33+, including Android 14/15), Manifest.permission.POST_NOTIFICATIONS
     * is a runtime permission requiring the system permission dialog ("Allow <App> to send notifications?").
     * Requests via the hosting Activity using ActivityCompat.requestPermissions to reliably open the system dialog,
     * with fallback to ActivityResultLauncher and app notification settings if direct request is unavailable.
     */
    fun requestNotificationPermission(
        context: Context,
        launcher: ActivityResultLauncher<String>? = null
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val activity = findActivity(context)
            if (activity != null) {
                try {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        102
                    )
                    return
                } catch (e: Throwable) {
                    android.util.Log.e("BlePermissionUtils", "Error requesting POST_NOTIFICATIONS via Activity", e)
                }
            }

            if (launcher != null) {
                try {
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    return
                } catch (e: Throwable) {
                    android.util.Log.e("BlePermissionUtils", "Error requesting POST_NOTIFICATIONS via launcher", e)
                }
            }
        }

        // Fallback for Android < 13 or if direct runtime request dialog could not be invoked
        try {
            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                putExtra("android.provider.extra.APP_PACKAGE", context.packageName)
                putExtra("app_package", context.packageName)
                putExtra("app_uid", context.applicationInfo.uid)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (_: Throwable) {
            try {
                val appDetailsIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = android.net.Uri.fromParts("package", context.packageName, null)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(appDetailsIntent)
            } catch (_: Throwable) {}
        }
    }

    /**
     * Request enabling Bluetooth on Android.
     * On Android 13+ (API 33-35, including Android 15), BluetoothAdapter.enable() is blocked
     * by the OS for third-party non-system apps (requires BLUETOOTH_PRIVILEGED).
     * The official OS-supported approach is launching BluetoothAdapter.ACTION_REQUEST_ENABLE,
     * which prompts the system dialog asking the user to turn on Bluetooth.
     */
    fun requestEnableBluetooth(
        activity: Activity?,
        launcher: ActivityResultLauncher<Intent>?,
        onEnabling: () -> Unit
    ) {
        onEnabling()
        val realActivity = activity ?: findActivity(appContext)
        val context = realActivity ?: appContext

        // On older Android (< 33), attempt programmatic enable() first
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            try {
                @Suppress("DEPRECATION")
                val adapter = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
                @android.annotation.SuppressLint("MissingPermission")
                val success = adapter?.enable() == true
                if (success) return
            } catch (_: Throwable) {}
        }

        // On Android 13+ (or if deprecated enable() failed), launch system request dialog
        try {
            val intent = android.content.Intent(android.bluetooth.BluetoothAdapter.ACTION_REQUEST_ENABLE)
            if (launcher != null) {
                launcher.launch(intent)
            } else if (activity != null) {
                @Suppress("DEPRECATION")
                activity.startActivityForResult(intent, 1001)
            } else if (context != null) {
                intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        } catch (e: Throwable) {
            // Fallback to Bluetooth system settings
            try {
                val settingsIntent = android.content.Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS).apply {
                    addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                (activity ?: context)?.startActivity(settingsIntent)
            } catch (_: Throwable) {}
        }
    }

    /**
     * Converts a middle name string (single or multi-word, e.g. "Apple Bee" or "Apple B.")
     * into uppercase initials with trailing dots, e.g. "A.B.".
     */
    fun formatMiddleInitials(middleName: String): String {
        val trimmed = middleName.trim()
        if (trimmed.isBlank()) return ""

        val tokens = trimmed.split("[\\s\\-]+".toRegex()).filter { it.isNotBlank() }
        val result = StringBuilder()
        for (token in tokens) {
            val subTokens = token.split(".").filter { it.isNotBlank() }
            if (subTokens.size > 1) {
                for (sub in subTokens) {
                    val letter = sub.firstOrNull { it.isLetter() }
                    if (letter != null) {
                        result.append("${letter.uppercaseChar()}.")
                    }
                }
            } else {
                val letter = token.firstOrNull { it.isLetter() }
                if (letter != null) {
                    result.append("${letter.uppercaseChar()}.")
                }
            }
        }
        return result.toString()
    }

    /**
     * Formats teacher name from explicit components.
     * E.g.: ("Sir", "Teacher", "Apple Bee", "Test") -> "Sir Teacher A.B. Test"
     */
    fun formatTeacherName(
        honorific: String,
        firstName: String,
        middleName: String,
        surname: String
    ): String {
        val h = honorific.trim()
        val f = firstName.trim()
        val mi = formatMiddleInitials(middleName)
        val s = surname.trim()

        val nameWithMi = when {
            mi.isNotBlank() && f.isNotBlank() && s.isNotBlank() -> "$f $mi $s"
            f.isNotBlank() && s.isNotBlank() -> "$f $s"
            s.isNotBlank() -> s
            else -> f
        }

        return if (h.isNotBlank() && nameWithMi.isNotBlank()) "$h $nameWithMi" else nameWithMi.ifBlank { h }
    }

    /**
     * Formats teacher full name with all middle names initialized (e.g. "A.B.").
     * Examples:
     * - "Sir Teacher Apple Bee Test" -> "Sir Teacher A.B. Test"
     * - "Sir Teacher Apple B. Test" -> "Sir Teacher A.B. Test"
     * - "Sir Teacher A.B. Test" -> "Sir Teacher A.B. Test"
     * - "Teacher Apple Bee Test" -> "Teacher A.B. Test"
     * - "Sir Teacher Bee Test" -> "Sir Teacher B. Test"
     * - "Sir Teacher Test" -> "Sir Teacher Test"
     */
    fun formatTeacherNameWithMiddleInitial(fullName: String, context: Context? = null): String {
        val trimmed = fullName.trim()
        if (trimmed.isBlank()) return ""

        val effectiveContext = context ?: appContext
        if (effectiveContext != null) {
            try {
                val prefs = effectiveContext.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                val savedFirst = prefs.getString("teacher_first_name", null)?.trim()
                val savedMiddle = prefs.getString("teacher_middle_name", null)?.trim()
                val savedSurname = prefs.getString("teacher_surname", null)?.trim()
                val savedHonorific = prefs.getString("teacher_honorific", null)?.trim()

                if (!savedFirst.isNullOrBlank() && !savedSurname.isNullOrBlank()) {
                    val matchesSurname = trimmed.contains(savedSurname, ignoreCase = true)
                    val matchesFirst = trimmed.contains(savedFirst, ignoreCase = true)
                    if (matchesSurname && matchesFirst) {
                        return formatTeacherName(
                            honorific = savedHonorific ?: "",
                            firstName = savedFirst,
                            middleName = savedMiddle ?: "",
                            surname = savedSurname
                        )
                    }
                }
            } catch (_: Exception) {}
        }

        val parts = trimmed.split("\\s+".toRegex()).filter { it.isNotBlank() }
        if (parts.size <= 1) return trimmed

        val honorifics = setOf(
            "sir", "ma'am", "mr", "mr.", "ms", "ms.", "mrs", "mrs.",
            "dr", "dr.", "prof", "prof.", "bb", "bb.", "g", "g.", "gng", "gng."
        )
        val hasHonorific = parts[0].lowercase() in honorifics

        val honorific = if (hasHonorific) parts[0] else ""
        val nameParts = if (hasHonorific) parts.subList(1, parts.size) else parts
        if (nameParts.size <= 2) {
            val name = nameParts.joinToString(" ")
            return if (honorific.isNotBlank()) "$honorific $name" else name
        }

        val surname = nameParts.last()
        val firstAndMiddle = nameParts.subList(0, nameParts.size - 1)

        val firstName = firstAndMiddle[0]
        val middleTokens = firstAndMiddle.subList(1, firstAndMiddle.size)
        val middleInitials = formatMiddleInitials(middleTokens.joinToString(" "))

        val formattedName = if (middleInitials.isNotBlank()) {
            "$firstName $middleInitials $surname"
        } else {
            "$firstName $surname"
        }

        return if (honorific.isNotBlank()) {
            "$honorific $formattedName"
        } else {
            formattedName
        }
    }

    /**
     * Formats student full name with all middle names initialized (e.g. "A.B.").
     * Examples:
     * - "Juan Ponce Cruz" -> "Juan P. Cruz"
     * - "Juan P. Cruz" -> "Juan P. Cruz"
     * - "Juan Cruz" -> "Juan Cruz"
     */
    fun formatStudentNameWithMiddleInitial(fullName: String, context: Context? = null): String {
        val trimmed = fullName.trim()
        if (trimmed.isBlank()) return ""

        val effectiveContext = context ?: appContext
        if (effectiveContext != null) {
            try {
                val prefs = effectiveContext.getSharedPreferences("calis_auth_prefs", Context.MODE_PRIVATE)
                val savedFirst = prefs.getString("student_first_name", null)?.trim()
                val savedMiddle = prefs.getString("student_middle_name", null)?.trim()
                val savedSurname = prefs.getString("student_surname", null)?.trim()

                if (!savedFirst.isNullOrBlank() && !savedSurname.isNullOrBlank()) {
                    val matchesSurname = trimmed.contains(savedSurname, ignoreCase = true)
                    val matchesFirst = trimmed.contains(savedFirst, ignoreCase = true)
                    if (matchesSurname && matchesFirst) {
                        val mi = formatMiddleInitials(savedMiddle ?: "")
                        return if (mi.isNotBlank()) "$savedFirst $mi $savedSurname" else "$savedFirst $savedSurname"
                    }
                }
            } catch (_: Exception) {}
        }

        val parts = trimmed.split("\\s+".toRegex()).filter { it.isNotBlank() }
        if (parts.size <= 2) return trimmed

        val surname = parts.last()
        val firstAndMiddle = parts.subList(0, parts.size - 1)
        val firstName = firstAndMiddle[0]
        val middleTokens = firstAndMiddle.subList(1, firstAndMiddle.size)
        val middleInitials = formatMiddleInitials(middleTokens.joinToString(" "))

        return if (middleInitials.isNotBlank()) {
            "$firstName $middleInitials $surname"
        } else {
            "$firstName $surname"
        }
    }
}
