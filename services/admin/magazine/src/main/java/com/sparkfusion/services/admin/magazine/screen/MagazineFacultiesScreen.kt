package com.sparkfusion.services.admin.magazine.screen

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
import com.sparkfusion.services.admin.magazine.viewmodel.FacultiesViewModel

@Composable
fun MagazineFacultiesScreen(
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
            FacultySelectionScreen(
                modifier = modifier,
                onBackClick = onBackClick,
                onFacultyClick = {
                    onFacultyClick((readingState as FacultiesViewModel.ReadingState.Success).data[it].id)
                },
                items = (readingState as FacultiesViewModel.ReadingState.Success).data.map { it.name }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MagazineFacultiesScreenPreview() {
    MagazineFacultiesScreen(
        onBackClick = {},
        onFacultyClick = {}
    )
}



















