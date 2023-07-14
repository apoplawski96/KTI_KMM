package com.example.myapplication.android.common.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.ui.theme.kti_text_icons
import com.example.myapplication.android.ui.theme.fonts
import com.example.myapplication.android.ui.theme.fontsKanit
import com.example.myapplication.android.ui.theme.kti_primary_text

@Composable
fun KTIText(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        color = kti_text_icons,
        lineHeight = 13.sp,
        fontSize = 12.sp,
        fontFamily = fontsKanit,
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

@Composable
fun KTITextNew(
    text: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    modifier: Modifier = Modifier,
    color: Color = kti_primary_text,
    fontFamily: FontFamily = fontsKanit,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        modifier = modifier,
        fontSize = fontSize,
        color = color
    )
}