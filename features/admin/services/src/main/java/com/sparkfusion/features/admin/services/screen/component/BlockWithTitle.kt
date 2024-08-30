package com.sparkfusion.features.admin.services.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun BlockWithTitle(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier.padding(horizontal = 24.dp)) {
        Spacer(modifier = Modifier.height(20.dp))

        SFProRoundedText(
            content = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        content()
    }
}