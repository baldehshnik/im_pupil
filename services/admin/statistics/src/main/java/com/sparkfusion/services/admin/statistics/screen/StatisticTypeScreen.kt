package com.sparkfusion.services.admin.statistics.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.statistics.R

@Composable
fun StatisticTypeScreen(
    modifier: Modifier = Modifier,
    onTypeClick: () -> Unit,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(title = "Statistic", onBackClick = onBackClick)

            Spacer(modifier = Modifier.height(20.dp))
        }

        items(1) {
            Row(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .background(Color.LightGray),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SFProRoundedText(
                    modifier = Modifier
                        .width(240.dp)
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    content = "Passes",
                    fontSize = 18.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis
                )

                IconButton(
                    modifier = Modifier.padding(end = 12.dp),
                    onClick = onTypeClick
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.angle_right),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StatisticTypeScreenPreview() {
    StatisticTypeScreen(
        onBackClick = {},
        onTypeClick = {}
    )
}
























