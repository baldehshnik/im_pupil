package com.sparkfusion.services.admin.magazine.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.magazine.R
import com.sparkfusion.services.admin.magazine.component.AccountComponent
import com.sparkfusion.services.admin.magazine.component.AccountInfoComponent
import com.sparkfusion.services.admin.magazine.component.SpinnerWithTitleComponent

@Composable
fun MagazineStudentDetailsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onHistoryClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(
                title = "Student details",
                onBackClick = onBackClick,
                buttons = {
                    IconButton(onClick = onHistoryClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.document_icon),
                            contentDescription = null
                        )
                    }
                }
            )

            AccountComponent(
                name = "Shcherba Vladislav Dmitrievich",
                status = "student"
            )

            AccountInfoComponent(
                modifier = Modifier.padding(top = 20.dp, start = 24.dp),
                title = "Faculty",
                value = "Faculty of Electronic Information Systems"
            )

            AccountInfoComponent(
                modifier = Modifier.padding(top = 12.dp, start = 24.dp),
                title = "Speciality",
                value = "Programmable mobile systems"
            )

            SpinnerWithTitleComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 80.dp),
                title = "Status"
            )

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 40.dp, bottom = 8.dp),
                content = "Weekly statistics:",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        items(7) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                    .fillMaxWidth()
            ) {
                SFProRoundedText(
                    modifier = Modifier
                        .width(280.dp)
                        .padding(start = 20.dp, top = 12.dp, bottom = 12.dp),
                    content = "Monday (3 passes)",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )

                IconButton(
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.angle_right_icon),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MagazineStudentDetailsScreenPreview() {
    MagazineStudentDetailsScreen(
        onBackClick = {},
        onHistoryClick = {}
    )
}




















