package com.sparkfusion.navigation.adminservicesport.schedule

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.schedule.destination.ScheduleDestination
import com.sparkfusion.services.admin.schedule.destination.ScheduleGroupDestination
import com.sparkfusion.services.admin.schedule.screen.ScheduleFacultiesScreen
import com.sparkfusion.services.admin.schedule.screen.ScheduleGroupScreen
import com.sparkfusion.services.admin.schedule.screen.ScheduleScreen

fun NavGraphBuilder.scheduleFacultiesScreen(
    navController: NavController
) {
    composable(ScheduleFacultiesDestination.route) {
        ScheduleFacultiesScreen(
            onFacultyClick = {
                navController.navigate(ScheduleGroupDestination.route)
            },
            onBackClick = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.scheduleGroupScreen(
    navController: NavController
) {
    composable(ScheduleGroupDestination.route) {
        ScheduleGroupScreen(
            onBackClick = { navController.popBackStack() },
            onScheduleClick = {
                navController.navigate(ScheduleDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.scheduleScreen(
    navController: NavController
) {
    composable(ScheduleDestination.route) {
        ScheduleScreen(
            onBackClick = { navController.popBackStack() },
            onEditClick = {

            }
        )
    }
}
















