package com.sparkfusion.services.admin.students.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
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
import com.sparkfusion.services.admin.students.component.SpinnerWithTitleComponent
import com.sparkfusion.services.admin.students.dialog.InputDialog
import com.sparkfusion.services.admin.students.list_item.AddStudentItem
import com.sparkfusion.services.admin.students.viewmodel.AddGroupViewModel

@Composable
fun AddGroupScreen(
    modifier: Modifier = Modifier,
    viewModel: AddGroupViewModel = hiltViewModel(),
    facultyId: Int,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readSpecialities(facultyId)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val creatingState by viewModel.creatingState.collectAsStateWithLifecycle()
    val checkState by viewModel.checkState.collectAsStateWithLifecycle()
    val specialityState by viewModel.specialityState.collectAsStateWithLifecycle()

    var specialityExpanded by remember { mutableStateOf(false) }
    var courseExpanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val courses = listOf(1, 2, 3, 4, 5, 6)

    when (checkState) {
        AddGroupViewModel.CheckState.CourseNotSelected -> {
            ShowToast(value = "Course not selected")
            viewModel.clearCheckState()
        }

        AddGroupViewModel.CheckState.Initial -> {}
        AddGroupViewModel.CheckState.NameIsTooShort -> {
            ShowToast(value = "Name is too short")
            viewModel.clearCheckState()
        }

        AddGroupViewModel.CheckState.SpecialityNotSelected -> {
            ShowToast(value = "Speciality not selected")
            viewModel.clearCheckState()
        }
    }

    when (creatingState) {
        AddGroupViewModel.CreatingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearCreatingState()
        }

        AddGroupViewModel.CreatingState.Initial -> {}
        AddGroupViewModel.CreatingState.Progress -> {
            ShowToast(value = "Creating...")
        }

        AddGroupViewModel.CreatingState.Success -> {
            onBackClick()
        }
    }

    if (showDialog) {
        InputDialog(
            onDismiss = { showDialog = false },
            onSave = {
                viewModel.addStudent(it)
            }
        )
    }

    when (specialityState) {
        AddGroupViewModel.SpecialityState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearSpecialityState()
        }

        AddGroupViewModel.SpecialityState.Initial -> {}
        AddGroupViewModel.SpecialityState.Progress -> {
            CircularProgressIndicator()
        }

        is AddGroupViewModel.SpecialityState.Success -> {
            val specialityData = (specialityState as AddGroupViewModel.SpecialityState.Success).data
            var currentItem by remember { mutableStateOf(specialityData[0]) }
            viewModel.updateSpecialityId(specialityData[0].id)

            Box(modifier = modifier.fillMaxSize()) {
                FloatingButtonComponent(
                    painter = painterResource(id = R.drawable.round_check),
                    onClick = { viewModel.create() }
                )

                LazyColumn {
                    item {
                        TopBar(
                            title = "Adding",
                            onBackClick = onBackClick
                        )

                        SpinnerWithTitleComponent(
                            title = "Speciality",
                            items = specialityData,
                            onDismiss = { specialityExpanded = false },
                            expanded = specialityExpanded,
                            currentItem = currentItem,
                            onItemClick = {
                                currentItem = it
                                viewModel.updateSpecialityId(it.id)
                            },
                            onExpanded = { specialityExpanded = true }
                        )

                        CourseSpinnerWithTitleComponent(
                            items = courses,
                            currentItem = state.course,
                            title = "Course",
                            expanded = courseExpanded,
                            onDismiss = { courseExpanded = false },
                            onExpanded = { courseExpanded = true },
                            onItemClick = {
                                viewModel.updateCurse(it)
                            }
                        )

                        SFProRoundedText(
                            modifier = Modifier.padding(top = 40.dp, start = 24.dp),
                            content = "Group",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )

                        OutlinedTextField(
                            modifier = Modifier
                                .padding(start = 24.dp, end = 24.dp, top = 8.dp)
                                .fillMaxWidth(),
                            value = state.name,
                            onValueChange = { viewModel.updateName(it) },
                            shape = RoundedCornerShape(20.dp),
                            placeholder = {
                                SFProRoundedText(
                                    content = "Enter name here",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        )

                        SFProRoundedText(
                            modifier = Modifier.padding(start = 24.dp, top = 2.dp),
                            content = "This field is unique and cannot be changed once created!*",
                            color = descriptionColor(),
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )

                        SFProRoundedText(
                            modifier = Modifier.padding(top = 40.dp, start = 24.dp),
                            content = "Students",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                    }

                    items(state.students) {
                        AddStudentItem(
                            model = it,
                            onDeleteClick = { viewModel.removeStudent(it) }
                        )
                    }

                    item {
                        AddStudentButtonComponent(
                            onClick = { showDialog = true }
                        )
                    }
                }
            }
        }

        AddGroupViewModel.SpecialityState.Empty -> {
            ShowToast(value = "Not found")
            viewModel.clearSpecialityState()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AddGroupScreenPreview() {
    AddGroupScreen(
        onBackClick = {},
        facultyId = 1
    )
}
























