package com.example.todolist.Service;

import com.example.todolist.Interface.TodoListRepository;
import com.example.todolist.Model.TodoList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;


    public TodoList createTodoList(TodoList todolist) {
        return todoListRepository.save(todolist);
    }

    public List<TodoList> readLists() {
        return todoListRepository.findAll();
    }

    public void updateTodo(Long id, TodoList todolist){
        var entity = todoListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("TodoList not found"));
        entity.setTitle(todolist.getTitle());
        entity.setDescription(todolist.getDescription());
        entity.setDate(todolist.getDate());
        entity.setTodo(todolist.getTodo());
        todoListRepository.save(entity);
    }

    public void deleteTodoList(Long id) {
        var entity = todoListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoList not found"));
        todoListRepository.delete(entity);
    }

    public List<TodoList> getTodoListByTitle(String title) {
        return todoListRepository.findByTitleContaining(title);
    }
}
