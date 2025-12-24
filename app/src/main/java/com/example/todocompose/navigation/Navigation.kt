package com.example.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todocompose.navigation.destination.listComposable
import com.example.todocompose.navigation.destination.taskComposable
import com.example.todocompose.util.Constants.LIST_SCREEN

@Composable
fun Navigation(
    navHostController: NavHostController
) {
    val screen = remember(navHostController) {
        Screens(navHostController)
    }

    NavHost(
        navHostController,
        LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.task
        )
        taskComposable(
            navigateToListScreen = screen.list
        )
    }
}