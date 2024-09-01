package com.sparkfusion.features.admin.account.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.account.R

@Composable
fun AdministratorItem(
    modifier: Modifier = Modifier,
    isDarkModeEnabled: Boolean,
    onMoreInfoClick: () -> Unit
) {
    var isImageLoadingCompleted by remember { mutableStateOf(false) }
    val painter = rememberAsyncImagePainter(
        model = com.sparkfusion.core.resource.R.drawable.magazine_service_icon,
        onSuccess = { isImageLoadingCompleted = true },
        onLoading = { isImageLoadingCompleted = false }
    )

    Row(
        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerImageBox(
            contentDescription = stringResource(R.string.another_administrator_image_description),
            size = DpSize(56.dp, 56.dp),
            painter = painter,
            isDarkModeEnabled = isDarkModeEnabled,
            isImageAnimationCompleted = isImageLoadingCompleted
        )

        Column(
            modifier = Modifier
                .padding(start = 12.dp, top = 6.dp)
                .align(Alignment.Top)
        ) {
            SFProRoundedText(
                content = "Kulaga Dmitriy",
                fontWeight = FontWeight.SemiBold,
            )

            SFProRoundedText(
                content = "Administrator",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = descriptionColor()
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onMoreInfoClick) {
            Icon(
                tint = MaterialTheme.colorScheme.primary,
                painter = painterResource(R.drawable.info_icon),
                contentDescription = stringResource(R.string.more_info_about_administrator_button_description)
            )
        }
    }
}