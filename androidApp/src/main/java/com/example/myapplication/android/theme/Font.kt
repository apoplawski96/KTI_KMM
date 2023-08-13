package com.example.myapplication.android.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.myapplication.android.R

val fonts = FontFamily(
    Font(R.font.inter_light, weight = FontWeight.W300),
    Font(R.font.inter_regular, weight = FontWeight.W400),
    Font(R.font.inter_medium, weight = FontWeight.W500),
    Font(R.font.inter_semibold, weight = FontWeight.W600),
    Font(R.font.inter_bold, weight = FontWeight.W700),
)

val fontsKanit = FontFamily(
    Font(R.font.kanit_light, weight = FontWeight.W300),
    Font(R.font.kanit_regular, weight = FontWeight.W400),
    Font(R.font.kanit_medium, weight = FontWeight.W500),
    Font(R.font.kanit_semibold, weight = FontWeight.W600),
    Font(R.font.kanit_bold, weight = FontWeight.W700),
    Font(R.font.kanit_italic, weight = FontWeight.W400, style = FontStyle.Italic)
)