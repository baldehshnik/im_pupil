package com.sparkfusion.navigation.common.destination

import com.sparkfusion.navigation.core.Destination

object WelcomeScreenDestination : Destination() {
    override val route: String
        get() = "welcome"
}