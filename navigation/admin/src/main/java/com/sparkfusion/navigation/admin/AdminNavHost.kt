package com.sparkfusion.navigation.admin

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sparkfusion.navigation.admin.destination.AdminHomeDestination

@Composable
fun AdminNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AdminHomeDestination.route) {
        composable(AdminHomeDestination.route) { /* add navigation channel */ }
    }
}