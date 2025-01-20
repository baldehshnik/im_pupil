package com.sparkfusion.navigation.admin.navigator

import android.graphics.Bitmap
import com.sparkfusion.core.resource.bundle.CROPPED_IMAGE_KEY
import com.sparkfusion.features.admin.account.navigator.IAccountNavigator
import com.sparkfusion.features.admin.admin_details.destination.AdminDetailsDestination
import com.sparkfusion.features.admin.post.destination.AdminPostViewingDestination
import com.sparkfusion.navigation.commoncoreport.destination.CircleImageCropDestination
import com.sparkfusion.navigation.core.keys.ADMIN_ACCESS_KEY
import com.sparkfusion.navigation.core.keys.ADMIN_ID_KEY
import com.sparkfusion.navigation.core.navigator.INavigator

class AccountNavigator(private val navigator: INavigator) : IAccountNavigator {

    override fun navigateToAdminDetailsScreen(id: Int, accessMode: Int) {
        navigator.currentBackStackEntry?.savedStateHandle?.set(ADMIN_ID_KEY, id)
        navigator.currentBackStackEntry?.savedStateHandle?.set(ADMIN_ACCESS_KEY, accessMode)
        navigator.navigateTo(AdminDetailsDestination)
    }

    override fun navigateToPostViewingScreen() {
        navigator.navigateTo(AdminPostViewingDestination)
    }

    override fun <T> navigateToCircleImageCropScreen(key: String, value: T) {
        navigator.navigateToWithParam(CircleImageCropDestination, key, value)
    }

    override fun getCroppedImageBitmap(): Bitmap? {
        return navigator.currentBackStackEntry?.savedStateHandle?.get<Bitmap>(CROPPED_IMAGE_KEY)
    }
}