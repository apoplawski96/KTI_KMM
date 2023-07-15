package com.example.myapplication.android.ui.questions.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.model.BottomSheetListItem
import com.example.myapplication.android.common.ui.component.KTIHorizontalSpacer
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.common.ui.component.KTIVerticalSpacer
import com.example.myapplication.android.common.ui.component.bottomsheet.base.FcBottomSheetSurface
import com.example.myapplication.android.common.ui.component.bottomsheet.content.BottomSheetListItemType
import com.example.myapplication.android.common.ui.component.bottomsheet.content.SelectableListItem
import com.example.myapplication.android.common.ui.component.clickableNoRipple
import com.example.myapplication.android.ui.theme.kti_accent_color
import com.example.myapplication.android.ui.theme.kti_primary
import com.example.myapplication.model.Difficulty

@Composable
fun ListScreenBottomSheetContent(
    onRandomizeQuestionsClick: () -> Unit,
    selectedDifficulties: List<Difficulty>,
    onDifficultyToggled: (Difficulty) -> Unit,
) {
    FcBottomSheetSurface(title = "Filter questions by difficulty") {
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
//            RandomizeQuestionsButton(onClick = onRandomizeQuestionsClick)
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

@Composable
private fun RandomizeQuestionsButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        KTITextNew(
            text = "Randomize!",
            fontSize = 20.sp,
            fontWeight = FontWeight.W500,
            color = kti_primary,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        KTIHorizontalSpacer(width = 4.dp)
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = null,
            tint = kti_accent_color,
            modifier = Modifier
                .clickableNoRipple { onClick.invoke() }
                .size(22.dp),
        )
    }
}

