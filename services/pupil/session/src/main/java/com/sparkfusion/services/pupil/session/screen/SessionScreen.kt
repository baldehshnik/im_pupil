package com.sparkfusion.services.pupil.session.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.session.component.TestItemComponent
import com.sparkfusion.services.pupil.session.viewmodel.SessionViewModel

@Composable
fun SessionScreenEnter(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    SessionScreen(
        modifier = modifier,
        onBackClick = onBackClick
    )
}

@Composable
internal fun SessionScreen(
    modifier: Modifier = Modifier,
    viewModel: SessionViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readExams()
    }

    val examReadingState by viewModel.examReadingState.collectAsStateWithLifecycle()

    if (examReadingState == SessionViewModel.ExamReadingState.Error) {
        ShowToast(value = "Error")
        viewModel.clearExamReadingState()
    }

    LazyColumn(
        modifier = modifier
    ) {
        item {
            TopBar(
                title = "Session",
                onBackClick = onBackClick
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 40.dp, start = 24.dp, end = 24.dp),
                content = "Tests",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        when (examReadingState) {
            SessionViewModel.ExamReadingState.Error -> {}
            SessionViewModel.ExamReadingState.Initial -> {}
            SessionViewModel.ExamReadingState.Progress -> {
                item { CircularProgressIndicator() }
            }

            is SessionViewModel.ExamReadingState.Success -> {
                val data =
                    (examReadingState as SessionViewModel.ExamReadingState.Success).tests
                items(data) {
                    TestItemComponent(
                        item = it,
                        index = data.indexOf(it) + 1
                    )
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                SFProRoundedText(
                    modifier = Modifier.padding(top = 40.dp, start = 24.dp, end = 24.dp),
                    content = "Exams",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        when (examReadingState) {
            SessionViewModel.ExamReadingState.Error -> {}
            SessionViewModel.ExamReadingState.Initial -> {}
            SessionViewModel.ExamReadingState.Progress -> {
                item { CircularProgressIndicator() }
            }

            is SessionViewModel.ExamReadingState.Success -> {
                val data = (examReadingState as SessionViewModel.ExamReadingState.Success).exams
                items(data) {
                    TestItemComponent(
                        item = it,
                        index = data.indexOf(it) + 1
                    )
                }
            }
        }
    }
}




























