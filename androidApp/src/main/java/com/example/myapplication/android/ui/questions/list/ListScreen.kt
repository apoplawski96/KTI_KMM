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
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Refresh
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
import com.example.myapplication.android.common.ui.component.KTIHorizontalSpacer
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
import com.example.myapplication.android.ui.theme.kti_light_primary
import com.example.myapplication.android.ui.theme.kti_primary_text
import com.example.myapplication.android.ui.theme.kti_secondary_text
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
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isAnswered.value) {
                    kti_green
                } else {
                    kti_light_primary.copy(alpha = 0.3f)
                }
            )
    ) {
        KTIVerticalSpacer(height = 8.dp)
        AnimatedVisibility(visible = isAnswered.value.not()) {
            QuestionDifficulty(difficulty = item.difficulty)
        }
        QuestionTitle(isAnswered = isAnswered.value, question = item.question)
        AnimatedVisibility(visible = isAnswered.value.not()) {
            ShowHideAnswerTextButton(
                displayAnswer = isExpanded.value,
                displayAnswerOnClick = { isExpanded.value = !isExpanded.value },
            )
        }
        AnimatedVisibility(isExpanded.value) {
            KTITextNew(
                text = item.answer,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                modifier = Modifier.padding(horizontal = horizontalPadding + 2.dp)
            )
        }
        KTIVerticalSpacer(height = 24.dp)
        BottomSection(
            isAnswered = isAnswered.value,
            markAsAnswered = { question ->
                isAnswered.value = true
                isExpanded.value = false
                markAsAnswered.invoke(question)
            },
            markAsUnanswered = { question ->
                isAnswered.value = false
                isExpanded.value = false
                markAsUnanswered.invoke(question)
            },
            item = item,
        )
        KTIVerticalSpacer(height = 18.dp)
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = kti_divider.copy(alpha = 0.06f)
        )
    }
}

@Composable
private fun QuestionDifficulty(
    difficulty: Difficulty,
) {
    KTIVerticalSpacer(height = 12.dp)
    KTITextNew(
        text = difficulty.displayName,
        fontSize = 10.sp,
        fontWeight = FontWeight.W300,
        color = kti_primary_text.copy(alpha = 0.6f),
        modifier = Modifier.padding(horizontal = 10.dp)
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
        fontSize = if (isAnswered.not()) 20.sp else 16.sp,
        fontWeight = if (isAnswered.not()) FontWeight.SemiBold else FontWeight.Normal,
        modifier = Modifier.padding(horizontal = horizontalPadding + 2.dp),
        color = kti_primary_text
    )
    KTIVerticalSpacer(height = if (isAnswered.not()) 8.dp else 0.dp)
}

@Composable
private fun ShowHideAnswerTextButton(
    displayAnswer: Boolean,
    displayAnswerOnClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickableNoRipple { displayAnswerOnClick() }
                .padding(horizontal = horizontalPadding)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = if (displayAnswer) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = kti_accent_color.copy(alpha = 0.5f),
                modifier = Modifier.clickableNoRipple { displayAnswerOnClick() }
            )
            KTIHorizontalSpacer(width = 4.dp)
            KTITextNew(
                text = if (displayAnswer) "Hide answer" else "Show answer",
                fontSize = 14.sp,
                fontWeight = FontWeight.W300,
                modifier = Modifier.clickableNoRipple { displayAnswerOnClick() },
                color = kti_primary_text.copy(alpha = 0.8f)
            )
        }
        KTIVerticalSpacer(height = 8.dp)
    }
}

@Composable
private fun BottomSection(
    isAnswered: Boolean,
    markAsAnswered: (Question) -> Unit,
    markAsUnanswered: (Question) -> Unit,
    item: Question,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding + 8.dp)
    ) {
        SetAnsweredSection(
            isAnswered = isAnswered,
            toggleAsAnswered = { markAsAnswered(item) },
            toggleReopen = { markAsUnanswered(item) }
        )
        IconsSection(isAnswered = isAnswered)
    }
}

@Composable
private fun IconsSection(isAnswered: Boolean) {
    val color = if (isAnswered.not()) {
        kti_accent_color.copy(alpha = 0.8f)
    } else {
        kti_primary_text
    }
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
//        Icon(
//            imageVector = Icons.Outlined.Share,
//            contentDescription = null,
//            tint = color,
//            modifier = Modifier
//                .clickable {}
//                .size(16.dp)
//        )
//        KTIHorizontalSpacer(width = 16.dp)
//        Icon(
//            imageVector = Icons.Filled.MailOutline,
//            contentDescription = null,
//            tint = color,
//            modifier = Modifier
//                .clickable {}
//                .size(16.dp)
//        )
    }
}

@Composable
private fun SetAnsweredSection(
    isAnswered: Boolean,
    toggleAsAnswered: () -> Unit,
    toggleReopen: () -> Unit,
) {
    val onClick = { if (isAnswered.not()) toggleAsAnswered.invoke() else toggleReopen.invoke() }
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        KTITextNew(
            text = if (isAnswered) "Reopen" else "Set as answered",
            fontSize = 14.sp,
            fontWeight = if (isAnswered) FontWeight.W500 else FontWeight.W400,
            color = if (isAnswered.not()) kti_secondary_text else kti_primary_text,
            modifier = Modifier.clickableNoRipple { onClick.invoke() },
        )
        KTIHorizontalSpacer(width = 8.dp)
        Icon(
            imageVector = if (isAnswered.not()) Icons.Outlined.Done else Icons.Outlined.Refresh,
            contentDescription = "Settings icon",
            tint = if (isAnswered.not()) kti_green.copy(alpha = 0.5f) else kti_primary_text,
            modifier = Modifier
                .size(12.dp)
                .clickableNoRipple { onClick.invoke() }
        )
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

