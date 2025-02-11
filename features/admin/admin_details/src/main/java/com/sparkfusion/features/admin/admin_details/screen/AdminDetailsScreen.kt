package com.sparkfusion.features.admin.admin_details.screen

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.features.admin.admin_details.R
import com.sparkfusion.features.admin.admin_details.navigator.IAdminDetailsNavigator
import com.sparkfusion.features.admin.admin_details.screen.component.DetailsComponent
import com.sparkfusion.features.admin.admin_details.state.AdminAccessStateHandler
import com.sparkfusion.features.admin.admin_details.state.DeletingAdminStateHandler
import com.sparkfusion.features.admin.admin_details.viewmodel.AdminDetailsViewModel

@Composable
fun AdminDetailsScreenEnter(
    modifier: Modifier = Modifier,
    navigator: IAdminDetailsNavigator,
    id: Int,
    accessMode: Int
) {
    AdminDetailsScreen(
        modifier = modifier,
        navigator = navigator,
        id = id,
        accessMode = accessMode
    )
}

@Composable
private fun AdminDetailsScreen(
    navigator: IAdminDetailsNavigator,
    modifier: Modifier = Modifier,
    viewModel: AdminDetailsViewModel = hiltViewModel(),
    id: Int,
    accessMode: Int
) {
    LaunchedEffect(Unit) {
        viewModel.readAdminDetails(id)
    }

    val adminDetailsState by viewModel.adminDetailsState.collectAsStateWithLifecycle()
    val adminAccessState by viewModel.adminAccessState.collectAsStateWithLifecycle()
    val deleteAdminState by viewModel.adminDeletingState.collectAsStateWithLifecycle()

    var isImageAnimationCompleted by rememberSaveable { mutableStateOf(false) }

    AdminAccessStateHandler(adminAccessState = adminAccessState)
    DeletingAdminStateHandler(
        deleteAdminState = deleteAdminState,
        onBackStack = navigator::popBackStack
    )

    AdminDetailsContent(
        modifier = modifier,
        adminDetailsState = adminDetailsState,
        isImageAnimationCompleted = isImageAnimationCompleted,
        accessMode = accessMode,
        onDeleteAdmin = { viewModel.deleteAdmin() },
        onUpdateAdminAccess = { viewModel.updateAdminAccess() },
        onChangeImageAnimation = { isImageAnimationCompleted = it },
        onBackStack = navigator::popBackStack
    )
}

@Composable
private fun AdminDetailsContent(
    modifier: Modifier = Modifier,
    adminDetailsState: AdminDetailsViewModel.ReadAdminDetailsState,
    isImageAnimationCompleted: Boolean,
    accessMode: Int,
    onDeleteAdmin: () -> Unit,
    onUpdateAdminAccess: () -> Unit,
    onChangeImageAnimation: (Boolean) -> Unit,
    onBackStack: () -> Unit
) {
    when (adminDetailsState) {
        AdminDetailsViewModel.ReadAdminDetailsState.Error -> {
            ShowToast(value = stringResource(id = R.string.error))
        }

        AdminDetailsViewModel.ReadAdminDetailsState.Initial -> {}
        AdminDetailsViewModel.ReadAdminDetailsState.Progress -> {
            CircularProgressIndicator()
        }

        is AdminDetailsViewModel.ReadAdminDetailsState.Success -> {
            val admin = adminDetailsState.admin
            DetailsComponent(
                modifier = modifier,
                admin = admin,
                isImageAnimationCompleted = isImageAnimationCompleted,
                accessMode = accessMode,
                onChangeImageAnimation = onChangeImageAnimation,
                onBackStack = onBackStack,
                onDeleteAdmin = onDeleteAdmin,
                onUpdateAdminAccess = onUpdateAdminAccess
            )
        }
    }
}























