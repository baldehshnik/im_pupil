package com.sparkfusion.core.resource.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Animatable<Float, AnimationVector1D>.bubbleAnimation(
    coroutineScope: CoroutineScope,
    afterAnimationContent: (() -> Unit)? = null
) {
    coroutineScope.launch {
        animateTo(
            targetValue = 0.95f,
            animationSpec = tween(durationMillis = 80)
        )
        animateTo(
            targetValue = 1.05f,
            animationSpec = tween(durationMillis = 80)
        )
        animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 80)
        )

        afterAnimationContent?.let { it() }
    }
}