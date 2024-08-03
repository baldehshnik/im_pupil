package com.sparkfusion.navigation.admin.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sparkfusion.features.admin.account.destination.AdminAccountDestination
import com.sparkfusion.features.admin.account.screen.AccountScreen
import com.sparkfusion.features.admin.admin_details.destination.AdminDetailsDestination
import com.sparkfusion.features.admin.admin_details.screen.AdminDetailsScreen
import com.sparkfusion.features.admin.home.screen.HomeScreen
import com.sparkfusion.features.admin.notifications.destination.AdminNotificationsDestination
import com.sparkfusion.features.admin.notifications.screen.NotificationsScreen
import com.sparkfusion.features.admin.services.destination.AdminServicesDestination
import com.sparkfusion.features.admin.services.screen.ServicesScreen
import com.sparkfusion.navigation.admin.bottombar.ScreenWithBottomAppBar
import com.sparkfusion.navigation.admin.navigator.AccountNavigator
import com.sparkfusion.navigation.admin.navigator.AdminDetailsNavigator
import com.sparkfusion.navigation.admin.navigator.FeaturesNavigator
import com.sparkfusion.navigation.admin.navigator.HomeNavigator
import com.sparkfusion.navigation.admin.navigator.NotificationsNavigator
import com.sparkfusion.navigation.admin.navigator.ServicesNavigator
import com.sparkfusion.navigation.admincoreport.destination.AdminHomeDestination

fun NavGraphBuilder.adminNavHost(navController: NavHostController) {
    val featuresNavigator = FeaturesNavigator(navController)
    val bottomNavDestinations = listOf(
        AdminHomeDestination.route,
        AdminAccountDestination.route,
        AdminServicesDestination.route
    )

    composable(AdminHomeDestination.route) {
        ScreenWithBottomAppBar(navController, bottomNavDestinations) {
            HomeScreen(HomeNavigator(featuresNavigator))
        }
    }
    composable(AdminServicesDestination.route) {
        ScreenWithBottomAppBar(navController, bottomNavDestinations) {
            ServicesScreen(ServicesNavigator(featuresNavigator))
        }
    }
    composable(AdminAccountDestination.route) {
        ScreenWithBottomAppBar(navController, bottomNavDestinations) {
            AccountScreen(AccountNavigator(featuresNavigator))
        }
    }
    composable(AdminDetailsDestination.route) {
        AdminDetailsScreen(AdminDetailsNavigator(featuresNavigator))
    }
    composable(AdminNotificationsDestination.route) {
        NotificationsScreen(NotificationsNavigator(featuresNavigator))
    }
}