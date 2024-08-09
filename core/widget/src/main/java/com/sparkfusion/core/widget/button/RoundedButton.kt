package com.sparkfusion.core.widget.button

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Unspecified,
    onClick: () -> Unit,
    body: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        content = body
    )
}