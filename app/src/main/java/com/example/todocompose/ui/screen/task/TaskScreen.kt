package com.example.todocompose.ui.screen.task

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import com.example.todocompose.data.model.ToDoTaskEntity
import com.example.todocompose.util.Action

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit,
    selectedTask: ToDoTaskEntity?
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = navigateToListScreen,
                selectedTask = selectedTask
            )
        },
        content = {}
    )
}