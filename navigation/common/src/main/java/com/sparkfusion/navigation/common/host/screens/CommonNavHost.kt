package com.sparkfusion.navigation.common.host.screens

import android.graphics.Bitmap
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.sparkfusion.core.image_crop.common.IMAGE_CROP_KEY
import com.sparkfusion.core.image_crop.screen.ImageCropScreen
import com.sparkfusion.core.image_crop.type.ImageCropType
import com.sparkfusion.features.common.about.screen.AboutApplicationScreen
import com.sparkfusion.features.common.filters.screen.FiltersScreen
import com.sparkfusion.features.common.news.screen.NewsScreen
import com.sparkfusion.features.common.password_recovery.destination.PasswordRecoveryEmailEnterDestination
import com.sparkfusion.features.common.sign_in.destination.SignInDestination
import com.sparkfusion.features.common.sign_in.screen.SignInScreen
import com.sparkfusion.features.common.welcome.screen.WelcomeScreen
import com.sparkfusion.navigation.common.host.pass_recovery.PASSWORD_RECOVERY_ROUTE
import com.sparkfusion.navigation.common.host.pass_recovery.passwordRecoveryNavHost
import com.sparkfusion.navigation.common.navigator.AboutApplicationNavigator
import com.sparkfusion.navigation.common.navigator.CommonNavigator
import com.sparkfusion.navigation.common.navigator.FiltersNavigator
import com.sparkfusion.navigation.common.navigator.NewsNavigator
import com.sparkfusion.navigation.common.navigator.SignInNavigator
import com.sparkfusion.navigation.common.navigator.WelcomeNavigator
import com.sparkfusion.navigation.common.navigator.crop.ImageCropNavigator
import com.sparkfusion.navigation.commoncoreport.destination.AboutApplicationDestination
import com.sparkfusion.navigation.commoncoreport.destination.CircleImageCropDestination
import com.sparkfusion.navigation.commoncoreport.destination.FiltersDestination
import com.sparkfusion.navigation.commoncoreport.destination.NewsDestination
import com.sparkfusion.navigation.commoncoreport.destination.WelcomeScreenDestination
import com.sparkfusion.navigation.core.keys.NEWS_ID_KEY

fun NavGraphBuilder.commonNavHost(navController: NavHostController) {
    val commonNavigator = CommonNavigator(navController)

    val welcomeNavigator = WelcomeNavigator(commonNavigator)
    composable(WelcomeScreenDestination.route) { WelcomeScreen(welcomeNavigator) }

    val singInNavigator = SignInNavigator(commonNavigator)
    composable(SignInDestination.route) { SignInScreen(singInNavigator) }

    val filtersNavigator = FiltersNavigator(commonNavigator)
    composable(FiltersDestination.route) { FiltersScreen(filtersNavigator) }

    val newsNavigator = NewsNavigator(commonNavigator)
    composable(NewsDestination.route) {
        val newsId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(NEWS_ID_KEY)
        newsId?.let {
            NewsScreen(
                navigator = newsNavigator,
                newsId = it
            )
        }
    }

    val aboutApplicationNavigator = AboutApplicationNavigator(commonNavigator)
    composable(AboutApplicationDestination.route) { AboutApplicationScreen(aboutApplicationNavigator) }

    val imageCropNavigator = ImageCropNavigator(commonNavigator)
    composable(CircleImageCropDestination.route) {
        val bitmap: Bitmap? =
            navController.previousBackStackEntry?.savedStateHandle?.get<Bitmap>(IMAGE_CROP_KEY)
        if (bitmap != null) {
            ImageCropScreen(imageCropNavigator, ImageCropType.CircleCrop, bitmap)
        }
    }

    navigation(PasswordRecoveryEmailEnterDestination.route, PASSWORD_RECOVERY_ROUTE) {
        passwordRecoveryNavHost(commonNavigator)
    }
}