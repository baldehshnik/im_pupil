package com.sparkfusion.services.admin.about.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.about.R
import com.sparkfusion.services.admin.about.model.EditAboutBlockModel
import com.sparkfusion.services.admin.about.viewmodel.EditAboutViewModel

@Composable
internal fun EditAboutScreenContent(
    readState: EditAboutViewModel.ReadState,
    onBackClick: () -> Unit,
    onClearReadingState: () -> Unit,
    onAddBlockClick: () -> Unit,
    onImageChangeClick: () -> Unit,
    onChangeCurrentImage: (EditAboutBlockModel) -> Unit,
    onUpdateDescription: (EditAboutBlockModel, String) -> Unit
) {
    LazyColumn {
        item {
            TopBar(title = stringResource(id = R.string.edit), onBackClick = onBackClick)

            SFProRoundedText(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 12.dp),
                color = descriptionColor(),
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                content = stringResource(id = R.string.edit_info_text)
            )
        }

        when (readState) {
            EditAboutViewModel.ReadState.Error -> {
                item { ShowToast(value = stringResource(id = R.string.error)) }
                onClearReadingState()
            }

            EditAboutViewModel.ReadState.Initial -> {}
            EditAboutViewModel.ReadState.Progress -> {
                item { CircularProgressIndicator() }
            }

            is EditAboutViewModel.ReadState.Success -> {
                items(readState.data) {
                    AddBlockItem(
                        item = it,
                        onValueChange = { value ->
                            onUpdateDescription(it, value)
                        },
                        onImageClick = {
                            onChangeCurrentImage(it)
                            onImageChangeClick()
                        }
                    )
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(onClick = { onAddBlockClick() }) {
                            SFProRoundedText(
                                content = stringResource(id = R.string.add_block),
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}























