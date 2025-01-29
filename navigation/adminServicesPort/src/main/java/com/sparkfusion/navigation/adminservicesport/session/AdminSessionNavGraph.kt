package com.sparkfusion.navigation.adminservicesport.session

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.adminSessionService(
    navController: NavHostController
) {
    sessionGroupTestsScreen(navController)
    sessionTestAddingScreen(navController)
    sessionTestEditingScreen(navController)
}
