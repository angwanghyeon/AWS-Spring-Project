package com.agh.firstproject.todo.controller;

import com.agh.firstproject.todo.entity.Todo;
import com.agh.firstproject.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.agh.firstproject.todo.dto.TodoCreateRequest;
import com.agh.firstproject.todo.dto.TodoUpdateRequest;

import java.util.List;

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
    public Todo create(@Valid @RequestBody TodoCreateRequest request) {
        return todoService.create(request.getContent());
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id,
                       @RequestBody TodoUpdateRequest request) {
        return todoService.update(id, request.getCompleted());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }
}