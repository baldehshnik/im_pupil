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
import com.sparkfusion.core.widget.button.BlackButton
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.common.password_recovery.R
import com.sparkfusion.features.common.password_recovery.navigator.IPasswordRecoveryEmailEnterNavigator
import com.sparkfusion.features.common.password_recovery.screen.component.EnterComponent

@Composable
fun PassRecoveryEmailEnterScreen(
    navigator: IPasswordRecoveryEmailEnterNavigator,
    modifier: Modifier = Modifier
) {
    val emailStateValue = remember { mutableStateOf("") }

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
            textFieldTitle = stringResource(R.string.email),
            placeHolderText = stringResource(R.string.enter_here),
            textFieldValue = emailStateValue.value,
            textFieldDescriptionInfo = stringResource(R.string.email_enter_description_info),
            onTextFieldValueChange = { emailStateValue.value = it },
            keyboardType = KeyboardType.Email,
            leadingIcon = {
                Icon(
                    modifier = Modifier.padding(start = 12.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.round_email),
                    contentDescription = stringResource(R.string.email_icon_description),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        BlackButton(
            text = stringResource(R.string.request_code),
            onClick = navigator::navigateToCodeEnterScreen,
            modifier = Modifier
                .height(48.dp)
                .width(240.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PassRecoveryEmailEnterScreenPreview() {
    PassRecoveryEmailEnterScreen(
        navigator = object : IPasswordRecoveryEmailEnterNavigator {
            override fun navigateToCodeEnterScreen() {}
            override fun popBackStack() {}
        }
    )
}