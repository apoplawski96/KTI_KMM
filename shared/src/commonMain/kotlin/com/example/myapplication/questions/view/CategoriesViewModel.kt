package com.example.myapplication.questions.view

import co.apoplawski96.kti.navigation.Navigator
import co.apoplawski96.kti.questions.model.Category
import co.touchlab.kampkit.models.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CategoriesViewModel(private val navigator: Navigator) : ViewModel() {

    sealed interface ViewState {
        object Loading : ViewState
        data class CategoriesLoaded(val categories: List<Category>) : ViewState
    }

    private val _state: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    fun getCategories() {
        _state.update {
            ViewState.CategoriesLoaded(
                categories = Category.values().toList()
            )
        }
    }

    fun categorySelected(category: Category) {
        // navigate to list screen
    }
}