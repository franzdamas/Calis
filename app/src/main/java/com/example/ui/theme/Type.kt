package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.R

val OutfitFontFamily = FontFamily(
    Font(R.font.outfit_light, FontWeight.Light),
    Font(R.font.outfit_regular, FontWeight.Normal),
    Font(R.font.outfit_medium, FontWeight.Medium),
    Font(R.font.outfit_semibold, FontWeight.SemiBold),
    Font(R.font.outfit_bold, FontWeight.Bold),
    Font(R.font.outfit_extrabold, FontWeight.ExtraBold)
)

val NunitoFontFamily = OutfitFontFamily

private val defaultTypography = Typography()

// Set of Material typography styles configured with the Outfit font family
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Bold),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Bold),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Bold),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Bold),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Bold),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Bold),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.SemiBold),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.SemiBold),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.SemiBold),
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Normal),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Normal),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Normal),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Medium),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Medium),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = OutfitFontFamily, fontWeight = FontWeight.Medium)
)

