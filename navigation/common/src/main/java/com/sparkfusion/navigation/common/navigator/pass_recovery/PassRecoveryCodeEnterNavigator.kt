package com.sparkfusion.navigation.common.navigator.pass_recovery

import com.sparkfusion.features.common.password_recovery.destination.PasswordRecoveryNewPasswordDestination
import com.sparkfusion.features.common.password_recovery.navigator.IPasswordRecoveryCodeEnterNavigator
import com.sparkfusion.navigation.core.navigator.INavigator

class PassRecoveryCodeEnterNavigator(
    private val navigator: INavigator
): IPasswordRecoveryCodeEnterNavigator {

    override fun navigateToNewPasswordScreen() {
        navigator.navigateTo(PasswordRecoveryNewPasswordDestination)
    }
}