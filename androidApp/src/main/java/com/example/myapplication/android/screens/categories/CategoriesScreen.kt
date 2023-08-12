package com.example.myapplication.android.screens.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.android.R
import com.example.myapplication.android.common.ui.component.KTIBoxWithGradientBackground
import com.example.myapplication.android.common.ui.component.KTICircularProgressIndicator
import com.example.myapplication.android.common.ui.component.KTICardItem
import com.example.myapplication.android.common.ui.component.GridVariant
import com.example.myapplication.android.common.ui.component.KTIGridWithCards
import com.example.myapplication.android.common.ui.component.KTIIllustration
import com.example.myapplication.android.common.ui.component.KTIVerticalSpacer
import com.example.myapplication.android.common.ui.component.KTITopBarNew
import com.example.myapplication.model.subcategory.TopCategory
import com.example.myapplication.screens.categories.CategoriesViewModel
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
        lazyGridState = lazyGridState,
        onClick = { category ->
            if (category is TopCategory) {
                viewModel.categorySelected(category)
            }
        },
    )
}

@Composable
private fun CategoriesScreenContent(
    state: CategoriesViewModel.ViewState,
    onClick: (TopCategory?) -> Unit,
    lazyGridState: LazyGridState
) {
    KTIBoxWithGradientBackground {
        when (state) {
            is CategoriesViewModel.ViewState.CategoriesLoaded -> {
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                    KTITopBarNew(title = "Categories")
                    KTIGridWithCards(
                        items = state.categories.map { topCategory ->
                            KTICardItem(
                                value = topCategory,
                                label = topCategory.displayName
                            )
                        },
                        onClick = onClick,
                        state = lazyGridState,
                        variant = GridVariant.TOP_CATEGORY,
                    )
                    KTIVerticalSpacer(height = 64.dp)
                    KTIIllustration(drawableRes = R.drawable.undraw_scientist_ft0o)
                }
            }

            is CategoriesViewModel.ViewState.Loading -> {
                KTICircularProgressIndicator()
            }
        }
    }
}