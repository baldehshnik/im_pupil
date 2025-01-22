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
import com.sparkfusion.core.image_crop.common.IMAGE_CROP_KEY
import com.sparkfusion.core.image_crop.launcher.rememberLauncherForImageCropping
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.features.admin.post.R
import com.sparkfusion.features.admin.post.navigator.IPostAddingNavigator
import com.sparkfusion.features.admin.post.screen.component.DurationBlock
import com.sparkfusion.features.admin.post.screen.component.PostDescriptionBlock
import com.sparkfusion.features.admin.post.screen.component.PostImageBlock
import com.sparkfusion.features.admin.post.screen.component.TopBarComponent
import com.sparkfusion.features.admin.post.viewmodel.PostAddingViewModel

@Composable
fun PostAddingScreen(
    navigator: IPostAddingNavigator,
    modifier: Modifier = Modifier,
    viewModel: PostAddingViewModel = hiltViewModel(),
    getCroppedImageBitmap: () -> Bitmap?
) {
    LaunchedEffect(Unit) {
        viewModel.updateImage(getCroppedImageBitmap())
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val addingState by viewModel.addingState.collectAsStateWithLifecycle()
    val addingCheckState by viewModel.addingCheckState.collectAsStateWithLifecycle()

    val scroll = rememberScrollState()
    val isScrollActive = remember { derivedStateOf { scroll.isScrollInProgress } }

//    val duration = rememberSaveable { mutableStateOf("") }
//    val postOnBehalfOfInstitution = rememberSaveable { mutableStateOf(true) }

    val postTypes = stringArrayResource(id = R.array.post_types)

    val durationTypes = stringArrayResource(id = R.array.duration_types)
    val selectedDurationType = rememberSaveable { mutableStateOf(durationTypes[0]) }

//    val additionalBlockEntities = getAdditionalBlocks()
//    val activeAdditionalBlock = remember { mutableStateOf(additionalBlockEntities[0]) }

    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForImageCropping(
        context = context,
        navigateToImageCrop = { navigator.navigateToCircleImageCropScreen(IMAGE_CROP_KEY, it) }
    )

    when (addingState) {
        PostAddingViewModel.AddingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearAddingState()
        }

        PostAddingViewModel.AddingState.Initial -> {}
        PostAddingViewModel.AddingState.Progress -> {
            ShowToast(value = "Saving...")
        }

        PostAddingViewModel.AddingState.Success -> {
            ShowToast(value = "Success")
            navigator.popBackStack()
        }
    }

    when (addingCheckState) {
        PostAddingViewModel.AddingCheckState.DescriptionTooShort -> {
            ShowToast(value = "Description too short")
            viewModel.clearAddingCheckState()
        }

        PostAddingViewModel.AddingCheckState.DurationNotSelected -> {
            ShowToast(value = "Duration not selected")
            viewModel.clearAddingCheckState()
        }

        PostAddingViewModel.AddingCheckState.ImageNotSelected -> {
            ShowToast(value = "Image not selected")
            viewModel.clearAddingCheckState()
        }

        PostAddingViewModel.AddingCheckState.Initial -> {}
        PostAddingViewModel.AddingCheckState.TitleTooShort -> {
            ShowToast(value = "Title too short")
            viewModel.clearAddingCheckState()
        }

        PostAddingViewModel.AddingCheckState.TypeNotSelected -> {
            ShowToast(value = "Type not selected")
            viewModel.clearAddingCheckState()
        }

        PostAddingViewModel.AddingCheckState.TitleTooLong -> {
            ShowToast(value = "Title too long")
            viewModel.clearAddingCheckState()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .background(Color(0xFFF3F9FF))
                .verticalScroll(scroll)
        ) {
            TopBarComponent(
                onIconClick = navigator::navigateToPostPreviewScreen,
                onBackClick = navigator::popBackStack
            )

            PostImageBlock(
                image = state.image,
                onImageChangeClick = {
                    galleryLauncher.launch("image/*")
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            PostDescriptionBlock(
                postTypes = postTypes,
                state = state,
                onTitleChange = { viewModel.updateTitle(it) },
                onDescriptionChange = { viewModel.updateDescription(it) },
                onPostTypeChange = { viewModel.updateType(it) }
            )

//            Spacer(modifier = Modifier.height(12.dp))
//
//            Column(
//                modifier = modifier
//                    .clip(RoundedCornerShape(30.dp))
//                    .background(Color.White)
//            ) {
//                LazyRow(
//                    modifier = Modifier.padding(
//                        start = 24.dp,
//                        end = 24.dp,
//                        top = 12.dp,
//                        bottom = 18.dp
//                    ),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    items(additionalBlockEntities.size) {
//                        val item = additionalBlockEntities[it]
//                        AdditionalBlockItem(
//                            iconId = item.iconId,
//                            contentDescription = item.contentDescription,
//                            content = item.title,
//                            isActive = item == activeAdditionalBlock.value
//                        )
//
//                        if (item != additionalBlockEntities[additionalBlockEntities.size - 1]) {
//                            Spacer(modifier = Modifier.width(8.dp))
//                        }
//                    }
//                }
//
//                CenteredItemList()
//            }

            Spacer(modifier = Modifier.height(12.dp))

            if (state.type != 1) {
                DurationBlock(
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
                        content = stringResource(id = R.string.add),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                },
                icon = {
                    Icon(
                        modifier = Modifier.padding(2.dp),
                        painter = painterResource(id = R.drawable.send_icon),
                        contentDescription = stringResource(id = R.string.create_post_button_icon_description)
                    )
                },
                onClick = { viewModel.saveEvent() }
            )
        }
    }
}































