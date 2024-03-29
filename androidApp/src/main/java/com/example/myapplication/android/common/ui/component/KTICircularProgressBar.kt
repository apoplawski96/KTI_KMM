package com.example.myapplication.android.common.ui.component

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.android.theme.KTITheme

@Composable
fun KTICircularProgressIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier, color = KTITheme.colors.secondary)
}