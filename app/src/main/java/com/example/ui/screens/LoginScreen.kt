package com.example.ui.screens

import android.content.pm.PackageManager
import android.content.Context
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CalisMobileTheme
import com.example.ui.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    onSwitchAccountRequested: () -> Unit
) {
    CalisMobileTheme(darkTheme = false) {
        val context = LocalContext.current
        val savedPin by authViewModel.savedPin.collectAsState()
        val enableBiometrics by authViewModel.enableBiometrics.collectAsState()
        val userRole by authViewModel.userRole.collectAsState()
        val currentTeacher by authViewModel.currentTeacher.collectAsState()
        val currentStudentProfile by authViewModel.currentStudentProfile.collectAsState()

        val accountName = remember(userRole, currentTeacher, currentStudentProfile) {
            if (userRole == "STUDENT") {
                val raw = currentStudentProfile?.fullName ?: "Student Account"
                com.example.util.BlePermissionUtils.formatStudentNameWithMiddleInitial(raw, context).ifBlank { "Student Account" }
            } else if (userRole == "TEACHER") {
                val raw = currentTeacher?.fullName ?: "Teacher Account"
                com.example.util.BlePermissionUtils.formatTeacherNameWithMiddleInitial(raw, context).ifBlank { "Teacher Account" }
            } else {
                currentStudentProfile?.fullName ?: currentTeacher?.fullName ?: "Calis User Account"
            }
        }

        var pinCode by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        val supportsBiometrics = remember(context) {
            val biometricManager = BiometricManager.from(context)
            val authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK
            val canAuth = biometricManager.canAuthenticate(authenticators)
            canAuth == BiometricManager.BIOMETRIC_SUCCESS ||
                    canAuth == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ||
                    context.packageManager.hasSystemFeature(android.content.pm.PackageManager.FEATURE_FINGERPRINT) ||
                    context.packageManager.hasSystemFeature("android.hardware.biometrics.face") ||
                    context.packageManager.hasSystemFeature("android.hardware.biometrics.iris")
        }

        fun verifyPin(entered: String) {
            if (entered == savedPin || savedPin.isEmpty()) {
                errorMessage = null
                authViewModel.setLoggedIn(true)
                onLoginSuccess()
            } else {
                errorMessage = "Incorrect 6-Digit PIN. Please try again."
                pinCode = ""
            }
        }

        fun Context.findActivity(): FragmentActivity? {
            var currentContext = this
            while (currentContext is android.content.ContextWrapper) {
                if (currentContext is FragmentActivity) return currentContext
                currentContext = currentContext.baseContext
            }
            return null
        }

        fun triggerBiometricLogin() {
            val activity = context.findActivity()
            if (activity != null) {
                val executor = ContextCompat.getMainExecutor(activity)
                val biometricPrompt = BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        authViewModel.setLoggedIn(true)
                        Toast.makeText(context, "Biometric authentication successful!", Toast.LENGTH_SHORT).show()
                        onLoginSuccess()
                    }

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        if (errorCode != BiometricPrompt.ERROR_USER_CANCELED && errorCode != BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                            Toast.makeText(context, "Biometric Auth: $errString", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Toast.makeText(context, "Biometric authentication failed", Toast.LENGTH_SHORT).show()
                    }
                })

                val promptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Calis Biometric Login")
                    .setSubtitle("Log in using your fingerprint or face")
                    .setNegativeButtonText("Use PIN")
                    .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK)
                    .build()

                try {
                    biometricPrompt.authenticate(promptInfo)
                } catch (e: Exception) {
                    Toast.makeText(context, "Failed to start biometrics: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Biometrics not available in this context.", Toast.LENGTH_SHORT).show()
            }
        }

        // Auto-trigger biometrics on login screen launch if enabled
        androidx.compose.runtime.LaunchedEffect(enableBiometrics, supportsBiometrics) {
            if (enableBiometrics && supportsBiometrics) {
                triggerBiometricLogin()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Center Content
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = accountName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Enter 6-digit PIN to unlock",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                if (errorMessage != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = errorMessage!!,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 6-Digit Dots Indicator
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 0 until 6) {
                        val isFilled = i < pinCode.length
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isFilled) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.surfaceVariant
                                )
                                .border(
                                    width = 1.5.dp,
                                    color = if (isFilled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                    shape = CircleShape
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 3x4 Number Pad
                NumberPadLogin(
                    onDigit = { digit ->
                        if (pinCode.length < 6) {
                            val newPin = pinCode + digit
                            pinCode = newPin
                            if (newPin.length == 6) {
                                verifyPin(newPin)
                            }
                        }
                    },
                    onBackspace = {
                        if (pinCode.isNotEmpty()) {
                            pinCode = pinCode.dropLast(1)
                            errorMessage = null
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Biometrics Option if enabled
                if (enableBiometrics && supportsBiometrics) {
                    OutlinedButton(
                        onClick = { triggerBiometricLogin() },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.testTag("login_biometric_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Fingerprint,
                            contentDescription = "Login with Biometrics",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Log in with Biometrics",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Bottom switch account option removed as requested
        }
    }
}

@Composable
private fun NumberPadLogin(
    onDigit: (String) -> Unit,
    onBackspace: () -> Unit
) {
    val buttons = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("", "0", "DEL")
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttons.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { item ->
                    if (item.isEmpty()) {
                        Spacer(modifier = Modifier.size(72.dp))
                    } else if (item == "DEL") {
                        IconButton(
                            onClick = onBackspace,
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Backspace,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
                                .clickable { onDigit(item) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
        }
    }
}
