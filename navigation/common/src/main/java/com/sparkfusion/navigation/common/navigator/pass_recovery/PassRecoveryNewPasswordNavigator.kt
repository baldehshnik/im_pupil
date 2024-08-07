package com.sparkfusion.navigation.common.navigator.pass_recovery

import com.sparkfusion.features.common.password_recovery.navigator.IPasswordRecoveryNewPasswordNavigator
import com.sparkfusion.features.common.sign_in.destination.SignInDestination
import com.sparkfusion.navigation.core.navigator.INavigator

class PassRecoveryNewPasswordNavigator(
    private val navigator: INavigator
) : IPasswordRecoveryNewPasswordNavigator {

    override fun navigateToSignInScreen() {
        navigator.navigateTo(SignInDestination)
    }
}