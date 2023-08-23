package com.example.myapplication.compose.bottomsheet.model

import com.example.myapplication.compose.bottomsheet.content.BottomSheetListItemType

data class BottomSheetListItem<T>(
    val value: T,
    val label: String,
    val bottomSheetListItemType: BottomSheetListItemType,
    val isSelected: Boolean,
)
