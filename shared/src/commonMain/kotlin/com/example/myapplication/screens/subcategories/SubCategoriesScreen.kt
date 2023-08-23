package com.example.myapplication.screens.subcategories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.myapplication.compose.GridVariant
import com.example.myapplication.compose.KTIBoxWithGradientBackground
import com.example.myapplication.compose.KTICardItem
import com.example.myapplication.compose.KTICircularProgressIndicator
import com.example.myapplication.compose.KTIGridWithCards
import com.example.myapplication.compose.KTITopAppBar
import com.example.myapplication.di.getScreenModel
import com.example.myapplication.model.SubCategory
import com.example.myapplication.model.TopCategory
import com.example.myapplication.screens.list.QuestionsListScreen

class SubCategoriesScreen(private val topCategory: TopCategory?) : Screen {

    @Composable
    override fun Content() {
        val viewModel: SubCategoriesScreenModel = getScreenModel()
        val navigator = LocalNavigator.currentOrThrow

        if (topCategory == null) return

        val viewState = viewModel.state.collectAsState().value
        val lazyGridState = rememberLazyGridState()

        LaunchedEffect(null) {
            viewModel.initialize(topCategory)
        }

        SubCategoriesScreenContent(
            state = viewState,
            onClick = { subCategory ->
                navigator.push(
                    QuestionsListScreen(topCategory, subCategory)
                )
            },
            lazyGridState = lazyGridState,
            topCategory = topCategory,
        )
    }
}
@Composable
fun SubCategoriesScreenContent(
    state: SubCategoriesScreenModel.ViewState,
    onClick: (SubCategory?) -> Unit,
    lazyGridState: LazyGridState,
    topCategory: TopCategory,
) {
    KTIBoxWithGradientBackground {
        when (state) {
            is SubCategoriesScreenModel.ViewState.Loading -> {
                KTICircularProgressIndicator()
            }

            is SubCategoriesScreenModel.ViewState.SubCategoriesLoaded -> {
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