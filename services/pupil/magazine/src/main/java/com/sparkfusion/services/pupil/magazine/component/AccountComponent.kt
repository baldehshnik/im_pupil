package com.sparkfusion.services.pupil.magazine.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun AccountComponent(
    modifier: Modifier = Modifier,
    name: String,
    status: String
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(start = 24.dp, top = 20.dp)
                .size(112.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(Color.Gray)
        )

        Column(
            modifier = Modifier.padding(vertical = 28.dp, horizontal = 12.dp)
        ) {
            SFProRoundedText(
                content = name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 2.dp),
                content = status,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = descriptionColor()
            )
        }
    }
}
