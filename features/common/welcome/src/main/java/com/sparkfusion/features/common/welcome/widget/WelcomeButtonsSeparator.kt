package com.sparkfusion.features.common.welcome.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.common.welcome.R

@Composable
fun WelcomeButtonsSeparator(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        val lineModifier = Modifier
            .width(40.dp)
            .padding(horizontal = 8.dp)

        Line(modifier = lineModifier)

        SFProRoundedText(
            content = stringResource(R.string.or),
            color = Color.Gray
        )

        Line(modifier = lineModifier)
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeButtonsSeparatorPreview() {
    WelcomeButtonsSeparator()
}