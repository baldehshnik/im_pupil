package com.sparkfusion.navigation.common.host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.sparkfusion.navigation.admin.host.adminNavHost
import com.sparkfusion.navigation.admincoreport.destination.AdminHomeDestination
import com.sparkfusion.navigation.common.host.screens.commonNavHost
import com.sparkfusion.navigation.common.type.AccountType
import com.sparkfusion.navigation.commoncoreport.destination.WelcomeScreenDestination

@Composable
fun AppNavHost(navController: NavHostController, accountType: AccountType) {
    NavHost(navController = navController, startDestination = accountType.type) {
        navigation(WelcomeScreenDestination.route, AccountType.Common.type) {
            commonNavHost(navController)
        }
        navigation(AdminHomeDestination.route, AccountType.Admin.type) {
            adminNavHost(navController)
        }
    }
}