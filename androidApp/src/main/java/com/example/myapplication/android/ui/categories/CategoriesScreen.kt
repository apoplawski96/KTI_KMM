package com.example.myapplication.android.ui.categories

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kampkit.android.ui.theme.podme_cinder
import co.touchlab.kampkit.android.ui.theme.podme_licorice
import co.touchlab.kampkit.android.ui.theme.podme_soft_white
import com.example.myapplication.android.common.ui.component.KTICircularProgressIndicator
import com.example.myapplication.android.common.ui.component.KTIText
import com.example.myapplication.questions.model.subcategory.TopCategory
import com.example.myapplication.questions.view.CategoriesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CategoriesScreen(viewModel: CategoriesViewModel = getViewModel()) {

    val viewState = viewModel.state.collectAsState().value
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(null) {
        viewModel.initialize()
    }

    CategoriesScreenContent(
        state = viewState,
        onClick = { category -> viewModel.categorySelected(category) },
        lazyGridState = lazyGridState
    )
}

@Composable
fun CategoriesScreenContent(
    state: CategoriesViewModel.ViewState,
    onClick: (TopCategory) -> Unit,
    lazyGridState: LazyGridState
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is CategoriesViewModel.ViewState.CategoriesLoaded -> {
                CategoriesGrid(
                    categories = state.categories,
                    onClick = onClick,
                    state = lazyGridState
                )
            }
            is CategoriesViewModel.ViewState.Loading -> {
                KTICircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun CategoriesGrid(
    categories: List<TopCategory>,
    onClick: (TopCategory) -> Unit,
    state: LazyGridState,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = Modifier.padding(horizontal = 12.dp),
        state = state,
        content = {
            items(items = categories) { category ->
                CategoryCard(category = category, onClick = onClick)
            }
        }
    )
}

@Composable
private fun CategoryCard(
    category: TopCategory,
    onClick: (TopCategory) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(size = 8.dp),
        backgroundColor = podme_cinder,
        border = BorderStroke(width = 0.5.dp, color = podme_licorice),
        modifier = Modifier
            .clickable { onClick.invoke(category) }
            .padding(4.dp)
            .heightIn(min = 96.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom,
        ) {
            KTIText(
                text = category.displayName,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textStyle = TextStyle(
                    fontWeight = FontWeight(700),
                    fontSize = 14.sp,
                    lineHeight = 18.2.sp,
                    letterSpacing = TextUnit(-0.01f, TextUnitType.Sp),
                    color = podme_soft_white
                )
            )
        }
    }
}