package com.sparkfusion.navigation.adminservicesport.about

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.about.destination.AboutDestination
import com.sparkfusion.services.admin.about.destination.AboutEditDestination
import com.sparkfusion.services.admin.about.screen.AdminAboutServiceScreen
import com.sparkfusion.services.admin.about.screen.AdminEditAboutServiceScreen

fun NavGraphBuilder.aboutScreen(
    navController: NavController
) {
    composable(AboutDestination.route) {
        AdminAboutServiceScreen(

        )
    }
}

fun NavGraphBuilder.aboutEditScreen(
    navController: NavController
) {
    composable(AboutEditDestination.route) {
        AdminEditAboutServiceScreen(

        )
    }
}



























