package com.sparkfusion.navigation.common.utils

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp

@Composable
fun ChangeNavigationBarColor(
    showBottomBar: Boolean
) {
    val scheme = MaterialTheme.colorScheme
    val navigationBarColor = remember(showBottomBar) {
        if (showBottomBar) {
            scheme.surfaceColorAtElevation(3.dp).toArgb()
        } else {
            scheme.surface.toArgb()
        }
    }

    val view = LocalView.current
    val window = remember { (view.context as Activity).window }
    DisposableEffect(navigationBarColor) {
        window.navigationBarColor = navigationBarColor
        onDispose {}
    }
}