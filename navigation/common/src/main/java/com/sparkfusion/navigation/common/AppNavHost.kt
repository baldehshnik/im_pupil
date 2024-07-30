package com.sparkfusion.navigation.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sparkfusion.features.common.welcome.WelcomeScreen
import com.sparkfusion.navigation.admin.AdminNavHost
import com.sparkfusion.navigation.admin.destination.AdminHomeDestination
import com.sparkfusion.navigation.common.destination.WelcomeScreenDestination
import com.sparkfusion.navigation.core.Destination
import com.sparkfusion.navigation.pupil.PupilNavHost
import com.sparkfusion.navigation.pupil.destination.PupilHomeDestination

@Composable
fun AppNavHost(navController: NavHostController, startDestination: Destination) {
    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(WelcomeScreenDestination.route) { WelcomeScreen() }
        composable(AdminHomeDestination.route) { AdminNavHost(navController = navController) }
        composable(PupilHomeDestination.route) { PupilNavHost(navController = navController) }
    }
}