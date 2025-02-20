package com.sparkfusion.services.pupil.magazine.screen

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.magazine.R
import com.sparkfusion.services.pupil.magazine.component.AccountComponent
import com.sparkfusion.services.pupil.magazine.component.SpinnerWithTitleComponent
import com.sparkfusion.services.pupil.magazine.viewmodel.StudentDetailsViewModel

@Composable
fun StudentDetailsScreenEnter(
    modifier: Modifier = Modifier,
    groupMemberId: Int,
    lessonId: Int,
    onHistoryClick: () -> Unit,
    onBackClick: () -> Unit
) {
    StudentDetailsScreen(
        modifier = modifier,
        groupMemberId = groupMemberId,
        lessonId = lessonId,
        onHistoryClick = onHistoryClick,
        onBackClick = onBackClick
    )
}

@Composable
internal fun StudentDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: StudentDetailsViewModel = hiltViewModel(),
    groupMemberId: Int,
    lessonId: Int,
    onHistoryClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val statuses = listOf("Attended", "Missed")
    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    LaunchedEffect(Unit) {
        viewModel.readAccount()
        viewModel.readGroupMember(groupMemberId, lessonId)
        viewModel.readWeeklyStatistics(groupMemberId)
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()
    val readWeekStatisticsState by viewModel.readingWeekStatisticsState.collectAsStateWithLifecycle()
    val accountReadingState by viewModel.accountReadingState.collectAsStateWithLifecycle()

    var expanded by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(
                title = "Student details",
                onBackClick = onBackClick,
                buttons = {
                    if (
                        accountReadingState is StudentDetailsViewModel.AccountReadingState.Success &&
                        (accountReadingState as StudentDetailsViewModel.AccountReadingState.Success).model?.prefect == true
                    ) {
                        IconButton(onClick = onHistoryClick) {
                            Icon(
                                painter = painterResource(id = R.drawable.document_icon),
                                contentDescription = null
                            )
                        }
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
                        status = if (data.getGroupMemberDto.prefect) "prefect" else "student"
                    )

                    if (
                        accountReadingState is StudentDetailsViewModel.AccountReadingState.Success &&
                        (accountReadingState as StudentDetailsViewModel.AccountReadingState.Success).model?.prefect == true
                    ) {
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
                    } else {
                        SFProRoundedText(
                            content = "Status",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(start = 24.dp, top = 16.dp)
                        )

                        Row(
                            modifier = Modifier
                                .padding(horizontal = 24.dp, vertical = 2.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.LightGray),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SFProRoundedText(
                                modifier = Modifier.padding(
                                    start = 12.dp,
                                    top = 8.dp,
                                    bottom = 8.dp
                                ),
                                content = statuses[if (data.getPassDto.status == 1) 1 else 0],
                                fontWeight = FontWeight.Medium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
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
                val data =
                    (readWeekStatisticsState as StudentDetailsViewModel.ReadWeekStatisticsState.Success).data
                items(days) {
                    val passesCount =
                        data[days.indexOf(it) + 1]?.find { m -> m.dayOfWeek == days.indexOf(it) + 1 }
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
                    }
                }
            }
        }
    }
}




















