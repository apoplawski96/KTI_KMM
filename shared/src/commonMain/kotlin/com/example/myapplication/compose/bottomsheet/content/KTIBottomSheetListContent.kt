package com.example.myapplication.compose.bottomsheet.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.SharedRes
import com.example.myapplication.compose.bottomsheet.model.BottomSheetListItem
import com.example.myapplication.compose.KTIIcon
import com.example.myapplication.compose.KTIText
import com.example.myapplication.theme.kti_grayish
import com.example.myapplication.theme.kti_green
import com.example.myapplication.theme.kti_secondary_text

enum class BottomSheetListItemType {
    CHECKABLE;
}

@Composable
fun <T> SelectableListItem(
    isSelected: Boolean,
    item: BottomSheetListItem<T>,
    onItemSelected: (T) -> Unit,
    itemType: BottomSheetListItemType,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier then Modifier
            .clickable {
                onItemSelected(item.value)
            }
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        KTIText(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp),
            text = item.label,
            color = kti_secondary_text
        )
        when (itemType) {
            BottomSheetListItemType.CHECKABLE -> CheckableItem(
                isSelected = isSelected
            )
        }
    }
}

@Composable
private fun CheckableItem(isSelected: Boolean) {
    if (isSelected) {
        SelectedItemCheckmark()
    } else {
        UnselectedItemCheckmark()
    }
}

@Composable
private fun SelectedItemCheckmark() {
    Surface(
        color = kti_green,
        shape = CircleShape
    ) {
        KTIIcon(
            size = 20.dp,
            contentDescription = null,
            tint = kti_grayish,
            imageResource = SharedRes.images.ic_check
        )
    }
}

@Composable
private fun UnselectedItemCheckmark() {
    KTIIcon(
        size = 20.dp,
        contentDescription = null,
        tint = kti_grayish,
        imageResource = SharedRes.images.ic_circle
    )
}