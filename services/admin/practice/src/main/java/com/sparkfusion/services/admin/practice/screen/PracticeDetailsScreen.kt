package com.sparkfusion.services.admin.practice.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.practice.R

@Composable
fun PracticeDetailsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
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
                            painter = painterResource(id = R.drawable.delete_icon),
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SFProRoundedText(
                    modifier = Modifier,
                    content = ".NET Developer",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.link_icon),
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .fillMaxWidth()
            ) {
                SFProRoundedText(
                    modifier = Modifier.align(Alignment.Center),
                    content = "Unpaid",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = descriptionColor()
                )
            }

            Row(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(26.dp),
                    painter = painterResource(id = R.drawable.marker_icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(12.dp))

                SFProRoundedText(
                    modifier = Modifier,
                    content = "Distant work\\Office work",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(26.dp),
                    painter = painterResource(id = R.drawable.world_icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(12.dp))

                SFProRoundedText(
                    modifier = Modifier,
                    content = "We will support your move - Bulgaria; Georgia; Lithuania; Poland",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }

            SFProRoundedText(
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
                content = "ISsoft, a subsidiary of Coherent Solutions, is a digital product engineering company focused on empowering business success",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        }

        items(1) {
            Column(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)
            ) {
                SFProRoundedText(
                    content = "Company Background",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )

                SFProRoundedText(
                    modifier = Modifier.padding(top = 4.dp),
                    content = "Renowned for their unparalleled diagnostic and pharmaceutical capabilities, our client is committed to improving global health outcomes.",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = onEditClick) {
                    SFProRoundedText(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp),
                        content = "Edit",
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
private fun PracticeDetailsScreenPreview() {
    PracticeDetailsScreen(
        onBackClick = {},
        onEditClick = {}
    )
}























