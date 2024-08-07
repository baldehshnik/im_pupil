package com.sparkfusion.navigation.commoncoreport.destination

import com.sparkfusion.navigation.core.Destination

object NewsDestination : Destination() {
    override val route: String
        get() = "news"
}