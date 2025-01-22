package com.sparkfusion.navigation.admin.navigator.post

import androidx.navigation.NavBackStackEntry
import com.sparkfusion.features.admin.post.destination.AdminPostPreviewDestination
import com.sparkfusion.features.admin.post.navigator.IPostAddingNavigator
import com.sparkfusion.navigation.commoncoreport.destination.RectangleImageCropDestination
import com.sparkfusion.navigation.core.navigator.INavigator

class PostAddingNavigator(private val navigator: INavigator) : IPostAddingNavigator {

    override val currentBackStackEntry: NavBackStackEntry? = navigator.currentBackStackEntry

    override fun navigateToPostPreviewScreen() {
        navigator.navigateTo(AdminPostPreviewDestination)
    }

    override fun popBackStack() {
        navigator.popBackStack()
    }

    override fun <T> navigateToCircleImageCropScreen(key: String, value: T) {
        navigator.navigateToWithParam(RectangleImageCropDestination, key, value)
    }
}