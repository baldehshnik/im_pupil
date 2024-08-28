package com.sparkfusion.navigation.common.navigator.crop

import android.graphics.Bitmap
import com.sparkfusion.core.image_crop.navigator.IImageCropNavigator
import com.sparkfusion.core.resource.bundle.CROPPED_IMAGE_KEY
import com.sparkfusion.navigation.core.navigator.INavigator

class ImageCropNavigator(private val navigator: INavigator) : IImageCropNavigator {

    override fun popBackStack() {
        navigator.popBackStack()
    }

    override fun navigateToPreviousScreen(bitmap: Bitmap) {
        navigator.popBackStackToWithParam(CROPPED_IMAGE_KEY, bitmap)
    }
}