package com.sparkfusion.navigation.common.navigator

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.sparkfusion.navigation.core.Destination
import com.sparkfusion.navigation.core.navigator.INavigator

class CommonNavigator(private val navController: NavHostController) : INavigator {

    override val currentBackStackEntry: NavBackStackEntry?
        get() = navController.currentBackStackEntry

    override fun navigateTo(destination: Destination) {
        navController.navigate(destination.route)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun popBackStackInclusive(destination: Destination) {
        navController.popBackStack(destination.route, true)
    }

    override fun <T> navigateToWithParam(destination: Destination, key: String, value: T) {
        navController.currentBackStackEntry?.savedStateHandle?.set(key, value)
        navigateTo(destination)
    }

    override fun <T> popBackStackToWithParam(key: String, value: T) {
        navController.previousBackStackEntry?.savedStateHandle?.set(key, value)
        navController.popBackStack()
    }
}