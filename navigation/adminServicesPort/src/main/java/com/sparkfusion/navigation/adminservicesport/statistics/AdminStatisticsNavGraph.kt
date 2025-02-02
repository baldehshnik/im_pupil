package com.sparkfusion.navigation.adminservicesport.statistics

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.adminStatisticsService(navController: NavHostController) {
    statisticsTypeScreen(navController)
    statisticsGroupScreen(navController)
    statisticsGroupPassesDestination(navController)
    statisticsStudentScreen(navController)
    statisticsGroupSearchScreen(navController)
}
