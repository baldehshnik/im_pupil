package com.sparkfusion.navigation.admin.navigator.post

import com.sparkfusion.features.admin.post.destination.AdminPostPreviewDestination
import com.sparkfusion.features.admin.post.navigator.IPostAddingNavigator
import com.sparkfusion.navigation.admincoreport.navigator.IFeaturesNavigator

class PostAddingNavigator(private val navigator: IFeaturesNavigator) : IPostAddingNavigator {

    override fun navigateToPostPreviewScreen() {
        navigator.navigateTo(AdminPostPreviewDestination)
    }
}