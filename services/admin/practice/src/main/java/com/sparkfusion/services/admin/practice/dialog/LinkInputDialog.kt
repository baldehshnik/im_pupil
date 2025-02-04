package com.sparkfusion.services.admin.practice.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun LinkInputDialog(
    value: String,
    onLinkSubmit: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var link by remember { mutableStateOf(value) }

    AlertDialog(
        onDismissRequest = {
            onDismiss()
            link = ""
        },
        title = { SFProRoundedText(content = "Введите ссылку") },
        text = {
            Column {
                TextField(
                    value = link,
                    onValueChange = { link = it },
                    label = { SFProRoundedText(content = "Ссылка") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onLinkSubmit(link)
                    link = ""
                    onDismiss()
                }
            ) {
                SFProRoundedText(content = "Подтвердить")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
                link = ""
            }) {
                SFProRoundedText(content = "Отмена")
            }
        }
    )
}