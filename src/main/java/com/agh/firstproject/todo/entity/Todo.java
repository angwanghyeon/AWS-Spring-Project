package com.agh.firstproject.todo.entity;

import jakarta.persistence.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private boolean completed;

    // getter / setter
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public  boolean isCompleted() {
        return completed;
    }

    public  void  setContent(String content) {
        this.content = content;
    }

    public void  setCompleted(boolean completed) {
        this.completed = completed;
    }
}
