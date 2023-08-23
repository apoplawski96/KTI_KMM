package com.example.myapplication.screens.subcategories

import cafe.adriel.voyager.core.model.ScreenModel
import com.example.myapplication.feature.subcategories.data.SubCategoriesRepository
import com.example.myapplication.model.SubCategory
import com.example.myapplication.model.TopCategory
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.navigation.model.Destinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SubCategoriesScreenModel(
    private val navigator: Navigator,
    private val subCategoriesRepository: SubCategoriesRepository,
) : ScreenModel {

    sealed interface ViewState {
        object Loading : ViewState
        data class SubCategoriesLoaded(val categories: List<SubCategory>) : ViewState
    }

    private val _state: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    fun initialize(category: TopCategory) {
        _state.update {
            val subCategories = subCategoriesRepository.getSubCategories(category)
            ViewState.SubCategoriesLoaded(subCategories)
        }
    }

    fun navigateToQuestionsList(
        topCategory: TopCategory,
        subCategory: SubCategory?
    ) {
        navigator.navigate(
            destination = Destinations.QuestionsList.destination(
                topCategoryId = topCategory.id,
                subCategoryId = subCategory?.id
            )
        )
    }
}