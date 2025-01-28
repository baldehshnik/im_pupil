package com.sparkfusion.services.admin.students.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.students.R
import com.sparkfusion.services.admin.students.component.AddStudentButtonComponent
import com.sparkfusion.services.admin.students.component.CourseSpinnerWithTitleComponent
import com.sparkfusion.services.admin.students.component.FloatingButtonComponent
import com.sparkfusion.services.admin.students.dialog.InputDialog
import com.sparkfusion.services.admin.students.list_item.UpdateStudentItem
import com.sparkfusion.services.admin.students.viewmodel.EditGroupViewModel

@Composable
fun EditGroupScreen(
    modifier: Modifier = Modifier,
    viewModel: EditGroupViewModel = hiltViewModel(),
    groupId: Int,
    onBackClick: () -> Unit,
    onDeleteSuccess: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readGroup(groupId)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val readingState by viewModel.readingState.collectAsStateWithLifecycle()
    val updatingState by viewModel.updatingState.collectAsStateWithLifecycle()
    val deletingState by viewModel.deletingState.collectAsStateWithLifecycle()

    var courseExpanded by remember { mutableStateOf(false) }
    var showInputDialog by remember { mutableStateOf(false) }
//    var showUpdateDialog by remember { mutableStateOf(false) }

    val courses = listOf(1, 2, 3, 4, 5, 6)

    when (deletingState) {
        EditGroupViewModel.DeletingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearDeletingState()
        }

        EditGroupViewModel.DeletingState.Initial -> {}
        EditGroupViewModel.DeletingState.Progress -> {
            ShowToast(value = "Deleting...")
        }

        EditGroupViewModel.DeletingState.Success -> {
            onDeleteSuccess()
        }
    }

    when (updatingState) {
        EditGroupViewModel.UpdatingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearUpdatingState()
        }

        EditGroupViewModel.UpdatingState.Initial -> {}
        EditGroupViewModel.UpdatingState.Progress -> {
            ShowToast(value = "Updating...")
        }

        EditGroupViewModel.UpdatingState.Success -> {
            onBackClick()
        }
    }

    if (showInputDialog) {
        InputDialog(
            onDismiss = { showInputDialog = false },
            onSave = {
                viewModel.addStudent(it)
            }
        )
    }

//    if (showUpdateDialog) {
//        if (viewModel.currentStudentIndex >= 0) {
//            val model = state.groupMemberDtos[viewModel.currentStudentIndex]
//            UpdateDialog(
//                onDismiss = { showUpdateDialog = false },
//                idInitial = model.id,
//                firstnameInitial = model.firstname,
//                lastnameInitial = model.lastname,
//                patronymicInitial = model.patronymic,
//                codeInitial = model.code,
//                onSave = {
//                    viewModel.updateStudent(it)
//                }
//            )
//        }
//    }

    when (readingState) {
        EditGroupViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        EditGroupViewModel.ReadingState.Initial -> {}
        EditGroupViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        EditGroupViewModel.ReadingState.Success -> {
            Box(
                modifier = modifier.fillMaxSize()
            ) {
                FloatingButtonComponent(
                    painter = painterResource(id = R.drawable.round_check),
                    onClick = { viewModel.update() }
                )

                LazyColumn {
                    item {
                        TopBar(
                            title = "Students",
                            onBackClick = onBackClick,
                            buttons = {
                                IconButton(onClick = { viewModel.delete(groupId) }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.round_delete_outline),
                                        contentDescription = null
                                    )
                                }
                            }
                        )

                        SFProRoundedText(
                            modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp),
                            content = "If the field you want to change is not listed below, the only way to change it is by deleting the entry and creating a new one.",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = descriptionColor()
                        )

                        CourseSpinnerWithTitleComponent(
                            items = courses,
                            currentItem = state.course,
                            title = "Course",
                            expanded = courseExpanded,
                            onDismiss = { courseExpanded = false },
                            onExpanded = { courseExpanded = true },
                            onItemClick = {
                                viewModel.updateCourse(it)
                            }
                        )

                        SFProRoundedText(
                            modifier = Modifier.padding(top = 30.dp, start = 24.dp),
                            content = "Students",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                    }

                    items(state.groupMemberDtos) {
                        UpdateStudentItem(
                            model = it,
                            onDeleteClick = { viewModel.removeStudent(it) }
                        )
                    }

                    item {
                        AddStudentButtonComponent(
                            onClick = { showInputDialog = true }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EditGroupScreenPreview() {
    EditGroupScreen(
        groupId = 1,
        onBackClick = {},
        onDeleteSuccess = {}
    )
}





















