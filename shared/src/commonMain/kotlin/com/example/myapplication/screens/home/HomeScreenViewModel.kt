package com.example.myapplication.screens.home

import co.touchlab.kampkit.models.ViewModel
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.navigation.model.Destinations
import com.example.myapplication.screens.home.domain.GetHomeScreenFeedItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getHomeScreenFeedItems: GetHomeScreenFeedItems,
    private val navigator: Navigator,
) : ViewModel() {

    sealed interface ViewState {
        object Loading : ViewState
        data class HomeItems(val items: List<HomeScreenFeedItem>) : ViewState
    }

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    fun initialize() {
        viewModelScope.launch {
            _viewState.update {
                ViewState.HomeItems(items = getHomeScreenFeedItems.get())
            }
        }
    }

    fun onItemClicked(item: HomeScreenMenuItem) {
        val destination = when(item) {
            HomeScreenMenuItem.QUESTIONS_QUIZ -> {
                println("2137 - $this")
                return
            }
            HomeScreenMenuItem.QUESTIONS_CATEGORIES -> Destinations.Categories
        }
        navigator.navigate(destination = destination)
    }
}