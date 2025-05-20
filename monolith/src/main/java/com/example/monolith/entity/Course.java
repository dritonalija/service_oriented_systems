package com.example.monolith.entity;
import jakarta.persistence.*;

@Entity
public class Course {
    @Id @GeneratedValue
    private Long id;
    private String title;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
} 