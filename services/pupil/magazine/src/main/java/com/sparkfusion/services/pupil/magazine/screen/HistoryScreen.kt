package com.sparkfusion.services.pupil.magazine.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.magazine.component.LessonItem
import com.sparkfusion.services.pupil.magazine.viewmodel.HistoryViewModel

@Composable
fun HistoryScreenEnter(
    modifier: Modifier = Modifier,
    groupMemberId: Int,
    date: Long,
    onBackClick: () -> Unit
) {
    HistoryScreen(
        modifier = modifier,
        groupMemberId = groupMemberId,
        date = date,
        onBackClick = onBackClick
    )
}

@Composable
internal fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
    groupMemberId: Int,
    date: Long,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.read(groupMemberId, date)
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    when (readingState) {
        HistoryViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        HistoryViewModel.ReadingState.Initial -> {}
        HistoryViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is HistoryViewModel.ReadingState.Success -> {
            val data = (readingState as HistoryViewModel.ReadingState.Success).data
            LazyColumn(
                modifier = modifier.fillMaxWidth()
            ) {
                item {
                    TopBar(title = "History", onBackClick = onBackClick)

                    SFProRoundedText(
                        modifier = Modifier.padding(start = 24.dp, top = 12.dp, bottom = 8.dp),
                        content = "Missed",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                val missedItems = data[1] ?: emptyList()
                items(missedItems) {
                    LessonItem(
                        onItemClick = {},
                        number = missedItems.indexOf(it),
                        item = it.getLessonDto
                    )
                }

                item {
                    SFProRoundedText(
                        modifier = Modifier.padding(start = 24.dp, top = 24.dp, bottom = 8.dp),
                        content = "Attended",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = descriptionColor()
                    )
                }

                val attendedItems = data[-1] ?: emptyList()
                items(attendedItems) {
                    LessonItem(
                        onItemClick = {},
                        number = attendedItems.indexOf(it),
                        item = it.getLessonDto
                    )
                }
            }
        }
    }
}

























