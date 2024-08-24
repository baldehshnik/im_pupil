package com.sparkfusion.features.admin.services.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.admin.services.navigator.IServicesNavigator

@Composable
fun ServicesScreen(
    navigator: IServicesNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "services screen")
        Button(onClick = navigator::navigateToNewsScreen) {
            Text(text = "News Screen")
        }
        Button(onClick = navigator::navigateToAboutApplicationScreen) {
            Text(text = "About Application Screen")
        }
    }
}