package com.example.todocompose.ui.screen.task

import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import com.example.todocompose.util.Action

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = navigateToListScreen
            )
        },
        content = {}
    )
}