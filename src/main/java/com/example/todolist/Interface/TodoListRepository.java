package com.example.todolist.Interface;

import com.example.todolist.Model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    List<TodoList> findByTitleContaining(String title);
    List<TodoList> findAllByDate(LocalDate date);
    TodoList findByCodigo(Long codigo);
}
