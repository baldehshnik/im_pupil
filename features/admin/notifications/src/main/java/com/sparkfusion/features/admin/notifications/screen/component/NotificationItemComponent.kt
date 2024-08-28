package com.sparkfusion.features.admin.notifications.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.text.ShimmerTextBox
import com.sparkfusion.features.admin.notifications.R

@Composable
fun NotificationItemComponent(
    modifier: Modifier = Modifier,
    isDarkModeEnabled: Boolean,
    isDataLoadingAnimationCompleted: Boolean,
    title: String,
    received: String
) {
    val isImageLoadingCompleted by remember { mutableStateOf(false) }
    val painter = rememberAsyncImagePainter(model = R.drawable.test_notification_image)

    Row(modifier = modifier) {
        ShimmerImageBox(
            shape = RoundedCornerShape(20.dp),
            contentDescription = stringResource(R.string.notification_item_icon_description),
            size = DpSize(84.dp, 84.dp),
            painter = painter,
            isDarkModeEnabled = isDarkModeEnabled,
            isImageAnimationCompleted = isImageLoadingCompleted
        )

        Column(
            modifier = Modifier.padding(start = 20.dp, top = 4.dp),
            verticalArrangement = Arrangement.Top
        ) {
            ShimmerTextBox(
                contentAlignment = Alignment.TopStart,
                size = DpSize(220.dp, 20.dp),
                isDarkModeEnabled = isDarkModeEnabled,
                isLoadingAnimationCompleted = isDataLoadingAnimationCompleted
            ) {
                SFProRoundedText(
                    content = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            ShimmerTextBox(
                modifier = Modifier.padding(top = 4.dp),
                contentAlignment = Alignment.TopStart,
                size = DpSize(180.dp, 16.dp),
                isDarkModeEnabled = isDarkModeEnabled,
                isLoadingAnimationCompleted = isDataLoadingAnimationCompleted
            ) {
                SFProRoundedText(
                    content = received,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = descriptionColor()
                )
            }
        }
    }
}