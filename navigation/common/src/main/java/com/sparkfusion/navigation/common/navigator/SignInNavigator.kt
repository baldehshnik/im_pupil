package com.sparkfusion.navigation.common.navigator

import com.sparkfusion.features.common.password_recovery.destination.PasswordRecoveryEmailEnterDestination
import com.sparkfusion.features.common.sign_in.navigator.ISignInNavigator
import com.sparkfusion.navigation.admincoreport.destination.AdminHomeDestination
import com.sparkfusion.navigation.admincoreport.destination.AdminSignUpDestination
import com.sparkfusion.navigation.commoncoreport.destination.WelcomeScreenDestination
import com.sparkfusion.navigation.core.navigator.INavigator
import com.sparkfusion.navigation.pupilcoreport.PupilHomeDestination
import com.sparkfusion.navigation.pupilcoreport.PupilSignUpDestination

class SignInNavigator(private val navigator: INavigator): ISignInNavigator {

    override fun navigateToPasswordRecoveryScreen() {
        navigator.navigateTo(PasswordRecoveryEmailEnterDestination)
    }

    override fun navigateToAdminRegistrationScreen() {
        navigator.navigateTo(AdminSignUpDestination)
    }

    override fun navigateToAdminHomeScreen() {
        navigator.popBackStackInclusive(WelcomeScreenDestination)
        navigator.navigateTo(AdminHomeDestination)
    }

    override fun navigateToPupilRegistrationScreen() {
        navigator.navigateTo(PupilSignUpDestination)
    }

    override fun navigateToPupilHomeScreen() {
        navigator.popBackStackInclusive(WelcomeScreenDestination)
        navigator.navigateTo(PupilHomeDestination)
    }

    override fun popBackStack() {
        navigator.popBackStack()
    }
}



















