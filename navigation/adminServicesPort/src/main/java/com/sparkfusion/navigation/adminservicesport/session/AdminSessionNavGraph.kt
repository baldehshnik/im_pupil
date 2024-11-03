package com.sparkfusion.navigation.adminservicesport.session

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.adminSessionService(
    navController: NavHostController
) {
    sessionFacultiesScreen(navController)
    sessionGroupTestsScreen(navController)
    sessionTestAddingScreen(navController)
    sessionTestEditingScreen(navController)
}
