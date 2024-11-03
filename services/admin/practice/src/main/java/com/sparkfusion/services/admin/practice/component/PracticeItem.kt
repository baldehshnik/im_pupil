package com.sparkfusion.services.admin.practice.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun PracticeItem(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onItemClick() }
            .padding(horizontal = 24.dp, vertical = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(112.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.LightGray)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 2.dp)
        ) {
            SFProRoundedText(
                modifier = Modifier.width(220.dp),
                content = "Title",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            SFProRoundedText(
                modifier = Modifier.width(220.dp),
                content = "Unpaid",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = descriptionColor()
            )

            SFProRoundedText(
                modifier = Modifier
                    .width(220.dp)
                    .padding(top = 4.dp),
                content = "fdsknfjs fsd g sd g sd gsdgsdgsd gsdgsdgs gsdg s dgsd g sdgsdgsdgg dsfdfsdfsdfsdfsd",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

















