package com.sparkfusion.navigation.adminservicesport.sections

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

fun NavGraphBuilder.adminSectionsService(
    navController: NavController
) {
    sectionDetailsScreen(navController)
    sectionsListScreen(navController)
    addSectionScreen(navController)
    editSectionScreen(navController)
}
