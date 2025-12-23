package com.example.todocompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todocompose.data.dao.TodoTaskDao
import com.example.todocompose.data.model.ToDoTaskEntity

@Database(entities = [ToDoTaskEntity::class], version = 1, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase(){
    abstract fun todoTaskDao(): TodoTaskDao
}