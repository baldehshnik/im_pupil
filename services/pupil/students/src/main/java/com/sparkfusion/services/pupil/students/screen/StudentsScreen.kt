package com.sparkfusion.services.pupil.students.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.students.component.StudentItem
import com.sparkfusion.services.pupil.students.viewmodel.StudentsViewModel

@Composable
fun StudentsScreenEnter(
    modifier: Modifier = Modifier,
    onStudentClick: (id: Int) -> Unit,
    onBackClick: () -> Unit
) {
    StudentsScreen(
        modifier = modifier,
        onStudentClick = onStudentClick,
        onBackClick = onBackClick
    )
}

@Composable
private fun StudentsScreen(
    modifier: Modifier = Modifier,
    viewModel: StudentsViewModel = hiltViewModel(),
    onStudentClick: (id: Int) -> Unit,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readGroupMembers()
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    when (readingState) {
        StudentsViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        StudentsViewModel.ReadingState.Initial -> {}
        StudentsViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is StudentsViewModel.ReadingState.Success -> {
            val data = (readingState as StudentsViewModel.ReadingState.Success).data
            LazyColumn(
                modifier = modifier
            ) {
                item {
                    TopBar(
                        title = "Students",
                        onBackClick = onBackClick
                    )
                }

                items(data) {
                    StudentItem(
                        groupMemberModel = it,
                        onItemClick = { onStudentClick(it.id) }
                    )
                }
            }
        }
    }
}























