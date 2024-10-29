package com.sparkfusion.navigation.adminservicesport.about

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.adminAboutService(
    navController: NavHostController
) {
    aboutScreen(navController)
    aboutEditScreen(navController)
}
