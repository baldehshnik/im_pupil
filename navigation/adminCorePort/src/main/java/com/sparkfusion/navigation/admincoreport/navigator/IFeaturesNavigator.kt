package com.sparkfusion.navigation.admincoreport.navigator

import com.sparkfusion.navigation.core.Destination

interface IFeaturesNavigator {

    fun navigateTo(destination: Destination)

    fun popBackStack()
}