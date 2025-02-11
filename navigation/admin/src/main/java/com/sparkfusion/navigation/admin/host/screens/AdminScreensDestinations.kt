package com.sparkfusion.navigation.admin.host.screens

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.features.admin.admin_details.destination.AdminDetailsDestination
import com.sparkfusion.features.admin.admin_details.navigator.IAdminDetailsNavigator
import com.sparkfusion.features.admin.admin_details.screen.AdminDetailsScreenEnter
import com.sparkfusion.features.admin.confirmation.destination.AdminConfirmationDestination
import com.sparkfusion.features.admin.confirmation.destination.PupilConfirmationDestination
import com.sparkfusion.features.admin.confirmation.navigator.IAdminConfirmationNavigator
import com.sparkfusion.features.admin.confirmation.navigator.IPupilConfirmationNavigator
import com.sparkfusion.features.admin.confirmation.screen.AdminConfirmationScreen
import com.sparkfusion.features.admin.confirmation.screen.PupilConfirmationScreen
import com.sparkfusion.features.admin.notifications.destination.AdminNotificationsDestination
import com.sparkfusion.features.admin.notifications.navigator.INotificationsNavigator
import com.sparkfusion.features.admin.notifications.screen.NotificationsScreen
import com.sparkfusion.features.admin.sign_up.navigator.ISignUpNavigator
import com.sparkfusion.features.admin.sign_up.screen.SignUpScreen
import com.sparkfusion.navigation.admincoreport.destination.AdminSignUpDestination
import com.sparkfusion.navigation.core.keys.ADMIN_ACCESS_KEY
import com.sparkfusion.navigation.core.keys.ADMIN_ID_KEY

fun NavGraphBuilder.adminNotifications(navigator: INotificationsNavigator) {
    composable(AdminNotificationsDestination.route) {
        NotificationsScreen(navigator)
    }
}

fun NavGraphBuilder.adminDetails(
    navigator: IAdminDetailsNavigator
) {
    composable(AdminDetailsDestination.route) {
        val id = navigator.previousNavBackStackEntry?.savedStateHandle?.get<Int>(ADMIN_ID_KEY)
        val accessMode =
            navigator.previousNavBackStackEntry?.savedStateHandle?.get<Int>(ADMIN_ACCESS_KEY)
        if (id != null && accessMode != null)
            AdminDetailsScreenEnter(
                navigator = navigator,
                id = id,
                accessMode = accessMode
            )
    }
}

fun NavGraphBuilder.adminConfirmation(
    navController: IAdminConfirmationNavigator
) {
    composable(AdminConfirmationDestination.route) {
        AdminConfirmationScreen(
            navigator = navController
        )
    }
}

fun NavGraphBuilder.pupilConfirmation(
    navController: IPupilConfirmationNavigator
) {
    composable(PupilConfirmationDestination.route) {
        PupilConfirmationScreen(
            navigator = navController
        )
    }
}

fun NavGraphBuilder.adminSignUp(navigator: ISignUpNavigator) {
    composable(AdminSignUpDestination.route) {
        SignUpScreen(navigator)
    }
}





















