package com.sparkfusion.services.admin.students.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
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
fun AddGroupScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        FloatingButtonComponent(
            painter = painterResource(id = R.drawable.round_check),
            onClick = {}
        )

        LazyColumn {
            item {
                TopBar(
                    title = "Adding",
                    onBackClick = {}
                )

                SpinnerWithTitleComponent(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Speciality"
                )

                SpinnerWithTitleComponent(
                    modifier = Modifier.width(260.dp),
                    title = "Course"
                )

                SFProRoundedText(
                    modifier = Modifier.padding(top = 40.dp, start = 24.dp),
                    content = "Group",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )

                OutlinedTextField(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, top = 8.dp)
                        .fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(20.dp),
                    placeholder = {
                        SFProRoundedText(
                            content = "Enter name here",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                )

                SFProRoundedText(
                    modifier = Modifier.padding(start = 24.dp, top = 2.dp),
                    content = "This field is unique and cannot be changed once created!*",
                    color = descriptionColor(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )

                SFProRoundedText(
                    modifier = Modifier.padding(top = 40.dp, start = 24.dp),
                    content = "Students",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }

            items(1) {
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
private fun AddGroupScreenPreview() {
    AddGroupScreen(

    )
}
























