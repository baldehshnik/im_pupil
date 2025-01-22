package com.sparkfusion.navigation.admin.navigator.post

import com.sparkfusion.features.admin.post.destination.AdminPostEditingDestination
import com.sparkfusion.features.admin.post.navigator.IPostViewingNavigator
import com.sparkfusion.navigation.core.keys.EVENT_ID_KEY
import com.sparkfusion.navigation.core.navigator.INavigator

class PostViewingNavigator(private val navigator: INavigator) : IPostViewingNavigator {

    override fun navigateToPostEditingScreen(id: Int) {
        navigator.currentBackStackEntry?.savedStateHandle?.set(EVENT_ID_KEY, id)
        navigator.navigateTo(AdminPostEditingDestination)
    }

    override fun popBackStack() {
        navigator.popBackStack()
    }
}