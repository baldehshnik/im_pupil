package com.sparkfusion.services.pupil.magazine.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.magazine.component.SpinnerWithTitleComponent
import com.sparkfusion.services.pupil.magazine.viewmodel.HistoryDateViewModel

@Composable
fun HistoryDateScreenEnter(
    modifier: Modifier = Modifier,
    groupMemberId: Int?,
    onSearchClick: (id: Int, date: Long) -> Unit,
    onBackClick: () -> Unit
) {
    HistoryDateScreen(
        modifier = modifier,
        groupMemberId = groupMemberId,
        onSearchClick = onSearchClick,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HistoryDateScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryDateViewModel = hiltViewModel(),
    groupMemberId: Int?,
    onSearchClick: (id: Int, date: Long) -> Unit,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        if (groupMemberId == null) {
            viewModel.readGroupMembers()
        } else {
            if (state.groupMemberId != -1) return@LaunchedEffect
            viewModel.updateGroupMemberId(groupMemberId)
        }
    }

    if (groupMemberId == null) {
        val readingState by viewModel.readingState.collectAsStateWithLifecycle()
        val dateState = rememberDatePickerState(System.currentTimeMillis())

        var expanded by remember { mutableStateOf(false) }

        when (readingState) {
            HistoryDateViewModel.ReadingState.Error -> {
                ShowToast(value = "Error")
                viewModel.clearReadingState()
            }

            HistoryDateViewModel.ReadingState.Initial -> {}
            HistoryDateViewModel.ReadingState.Progress -> {
                CircularProgressIndicator()
            }

            HistoryDateViewModel.ReadingState.Empty -> {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    TopBar(title = "History", onBackClick = onBackClick)

                    SFProRoundedText(content = "Students were not found")
                }
            }

            is HistoryDateViewModel.ReadingState.Success -> {
                val data = (readingState as HistoryDateViewModel.ReadingState.Success).data
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    TopBar(title = "History", onBackClick = onBackClick)

                    val currentItem = data.find { it.id == state.groupMemberId }
                    val current =
                        currentItem?.lastname + " " + currentItem?.firstname + " " + currentItem?.patronymic
                    SpinnerWithTitleComponent(
                        title = "Select the student for whom you want to get a history:",
                        items = data.map {
                            it.lastname + " " + it.firstname + " " + it.patronymic
                        },
                        onDismiss = { expanded = false },
                        expanded = expanded,
                        currentItem = current,
                        onItemClick = { value ->
                            val id = data.find {
                                (it.lastname + " " + it.firstname + " " + it.patronymic) == value
                            }?.id

                            if (id != null) viewModel.updateGroupMemberId(id)
                        },
                        onExpanded = { expanded = true }
                    )

                    SFProRoundedText(
                        modifier = Modifier
                            .padding(start = 24.dp, top = 12.dp, end = 24.dp),
                        content = "Select the date you want to view history for:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )

                    DatePicker(
                        state = dateState,
                        colors = DatePickerDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    )

                    Button(
                        modifier = Modifier
                            .padding(bottom = 20.dp, top = 8.dp)
                            .align(Alignment.CenterHorizontally),
                        onClick = {
                            onSearchClick(
                                state.groupMemberId,
                                dateState.selectedDateMillis ?: System.currentTimeMillis()
                            )
                        }
                    ) {
                        SFProRoundedText(
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp),
                            content = "Search",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    } else {
        val dateState = rememberDatePickerState(System.currentTimeMillis())
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            TopBar(title = "History", onBackClick = onBackClick)

            SFProRoundedText(
                modifier = Modifier
                    .padding(start = 24.dp, top = 12.dp, end = 24.dp),
                content = "Select the date you want to view history for:",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )

            DatePicker(
                state = dateState,
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )

            Button(
                modifier = Modifier
                    .padding(bottom = 20.dp, top = 8.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    onSearchClick(
                        state.groupMemberId,
                        dateState.selectedDateMillis ?: System.currentTimeMillis()
                    )
                }
            ) {
                SFProRoundedText(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp),
                    content = "Search",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}






















