package com.sparkfusion.core.widget.text

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.resource.color.descriptionColor

@Composable
fun DescriptionText(
    modifier: Modifier = Modifier,
    content: String
) {
    SFProRoundedText(
        content = content,
        modifier = modifier.padding(top = 4.dp, start = 24.dp, end = 24.dp),
        color = descriptionColor()
    )
}