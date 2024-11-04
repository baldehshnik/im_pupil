package com.sparkfusion.services.admin.magazine.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.R
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.magazine.list_item.LessonItem

@Composable
fun MagazineScheduleScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: () -> Unit,
    onHistoryClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(title = "Schedule", onBackClick = onBackClick)

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 20.dp),
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
                content = "Select a group to view the schedule*",
                color = descriptionColor()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 40.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SFProRoundedText(
                    modifier = Modifier,
                    content = "Today's schedule",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                SFProRoundedText(
                    modifier = Modifier,
                    content = "Today's schedule",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        items(3) {
            LessonItem(
                onItemClick = onItemClick
            )
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                TextButton(onClick = onHistoryClick) {
                    SFProRoundedText(
                        modifier = Modifier,
                        content = "Student history",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MagazineScheduleScreenPreview() {
    MagazineScheduleScreen(
        onBackClick = {},
        onItemClick = {},
        onHistoryClick = {}
    )
}


























