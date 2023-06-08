@file:OptIn(ExperimentalMaterialApi::class)

package co.touchlab.kampkit.android.ui.questions.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.apoplawski96.kti.questions.model.Question
import co.apoplawski96.kti.questions.view.ListViewModel
import com.apoplawski96.killtheinterview.common.ui.component.KTICircularProgressIndicator
import com.apoplawski96.killtheinterview.common.ui.component.KTIDestinationTopBar
import com.apoplawski96.killtheinterview.common.ui.component.KTIHorizontalSpacer
import com.apoplawski96.killtheinterview.common.ui.component.bottomsheet.base.FcModalBottomSheetLayout
import org.koin.androidx.compose.getViewModel

@Composable
fun ListScreen(viewModel: ListViewModel = getViewModel()) {

    val viewState = viewModel.state.collectAsState().value

    val bottomSheetState: ModalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    LaunchedEffect(null) {
        viewModel.getShuffledQuestionsList()
    }

    ListScreenContent(
        viewState = viewState,
        bottomSheetContent = { },
        bottomSheetState = bottomSheetState,
    )
}

@Composable
private fun ListScreenContent(
    viewState: ListViewModel.ViewState,
    bottomSheetState: ModalBottomSheetState,
    bottomSheetContent: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            KTIDestinationTopBar(
                title = "Random Questions",
                actions = {
                    IconButton(
                        onClick = {
                            // open bottom sheet
                        }
                    ) {
                        Icon(Icons.Filled.List, "Filter questions")
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
                    .background(Color.Gray),
            ) {
                when (viewState) {
                    is ListViewModel.ViewState.QuestionsLoaded -> {
                        QuestionListItem(questions = viewState.questions)
                    }
                    is ListViewModel.ViewState.Loading -> {
                        KTICircularProgressIndicator()
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
            .background(Color.Gray)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            itemsIndexed(items = questions) { _, item ->
                val isExpanded = rememberSaveable(item) { mutableStateOf(false) }
                val isAnswered = rememberSaveable(item) { mutableStateOf(false) }

                val color = if (isAnswered.value) Color.Green else Color.White

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color)
                        .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                ) {
                    KTIHorizontalSpacer(height = 8.dp)
                    Text(
                        text = item.question,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    KTIHorizontalSpacer(height = 8.dp)
                    ShowHideAnswerTextButton(
                        displayAnswer = isExpanded.value,
                        displayAnswerOnClick = { isExpanded.value = !isExpanded.value }
                    )
                    KTIHorizontalSpacer(height = 8.dp)
                    TextButton(
                        onClick = {
                            isAnswered.value = !isAnswered.value
                            isExpanded.value = !isAnswered.value
                        }
                    ) {
                        Text(text = if (isAnswered.value) "Not answered" else "Set as answered")
                    }
                    KTIHorizontalSpacer(height = 8.dp)
                    if (isExpanded.value) {
                        val answerText = if (item.answer.isEmpty().not()) {
                            item.answer
                        } else {
                            "ANSWER MISSING!"
                        }
                        Text(text = answerText)
                    }
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
            contentDescription = null
        )
        TextButton(onClick = displayAnswerOnClick) {
            Text(text = if (displayAnswer) "Hide answer" else "Display answer")
        }
    }
}

