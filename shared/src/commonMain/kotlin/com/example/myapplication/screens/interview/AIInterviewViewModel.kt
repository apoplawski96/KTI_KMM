package com.example.myapplication.screens.interview

import co.touchlab.kampkit.models.ViewModel
import com.example.myapplication.screens.interview.domain.AIInterviewQuestionsRepository
import com.example.myapplication.screens.interview.domain.model.AIQuestion
import com.example.myapplication.screens.interview.domain.model.Role
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AIInterviewViewModel(
    private val aiInterviewQuestionsRepository: AIInterviewQuestionsRepository,
) : ViewModel() {

    sealed interface ViewState {
        object Idle : ViewState
        object Loading : ViewState
        object Error : ViewState
        data class QuestionLoaded(val question: AIQuestion) : ViewState
    }

    sealed interface ViewEvent {
        object GenerateQuestion : ViewEvent
    }

    private var role: Role? = null

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Idle)
    val viewState: StateFlow<ViewState> = _viewState

    fun initialize(role: Role) {
        this.role = role
    }

    fun onEventHandle(event: ViewEvent) {
        when(event) {
            ViewEvent.GenerateQuestion -> {
                _viewState.update { ViewState.Loading }
                viewModelScope.launch {
                    val role = this@AIInterviewViewModel.role
                    if (role != null) {
                        when(val question = aiInterviewQuestionsRepository.promptForQuestion(role)) {
                            is AIInterviewQuestionsRepository.Result.Error -> {
                                _viewState.update { ViewState.Error }
                            }
                            is AIInterviewQuestionsRepository.Result.Success -> {
                                _viewState.update { ViewState.QuestionLoaded(question.question) }
                            }
                        }
                    } else {
                        throw Exception("Role has not been initialized.")
                    }
                }
            }
        }
    }
}