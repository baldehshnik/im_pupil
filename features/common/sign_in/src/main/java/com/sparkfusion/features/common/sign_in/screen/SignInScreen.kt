package com.sparkfusion.features.common.sign_in.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.common.sign_in.navigator.ISignInNavigator

@Composable
fun SignInScreen(
    navigator: ISignInNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Sing In Screen")
        Button(onClick = navigator::navigateToPasswordRecoveryScreen) {
            Text(text = "Password Recovery")
        }
    }
}