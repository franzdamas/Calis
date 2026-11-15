package com.example.ui.theme

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.core.view.WindowCompat

enum class AppThemeMode {
    LIGHT,
    DARK,
    SYSTEM
}

object ThemeConfig {
    var themeMode by mutableStateOf(AppThemeMode.LIGHT)
}

private val DarkColorScheme = darkColorScheme(
    primary = CalisPrimaryBlueLight,
    onPrimary = Color(0xFF075985),
    primaryContainer = Color(0xFF0369A1),
    onPrimaryContainer = CalisPrimaryContainer,
    secondary = CalisPrimaryBlueLight,
    secondaryContainer = Color(0xFF0369A1),
    onSecondaryContainer = CalisPrimaryContainer,
    tertiary = CalisTertiaryTeal,
    background = CalisDarkBackground,
    surface = CalisDarkSurface,
    surfaceVariant = CalisDarkCard,
    onBackground = Color(0xFFF8FAFC),
    onSurface = Color(0xFFF8FAFC),
    onSurfaceVariant = Color(0xFF94A3B8)
)

private val LightColorScheme = lightColorScheme(
    primary = CalisPrimaryBlue,
    onPrimary = Color.White,
    primaryContainer = CalisPrimaryContainer,
    onPrimaryContainer = CalisOnPrimaryContainer,
    secondary = CalisPrimaryBlue,
    onSecondary = Color.White,
    secondaryContainer = CalisPrimaryContainer,
    onSecondaryContainer = CalisOnPrimaryContainer,
    tertiary = CalisTertiaryTeal,
    tertiaryContainer = CalisTertiaryContainer,
    onTertiaryContainer = CalisOnTertiaryContainer,
    background = CalisLightBackground,
    surface = Color.White,
    surfaceDim = Color(0xFFF8FAFC),
    surfaceBright = Color.White,
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color.White,
    surfaceContainer = Color.White,
    surfaceContainerHigh = Color.White,
    surfaceContainerHighest = Color(0xFFF1F5F9),
    surfaceVariant = Color(0xFFF1F5F9),
    onBackground = Color(0xFF0F172A),
    onSurface = Color(0xFF0F172A),
    onSurfaceVariant = Color(0xFF475569),
    surfaceTint = Color.Transparent
)

@Composable
fun CalisMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                val controller = WindowCompat.getInsetsController(window, view)
                controller.isAppearanceLightStatusBars = !darkTheme
                controller.isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    val currentDensity = LocalDensity.current
    val currentConfig = LocalConfiguration.current
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val minDimensionPx = minOf(displayMetrics.widthPixels, displayMetrics.heightPixels).toFloat()

    // Detect if device is a tablet (smallest width >= 600dp)
    val isTablet = currentConfig.smallestScreenWidthDp >= 600
    val tabletScaleFactor = if (isTablet) 1.25f else 1.0f // Scale up by 25% on tablets for larger UI

    // Standard phone baseline has screen width of ~392dp to 411dp.
    // When the user sets Display Size to Large/Largest or enlarges text scale in settings,
    // density increases and screenWidthDp shrinks, causing UI elements to overset or overflow.
    // We strictly clamp the maximum density to guarantee effective width is at least 392dp,
    // and clamp fontScale to at most 1.0f (preventing text overset while keeping everything proportional).
    val maxAllowedDensity = (minDimensionPx / 392f).coerceAtLeast(1.0f)
    val baseDensity = currentDensity.density.coerceAtMost(maxAllowedDensity)
    val baseFontScale = currentDensity.fontScale.coerceIn(0.85f, 1.0f)

    val clampedDensity = baseDensity * tabletScaleFactor
    val clampedFontScale = baseFontScale * tabletScaleFactor

    val constrainedDensity = Density(
        density = clampedDensity,
        fontScale = clampedFontScale
    )

    val constrainedConfig = remember(currentConfig, clampedDensity, clampedFontScale, tabletScaleFactor) {
        Configuration(currentConfig).apply {
            fontScale = clampedFontScale
            densityDpi = (clampedDensity * 160f).toInt()
            screenWidthDp = (currentConfig.screenWidthDp / tabletScaleFactor).toInt()
            screenHeightDp = (currentConfig.screenHeightDp / tabletScaleFactor).toInt()
        }
    }

    CompositionLocalProvider(
        LocalDensity provides constrainedDensity,
        LocalConfiguration provides constrainedConfig
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = ModernShapes
        ) {
            CompositionLocalProvider(
                LocalContentColor provides colorScheme.onSurface,
                content = content
            )
        }
    }
}

@Composable
fun calisTextFieldColors(
    containerColor: Color = MaterialTheme.colorScheme.surface
): TextFieldColors = OutlinedTextFieldDefaults.colors(
    focusedTextColor = MaterialTheme.colorScheme.onSurface,
    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
    focusedLabelColor = MaterialTheme.colorScheme.primary,
    unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
    focusedSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
    unfocusedSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
    focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
    focusedContainerColor = containerColor,
    unfocusedContainerColor = containerColor,
    focusedBorderColor = MaterialTheme.colorScheme.primary,
    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
    cursorColor = MaterialTheme.colorScheme.primary
)

