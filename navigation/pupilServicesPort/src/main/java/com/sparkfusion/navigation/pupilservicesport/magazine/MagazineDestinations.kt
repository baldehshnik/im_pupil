package com.sparkfusion.navigation.pupilservicesport.magazine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.pupil.magazine.destination.HistoryDateDestination
import com.sparkfusion.services.pupil.magazine.destination.HistoryDestination
import com.sparkfusion.services.pupil.magazine.destination.StudentDetailsDestination
import com.sparkfusion.services.pupil.magazine.destination.StudentsListDestination
import com.sparkfusion.services.pupil.magazine.key.DATE_ID_KEY
import com.sparkfusion.services.pupil.magazine.key.LESSON_ID_KEY
import com.sparkfusion.services.pupil.magazine.key.STUDENT_ID_KEY
import com.sparkfusion.services.pupil.magazine.screen.HistoryDateScreenEnter
import com.sparkfusion.services.pupil.magazine.screen.HistoryScreenEnter
import com.sparkfusion.services.pupil.magazine.screen.StudentDetailsScreenEnter
import com.sparkfusion.services.pupil.magazine.screen.StudentsListScreenEnter
import com.sparkfusion.services.pupil.magazine.screen.TodayScheduleScreenEnter

internal fun NavGraphBuilder.magazineService(
    navController: NavController
) {
    composable(MagazineDestination.route) {
        TodayScheduleScreenEnter(
            onItemClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(LESSON_ID_KEY, it)
                navController.navigate(StudentsListDestination.route)
            },
            onHistoryClick = {
                navController.navigate(HistoryDateDestination.route)
            },
            onBackClick = navController::popBackStack
        )
    }
}

internal fun NavGraphBuilder.magazineHistoryDateService(
    navController: NavController
) {
    composable(HistoryDateDestination.route) {
        val studentId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(STUDENT_ID_KEY)

        HistoryDateScreenEnter(
            groupMemberId = studentId,
            onSearchClick = { id, date ->
                navController.currentBackStackEntry?.savedStateHandle?.set(STUDENT_ID_KEY, id)
                navController.currentBackStackEntry?.savedStateHandle?.set(DATE_ID_KEY, date)
                navController.navigate(HistoryDestination.route)
            },
            onBackClick = navController::popBackStack
        )
    }
}

internal fun NavGraphBuilder.magazineHistoryService(
    navController: NavController
) {
    composable(HistoryDestination.route) {
        val studentId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(STUDENT_ID_KEY)
        val date = navController.previousBackStackEntry?.savedStateHandle?.get<Long>(DATE_ID_KEY)

        if (studentId != null && date != null) {
            HistoryScreenEnter(
                groupMemberId = studentId,
                date = date,
                onBackClick = navController::popBackStack
            )
        }
    }
}

internal fun NavGraphBuilder.magazineStudentsListService(
    navController: NavController
) {
    composable(StudentsListDestination.route) {
        val lessonId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(LESSON_ID_KEY)

        if (lessonId != null) {
            StudentsListScreenEnter(
                lessonId = lessonId,
                onStudentClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(STUDENT_ID_KEY, it)
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        LESSON_ID_KEY,
                        lessonId
                    )
                    navController.navigate(StudentDetailsDestination.route)
                },
                onBackClick = navController::popBackStack
            )
        }
    }
}

internal fun NavGraphBuilder.magazineStudentDetailsService(
    navController: NavController
) {
    composable(StudentDetailsDestination.route) {
        val studentId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(STUDENT_ID_KEY)
        val lessonId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(LESSON_ID_KEY)

        if (studentId != null && lessonId != null) {
            StudentDetailsScreenEnter(
                groupMemberId = studentId,
                lessonId = lessonId,
                onHistoryClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        STUDENT_ID_KEY,
                        studentId
                    )
                    navController.navigate(HistoryDateDestination.route)
                },
                onBackClick = navController::popBackStack
            )
        }
    }
}
























