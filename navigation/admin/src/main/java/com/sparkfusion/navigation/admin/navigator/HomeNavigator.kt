package com.sparkfusion.navigation.admin.navigator

import com.sparkfusion.features.admin.home.navigator.IHomeNavigator
import com.sparkfusion.features.admin.notifications.destination.AdminNotificationsDestination
import com.sparkfusion.navigation.admincoreport.navigator.IFeaturesNavigator

class HomeNavigator(private val navigator: IFeaturesNavigator): IHomeNavigator {

    override fun navigateToNotificationsScreen() {
        navigator.navigateTo(AdminNotificationsDestination)
    }
}