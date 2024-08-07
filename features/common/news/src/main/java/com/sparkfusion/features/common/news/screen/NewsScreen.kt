package com.sparkfusion.features.common.news.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.common.news.navigator.INewsNavigator

@Composable
fun NewsScreen(
    navigator: INewsNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "News Screen")
    }
}