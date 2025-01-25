package com.sparkfusion.navigation.admin.navigator

import com.sparkfusion.features.admin.confirmation.destination.AdminConfirmationDestination
import com.sparkfusion.features.admin.confirmation.destination.PupilConfirmationDestination
import com.sparkfusion.features.admin.notifications.navigator.INotificationsNavigator
import com.sparkfusion.navigation.core.navigator.INavigator

class NotificationsNavigator(private val navigator: INavigator): INotificationsNavigator {

    override fun popBackStack() {
        navigator.popBackStack()
    }

    override fun navigateToAdminConfirmation() {
        navigator.navigateTo(AdminConfirmationDestination)
    }

    override fun navigateToPupilConfirmation() {
        navigator.navigateTo(PupilConfirmationDestination)
    }
}