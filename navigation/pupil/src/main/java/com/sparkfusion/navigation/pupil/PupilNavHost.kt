package com.sparkfusion.navigation.pupil

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.pupilNavHost(navController: NavHostController) {
    pupilSignUp(navController)
    pupilHome(navController)
    pupilAccount(navController)
    pupilServices(navController)
    pupilEventDetails(navController)
}

























