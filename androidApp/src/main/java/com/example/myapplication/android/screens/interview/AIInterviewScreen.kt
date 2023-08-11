package com.example.myapplication.android.screens.interview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.android.screens.theme.kti_soft_black
import com.example.myapplication.android.screens.theme.kti_soft_white
import com.example.myapplication.screens.interview.AIInterviewViewModel
import com.example.myapplication.screens.interview.domain.model.Role
import org.koin.androidx.compose.koinViewModel

@Composable
fun AIInterviewScreen(
    viewModel: AIInterviewViewModel = koinViewModel(),
    role: Role,
)  {

    val viewState = viewModel.viewState.collectAsState().value

    LaunchedEffect(null) {
        viewModel.initialize(role)
    }

    AIInterviewScreenContent(
        viewState = viewState,
        onButtonClick = {
            viewModel.onEventHandle(AIInterviewViewModel.ViewEvent.GenerateQuestion)
        }
    )
}

@Composable
private fun AIInterviewScreenContent(
    viewState: AIInterviewViewModel.ViewState,
    onButtonClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().background(kti_soft_white)) {
        Box(
            modifier = Modifier
                .weight(6f)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            when(viewState) {
                AIInterviewViewModel.ViewState.Error -> {
                    Text(text = "Error :(")
                }
                AIInterviewViewModel.ViewState.Idle -> {
                    Text(text = "Use button to generate interview question")
                }
                AIInterviewViewModel.ViewState.Loading -> {
                    CircularProgressIndicator()
                }
                is AIInterviewViewModel.ViewState.QuestionLoaded -> {
                    Text(text = viewState.question.content)
                }
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = onButtonClick) {
                Text(text = "Generate question", color = kti_soft_black)
            }
        }
    }
}