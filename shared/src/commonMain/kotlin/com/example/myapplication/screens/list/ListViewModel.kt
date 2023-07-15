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

    private val _sortMode: MutableSharedFlow<SortMode> = MutableSharedFlow()
    private val sortMode: SharedFlow<SortMode> = _sortMode

    private val _selectedDifficulties: MutableStateFlow<List<Difficulty>> = MutableStateFlow(Difficulty.values().toList())
    val selectedDifficulties: StateFlow<List<Difficulty>> = _selectedDifficulties

    private val _viewEvents: MutableSharedFlow<ViewEvent> = MutableSharedFlow()
    val viewEvents: SharedFlow<ViewEvent> = _viewEvents

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    fun initialize(topCategory: TopCategory, subCategory: SubCategory?) {
        val result = when (val questions = getQuestions.invoke(topCategory, subCategory)) {
            is GetQuestions.Result.Success -> ViewState.QuestionsLoaded(questions.questions.sortedBy { it.difficulty })
            is GetQuestions.Result.Error -> ViewState.Error
        }

        _viewState.update { result }

        collectSelectedDifficultiesUpdates()
        collectSortModeUpdates()
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

    private fun collectSelectedDifficultiesUpdates() {
        val viewState = viewState.value
        viewModelScope.launch {
            selectedDifficulties.collect { selectedDifficulties ->
                if (viewState is ViewState.QuestionsLoaded) {
                    val filteredQuestions = viewState.questions.filter { question ->
                        selectedDifficulties.contains(question.difficulty)
                    }
                    _viewState.update { ViewState.QuestionsLoaded(filteredQuestions) }
                }
            }
        }
    }

    private fun collectSortModeUpdates() {
        val viewState = viewState.value
        viewModelScope.launch {
            sortMode.collect { sortMode ->
                if (viewState is ViewState.QuestionsLoaded) {
                    val questions = viewState.questions
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
}