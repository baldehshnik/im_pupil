package com.sparkfusion.core.resource.screen

import androidx.compose.runtime.Composable
import com.sparkfusion.core.resource.theme.ImPupilTheme

@Composable
fun ImPupilScreen(screenContent: @Composable () -> Unit) {
    ImPupilTheme(content = screenContent)
}