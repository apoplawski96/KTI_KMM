package com.example.myapplication.android.ui.subcategory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.common.ui.component.KTIVerticalSpacer
import com.example.myapplication.android.common.ui.component.FcTextTopBar
import com.example.myapplication.android.common.ui.component.KTICircularProgressIndicator
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.ui.theme.kti_accent_color
import com.example.myapplication.android.ui.theme.kti_dark_primary
import com.example.myapplication.android.ui.theme.kti_light_primary
import com.example.myapplication.android.ui.theme.kti_primary
import com.example.myapplication.android.ui.theme.kti_text_icons
import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory
import com.example.myapplication.screens.subcategories.SubCategoriesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SubCategoriesScreen(
    viewModel: SubCategoriesViewModel = getViewModel(),
    topCategory: TopCategory?,
) {
    if (topCategory == null) return

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
        lazyGridState = lazyGridState,
        topCategory = topCategory,
    )
}

@Composable
fun SubCategoriesScreenContent(
    state: SubCategoriesViewModel.ViewState,
    onClick: (SubCategory?) -> Unit,
    lazyGridState: LazyGridState,
    topCategory: TopCategory,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    0.0f to kti_dark_primary,
                    0.9f to kti_primary,
                    1.0f to kti_accent_color.copy(alpha = 0.0001f),
                )
            )
    ) {
        when (state) {
            is SubCategoriesViewModel.ViewState.Loading -> {
                KTICircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is SubCategoriesViewModel.ViewState.SubCategoriesLoaded -> {
                Column {
                    FcTextTopBar(
                        middleContentText = "${topCategory.displayName} categories",
                        isNested = true,
                        hasBrandingLine = true,
                        rightActionButtons = {
                            IconButton(onClick = { }) {
                                Icon(Icons.Filled.Search, "Back Icon", tint = kti_accent_color)
                            }
                        })
                    CategoriesGrid(
                        categories = state.categories,
                        onClick = onClick,
                        state = lazyGridState,
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoriesGrid(
    categories: List<SubCategory>,
    onClick: (SubCategory?) -> Unit,
    state: LazyGridState,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        state = state,
        content = {
            item { KTIVerticalSpacer(height = 16.dp) }
            item { KTIVerticalSpacer(height = 16.dp) }
            item { SubCategoryCard(subCategory = null, onClick = { onClick(null) }) }
            items(items = categories) { category ->
                SubCategoryCard(subCategory = category, onClick = onClick)
            }
        }
    )
}

@Composable
private fun SubCategoryCard(
    subCategory: SubCategory?,
    onClick: (SubCategory?) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(size = 8.dp),
        backgroundColor = kti_primary,
        border = BorderStroke(width = 0.5.dp, color = kti_light_primary),
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
            KTITextNew(
                text = subCategory?.displayName ?: "All",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
                color = if (subCategory != null) kti_text_icons else kti_accent_color
            )
        }
    }
}