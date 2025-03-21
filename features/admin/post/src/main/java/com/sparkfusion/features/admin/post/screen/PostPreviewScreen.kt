package com.sparkfusion.features.admin.post.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.admin.post.navigator.IPostPreviewNavigator

@Composable
fun PostPreviewScreen(
    navigator: IPostPreviewNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Admin Post Preview")
    }
}