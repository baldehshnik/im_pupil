package com.sparkfusion.features.common.password_recovery.screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.fieldBorderColor
import com.sparkfusion.core.resource.font.sfProRoundedFontFamily
import com.sparkfusion.core.widget.text.DescriptionText
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun EnterComponent(
    modifier: Modifier = Modifier,
    textFieldTitle: String,
    placeHolderText: String,
    textFieldValue: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textFieldDescriptionInfo: String? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextFieldValueChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isDarkTheme = isSystemInDarkTheme()

    Column(modifier = modifier) {
        SFProRoundedText(
            content = textFieldTitle,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(start = 24.dp)
                .align(Alignment.Start)
        )

        OutlinedTextField(
            value = textFieldValue,
            onValueChange = onTextFieldValueChange,
            interactionSource = interactionSource,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(
                        2.dp,
                        fieldBorderColor(
                            isFocused = isFocused,
                            isDarkTheme = isDarkTheme
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                ),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            textStyle = TextStyle(
                fontFamily = sfProRoundedFontFamily,
                fontSize = 16.sp
            ),
            placeholder = {
                SFProRoundedText(content = placeHolderText)
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )

        if (textFieldDescriptionInfo != null) {
            DescriptionText(content = textFieldDescriptionInfo)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EnterComponentPreview() {
    EnterComponent(
        textFieldTitle = "Title",
        placeHolderText = "Placeholder",
        textFieldValue = "",
        textFieldDescriptionInfo = "Description information",
        onTextFieldValueChange = { }
    )
}