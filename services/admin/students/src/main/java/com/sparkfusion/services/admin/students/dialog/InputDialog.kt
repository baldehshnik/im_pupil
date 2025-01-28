package com.sparkfusion.services.admin.students.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.portdomainservices.admin.portstudents.model.CreateGroupMemberModel

@Composable
fun InputDialog(
    onDismiss: () -> Unit,
    onSave: (CreateGroupMemberModel) -> Unit
) {
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var patronymic by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }
    var showToast by remember { mutableStateOf(false) }

    if (showToast) {
        ShowToast(value = errorMessage)
        showToast = false
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { SFProRoundedText(content = "Введите данные") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = firstname,
                    onValueChange = { firstname = it },
                    label = { SFProRoundedText(content = "Имя") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = lastname,
                    onValueChange = { lastname = it },
                    label = { SFProRoundedText(content = "Фамилия") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = patronymic,
                    onValueChange = { patronymic = it },
                    label = { SFProRoundedText(content = "Отчество") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = code,
                    onValueChange = { code = it },
                    label = { SFProRoundedText(content = "Код") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (firstname.length < 2) {
                    errorMessage = "Firstname is too short"
                    showToast = true
                } else if (lastname.length < 2) {
                    errorMessage = "Lastname is too short"
                    showToast = true
                } else if (patronymic.length < 2) {
                    errorMessage = "Patronymic is too short"
                    showToast = true
                } else if (code.isEmpty()) {
                    errorMessage = "Code is empty"
                    showToast = true
                } else {
                    val model = CreateGroupMemberModel(firstname, lastname, patronymic, code)
                    onSave(model)
                    onDismiss()
                }
            }
            ) {
                SFProRoundedText(content = "Сохранить")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                SFProRoundedText(content = "Отмена")
            }
        }
    )
}