package com.sparkfusion.features.common.password_recovery.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.widget.button.BlackButton
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.common.password_recovery.R
import com.sparkfusion.features.common.password_recovery.navigator.IPasswordRecoveryNewPasswordNavigator
import com.sparkfusion.features.common.password_recovery.screen.component.EnterComponent

@Composable
fun PassRecoveryNewPasswordScreen(
    navigator: IPasswordRecoveryNewPasswordNavigator,
    modifier: Modifier = Modifier
) {
    val passwordStateValue = remember { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }

    val confirmPasswordStateValue = remember { mutableStateOf("") }
    val showConfirmPassword = remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            title = stringResource(R.string.password_recovery),
            onBackClick = navigator::popBackStack
        )

        Spacer(modifier = Modifier.height(10.dp))

        EnterComponent(
            textFieldTitle = stringResource(R.string.new_password),
            placeHolderText = stringResource(R.string.enter_here),
            textFieldValue = passwordStateValue.value,
            onTextFieldValueChange = { passwordStateValue.value = it },
            keyboardType = KeyboardType.Password,
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        modifier = Modifier.padding(end = 12.dp),
                        imageVector = ImageVector.vectorResource(
                            if (showPassword.value) R.drawable.round_visibility else R.drawable.round_visibility_off
                        ),
                        contentDescription = stringResource(R.string.password_visibility_icon_description)
                    )
                }
            }
        )

        EnterComponent(
            modifier = Modifier.padding(top = 16.dp),
            textFieldTitle = stringResource(R.string.confirm_password),
            placeHolderText = stringResource(R.string.enter_here),
            textFieldValue = confirmPasswordStateValue.value,
            onTextFieldValueChange = { confirmPasswordStateValue.value = it },
            keyboardType = KeyboardType.Password,
            visualTransformation = if (showConfirmPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showConfirmPassword.value = !showConfirmPassword.value }) {
                    Icon(
                        modifier = Modifier.padding(end = 12.dp),
                        imageVector = ImageVector.vectorResource(
                            if (showConfirmPassword.value) R.drawable.round_visibility else R.drawable.round_visibility_off
                        ),
                        contentDescription = stringResource(R.string.confirm_password_visibility_icon_description)
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        BlackButton(
            text = stringResource(R.string.restore),
            onClick = navigator::navigateToSignInScreen,
            modifier = Modifier
                .height(48.dp)
                .width(240.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PassRecoverNewPasswordScreenPreview() {
    PassRecoveryNewPasswordScreen(
        navigator = object : IPasswordRecoveryNewPasswordNavigator {
            override fun navigateToSignInScreen() {}
            override fun popBackStack() {}
        }
    )
}