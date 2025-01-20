package com.sparkfusion.navigation.core.navigator

import androidx.navigation.NavBackStackEntry
import com.sparkfusion.navigation.core.Destination

interface INavigator {

    val currentBackStackEntry: NavBackStackEntry?
    val previousBackStackEntry: NavBackStackEntry?

    fun navigateTo(destination: Destination)

    fun popBackStack()

    fun popBackStackInclusive(destination: Destination)

    fun <T> navigateToWithParam(destination: Destination, key: String, value: T)

    fun <T> popBackStackToWithParam(key: String, value: T)
}