package com.sparkfusion.navigation.pupil

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sparkfusion.navigation.pupil.destination.PupilHomeDestination

@Composable
fun PupilNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = PupilHomeDestination.route) {
        composable(PupilHomeDestination.route) { /* add route destination */ }
    }
}