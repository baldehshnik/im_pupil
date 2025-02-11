package com.sparkfusion.features.admin.account.screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
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
import com.sparkfusion.domain.admin.port.portaccount.InstitutionAdminModel
import com.sparkfusion.features.admin.account.R

@Composable
internal fun AdministratorItem(
    modifier: Modifier = Modifier,
    admin: InstitutionAdminModel,
    onMoreInfoClick: () -> Unit,
    onItemClick: () -> Unit
) {
    var isImageLoadingCompleted by remember { mutableStateOf(false) }
    val painter = rememberAsyncImagePainter(
        model = admin.icon,
        onSuccess = { isImageLoadingCompleted = true },
        onLoading = { isImageLoadingCompleted = false }
    )

    Row(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 4.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerImageBox(
            contentDescription = stringResource(R.string.another_administrator_image_description),
            size = DpSize(56.dp, 56.dp),
            painter = painter,
            isDarkModeEnabled = isSystemInDarkTheme(),
            isImageAnimationCompleted = isImageLoadingCompleted
        )

        Column(
            modifier = Modifier
                .padding(start = 12.dp, top = 6.dp)
                .align(Alignment.Top)
        ) {
            SFProRoundedText(
                content = admin.lastname + " " + admin.firstname,
                fontWeight = FontWeight.SemiBold,
            )

            SFProRoundedText(
                content = when (admin.accessMode) {
                    1 -> stringResource(id = R.string.assistant)
                    2 -> stringResource(id = R.string.teacher)
                    else -> stringResource(id = R.string.administrator)
                },
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