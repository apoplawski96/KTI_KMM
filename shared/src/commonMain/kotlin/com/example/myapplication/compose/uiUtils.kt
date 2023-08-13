package com.example.myapplication.compose

import com.example.myapplication.theme.kti_bright_red
import com.example.myapplication.theme.kti_dark_blue
import com.example.myapplication.theme.kti_green
import com.example.myapplication.theme.kti_green_variant
import com.example.myapplication.theme.kti_mauve
import com.example.myapplication.theme.kti_orange
import com.example.myapplication.theme.kti_purple
import com.example.myapplication.theme.kti_yellow

fun <T> KTICardItem<T>.applyColor(itemIndex: Int): KTICardItem<T> {
    val colorIndex = itemIndex % cardColors.size
    return this.copy(cardColor = cardColors[colorIndex])
}

private val cardColors = listOf(
    kti_dark_blue,
    kti_yellow,
    kti_green_variant,
    kti_purple,
    kti_bright_red,
    kti_mauve,
    kti_green,
    kti_orange
)