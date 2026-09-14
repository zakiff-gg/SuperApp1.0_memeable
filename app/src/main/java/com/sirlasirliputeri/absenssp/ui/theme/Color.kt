// File Path: app/src/main/java/com/sirlasirliputeri/absenssp/ui/theme/Color.kt
package com.sirlasirliputeri.absenssp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Menyimpan status mode gelap yang sedang aktif supaya token warna di bawah
 * bisa otomatis pilih varian terang/gelap TANPA mengubah cara pemakaiannya di
 * seluruh layar (semua sudah pakai "color = NavyDark" dsb -- tetap jalan apa
 * adanya karena sekarang jadi property @Composable, bukan konstanta statis).
 * Diisi lewat CompositionLocalProvider di AbsenSSPTheme (lihat Theme.kt).
 */
val LocalDarkTheme = staticCompositionLocalOf { false }

// ---------------- Warna Brand PT Sirla Sirli Puteri (Absen SSP) — mode Terang ----------------
private val NavyDarkLight = Color(0xFF0C1B3A)
private val RedAccentLight = Color(0xFFE4211F)
private val CreamBgLight = Color(0xFFF6F2EA)
private val BlueGrayLight = Color(0xFF2F4C8C)
private val GreenSuccessLight = Color(0xFF2E7D32)
private val OrangeWarningLight = Color(0xFFF57C00)
private val CardWhiteLight = Color(0xFFFFFFFF)
private val DividerGrayLight = Color(0xFFE3E0D8)
private val TextSecondaryLight = Color(0xFF8A8A8E)

// ---------------- Varian mode Gelap (kontras dibalik, tetap senada brand) ----------------
private val NavyDarkNight = Color(0xFFEDEFF5)      // dipakai sbg warna teks/ikon utama -> jadi terang di atas gelap
private val RedAccentNight = Color(0xFFFF6B6A)
private val CreamBgNight = Color(0xFF0B1220)       // latar utama jadi navy gelap pekat
private val BlueGrayNight = Color(0xFF7C9CE8)
private val GreenSuccessNight = Color(0xFF66BB6A)
private val OrangeWarningNight = Color(0xFFFFB74D)
private val CardWhiteNight = Color(0xFF151F35)     // kartu jadi navy sedikit lebih terang dari latar
private val DividerGrayNight = Color(0xFF2A3550)
private val TextSecondaryNight = Color(0xFF9AA3B8)

val NavyDark: Color @Composable get() = if (LocalDarkTheme.current) NavyDarkNight else NavyDarkLight
val RedAccent: Color @Composable get() = if (LocalDarkTheme.current) RedAccentNight else RedAccentLight
val CreamBg: Color @Composable get() = if (LocalDarkTheme.current) CreamBgNight else CreamBgLight
val BlueGray: Color @Composable get() = if (LocalDarkTheme.current) BlueGrayNight else BlueGrayLight
val GreenSuccess: Color @Composable get() = if (LocalDarkTheme.current) GreenSuccessNight else GreenSuccessLight
val OrangeWarning: Color @Composable get() = if (LocalDarkTheme.current) OrangeWarningNight else OrangeWarningLight
val CardWhite: Color @Composable get() = if (LocalDarkTheme.current) CardWhiteNight else CardWhiteLight
val DividerGray: Color @Composable get() = if (LocalDarkTheme.current) DividerGrayNight else DividerGrayLight
val TextSecondary: Color @Composable get() = if (LocalDarkTheme.current) TextSecondaryNight else TextSecondaryLight
