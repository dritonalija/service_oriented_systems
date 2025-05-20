package com.example.studentservice.controller;
import com.example.studentservice.entity.Student;
import com.example.studentservice.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository repo;
    public StudentController(StudentRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Student> getAll() { return repo.findAll(); }

    @PostMapping
    public Student create(@RequestBody Student s) { return repo.save(s); }
} 