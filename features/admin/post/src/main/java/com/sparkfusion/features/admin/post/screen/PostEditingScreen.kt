package com.sparkfusion.features.admin.post.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.admin.post.navigator.IPostEditingNavigator

@Composable
fun PostEditingScreen(
    navigator: IPostEditingNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Admin Post Editing")
    }
}