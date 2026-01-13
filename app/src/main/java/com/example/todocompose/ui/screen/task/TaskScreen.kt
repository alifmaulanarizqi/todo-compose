package com.example.todocompose.ui.screen.task

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.todocompose.data.model.Priority
import com.example.todocompose.data.model.ToDoTaskEntity
import com.example.todocompose.ui.viewmodel.SharedViewModel
import com.example.todocompose.util.Action

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit,
    selectedTask: ToDoTaskEntity?,
    sharedViewModel: SharedViewModel
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = { action ->
                    if(action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if(sharedViewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            displayToast(context = context)
                        }
                    }
                },
                selectedTask = selectedTask
            )
        },
        content = { paddingValues ->
            TaskContent(
                modifier = Modifier.padding(paddingValues),
                title = title,
                onTitleChange = {
                    sharedViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    sharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it
                }
            )
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Fields Empty",
        Toast.LENGTH_SHORT
    ).show()
}