package com.sparkfusion.navigation.core.bottombar

import androidx.compose.ui.graphics.vector.ImageVector
import com.sparkfusion.navigation.core.Destination

data class BottomBarItem(
    val destination: Destination,
    val icon: ImageVector,
    val route: String
)