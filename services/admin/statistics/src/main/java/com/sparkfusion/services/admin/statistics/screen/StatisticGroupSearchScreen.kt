package com.sparkfusion.services.admin.statistics.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.core.widget.R as CoreResource

@Composable
fun StatisticGroupSearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TopBar(title = "Group", onBackClick = onBackClick)

            Spacer(modifier = Modifier.height(20.dp))

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp),
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
                            painter = painterResource(id = CoreResource.drawable.round_arrow_drop_down),
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

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp),
                onClick = { onNextClick() }
            ) {
                SFProRoundedText(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 2.dp),
                    content = "Next",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }

        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            text = {
                SFProRoundedText(content = "Get statistics of faculty")
            },
            icon = {

            },
            onClick = {

            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StatisticGroupSearchScreenPreview() {
    StatisticGroupSearchScreen(
        onBackClick = {},
        onNextClick = {}
    )
}



















