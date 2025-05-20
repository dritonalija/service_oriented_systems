package com.example.enrollmentservice.service;

import com.example.enrollmentservice.client.StudentClient;
import com.example.enrollmentservice.client.CourseClient;
import com.example.enrollmentservice.entity.Enrollment;
import com.example.enrollmentservice.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {
    
    private final EnrollmentRepository enrollmentRepository;
    private final StudentClient studentClient;
    private final CourseClient courseClient;
    
    public EnrollmentService(
        EnrollmentRepository enrollmentRepository,
        StudentClient studentClient,
        CourseClient courseClient
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentClient = studentClient;
        this.courseClient = courseClient;
    }
    
    @Transactional
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        // Verifiko nëse studenti ekziston
        var student = studentClient.getStudent(studentId);
        if (student == null) {
            throw new RuntimeException("Studenti nuk u gjet");
        }
        
        // Verifiko nëse kursi ekziston dhe ka vende
        var course = courseClient.getCourse(courseId);
        if (course == null) {
            throw new RuntimeException("Kursi nuk u gjet");
        }
        
        if (course.enrolledStudents() >= course.capacity()) {
            throw new RuntimeException("Kursi është i plotë");
        }
        
        // Krijo regjistrimin
        var enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        
        return enrollmentRepository.save(enrollment);
    }
} 