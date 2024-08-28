package com.sparkfusion.core.image_crop.navigator

import android.graphics.Bitmap

interface IImageCropNavigator {

    fun popBackStack()
    fun navigateToPreviousScreen(bitmap: Bitmap)
}