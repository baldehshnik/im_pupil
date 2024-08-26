package com.sparkfusion.features.common.sign_in.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.common.sign_in.R
import com.sparkfusion.features.common.sign_in.navigator.ISignInNavigator
import com.sparkfusion.features.common.sign_in.screen.component.LoginButtons
import com.sparkfusion.features.common.sign_in.screen.component.SavedAccountsComponent
import com.sparkfusion.features.common.sign_in.widget.CheckButtonWidget
import com.sparkfusion.features.common.sign_in.widget.LoginTextField

@Composable
fun SignInScreen(
    navigator: ISignInNavigator,
    modifier: Modifier = Modifier
) {
    val emailStateValue = rememberSaveable { mutableStateOf("") }
    val emailInteractionSource = remember { MutableInteractionSource() }
    val isEmailFocused by emailInteractionSource.collectIsFocusedAsState()
    val defaultIconColor = LocalContentColor.current

    val passwordStateValue = rememberSaveable { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }

    val saveLogin = rememberSaveable { mutableStateOf(false) }

    val pagesCount = rememberSaveable { mutableIntStateOf(3) }
    val pagerState = rememberPagerState { pagesCount.intValue }
    val pages = listOf("Image 16546546456", "Image 2", "Image 6546543")
    val isDataLoadingCompleted = rememberSaveable { mutableStateOf(false) }

    val isSystemInDark = isSystemInDarkTheme()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            title = stringResource(R.string.login),
            onBackClick = navigator::popBackStack
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (pagesCount.intValue != 0) {
            SavedAccountsComponent(
                pagerState = pagerState,
                pages = pages,
                isSystemInDark = isSystemInDark,
                isDataLoadingCompleted = isDataLoadingCompleted.value
            )

            Spacer(modifier = Modifier.height(20.dp))
        } else {
            Image(
                modifier = Modifier.size(156.dp),
                painter = painterResource(R.drawable.empty_sign_in_list),
                contentDescription = stringResource(R.string.empty_sign_in_image_description)
            )
        }

        SFProRoundedText(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            content = stringResource(
                if (pagesCount.intValue == 0) R.string.enter_login_details else R.string.or
            ),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        LoginTextField(
            value = emailStateValue.value,
            onValueChange = { emailStateValue.value = it },
            keyboardType = KeyboardType.Email,
            label = stringResource(R.string.email),
            interactionSource = emailInteractionSource,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.round_email),
                    tint = if (isEmailFocused) MaterialTheme.colorScheme.primary else defaultIconColor,
                    contentDescription = stringResource(R.string.email_enter_icon_description)
                )
            }
        )

        LoginTextField(
            value = passwordStateValue.value,
            onValueChange = { passwordStateValue.value = it },
            label = stringResource(R.string.password),
            keyboardType = KeyboardType.Password,
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        painter = if (showPassword.value) painterResource(
                            R.drawable.round_visibility
                        ) else painterResource(
                            R.drawable.round_visibility_off
                        ),
                        contentDescription = stringResource(R.string.password_enter_icon_description)
                    )
                }
            }
        )

        CheckButtonWidget(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp, top = 2.dp),
            checked = saveLogin.value,
            onCheckedChange = { saveLogin.value = it },
            horizontalArrangement = Arrangement.End
        )

        LoginButtons(
            modifier = Modifier.fillMaxSize(),
            onPasswordRecoveryClick = navigator::navigateToPasswordRecoveryScreen,
            onRegisterClick = navigator::navigateToAdminRegistrationScreen,
            onLoginClick = navigator::navigateToAdminHomeScreen
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SignInScreenPreview() {
    SignInScreen(
        navigator = object : ISignInNavigator {
            override fun navigateToPasswordRecoveryScreen() {}
            override fun navigateToAdminRegistrationScreen() {}
            override fun navigateToAdminHomeScreen() {}
            override fun popBackStack() {}
        }
    )
}