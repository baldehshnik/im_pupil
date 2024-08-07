package com.sparkfusion.features.common.password_recovery.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.common.password_recovery.navigator.IPasswordRecoveryCodeEnterNavigator

@Composable
fun PassRecoveryCodeEnterScreen(
    navigator: IPasswordRecoveryCodeEnterNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Pass Recovery Code Enter Screen")
        Button(onClick = navigator::navigateToNewPasswordScreen) {
            Text(text = "Step 3")
        }
    }
}