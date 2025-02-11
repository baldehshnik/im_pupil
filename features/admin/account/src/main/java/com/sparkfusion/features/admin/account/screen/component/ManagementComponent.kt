package com.sparkfusion.features.admin.account.screen.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sparkfusion.features.admin.account.R

@Composable
internal fun ManagementComponent(
    modifier: Modifier = Modifier,
    name: String,
    address: String,
    phone: String
) {
    AccountScreenBlock(
        modifier = modifier,
        title = stringResource(R.string.management)
    ) {
        ManagementInfoComponent(
            title = stringResource(R.string.name),
            content = name
        )

        ManagementInfoComponent(
            title = stringResource(R.string.address),
            content = address
        )

        ManagementInfoComponent(
            title = stringResource(R.string.phone),
            content = phone
        )

        Spacer(modifier = Modifier.height(28.dp))
    }
}