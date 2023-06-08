package com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.content

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun FcIcon(
    @DrawableRes drawableRes: Int,
    size: FcIconSize,
    tint: Color,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(id = drawableRes),
        contentDescription = contentDescription,
        modifier = modifier then Modifier
            .size(width = size.width, height = size.height),
        tint = tint
    )
}

//@Composable
//fun FcIcon(
//    @DrawableRes drawableRes: Int,
//    sizeIn: FcIconSizeIn,
//    tint: Color,
//    modifier: Modifier = Modifier,
//    contentDescription: String? = null,
//) {
//    Icon(
//        painter = painterResource(id = drawableRes),
//        contentDescription = contentDescription,
//        modifier = modifier then Modifier
//            .sizeIn(maxWidth = sizeIn.maxWidth, maxHeight = sizeIn.maxHeight),
//        tint = tint
//    )
//}
