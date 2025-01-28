package com.sparkfusion.services.admin.students.list_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.portdomainservices.admin.portstudents.model.ReadGroupMemberModel

@Composable
fun StudentItem(
    modifier: Modifier = Modifier,
    groupMemberModel: ReadGroupMemberModel,
    onItemClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(vertical = 6.dp, horizontal = 12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable { onItemClick() }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            model = groupMemberModel.pupil?.icon
                ?: com.sparkfusion.core.resource.R.drawable.students_service_icon,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            SFProRoundedText(
                content = "${groupMemberModel.lastname} ${groupMemberModel.firstname}",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 4.dp),
                content = "Code: ${groupMemberModel.code}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = descriptionColor()
            )
        }
    }
}
