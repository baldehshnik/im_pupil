package com.sparkfusion.navigation.admin.host.post

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.features.admin.post.destination.AdminPostAddingDestination
import com.sparkfusion.features.admin.post.destination.AdminPostEditingDestination
import com.sparkfusion.features.admin.post.destination.AdminPostPreviewDestination
import com.sparkfusion.features.admin.post.destination.AdminPostViewingDestination
import com.sparkfusion.features.admin.post.navigator.IPostAddingNavigator
import com.sparkfusion.features.admin.post.navigator.IPostEditingNavigator
import com.sparkfusion.features.admin.post.navigator.IPostPreviewNavigator
import com.sparkfusion.features.admin.post.navigator.IPostViewingNavigator
import com.sparkfusion.features.admin.post.screen.PostAddingScreen
import com.sparkfusion.features.admin.post.screen.PostEditingScreen
import com.sparkfusion.features.admin.post.screen.PostPreviewScreen
import com.sparkfusion.features.admin.post.screen.PostViewingScreen
import com.sparkfusion.navigation.admin.navigator.post.PostAddingNavigator
import com.sparkfusion.navigation.admin.navigator.post.PostEditingNavigator
import com.sparkfusion.navigation.admin.navigator.post.PostPreviewNavigator
import com.sparkfusion.navigation.admin.navigator.post.PostViewingNavigator
import com.sparkfusion.navigation.admincoreport.navigator.IFeaturesNavigator

fun NavGraphBuilder.adminPostScreensDestinations(featuresNavigator: IFeaturesNavigator) {
    val postViewingNavigator = PostViewingNavigator(featuresNavigator)
    val postPreviewNavigator = PostPreviewNavigator(featuresNavigator)
    val postAddingNavigator = PostAddingNavigator(featuresNavigator)
    val postEditingNavigator = PostEditingNavigator(featuresNavigator)

    postViewing(postViewingNavigator)
    postPreview(postPreviewNavigator)
    postAdding(postAddingNavigator)
    postEditing(postEditingNavigator)
}

fun NavGraphBuilder.postViewing(navigator: IPostViewingNavigator) {
    composable(AdminPostViewingDestination.route) {
        PostViewingScreen(navigator)
    }
}

fun NavGraphBuilder.postPreview(navigator: IPostPreviewNavigator) {
    composable(AdminPostPreviewDestination.route) {
        PostPreviewScreen(navigator)
    }
}

fun NavGraphBuilder.postAdding(navigator: IPostAddingNavigator) {
    composable(AdminPostAddingDestination.route) {
        PostAddingScreen(navigator)
    }
}

fun NavGraphBuilder.postEditing(navigator: IPostEditingNavigator) {
    composable(AdminPostEditingDestination.route) {
        PostEditingScreen(navigator)
    }
}