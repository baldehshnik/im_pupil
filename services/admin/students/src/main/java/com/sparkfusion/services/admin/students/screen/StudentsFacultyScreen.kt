package com.sparkfusion.services.admin.students.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sparkfusion.core.design.screen.faculty.FacultySelectionScreen

@Composable
fun StudentsFacultyScreen(
    modifier: Modifier = Modifier,
    onGroupScreenNavigate: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    FacultySelectionScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onFacultyClick = onGroupScreenNavigate
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FacultyScreenPreview() {
    StudentsFacultyScreen(
        onGroupScreenNavigate = {},
        onBackClick = {}
    )
}




















