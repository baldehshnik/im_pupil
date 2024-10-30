package com.sparkfusion.services.admin.statistics.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sparkfusion.core.design.screen.faculty.FacultySelectionScreen

@Composable
fun StatisticsFacultiesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onFacultyClick: (Int) -> Unit
) {
    FacultySelectionScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onFacultyClick = onFacultyClick
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FacultiesScreenPreview() {
    StatisticsFacultiesScreen(
        onBackClick = {},
        onFacultyClick = {}
    )
}
















