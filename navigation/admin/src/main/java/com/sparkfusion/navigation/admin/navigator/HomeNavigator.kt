package com.sparkfusion.navigation.admin.navigator

import com.sparkfusion.features.admin.home.navigator.IHomeNavigator
import com.sparkfusion.features.admin.notifications.destination.AdminNotificationsDestination
import com.sparkfusion.features.admin.post.destination.AdminPostAddingDestination
import com.sparkfusion.features.admin.post.destination.AdminPostViewingDestination
import com.sparkfusion.navigation.commoncoreport.destination.FiltersDestination
import com.sparkfusion.navigation.core.keys.EVENT_ID_KEY
import com.sparkfusion.navigation.core.navigator.INavigator

class HomeNavigator(private val navigator: INavigator): IHomeNavigator {

    override fun navigateToNotificationsScreen() {
        navigator.navigateTo(AdminNotificationsDestination)
    }

    override fun navigateToPostAddingScreen() {
        navigator.navigateTo(AdminPostAddingDestination)
    }

    override fun navigateToPostViewingScreen(id: Int) {
        navigator.currentBackStackEntry?.savedStateHandle?.set(EVENT_ID_KEY, id)
        navigator.navigateTo(AdminPostViewingDestination)
    }

    override fun navigateToFiltersScreen() {
        navigator.navigateTo(FiltersDestination)
    }
}