package com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FcModalBottomSheetLayout(
    sheetState: ModalBottomSheetState,
    bottomSheetContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetContent = {
            // Sheet content height must be >= 1dp
            Box(modifier = Modifier.defaultMinSize(minHeight = 1.dp)) {
                bottomSheetContent()
            }
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Transparent,
        sheetShape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp),
        sheetElevation = 0.dp,
//        scrimColor = scrimColor,
        content = content
    )
}
