package com.sparkfusion.features.common.sign_in.navigator

interface ISignInNavigator {

    fun navigateToPasswordRecoveryScreen()

    fun navigateToAdminRegistrationScreen()
    fun navigateToAdminHomeScreen()

    fun navigateToPupilRegistrationScreen()
    fun navigateToPupilHomeScreen()

    fun popBackStack()
}