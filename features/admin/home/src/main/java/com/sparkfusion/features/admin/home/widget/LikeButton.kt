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
fun LikeButton(
    modifier: Modifier = Modifier,
    onLikeClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(40.dp),
            onDraw = { drawCircle(color = Color.LightGray, alpha = 0.4f) }
        )

        IconButton(onClick = onLikeClick) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(R.drawable.outline_heart_icon),
                contentDescription = stringResource(R.string.like_button_icon_description)
            )
        }
    }
}