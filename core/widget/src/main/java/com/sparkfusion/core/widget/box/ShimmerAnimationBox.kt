package com.sparkfusion.core.widget.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.resource.animation.shimmerLoadingAnimation

@Composable
fun ShimmerAnimationBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.CenterStart,
    isLoadingCompleted: Boolean,
    size: DpSize,
    isDarkModeEnabled: Boolean = false,
    backgroundColor: ShimmerAnimationBoxColor = DefaultShimmerAnimationBoxColor,
    shape: Shape = RoundedCornerShape(8.dp),
    content: @Composable (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(
                color = if (isLoadingCompleted) Color.Transparent else if (isDarkModeEnabled) backgroundColor.darkModeColor else backgroundColor.lightModeColor,
                shape = shape
            )
            .size(size)
            .shimmerLoadingAnimation(
                isLoadingCompleted = isLoadingCompleted,
                isDarkModeEnabled = isDarkModeEnabled
            ),
        contentAlignment = contentAlignment
    ) {
        content?.let { it() }
    }
}