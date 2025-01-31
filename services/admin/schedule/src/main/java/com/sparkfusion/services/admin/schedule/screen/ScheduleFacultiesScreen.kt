package com.sparkfusion.services.admin.schedule.screen

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.design.screen.faculty.FacultySelectionScreen
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.services.admin.schedule.viewmodel.FacultiesViewModel

@Composable
fun ScheduleFacultiesScreen(
    modifier: Modifier = Modifier,
    viewModel: FacultiesViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onFacultyClick: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.read()
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    when (readingState) {
        FacultiesViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        FacultiesViewModel.ReadingState.Initial -> {}
        FacultiesViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is FacultiesViewModel.ReadingState.Success -> {
            val data = (readingState as FacultiesViewModel.ReadingState.Success).data
            FacultySelectionScreen(
                modifier = modifier,
                items = data.map { it.name },
                onBackClick = onBackClick,
                onFacultyClick = {
                    onFacultyClick(data[it].id)
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScheduleFacultiesScreenPreview() {
    ScheduleFacultiesScreen(
        onBackClick = {},
        onFacultyClick = {}
    )
}





















