package com.sparkfusion.services.admin.schedule.screen

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.schedule.R
import com.sparkfusion.services.admin.schedule.component.DayOfWeekListComponent
import com.sparkfusion.services.admin.schedule.component.SpinnerWithTitleComponent
import com.sparkfusion.services.admin.schedule.list_item.EditLessonInfoItem
import com.sparkfusion.services.admin.schedule.viewmodel.EditingViewModel
import java.util.Calendar

@Composable
fun ScheduleEditingScreen(
    modifier: Modifier = Modifier,
    viewModel: EditingViewModel,
    scheduleId: Int,
    groupId: Int,
    onBackClick: () -> Unit,
    onEditLessonClick: (Int, Int) -> Unit
) {
    val daysOfWeek = stringArrayResource(id = R.array.days_of_week)
    val weekData = listOf("Upper week", "Bottom week")
    var expanded by remember { mutableStateOf(false) }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val checkState by viewModel.checkState.collectAsStateWithLifecycle()
    val editingState by viewModel.editingState.collectAsStateWithLifecycle()
    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (readingState != EditingViewModel.ReadingState.Success) {
            viewModel.read(scheduleId)
        }
    }

    var showStartDatePicker by remember { mutableStateOf(false) }
    var showEndDatePicker by remember { mutableStateOf(false) }

    when (checkState) {
        EditingViewModel.CheckState.EmptyName -> {
            ShowToast(value = "Name is empty")
            viewModel.clearCheckState()
        }

        EditingViewModel.CheckState.Initial -> {}
    }

    when (editingState) {
        EditingViewModel.EditingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearEditingState()
        }

        EditingViewModel.EditingState.Initial -> {}
        EditingViewModel.EditingState.Progress -> {
            ShowToast(value = "Adding...")
        }

        EditingViewModel.EditingState.Success -> {
            onBackClick()
        }
    }

    if (showStartDatePicker) {
        val datePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _, year, month, dayOfMonth ->
                state.begin.set(year, month, dayOfMonth)
                showStartDatePicker = false
            },
            state.begin.get(Calendar.YEAR),
            state.begin.get(Calendar.MONTH),
            state.begin.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    if (showEndDatePicker) {
        val datePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _, year, month, dayOfMonth ->
                state.end.set(year, month, dayOfMonth)
                showEndDatePicker = false
            },
            state.end.get(Calendar.YEAR),
            state.end.get(Calendar.MONTH),
            state.end.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    when (readingState) {
        EditingViewModel.ReadingState.Initial -> {}
        EditingViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        EditingViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        EditingViewModel.ReadingState.Success -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = modifier.fillMaxHeight()
                ) {
                    item {
                        TopBar(
                            title = "Editing",
                            onBackClick = onBackClick
                        )

                        SFProRoundedText(
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
                            content = "Subject name",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 24.dp, end = 24.dp, top = 2.dp),
                            shape = RoundedCornerShape(16.dp),
                            value = state.name,
                            onValueChange = { viewModel.updateName(it) },
                            placeholder = {
                                SFProRoundedText(content = "Enter here...")
                            }
                        )

                        Row(
                            modifier = Modifier
                                .padding(start = 24.dp, end = 24.dp, top = 20.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                SFProRoundedText(
                                    content = "Begin",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20.dp))
                                        .width(160.dp)
                                        .padding(top = 4.dp)
                                        .background(MaterialTheme.colorScheme.primaryContainer)
                                        .clickable { showStartDatePicker = true },
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    SFProRoundedText(
                                        modifier = Modifier.padding(
                                            start = 20.dp,
                                            top = 6.dp,
                                            bottom = 6.dp
                                        ),
                                        content = formatDate(state.begin),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )

                                    Icon(
                                        modifier = Modifier.padding(
                                            end = 20.dp,
                                            top = 16.dp,
                                            bottom = 16.dp,
                                            start = 20.dp
                                        ),
                                        painter = painterResource(id = R.drawable.calendar_icon),
                                        contentDescription = null
                                    )
                                }
                            }

                            Column {
                                SFProRoundedText(
                                    content = "End",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20.dp))
                                        .width(160.dp)
                                        .padding(top = 2.dp)
                                        .background(MaterialTheme.colorScheme.primaryContainer)
                                        .clickable { showEndDatePicker = true },
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    SFProRoundedText(
                                        modifier = Modifier.padding(
                                            start = 20.dp,
                                            top = 6.dp,
                                            bottom = 6.dp
                                        ),
                                        content = formatDate(state.end),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )

                                    Icon(
                                        modifier = Modifier.padding(
                                            end = 20.dp,
                                            top = 16.dp,
                                            bottom = 16.dp,
                                            start = 20.dp
                                        ),
                                        painter = painterResource(id = R.drawable.clock_icon),
                                        contentDescription = null
                                    )
                                }
                            }
                        }

                        SpinnerWithTitleComponent(
                            title = "The schedule will start from",
                            items = weekData,
                            onDismiss = { expanded = false },
                            expanded = expanded,
                            currentItem = weekData[state.weekType - 1],
                            onItemClick = {
                                viewModel.updateWeekType(weekData.indexOf(it) + 1)
                            },
                            onExpanded = { expanded = true }
                        )

                        SFProRoundedText(
                            modifier = Modifier.padding(start = 24.dp, top = 2.dp),
                            content = "This setting cannot be changed once the schedule goes into effect*",
                            color = descriptionColor(),
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
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

                    val upperWeekData = state.upperWeek[state.currentUpperWeekType] ?: emptyList()
                    items(upperWeekData) { model ->
                        EditLessonInfoItem(
                            color = Color.Green,
                            model = model,
                            onDeleteClick = { viewModel.deleteUpperWeekItem(model) },
                            onItemClick = {
                                viewModel.currentUpdatingLesson = model
                                onEditLessonClick(1, state.currentUpperWeekType)
                            }
                        )
                    }

                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            TextButton(
                                onClick = {
                                    viewModel.currentUpdatingLesson = null
                                    onEditLessonClick(1, state.currentUpperWeekType)
                                }
                            ) {
                                SFProRoundedText(
                                    modifier = Modifier,
                                    content = "Add one more",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        SFProRoundedText(
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 40.dp),
                            content = "The schedule for the upper and lower weeks will alternate. If you don't want to split your schedule into two weeks, you can leave the bottom week blank and it won't be counted.",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )

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

                    val bottomWeekData =
                        state.bottomWeek[state.currentBottomWeekType] ?: emptyList()
                    items(bottomWeekData) { model ->
                        EditLessonInfoItem(
                            color = Color.Red,
                            model = model,
                            onDeleteClick = { viewModel.deleteBottomWeekItem(model) },
                            onItemClick = {
                                viewModel.currentUpdatingLesson = model
                                onEditLessonClick(2, state.currentBottomWeekType)
                            }
                        )
                    }

                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            TextButton(
                                onClick = {
                                    viewModel.currentUpdatingLesson = null
                                    onEditLessonClick(2, state.currentBottomWeekType)
                                }
                            ) {
                                SFProRoundedText(
                                    modifier = Modifier,
                                    content = "Add one more",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }

                FloatingActionButton(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.BottomEnd),
                    onClick = { viewModel.save(groupId, scheduleId) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.check_icon),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

























