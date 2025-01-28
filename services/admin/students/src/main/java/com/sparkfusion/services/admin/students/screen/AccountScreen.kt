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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.students.component.AccountComponent
import com.sparkfusion.services.admin.students.component.AccountInfoComponent
import com.sparkfusion.services.admin.students.viewmodel.AccountViewModel

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = hiltViewModel(),
    studentId: Int,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readGroupMember(studentId)
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()
    val makeAPrefectState by viewModel.makeAPrefectState.collectAsStateWithLifecycle()

    when (makeAPrefectState) {
        AccountViewModel.MakeAPrefectState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearMakeAPrefectState()
        }

        AccountViewModel.MakeAPrefectState.Initial -> {}
        AccountViewModel.MakeAPrefectState.Progress -> {
            ShowToast(value = "Updating...")
        }
    }

    when (readingState) {
        AccountViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        AccountViewModel.ReadingState.Initial -> {}
        AccountViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is AccountViewModel.ReadingState.Success -> {
            val data = (readingState as AccountViewModel.ReadingState.Success).model

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
                        icon = data.pupil?.icon,
                        name = "${data.lastname} ${data.firstname} ${data.patronymic} (${data.code})",
                        status = if (data.isPrefect) "prefect" else {
                            "student"
                        } + if (data.pupil != null) " (registered)" else ""
                    )

                    AccountInfoComponent(
                        modifier = Modifier.padding(top = 20.dp, start = 24.dp),
                        title = "Institution",
                        value = data.educationPlaceDto?.institutionName ?: "Not found"
                    )

                    AccountInfoComponent(
                        modifier = Modifier.padding(top = 12.dp, start = 24.dp),
                        title = "Faculty",
                        value = data.educationPlaceDto?.facultyName ?: "Not found"
                    )

                    AccountInfoComponent(
                        modifier = Modifier.padding(top = 12.dp, start = 24.dp),
                        title = "Group",
                        value = data.educationPlaceDto?.groupName ?: "Not found"
                    )
                }

                if (!data.isPrefect) {
                    Button(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .width(240.dp)
                            .height(48.dp)
                            .align(Alignment.CenterHorizontally),
                        onClick = { viewModel.makeAPrefect(data.id) }
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

























