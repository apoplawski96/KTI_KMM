package com.example.myapplication.android.common.ui.component.bottomsheet.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.example.myapplication.android.common.ui.component.KTIText
import com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.base.FcBottomSheetSurface
import com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.content.FcIcon
import com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.content.FcRadioButton
import com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.content.iconSize
import com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.model.BottomSheetListItem
import com.example.myapplication.android.R

enum class BottomSheetListItemType {
    RADIO,
    CHECKABLE,
    SWITCH;
}

@Composable
fun <T> FcBottomSheetListContent(
    title: String,
    listItems: List<BottomSheetListItem<T>>,
    onItemSelected: (T) -> Unit,
) {
    FcBottomSheetSurface(title = title) {
        SelectableItemsList(
            listItems = listItems,
            onItemSelected = onItemSelected,
        )
    }
}

@Composable
private fun <T> SelectableItemsList(
    listItems: List<BottomSheetListItem<T>>,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier then Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        items(listItems) { item ->
            SelectableListItem(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .wrapContentHeight(),
                isSelected = item.isSelected,
                item = item,
                onItemSelected = onItemSelected,
                itemType = item.bottomSheetListItemType
            )
        }
    }
}

@Composable
private fun <T> SelectableListItem(
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
        )
        when (itemType) {
            BottomSheetListItemType.CHECKABLE -> CheckableItem(
                isSelected = isSelected
            )
            BottomSheetListItemType.RADIO -> RadioItem(
                isSelected = isSelected,
                onClick = { onItemSelected(item.value) }
            )
//            BottomSheetListItemType.SWITCH -> FcSwitch(
//                isChecked = isSelected,
//                onCheckedChange = { onItemSelected(item.value) },
//                modifier = Modifier.size(20.dp)
//            )
            BottomSheetListItemType.SWITCH -> {

            }
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
        color = Color.Green,
        shape = CircleShape
    ) {
        FcIcon(
            drawableRes = R.drawable.ic_font,
            size = iconSize(20.dp),
            contentDescription = null,
            tint = White,
        )
    }
}

@Composable
private fun UnselectedItemCheckmark() {
    FcIcon(
        drawableRes = R.drawable.ic_font,
        size = iconSize(20.dp),
        tint = Black
    )
}

@Composable
private fun RadioItem(isSelected: Boolean, onClick: (() -> Unit)?) {
    FcRadioButton(selected = isSelected, onClick = onClick)
}
