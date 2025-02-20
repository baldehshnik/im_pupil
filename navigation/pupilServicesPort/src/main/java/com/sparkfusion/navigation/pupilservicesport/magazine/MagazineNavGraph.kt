package com.sparkfusion.navigation.pupilservicesport.magazine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

internal fun NavGraphBuilder.magazineNavGraph(
    navController: NavController
) {
    magazineService(navController)
    magazineHistoryService(navController)
    magazineHistoryDateService(navController)
    magazineStudentsListService(navController)
    magazineStudentDetailsService(navController)
}