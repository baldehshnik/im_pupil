package com.sparkfusion.navigation.admin.navigator.confirmation

import com.sparkfusion.features.admin.confirmation.navigator.IAdminConfirmationNavigator
import com.sparkfusion.navigation.core.navigator.INavigator

class AdminConfirmationNavigator(private val navigator: INavigator) : IAdminConfirmationNavigator {

    override fun popBackStack() {
        navigator.popBackStack()
    }
}