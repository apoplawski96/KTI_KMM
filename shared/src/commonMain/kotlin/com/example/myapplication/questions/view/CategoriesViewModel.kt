package com.example.myapplication.questions.view

import co.apoplawski96.kti.navigation.Navigator
import com.example.myapplication.questions.model.DeprecatedCategory
import co.touchlab.kampkit.models.ViewModel
import com.example.myapplication.questions.model.subcategory.TopCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CategoriesViewModel(private val navigator: Navigator) : ViewModel() {

    sealed interface ViewState {
        object Loading : ViewState
        data class CategoriesLoaded(val categories: List<TopCategory>) : ViewState
    }

    private val _state: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    fun initialize() {
        _state.update {
            ViewState.CategoriesLoaded(
                categories = TopCategory.values().toList()
            )
        }
    }

    fun categorySelected(category: TopCategory) {
        // navigate to list screen
    }
}