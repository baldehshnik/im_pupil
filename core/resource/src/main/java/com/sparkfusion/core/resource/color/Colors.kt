package com.sparkfusion.core.resource.color

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun fieldBorderColor(
    isFocused: Boolean,
    isDarkTheme: Boolean
): Color {
    return when {
        isFocused -> MaterialTheme.colorScheme.primary
        isDarkTheme -> TextFieldDefaults.colors().unfocusedContainerColor
        else -> Color.Black
    }
}

@Composable
fun descriptionColor(): Color {
    return when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.outline
        else -> Color.DarkGray
    }
}

@Composable
fun blackButtonContainerColor(): Color {
    return when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.primary
        else -> Color.Black
    }
}

@Composable
fun blackButtonTextColor(): Color {
    return when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.onPrimary
        else -> Color.White
    }
}




























