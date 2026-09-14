package com.example.tarisumutapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarisumutapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onMateriClick: () -> Unit,
    onKuisClick: () -> Unit,
    onBelajarTariClick: () -> Unit,
    onVideoClick: () -> Unit,
    onGlosariumClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Latar Belakang Utama
        Image(
            painter = painterResource(id = R.drawable.bg_konten),
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
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
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
            // Tata Letak Kartu Rata & Presisi
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Kartu 1: Materi
                MenuImageCard(
                    imageResId = R.drawable.card_materi, // Pastikan sesuai nama gambar kamu
                    contentDescription = "Materi",
                    onClick = onMateriClick,
                    modifier = Modifier.weight(1f)
                )

                // Kartu 2: Kuis
                MenuImageCard(
                    imageResId = R.drawable.card_kuis, // Pastikan sesuai nama gambar kamu
                    contentDescription = "Kuis",
                    onClick = onKuisClick,
                    modifier = Modifier.weight(1f)
                )

                // Kartu 3: Video
                MenuImageCard(
                    imageResId = R.drawable.card_video, // Pastikan sesuai nama gambar kamu
                    contentDescription = "Video",
                    onClick = onVideoClick,
                    modifier = Modifier.weight(1f)
                )

                // Kartu 4: Glosarium
                MenuImageCard(
                    imageResId = R.drawable.card_video, // Pastikan sesuai nama gambar kamu
                    contentDescription = "Glosarium",
                    onClick = onGlosariumClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

// Komponen Gambar Kartu (Tanpa Text Tambahan & Tanpa Terpotong)
@Composable
fun MenuImageCard(
    imageResId: Int,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit, // Menggunakan Fit agar seluruh bingkai kartu terlihat utuh tanpa terpotong
        modifier = modifier
            .fillMaxHeight(0.9f) // Mengatur tinggi proporsional agar pas di layar
            .clickable { onClick() }
    )
}