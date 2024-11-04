package com.sparkfusion.navigation.adminservicesport.magazine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

fun NavGraphBuilder.adminMagazineService(
    navController: NavController
) {
    magazineHistoryScreen(navController)
    magazineScheduleScreen(navController)
    magazineFacultiesScreen(navController)
    magazineHistoryDateScreen(navController)
    magazineStudentsListScreen(navController)
    magazineStudentDetailsScreen(navController)
}
