package com.example.myapplication.android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.R
import com.example.myapplication.android.common.ui.component.KTIBackButton
import com.example.myapplication.android.common.ui.component.KTIIcon
import com.example.myapplication.android.common.ui.component.KTIIconButton
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.common.ui.component.bottomsheet.model.KTICard
import com.example.myapplication.android.common.ui.component.bottomsheet.model.KTICardItem
import com.example.myapplication.android.ui.theme.KTITheme
import com.example.myapplication.screens.home.HomeScreenItem
import com.example.myapplication.screens.home.HomeScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = getViewModel()) {
    val viewState = viewModel.viewState.collectAsState().value

    LaunchedEffect(null) {
        viewModel.initialize()
    }

    HomeScreenContent(
        state = viewState,
        onItemClicked = { item -> viewModel.onItemClicked(item) }
    )
}

@Composable
private fun HomeScreenContent(
    state: HomeScreenViewModel.ViewState,
    onItemClicked: (HomeScreenItem) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KTITheme.colors.backgroundSurface),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBarSection()
        HelloSection()
        when (state) {
            is HomeScreenViewModel.ViewState.HomeItems -> {
                MenuItems(items = state.items, onItemClicked = onItemClicked)
            }

            is HomeScreenViewModel.ViewState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun TopBarSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        KTIBackButton()
        TopBarIconsSection()
    }
}

@Composable
private fun TopBarIconsSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        KTIIconButton(onClick = { }) {
            KTIIcon(drawableRes = R.drawable.ic_bookmarks)
        }
        KTIIconButton(onClick = { }) {
            KTIIcon(drawableRes = R.drawable.ic_user)
        }
        KTIIconButton(onClick = { }) {
            KTIIcon(drawableRes = R.drawable.ic_menu)
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
            text = "Hello candidate!",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = KTITheme.colors.textMain
        )
        KTITextNew(
            text = "Prepare to kill your next interview",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = KTITheme.colors.textVariant2
        )
    }
}

@Composable
private fun MenuItems(
    items: List<HomeScreenItem>,
    onItemClicked: (HomeScreenItem) -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        items.forEach { homeItem ->
            KTICard(
                item = KTICardItem(value = homeItem, label = homeItem.displayName),
                onClick = onItemClicked,
                padding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeScreenContent(
        state = HomeScreenViewModel.ViewState.HomeItems(HomeScreenItem.values().toList()),
        onItemClicked = {}
    )
}