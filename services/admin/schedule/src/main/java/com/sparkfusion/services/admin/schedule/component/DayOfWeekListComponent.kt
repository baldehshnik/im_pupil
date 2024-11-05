package com.sparkfusion.services.admin.schedule.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sparkfusion.services.admin.schedule.list_item.DayOfWeekItem

@Composable
fun DayOfWeekListComponent(
    modifier: Modifier = Modifier,
    daysOfWeek: Array<String>
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(daysOfWeek) { day ->
            DayOfWeekItem(
                day = day
            )
        }
    }
}






















