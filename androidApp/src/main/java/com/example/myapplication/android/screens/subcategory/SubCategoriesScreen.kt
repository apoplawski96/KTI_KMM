package com.example.myapplication.android.screens.subcategory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.myapplication.android.common.ui.component.KTIBoxWithGradientBackground
import com.example.myapplication.android.common.ui.component.KTICircularProgressIndicator
import com.example.myapplication.android.common.ui.component.KTICardItem
import com.example.myapplication.android.common.ui.component.GridVariant
import com.example.myapplication.android.common.ui.component.KTIGridWithCards
import com.example.myapplication.android.common.ui.component.KTITopAppBar
import com.example.myapplication.model.domain.SubCategory
import com.example.myapplication.model.domain.TopCategory
import com.example.myapplication.feature.subcategories.SubCategoriesViewModel
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
                    KTITopAppBar(title = "${topCategory.displayName} categories")
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