package com.sparkfusion.features.common.about.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.common.about.navigator.IAboutApplicationNavigator

@Composable
fun AboutApplicationScreen(
    navigator: IAboutApplicationNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "About Application Screen")
    }
}