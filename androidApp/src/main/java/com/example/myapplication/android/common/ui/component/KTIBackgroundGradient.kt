package com.example.myapplication.android.common.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.example.myapplication.android.ui.theme.grey
import com.example.myapplication.android.ui.theme.kti_accent_color
import com.example.myapplication.android.ui.theme.kti_dark_primary
import com.example.myapplication.android.ui.theme.kti_primary

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
            .background(brush = getGradientBrush()),
    ) {
        content.invoke()
    }
}

private fun getGradientBrush() = Brush.verticalGradient(
    0.0f to kti_dark_primary,
    1.0f to grey
)