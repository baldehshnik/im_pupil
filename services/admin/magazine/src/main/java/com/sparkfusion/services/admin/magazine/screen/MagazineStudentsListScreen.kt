package com.sparkfusion.services.admin.magazine.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.magazine.R
import com.sparkfusion.services.admin.magazine.dialog.AdminDialogExample
import com.sparkfusion.services.admin.magazine.viewmodel.StudentsListViewModel

@Composable
fun MagazineStudentsListScreen(
    modifier: Modifier = Modifier,
    viewModel: StudentsListViewModel = hiltViewModel(),
    groupId: Int,
    lessonId: Int,
    onBackClick: () -> Unit,
    onStudentClick: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.read(groupId, lessonId)
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()
    val updatingState by viewModel.updatingState.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()

    var isSelectionMode by remember { mutableStateOf(false) }
    var isDialogOpen by remember { mutableStateOf(false) }

    AdminDialogExample(
        isOpen = isDialogOpen,
        changeOpenValue = {
            isDialogOpen = it
            if (!it) {
                isSelectionMode = false
                viewModel.clearSelectionState()
            }
        },
        onUpdateClick = {
            viewModel.update(lessonId, it)
            isSelectionMode = false
            viewModel.clearSelectionState()
        }
    )

    when (updatingState) {
        StudentsListViewModel.UpdatingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearUpdatingState()
        }

        StudentsListViewModel.UpdatingState.Initial -> {}
    }

    when (readingState) {
        StudentsListViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        StudentsListViewModel.ReadingState.Initial -> {}
        StudentsListViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is StudentsListViewModel.ReadingState.Success -> {
            val data = (readingState as StudentsListViewModel.ReadingState.Success).data
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = modifier.fillMaxWidth()
                ) {
                    item {
                        TopBar(
                            title = "Lesson details",
                            onBackClick = onBackClick,
                            buttons = {
                                IconButton(onClick = {
                                    if (isSelectionMode) {
                                        isSelectionMode = false
                                        viewModel.clearSelectionState()
                                    } else isSelectionMode = true
                                }) {
                                    Icon(
                                        painter = painterResource(
                                            id = if (isSelectionMode) R.drawable.angle_right_icon
                                            else R.drawable.users_icon
                                        ),
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }

                    items(data) {
                        Row(
                            modifier = modifier
                                .padding(vertical = 6.dp, horizontal = 12.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(20.dp))
                                .clickable {
                                    if (isSelectionMode) {
                                        if (state.selectedGroupMembers.contains(it.getGroupMemberDto.id)) {
                                            viewModel.deleteSelectedGroupMember(it.getGroupMemberDto.id)
                                        } else {
                                            viewModel.addSelectedGroupMember(it.getGroupMemberDto.id)
                                        }
                                    } else {
                                        onStudentClick(it.getGroupMemberDto.id)
                                    }
                                }
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
                                    content = "${it.getGroupMemberDto.lastname} ${it.getGroupMemberDto.firstname}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )

                                SFProRoundedText(
                                    modifier = Modifier.padding(top = 4.dp),
                                    content = "Status: " + when (it.getPassDto.status) {
                                        1 -> "Missed"
                                        else -> "Attended"
                                    },
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = descriptionColor()
                                )
                            }

                            if (isSelectionMode) {
                                Spacer(modifier = Modifier.weight(1f))
                                RadioButton(
                                    selected = state.selectedGroupMembers.contains(it.getGroupMemberDto.id),
                                    onClick = {
                                        if (state.selectedGroupMembers.contains(it.getGroupMemberDto.id)) {
                                            viewModel.deleteSelectedGroupMember(it.getGroupMemberDto.id)
                                        } else {
                                            viewModel.addSelectedGroupMember(it.getGroupMemberDto.id)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }

                if (isSelectionMode) {
                    FloatingActionButton(
                        modifier = Modifier
                            .padding(20.dp)
                            .align(Alignment.BottomEnd),
                        onClick = {
                            isDialogOpen = true
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.document_icon),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}






















