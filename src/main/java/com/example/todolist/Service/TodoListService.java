package com.example.todolist.Service;

import com.example.todolist.Interface.TodoListRepository;
import com.example.todolist.Model.TodoList;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;


    public TodoList createTodoList(TodoList todolist) {
        todolist.setDate(LocalDate.now());
        Long maxCodigo = todoListRepository.findAll().stream()
                .mapToLong(TodoList::getCodigo)
                .max()
                .orElse(0L);
        todolist.setCodigo(maxCodigo + 1);
        todolist.setAtivacao(true);
        todolist.setStatus(false);
        return todoListRepository.save(todolist);
    }

    public List<TodoList> readLists() {
        return todoListRepository.findAll();
    }

    public void updateTodo(Long id, TodoList todolist) {
        var entity = todoListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoList not found"));
        entity.setTitle(todolist.getTitle());
        entity.setDescription(todolist.getDescription());
        entity.setTodo(todolist.getTodo());
        todoListRepository.save(entity);
    }

    public void destroyTodoList(Long id) {
        var entity = todoListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoList not found"));
        todoListRepository.delete(entity);
    }

    public void desactiveTodoList(Long id) {
        var entity = todoListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoList not found"));
        entity.setAtivacao(false);
        todoListRepository.save(entity);
    }

    public List<TodoList> getTodoListByTitle(String title) {
        return todoListRepository.findByTitleContaining(title);
    }

    public List<TodoList> getTodoListByDate(LocalDate date) {
        return todoListRepository.findAllByDate(date);
    }

    public TodoList getTodoListByCodigo(Long codigo) {
        return todoListRepository.findByCodigo(codigo);
    }

    public TodoList listCompleted (Long id) {
        var entity = todoListRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TodoList not found"));
        entity.setStatus(true);
        return todoListRepository.save(entity);
    }


}
