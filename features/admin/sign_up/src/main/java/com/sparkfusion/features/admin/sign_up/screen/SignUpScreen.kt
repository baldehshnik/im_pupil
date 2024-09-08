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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.button.BlackButton
import com.sparkfusion.core.widget.text.DescriptionText
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.admin.sign_up.R
import com.sparkfusion.features.admin.sign_up.navigator.ISignUpNavigator
import com.sparkfusion.features.admin.sign_up.screen.component.LoginDetailsBlock
import com.sparkfusion.features.admin.sign_up.screen.component.PersonalInformationBlock
import com.sparkfusion.core.widget.spinner.OutlinedDropDownMenu
import com.sparkfusion.core.widget.textfield.TextFieldWithoutTitle

@Composable
fun SignUpScreen(
    navigator: ISignUpNavigator,
    modifier: Modifier = Modifier
) {
    val administrationPlaces = stringArrayResource(R.array.administration_place)
    val administrationPlaceSelectedItem = rememberSaveable { mutableStateOf(administrationPlaces[0]) }

    val accessModes = stringArrayResource(R.array.access_mode)
    val accessModeSelectedItem = rememberSaveable { mutableStateOf(accessModes[0]) }

    val educationalInstitutionValue = remember { mutableStateOf("") }
    val firstnameValue = remember { mutableStateOf("") }
    val lastnameValue = remember { mutableStateOf("") }
    val patronymicValue = remember { mutableStateOf("") }

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    val verticalScroll = rememberScrollState()

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
            content = stringResource(R.string.place_of_administration),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )

        OutlinedDropDownMenu(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp),
            items = administrationPlaces,
            selectedItem = administrationPlaceSelectedItem
        )

        Spacer(modifier = Modifier.height(16.dp))

        SFProRoundedText(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .align(Alignment.Start),
            content = administrationPlaceSelectedItem.value,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )

        TextFieldWithoutTitle(
            placeholder = stringResource(R.string.enter_name_here),
            value = educationalInstitutionValue.value,
            onValueChange = { educationalInstitutionValue.value = it }
        )

        Spacer(modifier = Modifier.padding(top = 24.dp))

        PersonalInformationBlock(
            firstnameValue = firstnameValue,
            lastnameValue = lastnameValue,
            patronymicValue = patronymicValue
        )

        Spacer(modifier = Modifier.padding(top = 24.dp))

        LoginDetailsBlock(
            emailValue = emailValue,
            passwordValue = passwordValue
        )

        Spacer(modifier = Modifier.padding(top = 24.dp))

        SFProRoundedText(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .align(Alignment.Start),
            content = stringResource(R.string.access_mode),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.padding(top = 10.dp))

        OutlinedDropDownMenu(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp),
            items = accessModes,
            selectedItem = accessModeSelectedItem
        )

        DescriptionText(
            modifier = Modifier.padding(top = 10.dp),
            content = stringResource(R.string.after_submit_info)
        )

        BlackButton(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 24.dp)
                .size(width = 240.dp, height = 48.dp),
            text = stringResource(R.string.register),
            onClick = navigator::navigateToSignInScreen
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