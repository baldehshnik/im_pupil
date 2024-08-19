package com.sparkfusion.navigation.admin.navigator

import com.sparkfusion.features.admin.sign_up.navigator.ISignUpNavigator
import com.sparkfusion.navigation.admincoreport.destination.AdminSignUpDestination
import com.sparkfusion.navigation.core.navigator.INavigator

class SignUpNavigator(private val navigator: INavigator) : ISignUpNavigator {

    override fun popBackStack() {
        navigator.popBackStackInclusive(AdminSignUpDestination)
    }

    override fun navigateToSignInScreen() {
        popBackStack()
    }
}