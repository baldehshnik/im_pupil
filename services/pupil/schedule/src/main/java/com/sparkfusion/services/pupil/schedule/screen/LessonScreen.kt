package com.sparkfusion.services.pupil.schedule.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.schedule.R
import com.sparkfusion.services.pupil.schedule.component.DayOfWeekListComponent
import com.sparkfusion.services.pupil.schedule.component.LessonInfoItem
import com.sparkfusion.services.pupil.schedule.viewmodel.LessonsViewModel

@Composable
fun LessonScreenEnter(
    modifier: Modifier = Modifier,
    scheduleId: Int,
    onBackClick: () -> Unit
) {
    LessonsScreen(
        modifier = modifier,
        scheduleId = scheduleId,
        onBackClick = onBackClick
    )
}

@Composable
internal fun LessonsScreen(
    modifier: Modifier = Modifier,
    viewModel: LessonsViewModel = hiltViewModel(),
    scheduleId: Int,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readLessons(scheduleId)
    }

    val daysOfWeek = stringArrayResource(id = R.array.days_of_week)

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (readingState) {
        LessonsViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        LessonsViewModel.ReadingState.Initial -> {}
        LessonsViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is LessonsViewModel.ReadingState.Success -> {
            val upperWeek = (readingState as LessonsViewModel.ReadingState.Success).upperWeek
            val bottomWeek = (readingState as LessonsViewModel.ReadingState.Success).bottomWeek

            LazyColumn(
                modifier = modifier
            ) {
                item {
                    TopBar(
                        title = "Schedule",
                        onBackClick = onBackClick
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



























