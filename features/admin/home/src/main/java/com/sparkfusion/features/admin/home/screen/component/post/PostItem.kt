package com.sparkfusion.features.admin.home.screen.component.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.text.ShimmerTextBox

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    isDarkModeEnabled: Boolean,
    isPlaceholder: Boolean = false
) {
    Column(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
    ) {
        PostImageIcon(
            isDarkModeEnabled = isDarkModeEnabled,
            onMoreVertButtonClick = {  }
        )

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp))
                .background(Color(0xFFEEF7FF))
                .fillMaxWidth()
        ) {
            ShimmerTextBox(
                modifier = Modifier.padding(top = 12.dp, start = 20.dp, end = 32.dp),
                size = DpSize(260.dp, 28.dp),
                isDarkModeEnabled = isDarkModeEnabled,
                isLoadingAnimationCompleted = !isPlaceholder
            ) {
                SFProRoundedText(
                    content = "Some short title about post",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            ShimmerTextBox(
                modifier = Modifier
                    .padding(start = 20.dp, top = 2.dp)
                    .width(240.dp),
                size = DpSize(220.dp, 24.dp),
                isDarkModeEnabled = isDarkModeEnabled,
                isLoadingAnimationCompleted = !isPlaceholder
            ) {
                SFProRoundedText(
                    content = "Some show description content",
                    fontWeight = FontWeight.Medium,
                    color = descriptionColor(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            DateLikeItem(
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 18.dp)
                    .fillMaxWidth(),
                isDarkModeEnabled = isDarkModeEnabled,
                isPlaceholder = isPlaceholder,
                onLikeClick = {  }
            )
        }
    }
}