package com.sparkfusion.navigation.common.navigator.crop

import android.graphics.Bitmap
import com.sparkfusion.core.image_crop.common.CROPPED_KEY
import com.sparkfusion.core.image_crop.navigator.IImageCropNavigator
import com.sparkfusion.navigation.core.navigator.INavigator

class ImageCropNavigator(private val navigator: INavigator) : IImageCropNavigator {

    override fun popBackStack() {
        navigator.popBackStack()
    }

    override fun navigateToPreviousScreen(bitmap: Bitmap) {
        navigator.popBackStackToWithParam(CROPPED_KEY, bitmap)
    }
}