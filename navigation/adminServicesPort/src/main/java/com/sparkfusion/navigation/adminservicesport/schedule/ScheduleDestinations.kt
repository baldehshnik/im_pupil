package com.sparkfusion.navigation.adminservicesport.schedule

import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.schedule.destination.LessonAddingDestination
import com.sparkfusion.services.admin.schedule.destination.LessonEditingDestination
import com.sparkfusion.services.admin.schedule.destination.ScheduleAddingDestination
import com.sparkfusion.services.admin.schedule.destination.ScheduleDestination
import com.sparkfusion.services.admin.schedule.destination.ScheduleEditingDestination
import com.sparkfusion.services.admin.schedule.destination.ScheduleGroupDestination
import com.sparkfusion.services.admin.schedule.key.DAY_OF_WEEK_KEY
import com.sparkfusion.services.admin.schedule.key.GROUP_ID_KEY
import com.sparkfusion.services.admin.schedule.key.SCHEDULE_ID_KEY
import com.sparkfusion.services.admin.schedule.key.SCHEDULE_TYPE_KEY
import com.sparkfusion.services.admin.schedule.key.WEEK_TYPE_KEY
import com.sparkfusion.services.admin.schedule.screen.LessonAddingScreen
import com.sparkfusion.services.admin.schedule.screen.LessonEditingScreen
import com.sparkfusion.services.admin.schedule.screen.ScheduleAddingScreen
import com.sparkfusion.services.admin.schedule.screen.ScheduleEditingScreen
import com.sparkfusion.services.admin.schedule.screen.ScheduleFacultiesScreen
import com.sparkfusion.services.admin.schedule.screen.ScheduleGroupScreen
import com.sparkfusion.services.admin.schedule.screen.ScheduleScreen
import com.sparkfusion.services.admin.schedule.viewmodel.AddingViewModel
import com.sparkfusion.services.admin.schedule.viewmodel.EditingViewModel

fun NavGraphBuilder.scheduleFacultiesScreen(
    navController: NavController
) {
    composable(ScheduleFacultiesDestination.route) {
        ScheduleFacultiesScreen(
            onFacultyClick = {
                navController.navigate(ScheduleGroupDestination.route)
            },
            onBackClick = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.scheduleGroupScreen(
    navController: NavController
) {
    composable(ScheduleGroupDestination.route) {
        ScheduleGroupScreen(
            onBackClick = { navController.popBackStack() },
            onScheduleClick = { id, type, groupId ->
                navController.currentBackStackEntry?.savedStateHandle?.set(SCHEDULE_ID_KEY, id)
                navController.currentBackStackEntry?.savedStateHandle?.set(SCHEDULE_TYPE_KEY, type)
                navController.currentBackStackEntry?.savedStateHandle?.set(GROUP_ID_KEY, groupId)
                navController.navigate(ScheduleDestination.route)
            },
            onAddScheduleClick = {
                Log.d("TAGTAG", it.toString())
                navController.currentBackStackEntry?.savedStateHandle?.set(GROUP_ID_KEY, it)
                navController.navigate(ScheduleAddingDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.scheduleScreen(
    navController: NavController
) {
    composable(ScheduleDestination.route) {
        val groupId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(GROUP_ID_KEY)
        val scheduleId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(SCHEDULE_ID_KEY)
        val scheduleType =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(SCHEDULE_TYPE_KEY)

        if (scheduleId != null && scheduleType != null) {
            ScheduleScreen(
                onBackClick = { navController.popBackStack() },
                onEditClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        SCHEDULE_ID_KEY,
                        scheduleId
                    )
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        GROUP_ID_KEY,
                        groupId
                    )
                    navController.navigate(ScheduleEditingDestination.route)
                },
                scheduleId = scheduleId,
                scheduleType = scheduleType
            )
        }
    }
}

fun NavGraphBuilder.scheduleEditingScreen(
    navController: NavController
) {
    composable(ScheduleEditingDestination.route) {
        val scheduleId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(SCHEDULE_ID_KEY)
        val groupId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(GROUP_ID_KEY)

        val viewModel = hiltViewModel<EditingViewModel>(it)
        if (scheduleId != null && groupId != null) {
            ScheduleEditingScreen(
                groupId = groupId,
                scheduleId = scheduleId,
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                },
                onEditLessonClick = { weekType, dayOfWeek ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        WEEK_TYPE_KEY, weekType
                    )
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        DAY_OF_WEEK_KEY, dayOfWeek
                    )
                    navController.navigate(LessonEditingDestination.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.scheduleAddingScreen(
    navController: NavController
) {
    composable(ScheduleAddingDestination.route) {
        val groupId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(GROUP_ID_KEY)
        val scheduleAddingViewModel = hiltViewModel<AddingViewModel>(it)
        if (groupId != null) {
            ScheduleAddingScreen(
                groupId = groupId,
                viewModel = scheduleAddingViewModel,
                onBackClick = {
                    navController.popBackStack()
                },
                onAddLessonClick = { weekType, dayOfWeek ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        WEEK_TYPE_KEY,
                        weekType
                    )
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        DAY_OF_WEEK_KEY,
                        dayOfWeek
                    )
                    navController.navigate(LessonAddingDestination.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.lessonAddingScreen(
    navController: NavController
) {
    composable(LessonAddingDestination.route) {
        val viewModel: AddingViewModel =
            if (navController.previousBackStackEntry == null) hiltViewModel()
            else hiltViewModel(navController.previousBackStackEntry!!)

        val weekType =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(WEEK_TYPE_KEY)
        val dayOfWeek =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(DAY_OF_WEEK_KEY)
        if (weekType != null && dayOfWeek != null) {
            LessonAddingScreen(
                viewModel = viewModel,
                weekType = weekType,
                dayOfWeek = dayOfWeek,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

fun NavGraphBuilder.lessonEditingScreen(
    navController: NavController
) {
    composable(LessonEditingDestination.route) {
        val viewModel: EditingViewModel =
            if (navController.previousBackStackEntry == null) hiltViewModel()
            else hiltViewModel(navController.previousBackStackEntry!!)

        val weekType =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(WEEK_TYPE_KEY)
        val dayOfWeek =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(DAY_OF_WEEK_KEY)

        if (weekType != null && dayOfWeek != null) {
            LessonEditingScreen(
                viewModel = viewModel,
                weekType = weekType,
                dayOfWeek = dayOfWeek,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}















