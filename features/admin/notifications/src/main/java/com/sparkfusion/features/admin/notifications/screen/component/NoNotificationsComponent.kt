package com.sparkfusion.features.admin.notifications.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.notifications.R

@Composable
fun NoNotificationsComponent(
    onReloadClick: () -> Unit
) {
    Spacer(modifier = Modifier.height(80.dp))

    Icon(
        tint = descriptionColor(),
        modifier = Modifier.size(156.dp),
        painter = painterResource(R.drawable.no_notifications_icon),
        contentDescription = stringResource(R.string.no_notifications_icon_description)
    )

    SFProRoundedText(
        modifier = Modifier.padding(top = 20.dp),
        content = stringResource(R.string.notifications_not_found),
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    )

    SFProRoundedText(
        modifier = Modifier.padding(top = 10.dp, start = 24.dp, end = 24.dp),
        content = stringResource(R.string.do_not_any_notifications),
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        color = descriptionColor()
    )

    Spacer(modifier = Modifier.height(40.dp))

    Button(
        modifier = Modifier.width(240.dp).height(46.dp),
        onClick = onReloadClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.round_replay),
                contentDescription = stringResource(R.string.no_notifications_icon_description)
            )

            Spacer(modifier = Modifier.width(8.dp))

            SFProRoundedText(
                content = stringResource(R.string.try_again),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
    }
}