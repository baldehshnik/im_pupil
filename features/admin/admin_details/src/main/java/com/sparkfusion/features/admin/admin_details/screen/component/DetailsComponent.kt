package com.sparkfusion.features.admin.admin_details.screen.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.domain.admin.port.portadmindetails.AdminDetailsModel
import com.sparkfusion.features.admin.admin_details.R

@Composable
internal fun DetailsComponent(
    modifier: Modifier = Modifier,
    admin: AdminDetailsModel,
    isImageAnimationCompleted: Boolean,
    accessMode: Int,
    onChangeImageAnimation: (Boolean) -> Unit,
    onBackStack: () -> Unit,
    onDeleteAdmin: () -> Unit,
    onUpdateAdminAccess: () -> Unit,
) {
    val painter = rememberAsyncImagePainter(
        model = admin.icon,
        onSuccess = { onChangeImageAnimation(true) },
        onLoading = { onChangeImageAnimation(false) }
    )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            title = stringResource(R.string.details),
            onBackClick = onBackStack
        )

        Spacer(modifier = Modifier.height(24.dp))

        ShimmerImageBox(
            contentAlignment = Alignment.Center,
            painter = painter,
            size = DpSize(156.dp, 156.dp),
            isDarkModeEnabled = isSystemInDarkTheme(),
            isImageAnimationCompleted = isImageAnimationCompleted,
            contentDescription = stringResource(R.string.admin_icon_description)
        )

        SFProRoundedText(
            modifier = Modifier.padding(top = 16.dp),
            content = admin.lastname,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )

        SFProRoundedText(
            modifier = Modifier.padding(top = 4.dp),
            content = admin.firstname + " " + admin.patronymic,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(44.dp))

        DetailItemComponent(
            title = stringResource(R.string.job_title),
            textContent = when (admin.accessMode) {
                1 -> stringResource(id = R.string.assistant)
                2 -> stringResource(id = R.string.teacher)
                else -> stringResource(id = R.string.administrator)
            },
            imageId = R.drawable.job_title_icon,
            detailButton = {
                if (accessMode > admin.accessMode) {
                    IconButton(onClick = { onUpdateAdminAccess() }) {
                        Icon(
                            tint = MaterialTheme.colorScheme.primary,
                            painter = painterResource(R.drawable.pencil_icon),
                            contentDescription = stringResource(R.string.change_admin_type_description)
                        )
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(14.dp))

        DetailItemComponent(
            title = stringResource(R.string.email),
            textContent = admin.email,
            imageId = R.drawable.email_icon,
        )

        if (accessMode > admin.accessMode) {
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                modifier = Modifier.padding(bottom = 48.dp),
                onClick = { onDeleteAdmin() }
            ) {
                SFProRoundedText(
                    content = stringResource(R.string.delete_administrator),
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Red
                )
            }
        }
    }
}






















