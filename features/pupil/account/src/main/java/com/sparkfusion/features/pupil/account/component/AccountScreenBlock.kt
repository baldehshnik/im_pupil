package com.sparkfusion.features.pupil.account.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
internal fun AccountScreenBlock(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(30.dp),
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .padding(top = 12.dp)
            .background(
                color = Color.White,
                shape = shape
            )
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(18.dp))

        SFProRoundedText(
            modifier = Modifier.padding(start = 24.dp),
            content = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )

        content()
    }
}