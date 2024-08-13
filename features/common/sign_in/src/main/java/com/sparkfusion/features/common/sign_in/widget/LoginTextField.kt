package com.sparkfusion.features.common.sign_in.widget

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.font.sfProRoundedFontFamily
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.common.sign_in.R

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    trailingIcon: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 36.dp, end = 36.dp, top = 12.dp),
        value = value,
        onValueChange = onValueChange,
        interactionSource = interactionSource,
        textStyle = TextStyle(
            fontFamily = sfProRoundedFontFamily,
            fontSize = 16.sp
        ),
        label = {
            SFProRoundedText(content = label)
        },
        placeholder = {
            SFProRoundedText(content = stringResource(R.string.enter))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent
        )
    )
}