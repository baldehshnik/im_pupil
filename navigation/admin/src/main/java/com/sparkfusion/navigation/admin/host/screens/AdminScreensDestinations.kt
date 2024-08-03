package com.sparkfusion.navigation.admin.host.screens

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.features.admin.admin_details.destination.AdminDetailsDestination
import com.sparkfusion.features.admin.admin_details.navigator.IAdminDetailsNavigator
import com.sparkfusion.features.admin.admin_details.screen.AdminDetailsScreen
import com.sparkfusion.features.admin.notifications.destination.AdminNotificationsDestination
import com.sparkfusion.features.admin.notifications.navigator.INotificationsNavigator
import com.sparkfusion.features.admin.notifications.screen.NotificationsScreen

fun NavGraphBuilder.adminNotifications(navigator: INotificationsNavigator) {
    composable(AdminNotificationsDestination.route) {
        NotificationsScreen(navigator)
    }
}

fun NavGraphBuilder.adminDetails(navigator: IAdminDetailsNavigator) {
    composable(AdminDetailsDestination.route) {
        AdminDetailsScreen(navigator)
    }
}