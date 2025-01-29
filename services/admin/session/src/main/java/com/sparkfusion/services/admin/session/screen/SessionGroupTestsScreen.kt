package com.sparkfusion.services.admin.session.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.sparkfusion.services.admin.session.R
import com.sparkfusion.services.admin.session.component.OutlinedDropDownMenu
import com.sparkfusion.services.admin.session.component.TestItemComponent
import com.sparkfusion.services.admin.session.viewmodel.SessionGroupTestsViewModel

@Composable
fun SessionGroupTestsScreen(
    modifier: Modifier = Modifier,
    viewModel: SessionGroupTestsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onAddItemClick: (Int, Int) -> Unit,
    onEditItemClick: (Int) -> Unit
) {
    val groupReadingState by viewModel.groupReadingState.collectAsStateWithLifecycle()
    val examReadingState by viewModel.examReadingState.collectAsStateWithLifecycle()
    val deletingState by viewModel.deletingState.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()

    var isExamDeletingMode by remember { mutableStateOf(false) }
    var isTestDeletingMode by remember { mutableStateOf(false) }

    if (examReadingState == SessionGroupTestsViewModel.ExamReadingState.Error) {
        ShowToast(value = "Error")
        viewModel.clearExamReadingState()
    }

    when (deletingState) {
        SessionGroupTestsViewModel.DeletingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearDeletingState()
        }

        SessionGroupTestsViewModel.DeletingState.Initial -> {}
        SessionGroupTestsViewModel.DeletingState.Progress -> {
            ShowToast(value = "Deleting...")
        }
    }

    LazyColumn(
        modifier = modifier
    ) {
        item {
            TopBar(
                title = "Group",
                onBackClick = onBackClick,
                buttons = {
                    if (isTestDeletingMode || isExamDeletingMode) {
                        IconButton(onClick = { viewModel.deleteExams() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.clock_icon),
                                contentDescription = null
                            )
                        }
                    }
                }
            )

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 20.dp),
                content = "Group",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            OutlinedDropDownMenu(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp),
                items = groupReadingState,
                selectedItem = if (groupReadingState.isEmpty()) null else
                    if (state.groupId == -1) groupReadingState[0] else groupReadingState.find { it.id == state.groupId },
                onItemSelected = {
                    viewModel.updateGroupId(it.id)
                },
                onValueChange = {
                    viewModel.updateGroupName(it)
                    viewModel.readGroupsByNamePart()
                }
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 2.dp, start = 24.dp, end = 24.dp),
                content = "Select a group to view information*",
                color = descriptionColor()
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SFProRoundedText(
                    modifier = Modifier.padding(top = 40.dp, start = 24.dp, end = 24.dp),
                    content = "Tests",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                if (isTestDeletingMode) {
                    TextButton(onClick = {
                        isTestDeletingMode = false
                        viewModel.clearSelectedItems()
                    }) {
                        SFProRoundedText(
                            modifier = Modifier.padding(top = 40.dp, end = 24.dp),
                            content = "Cancel",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        when (examReadingState) {
            SessionGroupTestsViewModel.ExamReadingState.Error -> {}
            SessionGroupTestsViewModel.ExamReadingState.Initial -> {}
            SessionGroupTestsViewModel.ExamReadingState.Progress -> {
                item { CircularProgressIndicator() }
            }

            is SessionGroupTestsViewModel.ExamReadingState.Success -> {
                val data =
                    (examReadingState as SessionGroupTestsViewModel.ExamReadingState.Success).tests
                items(data) {
                    TestItemComponent(
                        item = it,
                        index = data.indexOf(it) + 1,
                        isDeletingModeEnabled = isTestDeletingMode,
                        isItemSelected = state.selectedExams.contains(it.id),
                        onItemSelectionChange = {
                            if (state.selectedExams.contains(it.id)) viewModel.deleteSelectedExam(it.id)
                            else viewModel.addSelectedExam(it.id)
                        },
                        onLongClick = {
                            if (!isExamDeletingMode) isTestDeletingMode = true
                        },
                        onItemClick = {
                            onEditItemClick(it.id)
                        }
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
                if (state.groupId != -1) {
                    TextButton(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .align(Alignment.CenterHorizontally),
                        onClick = { onAddItemClick(state.groupId, 2) }
                    ) {
                        SFProRoundedText(
                            content = "Add",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SFProRoundedText(
                        modifier = Modifier.padding(top = 40.dp, start = 24.dp, end = 24.dp),
                        content = "Exams",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    if (isExamDeletingMode) {
                        TextButton(onClick = {
                            isExamDeletingMode = false
                            viewModel.clearSelectedItems()
                        }) {
                            SFProRoundedText(
                                modifier = Modifier.padding(top = 40.dp, end = 24.dp),
                                content = "Cancel",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        when (examReadingState) {
            SessionGroupTestsViewModel.ExamReadingState.Error -> {}
            SessionGroupTestsViewModel.ExamReadingState.Initial -> {}
            SessionGroupTestsViewModel.ExamReadingState.Progress -> {
                item { CircularProgressIndicator() }
            }

            is SessionGroupTestsViewModel.ExamReadingState.Success -> {
                val data =
                    (examReadingState as SessionGroupTestsViewModel.ExamReadingState.Success).exams
                items(data) {
                    TestItemComponent(
                        item = it,
                        index = data.indexOf(it) + 1,
                        isDeletingModeEnabled = isExamDeletingMode,
                        isItemSelected = state.selectedExams.contains(it.id),
                        onItemSelectionChange = {
                            if (state.selectedExams.contains(it.id)) viewModel.deleteSelectedExam(it.id)
                            else viewModel.addSelectedExam(it.id)
                        },
                        onLongClick = {
                            if (!isTestDeletingMode) isExamDeletingMode = true
                        },
                        onItemClick = {
                            onEditItemClick(it.id)
                        }
                    )
                }
            }
        }

        if (state.groupId != -1) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    TextButton(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .align(Alignment.Center),
                        onClick = {
                            onAddItemClick(state.groupId, 1)
                        }
                    ) {
                        SFProRoundedText(
                            content = "Add",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SessionGroupTestsScreenPreview() {
    SessionGroupTestsScreen(
        onBackClick = {},
        onAddItemClick = { _, _ -> },
        onEditItemClick = {}
    )
}






















