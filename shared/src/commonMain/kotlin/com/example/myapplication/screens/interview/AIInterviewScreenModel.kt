package com.example.myapplication.screens.interview

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.myapplication.feature.interview.data.AIInterviewQuestionsPrompter
import com.example.myapplication.model.schema.AIQuestion
import com.example.myapplication.model.schema.AIQuestionSchema
import com.example.myapplication.model.Role
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AIInterviewScreenModel(
    private val aiInterviewQuestionsPrompter: AIInterviewQuestionsPrompter,
) : ScreenModel {

    sealed interface ViewState {
        object Error : ViewState
        data class QuestionLoaded(val question: AIQuestion) : ViewState
        data class SimulationActive(val question: AIQuestionSchema?) : ViewState
    }

    sealed interface ViewEvent {
        object GenerateQuestion : ViewEvent
    }

    private var role: Role? = null

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.SimulationActive(question = null))
    val viewState: StateFlow<ViewState> = _viewState

    fun initialize(role: Role) {
        this.role = role
    }

    fun onEventHandle(event: ViewEvent) {
        when(event) {
            ViewEvent.GenerateQuestion -> {
                _isLoading.update { true }
                coroutineScope.launch {
                    val role = this@AIInterviewScreenModel.role
                    if (role != null) {
//                        when(val question = aiInterviewQuestionsRepository.promptForQuestionJson(role)) {
//                            is AIInterviewQuestionsRepository.Result.Error -> {
//                                _viewState.update { ViewState.Error }
//                            }
//                            is AIInterviewQuestionsRepository.Result.Success -> {
//                                _viewState.update { ViewState.QuestionLoaded(question.question) }
//                            }
//                        }
                        when(val question = aiInterviewQuestionsPrompter.promptForQuestionJson(role)) {
                            AIInterviewQuestionsPrompter.ResultJson.Error -> {
                                _viewState.update { ViewState.Error }
                                _isLoading.update { false }
                            }
                            is AIInterviewQuestionsPrompter.ResultJson.Success -> {
                                _viewState.update { ViewState.SimulationActive(question.question) }
                                _isLoading.update { false }
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