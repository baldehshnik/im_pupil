package com.sparkfusion.services.admin.session.destination

import com.sparkfusion.navigation.core.Destination

object SessionTestAddingDestination : Destination() {
    override val route: String
        get() = "session test adding destination"
}