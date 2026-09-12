package com.example.tarisumutapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tarisumutapp.R
import com.example.tarisumutapp.data.model.Tari
import com.example.tarisumutapp.ui.components.TariCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    listTari: List<Tari>,
    onTariClick: (Tari) -> Unit,
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
                            "Materi Tari Tradisional",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
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
        ) { paddingValues ->
            // Grid Vertikal: Mengunci 4 Kolom Ke Samping, Scroll Ke Bawah
            LazyVerticalGrid(
                columns = GridCells.Fixed(4), // 4 Kartu sebaris ke samping
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp), // Jarak horizontal antar kartu
                verticalArrangement = Arrangement.spacedBy(12.dp)    // Jarak vertikal antar baris
            ) {
                items(listTari) { tari ->
                    TariCard(
                        tari = tari,
                        onClick = { onTariClick(tari) }
                    )
                }
            }
        }
    }
}