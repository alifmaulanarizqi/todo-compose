package com.example.todocompose.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.data.model.Priority
import com.example.todocompose.data.model.ToDoTaskEntity
import com.example.todocompose.data.repository.ToDoRepository
import com.example.todocompose.util.Constants.MAX_TITLE_LENGTH
import com.example.todocompose.util.RequestState
import com.example.todocompose.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
): ViewModel() {

    private val _allTasks = MutableStateFlow<RequestState<List<ToDoTaskEntity>>>(RequestState.Idle)
    val allTasks = _allTasks

    val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(
        SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _selectedTask: MutableStateFlow<ToDoTaskEntity?> = MutableStateFlow(null)
    val selectedTask: MutableStateFlow<ToDoTaskEntity?> = _selectedTask

    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)


    fun getAllTask() {
        _allTasks.value = RequestState.Loading

        try {
            viewModelScope.launch {
                repository.getAllTasks.collect { tasks ->
                    _allTasks.value = RequestState.Success(tasks)
                }
            }
        } catch (e: Exception) {
            _allTasks.value = RequestState.Error(e)
        }

    }

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            repository.getSelectedTask(taskId).collect { task ->
                _selectedTask.value = task
            }
        }
    }

    fun updateTaskFields(
        selectedTask: ToDoTaskEntity?
    ) {
        if(selectedTask != null) {
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    fun updateTitle(newTitle: String) {
        if(newTitle.length < MAX_TITLE_LENGTH) {
            title.value = newTitle
        }
    }

    fun validateFields(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }
}