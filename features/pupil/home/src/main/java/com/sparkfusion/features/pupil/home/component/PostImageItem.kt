package com.sparkfusion.features.pupil.home.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.image.ShimmerImageBox

@Composable
internal fun PostImageIcon(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    var isImageLoadingAnimationCompleted by remember { mutableStateOf(false) }
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        onLoading = { isImageLoadingAnimationCompleted = false },
        onSuccess = { isImageLoadingAnimationCompleted = true }
    )

    Box(modifier = modifier) {
        ShimmerImageBox(
            shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp),
            contentDescription = null,
            size = DpSize((LocalConfiguration.current.screenWidthDp - 48).dp, 180.dp),
            painter = painter,
            isDarkModeEnabled = isSystemInDarkTheme(),
            isImageAnimationCompleted = isImageLoadingAnimationCompleted
        )
    }
}











