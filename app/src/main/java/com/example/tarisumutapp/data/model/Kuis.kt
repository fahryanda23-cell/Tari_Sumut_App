package com.example.tarisumutapp.data.model

data class SoalKuis(
    val id: Int,
    val pertanyaan: String,
    val pilihan: List<String>,
    val jawabanBenar: Int // indeks pilihan jawaban (0 = A, 1 = B, 2 = C, 3 = D)
)