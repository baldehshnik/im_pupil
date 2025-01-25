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
import com.sparkfusion.features.admin.confirmation.navigator.IAdminConfirmationNavigator
import com.sparkfusion.features.admin.confirmation.viewModel.AdminConfirmationViewModel

@Composable
fun AdminConfirmationScreen(
    modifier: Modifier = Modifier,
    viewModel: AdminConfirmationViewModel = hiltViewModel(),
    navigator: IAdminConfirmationNavigator
) {
    LaunchedEffect(Unit) {
        viewModel.readUnconfirmedAdmins()
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()
    val confirmState by viewModel.confirmState.collectAsStateWithLifecycle()

    var showDialog by remember { mutableStateOf(false) }
    var currentAdminId by remember { mutableIntStateOf(-1) }

    when (confirmState) {
        AdminConfirmationViewModel.ConfirmState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearConfirmState()
        }

        AdminConfirmationViewModel.ConfirmState.Initial -> {}
        AdminConfirmationViewModel.ConfirmState.Progress -> {
            ShowToast(value = "Confirmation...")
        }
    }

    if (!showDialog) {
        currentAdminId = -1
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
                    content = "Do you really want to confirm the registration of this administrator?",
                    fontWeight = FontWeight.Medium
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (currentAdminId != -1) {
                            viewModel.confirmAdmin(currentAdminId)
                            currentAdminId = -1
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
            AdminConfirmationViewModel.ReadingState.Error -> {
                item { ShowToast(value = "Error") }
                viewModel.clearReadingState()
            }

            AdminConfirmationViewModel.ReadingState.Initial -> {}
            AdminConfirmationViewModel.ReadingState.Progress -> {
                item { CircularProgressIndicator() }
            }

            is AdminConfirmationViewModel.ReadingState.Success -> {
                val data = (readingState as AdminConfirmationViewModel.ReadingState.Success).data
                items(data) {
                    AdminItem(
                        admin = it,
                        onItemClick = {
                            currentAdminId = it.id
                            showDialog = true
                        }
                    )
                }
            }
        }
    }
}
























