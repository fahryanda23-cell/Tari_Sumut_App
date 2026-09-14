package com.example.tarisumutapp.ui.screens

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarisumutapp.R

data class IstilahItem(
    val istilah: String,
    val etnis: String,
    val arti: String,
    val audioResId: Int? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlosariumScreen(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    // Variabel untuk mengelola pemutaran MediaPlayer secara aman
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    // Memastikan MediaPlayer dilepas saat pengguna keluar dari layar ini
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    val listGlosarium = listOf(
        IstilahItem(
            istilah = "Manortor",
            etnis = "Batak Toba (Tari Tor-Tor)",
            arti = "Gerakan dasar mengayunkan kedua tangan secara ritmis dengan ketukan konstan yang melambangkan penghormatan dan doa.",
            audioResId = R.raw.manortor
        ),
        IstilahItem(
            istilah = "Ragam Sapu Tangan",
            etnis = "Melayu Deli (Tari Serampang Duabelas)",
            arti = "Ragam gerak ke-12 atau penutup yang melambangkan ikatan janji suci dan komitmen pernikahan muda-mudi Melayu.",
            audioResId = R.raw.ragam_saputangan
        ),
        IstilahItem(
            istilah = "Tudung Karo",
            etnis = "Karo (Tari Landek)",
            arti = "Penutup kepala khas wanita Karo berbahan kain tenun tebal dengan bentuk yang kokoh, megah, dan bernilai simbolis.",
            audioResId = R.raw.tudung_karo
        ),
        IstilahItem(
            istilah = "Toping-Toping",
            etnis = "Simalungun (Tari Toping-Toping)",
            arti = "Topeng kayu khas Simalungun yang digunakan penari untuk memperagakan karakter unik saat pertunjukan persembahan raja.",
            audioResId = R.raw.toping_toping
        ),
        IstilahItem(
            istilah = "Gordang Sambilan",
            etnis = "Batak Mandailing (Tari Endeng-Endeng)",
            arti = "Ansambel musik tradisional yang terdiri dari 9 buah gendang berukuran berbeda untuk mengiringi tarian sakral dan perayaan.",
            audioResId = R.raw.gondang_samblan
        ),
        IstilahItem(
            istilah = "Merdang Merdem",
            etnis = "Pakpak (Tari Tatak Garo-Garo)",
            arti = "Tradisi pesta budaya agraris gotong royong muda-mudi Pakpak di ladang yang diabadikan dalam gerakan tarian.",
            audioResId = R.raw.merdang
        ),
        IstilahItem(
            istilah = "Gerak Moyo / Elang",
            etnis = "Nias (Tari Moyo)",
            arti = "Gerakan membentangkan dan mengayunkan kedua tangan secara halus melambangkan kepakan burung elang suci Nias.",
            audioResId = R.raw.gerak_moyo
        ),
        IstilahItem(
            istilah = "Musik Sikambang",
            etnis = "Pesisir / Sibolga (Tari Saputangan)",
            arti = "Irama musik khas masyarakat pesisir pantai barat Sumut yang menggabungkan instrumen biola, accordion, dan gendang.",
            audioResId = R.raw.sikambang
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
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
                            "Glosarium Istilah Tari",
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(listGlosarium) { item ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Black.copy(alpha = 0.6f)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = item.istilah,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFFFD700)
                                )
                                Text(
                                    text = "Etnis: ${item.etnis}",
                                    fontSize = 12.sp,
                                    color = Color.LightGray
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = item.arti,
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    lineHeight = 18.sp
                                )
                            }

                            if (item.audioResId != null) {
                                IconButton(
                                    onClick = {
                                        // Hentikan audio yang sedang berjalan (jika ada)
                                        mediaPlayer?.stop()
                                        mediaPlayer?.release()

                                        // Putar audio baru
                                        mediaPlayer = MediaPlayer.create(context, item.audioResId).apply {
                                            start()
                                            setOnCompletionListener { mp ->
                                                mp.release()
                                                mediaPlayer = null
                                            }
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = "Putar Pelafalan",
                                        tint = Color(0xFFFFD700),
                                        modifier = Modifier.size(32.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}