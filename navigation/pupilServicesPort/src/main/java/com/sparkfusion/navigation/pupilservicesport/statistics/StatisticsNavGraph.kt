package com.sparkfusion.navigation.pupilservicesport.statistics

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

internal fun NavGraphBuilder.statisticsNavGraph(
    navController: NavController
) {
    statisticsService(navController)
    studentStatisticsService(navController)
    groupPassesService(navController)
}