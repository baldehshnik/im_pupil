package com.sparkfusion.navigation.adminservicesport.magazine

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.magazine.destination.MagazineHistoryDateDestination
import com.sparkfusion.services.admin.magazine.destination.MagazineHistoryDestination
import com.sparkfusion.services.admin.magazine.destination.MagazineScheduleDestination
import com.sparkfusion.services.admin.magazine.destination.MagazineStudentDetailsDestination
import com.sparkfusion.services.admin.magazine.destination.MagazineStudentsListDestination
import com.sparkfusion.services.admin.magazine.key.DATE_KEY
import com.sparkfusion.services.admin.magazine.key.FACULTY_ID_KEY
import com.sparkfusion.services.admin.magazine.key.GROUP_ID_KEY
import com.sparkfusion.services.admin.magazine.key.GROUP_MEMBER_ID_KEY
import com.sparkfusion.services.admin.magazine.key.LESSON_ID_KEY
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
                navController.currentBackStackEntry?.savedStateHandle?.set(FACULTY_ID_KEY, it)
                navController.navigate(MagazineScheduleDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.magazineScheduleScreen(
    navController: NavController
) {
    composable(MagazineScheduleDestination.route) {
        val facultyId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(FACULTY_ID_KEY)

        if (facultyId != null) {
            MagazineScheduleScreen(
                facultyId = facultyId,
                onBackClick = { navController.popBackStack() },
                onItemClick = { lessonId, groupId ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        LESSON_ID_KEY,
                        lessonId
                    )
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        GROUP_ID_KEY,
                        groupId
                    )
                    navController.navigate(MagazineStudentsListDestination.route)
                },
                onHistoryClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(GROUP_ID_KEY, it)
                    navController.navigate(MagazineHistoryDateDestination.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.magazineStudentsListScreen(
    navController: NavController
) {
    composable(MagazineStudentsListDestination.route) {
        val lessonId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(LESSON_ID_KEY)
        val groupId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(GROUP_ID_KEY)

        if (lessonId != null && groupId != null) {
            MagazineStudentsListScreen(
                lessonId = lessonId,
                groupId = groupId,
                onBackClick = { navController.popBackStack() },
                onStudentClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        GROUP_MEMBER_ID_KEY,
                        it
                    )
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        LESSON_ID_KEY,
                        lessonId
                    )
                    navController.navigate(MagazineStudentDetailsDestination.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.magazineStudentDetailsScreen(
    navController: NavController
) {
    composable(MagazineStudentDetailsDestination.route) {
        val groupMemberId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(GROUP_MEMBER_ID_KEY)
        val lessonId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(LESSON_ID_KEY)

        if (groupMemberId != null && lessonId != null) {
            MagazineStudentDetailsScreen(
                groupMemberId = groupMemberId,
                lessonId = lessonId,
                onBackClick = { navController.popBackStack() },
                onHistoryClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        GROUP_MEMBER_ID_KEY,
                        groupMemberId
                    )
                    navController.navigate(MagazineHistoryDateDestination.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.magazineHistoryDateScreen(
    navController: NavController
) {
    composable(MagazineHistoryDateDestination.route) {
        val groupMemberId =
            navController.previousBackStackEntry?.savedStateHandle?.get<Int>(GROUP_MEMBER_ID_KEY)
        val groupId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(GROUP_ID_KEY)

        if (groupMemberId == null && groupId == null) return@composable
        MagazineHistoryDateScreen(
            groupMemberId = groupMemberId,
            groupId = groupId,
            onBackClick = { navController.popBackStack() },
            onSearchClick = { groupMember, date ->
                navController.currentBackStackEntry?.savedStateHandle?.set(GROUP_MEMBER_ID_KEY, groupMember)
                navController.currentBackStackEntry?.savedStateHandle?.set(DATE_KEY, date)
                navController.navigate(MagazineHistoryDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.magazineHistoryScreen(
    navController: NavController
) {
    composable(MagazineHistoryDestination.route) {
        val groupMemberId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(GROUP_MEMBER_ID_KEY)
        val date = navController.previousBackStackEntry?.savedStateHandle?.get<Long>(DATE_KEY)

        if (groupMemberId != null && date != null) {
            MagazineHistoryScreen(
                groupMemberId = groupMemberId,
                date = date,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
















