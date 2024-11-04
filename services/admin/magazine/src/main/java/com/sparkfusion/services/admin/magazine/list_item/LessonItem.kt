package com.sparkfusion.services.admin.magazine.list_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun LessonItem(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 14.dp)
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        SFProRoundedText(
            modifier = Modifier.padding(start = 12.dp, end = 4.dp),
            content = "1",
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp
        )

        Column(
            modifier = Modifier
        ) {
            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp),
                content = "8:30",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp),
                content = "9:50",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        Column(
            modifier = Modifier
        ) {
            SFProRoundedText(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .width(140.dp),
                content = "MSIS",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp),
                content = "lection",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = descriptionColor()
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        SFProRoundedText(
            modifier = Modifier
                .align(Alignment.Top)
                .width(70.dp)
                .padding(end = 2.dp),
            content = "2/402",
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End
        )
    }
}
















