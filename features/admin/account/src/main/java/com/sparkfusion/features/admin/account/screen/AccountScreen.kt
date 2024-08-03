package com.sparkfusion.features.admin.account.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.admin.account.navigator.IAccountNavigator

@Composable
fun AccountScreen(
    navigator: IAccountNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Account Screen")
        Button(onClick = navigator::navigateToAdminDetailsScreen) {
            Text(text = "Admin Details")
        }
        Button(onClick = navigator::navigateToPostViewingScreen) {
            Text(text = "Admin Post Viewing")
        }
    }
}