package com.example.demosecurity.Controller;

import com.example.demosecurity.Model.MyUser;
import com.example.demosecurity.Model.Todo;
import com.example.demosecurity.Service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Todo>> getAllTodos(){
        return ResponseEntity.status(200).body(todoService.getTodos());
    }


    @GetMapping("/get")
    public ResponseEntity getTodo(@AuthenticationPrincipal MyUser myUser){

        List<Todo> todoList=todoService.getTodo(myUser.getId());
        return ResponseEntity.status(200).body(todoList);
    }


    @PostMapping("/add")
    public ResponseEntity addTodo(@AuthenticationPrincipal MyUser myUser, @RequestBody Todo todo){
        todoService.addTodo(myUser.getId(),todo);

        return ResponseEntity.status(200).body("todo added");
    }

    @PutMapping("/update/{todoId}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal MyUser myUser,@RequestBody Todo todo,@PathVariable Integer todoId) {

        todoService.updateTodo(myUser.getId(),todo,todoId);
        return ResponseEntity.status(200).body("todo updated");
    }

    @DeleteMapping("/delete/{todoId}")
    public ResponseEntity deleteTodo(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer todoId) {

        todoService.deleteTodo(myUser.getId(),todoId);
        return ResponseEntity.status(200).body("todo deleted");
    }

}