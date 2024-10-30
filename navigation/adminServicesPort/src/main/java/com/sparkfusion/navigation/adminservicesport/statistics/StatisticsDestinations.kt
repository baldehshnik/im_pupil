package com.sparkfusion.navigation.adminservicesport.statistics

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.statistics.destination.StatisticsGroupDestination
import com.sparkfusion.services.admin.statistics.destination.StatisticsGroupSearchDestination
import com.sparkfusion.services.admin.statistics.destination.StatisticsTypeDestination
import com.sparkfusion.services.admin.statistics.destination.StudentStatisticsDestination
import com.sparkfusion.services.admin.statistics.screen.StatisticGroupScreen
import com.sparkfusion.services.admin.statistics.screen.StatisticGroupSearchScreen
import com.sparkfusion.services.admin.statistics.screen.StatisticTypeScreen
import com.sparkfusion.services.admin.statistics.screen.StatisticsFacultiesScreen
import com.sparkfusion.services.admin.statistics.screen.StudentStatisticsScreen

fun NavGraphBuilder.statisticsStudentScreen(
    navController: NavController
) {
    composable(StudentStatisticsDestination.route) {
        StudentStatisticsScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.statisticsTypeScreen(
    navController: NavController
) {
    composable(StatisticsTypeDestination.route) {
        StatisticTypeScreen(
            onTypeClick = { navController.navigate(StatisticsFacultiesDestination.route) },
            onBackClick = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.statisticsGroupSearchScreen(
    navController: NavController
) {
    composable(StatisticsGroupSearchDestination.route) {
        StatisticGroupSearchScreen(
            onBackClick = { navController.popBackStack() },
            onNextClick = {
                navController.navigate(StatisticsGroupDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.statisticsGroupScreen(
    navController: NavController
) {
    composable(StatisticsGroupDestination.route) {
        StatisticGroupScreen(
            onBackClick = { navController.popBackStack() },
            onStudentClick = {
                navController.navigate(StudentStatisticsDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.statisticsFacultiesScreen(
    navController: NavController
) {
    composable(StatisticsFacultiesDestination.route) {
        StatisticsFacultiesScreen(
            onBackClick = { navController.popBackStack() },
            onFacultyClick = {
                navController.navigate(StatisticsGroupSearchDestination.route)
            }
        )
    }
}


































