package com.sparkfusion.features.common.password_recovery.destination

import com.sparkfusion.navigation.core.Destination

object PasswordRecoveryNewPasswordDestination : Destination() {
    override val route: String
        get() = "password recovery new password"
}