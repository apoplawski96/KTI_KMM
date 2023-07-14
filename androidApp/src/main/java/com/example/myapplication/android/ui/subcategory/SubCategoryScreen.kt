package com.example.myapplication.android.ui.subcategory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.myapplication.android.common.ui.component.KTICircularProgressIndicator
import com.example.myapplication.android.common.ui.component.KTIDestinationTopBar
import com.example.myapplication.android.common.ui.component.KTIText
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.ui.theme.kti_accent_color
import com.example.myapplication.android.ui.theme.kti_dark_primary
import com.example.myapplication.android.ui.theme.kti_light_primary
import com.example.myapplication.android.ui.theme.kti_primary
import com.example.myapplication.android.ui.theme.kti_primary_text
import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory
import com.example.myapplication.screens.subcategories.SubCategoriesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SubCategoryScreen(
    viewModel: SubCategoriesViewModel = getViewModel(),
    topCategory: TopCategory?,
) {
    if (topCategory == null) {
        println("2137 - top category is null")
        return
    }

    val viewState = viewModel.state.collectAsState().value
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(null) {
        viewModel.initialize(topCategory)
    }

    SubCategoriesScreenContent(
        state = viewState,
        onClick = { subCategory ->
            viewModel.navigateToQuestionsList(
                topCategory = topCategory,
                subCategory = subCategory
            )
        },
        lazyGridState = lazyGridState
    )
}

@Composable
fun SubCategoriesScreenContent(
    state: SubCategoriesViewModel.ViewState,
    onClick: (SubCategory?) -> Unit,
    lazyGridState: LazyGridState
) {
    Box(modifier = Modifier.fillMaxSize().background(kti_light_primary)) {
        when (state) {
            is SubCategoriesViewModel.ViewState.Loading -> {
                KTICircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is SubCategoriesViewModel.ViewState.SubCategoriesLoaded -> {
                Column {
                    KTIDestinationTopBar(title = "Sub categories")
                    RandomCard { onClick(null) }
                    CategoriesGrid(
                        categories = state.categories,
                        onClick = onClick,
                        state = lazyGridState
                    )
                }
            }
        }
    }
}

@Composable
private fun RandomCard(onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(size = 8.dp),
        backgroundColor = kti_primary,
        border = BorderStroke(width = 0.5.dp, color = kti_dark_primary),
        modifier = Modifier
            .clickable { onClick.invoke() }
            .padding(8.dp)
            .heightIn(min = 96.dp)
            .fillMaxWidth()
    ) {
        KTITextNew(
            text = "Get random questions",
            fontSize = 18.sp,
            fontWeight = FontWeight.W400,
            color = kti_accent_color
        )
    }
}

@Composable
private fun CategoriesGrid(
    categories: List<SubCategory>,
    onClick: (SubCategory) -> Unit,
    state: LazyGridState,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = Modifier.padding(horizontal = 12.dp),
        state = state,
        content = {
            items(items = categories) { category ->
                SubCategoryCard(subCategory = category, onClick = onClick)
            }
        }
    )
}

@Composable
private fun SubCategoryCard(
    subCategory: SubCategory,
    onClick: (SubCategory) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(size = 8.dp),
        backgroundColor = kti_primary,
        border = BorderStroke(width = 0.5.dp, color = kti_dark_primary),
        modifier = Modifier
            .clickable { onClick.invoke(subCategory) }
            .padding(4.dp)
            .heightIn(min = 96.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom,
        ) {
            KTIText(
                text = subCategory.displayName,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textStyle = TextStyle(
                    fontWeight = FontWeight(700),
                    fontSize = 14.sp,
                    lineHeight = 18.2.sp,
                    letterSpacing = TextUnit(-0.01f, TextUnitType.Sp),
                    color = kti_primary_text
                )
            )
        }
    }
}