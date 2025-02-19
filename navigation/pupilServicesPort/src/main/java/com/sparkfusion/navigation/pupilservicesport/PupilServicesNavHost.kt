package com.sparkfusion.navigation.pupilservicesport

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.sparkfusion.navigation.pupilservicesport.about.aboutNavGraph
import com.sparkfusion.navigation.pupilservicesport.practice.practiceNavGraph
import com.sparkfusion.navigation.pupilservicesport.section.sectionNavGraph
import com.sparkfusion.navigation.pupilservicesport.students.studentsNavGraph

fun NavGraphBuilder.pupilServicesNavHost(
    navController: NavHostController
) {
    aboutNavGraph(navController)
    sectionNavGraph(navController)
    practiceNavGraph(navController)
    studentsNavGraph(navController)
}















