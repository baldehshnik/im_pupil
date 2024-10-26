package com.sparkfusion.services.admin.students.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.students.R
import com.sparkfusion.services.admin.students.list_item.StudentItem

@Composable
fun StudentsScreen(
    modifier: Modifier = Modifier,
    groupId: Int
) {
    LazyColumn {
        item {
            TopBar(
                title = "Students",
                onBackClick = {},
                buttons = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_edit),
                            contentDescription = null
                        )
                    }
                }
            )
        }

        items(10) {
            StudentItem()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StudentsScreenPreview() {
    StudentsScreen(
        groupId = 1
    )
}





















