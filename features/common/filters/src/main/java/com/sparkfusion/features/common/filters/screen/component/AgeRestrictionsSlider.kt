package com.sparkfusion.features.common.filters.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RangeSlider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.common.filters.R

@Composable
fun AgeRestrictionsSlider(
    modifier: Modifier = Modifier
) {
    var ageRange by remember { mutableStateOf(0f..80f) }
    val years = stringResource(R.string.years)
    val default = stringResource(R.string.default_word)

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val start = ageRange.start.toInt()
            val end = ageRange.endInclusive.toInt()
            SFProRoundedText(
                content = if (start == 0) default else "$start $years",
                fontWeight = FontWeight.SemiBold
            )
            SFProRoundedText(
                content = if (end == 80) default else "$end $years",
                fontWeight = FontWeight.SemiBold
            )
        }

        RangeSlider(
            value = ageRange,
            onValueChange = { ageRange = it },
            valueRange = 0f..80f,
            steps = 79
        )
    }
}