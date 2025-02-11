package com.sparkfusion.features.admin.account.screen.state

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.domain.admin.port.portaccount.InstitutionAdminModel
import com.sparkfusion.features.admin.account.R
import com.sparkfusion.features.admin.account.screen.component.AccountScreenBlock
import com.sparkfusion.features.admin.account.screen.component.AdministratorItem
import com.sparkfusion.features.admin.account.viewModel.AccountViewModel

@Composable
internal fun AdminsReadingStateHandler(
    adminsState: AccountViewModel.AdminsState,
    accountState: AccountViewModel.AccountState,
    showAllAdministrators: Boolean,
    onChangeShowAllAdministrators: (Boolean) -> Unit,
    onNavigateToAdminDetailsScreen: (id: Int, accessMode: Int) -> Unit
) {
    when (adminsState) {
        AccountViewModel.AdminsState.Error -> {
            ShowToast(value = stringResource(id = R.string.error))
        }

        AccountViewModel.AdminsState.Initial -> {}
        AccountViewModel.AdminsState.Progress -> {
            CircularProgressIndicator()
        }

        is AccountViewModel.AdminsState.Success -> {
            val data = adminsState.data
            val administratorsListHeight = 68.dp * if (showAllAdministrators) data.size else {
                2
            } + 32.dp + 40.dp
            AccountScreenBlock(
                modifier = Modifier,
                title = stringResource(R.string.administrators)
            ) {
                AdminAccountsList(
                    accountState = accountState,
                    data = data,
                    administratorsListHeight = administratorsListHeight,
                    showAllAdministrators = showAllAdministrators,
                    onChangeShowAllAdministrators = onChangeShowAllAdministrators,
                    onNavigateToAdminDetailsScreen = onNavigateToAdminDetailsScreen
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun AdminAccountsList(
    accountState: AccountViewModel.AccountState,
    data: List<InstitutionAdminModel>,
    administratorsListHeight: Dp,
    showAllAdministrators: Boolean,
    onChangeShowAllAdministrators: (Boolean) -> Unit,
    onNavigateToAdminDetailsScreen: (id: Int, accessMode: Int) -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(administratorsListHeight)
            .padding(vertical = 12.dp)
    ) {
        items(
            if (data.size > 2 && !showAllAdministrators) 2 else data.size
        ) {
            val item = data[it]
            AdministratorItem(
                admin = item,
                onMoreInfoClick = {
                    if (accountState is AccountViewModel.AccountState.Success) {
                        onNavigateToAdminDetailsScreen(item.id, accountState.data.accessMode)
                    }
                },
                onItemClick = {
                    if (accountState is AccountViewModel.AccountState.Success) {
                        onNavigateToAdminDetailsScreen(item.id, accountState.data.accessMode)
                    }
                }
            )
        }

        item {
            AnimatedVisibility(visible = data.size > 2) {
                TextButton(
                    modifier = Modifier.padding(top = 4.dp),
                    onClick = { onChangeShowAllAdministrators(!showAllAdministrators) }
                ) {
                    SFProRoundedText(
                        content = if (showAllAdministrators) stringResource(R.string.hide)
                        else stringResource(R.string.show_more),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}























