package com.sparkfusion.features.common.sign_in.screen

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.common.user_type.CurrentUserTypeHolder
import com.sparkfusion.core.common.user_type.UserType
import com.sparkfusion.core.widget.check.CheckButtonWidget
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.domain.admin.port.portauth.JwtAuthenticationModel
import com.sparkfusion.features.common.sign_in.R
import com.sparkfusion.features.common.sign_in.navigator.ISignInNavigator
import com.sparkfusion.features.common.sign_in.screen.component.LoginButtons
import com.sparkfusion.features.common.sign_in.viewmodel.SharedPreferencesHelper
import com.sparkfusion.features.common.sign_in.viewmodel.SignInViewModel
import com.sparkfusion.features.common.sign_in.widget.LoginTextField

@Composable
fun SignInScreen(
    navigator: ISignInNavigator,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val signInState by viewModel.signInState.collectAsStateWithLifecycle()
    val signInDataState by viewModel.signInDataState.collectAsStateWithLifecycle()

    val emailInteractionSource = remember { MutableInteractionSource() }
    val isEmailFocused by emailInteractionSource.collectIsFocusedAsState()
    val defaultIconColor = LocalContentColor.current
    val sharedPreferencesHelper = SharedPreferencesHelper(LocalContext.current)

    when (signInDataState) {
        is SignInViewModel.SignInDataState.Failure -> {
            ShowToast(value = (signInDataState as SignInViewModel.SignInDataState.Failure).message)
            viewModel.clearSignInDataState()
        }

        SignInViewModel.SignInDataState.Initial -> {}
    }

    when (signInState) {
        SignInViewModel.SignInState.Error -> {
            ShowToast(value = stringResource(id = R.string.error))
            viewModel.clearSignInState()
        }

        SignInViewModel.SignInState.Initial -> {}
        SignInViewModel.SignInState.Progress -> {
            ShowToast(value = stringResource(id = R.string.login_in_progress))
            viewModel.clearSignInState()
        }

        is SignInViewModel.SignInState.Success -> {
            val model: JwtAuthenticationModel =
                (signInState as SignInViewModel.SignInState.Success).response
            if (state.saveLogin) sharedPreferencesHelper.saveLoginDataForAdmin(model)

            val userType = CurrentUserTypeHolder.type
            if (userType == UserType.Admin) navigator.navigateToAdminHomeScreen()
            else { // navigate to pupil screen
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            title = stringResource(R.string.login),
            onBackClick = navigator::popBackStack
        )

        Spacer(modifier = Modifier.height(10.dp))

        SFProRoundedText(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            content = stringResource(R.string.enter_login_details),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        LoginTextField(
            value = state.email,
            onValueChange = viewModel::updateEmail,
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
            value = state.password,
            onValueChange = viewModel::updatePassword,
            label = stringResource(R.string.password),
            keyboardType = KeyboardType.Password,
            visualTransformation = if (state.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { viewModel.updatePasswordVisibility(!state.showPassword) }) {
                    Icon(
                        painter = if (state.showPassword) painterResource(
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
            checked = state.saveLogin,
            content = stringResource(id = R.string.save_login),
            onCheckedChange = viewModel::updateSaveLoginState,
            horizontalArrangement = Arrangement.End
        )

        LoginButtons(
            onPasswordRecoveryClick = navigator::navigateToPasswordRecoveryScreen,
            onRegisterClick = navigator::navigateToAdminRegistrationScreen,
            onLoginClick = viewModel::signIn
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