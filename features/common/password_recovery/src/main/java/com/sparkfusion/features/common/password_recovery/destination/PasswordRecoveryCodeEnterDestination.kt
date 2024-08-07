package com.sparkfusion.features.common.password_recovery.destination

import com.sparkfusion.navigation.core.Destination

object PasswordRecoveryCodeEnterDestination : Destination() {
    override val route: String
        get() = "password recovery code enter"
}