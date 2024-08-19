package com.sparkfusion.navigation.admin.navigator

import androidx.navigation.NavHostController
import com.sparkfusion.navigation.core.navigator.INavigator
import com.sparkfusion.navigation.core.Destination

class Navigator(private val navController: NavHostController): INavigator {

    override fun navigateTo(destination: Destination) {
        navController.navigate(destination.route)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun popBackStackInclusive(destination: Destination) {
        navController.popBackStack(destination.route, true)
    }
}