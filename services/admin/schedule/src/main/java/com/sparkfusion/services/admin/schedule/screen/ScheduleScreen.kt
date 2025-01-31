package com.sparkfusion.services.admin.schedule.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.schedule.R
import com.sparkfusion.services.admin.schedule.component.DayOfWeekListComponent
import com.sparkfusion.services.admin.schedule.list_item.LessonInfoItem
import com.sparkfusion.services.admin.schedule.viewmodel.ScheduleViewModel

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel = hiltViewModel(),
    scheduleId: Int,
    scheduleType: Int,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {
    val daysOfWeek = stringArrayResource(id = R.array.days_of_week)

    val state by viewModel.state.collectAsStateWithLifecycle()
    val readingState by viewModel.readingState.collectAsStateWithLifecycle()
    val updatingStatusState by viewModel.updatingStatusState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.readLessons(scheduleId)
        if (state.currentStatus == -1) viewModel.updateStatus(scheduleType)
    }

    when (updatingStatusState) {
        ScheduleViewModel.UpdatingStatusState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearUpdatingState()
        }

        ScheduleViewModel.UpdatingStatusState.Initial -> {}
        ScheduleViewModel.UpdatingStatusState.Progress -> {
            ShowToast(value = "Updating...")
        }
    }

    when (readingState) {
        ScheduleViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        ScheduleViewModel.ReadingState.Initial -> {}
        ScheduleViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is ScheduleViewModel.ReadingState.Success -> {
            val upperWeek = (readingState as ScheduleViewModel.ReadingState.Success).upperWeek
            val bottomWeek = (readingState as ScheduleViewModel.ReadingState.Success).bottomWeek

            Box(
                modifier = modifier.fillMaxSize()
            ) {
                FloatingActionButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomEnd),
                    onClick = { onEditClick() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.pencil_icon),
                        contentDescription = null
                    )
                }

                LazyColumn(
                    modifier = Modifier
                ) {
                    item {
                        TopBar(
                            title = "Schedule",
                            onBackClick = onBackClick,
                            buttons = {
                                IconButton(onClick = { viewModel.updateScheduleStatus(scheduleId) }) {
                                    Icon(
                                        painter = painterResource(
                                            id = if (state.currentStatus == 1) R.drawable.plus_icon
                                            else R.drawable.bookmark_icon
                                        ),
                                        contentDescription = null
                                    )
                                }
                            }
                        )

                        SFProRoundedText(
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                            content = "Upper week",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    item {
                        DayOfWeekListComponent(
                            currentWeekType = state.currentUpperWeekType,
                            daysOfWeek = daysOfWeek,
                            onItemClick = { viewModel.updateCurrentUpperWeekType(it) }
                        )
                    }

                    val upperWeekData = upperWeek[state.currentUpperWeekType] ?: emptyList()
                    items(upperWeekData) { model ->
                        LessonInfoItem(
                            color = Color.Green,
                            model = model
                        )
                    }


                    item {
                        SFProRoundedText(
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 40.dp),
                            content = "Bottom week",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    item {
                        DayOfWeekListComponent(
                            currentWeekType = state.currentBottomWeekType,
                            daysOfWeek = daysOfWeek,
                            onItemClick = { viewModel.updateCurrentBottomWeekType(it) }
                        )
                    }

                    val bottomWeekData = bottomWeek[state.currentBottomWeekType] ?: emptyList()
                        items(bottomWeekData) { model ->
                            LessonInfoItem(
                                color = Color.Red,
                                model = model
                            )
                        }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScheduleScreenPreview() {
    ScheduleScreen(
        onBackClick = {},
        onEditClick = {},
        scheduleId = 1,
        scheduleType = 1
    )
}