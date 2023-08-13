package com.example.myapplication.android.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.R
import com.example.myapplication.android.common.ui.component.KTIHorizontalSpacer
import com.example.myapplication.android.common.ui.component.KTIIllustration
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.common.ui.component.KTIVerticalSpacer
import com.example.myapplication.android.common.ui.component.applyColor
import com.example.myapplication.android.common.ui.component.KTICardItem
import com.example.myapplication.android.common.ui.component.KTICardSmallWithUnderText
import com.example.myapplication.android.common.ui.component.KTICardWithIllustration
import com.example.myapplication.android.common.ui.component.KTITopAppBar
import com.example.myapplication.android.theme.KTITheme
import com.example.myapplication.model.domain.SubCategory
import com.example.myapplication.model.domain.TopCategory
import com.example.myapplication.model.domain.HomeScreenFeedItem
import com.example.myapplication.model.domain.HomeScreenMenuItem
import com.example.myapplication.feature.home.HomeScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = getViewModel()) {
    val viewState = viewModel.viewState.collectAsState().value

    LaunchedEffect(null) {
        viewModel.initialize()
    }

    HomeScreenContent(
        state = viewState,
        onMenuItemClicked = { item -> viewModel.onItemClicked(item) },
        onSubCategoryClick = { subCategory -> }
    )
}

@Composable
private fun HomeScreenContent(
    state: HomeScreenViewModel.ViewState,
    onMenuItemClicked: (HomeScreenMenuItem) -> Unit,
    onSubCategoryClick: (SubCategory) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KTITheme.colors.backgroundSurface)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        KTITopAppBar(isNested = false)
        HelloSection()
        KTIVerticalSpacer(height = 32.dp)
        IllustrationSection()
        when (state) {
            is HomeScreenViewModel.ViewState.HomeItems -> {
                HomeScreenFeedSection(
                    feed = state.items,
                    onMenuItemClicked = onMenuItemClicked,
                    onSubCategoryClick = onSubCategoryClick
                )
            }
            is HomeScreenViewModel.ViewState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun HelloSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        KTITextNew(
            text = "Hello candidate",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = KTITheme.colors.textMain
        )
        KTITextNew(
            text = "It's time to prepare for your next interview!",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = KTITheme.colors.textVariant2
        )
    }
}

@Composable
private fun IllustrationSection() {
    KTIIllustration(drawableRes = R.drawable.undraw_certificate_re_yadi, modifier = Modifier.height(256.dp))
}

@Composable
private fun HomeScreenFeedSection(
    feed: List<HomeScreenFeedItem>,
    onMenuItemClicked: (HomeScreenMenuItem) -> Unit,
    onSubCategoryClick: (SubCategory) -> Unit,
) {
    // TODO: Migrate to LazyColumn when feed grows
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        feed.forEach { feedItem ->
            when (feedItem) {
                is HomeScreenFeedItem.MenuItems -> {
                    MenuItems(items = feedItem.items, onItemClicked = onMenuItemClicked)
                }
                is HomeScreenFeedItem.RandomSubCategoriesCarousel -> {
                    RandomSubCategoriesCarousel(
                        onSubCategoryClick = onSubCategoryClick,
                        subCategories = feedItem.subCategories,
                        topCategory = feedItem.topCategory
                    )
                }
                is HomeScreenFeedItem.LastLearnedSubCategoriesCarousel -> {}
                is HomeScreenFeedItem.LastLearnedSubCategory -> {}
                is HomeScreenFeedItem.RandomBookmarkedQuestion -> {}
            }
        }
    }
}

@Composable
private fun MenuItems(
    items: List<HomeScreenMenuItem>,
    onItemClicked: (HomeScreenMenuItem) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        items.forEach { homeItem ->
            KTICardWithIllustration(
                item = KTICardItem(value = homeItem, label = homeItem.displayName),
                onClick = onItemClicked,
                fontWeight = FontWeight.W500,
                illustrationResId = when(homeItem) {
                    HomeScreenMenuItem.QUESTIONS_QUIZ -> R.drawable.undraw_engineering_team_a7n2
                    HomeScreenMenuItem.QUESTIONS_CATEGORIES -> R.drawable.undraw_educator_re_ju47
                }
            )
            KTIVerticalSpacer(height = 12.dp)
        }
    }
}

@Composable
private fun RandomSubCategoriesCarousel(
    onSubCategoryClick: (SubCategory) -> Unit,
    subCategories: List<SubCategory>,
    topCategory: TopCategory,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        KTITextNew(
            text = "Categories for ${topCategory.displayName}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow {
            item { KTIHorizontalSpacer(width = 16.dp) }
            itemsIndexed(items = subCategories, key = { _, subCategory -> subCategory.id }) { index, subCategory ->
                KTICardSmallWithUnderText(
                    item = KTICardItem(value = subCategory, label = subCategory.displayName).applyColor(index),
                    onClick = onSubCategoryClick
                )
            }
            item { KTIHorizontalSpacer(width = 16.dp) }
        }
    }
}

