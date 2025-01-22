package com.sparkfusion.features.admin.post.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.sparkfusion.features.admin.post.R
import com.sparkfusion.features.admin.post.navigator.IPostViewingNavigator
import com.sparkfusion.features.admin.post.viewmodel.PostViewingViewModel

@Composable
fun PostViewingScreen(
    navigator: IPostViewingNavigator,
    modifier: Modifier = Modifier,
    viewModel: PostViewingViewModel = hiltViewModel(),
    id: Int
) {
    LaunchedEffect(Unit) {
        viewModel.readEvent(id)
    }

    val eventState by viewModel.eventState.collectAsStateWithLifecycle()
    val deletingState by viewModel.deletingState.collectAsStateWithLifecycle()

    when (deletingState) {
        PostViewingViewModel.DeletingState.Error -> {
            ShowToast(value = "Error")
        }

        PostViewingViewModel.DeletingState.Initial -> {}
        PostViewingViewModel.DeletingState.Progress -> {
            ShowToast(value = "Deleting...")
        }

        PostViewingViewModel.DeletingState.Success -> {
            navigator.popBackStack()
        }
    }

    when (eventState) {
        PostViewingViewModel.EventState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearEventState()
        }

        PostViewingViewModel.EventState.Initial -> {}
        PostViewingViewModel.EventState.Progress -> {
            CircularProgressIndicator()
        }

        is PostViewingViewModel.EventState.Success -> {
            val state = (eventState as PostViewingViewModel.EventState.Success).data
            Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                TopBar(
                    title = "",
                    buttons = {
                        Row(
                            modifier = Modifier.padding(end = 24.dp)
                        ) {
                            IconButton(
                                modifier = Modifier,
                                onClick = { navigator.navigateToPostEditingScreen(state.id) }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.pencil_icon),
                                    contentDescription = null
                                )
                            }

                            IconButton(
                                modifier = Modifier,
                                onClick = { viewModel.deleteEvent(id) }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.trash_icon),
                                    contentDescription = null,
                                    tint = Color.Red
                                )
                            }
                        }
                    },
                    onBackClick = { navigator.popBackStack() }
                )

                AsyncImage(
                    modifier = Modifier
                        .height(220.dp)
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp, bottom = 12.dp, top = 6.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop,
                    model = state.image,
                    contentDescription = stringResource(id = R.string.post_image_description)
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
                        content = "Event duration is about ${state.duration} minutes.",
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}





























