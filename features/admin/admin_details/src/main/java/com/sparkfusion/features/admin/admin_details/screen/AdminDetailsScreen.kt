package com.sparkfusion.features.admin.admin_details.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.admin.admin_details.navigator.IAdminDetailsNavigator

@Composable
fun AdminDetailsScreen(
    navigator: IAdminDetailsNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "admin details screen")
    }
}