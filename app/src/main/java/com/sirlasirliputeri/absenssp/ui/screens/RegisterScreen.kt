// File Path: app/src/main/java/com/sirlasirliputeri/absenssp/ui/screens/RegisterScreen.kt
package com.sirlasirliputeri.absenssp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sirlasirliputeri.absenssp.ui.theme.*

/**
 * Menu "Rekam Kartu Baru" -- form ala iOS (kartu putih rounded, field tanpa border tebal).
 */
@Composable
fun RegisterScreen(
    uidTerbaca: String,
    isSubmitting: Boolean,
    onSubmit: (nama: String, tempatKerja: String, posisi: String) -> Unit,
    onBack: () -> Unit
) {
    var nama by remember { mutableStateOf("") }
    var tempatKerja by remember { mutableStateOf("") }
    var posisi by remember { mutableStateOf("") }

    val fieldColors = TextFieldDefaults.colors(
        unfocusedContainerColor = CardWhite,
        focusedContainerColor = CardWhite,
        unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
        focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
    )

    Column(modifier = Modifier.fillMaxSize().background(CreamBg)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onBack) { Text("‹ Kembali", color = BlueGray) }
        }

        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
            Text("Rekam Kartu Baru", style = MaterialTheme.typography.headlineLarge, color = NavyDark)
            Spacer(Modifier.height(4.dp))
            Text(
                "Tempelkan kartu untuk mengisi UID otomatis",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
            Spacer(Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(CardWhite)
                    .padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                OutlinedTextField(
                    value = uidTerbaca,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("UID Kartu") },
                    colors = fieldColors,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = nama, onValueChange = { nama = it },
                    label = { Text("Nama Karyawan") },
                    colors = fieldColors,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = tempatKerja, onValueChange = { tempatKerja = it },
                    label = { Text("Tempat Kerja") },
                    colors = fieldColors,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = posisi, onValueChange = { posisi = it },
                    label = { Text("Posisi / Jabatan") },
                    colors = fieldColors,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = { onSubmit(nama, tempatKerja, posisi) },
                enabled = !isSubmitting && uidTerbaca.isNotEmpty() && nama.isNotBlank(),
                shape = RoundedCornerShape(50),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) {
                if (isSubmitting) CircularProgressIndicator(modifier = Modifier.size(18.dp), color = CardWhite)
                else Text("Simpan Data Karyawan", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(12.dp))
            Text(
                "Jika UID sudah terdaftar, data lama otomatis dipindahkan ke Riwayat Karyawan sebelum data baru disimpan.",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
    }
}

/**
 * Popup hasil pendaftaran kartu (berhasil / gagal) -- selalu disertai bunyi (lihat
 * pemanggilan SoundHelper di MainActivity saat dialog ini dimunculkan).
 */
@Composable
fun RegisterResultDialog(
    isSukses: Boolean,
    pesan: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(24.dp),
        title = {
            Text(
                if (isSukses) "Berhasil Didaftarkan" else "Gagal Mendaftarkan",
                color = if (isSukses) GreenSuccess else RedAccent,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = { Text(pesan, color = NavyDark) },
        confirmButton = { TextButton(onClick = onDismiss) { Text("OK") } }
    )
}
