package com.example.myapplication.android.common.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import co.touchlab.kampkit.android.ui.theme.podme_silver
import com.example.myapplication.android.ui.theme.fonts

@Composable
fun KTIText(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        color = podme_silver,
        lineHeight = 13.sp,
        fontSize = 12.sp,
        fontFamily = fonts,
        letterSpacing = TextUnit(-0.01f, TextUnitType.Sp)
    ),
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = Color.White,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        style = textStyle,
        textAlign = textAlign,
        color = color
    )
}