package com.sparkfusion.navigation.adminservicesport.practice

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

fun NavGraphBuilder.adminPracticeService(
    navController: NavController
) {
    practiceListScreen(navController)
    practiceDetailsScreen(navController)
    addPracticeScreen(navController)
    editPracticeScreen(navController)
}
