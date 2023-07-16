package com.example.myapplication.android.common.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun KTIIcon(
    @DrawableRes drawableRes: Int,
    size: Dp,
    tint: Color,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(id = drawableRes),
        contentDescription = contentDescription,
        modifier = modifier then Modifier.size(size),
        tint = tint
    )
}