package com.sparkfusion.navigation.admin.navigator

import androidx.navigation.NavHostController
import com.sparkfusion.navigation.admincoreport.navigator.IFeaturesNavigator
import com.sparkfusion.navigation.core.Destination

class FeaturesNavigator(private val navController: NavHostController): IFeaturesNavigator {

    override fun navigateTo(destination: Destination) {
        navController.navigate(destination.route)
    }

    override fun popBackStack() {

    }
}