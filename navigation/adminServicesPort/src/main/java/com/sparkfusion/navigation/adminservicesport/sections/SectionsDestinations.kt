package com.sparkfusion.navigation.adminservicesport.sections

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
import com.sparkfusion.services.admin.sections.destination.AddSectionDestination
import com.sparkfusion.services.admin.sections.destination.EditSectionDestination
import com.sparkfusion.services.admin.sections.destination.SectionDetailsDestination
import com.sparkfusion.services.admin.sections.key.SECTION_ID_KEY
import com.sparkfusion.services.admin.sections.screen.AddSectionScreen
import com.sparkfusion.services.admin.sections.screen.EditSectionScreen
import com.sparkfusion.services.admin.sections.screen.SectionDetailsScreen
import com.sparkfusion.services.admin.sections.screen.SectionsListScreen

fun NavGraphBuilder.sectionsListScreen(
    navController: NavController
) {
    composable(SectionsListDestination.route) {
        SectionsListScreen(
            onBackClick = { navController.popBackStack() },
            onItemClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(SECTION_ID_KEY, it)
                navController.navigate(SectionDetailsDestination.route)
            },
            onAddClick = {
                navController.navigate(AddSectionDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.sectionDetailsScreen(
    navController: NavController
) {
    composable(SectionDetailsDestination.route) {
        val sectionId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(SECTION_ID_KEY)

        if (sectionId != null) {
            SectionDetailsScreen(
                sectionId = sectionId,
                onBackClick = { navController.popBackStack() },
                onEditClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        SECTION_ID_KEY,
                        sectionId
                    )
                    navController.navigate(EditSectionDestination.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.addSectionScreen(
    navController: NavController
) {
    composable(AddSectionDestination.route) {
        AddSectionScreen(
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

fun NavGraphBuilder.editSectionScreen(
    navController: NavController
) {
    composable(EditSectionDestination.route) {
        val sectionId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(SECTION_ID_KEY)

        if (sectionId != null) {
            EditSectionScreen(
                sectionId = sectionId,
                onBackClick = { navController.popBackStack() },
                onChangeIconClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(IMAGE_CROP_KEY, it)
                    navController.currentBackStackEntry?.savedStateHandle
                        ?.setImageCropType(
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

























