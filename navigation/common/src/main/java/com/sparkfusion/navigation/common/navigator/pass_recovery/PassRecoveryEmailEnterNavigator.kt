package com.sparkfusion.navigation.common.navigator.pass_recovery

import com.sparkfusion.features.common.password_recovery.destination.PasswordRecoveryCodeEnterDestination
import com.sparkfusion.features.common.password_recovery.navigator.IPasswordRecoveryEmailEnterNavigator
import com.sparkfusion.navigation.core.navigator.INavigator

class PassRecoveryEmailEnterNavigator(
    private val navigator: INavigator
): IPasswordRecoveryEmailEnterNavigator {

    override fun navigateToCodeEnterScreen() {
        navigator.navigateTo(PasswordRecoveryCodeEnterDestination)
    }
}