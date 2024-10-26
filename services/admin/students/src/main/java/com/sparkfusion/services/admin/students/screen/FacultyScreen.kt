package com.sparkfusion.services.admin.students.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sparkfusion.core.design.screen.faculty.FacultySelectionScreen

@Composable
fun FacultyScreen(
    modifier: Modifier = Modifier,
    onGroupScreenNavigate: (Int) -> Unit
) {
    FacultySelectionScreen(
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FacultyScreenPreview() {
    FacultyScreen(
        onGroupScreenNavigate = {}
    )
}




















