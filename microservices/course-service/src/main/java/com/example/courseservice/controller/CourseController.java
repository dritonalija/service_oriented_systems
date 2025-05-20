package com.example.courseservice.controller;
import com.example.courseservice.entity.Course;
import com.example.courseservice.repository.CourseRepository;
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