package com.example.myapplication.android.ui.questions.list.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.common.ui.component.KTITextTopBar
import com.example.myapplication.android.ui.theme.kti_accent
import com.example.myapplication.android.ui.theme.kti_soft_black
import com.example.myapplication.screens.list.ListViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListScreenTopBar(
    bottomSheetState: ModalBottomSheetState,
    onToggleBottomSheetClick: () -> Unit,
    topBarTitle: String,
    sortDropdownMenuDisplayed: Boolean,
    toggleDropdownMenu: () -> Unit,
    onSortModeClick: (ListViewModel.SortMode) -> Unit,
) {
    KTITextTopBar(
        middleContentText = topBarTitle,
        isNested = true,
        hasBrandingLine = false,
        rightActionButtons = {
            IconButton(onClick = onToggleBottomSheetClick) {
                if (bottomSheetState.isVisible) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Bottom sheet icon",
                        tint = kti_accent
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = "Bottom sheet icon",
                        tint = kti_accent
                    )
                }
            }
            IconButton(onClick = toggleDropdownMenu) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "Menu icon",
                    tint = kti_soft_black
                )
            }
            DropdownMenu(
                expanded = sortDropdownMenuDisplayed,
                onDismissRequest = toggleDropdownMenu
            ) {
                ListViewModel.SortMode.values().toList().forEach { sortMode ->
                    DropdownMenuItem(onClick = { onSortModeClick(sortMode) }) {
                        KTITextNew(
                            text = sortMode.displayName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400,
                            color = kti_soft_black
                        )
                    }
                }
            }
        }
    )
}