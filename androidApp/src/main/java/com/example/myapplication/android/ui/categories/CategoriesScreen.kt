package com.example.myapplication.android.ui.categories

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
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
import com.example.myapplication.android.common.ui.component.KTIHorizontalSpacer
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.common.ui.component.KTITextTopBar
import com.example.myapplication.android.common.ui.component.KTIVerticalSpacer
import com.example.myapplication.android.common.ui.component.clickableNoRipple
import com.example.myapplication.android.ui.theme.kti_accent
import com.example.myapplication.android.ui.theme.kti_grayish_light
import com.example.myapplication.android.ui.theme.kti_grayish
import com.example.myapplication.android.ui.theme.kti_soft_black
import com.example.myapplication.android.ui.theme.kti_soft_white
import com.example.myapplication.android.ui.theme.kti_text_icons
import com.example.myapplication.model.subcategory.CardDisplayable
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
    onClick: (CardDisplayable) -> Unit,
    lazyGridState: LazyGridState
) {
    KTIBoxWithGradientBackground {
        when (state) {
            is CategoriesViewModel.ViewState.CategoriesLoaded -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    KTITextTopBar(
                        middleContentText = "Categories",
                        isNested = false,
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
                    CategoriesGrid(
                        categories = state.categories,
                        onClick = onClick,
                        state = lazyGridState
                    )
                }
            }

            is CategoriesViewModel.ViewState.Loading -> {
                KTICircularProgressIndicator()
            }
        }
    }
}

@Composable
fun CategoriesGrid(
    categories: List<CardDisplayable>,
    onClick: (CardDisplayable) -> Unit,
    state: LazyGridState,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        state = state,
        content = {
            item { KTIVerticalSpacer(height = 8.dp) }
            item { KTIVerticalSpacer(height = 8.dp) }
            items(items = categories) { category ->
                CategoryCard(category = category, onClick = onClick)
            }
        }
    )
//    LazyColumn(content = {
//        items(items = categories) { category ->
//            CategoryCard(category = category, onClick = onClick)
//        }
//    })
}

@Composable
private fun CategoryCard(
    category: CardDisplayable,
    onClick: (CardDisplayable) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(size = 12.dp),
        backgroundColor = kti_soft_white,
//        border = BorderStroke(width = 0.5.dp, color = kti_grayish_light.copy(alpha = 0.2f)),
        modifier = Modifier
            .clickableNoRipple { onClick.invoke(category) }
            .padding(4.dp)
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
                text = category.displayName,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
                color = kti_soft_black
            )
        }
    }
}