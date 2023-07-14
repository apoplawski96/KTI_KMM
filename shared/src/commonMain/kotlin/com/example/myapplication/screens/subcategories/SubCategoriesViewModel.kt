package com.example.myapplication.screens.subcategories

import co.touchlab.kampkit.models.ViewModel
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.navigation.model.Destinations
import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SubCategoriesViewModel(
    private val navigator: Navigator,
    private val subCategoriesRepository: SubCategoriesRepository,
) : ViewModel() {

    sealed interface ViewState {
        object Loading : ViewState
        data class SubCategoriesLoaded(val categories: List<SubCategory>) : ViewState
    }

    private val _state: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    fun initialize(category: TopCategory) {
        _state.update {
            val subCategories = subCategoriesRepository.invoke(category)
            ViewState.SubCategoriesLoaded(subCategories)
        }
    }

    fun navigateToQuestionsList(
        topCategory: TopCategory,
        subCategory: SubCategory
    ) {
        navigator.navigate(
            destination = Destinations.QuestionsList.destination(
                topCategoryId = topCategory.id,
                subCategoryId = subCategory.id
            )
        )
    }
}