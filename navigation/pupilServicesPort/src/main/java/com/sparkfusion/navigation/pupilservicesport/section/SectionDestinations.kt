package com.sparkfusion.navigation.pupilservicesport.section

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.pupil.section.destination.SectionDetailsDestination
import com.sparkfusion.services.pupil.section.key.SECTION_ID_KEY
import com.sparkfusion.services.pupil.section.screen.SectionDetailsScreenEnter
import com.sparkfusion.services.pupil.section.screen.SectionsScreenEnter

internal fun NavGraphBuilder.sectionService(
    navController: NavController
) {
    composable(SectionDestination.route) {
        SectionsScreenEnter(
            onItemClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(SECTION_ID_KEY, it)
                navController.navigate(SectionDetailsDestination.route)
            },
            onBackClick = navController::popBackStack
        )
    }
}

internal fun NavGraphBuilder.sectionDetailsService(
    navController: NavController
) {
    composable(SectionDetailsDestination.route) {
        val sectionId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(SECTION_ID_KEY)

        if (sectionId != null) {
            SectionDetailsScreenEnter(
                sectionId = sectionId,
                onBackStackClick = navController::popBackStack
            )
        }
    }
}
























