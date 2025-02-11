package com.sparkfusion.features.admin.post.screen

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.image_crop.launcher.rememberLauncherForImageCropping
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.admin.post.R
import com.sparkfusion.features.admin.post.navigator.IPostEditingNavigator
import com.sparkfusion.features.admin.post.screen.editing.PostEditingDescriptionBlock
import com.sparkfusion.features.admin.post.screen.editing.PostEditingDurationBlock
import com.sparkfusion.features.admin.post.screen.editing.PostEditingImageBlock
import com.sparkfusion.features.admin.post.viewmodel.PostEditingViewModel

@Composable
fun PostEditingScreen(
    navigator: IPostEditingNavigator,
    modifier: Modifier = Modifier,
    viewModel: PostEditingViewModel = hiltViewModel(),
    id: Int,
    getCroppedImageBitmap: () -> Bitmap?
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val readingState by viewModel.readingState.collectAsStateWithLifecycle()
    val updateState by viewModel.updateState.collectAsStateWithLifecycle()
    val updatingCheckState by viewModel.updatingCheckState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (readingState != PostEditingViewModel.ReadingState.Success) {
            viewModel.readEvent(id)
        }

        viewModel.updateImage(getCroppedImageBitmap())
    }


    val scroll = rememberScrollState()
    val isScrollActive = remember { derivedStateOf { scroll.isScrollInProgress } }

    val postTypes = stringArrayResource(id = R.array.post_types)

    val durationTypes = stringArrayResource(id = R.array.duration_types)
    val selectedDurationType = rememberSaveable { mutableStateOf(durationTypes[0]) }

    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForImageCropping(
        context = context,
        navigateToImageCrop = { navigator.navigateToCircleImageCropScreen(it) }
    )

    when (updatingCheckState) {
        PostEditingViewModel.UpdatingCheckState.DescriptionTooShort -> {
            ShowToast(value = "Description too short")
            viewModel.clearUpdatingCheckState()
        }

        PostEditingViewModel.UpdatingCheckState.DurationNotSelected -> {
            ShowToast(value = "Duration not selected")
            viewModel.clearUpdatingCheckState()
        }

        PostEditingViewModel.UpdatingCheckState.Initial -> {}
        PostEditingViewModel.UpdatingCheckState.TitleTooLong -> {
            ShowToast(value = "Title too long")
            viewModel.clearUpdatingCheckState()
        }

        PostEditingViewModel.UpdatingCheckState.TitleTooShort -> {
            ShowToast(value = "Title too short")
            viewModel.clearUpdatingCheckState()
        }

        PostEditingViewModel.UpdatingCheckState.TypeNotSelected -> {
            ShowToast(value = "Type not selected")
            viewModel.clearUpdatingCheckState()
        }
    }

    when (updateState) {
        PostEditingViewModel.UpdateState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearUpdatingState()
        }

        PostEditingViewModel.UpdateState.Initial -> {}
        PostEditingViewModel.UpdateState.Progress -> {
            ShowToast(value = "Updating...")
        }

        PostEditingViewModel.UpdateState.Success -> {
            navigator.popBackStack()
        }
    }

    when (readingState) {
        PostEditingViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        PostEditingViewModel.ReadingState.Initial -> {}
        PostEditingViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        PostEditingViewModel.ReadingState.Success -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = modifier
                        .background(Color(0xFFF3F9FF))
                        .verticalScroll(scroll)
                ) {
                    TopBar(
                        modifier = modifier.background(Color.White),
                        title = "Editing",
                        onBackClick = navigator::popBackStack
                    )

                    PostEditingImageBlock(
                        image = state.image,
                        initialImageUrl = state.initialImageUrl,
                        onImageChangeClick = {
                            galleryLauncher.launch("image/*")
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    PostEditingDescriptionBlock(
                        postTypes = postTypes,
                        state = state,
                        onTitleChange = { viewModel.updateTitle(it) },
                        onDescriptionChange = { viewModel.updateDescription(it) },
                        onPostTypeChange = { viewModel.updateType(it) }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    if (state.type != 1) {
                        PostEditingDurationBlock(
                            durationTypes = durationTypes,
                            selectedDurationType = selectedDurationType,
                            hoursDurationType = durationTypes[0],
                            onDurationChange = {
                                viewModel.updateDuration(it)
                            },
                            state = state
                        )
                    }
                }

                AnimatedVisibility(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 16.dp, bottom = 16.dp),
                    visible = !isScrollActive.value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    ExtendedFloatingActionButton(
                        text = {
                            SFProRoundedText(
                                modifier = Modifier.padding(end = 12.dp),
                                content = "Update",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            )
                        },
                        icon = {
                            Icon(
                                modifier = Modifier.padding(2.dp),
                                painter = painterResource(id = R.drawable.pencil_icon),
                                contentDescription = null
                            )
                        },
                        onClick = { viewModel.updateEvent() }
                    )
                }
            }
        }
    }
}



















