package com.example.myapplication.screens.categories

import co.touchlab.kampkit.models.ViewModel
import com.example.myapplication.model.subcategory.TopCategory
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.navigation.model.Destinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CategoriesViewModel(
    private val categoriesRepository: CategoriesRepository,
    private val navigator: Navigator,
) : ViewModel() {

    sealed interface ViewState {
        object Loading : ViewState
        data class CategoriesLoaded(val categories: List<TopCategory>) : ViewState
    }

    private val _state: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    fun initialize() {
        _state.update {
            ViewState.CategoriesLoaded(
                categories = categoriesRepository.getTopCategories()
            )
        }
    }

    fun categorySelected(category: TopCategory) {
        if (category.subCategories.isEmpty().not()) {
            navigator.navigate(Destinations.SubCategories.destination(category.id))
        } else {
            navigator.navigate(
                Destinations.QuestionsList.destination(
                    topCategoryId = category.id,
                    subCategoryId = null
                )
            )
        }
    }
}