package com.sparkfusion.navigation.pupilservicesport.about

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.pupil.about.screen.AboutScreenEnter

internal fun NavGraphBuilder.aboutService(
    navController: NavController
) {
    composable(AboutDestination.route) {
        AboutScreenEnter(
            onBackClick = navController::popBackStack
        )
    }
}
































