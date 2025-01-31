package com.sparkfusion.services.admin.schedule.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.schedule.R
import com.sparkfusion.services.admin.schedule.component.OutlinedDropDownGroupMenu
import com.sparkfusion.services.admin.schedule.viewmodel.GroupViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleGroupScreen(
    modifier: Modifier = Modifier,
    viewModel: GroupViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onScheduleClick: (Int, Int, Int) -> Unit,
    onAddScheduleClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val groupReadingState by viewModel.groupReadingState.collectAsStateWithLifecycle()
    val scheduleReadingState by viewModel.scheduleReadingState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        LazyColumn(
            modifier = modifier.align(Alignment.TopStart)
        ) {
            item {
                TopBar(title = "Schedule", onBackClick = onBackClick)

                SFProRoundedText(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp),
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
                    content = "Select a group to view information*",
                    color = descriptionColor()
                )

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        modifier = Modifier.padding(top = 30.dp),
                        onClick = { viewModel.readSchedules() }
                    ) {
                        SFProRoundedText(
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 2.dp),
                            content = "Search",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    }
                }

                SFProRoundedText(
                    modifier = Modifier.padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 40.dp,
                        bottom = 12.dp
                    ),
                    content = "Schedule",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            when (scheduleReadingState) {
                GroupViewModel.ScheduleReadingState.Error -> {
                    item { ShowToast(value = "Error") }
                    viewModel.clearScheduleReadingState()
                }

                GroupViewModel.ScheduleReadingState.Initial -> {}
                GroupViewModel.ScheduleReadingState.Progress -> {
                    item { CircularProgressIndicator() }
                }

                is GroupViewModel.ScheduleReadingState.Success -> {
                    val data =
                        (scheduleReadingState as GroupViewModel.ScheduleReadingState.Success).schedules
                    items(data) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 24.dp, end = 24.dp, top = 4.dp, bottom = 4.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .clickable { onScheduleClick(it.id, it.type, state.groupId) },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(280.dp)
                                    .padding(top = 16.dp, bottom = 16.dp, start = 16.dp)
                            ) {
                                SFProRoundedText(
                                    modifier = Modifier,
                                    content = it.name + if (it.type == 1) " (current)" else "",
                                    fontSize = 18.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.SemiBold
                                )

                                val startDate = formatInstantToString(it.startDate)
                                val endDate = formatInstantToString(it.finishDate)
                                SFProRoundedText(
                                    modifier = Modifier,
                                    content = "Duration: $startDate - $endDate",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = descriptionColor()
                                )
                            }

                            IconButton(
                                modifier = Modifier.padding(end = 16.dp),
                                onClick = { onScheduleClick(it.id, it.type, state.groupId) }
                            ) {
                                Icon(
                                    painter = painterResource(id = com.sparkfusion.core.design.R.drawable.round_arrow_forward),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier.padding(bottom = 24.dp, end = 24.dp),
            onClick = {
                if (state.groupId != -1) onAddScheduleClick(state.groupId)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus_icon),
                contentDescription = null
            )
        }
    }
}

fun formatInstantToString(instant: Instant): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val zonedDateTime = instant.atZone(ZoneId.systemDefault())
    return zonedDateTime.format(formatter)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScheduleGroupScreenPreview() {
    ScheduleGroupScreen(
        onBackClick = {},
        onScheduleClick = { _, _, _ -> },
        onAddScheduleClick = {}
    )
}
















