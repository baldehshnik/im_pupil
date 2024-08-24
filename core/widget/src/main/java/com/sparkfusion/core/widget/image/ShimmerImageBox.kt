package com.sparkfusion.core.widget.image

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.DpSize
import coil.compose.AsyncImagePainter
import com.sparkfusion.core.widget.box.ShimmerAnimationBox

@Composable
fun ShimmerImageBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.CenterStart,
    shape: Shape = CircleShape,
    contentDescription: String?,
    size: DpSize,
    painter: AsyncImagePainter,
    isDarkModeEnabled: Boolean,
    isImageAnimationCompleted: Boolean
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment
    ) {
        AnimatedVisibility(visible = !isImageAnimationCompleted) {
            ShimmerAnimationBox(
                isLoadingCompleted = isImageAnimationCompleted,
                size = size,
                isDarkModeEnabled = isDarkModeEnabled,
                shape = shape
            )
        }

        Image(
            modifier = Modifier
                .size(size)
                .clip(shape),
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
        )
    }
}