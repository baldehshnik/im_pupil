package com.sparkfusion.services.admin.magazine.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun SpinnerWithTitleComponent(
    modifier: Modifier = Modifier,
    title: String
) {
    SFProRoundedText(
        content = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(start = 24.dp, top = 16.dp)
    )

    SpinnerComponent(
        modifier = modifier,
        defaultValue = "Default value"
    )
}
