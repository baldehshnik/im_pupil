package com.sparkfusion.navigation.pupilservicesport.students

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.pupil.students.destination.StudentDetailsDestination
import com.sparkfusion.services.pupil.students.keys.STUDENT_ID_KEY
import com.sparkfusion.services.pupil.students.screen.StudentDetailsScreenEnter
import com.sparkfusion.services.pupil.students.screen.StudentsScreenEnter

internal fun NavGraphBuilder.studentsService(
    navController: NavController
) {
    composable(StudentsDestination.route) {
        StudentsScreenEnter(
            onStudentClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(STUDENT_ID_KEY, it)
                navController.navigate(StudentDetailsDestination.route)
            },
            onBackClick = navController::popBackStack
        )
    }
}

internal fun NavGraphBuilder.studentDetailsService(
    navController: NavController
) {
    composable(StudentDetailsDestination.route) {
        val id = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(STUDENT_ID_KEY)

        if (id != null) {
            StudentDetailsScreenEnter(
                studentId = id,
                onBackClick = navController::popBackStack
            )
        }
    }
}

















