package com.sparkfusion.navigation.adminservicesport.students

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.adminStudentsService(
    navController: NavHostController
) {
    studentsFacultyScreen(navController)
    studentsGroupScreen(navController)
    studentsListScreen(navController)
    studentsAddGroupScreen(navController)
    studentsEditGroupScreen(navController)
    studentsAccountScreen(navController)
}
