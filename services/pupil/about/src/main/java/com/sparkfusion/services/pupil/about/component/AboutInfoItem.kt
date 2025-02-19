package com.sparkfusion.services.pupil.about.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.portdomainservices.pupil.portabout.model.AboutModel

@Composable
internal fun AboutInfoItem(
    modifier: Modifier = Modifier,
    item: AboutModel
) {
    Column(
        modifier = modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth(),
            model = item.icon,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        SFProRoundedText(
            content = item.description,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
