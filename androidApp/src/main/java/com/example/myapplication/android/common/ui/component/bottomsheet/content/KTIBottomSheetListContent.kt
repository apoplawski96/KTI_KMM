package com.example.myapplication.android.common.ui.component.bottomsheet.content

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
import com.example.myapplication.android.R
import com.example.myapplication.android.common.ui.component.KTIText
import com.example.myapplication.android.common.ui.component.bottomsheet.model.BottomSheetListItem
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
        com.example.myapplication.android.common.ui.component.KTIIcon(
            drawableRes = R.drawable.ic_check,
            size = 20.dp,
            contentDescription = null,
            tint = kti_grayish,
        )
    }
}

@Composable
private fun UnselectedItemCheckmark() {
    com.example.myapplication.android.common.ui.component.KTIIcon(
        drawableRes = R.drawable.ic_circle,
        size = 20.dp,
        tint = kti_grayish
    )
}