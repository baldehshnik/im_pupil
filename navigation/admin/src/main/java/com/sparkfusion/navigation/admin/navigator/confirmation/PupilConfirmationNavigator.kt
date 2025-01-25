package com.sparkfusion.navigation.admin.navigator.confirmation

import com.sparkfusion.features.admin.confirmation.navigator.IPupilConfirmationNavigator
import com.sparkfusion.navigation.core.navigator.INavigator

class PupilConfirmationNavigator(private val navigator: INavigator) : IPupilConfirmationNavigator {

    override fun popBackStack() {
        navigator.popBackStack()
    }
}