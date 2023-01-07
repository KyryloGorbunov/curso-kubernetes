package com.kyrylo.springcloud.msvc.courses.services;

import com.kyrylo.springcloud.msvc.courses.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    Course save(Course course);
    void delete(Long id);
}
