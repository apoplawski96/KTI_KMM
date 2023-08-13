package com.example.myapplication.feature.quiz

import co.touchlab.kampkit.models.ViewModel
import com.example.myapplication._legacy.questions.questions
import com.example.myapplication._legacy.QuestionsRepository
import com.example.myapplication.model.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel(private val questionsRepository: QuestionsRepository) : ViewModel() {

    sealed interface MainScreenState {
        object Idle : MainScreenState
        object NoQuestionsLeft : MainScreenState
        data class QuestionAsked(val question: Question) : MainScreenState
    }

    private val mutableQuestions = questions.toMutableList()
    private val answeredQuestions = mutableListOf<Question>()

    private val _state: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState.Idle)
    val state: StateFlow<MainScreenState> = _state

    private val _displayAnswer: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val displayAnswer: StateFlow<Boolean> = _displayAnswer

    fun dropQuestion() {
        val state = when (val question = getRandomQuestionAndRemove()) {
            null -> MainScreenState.NoQuestionsLeft
            else -> MainScreenState.QuestionAsked(question)
        }

        _state.update { state }
        _displayAnswer.update { false }
    }

    fun toggleDisplayAnswer() {
        _displayAnswer.update { !displayAnswer.value }
    }

    fun resetQuestions() {
        resetQuestionsCollections()

        _state.update { MainScreenState.Idle }
        _displayAnswer.update { false }
    }

    fun getQuestionsCountString() =
        "${answeredQuestions.size}/${questionsRepository.getAllQuestions().size}"

    private fun resetQuestionsCollections() {
        answeredQuestions.clear()
        mutableQuestions.clear()
        mutableQuestions.addAll(questions.toMutableList())
    }

    private fun getRandomQuestionAndRemove(): Question? {
        val randomIndex = (0..mutableQuestions.size).random()
        val randomQuestion = mutableQuestions.getOrNull(randomIndex) ?: return null

        mutableQuestions.removeAt(randomIndex)
        answeredQuestions.add(randomQuestion)

        return randomQuestion
    }
}