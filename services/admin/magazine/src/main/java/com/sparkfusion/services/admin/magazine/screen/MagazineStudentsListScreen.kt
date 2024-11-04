package com.sparkfusion.services.admin.magazine.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.magazine.R

@Composable
fun MagazineStudentsListScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onStudentClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(
                title = "Lesson details",
                onBackClick = onBackClick,
                buttons = {
                    IconButton(onClick = {  }) {
                        Icon(
                            painter = painterResource(id = R.drawable.users_icon),
                            contentDescription = null
                        )
                    }
                }
            )
        }

        items(8) {
            Row(
                modifier = modifier
                    .padding(vertical = 6.dp, horizontal = 12.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { onStudentClick() }
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .size(64.dp)
                )

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    SFProRoundedText(
                        content = "Shcherba Vladislav",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    SFProRoundedText(
                        modifier = Modifier.padding(top = 4.dp),
                        content = "Code: 000722",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = descriptionColor()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MagazineStudentsListScreenPreview() {
    MagazineStudentsListScreen(
        onBackClick = {},
        onStudentClick = {}
    )
}




















