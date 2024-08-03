package com.sparkfusion.navigation.admin.bottombar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun ScreenWithBottomAppBar(
    navController: NavHostController,
    bottomNavDestinations: List<String>,
    content: @Composable (PaddingValues) -> Unit
) {
    val currentDestination by navController.currentBackStackEntryAsState()
    val showBottomAppBar = currentDestination?.destination?.route in bottomNavDestinations

    Scaffold(
        bottomBar = {
            if (showBottomAppBar) AdminBottomAppBar(navController)
        },
        content = content
    )
}