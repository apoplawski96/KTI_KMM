package com.example.myapplication.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.myapplication.model.HomeScreenFeedItem
import com.example.myapplication.model.HomeScreenMenuItem
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.navigation.model.Destinations
import com.example.myapplication.feature.home.data.GetHomeScreenFeedItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val getHomeScreenFeedItems: GetHomeScreenFeedItems,
    private val navigator: Navigator,
) : ScreenModel {

    sealed interface ViewState {
        object Loading : ViewState
        data class HomeItems(val items: List<HomeScreenFeedItem>) : ViewState
    }

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    fun initialize() {
        coroutineScope.launch {
            _viewState.update {
                ViewState.HomeItems(items = getHomeScreenFeedItems.get())
            }
        }
    }

    fun onItemClicked(item: HomeScreenMenuItem) {
        val destination = when(item) {
            HomeScreenMenuItem.AI_INTERVIEW -> Destinations.AIInterview
            HomeScreenMenuItem.QUESTIONS_CATEGORIES -> Destinations.Categories
        }
        navigator.navigate(destination = destination)
    }
}