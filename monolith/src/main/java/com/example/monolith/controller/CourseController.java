package com.example.monolith.controller;
import com.example.monolith.entity.Course;
import com.example.monolith.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository repo;
    public CourseController(CourseRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Course> getAll() { return repo.findAll(); }

    @PostMapping
    public Course create(@RequestBody Course c) { return repo.save(c); }
} 