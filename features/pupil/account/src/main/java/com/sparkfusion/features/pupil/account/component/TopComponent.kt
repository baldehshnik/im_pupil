package com.sparkfusion.features.pupil.account.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.pupil.account.viewmodel.AccountViewModel

@Composable
internal fun TopComponent(
    modifier: Modifier = Modifier,
    state: AccountViewModel.ReadingState,
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))

        if (state is AccountViewModel.ReadingState.Success) {
            Row(
                modifier = Modifier.padding(start = 64.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SFProRoundedText(
                    content = state.model!!.groupInfo.institutionAbbreviation,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            modifier = Modifier
                .padding(end = 24.dp)
                .size(40.dp),
            onClick = onSettingsClick
        ) {
            Icon(
                painter = painterResource(com.sparkfusion.core.resource.R.drawable.settings_icon),
                contentDescription = null
            )
        }
    }
}



















