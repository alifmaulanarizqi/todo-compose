package com.example.todocompose.ui.screen.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todocompose.ui.theme.topAppBarBackgroundColor
import com.example.todocompose.ui.theme.topAppBarContentColor
import com.example.todocompose.R
import com.example.todocompose.component.PriorityItem
import com.example.todocompose.data.model.Priority
import com.example.todocompose.ui.theme.LARGE_PADDING

@Composable
fun ListAppBar() {
    DefaultListAppBar(
        onSearchClicked = { },
        onSortClicked = { },
        onDeleteClicked = {},
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                "Tasks",
                color = MaterialTheme.colorScheme.topAppBarContentColor
            )
        },
        actions = {
            ListAppBarAction(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteClicked = onDeleteClicked,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor,
        )
    )
}

@Composable

fun ListAppBarAction(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(
        onClick = { onSearchClicked }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_action),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            expanded = true
        },
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_filter_list,
            ),
            contentDescription = stringResource(R.string.sort_action),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.LOW)
                },
                text = {
                    PriorityItem(priority = Priority.LOW)
                }
            )
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.HIGH)
                },
                text = {
                    PriorityItem(priority = Priority.HIGH)
                }
            )
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.NONE)
                },
                text = {
                    PriorityItem(priority = Priority.NONE)
                }
            )
        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            expanded = true
        },
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_delete_menu,
            ),
            contentDescription = stringResource(R.string.delete_all_action),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onDeleteClicked()
                },
                text = {
                    Text(
                        text = stringResource(R.string.delete_all_action),
                        modifier = Modifier.padding(start = LARGE_PADDING),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
        }
    }
}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )
}