package com.example.myapplication.questions.view

import com.example.myapplication.navigation.Navigator
import co.touchlab.kampkit.models.ViewModel
import com.example.myapplication.navigation.model.Destinations
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
        navigator.navigate(Destinations.SubCategories.destination(category.id))
    }
}