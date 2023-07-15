package com.example.myapplication.android.ui.questions.list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.android.common.ui.component.KTIVerticalSpacer
import com.example.myapplication.android.common.ui.component.bottomsheet.base.KTIBottomSheetSurface
import com.example.myapplication.android.common.ui.component.bottomsheet.content.BottomSheetListItemType
import com.example.myapplication.android.common.ui.component.bottomsheet.content.SelectableListItem
import com.example.myapplication.android.common.ui.component.bottomsheet.model.BottomSheetListItem
import com.example.myapplication.model.Difficulty

@Composable
fun ListScreenBottomSheetContent(
    selectedDifficulties: List<Difficulty>,
    onDifficultyToggled: (Difficulty) -> Unit,
) {
    KTIBottomSheetSurface(title = "Filter questions by difficulty") {
        Column(modifier = Modifier.fillMaxWidth()) {
            difficultiesToSelectableListItems(selectedDifficulties).forEach { difficulty ->
                SelectableListItem(
                    isSelected = difficulty.isSelected,
                    item = difficulty,
                    itemType = difficulty.bottomSheetListItemType,
                    onItemSelected = {
                        onDifficultyToggled(difficulty.value)
                    },
                )
            }
            KTIVerticalSpacer(height = 16.dp)
        }
    }
}

private fun difficultiesToSelectableListItems(
    selectedDifficulties: List<Difficulty>,
): List<BottomSheetListItem<Difficulty>> {
    return Difficulty.values().toList().map { difficulty ->
        BottomSheetListItem(
            value = difficulty,
            label = difficulty.displayName,
            bottomSheetListItemType = BottomSheetListItemType.CHECKABLE,
            isSelected = selectedDifficulties.firstOrNull { selectedDifficulty ->
                difficulty == selectedDifficulty
            } != null
        )
    }
}

