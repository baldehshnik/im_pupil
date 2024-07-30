package com.sparkfusion.navigation.admin.destination

import com.sparkfusion.navigation.core.Destination

object AdminHomeDestination : Destination() {
    override val route: String
        get() = "admin_home"
}