package com.sparkfusion.features.admin.account.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.features.admin.account.R
import com.sparkfusion.features.admin.account.viewModel.AccountViewModel

@Composable
fun PresentationComponent(
    modifier: Modifier = Modifier,
    state: AccountViewModel.AccountState
) {
    when (state) {
        AccountViewModel.AccountState.Error -> {
            ShowToast(value = "Error")
        }

        AccountViewModel.AccountState.Initial -> {}
        AccountViewModel.AccountState.Progress -> {
            CircularProgressIndicator()
        }

        is AccountViewModel.AccountState.Success -> {
            var isAccountImageLoadingCompleted by remember { mutableStateOf(false) }
            val accountImagePainter = rememberAsyncImagePainter(
                model = state.data.icon,
                onSuccess = { isAccountImageLoadingCompleted = true },
                onLoading = { isAccountImageLoadingCompleted = false }
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
                    contentDescription = stringResource(R.string.account_image_description),
                    size = DpSize(124.dp, 124.dp),
                    painter = accountImagePainter,
                    isDarkModeEnabled = isSystemInDarkTheme(),
                    isImageAnimationCompleted = isAccountImageLoadingCompleted
                )

                SFProRoundedText(
                    modifier = Modifier.padding(top = 10.dp),
                    content = state.data.lastname,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )

                SFProRoundedText(
                    modifier = Modifier.padding(top = 2.dp, bottom = 20.dp),
                    content = state.data.firstname + " " + state.data.patronymic,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}








































