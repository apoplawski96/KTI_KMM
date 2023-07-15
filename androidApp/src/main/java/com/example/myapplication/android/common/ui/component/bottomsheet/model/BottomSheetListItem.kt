package com.example.myapplication.android.common.ui.component.bottomsheet.model

import com.example.myapplication.android.common.ui.component.bottomsheet.content.BottomSheetListItemType

data class BottomSheetListItem<T>(
    val value: T,
    val label: String,
    val bottomSheetListItemType: BottomSheetListItemType,
    val isSelected: Boolean,
)
