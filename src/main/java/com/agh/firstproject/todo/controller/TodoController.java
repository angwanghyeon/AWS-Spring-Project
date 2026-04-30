package com.agh.firstproject.todo.controller;

import com.agh.firstproject.todo.dto.TodoResponse;
import com.agh.firstproject.todo.entity.Todo;
import com.agh.firstproject.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.agh.firstproject.todo.dto.TodoCreateRequest;
import com.agh.firstproject.todo.dto.TodoUpdateRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<TodoResponse> getAll() {
        return todoService.getAll()
                .stream()
                .map(TodoResponse::new)
                .toList();
    }

    @PostMapping
    public TodoResponse create(@Valid @RequestBody TodoCreateRequest request) {
        Todo todo = todoService.create(request.getContent());
        return new TodoResponse(todo);
    }

    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Long id,
                       @RequestBody TodoUpdateRequest request) {
        Todo todo = todoService.update(id, request.getCompleted());
        return new TodoResponse(todo);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        todoService.delete(id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", "삭제 완료");
        return response;
    }
}