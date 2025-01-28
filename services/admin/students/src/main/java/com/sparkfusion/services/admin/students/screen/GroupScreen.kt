package com.sparkfusion.services.admin.students.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import com.sparkfusion.services.admin.students.component.FloatingButtonComponent
import com.sparkfusion.services.admin.students.component.SpinnerWithTitleComponent
import com.sparkfusion.services.admin.students.list_item.GroupItem
import com.sparkfusion.services.admin.students.viewmodel.GroupViewModel

@Composable
fun GroupScreen(
    modifier: Modifier = Modifier,
    viewModel: GroupViewModel = hiltViewModel(),
    facultyId: Int,
    onBackClick: () -> Unit,
    onAddGroupClick: () -> Unit,
    onGroupClick: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readSpecialities(facultyId)
    }

    val specialityState by viewModel.specialityState.collectAsStateWithLifecycle()
    val groupState by viewModel.groupState.collectAsStateWithLifecycle()

    var expanded by remember { mutableStateOf(false) }

    when (specialityState) {
        GroupViewModel.SpecialityState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearSpecialityState()
        }

        GroupViewModel.SpecialityState.Initial -> {}
        GroupViewModel.SpecialityState.Progress -> {
            CircularProgressIndicator()
        }

        is GroupViewModel.SpecialityState.Success -> {
            val specialityData = (specialityState as GroupViewModel.SpecialityState.Success).data

            var currentItem by remember { mutableStateOf(specialityData[0]) }
            LaunchedEffect(currentItem) {
                viewModel.readGroups(currentItem.id)
            }

            Box(
                modifier = modifier.fillMaxSize()
            ) {
                FloatingButtonComponent(
                    painter = painterResource(id = R.drawable.round_add),
                    onClick = onAddGroupClick
                )

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        TopBar(
                            title = "Group",
                            onBackClick = onBackClick
                        )

                        SpinnerWithTitleComponent(
                            title = "Speciality",
                            items = specialityData,
                            onDismiss = { expanded = false },
                            expanded = expanded,
                            currentItem = currentItem,
                            onItemClick = {
                                currentItem = it
                            },
                            onExpanded = { expanded = true }
                        )

                        SFProRoundedText(
                            modifier = Modifier.padding(start = 24.dp, top = 2.dp),
                            content = "Select a speciality to view existing groups!",
                            color = descriptionColor(),
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )

                        SFProRoundedText(
                            modifier = Modifier.padding(top = 60.dp, start = 24.dp),
                            content = "Existing groups",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                    }

                    when (groupState) {
                        GroupViewModel.GroupState.Error -> {
                            item { ShowToast(value = "Error") }
                        }

                        GroupViewModel.GroupState.Initial -> {}
                        GroupViewModel.GroupState.Progress -> {
                            item { CircularProgressIndicator() }
                        }

                        is GroupViewModel.GroupState.Success -> {
                            val data = (groupState as GroupViewModel.GroupState.Success).data
                            items(data) {
                                GroupItem(
                                    item = it,
                                    specialityName = currentItem.name,
                                    onItemClick = { onGroupClick(it.id) }
                                )
                            }
                        }
                    }
                }
            }
        }

        GroupViewModel.SpecialityState.Empty -> {
            ShowToast(value = "Not found")
            viewModel.clearSpecialityState()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GroupScreenPreview() {
    GroupScreen(
        facultyId = 1,
        onBackClick = {},
        onGroupClick = { _ -> },
        onAddGroupClick = {}
    )
}

































