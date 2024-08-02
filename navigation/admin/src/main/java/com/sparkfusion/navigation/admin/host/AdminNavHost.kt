package com.sparkfusion.navigation.admin.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sparkfusion.features.admin.account.destination.AdminAccountDestination
import com.sparkfusion.features.admin.account.screen.AccountScreen
import com.sparkfusion.features.admin.home.screen.HomeScreen
import com.sparkfusion.navigation.admin.navigator.AccountNavigator
import com.sparkfusion.navigation.admin.navigator.FeaturesNavigator
import com.sparkfusion.navigation.admin.navigator.HomeNavigator
import com.sparkfusion.navigation.admincoreport.destination.AdminHomeDestination

fun NavGraphBuilder.adminNavHost(navController: NavHostController) {
    val featuresNavigator = FeaturesNavigator(navController)
    composable(AdminHomeDestination.route) {
        HomeScreen(HomeNavigator(featuresNavigator))
    }
    composable(AdminAccountDestination.route) {
        AccountScreen(AccountNavigator(featuresNavigator))
    }
}