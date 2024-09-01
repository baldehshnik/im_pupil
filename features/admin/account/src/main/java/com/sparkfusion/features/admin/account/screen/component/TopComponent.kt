package com.sparkfusion.features.admin.account.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.account.R

@Composable
fun TopComponent(
    modifier: Modifier = Modifier,
    content: String,
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.padding(start = 64.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SFProRoundedText(
                content = content,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { },
                painter = painterResource(R.drawable.round_keyboard_arrow_down),
                contentDescription = stringResource(R.string.abbreviation_button_description)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            modifier = Modifier
                .padding(end = 24.dp)
                .size(40.dp),
            onClick = onSettingsClick
        ) {
            Icon(
                painter = painterResource(com.sparkfusion.core.resource.R.drawable.settings_icon),
                contentDescription = stringResource(R.string.settings_button_description)
            )
        }
    }
}