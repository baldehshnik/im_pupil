package com.sparkfusion.features.pupil.home.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.pupil.home.R

@Composable
internal fun TopComponent(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(vertical = 12.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(40.dp),
            model = R.drawable.app_logo,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(6.dp))

        SFProRoundedText(
            content = "Hi, student!",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp
        )

        Spacer(
            modifier = Modifier
                .weight(1f)
                .padding(end = 40.dp)
        )
    }
}
















