package com.sparkfusion.navigation.pupil

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.features.pupil.sign_up.screen.SignUpScreenEnter
import com.sparkfusion.navigation.pupilcoreport.PupilHomeDestination
import com.sparkfusion.navigation.pupilcoreport.PupilSignUpDestination

fun NavGraphBuilder.pupilSignUp(navController: NavController) {
    composable(PupilSignUpDestination.route) {
        SignUpScreenEnter(
            onBackStackClick = navController::popBackStack,
            onNavigateToSignInScreen = navController::popBackStack
        )
    }
}

fun NavGraphBuilder.pupilHome(navController: NavController) {
    composable(PupilHomeDestination.route) {
        Box {
            Text(text = "FHJKSHKGJHSKJJKSHFKJHSJHFKJSHKFHSJHFKSK")
        }
    }
}

































