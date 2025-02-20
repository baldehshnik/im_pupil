package com.sparkfusion.navigation.pupilservicesport.session

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.pupil.session.screen.SessionScreenEnter

internal fun NavGraphBuilder.sessionService(
    navController: NavController
) {
    composable(SessionDestination.route) {
        SessionScreenEnter(
            onBackClick = navController::popBackStack
        )
    }
}





























