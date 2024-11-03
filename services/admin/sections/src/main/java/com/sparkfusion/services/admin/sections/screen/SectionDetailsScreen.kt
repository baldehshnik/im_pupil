package com.sparkfusion.services.admin.sections.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.sections.R

@Composable
fun SectionDetailsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(
                title = "Details",
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                SFProRoundedText(
                    modifier = Modifier,
                    content = "Boxing",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }

            Row(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(26.dp),
                    painter = painterResource(id = R.drawable.gender_icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(12.dp))

                SFProRoundedText(
                    modifier = Modifier,
                    content = "Gender: Men/Women",
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
                    painter = painterResource(id = R.drawable.trophy_icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(12.dp))

                SFProRoundedText(
                    modifier = Modifier,
                    content = "Trainer: Shcherba Vladislav Dmitrievich",
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
                    painter = painterResource(id = R.drawable.usd_icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(12.dp))

                SFProRoundedText(
                    modifier = Modifier,
                    content = "Price: Free",
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
                    painter = painterResource(id = R.drawable.users_icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(12.dp))

                SFProRoundedText(
                    modifier = Modifier,
                    content = "Courses: 1-3 courses",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 30.dp),
                content = "To enroll in sports sections by sport, you must contact directly the sports coach or the head of the sports club N.L. Bondareva.",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = onEditClick) {
                    SFProRoundedText(
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 2.dp),
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
private fun SectionDetailsScreenPreview() {
    SectionDetailsScreen(
        onBackClick = {},
        onEditClick = {}
    )
}






















