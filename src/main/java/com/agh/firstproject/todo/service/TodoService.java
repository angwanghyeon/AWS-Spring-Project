package com.agh.firstproject.todo.service;

import com.agh.firstproject.todo.entity.Todo;
import com.agh.firstproject.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.agh.firstproject.todo.exception.TodoNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 전체 조회
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    // 생성
    public Todo create(String content) {
        Todo todo = new Todo();
        todo.setContent(content);
        todo.setCompleted(false);
        return todoRepository.save(todo);
    }

    // 수정
    public Todo update(Long id, boolean completed) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        todo.setCompleted(completed);
        return todoRepository.save(todo);
    }

    // 삭제
    public void delete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        todoRepository.delete(todo);
    }
}