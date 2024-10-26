package com.sparkfusion.services.admin.students.list_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun StudentItem(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(vertical = 6.dp, horizontal = 12.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Gray)
                .size(64.dp)
        )

        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            SFProRoundedText(
                content = "Shcherba Vladislav",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 4.dp),
                content = "Code: 000722",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = descriptionColor()
            )
        }
    }
}
