package com.example.myapplication.screens.home

import co.touchlab.kampkit.models.ViewModel
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.navigation.model.Destinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel(private val navigator: Navigator) : ViewModel() {

    sealed interface ViewState {
        object Loading : ViewState
        data class HomeItems(val items: List<HomeScreenItem>) : ViewState
    }

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    fun initialize() {
        _viewState.update {
            ViewState.HomeItems(
                items = listOf(
                    HomeScreenItem.QUESTIONS_QUIZ,
                    HomeScreenItem.QUESTIONS_CATEGORIES,
                )
            )
        }
    }

    fun onItemClicked(item: HomeScreenItem) {
        val destination = when(item) {
            HomeScreenItem.QUESTIONS_QUIZ -> Destinations.QuestionsQuiz
            HomeScreenItem.QUESTIONS_CATEGORIES -> Destinations.Categories
        }
        navigator.navigate(destination = destination)
    }
}