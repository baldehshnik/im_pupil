package com.sparkfusion.services.admin.session.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.session.R
import com.sparkfusion.services.admin.session.viewmodel.SessionEditingViewModel
import java.util.Calendar

@Composable
fun SessionTestEditingScreen(
    modifier: Modifier = Modifier,
    viewModel: SessionEditingViewModel = hiltViewModel(),
    id: Int,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.read(id)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val readingState by viewModel.readingState.collectAsStateWithLifecycle()
    val checkState by viewModel.checkState.collectAsStateWithLifecycle()
    val updatingState by viewModel.updatingState.collectAsStateWithLifecycle()

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    when (checkState) {
        SessionEditingViewModel.CheckState.AudienceIsEmpty -> {
            ShowToast(value = "Audience is empty")
            viewModel.clearCheckState()
        }

        SessionEditingViewModel.CheckState.Initial -> {}
        SessionEditingViewModel.CheckState.NameIsEmpty -> {
            ShowToast(value = "Name is empty")
            viewModel.clearCheckState()
        }
    }

    when (updatingState) {
        SessionEditingViewModel.UpdatingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearUpdatingState()
        }

        SessionEditingViewModel.UpdatingState.Initial -> {}
        SessionEditingViewModel.UpdatingState.Progress -> {
            ShowToast(value = "Updating...")
        }

        SessionEditingViewModel.UpdatingState.Success -> {
            onBackClick()
        }
    }

    if (showDatePicker) {
        val datePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _, year, month, dayOfMonth ->
                state.dateAndTimeCalendar.set(year, month, dayOfMonth)
                showDatePicker = false
            },
            state.dateAndTimeCalendar.get(Calendar.YEAR),
            state.dateAndTimeCalendar.get(Calendar.MONTH),
            state.dateAndTimeCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    if (showTimePicker) {
        val timePickerDialog = TimePickerDialog(
            LocalContext.current,
            { _, hourOfDay, minute ->
                state.dateAndTimeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                state.dateAndTimeCalendar.set(Calendar.MINUTE, minute)
                showTimePicker = false
            },
            state.dateAndTimeCalendar.get(Calendar.HOUR_OF_DAY),
            state.dateAndTimeCalendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }

    when (readingState) {
        SessionEditingViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        SessionEditingViewModel.ReadingState.Initial -> {}
        SessionEditingViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is SessionEditingViewModel.ReadingState.Success -> {
            Box(
                modifier = modifier.fillMaxSize()
            ) {
                FloatingActionButton(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.BottomEnd),
                    onClick = { viewModel.update() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.check_icon),
                        contentDescription = null
                    )
                }

                Column {
                    TopBar(title = "Editing", onBackClick = onBackClick)

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

                    SFProRoundedText(
                        modifier = Modifier.padding(top = 2.dp, start = 24.dp, end = 24.dp),
                        content = "After adding an item, it will be assigned the status: not completed!*",
                        color = descriptionColor()
                    )

                    SFProRoundedText(
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp),
                        content = "Audience",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, top = 2.dp),
                        shape = RoundedCornerShape(16.dp),
                        value = state.audience,
                        onValueChange = { viewModel.updateAudience(it) },
                        placeholder = {
                            SFProRoundedText(content = "Enter here...")
                        }
                    )

                    SFProRoundedText(
                        modifier = Modifier.padding(top = 2.dp, start = 24.dp, end = 24.dp),
                        content = "The audience can be changed later!*",
                        color = descriptionColor()
                    )

                    SFProRoundedText(
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp),
                        content = "Date and time is very important information, but may change. However, to add an entry, you must specify both the date and time. They may be approximate and may be changed later.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Row(
                        modifier = Modifier
                            .padding(start = 24.dp, end = 24.dp, top = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            SFProRoundedText(
                                content = "Date",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .width(160.dp)
                                    .padding(top = 4.dp)
                                    .background(MaterialTheme.colorScheme.primaryContainer)
                                    .clickable { showDatePicker = true },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                SFProRoundedText(
                                    modifier = Modifier.padding(start = 20.dp, top = 6.dp, bottom = 6.dp),
                                    content = formatDate(state.dateAndTimeCalendar),
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
                                content = "Time",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .width(160.dp)
                                    .padding(top = 2.dp)
                                    .background(MaterialTheme.colorScheme.primaryContainer)
                                    .clickable { showTimePicker = true },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                SFProRoundedText(
                                    modifier = Modifier.padding(start = 20.dp, top = 6.dp, bottom = 6.dp),
                                    content = formatTime(state.dateAndTimeCalendar),
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
                }
            }
        }
    }

}






























