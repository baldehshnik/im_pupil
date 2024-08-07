package com.sparkfusion.features.common.password_recovery.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.common.password_recovery.navigator.IPasswordRecoveryEmailEnterNavigator

@Composable
fun PassRecoveryEmailEnterScreen(
    navigator: IPasswordRecoveryEmailEnterNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Pass Recovery Email Enter Screen")
        Button(onClick = navigator::navigateToCodeEnterScreen) {
            Text(text = "Step 2")
        }
    }
}