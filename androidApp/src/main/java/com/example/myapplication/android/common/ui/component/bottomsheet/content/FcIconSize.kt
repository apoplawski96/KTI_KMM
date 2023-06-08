package com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.content

import androidx.compose.ui.unit.Dp

data class FcIconSize(
    val width: Dp,
    val height: Dp,
)

fun iconSize(size: Dp) = FcIconSize(width = size, height = size)
fun iconSize(width: Dp, height: Dp) = FcIconSize(width = width, height = height)
