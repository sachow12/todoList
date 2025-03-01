package com.example.todolist.Controller;

import com.example.todolist.Interface.TodoListRepository;
import com.example.todolist.Model.TodoList;
import com.example.todolist.Service.TodoListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
            todoListService.destroyTodoList(id);
            return ResponseEntity.ok("TodoList deleted successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoList not found with id: " + id);
        }
    }

    @PutMapping("/desactive/{id}")
    public ResponseEntity<String> desactiveTodoList(@PathVariable Long id) {
        try {
            todoListService.desactiveTodoList(id);
            return ResponseEntity.ok("TodoList desactivated successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("TodoList not found with id: " + id);
        }
    }

    @GetMapping("/searchByTitle")
    public ResponseEntity<?> searchByTitle(@RequestParam String title) {
        try {
            List<TodoList> todoLists = todoListService.getTodoListByTitle(title);
            if (todoLists.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nenhuma lista de tarefas encontrada com o título: " + title);
            }

            return ResponseEntity.ok(todoLists);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao processar a solicitação: " + e.getMessage());
        }
    }

    @GetMapping("/searchByDate")
    public ResponseEntity<?> searchByDate(@RequestParam LocalDate date) {
        try {
            List<TodoList> todoLists = todoListService.getTodoListByDate(date);
            if (todoLists.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nenhuma lista de tarefas encontrada com a data: " + date);
            }

            return ResponseEntity.ok(todoLists);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao processar a solicitação: " + e.getMessage());
        }
    }

    @GetMapping("/searchByCodigo")
    public ResponseEntity<?> searchByCodigo(@RequestParam Long codigo) {
        try {
            TodoList todoList = todoListService.getTodoListByCodigo(codigo);
            if (todoList == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nenhuma lista de tarefas encontrada com o código: " + codigo);
            }

            return ResponseEntity.ok(todoList);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao processar a solicitação: " + e.getMessage());
        }
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<?> completeTodoList(@PathVariable Long id) {
        try {
            TodoList todoList = todoListService.listCompleted(id);
            return ResponseEntity.ok(todoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao processar a solicitação: " + e.getMessage());
        }
    }

}
