package com.sparkfusion.navigation.pupilservicesport.practice

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.pupil.practice.destination.PracticeDetailsDestination
import com.sparkfusion.services.pupil.practice.key.PRACTICE_ID_KEY
import com.sparkfusion.services.pupil.practice.screen.PracticeDetailsScreenEnter
import com.sparkfusion.services.pupil.practice.screen.PracticeScreenEnter

internal fun NavGraphBuilder.practiceService(
    navController: NavController
) {
    composable(PracticeDestination.route) {
        PracticeScreenEnter(
            onItemClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(PRACTICE_ID_KEY, it)
                navController.navigate(PracticeDetailsDestination.route)
            },
            onBackClick = navController::popBackStack
        )
    }
}

internal fun NavGraphBuilder.practiceDetailsService(
    navController: NavController
) {
    composable(PracticeDetailsDestination.route) {
        val practiceId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(PRACTICE_ID_KEY)

        if (practiceId != null) {
            PracticeDetailsScreenEnter(
                practiceId = practiceId,
                onBackClick = navController::popBackStack
            )
        }
    }
}



















