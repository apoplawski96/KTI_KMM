package com.example.myapplication.android.common.ui.component.bottomsheet.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.common.ui.component.KTIVerticalSpacer
import com.example.myapplication.android.common.ui.component.clickableNoRipple
import com.example.myapplication.android.ui.theme.kti_accent
import com.example.myapplication.android.ui.theme.kti_grayish
import com.example.myapplication.android.ui.theme.kti_grayish_light
import com.example.myapplication.android.ui.theme.kti_soft_black
import com.example.myapplication.android.ui.theme.kti_soft_white

data class KTICardItem<T>(
    val value: T,
    val label: String,
)

enum class GridVariant { TOP_CATEGORY, SUB_CATEGORY; }

@Composable
fun <T> KTIGridWithCards(
    items: List<KTICardItem<T>>,
    onClick: (T?) -> Unit,
    state: LazyGridState,
    variant: GridVariant
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        state = state,
        content = {
            item { KTIVerticalSpacer(height = 8.dp) }
            item { KTIVerticalSpacer(height = 8.dp) }
            if (variant == GridVariant.SUB_CATEGORY) {
                item {
                    AllCard(onClick = { onClick(null) })
                }
            }
            this.items(items = items) { item ->
                KTICard(item = item, onClick = onClick, padding = PaddingValues(all = 4.dp))
            }
            if (items.count() % 2 == 0) {
                item { KTIVerticalSpacer(height = 8.dp) }
                item { KTIVerticalSpacer(height = 8.dp) }
            } else {
                item { KTIVerticalSpacer(height = 8.dp) }
            }
        }
    )
}

@Composable
fun <T> KTICard(
    item: KTICardItem<T>,
    onClick: (T) -> Unit,
    padding: PaddingValues,
) {
    Card(
        shape = RoundedCornerShape(size = 12.dp),
        backgroundColor = kti_soft_white,
//        border = BorderStroke(width = 0.5.dp, color = kti_grayish_light.copy(alpha = 0.2f)),
        modifier = Modifier
            .clickableNoRipple { onClick.invoke(item.value) }
            .padding(padding)
            .heightIn(min = 96.dp)
            .fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom,
        ) {
            KTITextNew(
                text = item.label,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
                color = kti_soft_black
            )
        }
    }
}

@Composable
private fun AllCard(onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(size = 8.dp),
        backgroundColor = kti_grayish,
        border = BorderStroke(width = 0.5.dp, color = kti_grayish_light.copy(alpha = 0.2f)),
        modifier = Modifier
            .clickableNoRipple { onClick.invoke() }
            .padding(2.dp)
            .heightIn(min = 96.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom,
        ) {
            KTITextNew(
                text = "All",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
                color = kti_accent
            )
        }
    }
}