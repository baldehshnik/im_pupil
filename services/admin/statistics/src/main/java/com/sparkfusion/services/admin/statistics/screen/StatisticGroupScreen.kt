package com.sparkfusion.services.admin.statistics.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

@Composable
fun StatisticGroupScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onStudentClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                TopBar(title = "Group", onBackClick = onBackClick)
            }

            items(5) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { onStudentClick() }
                        .padding(horizontal = 24.dp, vertical = 6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.LightGray)
                            .size(64.dp)
                    )

                    Column(
                        modifier = Modifier.padding(start = 12.dp, top = 6.dp)
                    ) {
                        SFProRoundedText(
                            content = "Shcherba Vladislav",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )

                        SFProRoundedText(
                            modifier = Modifier.padding(top = 2.dp),
                            content = "Code: 000722",
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = descriptionColor()
                        )
                    }
                }
            }
        }

        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            text = {
                SFProRoundedText(content = "Get statistics of group")
            },
            icon = {

            },
            onClick = {

            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StatisticGroupScreenPreview() {
    StatisticGroupScreen(
        onBackClick = {},
        onStudentClick = {}
    )
}






















