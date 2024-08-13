package com.sparkfusion.features.common.welcome.screen.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.button.RoundedButton
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun WelcomeButton(
    modifier: Modifier = Modifier,
    information: String,
    onClick: () -> Unit
) {
    val containerColor = when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.primary
        else -> Color.Black
    }
    val textColor = when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.onPrimary
        else -> Color.White
    }

    RoundedButton(
        modifier = modifier
            .width(290.dp)
            .height(50.dp)
            .padding(bottom = 4.dp),
        containerColor = containerColor,
        onClick = onClick
    ) {
        SFProRoundedText(
            modifier = Modifier.padding(vertical = 4.dp),
            content = information,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeButtonPreview() {
    WelcomeButton(
        information = "Information",
        onClick = {   }
    )
}