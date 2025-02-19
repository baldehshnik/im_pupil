package com.sparkfusion.services.pupil.students.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun AccountInfoComponent(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    SFProRoundedText(
        modifier = modifier,
        content = title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )

    SFProRoundedText(
        modifier = Modifier.padding(top = 6.dp, start = 24.dp),
        content = value,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    )
}
