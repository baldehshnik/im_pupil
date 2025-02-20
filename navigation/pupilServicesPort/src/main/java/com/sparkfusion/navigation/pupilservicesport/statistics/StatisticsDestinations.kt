package com.sparkfusion.navigation.pupilservicesport.statistics

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.pupil.statistics.destination.GroupPassesDestination
import com.sparkfusion.services.pupil.statistics.destination.StudentStatisticsDestination
import com.sparkfusion.services.pupil.statistics.key.STUDENT_ID_KEY
import com.sparkfusion.services.pupil.statistics.screen.GroupMembersScreenEnter
import com.sparkfusion.services.pupil.statistics.screen.GroupPassesScreenEnter
import com.sparkfusion.services.pupil.statistics.screen.StudentStatisticsScreenEnter

internal fun NavGraphBuilder.statisticsService(
    navController: NavController
) {
    composable(StatisticsDestination.route) {
        GroupMembersScreenEnter(
            onStudentClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(STUDENT_ID_KEY, it)
                navController.navigate(StudentStatisticsDestination.route)
            },
            onGroupStatisticsClick = {
                navController.navigate(GroupPassesDestination.route)
            },
            onBackClick = navController::popBackStack
        )
    }
}

internal fun NavGraphBuilder.studentStatisticsService(
    navController: NavController
) {
    composable(StudentStatisticsDestination.route) {
        val id = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(STUDENT_ID_KEY)

        if (id != null) {
            StudentStatisticsScreenEnter(
                id = id,
                onBackClick = navController::popBackStack
            )
        }
    }
}

internal fun NavGraphBuilder.groupPassesService(
    navController: NavController
) {
    composable(GroupPassesDestination.route) {
        GroupPassesScreenEnter(
            onBackClick = navController::popBackStack
        )
    }
}





























