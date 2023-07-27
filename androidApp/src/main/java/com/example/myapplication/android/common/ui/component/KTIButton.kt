package com.example.myapplication.android.common.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KTIButton(
    label: String,
    labelColor: Color,
    backgroundColor: Color,
    onClick: () -> Unit,
    @DrawableRes iconResId: Int? = null
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        if (iconResId != null) {
            Icon(painter = painterResource(id = iconResId), contentDescription = "Button icon", tint = labelColor)
        }
        KTITextNew(text = label, fontSize = 12.sp, fontWeight = FontWeight.W400, color = labelColor)
    }
}

@Composable
fun KTITextButton(
    onClick: () -> Unit,
    label: String,
    labelColor: Color,
    size: TextUnit,
) {
    TextButton(onClick = onClick) {
        KTITextNew(text = label, fontSize = size, fontWeight = FontWeight.W400, color = labelColor)
    }
}