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

    // State untuk mengontrol kemunculan Dialog Skor Selesai
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
                // SISI KIRI: Soal & Opsi Jawaban
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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "${optionLabels[index]}. ",
                                color = Color(0xFFFFD700),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Text(
                                text = optionText,
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 14.sp,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }

                // SISI KANAN: Tombol Pilihan & Selanjutnya/Selesai
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
                            text = "Pilih Jawaban:",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            OptionButton("A", isSelected = selectedOptionIndex == 0) { selectedOptionIndex = 0 }
                            OptionButton("B", isSelected = selectedOptionIndex == 1) { selectedOptionIndex = 1 }
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            OptionButton("C", isSelected = selectedOptionIndex == 2) { selectedOptionIndex = 2 }
                            OptionButton("D", isSelected = selectedOptionIndex == 3) { selectedOptionIndex = 3 }
                        }
                    }

                    Button(
                        onClick = {
                            // Hitung Skor
                            if (selectedOptionIndex == currentQuestion.jawabanBenar) {
                                score += 5
                            }

                            // Pindah ke soal berikut atau tampilkan dialog hasil
                            if (currentQuestionIndex < questionList.size - 1) {
                                currentQuestionIndex++
                                selectedOptionIndex = null
                            } else {
                                isQuizFinished = true // Buka Dialog Skor
                            }
                        },
                        enabled = selectedOptionIndex != null,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFD700),
                            disabledContainerColor = Color.Gray.copy(alpha = 0.5f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = if (currentQuestionIndex < questionList.size - 1) "Selanjutnya" else "Selesai",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Pop-up Dialog Skor saat Kuis Selesai
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
                // Tombol Ulangi Kuis
                confirmButton = {
                    Button(
                        onClick = {
                            currentQuestionIndex = 0
                            selectedOptionIndex = null
                            score = 0
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
                // Tombol Kembali ke Menu
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
        }    }
}

@Composable
fun OptionButton(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(64.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 2.dp,
                color = if (isSelected) Color(0xFFFFD700) else Color.White.copy(alpha = 0.4f),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
    ) {
        Surface(
            color = if (isSelected) Color(0xFFFFD700).copy(alpha = 0.3f) else Color.Black.copy(alpha = 0.2f),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = label,
                    color = if (isSelected) Color(0xFFFFD700) else Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}