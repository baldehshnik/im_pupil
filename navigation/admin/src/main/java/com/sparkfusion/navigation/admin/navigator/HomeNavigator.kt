package com.sparkfusion.navigation.admin.navigator

import com.sparkfusion.features.admin.home.navigator.IHomeNavigator
import com.sparkfusion.features.admin.notifications.destination.AdminNotificationsDestination
import com.sparkfusion.features.admin.post.destination.AdminPostAddingDestination
import com.sparkfusion.features.admin.post.destination.AdminPostViewingDestination
import com.sparkfusion.navigation.admincoreport.navigator.IFeaturesNavigator

class HomeNavigator(private val navigator: IFeaturesNavigator): IHomeNavigator {

    override fun navigateToNotificationsScreen() {
        navigator.navigateTo(AdminNotificationsDestination)
    }

    override fun navigateToPostAddingScreen() {
        navigator.navigateTo(AdminPostAddingDestination)
    }

    override fun navigateToPostViewingScreen() {
        navigator.navigateTo(AdminPostViewingDestination)
    }
}