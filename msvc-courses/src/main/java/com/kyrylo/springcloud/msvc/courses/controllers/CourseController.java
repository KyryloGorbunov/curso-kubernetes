package com.kyrylo.springcloud.msvc.courses.controllers;

import com.kyrylo.springcloud.msvc.courses.entity.Course;
import com.kyrylo.springcloud.msvc.courses.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    private CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Course> optionalCourse = service.findById(id);
        if (optionalCourse.isPresent()) {
            return ResponseEntity.ok(optionalCourse.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Course course, @PathVariable Long id) {
        Optional<Course> optionalCourse = service.findById(id);
        if (optionalCourse.isPresent()) {
            Course courseDb = optionalCourse.get();
            courseDb.setName(course.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Course> optionalCourse = service.findById(id);
        if (optionalCourse.isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
