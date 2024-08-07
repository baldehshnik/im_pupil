package com.sparkfusion.navigation.common.navigator

import com.sparkfusion.features.common.sign_in.destination.SignInDestination
import com.sparkfusion.features.common.welcome.navigator.IWelcomeNavigator
import com.sparkfusion.navigation.core.navigator.INavigator

class WelcomeNavigator(private val navigator: INavigator) : IWelcomeNavigator {

    override fun navigateToSignInScreen() {
        navigator.navigateTo(SignInDestination)
    }
}