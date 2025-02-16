package com.sparkfusion.navigation.pupil.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.sparkfusion.navigation.core.bottombar.BottomAppBar
import com.sparkfusion.navigation.core.bottombar.BottomBarImageItem
import com.sparkfusion.navigation.core.bottombar.BottomBarItem
import com.sparkfusion.navigation.pupil.R
import com.sparkfusion.navigation.pupilcoreport.PupilAccountDestination
import com.sparkfusion.navigation.pupilcoreport.PupilHomeDestination
import com.sparkfusion.navigation.pupilcoreport.PupilServicesDestination

@Composable
fun PupilBottomAppBar(navController: NavHostController, currentRoute: String?) {
    val items = listOf(
        BottomBarItem(
            PupilHomeDestination,
            BottomBarImageItem(
                R.drawable.outlined_home_icon, R.drawable.filled_home_icon
            ),
            stringResource(R.string.home),
        ),
        BottomBarItem(
            PupilServicesDestination,
            BottomBarImageItem(
                R.drawable.outlined_services_icon, R.drawable.filled_services_icon
            ),
            stringResource(R.string.services)
        ),
        BottomBarItem(
            PupilAccountDestination,
            BottomBarImageItem(
                R.drawable.outlined_account_icon, R.drawable.filled_account_icon
            ),
            stringResource(R.string.account)
        )
    )

    BottomAppBar(
        navController = navController,
        items = items,
        currentRoute
    )
}




























