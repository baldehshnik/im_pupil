package com.sparkfusion.navigation.core.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material3.BottomAppBar as ComposeAppBar

@Composable
fun BottomAppBar(
    navController: NavHostController,
    items: List<BottomBarItem>
) {
    ComposeAppBar {
        items.forEach { item ->
            IconButton(onClick = {
                navController.navigate(item.destination.route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            }) {
                Icon(imageVector = item.icon, contentDescription = item.route)
            }
        }
    }
}