package com.example.myapplication.android.screens.subcategory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.common.ui.component.KTIBoxWithGradientBackground
import com.example.myapplication.android.common.ui.component.KTICircularProgressIndicator
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.common.ui.component.KTIVerticalSpacer
import com.example.myapplication.android.common.ui.component.KTICardItem
import com.example.myapplication.android.common.ui.component.GridVariant
import com.example.myapplication.android.common.ui.component.KTIGridWithCards
import com.example.myapplication.android.common.ui.component.KTITopBarNew
import com.example.myapplication.android.common.ui.component.clickableNoRipple
import com.example.myapplication.android.screens.theme.kti_accent
import com.example.myapplication.android.screens.theme.kti_grayish
import com.example.myapplication.android.screens.theme.kti_grayish_light
import com.example.myapplication.android.screens.theme.kti_text_icons
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
    KTIBoxWithGradientBackground {
        when (state) {
            is SubCategoriesViewModel.ViewState.Loading -> {
                KTICircularProgressIndicator()
            }

            is SubCategoriesViewModel.ViewState.SubCategoriesLoaded -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    KTITopBarNew(title = "${topCategory.displayName} categories")
                    KTIGridWithCards(
                        items = state.categories.map { subCategory: SubCategory ->
                            KTICardItem(
                                value = subCategory,
                                label = subCategory.displayName
                            )
                        },
                        onClick = onClick,
                        state = lazyGridState,
                        variant = GridVariant.SUB_CATEGORY,
                    )
                }
            }
        }
    }
}