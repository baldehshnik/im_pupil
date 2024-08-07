package com.sparkfusion.navigation.common.navigator

import com.sparkfusion.features.common.password_recovery.destination.PasswordRecoveryEmailEnterDestination
import com.sparkfusion.features.common.sign_in.navigator.ISignInNavigator
import com.sparkfusion.navigation.admincoreport.destination.AdminSignUpDestination
import com.sparkfusion.navigation.core.navigator.INavigator

class SignInNavigator(private val navigator: INavigator): ISignInNavigator {

    override fun navigateToPasswordRecoveryScreen() {
        navigator.navigateTo(PasswordRecoveryEmailEnterDestination)
    }

    override fun navigateToAdminRegistrationScreen() {
        navigator.navigateTo(AdminSignUpDestination)
    }
}