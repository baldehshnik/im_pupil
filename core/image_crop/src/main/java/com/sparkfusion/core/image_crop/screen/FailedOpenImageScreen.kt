package com.sparkfusion.core.image_crop.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.core.image_crop.navigator.IImageCropNavigator
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun FailedOpenImageScreen(
    navController: IImageCropNavigator,
    modifier: Modifier = Modifier
) {
    SFProRoundedText(content = "Failed open image screen")
}