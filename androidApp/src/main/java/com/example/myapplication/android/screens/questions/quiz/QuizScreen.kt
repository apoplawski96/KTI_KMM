// package com.example.killtheinterview.ui.questions.quiz
//
// import androidx.compose.foundation.clickable
// import androidx.compose.foundation.layout.*
// import androidx.compose.material.Button
// import androidx.compose.material.Icon
// import androidx.compose.material.Text
// import androidx.compose.material.TextButton
// import androidx.compose.material.icons.Icons
// import androidx.compose.material.icons.filled.KeyboardArrowDown
// import androidx.compose.material.icons.filled.KeyboardArrowUp
// import androidx.compose.runtime.Composable
// import androidx.compose.runtime.collectAsState
// import androidx.compose.ui.Alignment
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.text.font.FontStyle
// import androidx.compose.ui.text.font.FontWeight
// import androidx.compose.ui.unit.Dp
// import androidx.compose.ui.unit.dp
// import androidx.compose.ui.unit.sp
// import org.koin.androidx.compose.getViewModel
//
// @Composable
// fun QuizScreen(viewModel: QuizViewModel = getViewModel()) {
//     val state = viewModel.state.collectAsState()
//     val displayAnswer = viewModel.displayAnswer.collectAsState()
//
//     MainScreenContent(
//         screenState = state.value,
//         displayAnswer = displayAnswer.value,
//         dropQuestionOnClick = { viewModel.dropQuestion() },
//         displayAnswerOnClick = { viewModel.toggleDisplayAnswer() },
//         resetQuestions = { viewModel.resetQuestions() },
//         getAllQuestionsCount = { viewModel.getQuestionsCountString() }
//     )
// }
//
// @Composable
// private fun MainScreenContent(
//     screenState: QuizViewModel.MainScreenState,
//     displayAnswer: Boolean,
//     dropQuestionOnClick: () -> Unit,
//     displayAnswerOnClick: () -> Unit,
//     resetQuestions: () -> Unit,
//     getAllQuestionsCount: () -> String,
// ) {
//     Box(modifier = Modifier.fillMaxSize()) {
//         when (screenState) {
//             is QuizViewModel.MainScreenState.Idle -> {
//                 IdleScreen(
//                     dropQuestionOnClick = dropQuestionOnClick,
//                     modifier = Modifier.align(Alignment.Center),
//                 )
//             }
//             is QuizViewModel.MainScreenState.NoQuestionsLeft -> {
//                 NoQuestionsLeftScreen(
//                     resetQuestions = resetQuestions,
//                     modifier = Modifier.align(Alignment.Center),
//                 )
//             }
//             is QuizViewModel.MainScreenState.QuestionAsked -> {
//                 QuizScreen(
//                     screenState = screenState,
//                     dropQuestionOnClick = dropQuestionOnClick,
//                     displayAnswerOnClick = displayAnswerOnClick,
//                     displayAnswer = displayAnswer,
//                     getAllQuestionsCount = getAllQuestionsCount,
//                 )
//             }
//         }
//     }
// }
//
// @Composable
// private fun IdleScreen(dropQuestionOnClick: () -> Unit, modifier: Modifier = Modifier) {
//     Button(onClick = { dropQuestionOnClick() }, modifier = modifier) {
//         Text(text = "Drop the questions!")
//     }
// }
//
// @Composable
// private fun NoQuestionsLeftScreen(resetQuestions: () -> Unit, modifier: Modifier = Modifier) {
//     Button(onClick = { resetQuestions() }, modifier = modifier) {
//         Text(text = "No questions left, reset!")
//     }
// }
//
// @Composable
// private fun QuizScreen(
//     screenState: QuizViewModel.MainScreenState.QuestionAsked,
//     dropQuestionOnClick: () -> Unit,
//     displayAnswerOnClick: () -> Unit,
//     displayAnswer: Boolean,
//     getAllQuestionsCount: () -> String
// ) {
//     Column(
//         verticalArrangement = Arrangement.Top,
//         horizontalAlignment = Alignment.Start,
//         modifier = Modifier
//             .fillMaxSize()
//             .padding(16.dp)
//     ) {
//         HorizontalSpacer(height = 24.dp)
//         Text(
//             text = "Question ${getAllQuestionsCount.invoke()}",
//             fontStyle = FontStyle.Italic,
//             fontWeight = FontWeight.Light
//         )
//         HorizontalSpacer(height = 16.dp)
//         Text(
//             text = screenState.question.question,
//             fontSize = 20.sp,
//             fontWeight = FontWeight.SemiBold
//         )
//         HorizontalSpacer(height = 8.dp)
//         ShowHideAnswerTextButton(
//             displayAnswer = displayAnswer,
//             displayAnswerOnClick = displayAnswerOnClick
//         )
//         HorizontalSpacer(height = 16.dp)
//         if (displayAnswer) {
//             Text(text = screenState.question.answer)
//         }
//         HorizontalSpacer(height = 24.dp)
//         Button(onClick = { dropQuestionOnClick() }, modifier = Modifier.fillMaxWidth()) {
//             Text(text = "Next one!")
//         }
//     }
// }
//
// @Composable
// private fun HorizontalSpacer(height: Dp) {
//     Spacer(modifier = Modifier.height(height))
// }
//
// @Composable
// private fun ShowHideAnswerTextButton(
//     displayAnswer: Boolean,
//     displayAnswerOnClick: () -> Unit,
// ) {
//     Row(
//         horizontalArrangement = Arrangement.Start,
//         verticalAlignment = Alignment.CenterVertically,
//         modifier = Modifier.clickable { displayAnswerOnClick() }
//     ) {
//         Icon(
//             imageVector = if (displayAnswer) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
//             contentDescription = null
//         )
//         TextButton(onClick = displayAnswerOnClick) {
//             Text(text = if (displayAnswer) "Hide answer" else "Display answer")
//         }
//     }
// }
