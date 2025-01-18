package com.sparkfusion.features.admin.sign_up.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.textfield.TextFieldWithoutTitle
import com.sparkfusion.features.admin.sign_up.R

@Composable
fun LoginDetailsBlock(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    val isPasswordVisible = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        SFProRoundedText(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .align(Alignment.Start),
            content = stringResource(R.string.login_details),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextFieldWithoutTitle(
            value = email,
            placeholder = stringResource(R.string.email),
            onValueChange = onEmailChange,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.round_email),
                    contentDescription = stringResource(R.string.text_field_email_icon_description),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextFieldWithoutTitle(
            value = password,
            placeholder = stringResource(R.string.password),
            onValueChange = onPasswordChange,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible.value = !isPasswordVisible.value }) {
                    Icon(
                        painter = painterResource(
                            if (isPasswordVisible.value) R.drawable.round_visibility
                            else R.drawable.round_visibility_off
                        ),
                        contentDescription = stringResource(R.string.text_field_password_icon_description)
                    )
                }
            }
        )
    }
}









