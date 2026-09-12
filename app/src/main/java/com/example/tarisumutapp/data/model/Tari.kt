package com.example.tarisumutapp.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Tari(
    val id: String,
    val nama: String,
    val etnis: String,
    val deskripsi: String,
    val sejarah: String = "",
    @DrawableRes val imageResId: Int, // Menggunakan ID resource dari res/drawable
    @RawRes val videoResId: Int = 0,
    val audioUrl: String = ""
)