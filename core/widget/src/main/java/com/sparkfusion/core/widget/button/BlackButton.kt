package com.sparkfusion.core.widget.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.blackButtonContainerColor
import com.sparkfusion.core.resource.color.blackButtonTextColor
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun BlackButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    RoundedButton(
        onClick = onClick,
        containerColor = blackButtonContainerColor(),
        modifier = modifier
    ) {
        SFProRoundedText(
            content = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = blackButtonTextColor()
        )
    }
}