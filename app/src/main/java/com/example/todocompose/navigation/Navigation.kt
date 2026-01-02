package com.example.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todocompose.navigation.destination.listComposable
import com.example.todocompose.navigation.destination.taskComposable
import com.example.todocompose.ui.viewmodel.SharedViewModel
import com.example.todocompose.util.Action
import com.example.todocompose.util.Constants.LIST_SCREEN

@Composable
fun Navigation(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navHostController) {
        Screens(navHostController)
    }

    NavHost(
        navHostController,
        "list/${Action.NO_ACTION.name}"
    ) {
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.list
        )
    }
}