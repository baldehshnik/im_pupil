package com.sparkfusion.core.widget.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.font.sfProRoundedFontFamily

@Composable
fun SFProRoundedText(
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 16.sp,
    color: Color = Color.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    content: String
) {
    Text(
        text = content,
        fontFamily = sfProRoundedFontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
        modifier = modifier,
        style = TextStyle(lineHeight = fontSize),
        overflow = overflow,
        maxLines = maxLines
    )
}