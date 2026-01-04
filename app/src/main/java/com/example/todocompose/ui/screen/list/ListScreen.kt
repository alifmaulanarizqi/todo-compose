package com.example.todocompose.ui.screen.list

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.todocompose.R
import com.example.todocompose.ui.viewmodel.SharedViewModel
import com.example.todocompose.util.SearchAppBarState

@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel,
) {
    LaunchedEffect(Unit) {
        sharedViewModel.getAllTask()
    }

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    Scaffold(
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        floatingActionButton = {
            ListFab(navigateToTaskScreen)
        }
    ) { paddingValues ->
        ListContent(
            tasks = allTasks,
            navigateToTaskScreen = navigateToTaskScreen,
            paddingValues = paddingValues
        )
    }
}

@Composable
fun ListFab(
    onClick: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onClick(-1)
        },
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_button),
        )
    }
}