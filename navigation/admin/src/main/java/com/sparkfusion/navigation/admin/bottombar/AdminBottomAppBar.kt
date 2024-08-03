package com.sparkfusion.navigation.admin.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sparkfusion.features.admin.account.destination.AdminAccountDestination
import com.sparkfusion.features.admin.services.destination.AdminServicesDestination
import com.sparkfusion.navigation.admincoreport.destination.AdminHomeDestination
import com.sparkfusion.navigation.core.bottombar.BottomAppBar
import com.sparkfusion.navigation.core.bottombar.BottomBarItem

@Composable
fun AdminBottomAppBar(navController: NavHostController) {
    val items = listOf(
        BottomBarItem(AdminHomeDestination, Icons.Rounded.Home, "Home"),
        BottomBarItem(AdminAccountDestination, Icons.Rounded.AccountCircle, "Account"),
        BottomBarItem(AdminServicesDestination, Icons.Rounded.Favorite, "Services")
    )
    BottomAppBar(navController = navController, items = items)
}