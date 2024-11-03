package com.sparkfusion.services.admin.session.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.R
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.session.component.TestItemComponent

@Composable
fun SessionGroupTestsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onAddItemClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            TopBar(title = "Group", onBackClick = onBackClick)

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 20.dp),
                content = "Group",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            OutlinedTextField(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 2.dp),
                value = "",
                onValueChange = {},
                placeholder = {
                    SFProRoundedText(
                        fontWeight = FontWeight.Medium,
                        content = "Start enter here"
                    )
                },
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.padding(end = 4.dp),
                        onClick = { }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_arrow_drop_down),
                            contentDescription = null
                        )
                    }
                }
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 2.dp, start = 24.dp, end = 24.dp),
                content = "Select a group to view information*",
                color = descriptionColor()
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 40.dp, start = 24.dp, end = 24.dp),
                content = "Tests",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        items(3) {
            TestItemComponent(
                onItemClick = {}
            )
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                TextButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    onClick = onAddItemClick
                ) {
                    SFProRoundedText(
                        content = "Add",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                SFProRoundedText(
                    modifier = Modifier.padding(top = 40.dp, start = 24.dp, end = 24.dp),
                    content = "Exams",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        items(1) {
            TestItemComponent(
                onItemClick = {}
            )
        }

        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)) {
                TextButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.Center),
                    onClick = onAddItemClick
                ) {
                    SFProRoundedText(
                        content = "Add",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SessionGroupTestsScreenPreview() {
    SessionGroupTestsScreen(
        onBackClick = {},
        onAddItemClick = {}
    )
}






















