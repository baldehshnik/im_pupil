package com.sparkfusion.navigation.adminservicesport.session

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.session.destination.SessionGroupTestsDestination
import com.sparkfusion.services.admin.session.destination.SessionTestAddingDestination
import com.sparkfusion.services.admin.session.destination.SessionTestEditingDestination
import com.sparkfusion.services.admin.session.screen.SessionFacultiesScreen
import com.sparkfusion.services.admin.session.screen.SessionGroupTestsScreen
import com.sparkfusion.services.admin.session.screen.SessionTestAddingScreen
import com.sparkfusion.services.admin.session.screen.SessionTestEditingScreen

fun NavGraphBuilder.sessionFacultiesScreen(
    navController: NavController
) {
    composable(SessionFacultiesDestination.route) {
        SessionFacultiesScreen(
            onBackClick = { navController.popBackStack() },
            onFacultyItemClick = {
                navController.navigate(SessionGroupTestsDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.sessionGroupTestsScreen(
    navController: NavController
) {
    composable(SessionGroupTestsDestination.route) {
        SessionGroupTestsScreen(
            onBackClick = { navController.popBackStack() },
            onAddItemClick = {
                navController.navigate(SessionTestAddingDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.sessionTestAddingScreen(
    navController: NavController
) {
    composable(SessionTestAddingDestination.route) {
        SessionTestAddingScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.sessionTestEditingScreen(
    navController: NavController
) {
    composable(SessionTestEditingDestination.route) {
        SessionTestEditingScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
}





















