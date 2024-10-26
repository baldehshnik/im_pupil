package com.sparkfusion.core.design.screen.faculty

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sparkfusion.core.widget.topbar.TopBar

@Composable
fun FacultySelectionScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            TopBar(
                title = "Faculty",
                onBackClick = {

                }
            )
        }

        items(4) {
            FacultyItem(
                content = "Brest State Technical University",
                onItemClick = {  }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FacultySelectionScreenPreview() {
    FacultySelectionScreen(

    )
}
