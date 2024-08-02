package com.sparkfusion.navigation.admin.navigator

import com.sparkfusion.features.admin.account.destination.AdminAccountDestination
import com.sparkfusion.features.admin.home.navigator.IHomeNavigator
import com.sparkfusion.navigation.admincoreport.navigator.IFeaturesNavigator

class HomeNavigator(private val featuresNavigator: IFeaturesNavigator): IHomeNavigator {

    override fun navigateToAccount() {
        featuresNavigator.navigateTo(AdminAccountDestination)
    }
}