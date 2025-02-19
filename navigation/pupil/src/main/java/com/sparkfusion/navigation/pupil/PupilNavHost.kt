package com.sparkfusion.navigation.pupil

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.sparkfusion.navigation.pupilservicesport.pupilServicesNavHost

fun NavGraphBuilder.pupilNavHost(navController: NavHostController) {
    pupilServicesNavHost(navController)

    pupilSignUp(navController)
    pupilHome(navController)
    pupilAccount(navController)
    pupilServices(navController)
    pupilEventDetails(navController)
}

























