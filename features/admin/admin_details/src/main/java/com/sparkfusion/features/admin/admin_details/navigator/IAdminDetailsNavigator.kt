package com.sparkfusion.features.admin.admin_details.navigator

import androidx.navigation.NavBackStackEntry

interface IAdminDetailsNavigator {

    val previousNavBackStackEntry: NavBackStackEntry?

    fun popBackStack()
}