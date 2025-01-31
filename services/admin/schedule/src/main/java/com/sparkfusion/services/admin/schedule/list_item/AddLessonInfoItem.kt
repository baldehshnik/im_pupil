package com.sparkfusion.services.admin.schedule.list_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.portdomainservices.admin.portschedule.model.AddLessonModel
import com.sparkfusion.services.admin.schedule.R

@Composable
fun AddLessonInfoItem(
    color: Color,
    model: AddLessonModel,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(110.dp)
                .background(color, RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            SFProRoundedText(
                modifier = Modifier.padding(top = 2.dp),
                content = "${formatLocalTimeToString(model.start)} - ${formatLocalTimeToString(model.end)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 4.dp),
                content = "${model.name} (" + when (model.type) {
                    1 -> "Lection"
                    2 -> "Practice"
                    else -> "Laboratory"
                } +
                        ")",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 4.dp),
                content = model.teacher,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = descriptionColor()
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 4.dp, bottom = 2.dp),
                content = "Audience: ${model.audience}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = descriptionColor()
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            modifier = Modifier.align(Alignment.Top),
            onClick = { onDeleteClick() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cross_icon),
                contentDescription = null
            )
        }
    }
}




















