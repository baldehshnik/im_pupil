package com.sparkfusion.navigation.adminservicesport.sections

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.sections.destination.AddSectionDestination
import com.sparkfusion.services.admin.sections.destination.EditSectionDestination
import com.sparkfusion.services.admin.sections.destination.SectionDetailsDestination
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
        SectionDetailsScreen(
            onBackClick = { navController.popBackStack() },
            onEditClick = {
                navController.navigate(EditSectionDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.addSectionScreen(
    navController: NavController
) {
    composable(AddSectionDestination.route) {
        AddSectionScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.editSectionScreen(
    navController: NavController
) {
    composable(EditSectionDestination.route) {
        EditSectionScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
}

























