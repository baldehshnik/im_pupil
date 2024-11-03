package com.sparkfusion.services.admin.session.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.session.R

@Composable
fun SessionTestAddingScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomEnd),
            onClick = { }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.check_icon),
                contentDescription = null
            )
        }

        Column {
            TopBar(title = "Adding", onBackClick = onBackClick)

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
                content = "Subject name",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 2.dp),
                shape = RoundedCornerShape(16.dp),
                value = "",
                onValueChange = {},
                placeholder = {
                    SFProRoundedText(content = "Enter here...")
                }
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 2.dp, start = 24.dp, end = 24.dp),
                content = "After adding an item, it will be assigned the status: not completed!*",
                color = descriptionColor()
            )

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp),
                content = "Audience",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 2.dp),
                shape = RoundedCornerShape(16.dp),
                value = "",
                onValueChange = {},
                placeholder = {
                    SFProRoundedText(content = "Enter here...")
                }
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 2.dp, start = 24.dp, end = 24.dp),
                content = "The audience can be changed later!*",
                color = descriptionColor()
            )

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp),
                content = "Date and time is very important information, but may change. However, to add an entry, you must specify both the date and time. They may be approximate and may be changed later.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Row(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    SFProRoundedText(
                        content = "Date",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .width(160.dp)
                            .padding(top = 2.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SFProRoundedText(
                            modifier = Modifier.padding(start = 20.dp, top = 6.dp, bottom = 6.dp),
                            content = "No data",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Icon(
                            modifier = Modifier.padding(
                                end = 20.dp,
                                top = 16.dp,
                                bottom = 16.dp,
                                start = 20.dp
                            ),
                            painter = painterResource(id = R.drawable.calendar_icon),
                            contentDescription = null
                        )
                    }
                }

                Column {
                    SFProRoundedText(
                        content = "Time",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .width(160.dp)
                            .padding(top = 2.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SFProRoundedText(
                            modifier = Modifier.padding(start = 20.dp, top = 6.dp, bottom = 6.dp),
                            content = "No data",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Icon(
                            modifier = Modifier.padding(
                                end = 20.dp,
                                top = 16.dp,
                                bottom = 16.dp,
                                start = 20.dp
                            ),
                            painter = painterResource(id = R.drawable.clock_icon),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SessionTestAddingScreenPreview() {
    SessionTestAddingScreen(
        onBackClick = {}
    )
}





















