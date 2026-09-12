package com.example.tarisumutapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tarisumutapp.R

@Composable
fun MenuUtamaScreen(
    isMuted: Boolean,
    onToggleAudio: () -> Unit,
    onPlayClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    var showPengaturanDialog by remember { mutableStateOf(false) }
    var showTentangDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // 1. Gambar Latar Belakang Utama
        Image(
            painter = painterResource(id = R.drawable.bg_utama),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 2. Tombol Musik Audio (Pojok Kanan Atas)
        IconButton(
            onClick = onToggleAudio, // Menggunakan callback dari MainActivity
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
        ) {
            Surface(
                shape = MaterialTheme.shapes.small,
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = if (isMuted) Icons.Default.Close else Icons.Default.PlayArrow,
                        contentDescription = "Toggle Audio",
                        tint = if (isMuted) Color.Red else Color(0xFFFFD700)
                    )
                }
            }
        }

        // 3. Konten Layar (Tengah)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Tari Tradisional Sumut",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onPlayClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4AF37)), // Warna Emas
                modifier = Modifier.width(200.dp)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.Black)
                Spacer(modifier = Modifier.width(8.dp))
                Text("PLAY / MAIN", color = Color.Black, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = { showPengaturanDialog = true },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) {
                    Icon(Icons.Default.Settings, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Pengaturan")
                }

                OutlinedButton(
                    onClick = { showTentangDialog = true },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) {
                    Icon(Icons.Default.Info, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Tentang")
                }
            }
        }
    }

    // Dialog Popup
    if (showPengaturanDialog) {
        AlertDialog(
            onDismissRequest = { showPengaturanDialog = false },
            title = { Text("Pengaturan") },
            text = { Text("• Orientasi: Landscape Fix\n• Mode: Edukasi Interaktif") },
            confirmButton = {
                TextButton(onClick = { showPengaturanDialog = false }) { Text("Tutup") }
            }
        )
    }

    if (showTentangDialog) {
        AlertDialog(
            onDismissRequest = { showTentangDialog = false },
            title = { Text("Tentang Aplikasi") },
            text = { Text("Aplikasi Edukasi Tari Tradisional Sumatera Utara v1.0.0") },
            confirmButton = {
                TextButton(onClick = { showTentangDialog = false }) { Text("Tutup") }
            }
        )
    }
}