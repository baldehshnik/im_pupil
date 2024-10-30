package com.sparkfusion.services.admin.students.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.students.component.AccountComponent
import com.sparkfusion.services.admin.students.component.AccountInfoComponent

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    studentId: Int,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            TopBar(
                title = "Students",
                onBackClick = onBackClick
            )

            AccountComponent(
                name = "Shcherba Vladislav Dmitrievich (000722)",
                status = "student (registered)"
            )

            AccountInfoComponent(
                modifier = Modifier.padding(top = 20.dp, start = 24.dp),
                title = "Faculty",
                value = "Faculty of Electronic Information Systems"
            )

            AccountInfoComponent(
                modifier = Modifier.padding(top = 12.dp, start = 24.dp),
                title = "Group",
                value = "Programmable mobile systems"
            )

            AccountInfoComponent(
                modifier = Modifier.padding(top = 12.dp, start = 24.dp),
                title = "Speciality",
                value = "MS-7"
            )
        }

        Button(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .width(240.dp)
                .height(48.dp)
                .align(Alignment.CenterHorizontally),
            onClick = { }
        ) {
            SFProRoundedText(
                textAlign = TextAlign.Center,
                content = "Make a prefect",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AccountScreenPreview() {
    AccountScreen(
        studentId = 1,
        onBackClick = {}
    )
}

























