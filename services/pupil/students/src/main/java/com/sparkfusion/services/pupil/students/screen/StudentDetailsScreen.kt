package com.sparkfusion.services.pupil.students.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.students.component.AccountComponent
import com.sparkfusion.services.pupil.students.component.AccountInfoComponent
import com.sparkfusion.services.pupil.students.viewmodel.StudentDetailsViewModel

@Composable
fun StudentDetailsScreenEnter(
    modifier: Modifier = Modifier,
    studentId: Int,
    onBackClick: () -> Unit
) {
    StudentDetailsScreen(
        modifier = modifier,
        studentId = studentId,
        onBackClick = onBackClick
    )
}

@Composable
private fun StudentDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: StudentDetailsViewModel = hiltViewModel(),
    studentId: Int,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readGroupMember(studentId)
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    when (readingState) {
        StudentDetailsViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        StudentDetailsViewModel.ReadingState.Initial -> {}
        StudentDetailsViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is StudentDetailsViewModel.ReadingState.Success -> {
            val data = (readingState as StudentDetailsViewModel.ReadingState.Success).model

            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TopBar(
                    title = "Students",
                    onBackClick = onBackClick
                )

                AccountComponent(
                    icon = data.pupil?.icon,
                    name = "${data.lastname} ${data.firstname} ${data.patronymic} (${data.code})",
                    status = if (data.isPrefect) "prefect" else {
                        "student"
                    } + if (data.pupil != null) " (registered)" else ""
                )

                AccountInfoComponent(
                    modifier = Modifier.padding(top = 20.dp, start = 24.dp),
                    title = "Institution",
                    value = data.educationPlaceDto?.institutionName ?: "Not found"
                )

                AccountInfoComponent(
                    modifier = Modifier.padding(top = 12.dp, start = 24.dp),
                    title = "Faculty",
                    value = data.educationPlaceDto?.facultyName ?: "Not found"
                )

                AccountInfoComponent(
                    modifier = Modifier.padding(top = 12.dp, start = 24.dp),
                    title = "Group",
                    value = data.educationPlaceDto?.groupName ?: "Not found"
                )
            }
        }
    }
}





















