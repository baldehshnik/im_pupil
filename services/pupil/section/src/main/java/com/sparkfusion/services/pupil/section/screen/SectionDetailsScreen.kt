package com.sparkfusion.services.pupil.section.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.section.R
import com.sparkfusion.services.pupil.section.viewmodel.SectionDetailsViewModel

@Composable
fun SectionDetailsScreenEnter(
    modifier: Modifier = Modifier,
    sectionId: Int,
    onBackStackClick: () -> Unit
) {
    SectionDetailsScreen(
        modifier = modifier,
        sectionId = sectionId,
        onBackStackClick = onBackStackClick
    )
}

@Composable
private fun SectionDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: SectionDetailsViewModel = hiltViewModel(),
    sectionId: Int,
    onBackStackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.read(sectionId)
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    when (readingState) {
        SectionDetailsViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        SectionDetailsViewModel.ReadingState.Initial -> {}
        SectionDetailsViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is SectionDetailsViewModel.ReadingState.Success -> {
            val model = (readingState as SectionDetailsViewModel.ReadingState.Success).model
            LazyColumn(
                modifier = modifier.fillMaxWidth()
            ) {
                item {
                    TopBar(
                        title = "Details",
                        onBackClick = onBackStackClick
                    )

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .size(112.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            model = model.icon,
                            contentDescription = null
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, top = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        SFProRoundedText(
                            modifier = Modifier,
                            content = model.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    }

                    Row(
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(26.dp),
                            painter = painterResource(id = R.drawable.gender_icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        SFProRoundedText(
                            modifier = Modifier,
                            content = "Gender: " + when (model.gender) {
                                1 -> "Men"
                                2 -> "Women"
                                else -> "Men/Women"
                            },
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(26.dp),
                            painter = painterResource(id = R.drawable.trophy_icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        SFProRoundedText(
                            modifier = Modifier,
                            content = "Trainer: ${model.trainer}",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(26.dp),
                            painter = painterResource(id = R.drawable.usd_icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        SFProRoundedText(
                            modifier = Modifier,
                            content = "Price: " + if (!model.price) "free" else "paid",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(26.dp),
                            painter = painterResource(id = R.drawable.users_icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        SFProRoundedText(
                            modifier = Modifier,
                            content = "Courses: ${model.fromCourse}-${model.toCourse} courses",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    SFProRoundedText(
                        modifier = Modifier.padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 30.dp,
                            bottom = 20.dp
                        ),
                        content = "To enroll in sports sections by sport, you must contact directly the sports coach or the head of the sports club N.L. Bondareva.",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}



























