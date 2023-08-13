package com.example.myapplication.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.myapplication.theme.KTITheme
import com.example.myapplication.theme.kti_soft_black
import com.example.myapplication.navigation.Navigator
import org.jetbrains.compose.resources.painterResource

@Composable
fun KTIIcon(
    drawableRes: Int,
    modifier: Modifier = Modifier,
    size: Dp = Dp.Unspecified,
    tint: Color = KTITheme.colors.textMain,
    contentDescription: String? = null,
) {
//    Icon(
//        painter = painterResource(id = drawableRes),
//        contentDescription = contentDescription,
//        modifier = modifier then Modifier.size(size),
//        tint = tint
//    )
}

@Composable
fun KTIIllustration(
    drawableRes: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
//    Image(
//        painter = painterResource(id = drawableRes),
//        contentDescription = contentDescription,
//        modifier = modifier
//    )
}

@Composable
fun KTIIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
) {
    IconButton(onClick = onClick) {
        icon.invoke()
    }
}

@Composable
fun KTIIconButton(
    onClick: () -> Unit,
    drawableRes: Int,
    size: Dp,
    tint: Color,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    IconButton(onClick = onClick) {
        KTIIcon(
            drawableRes = drawableRes,
            size = size,
            tint = tint,
            contentDescription = contentDescription,
            modifier = modifier
        )
    }
}

@Composable
fun KTIBackIcon() {
    Icon(Icons.Filled.ArrowBack, "Back Icon", tint = kti_soft_black)
}

@Composable
fun KTIBackButton() {
//    KTIIconButton(onClick = { navigator.navigateBack() }) {
//        KTIBackIcon()
//    }
    KTIBackIcon()
}