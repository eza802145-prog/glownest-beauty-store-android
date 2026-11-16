package com.glownest.beauty.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object GlowNestColors {
    val CreamCanvas = Color(0xFFFCF9F6)
    val CardWhite = Color(0xFFFFFFFF)
    val BlushSurface = Color(0xFFF7EFF1)
    val BorderRoseSand = Color(0xFFEADCE0)
    val SubtleDivider = Color(0xFFF3E8EC)
    val RoseGoldAccent = Color(0xFFC78D7E)
    val PrimaryBlushRose = Color(0xFF9F3B58)
    val PrimaryHover = Color(0xFF862E48)
    val TextPrimary = Color(0xFF24191C)
    val TextSecondary = Color(0xFF5C494F)
    val TextMuted = Color(0xFF6E5A60)
    val PlaceholderText = Color(0xFF8E7A80)
    val DarkButton = Color(0xFF24191C)
}

private val GlowNestLightColorScheme = lightColorScheme(
    primary = GlowNestColors.PrimaryBlushRose,
    onPrimary = Color.White,
    primaryContainer = GlowNestColors.BlushSurface,
    onPrimaryContainer = GlowNestColors.PrimaryBlushRose,
    secondary = GlowNestColors.RoseGoldAccent,
    onSecondary = Color.White,
    background = GlowNestColors.CreamCanvas,
    onBackground = GlowNestColors.TextPrimary,
    surface = GlowNestColors.CardWhite,
    onSurface = GlowNestColors.TextPrimary,
    surfaceVariant = GlowNestColors.BlushSurface,
    onSurfaceVariant = GlowNestColors.TextSecondary,
    outline = GlowNestColors.BorderRoseSand
)

val GlowNestTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.5).sp,
        color = GlowNestColors.TextPrimary
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp,
        lineHeight = 36.sp,
        color = GlowNestColors.TextPrimary
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 30.sp,
        color = GlowNestColors.TextPrimary
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        color = GlowNestColors.TextPrimary
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        color = GlowNestColors.TextPrimary
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp,
        color = GlowNestColors.TextSecondary
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = GlowNestColors.TextSecondary
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 17.sp,
        color = GlowNestColors.TextMuted
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 18.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)

@Composable
fun GlowNestTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = GlowNestLightColorScheme,
        typography = GlowNestTypography,
        content = content
    )
}
