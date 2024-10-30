package com.sparkfusion.services.admin.students.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.sparkfusion.services.admin.students.component.FloatingButtonComponent
import com.sparkfusion.services.admin.students.component.SpinnerWithTitleComponent
import com.sparkfusion.services.admin.students.list_item.GroupItem

@Composable
fun GroupScreen(
    modifier: Modifier = Modifier,
    facultyId: Int,
    onBackClick: () -> Unit,
    onAddGroupClick: () -> Unit,
    onGroupClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        FloatingButtonComponent(
            painter = painterResource(id = R.drawable.round_add),
            onClick = onAddGroupClick
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                TopBar(
                    title = "Group",
                    onBackClick = onBackClick
                )

                SpinnerWithTitleComponent(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Speciality"
                )

                SFProRoundedText(
                    modifier = Modifier.padding(start = 24.dp, top = 2.dp),
                    content = "Select a speciality to view existing groups!",
                    color = descriptionColor(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )

                SFProRoundedText(
                    modifier = Modifier.padding(top = 60.dp, start = 24.dp),
                    content = "Existing groups",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }

            items(3) {
                GroupItem(
                    onItemClick = onGroupClick
                )
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GroupScreenPreview() {
    GroupScreen(
        facultyId = 1,
        onBackClick = {},
        onGroupClick = {},
        onAddGroupClick = {}
    )
}

































