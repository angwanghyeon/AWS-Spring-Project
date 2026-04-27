package com.agh.firstproject.todo.controller;

import com.agh.firstproject.todo.entity.Todo;
import com.agh.firstproject.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<Todo> getAll() {
        return todoService.getAll();
    }

    @PostMapping
    public Todo create(@RequestBody Map<String, String> request) {
        return todoService.create(request.get("content"));
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id,
                       @RequestBody Map<String, Boolean> request) {
        return todoService.update(id, request.get("completed"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }
}