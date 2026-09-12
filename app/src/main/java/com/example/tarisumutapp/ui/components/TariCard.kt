package com.example.tarisumutapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tarisumutapp.data.model.Tari

@Composable
fun TariCard(
    tari: Tari,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.7f) // Rasio tinggi-lebar gambar kartu portrait
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = tari.imageResId),
            contentDescription = tari.nama,
            contentScale = ContentScale.FillBounds, // Menyesuaikan seluruh gambar ke dalam bingkai kartu
            modifier = Modifier.fillMaxSize()
        )
    }
}