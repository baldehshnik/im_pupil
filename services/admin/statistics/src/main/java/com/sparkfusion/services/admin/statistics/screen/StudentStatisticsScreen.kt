package com.sparkfusion.services.admin.statistics.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.statistics.component.PassesPerComponent

@Composable
fun StudentStatisticsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TopBar(title = "Student statistics", onBackClick = onBackClick)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.LightGray)
            )

            Column(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                SFProRoundedText(
                    modifier = Modifier.padding(start = 12.dp),
                    content = "Shcherba Vladislav Dmitrievich (000722)",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                SFProRoundedText(
                    modifier = Modifier.padding(start = 12.dp, top = 2.dp),
                    content = "student (registered)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = descriptionColor()
                )
            }
        }

        PassesPerComponent(
            title = "Passes per month",
            value = "2 passes out of 10"
        )

        PassesPerComponent(
            title = "Passes per semester",
            value = "24 passes"
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StudentStatisticsScreenPreview() {
    StudentStatisticsScreen(
        onBackClick = {}
    )
}

















