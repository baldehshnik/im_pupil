package com.sparkfusion.core.widget.check

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun CheckButtonWidget(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    leftAlignment: Boolean = true,
    content: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val textColor = when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.outline
        else -> Color.DarkGray
    }

    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leftAlignment) {
            SFProRoundedText(
                content = content,
                color = textColor
            )
        }

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

        if (!leftAlignment) {
            SFProRoundedText(
                content = content,
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CheckButtonWidgetPreview() {
    CheckButtonWidget(
        modifier = Modifier,
        horizontalArrangement = Arrangement.End,
        checked = false,
        content = "Save login",
        onCheckedChange = {  }
    )
}