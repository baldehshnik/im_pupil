package com.sparkfusion.navigation.common.host

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import com.sparkfusion.core.resource.animation.enterTransition
import com.sparkfusion.core.resource.animation.exitTransition
import com.sparkfusion.core.resource.animation.popEnterTransition
import com.sparkfusion.core.resource.animation.popExitTransition
import com.sparkfusion.navigation.admin.bottombar.AdminBottomAppBar
import com.sparkfusion.navigation.admin.host.adminNavHost
import com.sparkfusion.navigation.admin.host.baritems.getAdminBottomBarItemRoutes
import com.sparkfusion.navigation.admincoreport.destination.AdminHomeDestination
import com.sparkfusion.navigation.common.host.screens.commonNavHost
import com.sparkfusion.navigation.common.type.AccountType
import com.sparkfusion.navigation.common.utils.ChangeNavigationBarColor
import com.sparkfusion.navigation.commoncoreport.destination.WelcomeScreenDestination

@Composable
fun AppNavHost(navController: NavHostController, accountType: AccountType) {
    val adminBottomBarItemRoutes = getAdminBottomBarItemRoutes()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute by remember(backStackEntry) {
        derivedStateOf { backStackEntry?.destination?.route }
    }

    val showBottomBar = currentRoute in adminBottomBarItemRoutes

    ChangeNavigationBarColor(showBottomBar)

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                AdminBottomAppBar(navController, AdminHomeDestination.route)
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = accountType.type,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) {
            navigation(WelcomeScreenDestination.route, AccountType.Common.type) {
                commonNavHost(navController)
            }
            navigation(AdminHomeDestination.route, AccountType.Admin.type) {
                adminNavHost(navController)
            }
        }
    }
}