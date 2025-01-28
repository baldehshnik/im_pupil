package com.sparkfusion.navigation.adminservicesport.students

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.students.destination.StudentsAccountDestination
import com.sparkfusion.services.admin.students.destination.StudentsAddGroupDestination
import com.sparkfusion.services.admin.students.destination.StudentsEditGroupDestination
import com.sparkfusion.services.admin.students.destination.StudentsGroupDestination
import com.sparkfusion.services.admin.students.destination.StudentsListDestination
import com.sparkfusion.services.admin.students.key.FACULTY_ID_KEY
import com.sparkfusion.services.admin.students.key.GROUP_ID_KEY
import com.sparkfusion.services.admin.students.key.STUDENT_ID_KEY
import com.sparkfusion.services.admin.students.screen.AccountScreen
import com.sparkfusion.services.admin.students.screen.AddGroupScreen
import com.sparkfusion.services.admin.students.screen.EditGroupScreen
import com.sparkfusion.services.admin.students.screen.GroupScreen
import com.sparkfusion.services.admin.students.screen.StudentsFacultyScreen
import com.sparkfusion.services.admin.students.screen.StudentsScreen

fun NavGraphBuilder.studentsFacultyScreen(
    navController: NavHostController
) {
    composable(StudentsFacultyDestination.route) {
        StudentsFacultyScreen(
            onGroupScreenNavigate = {
                navController.currentBackStackEntry?.savedStateHandle?.set(FACULTY_ID_KEY, it)
                navController.navigate(StudentsGroupDestination.route)
            },
            onBackClick = { navController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.studentsGroupScreen(
    navController: NavHostController
) {
    composable(StudentsGroupDestination.route) {
        val facultyId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(FACULTY_ID_KEY)
        if (facultyId != null) {
            GroupScreen(
                facultyId = facultyId,
                onBackClick = { navController.popBackStack() },
                onAddGroupClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(FACULTY_ID_KEY, facultyId)
                    navController.navigate(StudentsAddGroupDestination.route)
                },
                onGroupClick = { groupId ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        GROUP_ID_KEY,
                        groupId
                    )
                    navController.navigate(StudentsListDestination.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.studentsListScreen(
    navController: NavController
) {
    composable(StudentsListDestination.route) {
        val groupId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(GROUP_ID_KEY)
        if (groupId != null) {
            StudentsScreen(
                groupId = groupId,
                onBackClick = { navController.popBackStack() },
                onStudentClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(STUDENT_ID_KEY, it)
                    navController.navigate(StudentsAccountDestination.route)
                },
                onEditClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        GROUP_ID_KEY,
                        groupId
                    )
                    navController.navigate(StudentsEditGroupDestination.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.studentsEditGroupScreen(
    navController: NavController
) {
    composable(StudentsEditGroupDestination.route) {
        val groupId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(GROUP_ID_KEY)
        groupId?.let {
            EditGroupScreen(
                groupId = groupId,
                onBackClick = { navController.popBackStack() },
                onDeleteSuccess = { navController.popBackStack(StudentsListDestination.route, inclusive = true) }
            )
        }
    }
}

fun NavGraphBuilder.studentsAddGroupScreen(
    navController: NavController
) {
    composable(StudentsAddGroupDestination.route) {
        val facultyId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(FACULTY_ID_KEY)
        facultyId?.let {
            AddGroupScreen(
                facultyId = facultyId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

fun NavGraphBuilder.studentsAccountScreen(
    navController: NavController
) {
    composable(StudentsAccountDestination.route) {
        val studentId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(STUDENT_ID_KEY)
        studentId?.let {
            AccountScreen(
                studentId = studentId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}


























