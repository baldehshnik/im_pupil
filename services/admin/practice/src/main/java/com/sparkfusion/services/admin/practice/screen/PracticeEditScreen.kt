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
import androidx.compose.material3.CircularProgressIndicator
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
import com.sparkfusion.services.admin.practice.viewmodel.PracticeEditingViewModel

@Composable
fun PracticeEditScreen(
    modifier: Modifier = Modifier,
    viewModel: PracticeEditingViewModel = hiltViewModel(),
    practiceId: Int,
    onBackClick: () -> Unit,
    onChangeIconClick: (Bitmap) -> Unit,
    getCroppedImage: () -> Bitmap?
) {
    LaunchedEffect(Unit) {
        viewModel.read(practiceId)
        viewModel.updateBitmap(getCroppedImage())
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val readingState by viewModel.readingState.collectAsStateWithLifecycle()
    val updatingState by viewModel.updatingState.collectAsStateWithLifecycle()
    val checkState by viewModel.checkState.collectAsStateWithLifecycle()

    when (checkState) {
        PracticeEditingViewModel.CheckState.DescriptionEmpty -> {
            ShowToast(value = "Description empty")
            viewModel.clearCheckState()
        }

        PracticeEditingViewModel.CheckState.Initial -> {}
        PracticeEditingViewModel.CheckState.TitleEmpty -> {
            ShowToast(value = "Title is empty")
            viewModel.clearCheckState()
        }

        PracticeEditingViewModel.CheckState.WorkTypeNotSelected -> {
            ShowToast(value = "Work type not selected")
            viewModel.clearCheckState()
        }
    }

    when (updatingState) {
        PracticeEditingViewModel.UpdatingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearUpdatingState()
        }

        PracticeEditingViewModel.UpdatingState.Initial -> {}
        PracticeEditingViewModel.UpdatingState.Progress -> {
            ShowToast(value = "Updating...")
        }

        PracticeEditingViewModel.UpdatingState.Success -> {
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

    when (readingState) {
        PracticeEditingViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        PracticeEditingViewModel.ReadingState.Initial -> {}
        PracticeEditingViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        PracticeEditingViewModel.ReadingState.Success -> {
            LazyColumn(
                modifier = modifier.fillMaxWidth()
            ) {
                item {
                    TopBar(
                        title = "Editing",
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
                            model = state.bitmap ?: state.icon,
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
                }

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
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            modifier = Modifier
                                .width(120.dp)
                                .padding(bottom = 20.dp),
                            onClick = { viewModel.update() }
                        ) {
                            SFProRoundedText(
                                content = "Save changes",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}






























