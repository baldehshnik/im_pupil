package com.sparkfusion.services.admin.statistics.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.statistics.viewmodel.GroupViewModel
import com.sparkfusion.services.admin.statistics.viewmodel.StudentStatisticsViewModel

@Composable
fun StatisticGroupScreen(
    modifier: Modifier = Modifier,
    viewModel: GroupViewModel,
    studentStatisticsViewModel: StudentStatisticsViewModel,
    groupId: Int,
    onBackClick: () -> Unit,
    onStudentClick: () -> Unit,
    onGroupStatisticsClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.read(groupId)
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    when (readingState) {
        GroupViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        GroupViewModel.ReadingState.Initial -> {}
        GroupViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is GroupViewModel.ReadingState.Success -> {
            val data = (readingState as GroupViewModel.ReadingState.Success).data
            Box(
                modifier = modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        TopBar(title = "Group", onBackClick = onBackClick)
                    }

                    items(data) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(20.dp))
                                .clickable {
                                    studentStatisticsViewModel.setGroupMember(it)
                                    onStudentClick()
                                }
                                .padding(horizontal = 24.dp, vertical = 6.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape),
                                model = it.pupil?.icon ?: "",
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(start = 12.dp, top = 6.dp)
                            ) {
                                SFProRoundedText(
                                    content = "${it.lastname} ${it.firstname}",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )

                                SFProRoundedText(
                                    modifier = Modifier.padding(top = 2.dp),
                                    content = "Code: ${it.code}",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    color = descriptionColor()
                                )
                            }
                        }
                    }
                }

                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomEnd),
                    text = {
                        SFProRoundedText(content = "Get statistics of group")
                    },
                    icon = {

                    },
                    onClick = { onGroupStatisticsClick() }
                )
            }
        }
    }
}























