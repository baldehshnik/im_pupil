package com.sparkfusion.navigation.admin.navigator.post

import android.graphics.Bitmap
import com.sparkfusion.core.image_crop.common.IMAGE_CROP_KEY
import com.sparkfusion.features.admin.post.navigator.IPostEditingNavigator
import com.sparkfusion.navigation.commoncoreport.destination.RectangleImageCropDestination
import com.sparkfusion.navigation.core.navigator.INavigator

class PostEditingNavigator(private val navigator: INavigator) : IPostEditingNavigator {

    override fun popBackStack() {
        navigator.popBackStack()
    }

    override fun navigateToCircleImageCropScreen(bitmap: Bitmap) {
        navigator.navigateToWithParam(RectangleImageCropDestination, IMAGE_CROP_KEY, bitmap)
    }
}

























