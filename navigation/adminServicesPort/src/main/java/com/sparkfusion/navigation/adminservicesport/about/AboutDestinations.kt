package com.sparkfusion.navigation.adminservicesport.about

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.core.image_crop.common.CROPPED_KEY
import com.sparkfusion.core.image_crop.common.IMAGE_CROP_KEY
import com.sparkfusion.navigation.commoncoreport.destination.RectangleImageCropDestination
import com.sparkfusion.services.admin.about.destination.AboutEditDestination
import com.sparkfusion.services.admin.about.screen.AdminAboutServiceScreen
import com.sparkfusion.services.admin.about.screen.AdminEditAboutServiceScreen

fun NavGraphBuilder.aboutScreen(
    navController: NavController
) {
    composable(AboutDestination.route) {
        AdminAboutServiceScreen(
            onEditClick = { navController.navigate(AboutEditDestination.route) },
            onBackClick = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.aboutEditScreen(
    navController: NavController
) {
    composable(AboutEditDestination.route) {
        AdminEditAboutServiceScreen(
            onBackClick = { navController.popBackStack() },
            getCroppedImageBitmap = {
                Log.d("TAGTAG", "stack - " + navController.currentBackStackEntry)
                navController.currentBackStackEntry?.savedStateHandle?.get(CROPPED_KEY)
            },
            onCropNavigate = {
                navController.currentBackStackEntry?.savedStateHandle?.set(IMAGE_CROP_KEY, it)
                navController.navigate(RectangleImageCropDestination.route)
            }
        )
    }
}



























