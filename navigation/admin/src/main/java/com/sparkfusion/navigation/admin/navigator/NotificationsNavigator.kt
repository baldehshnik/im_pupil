package com.sparkfusion.navigation.admin.navigator

import com.sparkfusion.features.admin.notifications.navigator.INotificationsNavigator
import com.sparkfusion.navigation.core.navigator.INavigator

class NotificationsNavigator(private val navigator: INavigator): INotificationsNavigator {

    override fun popBackStack() {
        navigator.popBackStack()
    }
}