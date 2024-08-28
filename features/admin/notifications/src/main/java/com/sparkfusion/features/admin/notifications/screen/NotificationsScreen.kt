package com.sparkfusion.features.admin.notifications.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.admin.notifications.R
import com.sparkfusion.features.admin.notifications.navigator.INotificationsNavigator
import com.sparkfusion.features.admin.notifications.screen.component.NoNotificationsComponent
import com.sparkfusion.features.admin.notifications.screen.component.NotificationItemComponent

@Composable
fun NotificationsScreen(
    navigator: INotificationsNavigator,
    modifier: Modifier = Modifier
) {
    val count by remember { mutableIntStateOf(10) }
    val isDataLoadingAnimationCompleted = remember { mutableStateOf(false) }

    val isDarkModeEnabled = isSystemInDarkTheme()

    LazyColumn(
        modifier = modifier.padding(start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TopBar(
                title = stringResource(R.string.notifications),
                onBackClick = navigator::popBackStack
            )

            if (count <= 0) NoNotificationsComponent(
                onReloadClick = { }
            )
        }
        items(count) {
            NotificationItemComponent(
                modifier = Modifier.padding(vertical = 8.dp),
                title = "Some title of the notificationhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh",
                received = "Received 19.10.2024 at 15:30",
                isDarkModeEnabled = isDarkModeEnabled,
                isDataLoadingAnimationCompleted = isDataLoadingAnimationCompleted.value
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NotificationsScreenPreview() {
    NotificationsScreen(
        navigator = object : INotificationsNavigator {
            override fun popBackStack() {}
        }
    )
}