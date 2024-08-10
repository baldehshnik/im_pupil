package com.sparkfusion.features.common.password_recovery.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.common.password_recovery.R
import com.sparkfusion.features.common.password_recovery.navigator.IPasswordRecoveryCodeEnterNavigator
import com.sparkfusion.features.common.password_recovery.screen.component.EnterComponent
import com.sparkfusion.features.common.password_recovery.widget.PassRecoveryButton

@Composable
fun PassRecoveryCodeEnterScreen(
    navigator: IPasswordRecoveryCodeEnterNavigator,
    modifier: Modifier = Modifier
) {
    val codeStateValue = remember { mutableStateOf("") }

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
            textFieldTitle = stringResource(R.string.confirmation_code),
            placeHolderText = stringResource(R.string.enter_here),
            textFieldValue = codeStateValue.value,
            textFieldDescriptionInfo = stringResource(R.string.code_enter_description_info),
            onTextFieldValueChange = { codeStateValue.value = it },
            keyboardType = KeyboardType.Number,
            leadingIcon = {
                Icon(
                    modifier = Modifier.padding(start = 12.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.round_vpn_key),
                    contentDescription = stringResource(R.string.code_icon_description),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        PassRecoveryButton(
            text = stringResource(R.string.confirm),
            onClick = navigator::navigateToNewPasswordScreen,
            modifier = Modifier
                .height(48.dp)
                .width(240.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PassRecoveryCodeEnterScreenPreview() {
    PassRecoveryCodeEnterScreen(
        navigator = object : IPasswordRecoveryCodeEnterNavigator {
            override fun navigateToNewPasswordScreen() {}
            override fun popBackStack() {}
        }
    )
}