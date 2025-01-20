package com.sparkfusion.navigation.admin.host.baritems

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.core.image_crop.common.CROPPED_KEY
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

fun NavGraphBuilder.adminBottomBarDestinations(
    navigator: INavigator
) {
    composable(
        AdminAccountDestination.route
    ) {
        val accountNavigator = remember { AccountNavigator(navigator) }
        AccountScreen(
            navigator = accountNavigator,
            getCroppedImageBitmap = {
                navigator.currentBackStackEntry?.savedStateHandle?.get(CROPPED_KEY)
            }
        )
    }
    composable(
        AdminHomeDestination.route
    ) {
        val homeNavigator = remember { HomeNavigator(navigator) }
        HomeScreen(homeNavigator)
    }
    composable(
        AdminServicesDestination.route
    ) {
        val servicesNavigator = remember { ServicesNavigator(navigator) }
        ServicesScreen(servicesNavigator)
    }
}