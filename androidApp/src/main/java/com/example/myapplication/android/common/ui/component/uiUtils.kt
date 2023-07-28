package com.example.myapplication.android.common.ui.component

import com.example.myapplication.android.ui.theme.kti_blue
import com.example.myapplication.android.ui.theme.kti_bright_red
import com.example.myapplication.android.ui.theme.kti_green
import com.example.myapplication.android.ui.theme.kti_green_variant
import com.example.myapplication.android.ui.theme.kti_light_blue
import com.example.myapplication.android.ui.theme.kti_mauve
import com.example.myapplication.android.ui.theme.kti_orange
import com.example.myapplication.android.ui.theme.kti_purple
import com.example.myapplication.android.ui.theme.kti_yellow

fun <T> KTICardItem<T>.applyColor(itemIndex: Int): KTICardItem<T> {
    val colorIndex = itemIndex % cardColors.size
    return this.copy(cardColor = cardColors[colorIndex])
}

private val cardColors = listOf(
    kti_blue,
    kti_yellow,
    kti_green_variant,
    kti_purple,
    kti_bright_red,
    kti_mauve,
    kti_green,
    kti_light_blue,
    kti_orange
)