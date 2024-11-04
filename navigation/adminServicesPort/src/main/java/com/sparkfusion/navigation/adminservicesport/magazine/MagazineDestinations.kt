package com.sparkfusion.navigation.adminservicesport.magazine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.magazine.destination.MagazineHistoryDateDestination
import com.sparkfusion.services.admin.magazine.destination.MagazineHistoryDestination
import com.sparkfusion.services.admin.magazine.destination.MagazineScheduleDestination
import com.sparkfusion.services.admin.magazine.destination.MagazineStudentDetailsDestination
import com.sparkfusion.services.admin.magazine.destination.MagazineStudentsListDestination
import com.sparkfusion.services.admin.magazine.screen.MagazineFacultiesScreen
import com.sparkfusion.services.admin.magazine.screen.MagazineHistoryDateScreen
import com.sparkfusion.services.admin.magazine.screen.MagazineHistoryScreen
import com.sparkfusion.services.admin.magazine.screen.MagazineScheduleScreen
import com.sparkfusion.services.admin.magazine.screen.MagazineStudentDetailsScreen
import com.sparkfusion.services.admin.magazine.screen.MagazineStudentsListScreen

fun NavGraphBuilder.magazineFacultiesScreen(
    navController: NavController
) {
    composable(MagazineFacultiesDestination.route) {
        MagazineFacultiesScreen(
            onBackClick = { navController.popBackStack() },
            onFacultyClick = {
                navController.navigate(MagazineScheduleDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.magazineScheduleScreen(
    navController: NavController
) {
    composable(MagazineScheduleDestination.route) {
        MagazineScheduleScreen(
            onBackClick = { navController.popBackStack() },
            onItemClick = {

            },
            onHistoryClick = {
                navController.navigate(MagazineStudentsListDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.magazineStudentsListScreen(
    navController: NavController
) {
    composable(MagazineStudentsListDestination.route) {
        MagazineStudentsListScreen(
            onBackClick = { navController.popBackStack() },
            onStudentClick = {
                navController.navigate(MagazineStudentDetailsDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.magazineStudentDetailsScreen(
    navController: NavController
) {
    composable(MagazineStudentDetailsDestination.route) {
        MagazineStudentDetailsScreen(
            onBackClick = { navController.popBackStack() },
            onHistoryClick = {
                navController.navigate(MagazineHistoryDateDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.magazineHistoryDateScreen(
    navController: NavController
) {
    composable(MagazineHistoryDateDestination.route) {
        MagazineHistoryDateScreen(
            onBackClick = { navController.popBackStack() },
            onSearchClick = {
                navController.navigate(MagazineHistoryDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.magazineHistoryScreen(
    navController: NavController
) {
    composable(MagazineHistoryDestination.route) {
        MagazineHistoryScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
}
















