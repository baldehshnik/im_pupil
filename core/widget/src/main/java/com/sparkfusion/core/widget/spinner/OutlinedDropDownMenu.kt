package com.sparkfusion.core.widget.spinner

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.sparkfusion.core.resource.color.fieldBorderColor
import com.sparkfusion.core.widget.R
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun OutlinedDropDownMenu(
    modifier: Modifier = Modifier,
    items: Array<String>,
    item: String,
    onValueChange: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val isDarkTheme = isSystemInDarkTheme()
    val boxSize = remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { layoutCoordinates ->
                boxSize.value = layoutCoordinates.size.toSize()
            }
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = fieldBorderColor(
                        isFocused = expanded.value,
                        isDarkTheme = isDarkTheme
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable { expanded.value = !expanded.value }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 14.dp, horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            SFProRoundedText(
                content = item,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                if (expanded.value) painterResource(R.drawable.baseline_arrow_drop_up)
                else painterResource(R.drawable.round_arrow_drop_down),
                contentDescription = stringResource(R.string.drop_menu_icon_description)
            )
        }

        val dropdownMenuWidth = LocalDensity.current.run { boxSize.value.width.toDp() }
        DropdownMenu(
            modifier = Modifier
                .sizeIn(minWidth = dropdownMenuWidth),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { SFProRoundedText(content = item) },
                    onClick = {
                        onValueChange(item)
                        expanded.value = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OutlinedDropDownMenuPreview() {
    val mutableState = remember { mutableStateOf("Test1") }
    OutlinedDropDownMenu(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp),
        items = arrayOf("Test1", "Test2"),
        item = mutableState.value,
        onValueChange = {}
    )
}