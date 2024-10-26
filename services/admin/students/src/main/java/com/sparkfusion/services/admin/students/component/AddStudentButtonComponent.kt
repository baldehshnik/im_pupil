package com.sparkfusion.services.admin.students.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun AddStudentButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Button(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 12.dp),
            onClick = onClick
        ) {
            SFProRoundedText(
                content = "Add student",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
