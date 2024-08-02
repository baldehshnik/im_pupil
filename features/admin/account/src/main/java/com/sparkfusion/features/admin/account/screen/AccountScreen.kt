package com.sparkfusion.features.admin.account.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.admin.account.navigator.IAccountNavigator

@Composable
fun AccountScreen(
    navigator: IAccountNavigator,
    modifier: Modifier = Modifier
) {
    Text(text = "Account Screen")
}