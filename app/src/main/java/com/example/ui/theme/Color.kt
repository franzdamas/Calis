package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Calis Brand Theme Palette (Elegant Vibrant Light Blue / Sky Blue Theme)
val CalisPrimaryBlue = Color(0xFF0284C7)         // Sky Blue 600
val CalisPrimaryBlueLight = Color(0xFF38BDF8)    // Sky Blue 400
val CalisPrimaryContainer = Color(0xFFF0F9FF)    // Sky Blue 50
val CalisOnPrimaryContainer = Color(0xFF0369A1)  // Sky Blue 700

val CalisSecondaryTeal = Color(0xFF0D9488)       // Teal 600
val CalisSecondaryTealLight = Color(0xFF2DD4BF)  // Teal 400
val CalisSecondaryContainer = Color(0xFFF0FDFA)  // Teal 50
val CalisOnSecondaryContainer = Color(0xFF0F766E) // Teal 700

val CalisTertiaryTeal = Color(0xFF06B6D4)         // Cyber/Ocean Teal
val CalisTertiaryContainer = Color(0xFFECFEFF)
val CalisOnTertiaryContainer = Color(0xFF0891B2)

val CalisDarkBackground = Color(0xFF0F172A)
val CalisDarkSurface = Color(0xFF1E293B)
val CalisDarkCard = Color(0xFF334155)

val CalisLightBackground = Color(0xFFF8FAFC)
val CalisLightSurface = Color(0xFFFFFFFF)
val CalisLightCard = Color(0xFFF1F5F9)

val StatusPresentGreen = Color(0xFF0284C7) // Beautiful premium Sky Blue (replacing green)
val StatusLateAmber = Color(0xFFF59E0B)
val StatusAbsentRed = Color(0xFFEF4444)

// Gradient brushes for accents - in beautiful Light Blue / Sky Blue
val PrimaryAccentGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF0EA5E9), Color(0xFF38BDF8)) // Sky Blue Gradient (Light Blue)
)
val SecondaryAccentGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF06B6D4), Color(0xFF67E8F9)) // Cyan light gradient
)
val AccentGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF0EA5E9), Color(0xFF38BDF8)) // Beautiful vibrant Sky Blue (Light Blue) Gradient
)
