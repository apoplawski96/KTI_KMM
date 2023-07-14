@file:OptIn(ExperimentalMaterialApi::class)

package com.example.myapplication.android.ui.questions.list

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apoplawski96.killtheinterview.common.ui.component.KTIHorizontalSpacer
import com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.base.FcModalBottomSheetLayout
import com.example.myapplication.android.common.ui.component.FcTextTopBar
import com.example.myapplication.android.common.ui.component.KTICircularProgressIndicator
import com.example.myapplication.android.common.ui.component.KTIText
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.common.ui.component.clickableNoRipple
import com.example.myapplication.android.ui.theme.kti_accent_color
import com.example.myapplication.android.ui.theme.kti_dark_primary
import com.example.myapplication.android.ui.theme.kti_green
import com.example.myapplication.android.ui.theme.kti_light_primary
import com.example.myapplication.android.ui.theme.kti_primary
import com.example.myapplication.android.ui.theme.kti_primary_text
import com.example.myapplication.android.ui.theme.kti_secondary_text
import com.example.myapplication.model.Question
import com.example.myapplication.model.subcategory.SubCategory
import com.example.myapplication.model.subcategory.TopCategory
import com.example.myapplication.screens.list.ListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ListScreen(
    viewModel: ListViewModel = getViewModel(),
    topCategory: TopCategory?,
    subCategory: SubCategory?,
) {
    if (topCategory == null) return

    val viewState = viewModel.state.collectAsState().value

    val bottomSheetState: ModalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val subCategoryTitle = subCategory?.displayName ?: "All"
    val topBarTitle = "$subCategoryTitle (${topCategory.displayName})"

    LaunchedEffect(null) {
        viewModel.initialize(
            subCategory = subCategory,
            topCategory = topCategory,
        )
    }

    ListScreenContent(
        viewState = viewState,
        bottomSheetContent = { },
        bottomSheetState = bottomSheetState,
        topBarTitle = topBarTitle
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ListScreenContent(
    viewState: ListViewModel.ViewState,
    bottomSheetState: ModalBottomSheetState,
    bottomSheetContent: @Composable () -> Unit,
    topBarTitle: String,
) {
    Scaffold(
        topBar = {
            FcTextTopBar(
                middleContentText = topBarTitle,
                isNested = true,
                hasBrandingLine = true,
                rightActionButtons = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Settings icon",
                            tint = kti_primary_text)
                    }
                }
            )
        }
    ) {
        FcModalBottomSheetLayout(
            sheetState = bottomSheetState,
            bottomSheetContent = bottomSheetContent,
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(kti_dark_primary),
            ) {
                when (viewState) {
                    is ListViewModel.ViewState.QuestionsLoaded -> {
                        QuestionListItem(questions = viewState.questions)
                    }

                    is ListViewModel.ViewState.Loading -> {
                        KTICircularProgressIndicator()
                    }

                    ListViewModel.ViewState.Error -> {
                        KTIText(text = "error")
                    }
                }
            }
        }
    }
}

@Composable
private fun QuestionListItem(questions: List<Question>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(kti_dark_primary)
            .background(
                brush = Brush.verticalGradient(
                    0.0f to kti_dark_primary,
                    0.9f to kti_primary,
                    1.0f to kti_accent_color.copy(alpha = 0.0001f),
                )
            )
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            itemsIndexed(items = questions) { _, item ->
                val isExpanded = rememberSaveable(item) { mutableStateOf(false) }
                val isAnswered = rememberSaveable(item) { mutableStateOf(false) }

                val color =
                    if (isAnswered.value) kti_green else kti_light_primary.copy(alpha = 0.6f)

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color)
                        .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                ) {
                    KTIHorizontalSpacer(height = 8.dp)
                    KTITextNew(
                        text = item.question,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    KTIHorizontalSpacer(height = 8.dp)
                    ShowHideAnswerTextButton(
                        displayAnswer = isExpanded.value,
                        displayAnswerOnClick = { isExpanded.value = !isExpanded.value }
                    )
                    KTIHorizontalSpacer(height = 8.dp)
                    AnimatedVisibility(isExpanded.value) {
                        val answerText = if (item.answer.isEmpty().not()) {
                            item.answer
                        } else {
                            "ANSWER MISSING!"
                        }
                        KTITextNew(
                            text = answerText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400
                        )
                    }
                    KTIHorizontalSpacer(height = 16.dp)
                    KTITextNew(
                        text = if (isAnswered.value) "Not answered" else "Set as answered",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = kti_secondary_text,
                        modifier = Modifier.clickableNoRipple {
                            isAnswered.value = !isAnswered.value
                            isExpanded.value = !isAnswered.value
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ShowHideAnswerTextButton(
    displayAnswer: Boolean,
    displayAnswerOnClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { displayAnswerOnClick() }
    ) {
        Icon(
            imageVector = if (displayAnswer) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = kti_accent_color
        )
        KTITextNew(
            text = if (displayAnswer) "Hide answer" else "Display answer",
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            modifier = Modifier.clickableNoRipple { displayAnswerOnClick() },
            color = kti_primary_text.copy(alpha = 0.8f)
        )
    }
}

