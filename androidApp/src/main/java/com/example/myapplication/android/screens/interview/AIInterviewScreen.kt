package com.example.myapplication.android.screens.interview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.android.R
import com.example.myapplication.android.common.ui.component.KTIButton
import com.example.myapplication.android.common.ui.component.KTICircularProgressIndicator
import com.example.myapplication.android.common.ui.component.KTIIcon
import com.example.myapplication.android.common.ui.component.KTIIconButton
import com.example.myapplication.android.common.ui.component.KTIIllustration
import com.example.myapplication.android.common.ui.component.KTITextButton
import com.example.myapplication.android.common.ui.component.KTITextNew
import com.example.myapplication.android.common.ui.component.KTIVerticalSpacer
import com.example.myapplication.android.common.ui.component.KTITopAppBar
import com.example.myapplication.android.common.ui.component.clickableNoRipple
import com.example.myapplication.android.theme.KTITheme
import com.example.myapplication.android.theme.kti_accent
import com.example.myapplication.android.theme.kti_soft_black
import com.example.myapplication.android.theme.kti_soft_white
import com.example.myapplication.feature.interview.AIInterviewViewModel
import com.example.myapplication.model.schema.AIQuestionSchema
import com.example.myapplication.model.Role
import org.koin.androidx.compose.koinViewModel

@Composable
fun AIInterviewScreen(
    viewModel: AIInterviewViewModel = koinViewModel(),
    role: Role,
) {

    val viewState = viewModel.viewState.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    LaunchedEffect(null) {
        viewModel.initialize(role)
    }

    AIInterviewScreenContent(
        viewState = viewState,
        onGenerateQuestionClick = {
            viewModel.onEventHandle(AIInterviewViewModel.ViewEvent.GenerateQuestion)
        },
        isLoading = isLoading
    )
}

@Composable
private fun AIInterviewScreenContent(
    viewState: AIInterviewViewModel.ViewState,
    onGenerateQuestionClick: () -> Unit,
    isLoading: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(kti_soft_white)
    ) {
        KTITopAppBar(title = "AI Interview")
        KTIVerticalSpacer(height = 8.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            when (viewState) {
                AIInterviewViewModel.ViewState.Error -> {
                    Text(text = "Error :(")
                }

                is AIInterviewViewModel.ViewState.QuestionLoaded -> {
                    Text(text = viewState.question.content)
                }

                is AIInterviewViewModel.ViewState.SimulationActive -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(
                                rememberScrollState()
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        KTIIllustration(drawableRes = R.drawable.undraw_interview_re_e5jn)
                        KTIVerticalSpacer(height = 16.dp)
                        QuestionCard(aiQuestionSchema = viewState.question, isLoading = isLoading)
                        KTIButton(
                            label = "Generate next",
                            labelColor = kti_soft_black,
                            backgroundColor = kti_accent,
                            onClick = onGenerateQuestionClick,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun QuestionCard(
    aiQuestionSchema: AIQuestionSchema?,
    isLoading: Boolean,
) {
    val isExpanded = rememberSaveable(aiQuestionSchema) { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(size = 12.dp),
        backgroundColor = kti_soft_white,
//        border = BorderStroke(width = 0.5.dp, color = kti_grayish_light.copy(alpha = 0.2f)),
        modifier = Modifier
            .clickableNoRipple { }
            .fillMaxWidth(),
        elevation = 2.dp
    ) {
        AnimatedVisibility(visible = isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(36.dp), contentAlignment = Alignment.Center
            ) {
                KTICircularProgressIndicator()
            }
        }
        AnimatedVisibility(visible = isLoading.not()) {
            Column {
                KTITextNew(
                    text = aiQuestionSchema?.question
                        ?: ("Hello fellow candidate on this interview simulation.\n" +
                                "Use the button make AI ask you a question."),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier.padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 12.dp,
                        bottom = 8.dp
                    )
                )
                AnimatedVisibility(visible = aiQuestionSchema != null) {
                    Row {
                        KTITextButton(
                            onClick = { isExpanded.value = !isExpanded.value },
                            label = if (isExpanded.value) "Hide answer" else "Show answer",
                            labelColor = KTITheme.colors.textMain.copy(alpha = 0.8f),
                            size = 14.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        KTIIconButton(onClick = { isExpanded.value = !isExpanded.value }) {
                            KTIIcon(
                                drawableRes = com.google.android.material.R.drawable.mtrl_ic_arrow_drop_down,
                                tint = KTITheme.colors.secondary
                            )
                        }
                    }
                }
                AnimatedVisibility(visible = isExpanded.value) {
                    KTITextNew(
                        text = aiQuestionSchema?.answer ?: return@AnimatedVisibility,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 16.dp)
                    )
                }
            }
        }
    }
}