package com.example.myapplication.android.ui.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.android.common.ui.component.KTIBoxWithGradientBackground
import com.example.myapplication.android.common.ui.component.KTICircularProgressIndicator
import com.example.myapplication.android.common.ui.component.KTIHorizontalSpacer
import com.example.myapplication.android.common.ui.component.KTITextTopBar
import com.example.myapplication.android.common.ui.component.bottomsheet.model.KTICardItem
import com.example.myapplication.android.common.ui.component.bottomsheet.model.GridVariant
import com.example.myapplication.android.common.ui.component.bottomsheet.model.KTIGridWithCards
import com.example.myapplication.android.ui.theme.kti_accent
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
                Column(modifier = Modifier.fillMaxSize()) {
                    KTITextTopBar(
                        middleContentText = "Categories",
                        isNested = true,
                        hasBrandingLine = true,
                        rightActionButtons = {
                            Icon(
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = "",
                                tint = kti_accent,
                                modifier = Modifier.size(24.dp)
                            )
                            KTIHorizontalSpacer(width = 8.dp)
                        })
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
                }
            }

            is CategoriesViewModel.ViewState.Loading -> {
                KTICircularProgressIndicator()
            }
        }
    }
}