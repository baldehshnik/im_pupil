package com.sparkfusion.features.admin.home.screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.home.R

@Composable
fun HelloComponent() {
    Spacer(modifier = Modifier.height(30.dp))

    Row(
        modifier = Modifier.padding(start = 24.dp)
    ) {
        SFProRoundedText(
            content = stringResource(R.string.congratulations),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp
        )

        AsyncImage(
            modifier = Modifier
                .padding(start = 6.dp)
                .size(38.dp)
                .rotate(10f),
            model = R.drawable.balloons_icon,
            contentDescription = stringResource(R.string.balloons_image_description)
        )
    }

    SFProRoundedText(
        modifier = Modifier.padding(start = 24.dp),
        content = stringResource(R.string.you_are_now_with_us),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp
    )

    SFProRoundedText(
        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 24.dp),
        content = stringResource(R.string.now_you_can_see_description),
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        color = descriptionColor()
    )
}