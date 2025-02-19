package com.sparkfusion.navigation.pupilservicesport.practice

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

internal fun NavGraphBuilder.practiceNavGraph(
    navController: NavController
) {
    practiceService(navController)
    practiceDetailsService(navController)
}