package com.sparkfusion.features.admin.post.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.admin.post.navigator.IPostAddingNavigator

@Composable
fun PostAddingScreen(
    navigator: IPostAddingNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Admin Post Adding Screen")
        Button(onClick = navigator::navigateToPostPreviewScreen) {
            Text(text = "Preview")
        }
    }
}