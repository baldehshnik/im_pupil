package com.sparkfusion.navigation.admin.navigator

import com.sparkfusion.features.admin.account.navigator.IAccountNavigator
import com.sparkfusion.features.admin.admin_details.destination.AdminDetailsDestination
import com.sparkfusion.features.admin.post.destination.AdminPostViewingDestination
import com.sparkfusion.navigation.core.navigator.INavigator

class AccountNavigator(private val navigator: INavigator) : IAccountNavigator {

    override fun navigateToAdminDetailsScreen() {
        navigator.navigateTo(AdminDetailsDestination)
    }

    override fun navigateToPostViewingScreen() {
        navigator.navigateTo(AdminPostViewingDestination)
    }
}