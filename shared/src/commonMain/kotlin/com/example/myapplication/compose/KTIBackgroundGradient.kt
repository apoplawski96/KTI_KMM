package com.example.myapplication.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.example.myapplication.theme.kti_background_grey
import com.example.myapplication.theme.kti_grey
import com.example.myapplication.theme.kti_soft_white

@Composable
fun KTIColumnWithGradientColumn(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = getGradientBrush()),
    ) {
        content.invoke()
    }
}

@Composable
fun KTIBoxWithGradientBackground(
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = contentAlignment,
        modifier = Modifier
            .fillMaxSize()
            .background(kti_background_grey),
    ) {
        content.invoke()
    }
}

private fun getGradientBrush() = Brush.verticalGradient(
    0.0f to kti_soft_white,
    1.0f to kti_grey
)