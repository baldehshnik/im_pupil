package com.sparkfusion.core.resource.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

const val NavigationAnimationDurationMillis = 400
const val DefaultAnimationNavigationScreenDelay = (NavigationAnimationDurationMillis - 150).toLong()

fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
    return slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Start,
        tween(NavigationAnimationDurationMillis)
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
    return slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Start,
        tween(NavigationAnimationDurationMillis)
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
    return slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.End,
        tween(NavigationAnimationDurationMillis)
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition(): ExitTransition {
    return slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.End,
        tween(NavigationAnimationDurationMillis)
    )
}