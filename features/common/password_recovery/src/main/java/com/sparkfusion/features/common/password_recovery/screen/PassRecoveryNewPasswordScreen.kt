package com.sparkfusion.features.common.password_recovery.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.common.password_recovery.navigator.IPasswordRecoveryNewPasswordNavigator

@Composable
fun PassRecoveryNewPasswordScreen(
    navigator: IPasswordRecoveryNewPasswordNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Password Recovery New Password Screen")
        Button(onClick = navigator::navigateToSignInScreen) {
            Text(text = "Sign In Screen")
        }
    }
}