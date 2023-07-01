package com.example.myapplication.questions.view

import co.apoplawski96.kti.questions.domain.interactors.GetQuestionsShuffled
import com.example.myapplication.questions.model.DeprecatedCategory
import co.apoplawski96.kti.questions.model.Question
import co.touchlab.kampkit.models.ViewModel
import com.example.myapplication.questions.domain.new.NewQuestionsRepository
import com.example.myapplication.questions.model.subcategory.SubCategory
import com.example.myapplication.questions.model.subcategory.TopCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ListViewModel(
    private val getQuestionsShuffled: GetQuestionsShuffled,
    private val newQuestionsRepository: NewQuestionsRepository,
) : ViewModel() {

    sealed interface ViewState {
        object Loading : ViewState
        data class QuestionsLoaded(val questions: List<Question>) : ViewState
    }

    private val _state: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    fun initialize(subCategory: SubCategory, category: TopCategory) {
        _state.update {
            val questions = newQuestionsRepository.getQuestions(
                subCategory = subCategory,
                topCategory = category,
            )
            ViewState.QuestionsLoaded(questions)
        }
    }

    fun getShuffledQuestionsList() {
        _state.update {
            ViewState.QuestionsLoaded(
                questions = getQuestionsShuffled.invoke().filter { question ->
                    question.category == DeprecatedCategory.Android
                }
            )
        }
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