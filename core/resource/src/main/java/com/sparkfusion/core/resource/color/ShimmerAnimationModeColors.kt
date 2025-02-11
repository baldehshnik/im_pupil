package com.sparkfusion.core.resource.color

import androidx.compose.ui.graphics.Color

internal data class ShimmerAnimationModeColors(
    private val isDarkTheme: Boolean
) {

    internal fun getColors(): List<Color> {
        val color = if (isDarkTheme) Color.Black else Color.White
        return listOf(
            color.copy(alpha = 0.3f),
            color.copy(alpha = 0.5f),
            color.copy(alpha = 1.0f),
            color.copy(alpha = 0.5f),
            color.copy(alpha = 0.3f)
        )
    }
}