package com.sparkfusion.services.pupil.schedule.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.schedule.viewmodel.ScheduleViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleScreenEnter(
    modifier: Modifier = Modifier,
    onScheduleClick: (id: Int) -> Unit,
    onBackClick: () -> Unit
) {
    ScheduleScreen(
        modifier = modifier,
        onScheduleClick = onScheduleClick,
        onBackClick = onBackClick
    )
}

@Composable
internal fun ScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel = hiltViewModel(),
    onScheduleClick: (id: Int) -> Unit,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readSchedules()
    }

    val scheduleReadingState by viewModel.scheduleReadingState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            TopBar(title = "Schedule", onBackClick = onBackClick)

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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, top = 4.dp, bottom = 4.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .clickable { onScheduleClick(it.id) },
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
                            onClick = { onScheduleClick(it.id) }
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
}

fun formatInstantToString(instant: Instant): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val zonedDateTime = instant.atZone(ZoneId.systemDefault())
    return zonedDateTime.format(formatter)
}





















