package com.example.todocompose.navigation.destination

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todocompose.ui.screen.task.TaskScreen
import com.example.todocompose.ui.viewmodel.SharedViewModel
import com.example.todocompose.util.Action
import com.example.todocompose.util.Constants.TASK_ARGUMENT_KEY
import com.example.todocompose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel,
) {
    composable (
        TASK_SCREEN,
        listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

        sharedViewModel.getSelectedTask(taskId)

        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        TaskScreen(
            navigateToListScreen = navigateToListScreen,
            selectedTask = selectedTask
        )
    }
}