package com.sparkfusion.features.admin.sign_up.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.textfield.TextFieldWithoutTitle
import com.sparkfusion.features.admin.sign_up.R

@Composable
fun PersonalInformationBlock(
    modifier: Modifier = Modifier,
    firstname: String,
    lastname: String,
    patronymic: String,
    onLastnameChange: (String) -> Unit,
    onFirstnameChange: (String) -> Unit,
    onPatronymicChange: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        SFProRoundedText(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .align(Alignment.Start),
            content = stringResource(R.string.personal_information),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextFieldWithoutTitle(
            value = firstname,
            placeholder = stringResource(R.string.firstname),
            onValueChange = onFirstnameChange
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextFieldWithoutTitle(
            value = lastname,
            placeholder = stringResource(R.string.lastname),
            onValueChange = onLastnameChange
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextFieldWithoutTitle(
            value = patronymic,
            placeholder = stringResource(R.string.patronymic),
            onValueChange = onPatronymicChange
        )
    }
}









