package com.sparkfusion.services.admin.schedule.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.R
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar

@Composable
fun ScheduleGroupScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onScheduleClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            TopBar(title = "Schedule", onBackClick = onBackClick)

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                content = "Group",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            OutlinedTextField(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 2.dp),
                value = "",
                onValueChange = {},
                placeholder = {
                    SFProRoundedText(
                        fontWeight = FontWeight.Medium,
                        content = "Start enter here"
                    )
                },
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.padding(end = 4.dp),
                        onClick = { }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_arrow_drop_down),
                            contentDescription = null
                        )
                    }
                }
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 2.dp, start = 24.dp, end = 24.dp),
                content = "Select a group to view information*",
                color = descriptionColor()
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    modifier = Modifier.padding(top = 30.dp),
                    onClick = { }
                ) {
                    SFProRoundedText(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 2.dp),
                        content = "Search",
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 40.dp, bottom = 12.dp),
                content = "Schedule",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        items(2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 4.dp, bottom = 4.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .width(280.dp)
                        .padding(top = 16.dp, bottom = 16.dp, start = 16.dp)
                ) {
                    SFProRoundedText(
                        modifier = Modifier,
                        content = "Main schedule (current)",
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold
                    )

                    SFProRoundedText(
                        modifier = Modifier,
                        content = "Duration: 06.18.2024 - 06.20.2024",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = descriptionColor()
                    )
                }

                IconButton(
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = { onScheduleClick() }
                ) {
                    Icon(
                        painter = painterResource(id = com.sparkfusion.core.design.R.drawable.round_arrow_forward),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScheduleGroupScreenPreview() {
    ScheduleGroupScreen(
        onBackClick = {},
        onScheduleClick = {}
    )
}
















