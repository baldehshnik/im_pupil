package com.sparkfusion.navigation.common.host.pass_recovery

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.features.common.password_recovery.destination.PasswordRecoveryCodeEnterDestination
import com.sparkfusion.features.common.password_recovery.destination.PasswordRecoveryEmailEnterDestination
import com.sparkfusion.features.common.password_recovery.destination.PasswordRecoveryNewPasswordDestination
import com.sparkfusion.features.common.password_recovery.screen.PassRecoveryCodeEnterScreen
import com.sparkfusion.features.common.password_recovery.screen.PassRecoveryEmailEnterScreen
import com.sparkfusion.features.common.password_recovery.screen.PassRecoveryNewPasswordScreen
import com.sparkfusion.navigation.common.navigator.pass_recovery.PassRecoveryCodeEnterNavigator
import com.sparkfusion.navigation.common.navigator.pass_recovery.PassRecoveryEmailEnterNavigator
import com.sparkfusion.navigation.common.navigator.pass_recovery.PassRecoveryNewPasswordNavigator
import com.sparkfusion.navigation.core.navigator.INavigator

fun NavGraphBuilder.passwordRecoveryNavHost(navigator: INavigator) {
    composable(PasswordRecoveryEmailEnterDestination.route) {
        val passRecoveryEmailEnterNavigator = remember { PassRecoveryEmailEnterNavigator(navigator) }
        PassRecoveryEmailEnterScreen(passRecoveryEmailEnterNavigator)
    }
    composable(PasswordRecoveryCodeEnterDestination.route) {
        val passRecoveryCodeEnterNavigator = remember { PassRecoveryCodeEnterNavigator(navigator) }
        PassRecoveryCodeEnterScreen(passRecoveryCodeEnterNavigator)
    }
    composable(PasswordRecoveryNewPasswordDestination.route) {
        val passRecoveryNewPasswordNavigator = remember { PassRecoveryNewPasswordNavigator(navigator) }
        PassRecoveryNewPasswordScreen(passRecoveryNewPasswordNavigator)
    }
}