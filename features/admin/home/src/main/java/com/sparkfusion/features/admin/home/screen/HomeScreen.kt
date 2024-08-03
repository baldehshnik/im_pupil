package com.sparkfusion.features.admin.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.admin.home.navigator.IHomeNavigator

@Composable
fun HomeScreen(
    navigator: IHomeNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Admin Home Screen")
        Button(onClick = { navigator.navigateToNotificationsScreen() }) {
            Text(text = "Notifications")
        }
    }
}