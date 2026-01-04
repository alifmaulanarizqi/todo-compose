package com.example.todocompose.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.data.model.ToDoTaskEntity
import com.example.todocompose.data.repository.ToDoRepository
import com.example.todocompose.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
): ViewModel() {

    private val _allTasks = MutableStateFlow<List<ToDoTaskEntity>>(emptyList())
    val allTasks = _allTasks

    val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(
        SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")

    fun getAllTask() {
        viewModelScope.launch {
            repository.getAllTasks.collect { tasks ->
                _allTasks.value = tasks
            }
        }
    }
}