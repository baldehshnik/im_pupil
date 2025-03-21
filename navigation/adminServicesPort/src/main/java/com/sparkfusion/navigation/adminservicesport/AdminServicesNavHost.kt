package com.sparkfusion.navigation.adminservicesport

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.sparkfusion.navigation.adminservicesport.about.adminAboutService
import com.sparkfusion.navigation.adminservicesport.magazine.adminMagazineService
import com.sparkfusion.navigation.adminservicesport.practice.adminPracticeService
import com.sparkfusion.navigation.adminservicesport.schedule.adminScheduleService
import com.sparkfusion.navigation.adminservicesport.sections.adminSectionsService
import com.sparkfusion.navigation.adminservicesport.session.adminSessionService
import com.sparkfusion.navigation.adminservicesport.statistics.adminStatisticsService
import com.sparkfusion.navigation.adminservicesport.students.adminStudentsService

fun NavGraphBuilder.adminServicesNavHost(
    navController: NavHostController
) {
    adminStudentsService(navController)
    adminStatisticsService(navController)
    adminAboutService(navController)
    adminSessionService(navController)
    adminPracticeService(navController)
    adminSectionsService(navController)
    adminMagazineService(navController)
    adminScheduleService(navController)
}
