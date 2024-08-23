package com.sparkfusion.navigation.admin.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.sparkfusion.features.admin.account.destination.AdminAccountDestination
import com.sparkfusion.features.admin.services.destination.AdminServicesDestination
import com.sparkfusion.navigation.admin.R
import com.sparkfusion.navigation.admincoreport.destination.AdminHomeDestination
import com.sparkfusion.navigation.core.bottombar.BottomAppBar
import com.sparkfusion.navigation.core.bottombar.BottomBarImageItem
import com.sparkfusion.navigation.core.bottombar.BottomBarItem

@Composable
fun AdminBottomAppBar(navController: NavHostController, currentRoute: String?) {
    val items = listOf(
        BottomBarItem(
            AdminHomeDestination,
            BottomBarImageItem(
                R.drawable.outlined_home_icon, R.drawable.filled_home_icon
            ),
            stringResource(R.string.home),
        ),
        BottomBarItem(
            AdminServicesDestination,
            BottomBarImageItem(
                R.drawable.outlined_services_icon, R.drawable.filled_services_icon
            ),
            stringResource(R.string.services)
        ),
        BottomBarItem(
            AdminAccountDestination,
            BottomBarImageItem(
                R.drawable.outlined_account_icon, R.drawable.filled_account_icon
            ),
            stringResource(R.string.account)
        )
    )

    BottomAppBar(
        navController = navController,
        items = items,
        currentRoute
    )
}