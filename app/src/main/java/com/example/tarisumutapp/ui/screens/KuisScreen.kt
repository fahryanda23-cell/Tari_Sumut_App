package com.example.tarisumutapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarisumutapp.R
import com.example.tarisumutapp.data.KuisData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KuisScreen(
    onBackClick: () -> Unit
) {
    val questionList = KuisData.listSoal

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableIntStateOf(0) }

    // State baru untuk mengecek apakah jawaban sudah diperiksa
    var isAnswerSubmitted by remember { mutableStateOf(false) }
    var isQuizFinished by remember { mutableStateOf(false) }

    val currentQuestion = questionList[currentQuestionIndex]
    val optionLabels = listOf("A", "B", "C", "D")

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
                            "Kuis & Evaluasi Tari Sumut",
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
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // SISI KIRI: Soal, Opsi Jawaban, dan Kartu Pembahasan
                Column(
                    modifier = Modifier
                        .weight(1.3f)
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Soal ${currentQuestionIndex + 1} dari ${questionList.size}",
                        color = Color(0xFFFFC107),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = currentQuestion.pertanyaan,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    currentQuestion.pilihan.forEachIndexed { index, optionText ->
                        // Penentuan Warna Teks Pilihan berdasarkan Status Jawaban
                        val textColor = when {
                            !isAnswerSubmitted -> Color.White.copy(alpha = 0.9f)
                            index == currentQuestion.jawabanBenar -> Color(0xFF4CAF50) // Hijau jika Benar
                            index == selectedOptionIndex -> Color(0xFFFF5252) // Merah jika Salah yang dipilih
                            else -> Color.White.copy(alpha = 0.4f)
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "${optionLabels[index]}. ",
                                color = if (isAnswerSubmitted && index == currentQuestion.jawabanBenar) Color(0xFF4CAF50) else Color(0xFFFFD700),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Text(
                                text = optionText,
                                color = textColor,
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                fontWeight = if (isAnswerSubmitted && index == currentQuestion.jawabanBenar) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }

                    // KARTU PEMBAHASAN (Muncul setelah tombol 'Cek Jawaban' diklik)
                    if (isAnswerSubmitted) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = if (selectedOptionIndex == currentQuestion.jawabanBenar) "Jawaban Benar! 🎉" else "Jawaban Kurang Tepat!",
                                    fontWeight = FontWeight.Bold,
                                    color = if (selectedOptionIndex == currentQuestion.jawabanBenar) Color(0xFF2E7D32) else Color(0xFFC62828),
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Pembahasan: Kunci jawaban yang benar adalah ${optionLabels[currentQuestion.jawabanBenar]}.",
                                    fontSize = 13.sp,
                                    color = Color.Black.copy(alpha = 0.8f)
                                )
                            }
                        }
                    }
                }

                // SISI KANAN: Tombol Pilihan (A, B, C, D) & Tombol Aksi
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(
                            text = if (isAnswerSubmitted) "Hasil Jawaban:" else "Pilih Jawaban:",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        // Penentuan Warna Tombol A, B, C, D secara dinamis
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            OptionButton("A", optionIndex = 0, selectedIndex = selectedOptionIndex, correctIndex = currentQuestion.jawabanBenar, isSubmitted = isAnswerSubmitted) {
                                if (!isAnswerSubmitted) selectedOptionIndex = 0
                            }
                            OptionButton("B", optionIndex = 1, selectedIndex = selectedOptionIndex, correctIndex = currentQuestion.jawabanBenar, isSubmitted = isAnswerSubmitted) {
                                if (!isAnswerSubmitted) selectedOptionIndex = 1
                            }
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            OptionButton("C", optionIndex = 2, selectedIndex = selectedOptionIndex, correctIndex = currentQuestion.jawabanBenar, isSubmitted = isAnswerSubmitted) {
                                if (!isAnswerSubmitted) selectedOptionIndex = 2
                            }
                            OptionButton("D", optionIndex = 3, selectedIndex = selectedOptionIndex, correctIndex = currentQuestion.jawabanBenar, isSubmitted = isAnswerSubmitted) {
                                if (!isAnswerSubmitted) selectedOptionIndex = 3
                            }
                        }
                    }

                    // TOMBOL AKSI 2 FASE: "Cek Jawaban" -> "Selanjutnya / Selesai"
                    Button(
                        onClick = {
                            if (!isAnswerSubmitted) {
                                // Fase 1: Periksa Jawaban
                                isAnswerSubmitted = true
                                if (selectedOptionIndex == currentQuestion.jawabanBenar) {
                                    score += (100 / questionList.size)
                                }
                            } else {
                                // Fase 2: Pindah ke Soal Berikutnya
                                if (currentQuestionIndex < questionList.size - 1) {
                                    currentQuestionIndex++
                                    selectedOptionIndex = null
                                    isAnswerSubmitted = false
                                } else {
                                    isQuizFinished = true
                                }
                            }
                        },
                        enabled = selectedOptionIndex != null,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (!isAnswerSubmitted) Color(0xFFFFD700) else Color(0xFF4CAF50),
                            disabledContainerColor = Color.Gray.copy(alpha = 0.5f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = when {
                                !isAnswerSubmitted -> "Cek Jawaban"
                                currentQuestionIndex < questionList.size - 1 -> "Selanjutnya"
                                else -> "Selesai"
                            },
                            color = if (!isAnswerSubmitted) Color.Black else Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Pop-up Dialog Skor saat Kuis Selesai
        if (isQuizFinished) {
            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text(
                        text = "Kuis Selesai!",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Skor Akhir Kamu:",
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "$score / 100",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF8B0000)
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            currentQuestionIndex = 0
                            selectedOptionIndex = null
                            score = 0
                            isAnswerSubmitted = false
                            isQuizFinished = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Ulangi Kuis",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = onBackClick,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Kembali ke Menu",
                            color = Color(0xFF8B0000),
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                containerColor = Color(0xFFFFF8E7)
            )
        }
    }
}

@Composable
fun OptionButton(
    label: String,
    optionIndex: Int,
    selectedIndex: Int?,
    correctIndex: Int,
    isSubmitted: Boolean,
    onClick: () -> Unit
) {
    // Logika Perubahan Warna Tombol
    val isSelected = selectedIndex == optionIndex

    val borderColor = when {
        !isSubmitted -> if (isSelected) Color(0xFFFFD700) else Color.White.copy(alpha = 0.4f)
        optionIndex == correctIndex -> Color(0xFF4CAF50) // Border Hijau jika jawaban benar
        isSelected -> Color(0xFFFF5252) // Border Merah jika pilihan pengguna salah
        else -> Color.White.copy(alpha = 0.2f)
    }

    val backgroundColor = when {
        !isSubmitted -> if (isSelected) Color(0xFFFFD700).copy(alpha = 0.3f) else Color.Black.copy(alpha = 0.2f)
        optionIndex == correctIndex -> Color(0xFF4CAF50).copy(alpha = 0.4f) // Background Hijau
        isSelected -> Color(0xFFFF5252).copy(alpha = 0.4f) // Background Merah
        else -> Color.Black.copy(alpha = 0.2f)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(64.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
    ) {
        Surface(
            color = backgroundColor,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = label,
                    color = when {
                        !isSubmitted -> if (isSelected) Color(0xFFFFD700) else Color.White
                        optionIndex == correctIndex -> Color(0xFF4CAF50)
                        isSelected -> Color(0xFFFF5252)
                        else -> Color.White.copy(alpha = 0.4f)
                    },
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}