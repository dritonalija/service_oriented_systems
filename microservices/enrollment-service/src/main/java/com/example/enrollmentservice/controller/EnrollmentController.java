package com.example.enrollmentservice.controller;
import com.example.enrollmentservice.entity.Enrollment;
import com.example.enrollmentservice.repository.EnrollmentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentRepository repo;
    public EnrollmentController(EnrollmentRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Enrollment> getAll() { return repo.findAll(); }

    @PostMapping
    public Enrollment create(@RequestBody Enrollment e) { return repo.save(e); }
} 