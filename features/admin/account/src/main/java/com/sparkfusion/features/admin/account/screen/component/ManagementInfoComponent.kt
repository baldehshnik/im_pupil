package com.sparkfusion.features.admin.account.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun ManagementInfoComponent(
    modifier: Modifier = Modifier,
    title: String,
    content: String
) {
    Column(modifier = modifier.padding(top = 16.dp, start = 32.dp)) {
        SFProRoundedText(
            content = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp
        )

        SFProRoundedText(
            modifier = Modifier.padding(top = 4.dp),
            content = content,
            fontWeight = FontWeight.Medium
        )
    }
}