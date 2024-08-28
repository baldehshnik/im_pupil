package com.sparkfusion.features.admin.account.navigator

import android.graphics.Bitmap

interface IAccountNavigator {

    fun navigateToAdminDetailsScreen()
    fun navigateToPostViewingScreen()
    fun <T> navigateToCircleImageCropScreen(key: String, value: T)
    fun getCroppedImageBitmap(): Bitmap?
}