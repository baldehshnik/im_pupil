package com.sparkfusion.navigation.adminservicesport

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.sparkfusion.navigation.adminservicesport.students.adminStudentsService

fun NavGraphBuilder.adminServicesNavHost(
    navController: NavHostController
) {
    adminStudentsService(navController)
}
