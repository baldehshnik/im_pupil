package com.sparkfusion.features.admin.sign_up.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.admin.sign_up.navigator.ISignUpNavigator

@Composable
fun SignUpScreen(
    navigator: ISignUpNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Admin Sign Up Screen")
    }
}