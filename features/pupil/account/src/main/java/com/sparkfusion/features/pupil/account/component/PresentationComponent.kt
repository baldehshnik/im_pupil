package com.sparkfusion.features.pupil.account.component

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.features.pupil.account.viewmodel.AccountViewModel

@Composable
internal fun PresentationComponent(
    modifier: Modifier = Modifier,
    state: AccountViewModel.ReadingState,
    launcher: ManagedActivityResultLauncher<String, Uri?>
) {
    when (state) {
        AccountViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
        }

        AccountViewModel.ReadingState.Initial -> {}
        AccountViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is AccountViewModel.ReadingState.Success -> {
            var isAccountImageLoadingCompleted by remember { mutableStateOf(false) }
            val accountImagePainter = rememberAsyncImagePainter(
                model = state.model!!.pupil.icon,
                onSuccess = { isAccountImageLoadingCompleted = true },
                onLoading = { isAccountImageLoadingCompleted = false },
                onError = { isAccountImageLoadingCompleted = true }
            )

            Column(
                modifier = modifier.background(
                    color = Color.White,
                    shape = RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp)
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                ShimmerImageBox(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            if (isAccountImageLoadingCompleted) launcher.launch("image/*")
                        },
                    contentDescription = null,
                    size = DpSize(124.dp, 124.dp),
                    painter = accountImagePainter,
                    isDarkModeEnabled = isSystemInDarkTheme(),
                    isImageAnimationCompleted = isAccountImageLoadingCompleted,
                )

                SFProRoundedText(
                    modifier = Modifier.padding(top = 10.dp),
                    content = state.model.lastname,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )

                SFProRoundedText(
                    modifier = Modifier.padding(top = 2.dp, bottom = 20.dp),
                    content = state.model.firstname + " " + state.model.patronymic,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            ManagementComponent(
                name = state.model.groupInfo.institutionName,
                address = state.model.groupInfo.institutionAddress,
                phone = state.model.groupInfo.institutionPhone
            )

            EducationPlaceComponent(
                speciality = state.model.groupInfo.specialityName,
                group = state.model.groupInfo.groupName,
                membersCount = state.model.groupInfo.groupMembersCount
            )
        }
    }
}








































