package com.example.monolith.controller;
import com.example.monolith.entity.Enrollment;
import com.example.monolith.repository.EnrollmentRepository;
import com.example.monolith.repository.StudentRepository;
import com.example.monolith.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentRepository repo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public EnrollmentController(EnrollmentRepository repo, StudentRepository studentRepo, CourseRepository courseRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @GetMapping
    public List<Enrollment> getAll() { return repo.findAll(); }

    @PostMapping
    public Enrollment create(@RequestBody EnrollmentRequest request) {
        var student = studentRepo.findById(request.getStudentId()).orElseThrow();
        var course = courseRepo.findById(request.getCourseId()).orElseThrow();
        Enrollment e = new Enrollment();
        e.setStudent(student);
        e.setCourse(course);
        return repo.save(e);
    }

    public static class EnrollmentRequest {
        private Long studentId;
        private Long courseId;

        public Long getStudentId() { return studentId; }
        public void setStudentId(Long studentId) { this.studentId = studentId; }
        public Long getCourseId() { return courseId; }
        public void setCourseId(Long courseId) { this.courseId = courseId; }
    }
} 