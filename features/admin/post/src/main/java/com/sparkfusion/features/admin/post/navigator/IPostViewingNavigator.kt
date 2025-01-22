package com.sparkfusion.features.admin.post.navigator

interface IPostViewingNavigator {

    fun navigateToPostEditingScreen(id: Int)

    fun popBackStack()
}