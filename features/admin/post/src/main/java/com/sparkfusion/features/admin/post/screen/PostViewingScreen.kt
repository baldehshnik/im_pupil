package com.sparkfusion.features.admin.post.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.admin.post.navigator.IPostViewingNavigator

@Composable
fun PostViewingScreen(
    navigator: IPostViewingNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Admin Post Viewing Screen")
        Button(onClick = navigator::navigateToPostEditingScreen) {
            Text(text = "Editing")
        }
    }
}