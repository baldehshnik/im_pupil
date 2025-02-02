package com.sparkfusion.navigation.adminservicesport.statistics

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.statistics.destination.StatisticsGroupDestination
import com.sparkfusion.services.admin.statistics.destination.StatisticsGroupPassesDestination
import com.sparkfusion.services.admin.statistics.destination.StatisticsTypeDestination
import com.sparkfusion.services.admin.statistics.destination.StudentStatisticsDestination
import com.sparkfusion.services.admin.statistics.key.GROUP_ID_KEY
import com.sparkfusion.services.admin.statistics.screen.StatisticGroupScreen
import com.sparkfusion.services.admin.statistics.screen.StatisticGroupSearchScreen
import com.sparkfusion.services.admin.statistics.screen.StatisticTypeScreen
import com.sparkfusion.services.admin.statistics.screen.StatisticsGroupPassesScreen
import com.sparkfusion.services.admin.statistics.screen.StudentStatisticsScreen
import com.sparkfusion.services.admin.statistics.viewmodel.GroupViewModel
import com.sparkfusion.services.admin.statistics.viewmodel.StudentStatisticsViewModel

fun NavGraphBuilder.statisticsStudentScreen(
    navController: NavController
) {
    composable(StudentStatisticsDestination.route) {
        val viewModel: StudentStatisticsViewModel = if (navController.previousBackStackEntry == null) hiltViewModel()
        else hiltViewModel(navController.previousBackStackEntry!!)

        StudentStatisticsScreen(
            viewModel = viewModel,
            onBackClick = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.statisticsTypeScreen(
    navController: NavController
) {
    composable(StatisticsTypeDestination.route) {
        StatisticTypeScreen(
            onTypeClick = { navController.navigate(StatisticsGroupSearchDestination.route) },
            onBackClick = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.statisticsGroupSearchScreen(
    navController: NavController
) {
    composable(StatisticsGroupSearchDestination.route) {
        StatisticGroupSearchScreen(
            onBackClick = { navController.popBackStack() },
            onNextClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(GROUP_ID_KEY, it)
                navController.navigate(StatisticsGroupDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.statisticsGroupScreen(
    navController: NavController
) {
    composable(StatisticsGroupDestination.route) {
        val groupId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(GROUP_ID_KEY)

        val viewModel = hiltViewModel<GroupViewModel>(it)
        val studentStatisticsViewModel = hiltViewModel<StudentStatisticsViewModel>(it)
        if (groupId != null) {
            StatisticGroupScreen(
                groupId = groupId,
                viewModel = viewModel,
                studentStatisticsViewModel = studentStatisticsViewModel,
                onBackClick = { navController.popBackStack() },
                onStudentClick = {
                    navController.navigate(StudentStatisticsDestination.route)
                },
                onGroupStatisticsClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(GROUP_ID_KEY, groupId)
                    navController.navigate(StatisticsGroupPassesDestination.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.statisticsGroupPassesDestination(
    navController: NavController
) {
    composable(StatisticsGroupPassesDestination.route) {
        val groupId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(GROUP_ID_KEY)

        if (groupId != null) {
            StatisticsGroupPassesScreen(
                groupId = groupId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

































