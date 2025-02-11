package com.sparkfusion.features.pupil.sign_up.component

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
import com.sparkfusion.features.pupil.sign_up.R

@Composable
fun PersonalInformationBlock(
    modifier: Modifier = Modifier,
    code: String,
    onCodeChange: (String) -> Unit
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
            value = code,
            placeholder = stringResource(R.string.code),
            onValueChange = onCodeChange
        )
    }
}















