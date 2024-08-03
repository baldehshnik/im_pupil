package com.sparkfusion.navigation.admin.navigator.post

import com.sparkfusion.features.admin.post.destination.AdminPostEditingDestination
import com.sparkfusion.features.admin.post.navigator.IPostViewingNavigator
import com.sparkfusion.navigation.admincoreport.navigator.IFeaturesNavigator

class PostViewingNavigator(private val navigator: IFeaturesNavigator) : IPostViewingNavigator {

    override fun navigateToPostEditingScreen() {
        navigator.navigateTo(AdminPostEditingDestination)
    }
}