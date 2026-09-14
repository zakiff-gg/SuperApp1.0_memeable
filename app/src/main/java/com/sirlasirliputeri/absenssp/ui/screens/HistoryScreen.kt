// File Path: app/src/main/java/com/sirlasirliputeri/absenssp/ui/screens/HistoryScreen.kt
package com.sirlasirliputeri.absenssp.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sirlasirliputeri.absenssp.model.AbsensiRecord
import com.sirlasirliputeri.absenssp.ui.theme.*

/**
 * Menu Pencarian Riwayat & Agregasi Frekuensi kehadiran per karyawan.
 * Cukup cari berdasarkan Nama saja -- akan menampilkan seluruh riwayat absen tercatat.
 */
@Composable
fun HistoryScreen(
    isLoading: Boolean,
    hasilPencarian: List<AbsensiRecord>,
    totalKehadiran: Int,
    onCari: (namaKaryawan: String) -> Unit,
    onBack: () -> Unit
) {
    var nama by remember { mutableStateOf("") }
    var sudahPernahCari by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().background(CreamBg)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onBack) { Text("‹ Kembali", color = BlueGray) }
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text("Riwayat & Pencarian", style = MaterialTheme.typography.headlineLarge, color = NavyDark)
            Spacer(Modifier.height(16.dp))

            // Search bar ala iOS: pill rounded, ikon kaca pembesar
            OutlinedTextField(
                value = nama, onValueChange = { nama = it },
                placeholder = { Text("Cari nama karyawan") },
                leadingIcon = { Text("🔍") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = CardWhite,
                    focusedContainerColor = CardWhite,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(50),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            Button(
                onClick = { sudahPernahCari = true; onCari(nama) },
                enabled = !isLoading && nama.isNotBlank(),
                shape = RoundedCornerShape(50),
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                if (isLoading) CircularProgressIndicator(modifier = Modifier.size(18.dp), color = CardWhite)
                else Text("Cari", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(20.dp))

            if (hasilPencarian.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total Kehadiran", style = MaterialTheme.typography.labelMedium, color = TextSecondary)
                    Text("$totalKehadiran Kali Masuk", style = MaterialTheme.typography.labelLarge, color = NavyDark, fontWeight = FontWeight.SemiBold)
                }
                Spacer(Modifier.height(10.dp))
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            if (isLoading) {
                items(4) { SkeletonRiwayatCard() }
            } else if (hasilPencarian.isEmpty()) {
                item { EmptyStateRiwayat(sudahPernahCari = sudahPernahCari) }
            } else {
                items(hasilPencarian) { record ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = CardWhite),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("${record.tanggal} · ${record.jam}", style = MaterialTheme.typography.labelLarge, color = NavyDark)
                                Text(record.jenis, style = MaterialTheme.typography.labelMedium, color = BlueGray, fontWeight = FontWeight.SemiBold)
                            }
                            Spacer(Modifier.height(4.dp))
                            Text("Proyek: ${record.proyek}", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                            if (record.keterangan.isNotEmpty()) {
                                Text(record.keterangan, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                            }
                        }
                    }
                }
            }
        }
    }
}

/** Placeholder shimmer -- dipakai selagi menunggu hasil pencarian dari server, jauh lebih
 *  terasa "hidup" & modern dibanding spinner polos di tengah layar kosong. */
@Composable
private fun SkeletonRiwayatCard() {
    val infiniteTransition = rememberInfiniteTransition(label = "skeleton_shimmer")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(animation = tween(700, easing = LinearEasing), repeatMode = RepeatMode.Reverse),
        label = "skeleton_alpha"
    )
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Box(modifier = Modifier.fillMaxWidth(0.5f).height(14.dp).clip(RoundedCornerShape(6.dp)).background(DividerGray.copy(alpha = alpha)))
            Spacer(Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth(0.75f).height(12.dp).clip(RoundedCornerShape(6.dp)).background(DividerGray.copy(alpha = alpha)))
        }
    }
}

/** Empty state yang lebih ramah -- pesan beda antara "belum pernah cari" vs "sudah cari tapi kosong". */
@Composable
private fun EmptyStateRiwayat(sudahPernahCari: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 40.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(60.dp).clip(CircleShape).background(NavyDark.copy(alpha = 0.06f)),
            contentAlignment = Alignment.Center
        ) {
            Text(if (sudahPernahCari) "🔎" else "👋", fontSize = 26.sp)
        }
        Spacer(Modifier.height(14.dp))
        if (sudahPernahCari) {
            Text("Tidak Ditemukan", style = MaterialTheme.typography.titleLarge, color = NavyDark, textAlign = TextAlign.Center)
            Spacer(Modifier.height(4.dp))
            Text("Coba periksa lagi ejaan namanya, atau pastikan sudah Sinkronisasi Data.", style = MaterialTheme.typography.bodyMedium, color = TextSecondary, textAlign = TextAlign.Center)
        } else {
            Text("Cari Riwayat Kehadiran", style = MaterialTheme.typography.titleLarge, color = NavyDark, textAlign = TextAlign.Center)
            Spacer(Modifier.height(4.dp))
            Text("Ketik nama karyawan di atas, lalu ketuk Cari.", style = MaterialTheme.typography.bodyMedium, color = TextSecondary, textAlign = TextAlign.Center)
        }
    }
}
