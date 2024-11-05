package com.sparkfusion.navigation.adminservicesport.schedule

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

fun NavGraphBuilder.adminScheduleService(
    navController: NavController
) {
    scheduleScreen(navController)
    scheduleGroupScreen(navController)
    scheduleFacultiesScreen(navController)
}