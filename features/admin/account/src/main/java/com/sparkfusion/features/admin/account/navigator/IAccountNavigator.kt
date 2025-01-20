package com.sparkfusion.features.admin.account.navigator

import android.graphics.Bitmap

interface IAccountNavigator {

    fun navigateToAdminDetailsScreen(id: Int, accessMode: Int)
    fun navigateToPostViewingScreen()
    fun <T> navigateToCircleImageCropScreen(key: String, value: T)
    fun getCroppedImageBitmap(): Bitmap?
}