package com.agh.firstproject.todo.controller;

import com.agh.firstproject.common.ApiResponse;
import com.agh.firstproject.todo.dto.TodoResponse;
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
    public ApiResponse<List<TodoResponse>> getAll() {
        List<TodoResponse> result = todoService.getAll()
                .stream()
                .map(TodoResponse::new)
                .toList();

        return ApiResponse.success(result);
    }

    @PostMapping
    public ApiResponse<TodoResponse> create(@Valid @RequestBody TodoCreateRequest request) {
        Todo todo = todoService.create(request.getContent());
        return ApiResponse.success(new TodoResponse(todo));
    }

    @PutMapping("/{id}")
    public ApiResponse<TodoResponse> update(@PathVariable Long id,
                       @RequestBody TodoUpdateRequest request) {
        Todo todo = todoService.update(id, request.getCompleted());
        return ApiResponse.success(new TodoResponse(todo));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ApiResponse.success();
    }
}