package com.example.enrollmentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service", url = "${course.service.url}")
public interface CourseClient {
    
    @GetMapping("/api/courses/{id}")
    CourseDTO getCourse(@PathVariable("id") Long id);
    
    record CourseDTO(
        Long id,
        String name,
        String code,
        int capacity,
        int enrolledStudents
    ) {}
} 