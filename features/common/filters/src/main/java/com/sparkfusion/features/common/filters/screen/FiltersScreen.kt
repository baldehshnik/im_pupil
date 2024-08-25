package com.sparkfusion.features.common.filters.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.common.filters.R
import com.sparkfusion.features.common.filters.entity.GenderTypeEntity
import com.sparkfusion.features.common.filters.entity.genderTypeEntitySaver
import com.sparkfusion.features.common.filters.navigator.IFiltersNavigator
import com.sparkfusion.features.common.filters.screen.component.AgeRestrictionsSlider
import com.sparkfusion.features.common.filters.screen.component.FilterBlock
import com.sparkfusion.features.common.filters.screen.component.GenderComponent
import com.sparkfusion.features.common.filters.screen.component.NumberInputWithButtons

@Composable
fun FiltersScreen(
    navigator: IFiltersNavigator,
    modifier: Modifier = Modifier
) {
    val genderType = rememberSaveable(stateSaver = genderTypeEntitySaver) {
        mutableStateOf(GenderTypeEntity.AnyGender)
    }
    val maxAmount = remember { mutableIntStateOf(0) }

    val scroll = rememberScrollState()
    val isDarkModeEnabled = isSystemInDarkTheme()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scroll)
    ) {
        TopBar(
            title = stringResource(R.string.filters),
            onBackClick = navigator::popBackStack
        )

        Spacer(modifier = Modifier.height(20.dp))

        FilterBlock(title = stringResource(R.string.gender)) {
            GenderComponent(
                isDarkModeEnabled = isDarkModeEnabled,
                genderType = genderType
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        FilterBlock(title = stringResource(R.string.age_restrictions)) {
            AgeRestrictionsSlider(modifier = Modifier.padding(top = 12.dp))

            SFProRoundedText(
                modifier = Modifier.padding(top = 4.dp),
                content = stringResource(R.string.default_age_restrictions_description),
                color = descriptionColor()
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        FilterBlock(title = stringResource(R.string.maximum_amount)) {
            NumberInputWithButtons(
                modifier = Modifier.padding(top = 12.dp),
                maxAmount = maxAmount
            )

            SFProRoundedText(
                modifier = Modifier.padding(top = 4.dp),
                content = stringResource(R.string.default_max_amount_description),
                color = descriptionColor()
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .size(240.dp, 48.dp)
                .align(Alignment.CenterHorizontally),
            onClick = navigator::navigateToHomeScreen
        ) {
            Row(
                modifier = Modifier.padding(end = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.apply_filters_icon),
                    contentDescription = stringResource(R.string.apply_filters_description)
                )

                Spacer(modifier = Modifier.width(16.dp))

                SFProRoundedText(
                    content = stringResource(R.string.apply_filters),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FiltersScreenPreview() {
    FiltersScreen(navigator = object : IFiltersNavigator {
        override fun popBackStack() {}
        override fun navigateToHomeScreen() {}
    })
}