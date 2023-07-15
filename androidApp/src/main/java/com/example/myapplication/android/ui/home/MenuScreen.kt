package com.example.myapplication.android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.screens.home.HomeScreenViewModel
import com.example.myapplication.screens.home.HomeScreenItem
import org.koin.androidx.compose.getViewModel

@Composable
fun MenuScreen(viewModel: HomeScreenViewModel = getViewModel()) {
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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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
private fun MenuItems(
    items: List<HomeScreenItem>,
    onItemClicked: (HomeScreenItem) -> Unit,
) {
    LazyColumn {
        items(items = items) { item ->
            MenuItem(item = item, onItemClicked = onItemClicked)
        }
    }
}

@Composable
private fun MenuItem(
    item: HomeScreenItem,
    onItemClicked: (HomeScreenItem) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.Green)
    ) {
        Text(
            text = item.name,
            modifier = Modifier
                .clickable { onItemClicked(item) }
                .align(Alignment.Center)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}