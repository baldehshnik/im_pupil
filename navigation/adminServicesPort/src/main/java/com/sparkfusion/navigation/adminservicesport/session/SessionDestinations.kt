package com.sparkfusion.navigation.adminservicesport.session

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.services.admin.session.destination.SessionTestAddingDestination
import com.sparkfusion.services.admin.session.destination.SessionTestEditingDestination
import com.sparkfusion.services.admin.session.key.EXAM_ID_KEY
import com.sparkfusion.services.admin.session.key.GROUP_ID_KEY
import com.sparkfusion.services.admin.session.key.SESSION_TYPE_KEY
import com.sparkfusion.services.admin.session.screen.SessionGroupTestsScreen
import com.sparkfusion.services.admin.session.screen.SessionTestAddingScreen
import com.sparkfusion.services.admin.session.screen.SessionTestEditingScreen

fun NavGraphBuilder.sessionGroupTestsScreen(
    navController: NavController
) {
    composable(SessionGroupTestsDestination.route) {
        SessionGroupTestsScreen(
            onBackClick = { navController.popBackStack() },
            onAddItemClick = { groupId, type ->
                navController.currentBackStackEntry?.savedStateHandle?.set(GROUP_ID_KEY, groupId)
                navController.currentBackStackEntry?.savedStateHandle?.set(SESSION_TYPE_KEY, type)
                navController.navigate(SessionTestAddingDestination.route)
            },
            onEditItemClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(EXAM_ID_KEY, it)
                navController.navigate(SessionTestEditingDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.sessionTestAddingScreen(
    navController: NavController
) {
    composable(SessionTestAddingDestination.route) {
        val groupId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(GROUP_ID_KEY)
        val sessionType = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(SESSION_TYPE_KEY)

        if (groupId != null && sessionType != null) {
            SessionTestAddingScreen(
                groupId = groupId,
                sessionType = sessionType,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

fun NavGraphBuilder.sessionTestEditingScreen(
    navController: NavController
) {
    composable(SessionTestEditingDestination.route) {
        val id = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(EXAM_ID_KEY)
        if (id != null) {
            SessionTestEditingScreen(
                id = id,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}





















