package com.sparkfusion.features.admin.home.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sparkfusion.features.admin.home.R

@Composable
fun MoreVertButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val contentModifier = Modifier
            .align(Alignment.TopEnd)
            .size(40.dp)

        Canvas(modifier = contentModifier) {
            drawCircle(color = Color.Black, alpha = 0.4f)
        }

        IconButton(
            modifier = contentModifier,
            onClick = onClick
        ) {
            Icon(
                tint = Color.White,
                painter = painterResource(R.drawable.round_more_vert),
                contentDescription = stringResource(R.string.more_vert_icon_description)
            )
        }
    }
}