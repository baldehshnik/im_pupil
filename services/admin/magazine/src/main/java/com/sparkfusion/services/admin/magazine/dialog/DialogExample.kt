package com.sparkfusion.services.admin.magazine.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.services.admin.magazine.component.SpinnerWithTitleComponent

@Composable
fun DialogExample(
    isOpen: Boolean,
    changeOpenValue: (Boolean) -> Unit,
    onUpdateClick: (Int) -> Unit
) {
    val statuses = listOf("Attended", "Missed")
    val currentStatus = remember { mutableIntStateOf(1) }
    var expanded by remember { mutableStateOf(false) }

    if (isOpen) {
        AlertDialog(
            onDismissRequest = { changeOpenValue(false) },
            title = { SFProRoundedText(content = "Вы хотите подтвердить действие?") },
            text = {
                SpinnerWithTitleComponent(
                    title = "Status",
                    items = statuses,
                    onDismiss = { expanded = false },
                    expanded = expanded,
                    currentItem = statuses[if (currentStatus.intValue == 1) 1 else 0],
                    onItemClick = {
                        currentStatus.intValue = if (statuses.indexOf(it) == 1) 1 else -1
                    },
                    onExpanded = { expanded = true }
                )
            },
            confirmButton = {
                Button(onClick = {
                    onUpdateClick(currentStatus.intValue)
                    changeOpenValue(false)
                }) {
                    SFProRoundedText(content = "Confirm")
                }
            },
            dismissButton = {
                Button(onClick = {
                    currentStatus.intValue = 1
                    changeOpenValue(false)
                }) {
                    SFProRoundedText(content = "Cancel")
                }
            }
        )
    }
}







