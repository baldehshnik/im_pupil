package com.sparkfusion.services.pupil.statistics.screen

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.statistics.viewmodel.GroupPassesViewModel

@Composable
fun GroupPassesScreenEnter(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    GroupPassesScreen(
        modifier = modifier,
        onBackClick = onBackClick
    )
}

@Composable
internal fun GroupPassesScreen(
    modifier: Modifier = Modifier,
    viewModel: GroupPassesViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.read()
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    when (readingState) {
        GroupPassesViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        GroupPassesViewModel.ReadingState.Initial -> {}
        GroupPassesViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is GroupPassesViewModel.ReadingState.Success -> {
            val map = (readingState as GroupPassesViewModel.ReadingState.Success).data
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                item {
                    TopBar(title = "Group Statistics", onBackClick = onBackClick)
                }

                map.forEach { (dateKey, items) ->
                    item {
                        SFProRoundedText(
                            content = dateKey.toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    items(items) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(20.dp))
                                .padding(horizontal = 24.dp, vertical = 6.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape),
                                model = "",
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(start = 12.dp, top = 6.dp)
                            ) {
                                SFProRoundedText(
                                    content = "${it.groupMember.lastname} ${it.groupMember.firstname}",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )

                                SFProRoundedText(
                                    modifier = Modifier.padding(top = 2.dp),
                                    content = "Lesson: ${it.lesson.name}",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    color = descriptionColor()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
























