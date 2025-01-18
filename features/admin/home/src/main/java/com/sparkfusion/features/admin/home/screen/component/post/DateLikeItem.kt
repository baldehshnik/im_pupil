package com.sparkfusion.features.admin.home.screen.component.post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.home.widget.LikeButton

@Composable
fun DateLikeItem(
    modifier: Modifier = Modifier,
    onLikeClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SFProRoundedText(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.Bottom)
                .width(140.dp),
            content = "Duration06.12.2023 at 12:04",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = descriptionColor(),
        )

        LikeButton(
            modifier = Modifier.padding(end = 24.dp),
            onLikeClick = onLikeClick
        )
    }
}