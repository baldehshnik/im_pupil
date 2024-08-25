package com.sparkfusion.features.common.filters.screen.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.resource.theme.surfaceContainerHighestDark
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun GenderCardComponent(
    modifier: Modifier = Modifier,
    borderStroke: BorderStroke,
    isDarkModeEnabled: Boolean,
    @DrawableRes drawableId: Int,
    drawableDescription: String,
    content: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.size(160.dp, 50.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkModeEnabled) surfaceContainerHighestDark else Color.White
        ),
        border = borderStroke,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp)
        ) {
            Image(
                painter = painterResource(drawableId),
                contentDescription = drawableDescription,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            SFProRoundedText(
                modifier = Modifier.padding(start = 10.dp),
                content = content,
                fontWeight = FontWeight.Medium
            )
        }
    }
}