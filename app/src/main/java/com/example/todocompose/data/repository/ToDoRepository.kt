package com.example.todocompose.data.repository

import androidx.room.Dao
import com.example.todocompose.data.dao.TodoTaskDao
import com.example.todocompose.data.model.ToDoTaskEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ToDoRepository @Inject constructor(
    private val toDoTaskDao: TodoTaskDao
) {
    val getAllTasks = toDoTaskDao.getAllTasks()
    val sortByLowPriority = toDoTaskDao.sortByLowPriority()
    val sortByHighPriority = toDoTaskDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int): Flow<ToDoTaskEntity> {
        return toDoTaskDao.getSelectedTask(taskId)
    }

    suspend fun addTask(toDoTaskEntity: ToDoTaskEntity) {
        toDoTaskDao.addTask(toDoTaskEntity)
    }

    suspend fun updateTask(toDoTaskEntity: ToDoTaskEntity) {
        toDoTaskDao.updateTask(toDoTaskEntity)
    }

    suspend fun deleteTask(toDoTaskEntity: ToDoTaskEntity) {
        toDoTaskDao.deleteTask(toDoTaskEntity)
    }

    suspend fun deleteAllTask() {
        toDoTaskDao.deleteAllTask()
    }

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTaskEntity>> {
        return toDoTaskDao.searchDatabase(searchQuery)
    }
}