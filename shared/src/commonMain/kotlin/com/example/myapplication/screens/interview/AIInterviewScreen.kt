package com.example.myapplication.screens.interview

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
import cafe.adriel.voyager.core.screen.Screen
import com.example.myapplication.SharedRes
import com.example.myapplication.compose.KTIButton
import com.example.myapplication.compose.KTICircularProgressIndicator
import com.example.myapplication.compose.KTIIcon
import com.example.myapplication.compose.KTIIconButton
import com.example.myapplication.compose.KTIIllustration
import com.example.myapplication.compose.KTITextButton
import com.example.myapplication.compose.KTITextNew
import com.example.myapplication.compose.KTITopAppBar
import com.example.myapplication.compose.KTIVerticalSpacer
import com.example.myapplication.compose.clickableNoRipple
import com.example.myapplication.di.getScreenModel
import com.example.myapplication.model.Role
import com.example.myapplication.model.schema.AIQuestionSchema
import com.example.myapplication.theme.KTITheme
import com.example.myapplication.theme.kti_accent
import com.example.myapplication.theme.kti_soft_black
import com.example.myapplication.theme.kti_soft_white

class AIInterviewScreen(val role: Role) : Screen {

    @Composable
    override fun Content() {
        val viewModel: AIInterviewScreenModel = getScreenModel()

        val viewState = viewModel.viewState.collectAsState().value
        val isLoading = viewModel.isLoading.collectAsState().value

        LaunchedEffect(null) {
            viewModel.initialize(role)
        }

        AIInterviewScreenContent(
            viewState = viewState,
            onGenerateQuestionClick = {
                viewModel.onEventHandle(AIInterviewScreenModel.ViewEvent.GenerateQuestion)
            },
            isLoading = isLoading
        )
    }
}

@Composable
private fun AIInterviewScreenContent(
    viewState: AIInterviewScreenModel.ViewState,
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
                AIInterviewScreenModel.ViewState.Error -> {
                    Text(text = "Error :(")
                }

                is AIInterviewScreenModel.ViewState.QuestionLoaded -> {
                    Text(text = viewState.question.content)
                }

                is AIInterviewScreenModel.ViewState.SimulationActive -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(
                                rememberScrollState()
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        KTIIllustration(imageResource = SharedRes.images.undraw_interview_re_e5jn)
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
                                imageResource = SharedRes.images.ic_sort,
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