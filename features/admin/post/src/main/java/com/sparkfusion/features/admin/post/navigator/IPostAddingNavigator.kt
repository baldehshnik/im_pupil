package com.sparkfusion.features.admin.post.navigator

import androidx.navigation.NavBackStackEntry

interface IPostAddingNavigator {

    val currentBackStackEntry: NavBackStackEntry?

    fun navigateToPostPreviewScreen()
    fun popBackStack()

    fun <T> navigateToCircleImageCropScreen(key: String, value: T)
}