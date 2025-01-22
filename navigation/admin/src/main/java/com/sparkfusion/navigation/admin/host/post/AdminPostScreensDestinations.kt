package com.sparkfusion.navigation.admin.host.post

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.core.image_crop.common.CROPPED_KEY
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
import com.sparkfusion.navigation.core.keys.EVENT_ID_KEY
import com.sparkfusion.navigation.core.navigator.INavigator

fun NavGraphBuilder.adminPostScreensDestinations(navigator: INavigator) {
    val postViewingNavigator = PostViewingNavigator(navigator)
    val postPreviewNavigator = PostPreviewNavigator(navigator)
    val postAddingNavigator = PostAddingNavigator(navigator)
    val postEditingNavigator = PostEditingNavigator(navigator)

    postViewing(postViewingNavigator, navigator)
    postPreview(postPreviewNavigator)
    postAdding(postAddingNavigator, navigator)
    postEditing(postEditingNavigator, navigator)
}

fun NavGraphBuilder.postViewing(navigator: IPostViewingNavigator, iNavigator: INavigator) {
    composable(AdminPostViewingDestination.route) {
        val id = iNavigator.previousBackStackEntry?.savedStateHandle?.get<Int>(EVENT_ID_KEY)
        id?.let {
            PostViewingScreen(
                navigator = navigator,
                id = it
            )
        }
    }
}

fun NavGraphBuilder.postPreview(navigator: IPostPreviewNavigator) {
    composable(AdminPostPreviewDestination.route) {
        PostPreviewScreen(navigator)
    }
}

fun NavGraphBuilder.postAdding(navigator: IPostAddingNavigator, defaultNavigator: INavigator) {
    composable(AdminPostAddingDestination.route) {
        PostAddingScreen(
            navigator = navigator,
            getCroppedImageBitmap = {
                defaultNavigator.currentBackStackEntry?.savedStateHandle?.get(CROPPED_KEY)
            }
        )
    }
}

fun NavGraphBuilder.postEditing(navigator: IPostEditingNavigator, iNavigator: INavigator) {
    composable(AdminPostEditingDestination.route) {
        val id = iNavigator.previousBackStackEntry?.savedStateHandle?.get<Int>(EVENT_ID_KEY)
        id?.let {
            PostEditingScreen(
                navigator = navigator,
                id = it,
                getCroppedImageBitmap = {
                    iNavigator.currentBackStackEntry?.savedStateHandle?.get(CROPPED_KEY)
                }
            )
        }
    }
}






















