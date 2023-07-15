package com.example.myapplication.screens.list

import co.touchlab.kampkit.models.ViewModel
import com.example.myapplication.domain.GetQuestions
import com.example.myapplication.model.Difficulty
import com.example.myapplication.model.Question
import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel(private val getQuestions: GetQuestions) : ViewModel() {

    sealed interface ViewState {
        object Loading : ViewState
        object Error : ViewState
        data class QuestionsLoaded(val questions: List<Question>) : ViewState
    }

    sealed interface ViewEvent {
        object ToggleBottomSheet : ViewEvent
    }

    enum class SortMode(val displayName: String) {
        BY_DIFFICULTY_ASCENDING(displayName = "By difficulty ascending"),
        BY_DIFFICULTY_DESCENDING(displayName = "By difficulty descending"),
        RANDOMIZED(displayName = "Randomize order");
    }

    data class Scoreboard(val answeredCount: Int, val totalCount: Int)

    private val _answeredQuestions: MutableStateFlow<List<Question>> = MutableStateFlow(emptyList())
    private val answeredQuestions: StateFlow<List<Question>> = _answeredQuestions

    private val _sortMode: MutableSharedFlow<SortMode> = MutableSharedFlow()
    private val sortMode: SharedFlow<SortMode> = _sortMode

    private val _selectedDifficulties: MutableStateFlow<List<Difficulty>> = MutableStateFlow(Difficulty.values().toList())
    val selectedDifficulties: StateFlow<List<Difficulty>> = _selectedDifficulties

    private val _scoreboard: MutableStateFlow<Scoreboard> = MutableStateFlow(Scoreboard(0, 0))
    val scoreboard: StateFlow<Scoreboard> = _scoreboard

    private val _viewEvents: MutableSharedFlow<ViewEvent> = MutableSharedFlow()
    val viewEvents: SharedFlow<ViewEvent> = _viewEvents

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    fun initialize(topCategory: TopCategory, subCategory: SubCategory?) {
        val result = when (val questions = getQuestions.invoke(topCategory, subCategory)) {
            is GetQuestions.Result.Success -> {
                _scoreboard.update { scoreboard.value.copy(totalCount = questions.questions.count()) }
                ViewState.QuestionsLoaded(questions.questions.sortedBy { it.difficulty })
            }
            is GetQuestions.Result.Error -> {
                ViewState.Error
            }
        }

        _viewState.update { result }

        collectSelectedDifficultiesUpdates()
        collectSortModeUpdates()
        collectAnsweredQuestionsUpdates()
    }

    fun toggleBottomSheet() {
        viewModelScope.launch {
            _viewEvents.emit(ViewEvent.ToggleBottomSheet)
        }
    }

    fun toggleDifficulty(selectedDifficulty: Difficulty) {
        val isDifficultyAlreadySelected = selectedDifficulties.value.firstOrNull { difficulty ->
            selectedDifficulty == difficulty
        } != null

        if (isDifficultyAlreadySelected && selectedDifficulties.value.count() < 2) return

        val result = if (isDifficultyAlreadySelected) {
            selectedDifficulties.value.filterNot { difficulty ->
                selectedDifficulty == difficulty
            }
        } else {
            selectedDifficulties.value.toMutableList() + selectedDifficulty
        }

        _selectedDifficulties.update { result }
    }

    fun sortModeSelected(sortMode: SortMode) {
        viewModelScope.launch {
            _sortMode.emit(sortMode)
        }
    }

    fun markQuestionAsAnswered(question: Question) {
        if (answeredQuestions.value.contains(question)) return

        val updatedList = answeredQuestions.value + question
        _answeredQuestions.update { updatedList }
    }

    fun markQuestionAsUnanswered(question: Question) {
        if (answeredQuestions.value.contains(question).not()) return

        val updatedList = answeredQuestions.value.filterNot { it == question }
        _answeredQuestions.update { updatedList }
    }

    private fun collectSelectedDifficultiesUpdates() {
        val initialViewState = viewState.value
        viewModelScope.launch {
            selectedDifficulties.collect { selectedDifficulties ->
                if (initialViewState is ViewState.QuestionsLoaded) {
                    val filteredQuestions = initialViewState.questions.filter { question ->
                        selectedDifficulties.contains(question.difficulty)
                    }
                    _viewState.update { ViewState.QuestionsLoaded(filteredQuestions) }
                }
            }
        }
    }

    private fun collectSortModeUpdates() {
        viewModelScope.launch {
            sortMode.collect { sortMode ->
                val currentViewState = viewState.value
                if (currentViewState is ViewState.QuestionsLoaded) {
                    val questions = currentViewState.questions
                    val sortedQuestions = when(sortMode) {
                        SortMode.BY_DIFFICULTY_ASCENDING -> questions.sortedBy { it.difficulty }
                        SortMode.BY_DIFFICULTY_DESCENDING -> questions.sortedByDescending { it.difficulty }
                        SortMode.RANDOMIZED -> questions.shuffled()
                    }
                    _viewState.update { ViewState.QuestionsLoaded(sortedQuestions) }
                }
            }
        }
    }

    private fun collectAnsweredQuestionsUpdates() {
        viewModelScope.launch {
            answeredQuestions.collect { answeredQuestions ->
                _scoreboard.update { scoreboard.value.copy(answeredCount = answeredQuestions.count()) }
            }
        }
    }
}