package com.sparkfusion.services.admin.students.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.students.R
import com.sparkfusion.services.admin.students.component.AddStudentButtonComponent
import com.sparkfusion.services.admin.students.component.FloatingButtonComponent
import com.sparkfusion.services.admin.students.component.SpinnerWithTitleComponent
import com.sparkfusion.services.admin.students.list_item.AddStudentItem

@Composable
fun EditGroupScreen(
    modifier: Modifier = Modifier,
    groupId: Int,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        FloatingButtonComponent(
            painter = painterResource(id = R.drawable.round_check),
            onClick = {}
        )

        LazyColumn {
            item {
                TopBar(
                    title = "Students",
                    onBackClick = onBackClick,
                    buttons = {
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_delete_outline),
                                contentDescription = null
                            )
                        }
                    }
                )

                SFProRoundedText(
                    modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp),
                    content = "If the field you want to change is not listed below, the only way to change it is by deleting the entry and creating a new one.",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = descriptionColor()
                )

                SpinnerWithTitleComponent(
                    modifier = Modifier.width(260.dp),
                    title = "Course"
                )

                SFProRoundedText(
                    modifier = Modifier.padding(start = 24.dp, top = 2.dp),
                    content = "You cannot downgrade the group rate!*",
                    color = descriptionColor(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )

                SFProRoundedText(
                    modifier = Modifier.padding(top = 30.dp, start = 24.dp),
                    content = "Students",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }

            items(2) {
                AddStudentItem()
            }

            item {
                AddStudentButtonComponent(
                    onClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EditGroupScreenPreview() {
    EditGroupScreen(
        groupId = 1,
        onBackClick = {}
    )
}





















