package com.sparkfusion.navigation.adminservicesport.statistics

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.statistics.destination.StatisticsFacultiesDestination
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

        )
    }
}

fun NavGraphBuilder.statisticsTypeScreen(
    navController: NavController
) {
    composable(StatisticsTypeDestination.route) {
        StatisticTypeScreen(

        )
    }
}

fun NavGraphBuilder.statisticsGroupSearchScreen(
    navController: NavController
) {
    composable(StatisticsGroupSearchDestination.route) {
        StatisticGroupSearchScreen(

        )
    }
}

fun NavGraphBuilder.statisticsGroupScreen(
    navController: NavController
) {
    composable(StatisticsGroupDestination.route) {
        StatisticGroupScreen(

        )
    }
}

fun NavGraphBuilder.statisticsFacultiesScreen(
    navController: NavController
) {
    composable(StatisticsFacultiesDestination.route) {
        StatisticsFacultiesScreen(

        )
    }
}


































