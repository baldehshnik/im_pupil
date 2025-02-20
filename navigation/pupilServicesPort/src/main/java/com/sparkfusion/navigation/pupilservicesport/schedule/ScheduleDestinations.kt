package com.sparkfusion.navigation.pupilservicesport.schedule

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.pupil.schedule.destination.LessonsScreenDestination
import com.sparkfusion.services.pupil.schedule.key.SCHEDULE_ID_KEY
import com.sparkfusion.services.pupil.schedule.screen.LessonScreenEnter
import com.sparkfusion.services.pupil.schedule.screen.ScheduleScreenEnter

internal fun NavGraphBuilder.scheduleService(
    navController: NavController
) {
    composable(ScheduleDestination.route) {
        ScheduleScreenEnter(
            onScheduleClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(SCHEDULE_ID_KEY, it)
                navController.navigate(LessonsScreenDestination.route)
            },
            onBackClick = navController::popBackStack
        )
    }
}

internal fun NavGraphBuilder.scheduleLessonsService(
    navController: NavController
) {
    composable(LessonsScreenDestination.route) {
        val scheduleId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(SCHEDULE_ID_KEY)

        if (scheduleId != null) {
            LessonScreenEnter(
                scheduleId = scheduleId,
                onBackClick = navController::popBackStack
            )
        }
    }
}


























