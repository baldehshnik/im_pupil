package com.sparkfusion.services.pupil.session.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.sparkfusion.portdomainservices.pupil.portsession.SessionModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun TestItemComponent(
    modifier: Modifier = Modifier,
    item: SessionModel,
    index: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 6.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(24.dp)
            )
            .clip(RoundedCornerShape(24.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SFProRoundedText(
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp, start = 28.dp, end = 20.dp),
            content = "$index",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        Column {
            SFProRoundedText(
                modifier = Modifier.width(200.dp),
                content = item.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            SFProRoundedText(
                modifier = Modifier,
                content = if (item.status == 1) "finished" else "",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = descriptionColor()
            )
        }

        Column(
            modifier = Modifier.padding(end = 20.dp),
            horizontalAlignment = Alignment.End
        ) {
            SFProRoundedText(
                modifier = Modifier,
                content = item.audience,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                textAlign = TextAlign.End
            )

            SFProRoundedText(
                modifier = Modifier,
                content = item.dateTime.toFormattedDateString(),
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                textAlign = TextAlign.End
            )
        }
    }
}

private fun Instant.toFormattedDateString(): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy").withZone(ZoneId.of("UTC"))
    return formatter.format(this)
}













