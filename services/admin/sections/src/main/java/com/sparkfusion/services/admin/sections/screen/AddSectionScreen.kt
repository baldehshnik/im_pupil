package com.sparkfusion.services.admin.sections.screen

import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RangeSlider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.sparkfusion.core.image_crop.launcher.rememberLauncherForImageCropping
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.sections.viewmodel.AddSectionViewModel

@Composable
fun AddSectionScreen(
    modifier: Modifier = Modifier,
    viewModel: AddSectionViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onChangeIconClick: (Bitmap) -> Unit,
    getCroppedImage: () -> Bitmap?
) {
    LaunchedEffect(Unit) {
        viewModel.updateBitmap(getCroppedImage())
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val creationState by viewModel.creationState.collectAsStateWithLifecycle()
    val checkState by viewModel.checkState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForImageCropping(
        context = context,
        navigateToImageCrop = { onChangeIconClick(it) }
    )

    val rangeValue = state.fromCourse.toFloat()..state.toCourse.toFloat()
    val default = "all"

    when (checkState) {
        AddSectionViewModel.CheckState.GenderNotSelected -> {
            ShowToast(value = "Gender not selected")
            viewModel.clearCheckState()
        }

        AddSectionViewModel.CheckState.ImageNotSelected -> {
            ShowToast(value = "Image not selected")
            viewModel.clearCheckState()
        }

        AddSectionViewModel.CheckState.Initial -> {}
        AddSectionViewModel.CheckState.TitleEmpty -> {
            ShowToast(value = "Title empty")
            viewModel.clearCheckState()
        }

        AddSectionViewModel.CheckState.TrainerEmpty -> {
            ShowToast(value = "Trainer empty")
            viewModel.clearCheckState()
        }
    }

    when (creationState) {
        AddSectionViewModel.CreationState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearCreatingState()
        }

        AddSectionViewModel.CreationState.Initial -> {}
        AddSectionViewModel.CreationState.Progress -> {
            ShowToast(value = "Creating...")
        }

        AddSectionViewModel.CreationState.Success -> {
            onBackClick()
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(
                title = "Adding",
                onBackClick = onBackClick
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .size(112.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            galleryLauncher.launch("image/*")
                        },
                    model = state.bitmap,
                    contentDescription = null
                )
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
                content = "Title*",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 72.dp, top = 2.dp),
                value = state.title,
                onValueChange = { viewModel.updateTitle(it) },
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    SFProRoundedText(
                        content = "Enter here (max 24 symbols)"
                    )
                }
            )

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
                content = "Trainer*",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 2.dp),
                value = state.trainer,
                onValueChange = { viewModel.updateTrainer(it) },
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    SFProRoundedText(
                        content = "Enter here"
                    )
                }
            )

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
                content = "Gender*",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = state.genderMen,
                    onCheckedChange = { viewModel.updateGenderMen(!state.genderMen) }
                )
                SFProRoundedText(content = "Men", fontSize = 16.sp)

                Spacer(modifier = Modifier.width(16.dp))

                Checkbox(
                    checked = state.genderWomen,
                    onCheckedChange = { viewModel.updateGenderWomen(!state.genderWomen) }
                )
                SFProRoundedText(content = "Women", fontSize = 16.sp)
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 12.dp),
                content = "Price*",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = state.price,
                    onClick = { viewModel.updatePrice(true) }
                )
                SFProRoundedText(content = "Paid", fontSize = 16.sp)

                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(
                    selected = !state.price,
                    onClick = { viewModel.updatePrice(false) }
                )

                SFProRoundedText(content = "Free", fontSize = 16.sp)
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 12.dp),
                content = "Courses",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val start = rangeValue.start.toInt()
                    val end = rangeValue.endInclusive.toInt()
                    SFProRoundedText(
                        content = if (start == 0) default else "$start course",
                        fontWeight = FontWeight.SemiBold
                    )
                    SFProRoundedText(
                        content = if (end == 80) default else "$end course",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                RangeSlider(
                    value = rangeValue,
                    onValueChange = { newRange ->
                        viewModel.updateFromCourse(newRange.start.toInt())
                        viewModel.updateToCourse(newRange.endInclusive.toInt())
                    },
                    valueRange = 1f..6f,
                    steps = 5
                )
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 12.dp),
                content = "Description",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(start = 24.dp, end = 24.dp, top = 2.dp),
                value = state.description,
                onValueChange = { viewModel.updateDescription(it) },
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    SFProRoundedText(
                        content = "Enter here"
                    )
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { viewModel.create() }) {
                    SFProRoundedText(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp),
                        content = "Save",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}




























