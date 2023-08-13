package com.example.myapplication.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun KTIButton(
    label: String,
    labelColor: Color,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconResId: String? = null,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        if (iconResId != null) {
            Icon(
                painter = painterResource(res = iconResId),
                contentDescription = "Button icon",
                tint = labelColor
            )
        }
        KTITextNew(text = label, fontSize = 14.sp, fontWeight = FontWeight.W400, color = labelColor, modifier = Modifier.padding(vertical = 16.dp))
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun KTIButton(
    label: String,
    labelColor: Color,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconResId: String? = null,
    isLoading: Boolean = false,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        enabled = isLoading.not(),
    ) {
        if (iconResId != null) {
            Icon(
                painter = painterResource(res = iconResId),
                contentDescription = "Button icon",
                tint = labelColor
            )
        }
        KTITextNew(text = label, fontSize = 12.sp, fontWeight = FontWeight.W400, color = labelColor)
        if (isLoading) { KTICircularProgressIndicator() }
    }
}

@Composable
fun KTITextButton(
    onClick: () -> Unit,
    label: String,
    labelColor: Color,
    size: TextUnit,
    modifier: Modifier = Modifier,
) {
    TextButton(onClick = onClick) {
        KTITextNew(
            text = label,
            fontSize = size,
            fontWeight = FontWeight.W400,
            color = labelColor,
            modifier = modifier
        )
    }
}