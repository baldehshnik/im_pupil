package com.sparkfusion.features.admin.home.widget

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun TopIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    contentDescription: String?,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.primary,
            painter = painterResource(iconId),
            contentDescription = contentDescription
        )
    }
}