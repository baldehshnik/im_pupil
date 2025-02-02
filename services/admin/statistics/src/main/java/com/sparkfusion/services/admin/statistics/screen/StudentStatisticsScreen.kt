package com.sparkfusion.services.admin.statistics.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.statistics.component.PassesPerComponent
import com.sparkfusion.services.admin.statistics.viewmodel.StudentStatisticsViewModel

@Composable
fun StudentStatisticsScreen(
    modifier: Modifier = Modifier,
    viewModel: StudentStatisticsViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val monthStatisticsState by viewModel.monthStatisticsState.collectAsStateWithLifecycle()
    val semesterStatisticsState by viewModel.semesterStatisticsState.collectAsStateWithLifecycle()

    val model = state.student
    if (model != null) {
        LaunchedEffect(Unit) {
            viewModel.readMonthStatistics()
            viewModel.readSemesterStatistics()
        }

        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            TopBar(title = "Student statistics", onBackClick = onBackClick)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 12.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    model = model.pupil?.icon ?: "",
                    contentDescription = null
                )

                Column(
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    SFProRoundedText(
                        modifier = Modifier.padding(start = 12.dp),
                        content = "${model.lastname} ${model.firstname} ${model.patronymic} (${model.code})",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    SFProRoundedText(
                        modifier = Modifier.padding(start = 12.dp, top = 2.dp),
                        content = if (model.prefect) "prefect" else {
                            "student"
                        } + if (model.pupil != null) " (registered)" else "",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = descriptionColor()
                    )
                }
            }

            when (monthStatisticsState) {
                StudentStatisticsViewModel.MonthStatisticsState.Error -> {
                    PassesPerComponent(
                        title = "Passes per month",
                        value = "Error"
                    )
                }

                StudentStatisticsViewModel.MonthStatisticsState.Initial -> {}
                StudentStatisticsViewModel.MonthStatisticsState.Progress -> {
                    CircularProgressIndicator()
                }

                is StudentStatisticsViewModel.MonthStatisticsState.Success -> {
                    PassesPerComponent(
                        title = "Passes per month",
                        value = "${(monthStatisticsState as StudentStatisticsViewModel.MonthStatisticsState.Success).count} passes"
                    )
                }
            }

            when (semesterStatisticsState) {
                StudentStatisticsViewModel.SemesterStatisticsState.Error -> {
                    PassesPerComponent(
                        title = "Passes per semester",
                        value = "Error"
                    )
                }

                StudentStatisticsViewModel.SemesterStatisticsState.Initial -> {}
                StudentStatisticsViewModel.SemesterStatisticsState.Progress -> {
                    CircularProgressIndicator()
                }

                is StudentStatisticsViewModel.SemesterStatisticsState.Success -> {
                    PassesPerComponent(
                        title = "Passes per semester",
                        value = "${(semesterStatisticsState as StudentStatisticsViewModel.SemesterStatisticsState.Success).count} passes"
                    )
                }
            }
        }
    }
}



















