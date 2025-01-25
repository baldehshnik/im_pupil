package com.sparkfusion.navigation.admin.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.sparkfusion.features.admin.post.destination.AdminPostViewingDestination
import com.sparkfusion.navigation.admin.host.baritems.adminBottomBarDestinations
import com.sparkfusion.navigation.admin.host.post.adminPostScreensDestinations
import com.sparkfusion.navigation.admin.host.screens.adminConfirmation
import com.sparkfusion.navigation.admin.host.screens.adminDetails
import com.sparkfusion.navigation.admin.host.screens.adminNotifications
import com.sparkfusion.navigation.admin.host.screens.adminSignUp
import com.sparkfusion.navigation.admin.host.screens.pupilConfirmation
import com.sparkfusion.navigation.admin.navigator.AdminDetailsNavigator
import com.sparkfusion.navigation.admin.navigator.Navigator
import com.sparkfusion.navigation.admin.navigator.NotificationsNavigator
import com.sparkfusion.navigation.admin.navigator.SignUpNavigator
import com.sparkfusion.navigation.admin.navigator.confirmation.AdminConfirmationNavigator
import com.sparkfusion.navigation.admin.navigator.confirmation.PupilConfirmationNavigator
import com.sparkfusion.navigation.admin.navigator.post.POST_ROUTE
import com.sparkfusion.navigation.adminservicesport.adminServicesNavHost

fun NavGraphBuilder.adminNavHost(
    navController: NavHostController
) {
    adminServicesNavHost(navController)

    val navigator = Navigator(navController)
    adminBottomBarDestinations(navigator)

    val adminDetailsNavigator = AdminDetailsNavigator(navigator)
    adminDetails(adminDetailsNavigator)

    val adminConfirmationDestination = AdminConfirmationNavigator(navigator)
    adminConfirmation(adminConfirmationDestination)

    val pupilConfirmationNavigator = PupilConfirmationNavigator(navigator)
    pupilConfirmation(pupilConfirmationNavigator)

    val notificationsNavigator = NotificationsNavigator(navigator)
    adminNotifications(notificationsNavigator)

    val signUpNavigator = SignUpNavigator(navigator)
    adminSignUp(signUpNavigator)

    navigation(AdminPostViewingDestination.route, POST_ROUTE) {
        adminPostScreensDestinations(navigator)
    }
}