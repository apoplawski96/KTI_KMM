@file:OptIn(ExperimentalMaterialApi::class)

package com.example.myapplication.android.ui.questions.list

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.common.ui.component.KTIBoxWithGradientBackground
import com.example.myapplication.android.common.ui.component.KTICircularProgressIndicator
import com.example.myapplication.android.common.ui.component.KTIText
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.common.ui.component.KTIVerticalSpacer
import com.example.myapplication.android.common.ui.component.bottomsheet.base.KTIModalBottomSheetLayout
import com.example.myapplication.android.common.ui.component.clickableNoRipple
import com.example.myapplication.android.ui.questions.list.components.ListScreenBottomSheetContent
import com.example.myapplication.android.ui.questions.list.components.ListScreenScoreBar
import com.example.myapplication.android.ui.questions.list.components.ListScreenTopBar
import com.example.myapplication.android.ui.theme.kti_accent_color
import com.example.myapplication.android.ui.theme.kti_dark_primary
import com.example.myapplication.android.ui.theme.kti_divider
import com.example.myapplication.android.ui.theme.kti_green
import com.example.myapplication.android.ui.theme.kti_primary_text
import com.example.myapplication.model.Difficulty
import com.example.myapplication.model.Question
import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory
import com.example.myapplication.screens.list.ListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun ListScreen(
    viewModel: ListViewModel = getViewModel(),
    topCategory: TopCategory?,
    subCategory: SubCategory?,
) {
    if (topCategory == null) return

    val scope = rememberCoroutineScope()

    val viewState = viewModel.viewState.collectAsState().value
    val selectedDifficulties = viewModel.selectedDifficulties.collectAsState().value
    val scoreboard = viewModel.scoreboard.collectAsState().value

    val bottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    var sortDropdownMenuDisplayed by remember { mutableStateOf(false) }

    val subCategoryTitle = subCategory?.displayName ?: "All"

    LaunchedEffect(null) {
        viewModel.viewEvents.collect { event ->
            when (event) {
                ListViewModel.ViewEvent.ToggleBottomSheet -> {
                    toggleBottomSheet(
                        scope = scope,
                        bottomSheetState = bottomSheetState,
                    )
                }
            }
        }
    }

    LaunchedEffect(null) {
        viewModel.initialize(
            subCategory = subCategory,
            topCategory = topCategory,
        )
    }

    ListScreenContent(
        viewState = viewState,
        bottomSheetContent = {
            ListScreenBottomSheetContent(
                selectedDifficulties = selectedDifficulties,
                onDifficultyToggled = { toggledDifficulty ->
                    viewModel.toggleDifficulty(toggledDifficulty)
                }
            )
        },
        bottomSheetState = bottomSheetState,
        topBarTitle = subCategoryTitle,
        onToggleBottomSheetClick = { viewModel.toggleBottomSheet() },
        toggleDropdownMenu = { sortDropdownMenuDisplayed = !sortDropdownMenuDisplayed },
        sortDropdownMenuDisplayed = sortDropdownMenuDisplayed,
        onSortModeClick = { sortMode -> viewModel.sortModeSelected(sortMode) },
        questionsAnsweredCount = scoreboard.answeredCount,
        questionsTotalCount = scoreboard.totalCount,
        markAsAnswered = { question -> viewModel.markQuestionAsAnswered(question) },
        markAsUnanswered = { question -> viewModel.markQuestionAsUnanswered(question) },
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ListScreenContent(
    viewState: ListViewModel.ViewState,
    bottomSheetState: ModalBottomSheetState,
    bottomSheetContent: @Composable () -> Unit,
    onToggleBottomSheetClick: () -> Unit,
    topBarTitle: String,
    sortDropdownMenuDisplayed: Boolean,
    toggleDropdownMenu: () -> Unit,
    onSortModeClick: (ListViewModel.SortMode) -> Unit,
    markAsAnswered: (Question) -> Unit,
    markAsUnanswered: (Question) -> Unit,
    questionsAnsweredCount: Int,
    questionsTotalCount: Int,
) {
    Scaffold(
        topBar = {
            ListScreenTopBar(
                bottomSheetState = bottomSheetState,
                onToggleBottomSheetClick = onToggleBottomSheetClick,
                topBarTitle = topBarTitle,
                sortDropdownMenuDisplayed = sortDropdownMenuDisplayed,
                toggleDropdownMenu = toggleDropdownMenu,
                onSortModeClick = onSortModeClick
            )
        },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(kti_dark_primary),
        ) {
            KTIModalBottomSheetLayout(
                sheetState = bottomSheetState,
                bottomSheetContent = bottomSheetContent,
                modifier = Modifier.fillMaxSize(),
            ) {
                KTIBoxWithGradientBackground {
                    when (viewState) {
                        is ListViewModel.ViewState.QuestionsLoaded -> {
                            QuestionList(
                                questions = viewState.questions,
                                markAsAnswered = markAsAnswered,
                                markAsUnanswered = markAsUnanswered,
                                questionsTotalCount = questionsTotalCount,
                                questionsAnsweredCount = questionsAnsweredCount
                            )
                        }

                        is ListViewModel.ViewState.Loading -> {
                            KTICircularProgressIndicator()
                        }

                        ListViewModel.ViewState.Error -> {
                            KTIText(text = "Error!")
                        }
                    }
                }
            }
        }

    }
}

private val horizontalPadding = 8.dp

@Composable
private fun QuestionList(
    questions: List<Question>,
    markAsAnswered: (Question) -> Unit,
    markAsUnanswered: (Question) -> Unit,
    questionsAnsweredCount: Int,
    questionsTotalCount: Int,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        ListScreenScoreBar(score = questionsAnsweredCount, total = questionsTotalCount)
        LazyColumn {
            itemsIndexed(
                items = questions,
                key = { _, item -> "${item.id} + ${item.hashCode()}" }
            ) { _, item ->
                QuestionItem(
                    item = item,
                    markAsAnswered = markAsAnswered,
                    markAsUnanswered = markAsUnanswered,
                )
            }
        }
    }
}

@Composable
private fun QuestionItem(
    item: Question,
    markAsAnswered: (Question) -> Unit,
    markAsUnanswered: (Question) -> Unit,
) {
    val isExpanded = rememberSaveable(item) { mutableStateOf(false) }
    val isAnswered = rememberSaveable(item) { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isAnswered.value) {
                    kti_green
                } else {
                    kti_dark_primary
                }
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(8f)
            ) {
                KTIVerticalSpacer(height = 4.dp)
                QuestionTopSection(question = item)
                QuestionTitle(isAnswered = isAnswered.value, question = item.question)
                AnimatedVisibility(visible = isExpanded.value) {
                    Column {
                        KTIVerticalSpacer(height = 2.dp)
                        KTITextNew(
                            text = item.answer,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400,
                            modifier = Modifier.padding(horizontal = horizontalPadding + 2.dp),
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.5f)
                    .defaultMinSize(minHeight = 52.dp),
                contentAlignment = Alignment.Center
            ) {
                if (isAnswered.value.not()) {
                    ToggleAnswerButton(
                        shouldDisplayAnswer = isExpanded.value,
                        displayAnswerOnClick = { isExpanded.value = !isExpanded.value },
                    )
                } else {
                    IconButton(onClick = {
                        isAnswered.value = false
                        isExpanded.value = false
                        markAsUnanswered.invoke(item)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "Reopen question",
                            tint = kti_dark_primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
        AnimatedVisibility(visible = isExpanded.value || isAnswered.value) {
            Column(modifier = Modifier.fillMaxWidth()) {
                BottomSection(
                    isAnswered = isAnswered.value,
                    markAsAnswered = { question ->
                        isAnswered.value = true
                        isExpanded.value = false
                        markAsAnswered.invoke(question)
                    },
                    item = item,
                )
            }
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = kti_divider.copy(alpha = 0.4f)
        )
    }
}

@Composable
private fun QuestionTopSection(
    question: Question,
) {
    KTITextNew(
        text = "${question.topCategory.displayName}, ${question.subCategory?.displayName} (Difficulty: ${question.difficulty.displayName})", // todo: check subcategory
        fontSize = 10.sp,
        fontWeight = FontWeight.W300,
        color = kti_primary_text.copy(alpha = 0.6f),
        modifier = Modifier.padding(horizontal = 10.dp),
        lineHeight = 6.sp,
    )
}

@Composable
private fun QuestionTitle(
    isAnswered: Boolean,
    question: String,
) {
    KTIVerticalSpacer(height = if (isAnswered.not()) 2.dp else 0.dp)
    KTITextNew(
        text = question,
        fontSize = 14.sp,
        fontWeight = if (isAnswered.not()) FontWeight.SemiBold else FontWeight.Normal,
        modifier = Modifier.padding(horizontal = horizontalPadding + 2.dp),
        color = if (isAnswered.not()) kti_primary_text else kti_dark_primary,
        lineHeight = 14.sp,
    )
    KTIVerticalSpacer(height = if (isAnswered.not()) 4.dp else 0.dp)
}

@Composable
private fun ToggleAnswerButton(
    shouldDisplayAnswer: Boolean,
    displayAnswerOnClick: () -> Unit,
) {
    Icon(
        imageVector = if (shouldDisplayAnswer) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
        contentDescription = null,
        tint = if (shouldDisplayAnswer) kti_accent_color else kti_primary_text,
        modifier = Modifier
            .clickableNoRipple { displayAnswerOnClick() }
            .size(18.dp)
    )
}

@Composable
private fun BottomSection(
    isAnswered: Boolean,
    markAsAnswered: (Question) -> Unit,
    item: Question,
) {
    if (isAnswered.not()) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = horizontalPadding + 8.dp,
                    end = horizontalPadding + 8.dp,
                    top = 12.dp,
                    bottom = 2.dp
                )
        ) {
            MarkAsAnsweredButton(
                toggleAsAnswered = { markAsAnswered(item) },
            )
        }
    }
}

@Composable
private fun MarkAsAnsweredButton(
    toggleAsAnswered: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(onClick = toggleAsAnswered) {
            KTITextNew(
                text = "Mark as answered",
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                color = kti_green,
            )
        }
    }
}

@ExperimentalMaterialApi
private fun toggleBottomSheet(
    scope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
) {
    scope.launch {
        when (bottomSheetState.currentValue) {
            ModalBottomSheetValue.Hidden -> bottomSheetState.show()
            ModalBottomSheetValue.Expanded,
            ModalBottomSheetValue.HalfExpanded -> bottomSheetState.hide()
        }
    }
}

