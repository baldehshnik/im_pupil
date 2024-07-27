package com.sparkfusion.features.common.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Button(onClick = { }) {
            Text(text = "Administrator")
        }

        Button(onClick = { }) {
            Text(text = "Pupil")
        }
    }
}