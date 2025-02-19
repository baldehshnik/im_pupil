package com.sparkfusion.services.pupil.practice.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import coil.compose.AsyncImage
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.practice.R
import com.sparkfusion.services.pupil.practice.viewmodel.PracticeDetailsViewModel

@Composable
fun PracticeDetailsScreenEnter(
    modifier: Modifier = Modifier,
    practiceId: Int,
    onBackClick: () -> Unit
) {
    PracticeDetailsScreen(
        modifier = modifier,
        practiceId = practiceId,
        onBackClick = onBackClick
    )
}

@Composable
private fun PracticeDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: PracticeDetailsViewModel = hiltViewModel(),
    practiceId: Int,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.read(practiceId)
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    when (readingState) {
        PracticeDetailsViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        PracticeDetailsViewModel.ReadingState.Initial -> {}
        PracticeDetailsViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is PracticeDetailsViewModel.ReadingState.Success -> {
            val model = (readingState as PracticeDetailsViewModel.ReadingState.Success).model
            LazyColumn(
                modifier = modifier.fillMaxWidth()
            ) {
                item {
                    TopBar(
                        title = "Details",
                        onBackClick = onBackClick
                    )

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .size(112.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            model = model.icon,
                            contentDescription = null
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, top = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SFProRoundedText(
                            modifier = Modifier,
                            content = model.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )

                        Image(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.link_icon),
                            contentDescription = null
                        )
                    }

                    Box(
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .fillMaxWidth()
                    ) {
                        SFProRoundedText(
                            modifier = Modifier.align(Alignment.Center),
                            content = if (model.payAbility) "Paid" else "Unpaid",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = descriptionColor()
                        )
                    }

                    Row(
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(26.dp),
                            painter = painterResource(id = R.drawable.marker_icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        SFProRoundedText(
                            modifier = Modifier,
                            content = when (model.workType) {
                                1 -> "Distant work"
                                2 -> "Office work"
                                else -> "Distant work\\Office work"
                            },
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    SFProRoundedText(
                        modifier = Modifier.padding(
                            top = 20.dp,
                            start = 24.dp,
                            end = 24.dp,
                            bottom = 20.dp
                        ),
                        content = model.description,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}






















