package com.example.enrollmentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "student-service", url = "${student.service.url}")
public interface StudentClient {
    
    @GetMapping("/api/students/{id}")
    StudentDTO getStudent(@PathVariable("id") Long id);
    
    record StudentDTO(
        Long id,
        String name,
        String email
    ) {}
} 