package com.sparkfusion.services.admin.magazine.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagazineHistoryDateScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    val dateState = rememberDatePickerState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(title = "History", onBackClick = onBackClick)

        SFProRoundedText(
            modifier = Modifier
                .padding(start = 24.dp, top = 12.dp, end = 24.dp),
            content = "Select the date you want to view history for:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )

        DatePicker(
            state = dateState,
            colors = DatePickerDefaults.colors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )

        Button(
            modifier = Modifier
                .padding(bottom = 20.dp, top = 8.dp)
                .align(Alignment.CenterHorizontally),
            onClick = onSearchClick
        ) {
            SFProRoundedText(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp),
                content = "Search",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MagazineHistoryDateScreenPreview() {
    MagazineHistoryDateScreen(
        onBackClick = {},
        onSearchClick = {}
    )
}
























