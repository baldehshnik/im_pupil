package com.sparkfusion.services.admin.schedule.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.schedule.R
import com.sparkfusion.services.admin.schedule.component.DayOfWeekListComponent
import com.sparkfusion.services.admin.schedule.list_item.LessonInfoItem

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {
    val daysOfWeek = stringArrayResource(id = R.array.days_of_week)

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            onClick = onEditClick
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
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.bookmark_icon),
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
                DayOfWeekListComponent(daysOfWeek = daysOfWeek)
            }

            items(2) {
                LessonInfoItem(
                    color = Color.Green
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
                DayOfWeekListComponent(daysOfWeek = daysOfWeek)
            }

            items(1) {
                LessonInfoItem(
                    color = Color.Red
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScheduleScreenPreview() {
    ScheduleScreen(
        onBackClick = {},
        onEditClick = {}
    )
}