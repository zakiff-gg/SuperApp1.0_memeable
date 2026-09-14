// File Path: app/src/main/java/com/sirlasirliputeri/absenssp/ui/theme/Type.kt
package com.sirlasirliputeri.absenssp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Skala tipografi terinspirasi iOS Human Interface Guidelines (Large Title / Headline /
// Body / Footnote / Caption) -- tetap pakai font sistem default, bukan SF Pro asli,
// tapi ukuran & bobotnya dibuat senada dengan gaya iOS.
val AbsenTypography = Typography(
    displayLarge = TextStyle(fontWeight = FontWeight.Bold, fontSize = 34.sp, letterSpacing = (-0.4).sp), // Large Title
    headlineLarge = TextStyle(fontWeight = FontWeight.Bold, fontSize = 28.sp, letterSpacing = (-0.3).sp),
    headlineMedium = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 22.sp, letterSpacing = (-0.2).sp),
    titleLarge = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 17.sp),
    bodyLarge = TextStyle(fontWeight = FontWeight.Normal, fontSize = 17.sp),
    bodyMedium = TextStyle(fontWeight = FontWeight.Normal, fontSize = 15.sp),
    labelLarge = TextStyle(fontWeight = FontWeight.Medium, fontSize = 15.sp),
    labelMedium = TextStyle(fontWeight = FontWeight.Medium, fontSize = 13.sp, letterSpacing = 0.2.sp), // Section header ala iOS
    bodySmall = TextStyle(fontWeight = FontWeight.Normal, fontSize = 13.sp)
)
