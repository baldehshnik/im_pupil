package com.sparkfusion.features.common.password_recovery.widget

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.button.RoundedButton
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun PassRecoveryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    val containerColor = when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.surfaceContainerHighest
        else -> Color.Black
    }
    val textColor = when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.onSurface
        else -> Color.White
    }

    RoundedButton(
        onClick = onClick,
        containerColor = containerColor,
        modifier = modifier
    ) {
        SFProRoundedText(
            content = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PassRecoveryButtonPreview() {
    PassRecoveryButton(
        text = "Button",
        onClick = {  }
    )
}