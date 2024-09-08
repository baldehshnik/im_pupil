package com.sparkfusion.navigation.core.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun BottomAppBar(
    navController: NavHostController,
    items: List<BottomBarItem>,
    currentRoute: String?
) {
    NavigationBar {
        items.forEach { item ->
            val selection = currentRoute == item.destination.route

            NavigationBarItem(
                selected = false,
                onClick = {
                    navController.navigate(item.destination.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            if (selection) item.icons.filled else item.icons.outlined
                        ),
                        contentDescription = null
                    )
                },
                label = {
                    SFProRoundedText(
                        content = item.title,
                        fontWeight = FontWeight.Medium
                    )
                }
            )
        }
    }
}