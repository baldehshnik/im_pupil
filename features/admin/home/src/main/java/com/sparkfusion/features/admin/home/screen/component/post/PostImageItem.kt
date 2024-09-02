package com.sparkfusion.features.admin.home.screen.component.post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.features.admin.home.R
import com.sparkfusion.features.admin.home.widget.MoreVertButton

@Composable
fun PostImageIcon(
    modifier: Modifier = Modifier,
    isDarkModeEnabled: Boolean,
    onMoreVertButtonClick: () -> Unit
) {
    var isImageLoadingAnimationCompleted by remember { mutableStateOf(false) }

    val painter = rememberAsyncImagePainter(
        model = R.drawable.balloons_icon,
        onLoading = { isImageLoadingAnimationCompleted = false },
        onSuccess = { isImageLoadingAnimationCompleted = true }
    )

    Box {
        ShimmerImageBox(
            shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp),
            contentDescription = stringResource(R.string.post_image_description),
            size = DpSize((LocalConfiguration.current.screenWidthDp - 48).dp, 180.dp),
            painter = painter,
            isDarkModeEnabled = isDarkModeEnabled,
            isImageAnimationCompleted = isImageLoadingAnimationCompleted
        )

        if (isImageLoadingAnimationCompleted) {
            MoreVertButton(
                modifier = modifier
                    .padding(top = 6.dp, end = 12.dp)
                    .fillMaxWidth(),
                onClick = onMoreVertButtonClick
            )
        }
    }
}