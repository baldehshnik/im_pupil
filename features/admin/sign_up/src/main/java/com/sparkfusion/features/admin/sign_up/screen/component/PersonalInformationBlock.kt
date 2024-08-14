package com.sparkfusion.features.admin.sign_up.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.sign_up.R
import com.sparkfusion.features.admin.sign_up.widget.TextFieldWithoutTitle

@Composable
fun PersonalInformationBlock(
    modifier: Modifier = Modifier,
    firstnameValue: MutableState<String>,
    lastnameValue: MutableState<String>,
    patronymicValue: MutableState<String>
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
            value = firstnameValue.value,
            placeholder = stringResource(R.string.firstname),
            onValueChange = { firstnameValue.value = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextFieldWithoutTitle(
            value = lastnameValue.value,
            placeholder = stringResource(R.string.lastname),
            onValueChange = { lastnameValue.value = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextFieldWithoutTitle(
            value = patronymicValue.value,
            placeholder = stringResource(R.string.patronymic),
            onValueChange = { patronymicValue.value = it }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PersonalInformationBlockPreview() {
    val firstnameValue = remember { mutableStateOf("") }
    val lastnameValue = remember { mutableStateOf("") }
    val patronymicValue = remember { mutableStateOf("") }

    PersonalInformationBlock(
        firstnameValue = firstnameValue,
        lastnameValue = lastnameValue,
        patronymicValue = patronymicValue
    )
}