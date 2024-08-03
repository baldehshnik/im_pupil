package com.sparkfusion.features.admin.notifications.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.admin.notifications.navigator.INotificationsNavigator

@Composable
fun NotificationsScreen(
    navigator: INotificationsNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "notifications screen")
    }
}