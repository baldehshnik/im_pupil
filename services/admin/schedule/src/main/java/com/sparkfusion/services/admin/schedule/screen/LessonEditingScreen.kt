package com.sparkfusion.services.admin.schedule.screen

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.schedule.R
import com.sparkfusion.services.admin.schedule.component.SpinnerWithTitleComponent
import com.sparkfusion.services.admin.schedule.viewmodel.EditingViewModel
import java.util.Calendar

@Composable
fun LessonEditingScreen(
    modifier: Modifier = Modifier,
    viewModel: EditingViewModel,
    weekType: Int,
    dayOfWeek: Int,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readLesson()
    }

    val typeData = listOf("Lection", "Practice", "Laboratory")
    var expanded by remember { mutableStateOf(false) }

    val lessonAddingState by viewModel.lessonEditingState.collectAsStateWithLifecycle()
    val lessonCheckState by viewModel.lessonCheckState.collectAsStateWithLifecycle()
    val lessonReadingState by viewModel.lessonReadingState.collectAsStateWithLifecycle()

    var showStartTimePicker by remember { mutableStateOf(false) }
    var showEndTimePicker by remember { mutableStateOf(false) }

    when (lessonCheckState) {
        EditingViewModel.LessonCheckState.AudienceNotSelected -> {
            ShowToast(value = "Audience not selected")
            viewModel.clearLessonCheckState()
        }

        EditingViewModel.LessonCheckState.Initial -> {}
        EditingViewModel.LessonCheckState.NameIsEmpty -> {
            ShowToast(value = "Name is empty")
            viewModel.clearLessonCheckState()
        }

        EditingViewModel.LessonCheckState.Success -> {
            onBackClick()
            viewModel.clearLessonCheckState()
        }

        EditingViewModel.LessonCheckState.TeacherIsEmpty -> {
            ShowToast(value = "Teacher is empty")
            viewModel.clearLessonCheckState()
        }
    }

    if (showStartTimePicker) {
        val timePickerDialog = TimePickerDialog(
            LocalContext.current,
            { _, hourOfDay, minute ->
                lessonAddingState.begin.set(Calendar.HOUR_OF_DAY, hourOfDay)
                lessonAddingState.begin.set(Calendar.MINUTE, minute)
                showStartTimePicker = false
            },
            lessonAddingState.begin.get(Calendar.HOUR_OF_DAY),
            lessonAddingState.begin.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.setOnCancelListener { showStartTimePicker = false }
        timePickerDialog.show()
    }

    if (showEndTimePicker) {
        val timePickerDialog = TimePickerDialog(
            LocalContext.current,
            { _, hourOfDay, minute ->
                lessonAddingState.end.set(Calendar.HOUR_OF_DAY, hourOfDay)
                lessonAddingState.end.set(Calendar.MINUTE, minute)
                showEndTimePicker = false
            },
            lessonAddingState.end.get(Calendar.HOUR_OF_DAY),
            lessonAddingState.end.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.setOnCancelListener { showEndTimePicker = false }
        timePickerDialog.show()
    }

    when (lessonReadingState) {
        EditingViewModel.LessonReadingState.Initial -> {
            SFProRoundedText(content = "INIT")
        }
        EditingViewModel.LessonReadingState.Success -> {
            LazyColumn(
                modifier = modifier.fillMaxHeight()
            ) {
                item {
                    TopBar(
                        title = "Lesson Editing",
                        onBackClick = {
                            viewModel.clearLessonEditingState()
                            onBackClick()
                        }
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
                        value = lessonAddingState.name,
                        onValueChange = { viewModel.updateLessonName(it) },
                        placeholder = {
                            SFProRoundedText(content = "Enter here...")
                        }
                    )

                    SFProRoundedText(
                        modifier = Modifier.padding(start = 24.dp, top = 2.dp),
                        content = "The name will not be abbreviated, so abbreviations can be used*",
                        color = descriptionColor(),
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
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
                                    .clickable { showStartTimePicker = true },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                SFProRoundedText(
                                    modifier = Modifier.padding(
                                        start = 20.dp,
                                        top = 6.dp,
                                        bottom = 6.dp
                                    ),
                                    content = formatCalendarToTime(lessonAddingState.begin),
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
                                    .clickable { showEndTimePicker = true },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                SFProRoundedText(
                                    modifier = Modifier.padding(
                                        start = 20.dp,
                                        top = 6.dp,
                                        bottom = 6.dp
                                    ),
                                    content = formatCalendarToTime(lessonAddingState.end),
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

                    SFProRoundedText(
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
                        content = "Teacher",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, top = 2.dp),
                        shape = RoundedCornerShape(16.dp),
                        value = lessonAddingState.teacher,
                        onValueChange = { viewModel.updateLessonTeacher(it) },
                        placeholder = {
                            SFProRoundedText(content = "Enter here...")
                        }
                    )

                    SFProRoundedText(
                        modifier = Modifier.padding(start = 24.dp, top = 2.dp),
                        content = "After adding a lesson, the entered teacher will be recorded in the local database. Therefore, the next time you enter, a search will be performed to simplify repetition.",
                        color = descriptionColor(),
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )

                    SpinnerWithTitleComponent(
                        title = "Type",
                        items = typeData,
                        onDismiss = { expanded = false },
                        expanded = expanded,
                        currentItem = typeData[lessonAddingState.type - 1],
                        onItemClick = {
                            viewModel.updateLessonType(typeData.indexOf(it) + 1)
                        },
                        onExpanded = { expanded = true }
                    )

                    Column(
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                    ) {
                        SFProRoundedText(
                            modifier = Modifier,
                            content = "Audience",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            value = lessonAddingState.audience,
                            onValueChange = { viewModel.updateLessonAudience(it) },
                            placeholder = {
                                SFProRoundedText(content = "Enter here...")
                            }
                        )
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            modifier = Modifier.padding(vertical = 30.dp),
                            onClick = {
                                viewModel.saveLesson(weekType, dayOfWeek)
                            }
                        ) {
                            SFProRoundedText(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp),
                                content = "Save",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}




























