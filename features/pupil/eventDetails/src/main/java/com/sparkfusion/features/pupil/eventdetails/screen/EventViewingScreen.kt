package com.sparkfusion.features.pupil.eventdetails.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.pupil.eventdetails.R
import com.sparkfusion.features.pupil.eventdetails.viewmodel.EventDetailsViewModel

@Composable
fun EventDetailsScreenEnter(
    modifier: Modifier = Modifier,
    id: Int,
    onBackStackClick: () -> Unit
) {
    EventDetailsScreen(
        modifier = modifier,
        id = id,
        onBackStackClick = onBackStackClick
    )
}

@Composable
internal fun EventDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: EventDetailsViewModel = hiltViewModel(),
    id: Int,
    onBackStackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readEvent(id)
    }

    val eventState by viewModel.eventState.collectAsStateWithLifecycle()

    when (eventState) {
        EventDetailsViewModel.EventState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearEventState()
        }

        EventDetailsViewModel.EventState.Initial -> {}
        EventDetailsViewModel.EventState.Progress -> {
            CircularProgressIndicator()
        }

        is EventDetailsViewModel.EventState.Success -> {
            val state = (eventState as EventDetailsViewModel.EventState.Success).data
            Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                TopBar(
                    title = "",
                    buttons = {},
                    onBackClick = { onBackStackClick() }
                )

                AsyncImage(
                    modifier = Modifier
                        .height(220.dp)
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp, bottom = 12.dp, top = 6.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop,
                    model = state.image,
                    contentDescription = null
                )

                SFProRoundedText(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
                    content = state.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )

                SFProRoundedText(
                    modifier = Modifier.padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 8.dp,
                        bottom = 6.dp
                    ),
                    content = state.description,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                if (state.duration != 0) {
                    SFProRoundedText(
                        modifier = Modifier.padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 4.dp,
                            bottom = 12.dp
                        ),
                        content = stringResource(id = R.string.event_duration_about, state.duration.toString()),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}




























