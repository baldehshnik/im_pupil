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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.sign_up.R
import com.sparkfusion.core.widget.textfield.TextFieldWithoutTitle

@Composable
fun LoginDetailsBlock(
    modifier: Modifier = Modifier,
    emailValue: MutableState<String>,
    passwordValue: MutableState<String>
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
            value = emailValue.value,
            placeholder = stringResource(R.string.email),
            onValueChange = { emailValue.value = it },
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
            value = passwordValue.value,
            placeholder = stringResource(R.string.password),
            onValueChange = { passwordValue.value = it },
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginDetailsBlockPreview() {
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    LoginDetailsBlock(
        emailValue = emailValue,
        passwordValue = passwordValue
    )
}