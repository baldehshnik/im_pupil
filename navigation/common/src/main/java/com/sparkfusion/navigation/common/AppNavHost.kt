package com.sparkfusion.navigation.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sparkfusion.features.common.welcome.WelcomeScreen
import com.sparkfusion.navigation.common.destination.WelcomeScreenDestination
import com.sparkfusion.navigation.core.Destination

@Composable
fun AppNavHost(navController: NavHostController, startDestination: Destination) {
    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(WelcomeScreenDestination.route) { WelcomeScreen() }
    }
}