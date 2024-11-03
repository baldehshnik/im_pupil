package com.sparkfusion.services.admin.session.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sparkfusion.core.design.screen.faculty.FacultySelectionScreen

@Composable
fun SessionFacultiesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onFacultyItemClick: (Int) -> Unit
) {
    FacultySelectionScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onFacultyClick = onFacultyItemClick
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SessionFacultiesScreenPreview() {
    SessionFacultiesScreen(
        onBackClick = {},
        onFacultyItemClick = {}
    )
}


























