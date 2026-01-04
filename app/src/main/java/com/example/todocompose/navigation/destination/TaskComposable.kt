package com.example.todocompose.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todocompose.ui.screen.task.TaskScreen
import com.example.todocompose.util.Action
import com.example.todocompose.util.Constants.TASK_ARGUMENT_KEY
import com.example.todocompose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable (
        TASK_SCREEN,
        listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

        TaskScreen(
            navigateToListScreen = navigateToListScreen
        )
    }
}