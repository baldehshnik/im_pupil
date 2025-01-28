package com.sparkfusion.services.admin.students.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.students.R
import com.sparkfusion.services.admin.students.list_item.StudentItem
import com.sparkfusion.services.admin.students.viewmodel.StudentsViewModel

@Composable
fun StudentsScreen(
    modifier: Modifier = Modifier,
    viewModel: StudentsViewModel = hiltViewModel(),
    groupId: Int,
    onBackClick: () -> Unit,
    onStudentClick: (Int) -> Unit,
    onEditClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readGroupMembers(groupId)
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
                        onBackClick = onBackClick,
                        buttons = {
                            IconButton(onClick = onEditClick) {
                                Icon(
                                    painter = painterResource(id = R.drawable.round_edit),
                                    contentDescription = null
                                )
                            }
                        }
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StudentsScreenPreview() {
    StudentsScreen(
        groupId = 1,
        onBackClick = {},
        onStudentClick = {},
        onEditClick = {}
    )
}





















