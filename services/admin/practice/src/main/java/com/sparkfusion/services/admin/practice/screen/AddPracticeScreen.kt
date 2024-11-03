package com.sparkfusion.services.admin.practice.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.practice.R

@Composable
fun AddPracticeScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(
                title = "Adding",
                onBackClick = onBackClick,
                buttons = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.globe_icon),
                            contentDescription = null
                        )
                    }
                }
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .size(112.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray)
                )
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
                content = "Title*",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 2.dp),
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    SFProRoundedText(
                        content = "Enter here (max 24 symbols)"
                    )
                }
            )

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
                content = "Work type*",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = { }
                )
                SFProRoundedText(content = "Remote", fontSize = 16.sp)

                Spacer(modifier = Modifier.width(16.dp))

                Checkbox(
                    checked = false,
                    onCheckedChange = { }
                )
                SFProRoundedText(content = "Office", fontSize = 16.sp)
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
                content = "Payability*",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = false,
                    onClick = {}
                )
                SFProRoundedText(content = "Paid", fontSize = 16.sp)

                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(
                    selected = true,
                    onClick = {}
                )
                SFProRoundedText(content = "Unpaid", fontSize = 16.sp)
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
                content = "Relocation support",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }

        items(1) {
            Row(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SFProRoundedText(
                    content = "1.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 40.dp, top = 8.dp),
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(16.dp),
                    placeholder = {
                        SFProRoundedText(
                            content = "Enter country or city"
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.plus_icon),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                )
            }
        }

        item {
            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
                content = "Description",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(start = 24.dp, end = 40.dp, top = 8.dp),
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    SFProRoundedText(
                        content = "Enter here"
                    )
                }
            )

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
                content = "Information blocks",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }

        items(2) {
            Card(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 16.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SFProRoundedText(
                        modifier = Modifier,
                        content = "Block title",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )

                    OutlinedTextField(
                        shape = RoundedCornerShape(16.dp),
                        value = "",
                        onValueChange = { },
                        label = {
                            SFProRoundedText(
                                content = "Enter block name"
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    SFProRoundedText(
                        modifier = Modifier,
                        content = "Block content",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )

                    OutlinedTextField(
                        shape = RoundedCornerShape(16.dp),
                        value = "",
                        onValueChange = { },
                        label = {
                            SFProRoundedText(
                                content = "Enter block information"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                    )
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(
                    onClick = { }
                ) {
                    SFProRoundedText(
                        content = "Add new block",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Button(
                    modifier = Modifier
                        .width(120.dp)
                        .padding(bottom = 20.dp),
                    onClick = { }
                ) {
                    SFProRoundedText(
                        content = "Save",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AddPracticeScreenPreview() {
    AddPracticeScreen(
        onBackClick = {}
    )
}


























