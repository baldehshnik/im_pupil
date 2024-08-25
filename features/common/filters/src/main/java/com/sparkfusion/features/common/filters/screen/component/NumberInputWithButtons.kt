package com.sparkfusion.features.common.filters.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sparkfusion.features.common.filters.R

@Composable
fun NumberInputWithButtons(
    modifier: Modifier = Modifier,
    maxAmount: MutableState<Int>
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { maxAmount.value-- },
            enabled = maxAmount.value > 0
        ) {
            Text(stringResource(R.string.minus))
        }

        OutlinedTextField(
            shape = RoundedCornerShape(20.dp),
            value = maxAmount.value.toString(),
            onValueChange = {
                val newValue = it.toIntOrNull()
                if (newValue != null) {
                    maxAmount.value = newValue
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .width(180.dp)
                .clip(RoundedCornerShape(20.dp)),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.round_filter_list),
                    contentDescription = stringResource(R.string.max_amount_icon_description)
                )
            }
        )

        Button(onClick = { maxAmount.value++ }) {
            Text(stringResource(R.string.plus))
        }
    }
}