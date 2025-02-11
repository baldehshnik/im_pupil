package com.sparkfusion.features.pupil.sign_up.screen

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.button.BlackButton
import com.sparkfusion.core.widget.text.DescriptionText
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.pupil.sign_up.R
import com.sparkfusion.features.pupil.sign_up.component.LoginDetailsBlock
import com.sparkfusion.features.pupil.sign_up.component.OutlinedDropDownMenu
import com.sparkfusion.features.pupil.sign_up.component.PersonalInformationBlock
import com.sparkfusion.features.pupil.sign_up.viewmodel.SignUpViewModel

@Composable
fun SignUpScreenEnter(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    onBackStackClick: () -> Unit,
    onNavigateToSignInScreen: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val institutionState by viewModel.institutionState.collectAsStateWithLifecycle()
    val signUpState by viewModel.singUpState.collectAsStateWithLifecycle()
    val signUpDataState by viewModel.singUpDataState.collectAsStateWithLifecycle()

    val verticalScroll = rememberScrollState()

    when (signUpDataState) {
        is SignUpViewModel.SignUpDataState.Failure -> {
            ShowToast(value = (signUpDataState as SignUpViewModel.SignUpDataState.Failure).value)
            viewModel.clearSignUpDataState()
        }

        SignUpViewModel.SignUpDataState.Initial -> {}
    }

    when (signUpState) {
        SignUpViewModel.SignUpState.Error -> {
            ShowToast(value = stringResource(id = R.string.error))
            viewModel.clearSignUpState()
        }

        SignUpViewModel.SignUpState.Initial -> {}
        SignUpViewModel.SignUpState.Progress -> {
            ShowToast(value = stringResource(id = R.string.registration_with_dots))
            viewModel.clearSignUpState()
        }

        SignUpViewModel.SignUpState.Success -> {
            onNavigateToSignInScreen()
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
            onBackClick = onBackStackClick
        )

        Spacer(modifier = Modifier.height(10.dp))

        SFProRoundedText(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .align(Alignment.Start),
            content = stringResource(id = R.string.university),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )

        OutlinedDropDownMenu(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp),
            items = institutionState,
            selectedItem = if (institutionState.isEmpty()) null else institutionState[0],
            onItemSelected = {
                viewModel.setInstitution(it.id)
            },
            onValueChange = {
                viewModel.readInstitutionByNamePart(it)
            }
        )

        Spacer(modifier = Modifier.padding(top = 24.dp))

        PersonalInformationBlock(
            code = state.code,
            onCodeChange = viewModel::updateCode
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




























