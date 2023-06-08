package com.apoplawski96.killtheinterview.common.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun KTIText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        text = text,
        color = color,
        modifier = modifier,
        overflow = overflow
    )
}