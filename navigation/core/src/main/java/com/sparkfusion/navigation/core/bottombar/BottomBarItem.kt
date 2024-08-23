package com.sparkfusion.navigation.core.bottombar

import com.sparkfusion.navigation.core.Destination

data class BottomBarItem(
    val destination: Destination,
    val icons: BottomBarImageItem,
    val title: String
)