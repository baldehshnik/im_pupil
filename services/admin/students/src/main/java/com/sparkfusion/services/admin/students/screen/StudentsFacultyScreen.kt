package com.sparkfusion.services.admin.students.screen

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
import com.sparkfusion.services.admin.students.viewmodel.FacultyViewModel

@Composable
fun StudentsFacultyScreen(
    modifier: Modifier = Modifier,
    viewModel: FacultyViewModel = hiltViewModel(),
    onGroupScreenNavigate: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readFaculties()
    }

    val facultyState by viewModel.facultyState.collectAsStateWithLifecycle()

    when (facultyState) {
        FacultyViewModel.FacultyState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearFacultyState()
        }

        FacultyViewModel.FacultyState.Initial -> {}
        FacultyViewModel.FacultyState.Progress -> {
            CircularProgressIndicator()
        }

        is FacultyViewModel.FacultyState.Success -> {
            val data = (facultyState as FacultyViewModel.FacultyState.Success).data
            FacultySelectionScreen(
                modifier = modifier,
                onBackClick = onBackClick,
                items = data.map { it.name },
                onFacultyClick = {
                    onGroupScreenNavigate(data[it].id)
                }
            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FacultyScreenPreview() {
    StudentsFacultyScreen(
        onGroupScreenNavigate = {},
        onBackClick = {}
    )
}




















