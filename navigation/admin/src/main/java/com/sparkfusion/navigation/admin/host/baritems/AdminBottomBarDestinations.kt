package com.sparkfusion.navigation.admin.host.baritems

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sparkfusion.features.admin.account.destination.AdminAccountDestination
import com.sparkfusion.features.admin.account.navigator.IAccountNavigator
import com.sparkfusion.features.admin.account.screen.AccountScreen
import com.sparkfusion.features.admin.home.navigator.IHomeNavigator
import com.sparkfusion.features.admin.home.screen.HomeScreen
import com.sparkfusion.features.admin.services.destination.AdminServicesDestination
import com.sparkfusion.features.admin.services.navigator.IServicesNavigator
import com.sparkfusion.features.admin.services.screen.ServicesScreen
import com.sparkfusion.navigation.admin.bottombar.ScreenWithBottomAppBar
import com.sparkfusion.navigation.admin.navigator.AccountNavigator
import com.sparkfusion.navigation.admin.navigator.HomeNavigator
import com.sparkfusion.navigation.admin.navigator.ServicesNavigator
import com.sparkfusion.navigation.admincoreport.destination.AdminHomeDestination
import com.sparkfusion.navigation.admincoreport.navigator.IFeaturesNavigator

fun NavGraphBuilder.adminBottomBarDestinations(
    navController: NavHostController,
    featuresNavigator: IFeaturesNavigator,
    bottomNavDestinations: List<String>
) {
    val homeNavigator = HomeNavigator(featuresNavigator)
    val servicesNavigator = ServicesNavigator(featuresNavigator)
    val accountNavigator = AccountNavigator(featuresNavigator)

    adminHome(navController, homeNavigator, bottomNavDestinations)
    adminServices(navController, servicesNavigator, bottomNavDestinations)
    adminAccount(navController, accountNavigator, bottomNavDestinations)
}

fun NavGraphBuilder.adminAccount(
    navController: NavHostController,
    navigator: IAccountNavigator,
    bottomNavDestinations: List<String>
) {
    composable(AdminAccountDestination.route) {
        ScreenWithBottomAppBar(navController, bottomNavDestinations) {
            AccountScreen(navigator)
        }
    }
}

fun NavGraphBuilder.adminHome(
    navController: NavHostController,
    navigator: IHomeNavigator,
    bottomNavDestinations: List<String>
) {
    composable(AdminHomeDestination.route) {
        ScreenWithBottomAppBar(navController, bottomNavDestinations) {
            HomeScreen(navigator)
        }
    }
}

fun NavGraphBuilder.adminServices(
    navController: NavHostController,
    navigator: IServicesNavigator,
    bottomNavDestinations: List<String>
) {
    composable(AdminServicesDestination.route) {
        ScreenWithBottomAppBar(navController, bottomNavDestinations) {
            ServicesScreen(navigator)
        }
    }
}