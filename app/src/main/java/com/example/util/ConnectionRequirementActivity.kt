package com.example.util

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.ble.TeacherBleManager
import com.example.ble.StudentBleManager
import com.example.ble.CalisBleService
import com.example.data.db.CalisDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConnectionRequirementActivity : ComponentActivity() {

    private val reqPermissionsCode = 1001
    private var classroomId: String = ""

    private val enableBtLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK || BlePermissionUtils.isBluetoothEnabled(this)) {
            evaluateNextRequirement()
        } else {
            // Student cancelled Bluetooth enable prompt; finish activity and ensure Check-In notification is posted
            ReminderNotificationHelper.checkAndPostCheckInReminder(this)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        classroomId = intent.getStringExtra("classroom_id") ?: ""
        evaluateNextRequirement()
    }

    private fun evaluateNextRequirement() {
        val hasPermissions = BlePermissionUtils.hasPermissions(this)
        val isBtEnabled = BlePermissionUtils.isBluetoothEnabled(this)
        val isLocEnabled = BlePermissionUtils.isLocationEnabled(this)
        val isOlderThanAndroid12 = Build.VERSION.SDK_INT < Build.VERSION_CODES.S

        if (!hasPermissions) {
            ActivityCompat.requestPermissions(
                this,
                BlePermissionUtils.getRequiredPermissions(),
                reqPermissionsCode
            )
        } else if (!isBtEnabled) {
            try {
                @Suppress("DEPRECATION")
                val adapter = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
                @android.annotation.SuppressLint("MissingPermission")
                val enabled = adapter?.enable() == true
                if (enabled || BlePermissionUtils.isBluetoothEnabled(this)) {
                    evaluateNextRequirement()
                    return
                }
            } catch (_: Throwable) {
                // Ignore failure
            }

            try {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                enableBtLauncher.launch(enableBtIntent)
            } catch (e: Exception) {
                if (BlePermissionUtils.isBluetoothEnabled(this)) {
                    evaluateNextRequirement()
                } else {
                    ReminderNotificationHelper.checkAndPostCheckInReminder(this)
                    finish()
                }
            }
        } else if (isOlderThanAndroid12 && !isLocEnabled) {
            try {
                val locIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                locIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(locIntent)
            } catch (e: Exception) {
                // Ignore
            }
            ReminderNotificationHelper.checkAndPostCheckInReminder(this)
            finish()
        } else {
            // All connection requirements granted and enabled!
            onAllRequirementsGranted()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == reqPermissionsCode) {
            if (BlePermissionUtils.hasPermissions(this)) {
                // Granted! Proceed to next connection requirement (Bluetooth / Location)
                evaluateNextRequirement()
            } else {
                // Permission denied by student; finish activity and ensure Check-In notification is posted
                ReminderNotificationHelper.checkAndPostCheckInReminder(this)
                finish()
            }
        }
    }

    private fun onAllRequirementsGranted() {
        val targetCid = classroomId
        val todayDateStr = java.text.SimpleDateFormat("MM/dd/yyyy", java.util.Locale.getDefault()).format(java.util.Date())
        val prefs = getSharedPreferences("calis_auth_prefs", android.content.Context.MODE_PRIVATE)
        val userRole = prefs.getString("user_role", null)
        val isAlreadyCheckedIn = (targetCid.isNotBlank() && prefs.getBoolean("checked_in_today_${targetCid}_$todayDateStr", false)) ||
                prefs.getBoolean("checked_in_today_global_$todayDateStr", false)

        if (!isAlreadyCheckedIn) {
            if (targetCid.isNotBlank()) {
                ReminderNotificationHelper.updateNotificationToFindingCalisDevice(this, targetCid)
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val db = CalisDatabase.getDatabase(applicationContext)
                    val classroom = db.classroomDao().getActiveBoundClassroomSync()
                        ?: db.classroomDao().getAnyClassroomSync()
                    val cid = classroom?.cid ?: ""
                    val isCheckedInClassroom = prefs.getBoolean("checked_in_today_${cid}_$todayDateStr", false)
                    if (cid.isNotBlank() && !isCheckedInClassroom) {
                        ReminderNotificationHelper.updateNotificationToFindingCalisDevice(applicationContext, cid)
                    }
                }
            }
        }

        // Start background BLE service and scanning according to role
        try {
            if (userRole == "STUDENT") {
                com.example.ble.StudentBleService.startService(applicationContext)
                StudentBleManager.getInstance(applicationContext).startScan()
            } else if (userRole == "TEACHER") {
                CalisBleService.updateNotification(applicationContext, immediate = true)
                TeacherBleManager.getInstance(applicationContext).startScan()
            }
        } catch (e: Exception) {
            // Ignore
        }

        ReminderNotificationHelper.checkAndPostCheckInReminder(applicationContext)
        finish()
    }
}
