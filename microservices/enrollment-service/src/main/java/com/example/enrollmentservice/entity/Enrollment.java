package com.example.enrollmentservice.entity;
import jakarta.persistence.*;

@Entity
public class Enrollment {
    @Id @GeneratedValue
    private Long id;
    private Long studentId;
    private Long courseId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
} 