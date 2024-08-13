package com.sparkfusion.features.common.sign_in.widget

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.common.sign_in.R

@Composable
fun CheckButtonWidget(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
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
        SFProRoundedText(
            content = stringResource(R.string.save_login),
            fontSize = 18.sp,
            color = textColor
        )

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CheckButtonWidgetPreview() {
    CheckButtonWidget(
        modifier = Modifier,
        horizontalArrangement = Arrangement.End,
        checked = false,
        onCheckedChange = {  }
    )
}