package com.sparkfusion.services.admin.about.screen

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.image_crop.launcher.rememberLauncherForImageCropping
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.about.R
import com.sparkfusion.services.admin.about.component.AddBlockItem
import com.sparkfusion.services.admin.about.viewmodel.EditAboutViewModel

@Composable
fun AdminEditAboutServiceScreen(
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
        navigateToImageCrop = {
            onCropNavigate(it)
        }
    )

    when (updateState) {
        EditAboutViewModel.UpdateState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearUpdateState()
        }

        EditAboutViewModel.UpdateState.Initial -> {}
        EditAboutViewModel.UpdateState.Progress -> {
            ShowToast(value = "Updating...")
        }

        EditAboutViewModel.UpdateState.Success -> {
            onBackClick()
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn {
            item {
                TopBar(title = "Edit", onBackClick = onBackClick)

                SFProRoundedText(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 12.dp),
                    color = descriptionColor(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    content = "There are two ways to describe: adding an image and adding a text description. This is done using blocks, the number of which is as many as you wish. You can combine text and image, or add just one option (empty blocks will not be added)."
                )
            }

            when (readState) {
                EditAboutViewModel.ReadState.Error -> {
                    item { ShowToast(value = "Error") }
                    viewModel.clearReadingState()
                }

                EditAboutViewModel.ReadState.Initial -> {}
                EditAboutViewModel.ReadState.Progress -> {
                    item { CircularProgressIndicator() }
                }

                is EditAboutViewModel.ReadState.Success -> {
                    val data = (readState as EditAboutViewModel.ReadState.Success).data
                    items(data) {
                        AddBlockItem(
                            item = it,
                            onValueChange = { value ->
                                viewModel.updateDescription(it, value)
                            },
                            onImageClick = {
                                viewModel.currentImageItem = it
                                galleryLauncher.launch("image/*")
                            }
                        )
                    }

                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            TextButton(onClick = { viewModel.addBlock() }) {
                                SFProRoundedText(
                                    content = "Add block",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }

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

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AdminEditAboutServiceScreenPreview() {
    AdminEditAboutServiceScreen(
        onBackClick = {},
        onCropNavigate = {},
        getCroppedImageBitmap = { null }
    )
}































