package com.sparkfusion.navigation.core.navigator

import com.sparkfusion.navigation.core.Destination

interface INavigator {

    fun navigateTo(destination: Destination)

    fun popBackStack()

    fun popBackStackInclusive(destination: Destination)
}