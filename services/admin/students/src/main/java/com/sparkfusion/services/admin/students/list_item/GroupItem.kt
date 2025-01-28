package com.sparkfusion.services.admin.students.list_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.portdomainservices.admin.portstudents.model.GroupModel

@Composable
fun GroupItem(
    modifier: Modifier = Modifier,
    item: GroupModel,
    specialityName: String,
    onItemClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .clickable { onItemClick() }
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            SFProRoundedText(
                modifier = Modifier.padding(start = 12.dp),
                content = item.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )

            SFProRoundedText(
                modifier = Modifier.padding(end = 12.dp),
                content = "${item.course} course",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        SFProRoundedText(
            modifier = Modifier.padding(start = 12.dp),
            content = specialityName,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = descriptionColor()
        )
    }
}
