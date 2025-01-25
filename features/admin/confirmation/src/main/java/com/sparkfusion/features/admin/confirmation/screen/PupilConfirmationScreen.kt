package com.sparkfusion.features.admin.confirmation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.features.admin.confirmation.navigator.IPupilConfirmationNavigator
import com.sparkfusion.features.admin.confirmation.viewModel.PupilConfirmationViewModel

@Composable
fun PupilConfirmationScreen(
    modifier: Modifier = Modifier,
    viewModel: PupilConfirmationViewModel = hiltViewModel(),
    navigator: IPupilConfirmationNavigator
) {
    LaunchedEffect(Unit) {
        viewModel.readUnconfirmedPupils()
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()
    val confirmState by viewModel.confirmState.collectAsStateWithLifecycle()

    var showDialog by remember { mutableStateOf(false) }
    var currentPupilId by remember { mutableIntStateOf(-1) }

    when (confirmState) {
        PupilConfirmationViewModel.ConfirmState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearConfirmState()
        }
        PupilConfirmationViewModel.ConfirmState.Initial -> {}
        PupilConfirmationViewModel.ConfirmState.Progress -> {
            ShowToast(value = "Confirmation...")
        }
    }

    if (!showDialog) {
        currentPupilId = -1
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                SFProRoundedText(
                    content = "Are you sure?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                SFProRoundedText(
                    content = "Do you really want to confirm the registration of this pupil?",
                    fontWeight = FontWeight.Medium
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (currentPupilId != -1) {
                            viewModel.confirmPupil(currentPupilId)
                            currentPupilId = -1
                        }

                        showDialog = false
                    }
                ) {
                    SFProRoundedText(content = "Confirm")
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = modifier
                    .height(64.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .size(40.dp),
                    onClick = { navigator.popBackStack() }
                ) {
                    Icon(
                        painter = painterResource(com.sparkfusion.core.widget.R.drawable.round_arrow_back),
                        contentDescription = null
                    )
                }
            }
        }

        when (readingState) {
            PupilConfirmationViewModel.ReadingState.Error -> {
                item { ShowToast(value = "Error") }
                viewModel.clearReadingState()
            }

            PupilConfirmationViewModel.ReadingState.Initial -> {}
            PupilConfirmationViewModel.ReadingState.Progress -> {
                item { CircularProgressIndicator() }
            }

            is PupilConfirmationViewModel.ReadingState.Success -> {
                val data = (readingState as PupilConfirmationViewModel.ReadingState.Success).data
                items(data) {
                    PupilItem(
                        pupil = it,
                        onItemClick = {
                            currentPupilId = it.id
                            showDialog = true
                        }
                    )
                }
            }
        }
    }
}






















