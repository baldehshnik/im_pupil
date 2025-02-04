package com.sparkfusion.navigation.adminservicesport.practice

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.core.image_crop.common.CROPPED_KEY
import com.sparkfusion.core.image_crop.common.IMAGE_CROP_KEY
import com.sparkfusion.core.image_crop.common.IMAGE_CROP_TYPE_KEY
import com.sparkfusion.core.image_crop.type.ImageCropType
import com.sparkfusion.core.image_crop.type.setImageCropType
import com.sparkfusion.navigation.commoncoreport.destination.DynamicRectangleCropDestination
import com.sparkfusion.services.admin.practice.destination.AddPracticeDestination
import com.sparkfusion.services.admin.practice.destination.EditPracticeDestination
import com.sparkfusion.services.admin.practice.destination.PracticeDetailsDestination
import com.sparkfusion.services.admin.practice.key.PRACTICE_ID_KEY
import com.sparkfusion.services.admin.practice.screen.AddPracticeScreen
import com.sparkfusion.services.admin.practice.screen.PracticeDetailsScreen
import com.sparkfusion.services.admin.practice.screen.PracticeEditScreen
import com.sparkfusion.services.admin.practice.screen.PracticeListScreen

fun NavGraphBuilder.practiceListScreen(
    navController: NavController
) {
    composable(PracticeListDestination.route) {
        PracticeListScreen(
            onBackClick = { navController.popBackStack() },
            onItemClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(PRACTICE_ID_KEY, it)
                navController.navigate(PracticeDetailsDestination.route)
            },
            onAddClick = {
                navController.navigate(AddPracticeDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.practiceDetailsScreen(
    navController: NavController
) {
    composable(PracticeDetailsDestination.route) {
        val practiceId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(PRACTICE_ID_KEY)

        if (practiceId != null) {
                PracticeDetailsScreen(
                    practiceId = practiceId,
                    onBackClick = { navController.popBackStack() },
                    onEditClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(PRACTICE_ID_KEY, practiceId)
                        navController.navigate(EditPracticeDestination.route)
                    }
                )
            }
    }
}

fun NavGraphBuilder.addPracticeScreen(
    navController: NavController
) {
    composable(AddPracticeDestination.route) {
        AddPracticeScreen(
            onBackClick = { navController.popBackStack() },
            onChangeIconClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(IMAGE_CROP_KEY, it)
                navController.currentBackStackEntry?.savedStateHandle?.setImageCropType(
                    IMAGE_CROP_TYPE_KEY,
                    ImageCropType.DynamicRectangleCrop(112.dp, 112.dp)
                )
                navController.navigate(DynamicRectangleCropDestination.route)
            },
            getCroppedImage = {
                navController.currentBackStackEntry?.savedStateHandle?.get(CROPPED_KEY)
            }
        )
    }
}

fun NavGraphBuilder.editPracticeScreen(
    navController: NavController
) {
    composable(EditPracticeDestination.route) {
        val practiceId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(PRACTICE_ID_KEY)

        if (practiceId != null) {
            PracticeEditScreen(
                practiceId = practiceId,
                onBackClick = { navController.popBackStack() },
                onChangeIconClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(IMAGE_CROP_KEY, it)
                    navController.currentBackStackEntry?.savedStateHandle?.setImageCropType(
                        IMAGE_CROP_TYPE_KEY,
                        ImageCropType.DynamicRectangleCrop(112.dp, 112.dp)
                    )
                    navController.navigate(DynamicRectangleCropDestination.route)
                },
                getCroppedImage = {
                    navController.currentBackStackEntry?.savedStateHandle?.get(CROPPED_KEY)
                }
            )
        }
    }
}




































