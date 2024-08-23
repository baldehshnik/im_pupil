package com.sparkfusion.navigation.admin.host.baritems

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.features.admin.account.destination.AdminAccountDestination
import com.sparkfusion.features.admin.account.screen.AccountScreen
import com.sparkfusion.features.admin.home.screen.HomeScreen
import com.sparkfusion.features.admin.services.destination.AdminServicesDestination
import com.sparkfusion.features.admin.services.screen.ServicesScreen
import com.sparkfusion.navigation.admin.navigator.AccountNavigator
import com.sparkfusion.navigation.admin.navigator.HomeNavigator
import com.sparkfusion.navigation.admin.navigator.ServicesNavigator
import com.sparkfusion.navigation.admincoreport.destination.AdminHomeDestination
import com.sparkfusion.navigation.core.navigator.INavigator

fun NavGraphBuilder.adminBottomBarDestinations(navigator: INavigator) {
    val homeNavigator = HomeNavigator(navigator)
    val servicesNavigator = ServicesNavigator(navigator)
    val accountNavigator = AccountNavigator(navigator)

    composable(AdminAccountDestination.route) { AccountScreen(accountNavigator) }
    composable(AdminHomeDestination.route) { HomeScreen(homeNavigator) }
    composable(AdminServicesDestination.route) { ServicesScreen(servicesNavigator) }
}