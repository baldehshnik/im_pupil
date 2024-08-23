package com.sparkfusion.navigation.admin.host.baritems

import com.sparkfusion.features.admin.account.destination.AdminAccountDestination
import com.sparkfusion.features.admin.services.destination.AdminServicesDestination
import com.sparkfusion.navigation.admincoreport.destination.AdminHomeDestination

fun getAdminBottomBarItemRoutes(): List<String?> {
    return listOf(
        AdminHomeDestination.route,
        AdminAccountDestination.route,
        AdminServicesDestination.route
    )
}