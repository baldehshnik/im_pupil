package com.sparkfusion.navigation.pupilservicesport.section

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

internal fun NavGraphBuilder.sectionNavGraph(
    navController: NavController
) {
    sectionService(navController)
    sectionDetailsService(navController)
}