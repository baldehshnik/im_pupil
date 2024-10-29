package com.sparkfusion.services.admin.statistics.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun PassesPerComponent(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Column(
        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 30.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SFProRoundedText(
                content = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            TextButton(onClick = { }) {
                SFProRoundedText(
                    content = "PDF"
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
        ) {
            SFProRoundedText(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                content = value,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
    }
}
