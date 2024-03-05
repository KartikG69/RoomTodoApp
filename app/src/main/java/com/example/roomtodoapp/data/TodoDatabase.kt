package com.example.roomtodoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomtodoapp.domain.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = true)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}