package com.sparkfusion.services.admin.practice.screen

import android.graphics.Bitmap
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.sparkfusion.services.admin.practice.R
import com.sparkfusion.services.admin.practice.dialog.LinkInputDialog
import com.sparkfusion.services.admin.practice.viewmodel.PracticeAddingViewModel

@Composable
fun AddPracticeScreen(
    modifier: Modifier = Modifier,
    viewModel: PracticeAddingViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onChangeIconClick: (Bitmap) -> Unit,
    getCroppedImage: () -> Bitmap?
) {
    LaunchedEffect(Unit) {
        viewModel.updateBitmap(getCroppedImage())
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val checkState by viewModel.checkState.collectAsStateWithLifecycle()
    val creatingState by viewModel.creatingState.collectAsStateWithLifecycle()

    when (checkState) {
        PracticeAddingViewModel.CheckState.DescriptionEmpty -> {
            ShowToast(value = "Description empty")
            viewModel.clearCheckState()
        }

        PracticeAddingViewModel.CheckState.ImageNotSelected -> {
            ShowToast(value = "Image not selected")
            viewModel.clearCheckState()
        }

        PracticeAddingViewModel.CheckState.Initial -> {}
        PracticeAddingViewModel.CheckState.TitleEmpty -> {
            ShowToast(value = "Title is empty")
            viewModel.clearCheckState()
        }

        PracticeAddingViewModel.CheckState.WorkTypeNotSelected -> {
            ShowToast(value = "Work type not selected")
            viewModel.clearCheckState()
        }
    }

    when (creatingState) {
        PracticeAddingViewModel.CreatingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearCreatingState()
        }

        PracticeAddingViewModel.CreatingState.Initial -> {}
        PracticeAddingViewModel.CreatingState.Progress -> {
            ShowToast(value = "Creating...")
        }

        PracticeAddingViewModel.CreatingState.Success -> {
            onBackClick()
        }
    }

    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForImageCropping(
        context = context,
        navigateToImageCrop = { onChangeIconClick(it) }
    )

    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        LinkInputDialog(
            value = state.website,
            onLinkSubmit = { link ->
                viewModel.updateWebsite(link)
            },
            onDismiss = { showDialog = false }
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopBar(
                title = "Adding",
                onBackClick = onBackClick,
                buttons = {
                    IconButton(onClick = { showDialog = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.globe_icon),
                            contentDescription = null
                        )
                    }
                }
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
                    .padding(start = 24.dp, end = 24.dp, top = 2.dp),
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
                content = "Work type*",
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
                    checked = state.workTypeRemote,
                    onCheckedChange = {
                        viewModel.workTypeRemote(it)
                    }
                )
                SFProRoundedText(content = "Remote", fontSize = 16.sp)

                Spacer(modifier = Modifier.width(16.dp))

                Checkbox(
                    checked = state.workTypeOffline,
                    onCheckedChange = {
                        viewModel.workTypeOffline(it)
                    }
                )
                SFProRoundedText(content = "Office", fontSize = 16.sp)
            }

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
                content = "Payability*",
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
                    selected = state.payAbility,
                    onClick = { viewModel.updatePayAbility(true) }
                )
                SFProRoundedText(content = "Paid", fontSize = 16.sp)

                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(
                    selected = !state.payAbility,
                    onClick = { viewModel.updatePayAbility(false) }
                )
                SFProRoundedText(content = "Unpaid", fontSize = 16.sp)
            }

//            SFProRoundedText(
//                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
//                content = "Relocation support",
//                fontWeight = FontWeight.SemiBold,
//                fontSize = 18.sp
//            )
        }

//        items(1) {
//            Row(
//                modifier = Modifier
//                    .padding(start = 24.dp, end = 24.dp)
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                SFProRoundedText(
//                    content = "1.",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Medium
//                )
//
//                OutlinedTextField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 24.dp, end = 40.dp, top = 8.dp),
//                    value = "",
//                    onValueChange = {},
//                    shape = RoundedCornerShape(16.dp),
//                    placeholder = {
//                        SFProRoundedText(
//                            content = "Enter country or city"
//                        )
//                    },
//                    trailingIcon = {
//                        IconButton(onClick = { }) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.plus_icon),
//                                contentDescription = null,
//                                tint = MaterialTheme.colorScheme.primary
//                            )
//                        }
//                    }
//                )
//            }
//        }

        item {
            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
                content = "Description",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(start = 24.dp, end = 40.dp, top = 8.dp),
                value = state.description,
                onValueChange = { viewModel.updateDescription(it) },
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    SFProRoundedText(
                        content = "Enter here"
                    )
                }
            )

//            SFProRoundedText(
//                modifier = Modifier.padding(start = 24.dp, top = 20.dp),
//                content = "Information blocks",
//                fontWeight = FontWeight.SemiBold,
//                fontSize = 18.sp
//            )
        }

//        items(2) {
//            Card(
//                modifier = Modifier
//                    .padding(start = 24.dp, end = 24.dp, top = 16.dp)
//                    .fillMaxWidth(),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
//            ) {
//                Column(
//                    modifier = Modifier.padding(16.dp),
//                    verticalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    SFProRoundedText(
//                        modifier = Modifier,
//                        content = "Block title",
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 16.sp
//                    )
//
//                    OutlinedTextField(
//                        shape = RoundedCornerShape(16.dp),
//                        value = "",
//                        onValueChange = { },
//                        label = {
//                            SFProRoundedText(
//                                content = "Enter block name"
//                            )
//                        },
//                        modifier = Modifier.fillMaxWidth()
//                    )
//
//                    SFProRoundedText(
//                        modifier = Modifier,
//                        content = "Block content",
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 16.sp
//                    )
//
//                    OutlinedTextField(
//                        shape = RoundedCornerShape(16.dp),
//                        value = "",
//                        onValueChange = { },
//                        label = {
//                            SFProRoundedText(
//                                content = "Enter block information"
//                            )
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(120.dp)
//                    )
//                }
//            }
//        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                TextButton(
//                    onClick = { }
//                ) {
//                    SFProRoundedText(
//                        content = "Add new block",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Medium
//                    )
//                }

                Button(
                    modifier = Modifier
                        .width(120.dp)
                        .padding(bottom = 20.dp),
                    onClick = { viewModel.create() }
                ) {
                    SFProRoundedText(
                        content = "Save",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}




























