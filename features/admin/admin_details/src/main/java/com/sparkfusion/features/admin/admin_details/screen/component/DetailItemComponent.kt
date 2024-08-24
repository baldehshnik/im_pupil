package com.sparkfusion.features.admin.admin_details.screen.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.text.ShimmerTextBox

@Composable
fun DetailItemComponent(
    modifier: Modifier = Modifier,
    isDarkModeEnabled: Boolean,
    isAnimationLoadingCompleted: Boolean,
    title: String,
    textContent: String,
    @DrawableRes imageId: Int,
    width: Dp = 100.dp,
    detailButton: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .padding(start = 36.dp, end = 36.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(20.dp)),
            painter = painterResource(imageId),
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.Top)
        ) {
            SFProRoundedText(
                modifier = Modifier.padding(top = 8.dp),
                content = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            ShimmerTextBox(
                modifier = Modifier.padding(top = 2.dp),
                size = DpSize(width, 24.dp),
                isDarkModeEnabled = isDarkModeEnabled,
                isLoadingAnimationCompleted = isAnimationLoadingCompleted,
            ) {
                SFProRoundedText(
                    content = textContent,
                    fontWeight = FontWeight.SemiBold,
                    color = descriptionColor()
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        detailButton?.invoke()
    }
}