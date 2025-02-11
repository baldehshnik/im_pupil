package com.sparkfusion.services.admin.about.screen

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.image_crop.launcher.rememberLauncherForImageCropping
import com.sparkfusion.services.admin.about.R
import com.sparkfusion.services.admin.about.component.EditAboutScreenContent
import com.sparkfusion.services.admin.about.handler.UpdateStateHandler
import com.sparkfusion.services.admin.about.viewmodel.EditAboutViewModel

@Composable
fun AdminEditAboutServiceScreenEnter(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCropNavigate: (Bitmap) -> Unit,
    getCroppedImageBitmap: () -> Bitmap?
) {
    AdminEditAboutServiceScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onCropNavigate = onCropNavigate,
        getCroppedImageBitmap = getCroppedImageBitmap
    )
}

@Composable
private fun AdminEditAboutServiceScreen(
    modifier: Modifier = Modifier,
    viewModel: EditAboutViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCropNavigate: (Bitmap) -> Unit,
    getCroppedImageBitmap: () -> Bitmap?
) {
    LaunchedEffect(Unit) {
        viewModel.readBlocks()
        viewModel.updateImage(getCroppedImageBitmap())
    }

    val readState by viewModel.readState.collectAsStateWithLifecycle()
    val updateState by viewModel.updateState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForImageCropping(
        context = context,
        navigateToImageCrop = { onCropNavigate(it) }
    )

    UpdateStateHandler(
        updateState = updateState,
        onClearUpdateState = { viewModel.clearUpdateState() },
        onBackClick = onBackClick
    )

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        EditAboutScreenContent(
            readState = readState,
            onBackClick = onBackClick,
            onClearReadingState = { viewModel.clearReadingState() },
            onAddBlockClick = { viewModel.addBlock() },
            onImageChangeClick = { galleryLauncher.launch("image/*") },
            onChangeCurrentImage = { viewModel.currentImageItem = it },
            onUpdateDescription = { model, value -> viewModel.updateDescription(model, value) }
        )

        if (readState is EditAboutViewModel.ReadState.Success) {
            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
                onClick = { viewModel.updateBlocks() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.check_icon),
                    contentDescription = null
                )
            }
        }
    }
}

































