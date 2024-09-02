package com.sparkfusion.features.admin.home.screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.home.R
import com.sparkfusion.features.admin.home.widget.TopIconButton

@Composable
fun TopComponent(
    modifier: Modifier = Modifier,
    name: String,
    onFilterIconClick: () -> Unit,
    onNotificationsIconClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(vertical = 12.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(40.dp),
            model = R.drawable.app_logo,
            contentDescription = stringResource(R.string.app_logo_description)
        )

        Spacer(modifier = Modifier.width(6.dp))

        SFProRoundedText(
            content = stringResource(R.string.hi_with_name, name),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        TopIconButton(
            onClick = onFilterIconClick,
            iconId = R.drawable.filter_icon,
            contentDescription = stringResource(R.string.filters_icon_description)
        )

        TopIconButton(
            onClick = onNotificationsIconClick,
            iconId = R.drawable.notification_icon,
            contentDescription = stringResource(R.string.notifications_icon_description)
        )
    }
}