package com.sparkfusion.services.pupil.magazine.screen

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.magazine.component.LessonItem
import com.sparkfusion.services.pupil.magazine.viewmodel.TodayScheduleViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder

@Composable
fun TodayScheduleScreenEnter(
    modifier: Modifier = Modifier,
    onItemClick: (id: Int) -> Unit,
    onHistoryClick: () -> Unit,
    onBackClick: () -> Unit
) {
    TodayScheduleScreen(
        modifier = modifier,
        onItemClick = onItemClick,
        onHistoryClick = onHistoryClick,
        onBackClick = onBackClick
    )
}

@Composable
internal fun TodayScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: TodayScheduleViewModel = hiltViewModel(),
    onItemClick: (id: Int) -> Unit,
    onHistoryClick: () -> Unit,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readSchedules()
        viewModel.readAccount()
    }

    val accountReadingState by viewModel.accountReadingState.collectAsStateWithLifecycle()
    val scheduleReadingState by viewModel.scheduleReadingState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(title = "Schedule", onBackClick = onBackClick)

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
            TodayScheduleViewModel.ScheduleReadingState.Error -> {
                item { ShowToast(value = "Error") }
                viewModel.clearScheduleReadingState()
            }

            TodayScheduleViewModel.ScheduleReadingState.Initial -> {}
            TodayScheduleViewModel.ScheduleReadingState.Progress -> {
                item { CircularProgressIndicator() }
            }

            is TodayScheduleViewModel.ScheduleReadingState.Success -> {
                val data =
                    (scheduleReadingState as TodayScheduleViewModel.ScheduleReadingState.Success).schedules
                items(data) {
                    LessonItem(
                        number = data.indexOf(it) + 1,
                        item = it,
                        onItemClick = { onItemClick(it.id) }
                    )
                }

                item {
                    when (accountReadingState) {
                        TodayScheduleViewModel.AccountReadingState.Error -> {
                            ShowToast(value = "Error")
                            viewModel.clearAccountReadingState()
                        }

                        TodayScheduleViewModel.AccountReadingState.Initial -> {}
                        TodayScheduleViewModel.AccountReadingState.Progress -> {}
                        is TodayScheduleViewModel.AccountReadingState.Success -> {
                            val model =
                                (accountReadingState as TodayScheduleViewModel.AccountReadingState.Success).model

                            if (model != null && model.prefect) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 12.dp, bottom = 20.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    TextButton(onClick = {
                                        onHistoryClick()
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



















