// File Path: app/src/main/java/com/sirlasirliputeri/absenssp/ui/theme/Theme.kt
package com.sirlasirliputeri.absenssp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightScheme = lightColorScheme(
    primary = Color(0xFF0C1B3A),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF2F4C8C),
    onSecondary = Color(0xFFFFFFFF),
    error = Color(0xFFE4211F),
    background = Color(0xFFF6F2EA),
    onBackground = Color(0xFF0C1B3A),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0C1B3A),
    surfaceVariant = Color(0xFFF6F2EA),
    outline = Color(0xFFE3E0D8)
)

private val DarkScheme = darkColorScheme(
    primary = Color(0xFFEDEFF5),
    onPrimary = Color(0xFF0B1220),
    secondary = Color(0xFF7C9CE8),
    onSecondary = Color(0xFF0B1220),
    error = Color(0xFFFF6B6A),
    background = Color(0xFF0B1220),
    onBackground = Color(0xFFEDEFF5),
    surface = Color(0xFF151F35),
    onSurface = Color(0xFFEDEFF5),
    surfaceVariant = Color(0xFF0B1220),
    outline = Color(0xFF2A3550)
)

// Sudut membulat ala iOS (kartu, dialog, tombol, text field) -- iOS umumnya memakai
// radius 10-14pt untuk kartu/list dan radius penuh (stadium) untuk tombol utama.
private val AbsenShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

/**
 * @param darkTheme true = paksa gelap, false = paksa terang. Default ikut sistem HP.
 * Semua layar pakai token warna dari Color.kt (NavyDark, CreamBg, dst) yang otomatis
 * menyesuaikan lewat LocalDarkTheme -- tidak perlu ubah kode di masing-masing layar.
 */
/**
 * Ubah nilai preferensi tersimpan ("system"/"light"/"dark") jadi boolean final
 * yang dipakai AbsenSSPTheme -- kalau "system", ikut pengaturan HP saat ini.
 */
@Composable
fun resolveDarkTheme(pref: String): Boolean = when (pref) {
    "dark" -> true
    "light" -> false
    else -> isSystemInDarkTheme()
}

@Composable
fun AbsenSSPTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalDarkTheme provides darkTheme) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkScheme else LightScheme,
            typography = AbsenTypography,
            shapes = AbsenShapes,
            content = content
        )
    }
}
