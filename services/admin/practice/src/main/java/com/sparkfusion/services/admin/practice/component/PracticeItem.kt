package com.sparkfusion.services.admin.practice.component

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.portdomainservices.admin.portpractice.model.ReadListPracticeModel

@Composable
fun PracticeItem(
    modifier: Modifier = Modifier,
    model: ReadListPracticeModel,
    onItemClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onItemClick() }
            .padding(horizontal = 24.dp, vertical = 6.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(112.dp)
                .clip(RoundedCornerShape(12.dp)),
            model = model.icon,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 2.dp)
        ) {
            SFProRoundedText(
                modifier = Modifier.width(220.dp),
                content = model.title,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            SFProRoundedText(
                modifier = Modifier.width(220.dp),
                content = if (model.payAbility) "Paid" else "Unpaid",
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
                content = model.description,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

















