package com.example.tarisumutapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tarisumutapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onMateriClick: () -> Unit,
    onKuisClick: () -> Unit,
    onBelajarTariClick: () -> Unit,
    onVideoClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Motif
        Image(
            painter = painterResource(id = R.drawable.bg_card),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Pilih Mode Pembelajaran",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Kembali",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Card 1: Materi Tari
                MenuCard(
                    title = "Materi Tari",
                    imageResId = R.drawable.card_materi,
                    onClick = onMateriClick
                )

                // Card 2: Kuis & Evaluasi
                MenuCard(
                    title = "Kuis & Evaluasi",
                    imageResId = R.drawable.card_kuis,
                    onClick = onKuisClick
                )

                // Card 3: Video Tari
                MenuCard(
                    title = "Video Tari",
                    imageResId = R.drawable.card_video,
                    onClick = onVideoClick
                )
            }
        }
    }
}

// Komponen Card khusus Gambar Utuh
@Composable
fun MenuCard(
    title: String,
    imageResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            // CARA NGERAMPINGIN KARTU:
            .width(180.dp)  // Tinggal kecilkan angka ini (misal 160.dp atau 150.dp) kalau mau lebih RAMPING
            .height(280.dp) // Atur tingginya di sini
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}