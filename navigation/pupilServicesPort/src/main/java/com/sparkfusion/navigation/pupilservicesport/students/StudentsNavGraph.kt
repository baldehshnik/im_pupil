package com.sparkfusion.navigation.pupilservicesport.students

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

internal fun NavGraphBuilder.studentsNavGraph(
    navController: NavController
) {
    studentsService(navController)
    studentDetailsService(navController)
}