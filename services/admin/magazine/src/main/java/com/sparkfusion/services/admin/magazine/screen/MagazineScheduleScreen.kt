package com.sparkfusion.services.admin.magazine.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.magazine.component.OutlinedDropDownGroupMenu
import com.sparkfusion.services.admin.magazine.list_item.LessonItem
import com.sparkfusion.services.admin.magazine.viewmodel.ScheduleViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder

@Composable
fun MagazineScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel = hiltViewModel(),
    facultyId: Int,
    onBackClick: () -> Unit,
    onItemClick: (Int, Int) -> Unit,
    onHistoryClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val groupReadingState by viewModel.groupReadingState.collectAsStateWithLifecycle()
    val scheduleReadingState by viewModel.scheduleReadingState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(title = "Schedule", onBackClick = onBackClick)

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 20.dp),
                content = "Group",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            OutlinedDropDownGroupMenu(
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
                content = "Select a group to view the schedule*",
                color = descriptionColor()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 40.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SFProRoundedText(
                    modifier = Modifier,
                    content = "Today's schedule",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                SFProRoundedText(
                    modifier = Modifier,
                    content = currentDateConverter(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        when (scheduleReadingState) {
            ScheduleViewModel.ScheduleReadingState.Error -> {
                item { ShowToast(value = "Error") }
                viewModel.clearScheduleReadingState()
            }

            ScheduleViewModel.ScheduleReadingState.Initial -> {}
            ScheduleViewModel.ScheduleReadingState.Progress -> {
                item { CircularProgressIndicator() }
            }

            is ScheduleViewModel.ScheduleReadingState.Success -> {
                val data =
                    (scheduleReadingState as ScheduleViewModel.ScheduleReadingState.Success).schedules
                items(data) {
                    LessonItem(
                        number = data.indexOf(it) + 1,
                        item = it,
                        onItemClick = { onItemClick(it.id, state.groupId) }
                    )
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(onClick = {
                            onHistoryClick(state.groupId)
                        }) {
                            SFProRoundedText(
                                modifier = Modifier,
                                content = "Student history",
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun currentDateConverter(): String {
    val formatter = DateTimeFormatterBuilder()
        .appendPattern("d")
        .appendLiteral(" ")
        .appendPattern("MMMM")
        .toFormatter()

    return LocalDate.now().format(formatter)
}
























