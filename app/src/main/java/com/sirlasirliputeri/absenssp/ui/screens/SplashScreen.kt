// File Path: app/src/main/java/com/sirlasirliputeri/absenssp/ui/screens/SplashScreen.kt
package com.sirlasirliputeri.absenssp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sirlasirliputeri.absenssp.R
import com.sirlasirliputeri.absenssp.ui.theme.CreamBg
import com.sirlasirliputeri.absenssp.ui.theme.NavyDark
import com.sirlasirliputeri.absenssp.ui.theme.TextSecondary

/** Ditampilkan singkat saat app baru dibuka (cold start), sebelum masuk ke layar utama. */
@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(CreamBg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_brand),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.height(64.dp).width(132.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text("SSP Super App", style = MaterialTheme.typography.titleLarge, color = NavyDark, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(4.dp))
        Text("PT Sirla Sirli Puteri", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
    }
}
