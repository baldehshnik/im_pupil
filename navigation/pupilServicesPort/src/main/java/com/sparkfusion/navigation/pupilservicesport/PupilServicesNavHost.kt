package com.sparkfusion.navigation.pupilservicesport

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.sparkfusion.navigation.pupilservicesport.about.aboutNavGraph
import com.sparkfusion.navigation.pupilservicesport.magazine.magazineNavGraph
import com.sparkfusion.navigation.pupilservicesport.practice.practiceNavGraph
import com.sparkfusion.navigation.pupilservicesport.schedule.scheduleNavGraph
import com.sparkfusion.navigation.pupilservicesport.section.sectionNavGraph
import com.sparkfusion.navigation.pupilservicesport.session.sessionNavGraph
import com.sparkfusion.navigation.pupilservicesport.statistics.statisticsNavGraph
import com.sparkfusion.navigation.pupilservicesport.students.studentsNavGraph

fun NavGraphBuilder.pupilServicesNavHost(
    navController: NavHostController
) {
    aboutNavGraph(navController)
    sectionNavGraph(navController)
    practiceNavGraph(navController)
    studentsNavGraph(navController)
    sessionNavGraph(navController)
    scheduleNavGraph(navController)
    statisticsNavGraph(navController)
    magazineNavGraph(navController)
}















