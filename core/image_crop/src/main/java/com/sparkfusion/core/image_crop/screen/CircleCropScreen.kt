package com.sparkfusion.core.image_crop.screen

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.image_crop.R
import com.sparkfusion.core.image_crop.component.CircleCropSpace
import com.sparkfusion.core.image_crop.crop.ImageCrop
import com.sparkfusion.core.image_crop.navigator.IImageCropNavigator
import com.sparkfusion.core.resource.theme.backgroundDark
import com.sparkfusion.core.resource.theme.surfaceContainerHighestDark
import com.sparkfusion.core.widget.text.SFProRoundedText
import kotlinx.coroutines.launch

@Composable
fun CircleCropScreen(
    navController: IImageCropNavigator,
    bitmap: Bitmap,
    modifier: Modifier = Modifier
) {
    val image = bitmap.asImageBitmap()

    val coroutineScope = rememberCoroutineScope()
    val imageCropper by remember { mutableStateOf(ImageCrop(image)) }
    var croppedImage by remember { mutableStateOf<ImageBitmap?>(null) }

    val configuration = LocalConfiguration.current
    val screenWidthPx = with(LocalDensity.current) { configuration.screenWidthDp.dp.toPx() }
    val cropCircleDiameter = remember {
        if (screenWidthPx < minOf(image.width, image.height)) {
            screenWidthPx
        } else {
            minOf(image.width, image.height).toFloat()
        }
    }

    var scale by remember { mutableFloatStateOf(1f) }
    var transform by remember { mutableStateOf(Offset(0f, 0f)) }
    var isPreviewImageDialogShow by remember { mutableStateOf(false) }

    if (isPreviewImageDialogShow) {
        croppedImage?.let {
            navController.navigateToPreviousScreen(it.asAndroidBitmap())
        }
    }

    LaunchedEffect(croppedImage) {
        if (croppedImage != null) {
            // handle
            isPreviewImageDialogShow = true
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundDark)
    ) {
        CircleCropSpace(
            modifier = Modifier.fillMaxSize(),
            cropCircleDiameter = cropCircleDiameter,
            image = image,
            onScaleChange = { scale = it },
            onTransformChange = { transform = it }
        )

        ActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(vertical = 20.dp, horizontal = 24.dp),
            icon = painterResource(id = R.drawable.round_crop),
            onClick = {
                coroutineScope.launch {
                    croppedImage = imageCropper.cropImage(
                        cropSize = cropCircleDiameter,
                        transform = transform,
                        scale = scale
                    ).asImageBitmap()
                }
            }
        )
    }
}

@Composable
private fun ActionButton(
    modifier: Modifier,
    icon: Painter,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = surfaceContainerHighestDark),
        shape = MaterialTheme.shapes.small,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    tint = Color.White,
                    painter = icon,
                    contentDescription = stringResource(R.string.image_crop_icon_description)
                )

                Spacer(modifier = Modifier.size(12.dp))

                SFProRoundedText(
                    content = stringResource(R.string.crop),
                    fontWeight = FontWeight.Medium
                )
            }
        },
        onClick = onClick
    )
}