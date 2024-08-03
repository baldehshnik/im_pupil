package com.sparkfusion.navigation.admin.navigator

import com.sparkfusion.features.admin.account.navigator.IAccountNavigator
import com.sparkfusion.features.admin.admin_details.destination.AdminDetailsDestination
import com.sparkfusion.features.admin.post.destination.AdminPostViewingDestination
import com.sparkfusion.navigation.admincoreport.navigator.IFeaturesNavigator

class AccountNavigator(private val navigator: IFeaturesNavigator) : IAccountNavigator {

    override fun navigateToAdminDetailsScreen() {
        navigator.navigateTo(AdminDetailsDestination)
    }

    override fun navigateToPostViewingScreen() {
        navigator.navigateTo(AdminPostViewingDestination)
    }
}