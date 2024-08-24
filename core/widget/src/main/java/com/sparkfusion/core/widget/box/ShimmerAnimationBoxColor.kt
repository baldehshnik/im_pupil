package com.sparkfusion.core.widget.box

import androidx.compose.ui.graphics.Color
import com.sparkfusion.core.resource.theme.surfaceContainerHighestDark

open class ShimmerAnimationBoxColor(
    val lightModeColor: Color,
    val darkModeColor: Color
)

data object DefaultShimmerAnimationBoxColor : ShimmerAnimationBoxColor(
    lightModeColor = Color.LightGray,
    darkModeColor = surfaceContainerHighestDark
)
