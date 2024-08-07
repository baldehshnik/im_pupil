package com.sparkfusion.features.common.filters.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparkfusion.features.common.filters.navigator.IFiltersNavigator

@Composable
fun FiltersScreen(
    navigator: IFiltersNavigator,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Filters Screen")
    }
}