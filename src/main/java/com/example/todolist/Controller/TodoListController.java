package com.example.todolist.Controller;

import com.example.todolist.Interface.TodoListRepository;
import com.example.todolist.Model.TodoList;
import com.example.todolist.Service.TodoListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/todolist")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @Autowired
    private TodoListRepository todoListRepository;

    @GetMapping("/getAll")
    public List<TodoList> readLists() {
        return todoListService.readLists();
    }

    @PostMapping("/create")
    public TodoList createTodoList(@RequestBody TodoList todolist) {
        return todoListService.createTodoList(todolist);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable Long id, @RequestBody TodoList todolist) {
        try {
            todoListService.updateTodo(id, todolist);
            return ResponseEntity.ok("TodoList updated successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoList not found with id: " + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodoList(@PathVariable Long id) {
        try {
            todoListService.deleteTodoList(id);
            return ResponseEntity.ok("TodoList deleted successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoList not found with id: " + id);
        }
    }

    @GetMapping("/getByTitle/{title}")
    public TodoList findByTitle(@PathVariable String title) {
        return todoListService.findByTitle(title);
    }
}
