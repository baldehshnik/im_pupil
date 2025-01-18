package com.sparkfusion.features.admin.sign_up.screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.sparkfusion.core.resource.color.fieldBorderColor
import com.sparkfusion.core.widget.R
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.domain.admin.port.portauth.InstitutionModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OutlinedDropDownMenu(
    modifier: Modifier = Modifier,
    items: List<InstitutionModel>,
    selectedItem: InstitutionModel?,
    onItemSelected: (InstitutionModel) -> Unit,
    onValueChange: (String) -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()
    val boxSize = remember { mutableStateOf(Size.Zero) }
    val textState = remember { mutableStateOf(selectedItem?.toString() ?: "") }
    var expanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var timerJob: Job? by remember { mutableStateOf(null) }
    var isFocused by remember { mutableStateOf(false) }

    var selected by remember { mutableStateOf(false) }
    LaunchedEffect(textState.value, isFocused) {
        timerJob?.cancel()
        if (!selected && isFocused && textState.value.isNotEmpty()) {
            timerJob = coroutineScope.launch {
                delay(800)
                expanded = true
            }
        } else {
            expanded = false
        }
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
                onValueChange(textState.value)
            },
            placeholder = { SFProRoundedText(content = "Enter name...") },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { layoutCoordinates ->
                    boxSize.value = layoutCoordinates.size.toSize()
                }
                .border(
                    border = BorderStroke(
                        width = 2.dp,
                        color = fieldBorderColor(
                            isFocused = isFocused,
                            isDarkTheme = isDarkTheme
                        )
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            trailingIcon = {
                Icon(
                    if (expanded) painterResource(R.drawable.baseline_arrow_drop_up)
                    else painterResource(R.drawable.round_arrow_drop_down),
                    contentDescription = stringResource(R.string.drop_menu_icon_description)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )

        val dropdownMenuWidth = LocalDensity.current.run { boxSize.value.width.toDp() }
        DropdownMenu(
            modifier = Modifier.sizeIn(minWidth = dropdownMenuWidth),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { SFProRoundedText(content = item.name) },
                    onClick = {
                        textState.value = item.name
                        onItemSelected(item)
                        expanded = false
                        selected = true
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutlinedDropDownMenuPreview() {
    val mockItems = listOf(
        InstitutionModel("Institution 1", "", 1),
        InstitutionModel("Institution 2", "", 1),
        InstitutionModel("Institution 3", "", 1)
    )

    var selectedItem by remember { mutableStateOf<InstitutionModel?>(null) }

    OutlinedDropDownMenu(
        items = mockItems,
        selectedItem = selectedItem,
        onItemSelected = { selectedItem = it },
        onValueChange = {}
    )
}














