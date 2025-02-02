package com.sparkfusion.services.admin.statistics.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.statistics.component.OutlinedDropDownGroupMenu
import com.sparkfusion.services.admin.statistics.viewmodel.GroupSearchViewModel

@Composable
fun StatisticGroupSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: GroupSearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onNextClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val groupReadingState by viewModel.groupReadingState.collectAsStateWithLifecycle()

    var showError by remember { mutableStateOf(false) }

    if (showError) {
        ShowToast(value = "Group now selected")
        showError = false
    }

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

            OutlinedDropDownGroupMenu(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp),
                items = groupReadingState,
                selectedItem = if (groupReadingState.isEmpty()) null else
                    if (state.groupId == -1) groupReadingState[0] else groupReadingState.find { it.id == state.groupId },
                onItemSelected = {
                    viewModel.updateGroupId(it.id)
                },
                onValueChange = {
                    viewModel.updateGroupName(it)
                    viewModel.readGroupsByNamePart()
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
                onClick = {
                    if (state.groupId == -1) {
                        showError = true
                    } else {
                        onNextClick(state.groupId)
                    }
                }
            ) {
                SFProRoundedText(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 2.dp),
                    content = "Next",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }

//        ExtendedFloatingActionButton(
//            modifier = Modifier
//                .padding(16.dp)
//                .align(Alignment.BottomEnd),
//            text = {
//                SFProRoundedText(content = "Get statistics of faculty")
//            },
//            icon = {
//
//            },
//            onClick = {
//
//            }
//        )
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



















