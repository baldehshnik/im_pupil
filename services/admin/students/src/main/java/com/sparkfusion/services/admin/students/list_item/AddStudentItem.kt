package com.sparkfusion.services.admin.students.list_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.services.admin.students.R

@Composable
fun AddStudentItem(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp, top = 12.dp)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, start = 16.dp)
        ) {
            SFProRoundedText(
                content = "Shcherba Vladislav Dmitrievich",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            SFProRoundedText(
                content = "Code: 000722",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = descriptionColor()
            )
        }

        IconButton(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterVertically),
            onClick = { }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.round_close),
                contentDescription = null
            )
        }
    }
}
