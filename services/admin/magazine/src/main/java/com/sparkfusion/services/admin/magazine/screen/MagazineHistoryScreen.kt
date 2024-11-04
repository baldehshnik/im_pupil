package com.sparkfusion.services.admin.magazine.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.magazine.list_item.LessonItem

@Composable
fun MagazineHistoryScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(title = "History", onBackClick = onBackClick)

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 12.dp, bottom = 8.dp),
                content = "Missed",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        items(2) {
            LessonItem(onItemClick = {})
        }

        item {
            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 24.dp, bottom = 8.dp),
                content = "Temporary pass",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = descriptionColor()
            )
        }

        items(1) {
            LessonItem(onItemClick = {})
        }

        item {
            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 24.dp, bottom = 8.dp),
                content = "Attended",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = descriptionColor()
            )
        }

        items(1) {
            LessonItem(onItemClick = {})
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MagazineHistoryScreenPreview() {
    MagazineHistoryScreen(
        onBackClick = {}
    )
}























