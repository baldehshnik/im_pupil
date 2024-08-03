package com.sparkfusion.navigation.admin.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.sparkfusion.features.admin.account.destination.AdminAccountDestination
import com.sparkfusion.features.admin.post.destination.AdminPostViewingDestination
import com.sparkfusion.features.admin.services.destination.AdminServicesDestination
import com.sparkfusion.navigation.admin.host.baritems.adminBottomBarDestinations
import com.sparkfusion.navigation.admin.host.post.adminPostScreensDestinations
import com.sparkfusion.navigation.admin.host.screens.adminDetails
import com.sparkfusion.navigation.admin.host.screens.adminNotifications
import com.sparkfusion.navigation.admin.navigator.AdminDetailsNavigator
import com.sparkfusion.navigation.admin.navigator.FeaturesNavigator
import com.sparkfusion.navigation.admin.navigator.NotificationsNavigator
import com.sparkfusion.navigation.admin.navigator.post.POST_ROUTE
import com.sparkfusion.navigation.admincoreport.destination.AdminHomeDestination

fun NavGraphBuilder.adminNavHost(navController: NavHostController) {
    val featuresNavigator = FeaturesNavigator(navController)
    val bottomNavDestinations = listOf(
        AdminHomeDestination.route,
        AdminAccountDestination.route,
        AdminServicesDestination.route
    )

    adminBottomBarDestinations(navController, featuresNavigator, bottomNavDestinations)

    val adminDetailsNavigator = AdminDetailsNavigator(featuresNavigator)
    adminDetails(adminDetailsNavigator)

    val notificationsNavigator = NotificationsNavigator(featuresNavigator)
    adminNotifications(notificationsNavigator)

    val postViewingRoute = AdminPostViewingDestination.route
    navigation(postViewingRoute, POST_ROUTE) {
        adminPostScreensDestinations(featuresNavigator)
    }
}