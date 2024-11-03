package com.sparkfusion.services.admin.sections.screen

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RangeSlider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar

@Composable
fun AddSectionScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val courseRange by remember { mutableStateOf(1f..6f) }
    val course = "course"
    val default = "all"

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(
                title = "Adding",
                onBackClick = onBackClick
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
                    .padding(start = 24.dp, end = 72.dp, top = 2.dp),
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
                content = "Trainer*",
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
                        content = "Enter here"
                    )
                }
            )

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
                content = "Gender*",
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
                SFProRoundedText(content = "Men", fontSize = 16.sp)

                Spacer(modifier = Modifier.width(16.dp))

                Checkbox(
                    checked = false,
                    onCheckedChange = { }
                )
                SFProRoundedText(content = "Women", fontSize = 16.sp)
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 12.dp),
                content = "Price*",
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

                SFProRoundedText(content = "Free", fontSize = 16.sp)
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 12.dp),
                content = "Courses",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val start = courseRange.start.toInt()
                    val end = courseRange.endInclusive.toInt()
                    SFProRoundedText(
                        content = if (start == 0) default else "$start $course",
                        fontWeight = FontWeight.SemiBold
                    )
                    SFProRoundedText(
                        content = if (end == 80) default else "$end $course",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                RangeSlider(
                    value = 1f..6f,
                    onValueChange = { },
                    valueRange = 1f..6f,
                    steps = 5
                )
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 12.dp),
                content = "Description",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(start = 24.dp, end = 24.dp, top = 2.dp),
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    SFProRoundedText(
                        content = "Enter here"
                    )
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { }) {
                    SFProRoundedText(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp),
                        content = "Save",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AddSectionScreenPreview() {
    AddSectionScreen(
        onBackClick = {}
    )
}


























