package com.sparkfusion.navigation.adminservicesport.about

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.core.image_crop.common.CROPPED_KEY
import com.sparkfusion.core.image_crop.common.IMAGE_CROP_KEY
import com.sparkfusion.navigation.commoncoreport.destination.RectangleImageCropDestination
import com.sparkfusion.services.admin.about.destination.AboutEditDestination
import com.sparkfusion.services.admin.about.screen.AdminAboutServiceScreenEnter
import com.sparkfusion.services.admin.about.screen.AdminEditAboutServiceScreenEnter

fun NavGraphBuilder.aboutScreen(
    navController: NavController
) {
    composable(AboutDestination.route) {
        AdminAboutServiceScreenEnter(
            onEditClick = { navController.navigate(AboutEditDestination.route) },
            onBackClick = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.aboutEditScreen(
    navController: NavController
) {
    composable(AboutEditDestination.route) {
        AdminEditAboutServiceScreenEnter(
            onBackClick = { navController.popBackStack() },
            getCroppedImageBitmap = {
                navController.currentBackStackEntry?.savedStateHandle?.get(CROPPED_KEY)
            },
            onCropNavigate = {
                navController.currentBackStackEntry?.savedStateHandle?.set(IMAGE_CROP_KEY, it)
                navController.navigate(RectangleImageCropDestination.route)
            }
        )
    }
}



























