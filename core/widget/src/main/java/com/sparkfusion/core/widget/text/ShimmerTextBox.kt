package com.sparkfusion.core.widget.text

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import com.sparkfusion.core.widget.box.ShimmerAnimationBox

@Composable
fun ShimmerTextBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.CenterStart,
    size: DpSize,
    isDarkModeEnabled: Boolean,
    isLoadingAnimationCompleted: Boolean,
    textContent: @Composable AnimatedVisibilityScope.() -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment
    ) {
        AnimatedVisibility(visible = !isLoadingAnimationCompleted) {
            ShimmerAnimationBox(
                isLoadingCompleted = isLoadingAnimationCompleted,
                size = size,
                isDarkModeEnabled = isDarkModeEnabled
            )
        }

        AnimatedVisibility(
            visible = isLoadingAnimationCompleted,
            content = textContent
        )
    }
}