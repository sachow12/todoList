package com.example.todolist.Interface;

import com.example.todolist.Model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    TodoList findByTitle(String title);
}
