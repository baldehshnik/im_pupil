package com.sparkfusion.features.admin.post.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.textfield.TextFieldWithoutTitle

@Composable
fun AddingTextField(
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    title: String,
    placeholder: String,
    value: String,
    onValueUpdate: (String) -> Unit
) {
    SFProRoundedText(
        modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 2.dp),
        content = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    )

    TextFieldWithoutTitle(
        modifier = modifier,
        singleLine = singleLine,
        value = value,
        onValueChange = onValueUpdate,
        placeholder = placeholder
    )
}