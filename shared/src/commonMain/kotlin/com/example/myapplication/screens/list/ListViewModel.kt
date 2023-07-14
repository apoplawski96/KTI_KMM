package com.example.myapplication.screens.list

import co.apoplawski96.kti.questions.domain.interactors.GetQuestionsShuffled
import co.touchlab.kampkit.models.ViewModel
import com.example.myapplication.domain.GetQuestions
import com.example.myapplication.model.DeprecatedCategory
import com.example.myapplication.model.Question
import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ListViewModel(private val getQuestions: GetQuestions) : ViewModel() {

    sealed interface ViewState {
        object Loading : ViewState
        object Error : ViewState
        data class QuestionsLoaded(val questions: List<Question>) : ViewState
    }

    private val _state: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    fun initialize(topCategory: TopCategory, subCategory: SubCategory?) {
        val result = when (val questions = getQuestions.invoke(topCategory, subCategory)) {
            is GetQuestions.Result.Success -> ViewState.QuestionsLoaded(questions.questions)
            is GetQuestions.Result.Error -> ViewState.Error
        }
        _state.update { result }
    }

    fun toggleCategory(category: DeprecatedCategory) {
        val viewStateValue = state.value
        if (viewStateValue is ViewState.QuestionsLoaded) {
            _state.update {
                viewStateValue.copy(
                    questions = viewStateValue.questions.filter { question ->
                        question.category == DeprecatedCategory.Android
                    }
                )
            }
        }
    }
}