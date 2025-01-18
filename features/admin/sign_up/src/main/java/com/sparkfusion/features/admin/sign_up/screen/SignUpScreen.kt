package com.sparkfusion.features.admin.sign_up.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.button.BlackButton
import com.sparkfusion.core.widget.text.DescriptionText
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.admin.sign_up.R
import com.sparkfusion.features.admin.sign_up.navigator.ISignUpNavigator
import com.sparkfusion.features.admin.sign_up.screen.component.LoginDetailsBlock
import com.sparkfusion.features.admin.sign_up.screen.component.OutlinedDropDownMenu
import com.sparkfusion.features.admin.sign_up.screen.component.PersonalInformationBlock
import com.sparkfusion.features.admin.sign_up.viewmodel.SingUpViewModel

@Composable
fun SignUpScreen(
    navigator: ISignUpNavigator,
    modifier: Modifier = Modifier,
    viewModel: SingUpViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val institutionState by viewModel.institutionState.collectAsStateWithLifecycle()
    val signUpState by viewModel.singUpState.collectAsStateWithLifecycle()
    val signUpDataState by viewModel.singUpDataState.collectAsStateWithLifecycle()

    val verticalScroll = rememberScrollState()

    when (signUpDataState) {
        is SingUpViewModel.SignUpDataState.Failure -> {
            ShowToast(value = (signUpDataState as SingUpViewModel.SignUpDataState.Failure).value)
            viewModel.clearSignUpDataState()
        }

        SingUpViewModel.SignUpDataState.Initial -> {}
    }

    when (signUpState) {
        SingUpViewModel.SignUpState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearSignUpState()
        }

        SingUpViewModel.SignUpState.Initial -> {}
        SingUpViewModel.SignUpState.Progress -> {
            ShowToast(value = "Registration...")
            viewModel.clearSignUpState()
        }

        SingUpViewModel.SignUpState.Success -> {
            navigator.navigateToSignInScreen()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(verticalScroll),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            title = stringResource(R.string.registration),
            onBackClick = navigator::popBackStack
        )

        Spacer(modifier = Modifier.height(10.dp))

        SFProRoundedText(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .align(Alignment.Start),
            content = "University",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )

        OutlinedDropDownMenu(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp),
            items = institutionState,
            selectedItem = if (institutionState.isEmpty()) null else institutionState[0],
            onItemSelected = {
                viewModel.setInstitution(it.abbreviation)
            },
            onValueChange = {
                viewModel.readInstitutionByNamePart(it)
            }
        )

        Spacer(modifier = Modifier.padding(top = 24.dp))

        PersonalInformationBlock(
            firstname = state.firstname,
            lastname = state.lastname,
            patronymic = state.patronymic,
            onLastnameChange = viewModel::updateLastname,
            onFirstnameChange = viewModel::updateFirstname,
            onPatronymicChange = viewModel::updatePatronymic
        )

        Spacer(modifier = Modifier.padding(top = 24.dp))

        LoginDetailsBlock(
            email = state.email,
            password = state.password,
            onEmailChange = viewModel::updateEmail,
            onPasswordChange = viewModel::updatePassword
        )

        Spacer(modifier = Modifier.padding(top = 24.dp))

        DescriptionText(
            modifier = Modifier,
            content = stringResource(R.string.after_submit_info)
        )

        BlackButton(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 24.dp)
                .size(width = 240.dp, height = 48.dp),
            text = stringResource(R.string.register),
            onClick = viewModel::signUp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(
        navigator = object : ISignUpNavigator {
            override fun popBackStack() {}
            override fun navigateToSignInScreen() {}
        }
    )
}