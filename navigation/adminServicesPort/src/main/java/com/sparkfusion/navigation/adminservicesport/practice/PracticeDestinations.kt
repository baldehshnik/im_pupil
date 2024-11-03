package com.sparkfusion.navigation.adminservicesport.practice

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.practice.destination.AddPracticeDestination
import com.sparkfusion.services.admin.practice.destination.EditPracticeDestination
import com.sparkfusion.services.admin.practice.destination.PracticeDetailsDestination
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
        PracticeDetailsScreen(
            onBackClick = { navController.popBackStack() },
            onEditClick = {
                navController.navigate(EditPracticeDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.addPracticeScreen(
    navController: NavController
) {
    composable(AddPracticeDestination.route) {
        AddPracticeScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.editPracticeScreen(
    navController: NavController
) {
    composable(EditPracticeDestination.route) {
        PracticeEditScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
}




































