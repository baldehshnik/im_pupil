package com.sparkfusion.features.common.filters.screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sparkfusion.features.common.filters.R
import com.sparkfusion.features.common.filters.entity.GenderTypeEntity

@Composable
fun getBorderStrokeByGender(
    isGenderTheSame: Boolean
): BorderStroke {
    return BorderStroke(
        if (isGenderTheSame) 2.dp else 1.dp,
        if (isGenderTheSame) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
    )
}

@Composable
fun GenderComponent(
    isDarkModeEnabled: Boolean,
    genderType: MutableState<GenderTypeEntity>
) {
    Row(modifier = Modifier.padding(top = 12.dp)) {
        GenderCardComponent(
            isDarkModeEnabled = isDarkModeEnabled,
            drawableId = R.drawable.girls,
            drawableDescription = stringResource(R.string.girls_icon_description),
            content = stringResource(R.string.girls),
            borderStroke = getBorderStrokeByGender(genderType.value == GenderTypeEntity.GirlGender),
            onClick = { genderType.value = GenderTypeEntity.GirlGender }
        )

        Spacer(modifier = Modifier.weight(1f))

        GenderCardComponent(
            isDarkModeEnabled = isDarkModeEnabled,
            drawableId = R.drawable.boys,
            drawableDescription = stringResource(R.string.boys_icon_description),
            content = stringResource(R.string.boys),
            borderStroke = getBorderStrokeByGender(genderType.value == GenderTypeEntity.BoyGender),
            onClick = { genderType.value = GenderTypeEntity.BoyGender }
        )
    }

    GenderCardComponent(
        modifier = Modifier.padding(top = 12.dp),
        isDarkModeEnabled = isDarkModeEnabled,
        drawableId = R.drawable.any,
        drawableDescription = stringResource(R.string.any_icon_description),
        content = stringResource(R.string.any),
        borderStroke = getBorderStrokeByGender(genderType.value == GenderTypeEntity.AnyGender),
        onClick = { genderType.value = GenderTypeEntity.AnyGender }
    )
}