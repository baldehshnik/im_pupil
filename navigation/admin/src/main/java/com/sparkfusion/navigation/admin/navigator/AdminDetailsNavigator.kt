package com.sparkfusion.navigation.admin.navigator

import com.sparkfusion.features.admin.admin_details.navigator.IAdminDetailsNavigator
import com.sparkfusion.navigation.core.navigator.INavigator

class AdminDetailsNavigator(private val navigator: INavigator) : IAdminDetailsNavigator {

    override fun popBackStack() {
        navigator.popBackStack()
    }
}