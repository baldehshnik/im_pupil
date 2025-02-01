package com.sparkfusion.services.admin.magazine.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.magazine.R
import com.sparkfusion.services.admin.magazine.component.AccountComponent
import com.sparkfusion.services.admin.magazine.component.SpinnerWithTitleComponent
import com.sparkfusion.services.admin.magazine.viewmodel.StudentDetailsViewModel

@Composable
fun MagazineStudentDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: StudentDetailsViewModel = hiltViewModel(),
    groupMemberId: Int,
    lessonId: Int,
    onBackClick: () -> Unit,
    onHistoryClick: () -> Unit
) {
    val statuses = listOf("Attended", "Missed")
    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    LaunchedEffect(Unit) {
        viewModel.readGroupMember(groupMemberId, lessonId)
        viewModel.readWeeklyStatistics(groupMemberId)
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()
    val readWeekStatisticsState by viewModel.readingWeekStatisticsState.collectAsStateWithLifecycle()

    var expanded by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(
                title = "Student details",
                onBackClick = onBackClick,
                buttons = {
                    IconButton(onClick = onHistoryClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.document_icon),
                            contentDescription = null
                        )
                    }
                }
            )

            when (readingState) {
                StudentDetailsViewModel.ReadingState.Error -> {
                    ShowToast(value = "Error")
                    viewModel.clearReadingState()
                }

                StudentDetailsViewModel.ReadingState.Initial -> {}
                StudentDetailsViewModel.ReadingState.Progress -> {
                    CircularProgressIndicator()
                }

                is StudentDetailsViewModel.ReadingState.Success -> {
                    val data = (readingState as StudentDetailsViewModel.ReadingState.Success).model
                    AccountComponent(
                        name = "${data.getGroupMemberDto.lastname} ${data.getGroupMemberDto.firstname} ${data.getGroupMemberDto.patronymic}",
                        status = if (data.getGroupMemberDto.isPrefect) "prefect" else "student"
                    )

//                    AccountInfoComponent(
//                        modifier = Modifier.padding(top = 20.dp, start = 24.dp),
//                        title = "Faculty",
//                        value = "Faculty of Electronic Information Systems"
//                    )
//
//                    AccountInfoComponent(
//                        modifier = Modifier.padding(top = 12.dp, start = 24.dp),
//                        title = "Speciality",
//                        value = "Programmable mobile systems"
//                    )

                    SpinnerWithTitleComponent(
                        title = "Status",
                        items = statuses,
                        onDismiss = { expanded = false },
                        expanded = expanded,
                        currentItem = statuses[if (data.getPassDto.status == 1) 1 else 0],
                        onItemClick = {
                            viewModel.updateStatus(
                                newStatus = if (statuses.indexOf(it) == 1) 1 else -1,
                                lessonId = lessonId
                            )
                        },
                        onExpanded = { expanded = true }
                    )
                }
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 40.dp, bottom = 8.dp),
                content = "Weekly statistics:",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        when (readWeekStatisticsState) {
            StudentDetailsViewModel.ReadWeekStatisticsState.Error -> {
                item { ShowToast(value = "Error") }
                viewModel.clearReadingWeekStatisticsState()
            }

            StudentDetailsViewModel.ReadWeekStatisticsState.Initial -> {}
            StudentDetailsViewModel.ReadWeekStatisticsState.Progress -> {
                item { CircularProgressIndicator() }
            }

            is StudentDetailsViewModel.ReadWeekStatisticsState.Success -> {
                val data = (readWeekStatisticsState as StudentDetailsViewModel.ReadWeekStatisticsState.Success).data
                items(days) {
                    val passesCount = data[days.indexOf(it) + 1]?.find { m -> m.dayOfWeek ==  days.indexOf(it) + 1}
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 16.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                            .fillMaxWidth()
                    ) {
                        SFProRoundedText(
                            modifier = Modifier
                                .width(280.dp)
                                .padding(start = 20.dp, top = 12.dp, bottom = 12.dp),
                            content = it + if (passesCount == null) "" else " ${passesCount.passesCount}",
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp
                        )

//                        IconButton(
//                            modifier = Modifier.padding(end = 16.dp),
//                            onClick = { }
//                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.angle_right_icon),
//                                contentDescription = null
//                            )
//                        }
                    }
                }
            }
        }
    }
}






















