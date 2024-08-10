package com.sparkfusion.navigation.common.navigator

import androidx.navigation.NavHostController
import com.sparkfusion.navigation.core.Destination
import com.sparkfusion.navigation.core.navigator.INavigator

class CommonNavigator(private val navController: NavHostController) : INavigator {

    override fun navigateTo(destination: Destination) {
        navController.navigate(destination.route)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }
}