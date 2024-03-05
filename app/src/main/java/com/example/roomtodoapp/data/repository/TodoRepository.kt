package com.example.roomtodoapp.data.repository

import com.example.roomtodoapp.data.TodoDao
import com.example.roomtodoapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val dao: TodoDao
) {
    suspend fun insertTodo(todo: Todo) = dao.insertTodo(todo = todo)

    suspend fun updateTodo(todo: Todo) = dao.updateTodo(todo = todo )

    suspend fun deleteTodo(todo: Todo) = dao.deleteTodo(todo = todo)

    suspend fun getTodoById(id: Int) = dao.getTodoById(id = id)

    fun getAllTodo(): Flow<List<Todo>> = dao.getAllTodos()

}