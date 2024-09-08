package com.sparkfusion.core.widget.textfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.fieldBorderColor
import com.sparkfusion.core.resource.font.sfProRoundedFontFamily
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun TextFieldWithoutTitle(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String,
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isDarkTheme = isSystemInDarkTheme()

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        interactionSource = interactionSource,
        modifier = modifier
            .padding(horizontal = 24.dp)
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = fieldBorderColor(
                        isFocused = isFocused,
                        isDarkTheme = isDarkTheme
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        singleLine = singleLine,
        textStyle = TextStyle(
            fontFamily = sfProRoundedFontFamily,
            fontSize = 16.sp
        ),
        placeholder = {
            SFProRoundedText(content = placeholder)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TextFieldWithoutTitlePreview() {
    TextFieldWithoutTitle(
        modifier = Modifier,
        onValueChange = { },
        placeholder = "Firstname"
    )
}