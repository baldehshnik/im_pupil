package com.sparkfusion.navigation.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.sparkfusion.features.common.welcome.WelcomeScreen
import com.sparkfusion.navigation.admin.host.adminNavHost
import com.sparkfusion.navigation.admincoreport.destination.AdminHomeDestination
import com.sparkfusion.navigation.common.type.AccountType

@Composable
fun AppNavHost(navController: NavHostController, accountType: AccountType) {
    NavHost(navController = navController, startDestination = accountType.type) {
        composable(AccountType.Welcome.type) { WelcomeScreen() }
        navigation(AdminHomeDestination.route, AccountType.Admin.type) {
            adminNavHost(navController)
        }
//        composable(AccountType.Admin.type) { AdminNavHost(navController = navController) }
//        composable(AccountType.Pupil.type) { PupilNavHost(navController = navController) }
    }
}