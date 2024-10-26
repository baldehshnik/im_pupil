package com.sparkfusion.navigation.adminservicesport.students

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.students.destination.StudentsFacultyDestination
import com.sparkfusion.services.admin.students.destination.StudentsGroupDestination
import com.sparkfusion.services.admin.students.key.FACULTY_ID_KEY
import com.sparkfusion.services.admin.students.screen.FacultyScreen
import com.sparkfusion.services.admin.students.screen.GroupScreen

fun NavGraphBuilder.studentsFacultyScreen(
    navController: NavHostController
) {
    composable(StudentsFacultyDestination.route) {
        FacultyScreen(
            onGroupScreenNavigate = {
                navController.currentBackStackEntry?.savedStateHandle?.set(FACULTY_ID_KEY, it)
                navController.navigate(StudentsGroupDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.studentsGroupScreen(
    navController: NavHostController
) {
    composable(StudentsGroupDestination.route) {
        val facultyId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(FACULTY_ID_KEY)
        if (facultyId != null) {
            GroupScreen(
                facultyId = facultyId
            )
        } else {
            navController.popBackStack()
        }
    }
}
