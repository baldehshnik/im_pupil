package com.sparkfusion.features.admin.services.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.services.R

@Composable
fun TopComponent(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(vertical = 12.dp, horizontal = 24.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.settings_icon),
            contentDescription = stringResource(R.string.account_image_description)
        )

        Spacer(modifier = Modifier.width(12.dp))

        SFProRoundedText(
            content = stringResource(R.string.services),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onSettingsClick) {
            Icon(
                tint = MaterialTheme.colorScheme.primary,
                painter = painterResource(R.drawable.settings_icon),
                contentDescription = stringResource(R.string.settings_icon_description)
            )
        }
    }
}