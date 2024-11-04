package com.sparkfusion.services.admin.magazine.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.R
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun SpinnerComponent(
    modifier: Modifier = Modifier,
    defaultValue: String
) {
    Row(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp, top = 4.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHighest),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SFProRoundedText(
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, start = 16.dp),
            content = defaultValue,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        IconButton(
            modifier = Modifier.padding(end = 8.dp),
            onClick = {  }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_drop_down),
                contentDescription = null
            )
        }
    }
}
