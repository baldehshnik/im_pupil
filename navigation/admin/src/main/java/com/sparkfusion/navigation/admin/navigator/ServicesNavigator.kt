package com.sparkfusion.navigation.admin.navigator

import com.sparkfusion.features.admin.services.navigator.IServicesNavigator
import com.sparkfusion.navigation.commoncoreport.destination.AboutApplicationDestination
import com.sparkfusion.navigation.commoncoreport.destination.NewsDestination
import com.sparkfusion.navigation.core.navigator.INavigator

class ServicesNavigator(private val navigator: INavigator) : IServicesNavigator {

    override fun navigateToNewsScreen() {
        navigator.navigateTo(NewsDestination)
    }

    override fun navigateToAboutApplicationScreen() {
        navigator.navigateTo(AboutApplicationDestination)
    }
}