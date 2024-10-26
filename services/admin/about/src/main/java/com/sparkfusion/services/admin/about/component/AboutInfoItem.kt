package com.sparkfusion.services.admin.about.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.services.admin.about.model.AboutBlockModel

@Composable
fun AboutInfoItem(
    modifier: Modifier = Modifier,
    item: AboutBlockModel
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
        if (item.imageUrl.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .background(Color.Gray)
            )
        }

        SFProRoundedText(
            content = item.info,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
