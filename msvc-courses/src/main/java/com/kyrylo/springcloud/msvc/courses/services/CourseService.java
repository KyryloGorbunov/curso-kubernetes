package com.kyrylo.springcloud.msvc.courses.services;

import com.kyrylo.springcloud.msvc.courses.models.User;
import com.kyrylo.springcloud.msvc.courses.models.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    Optional<Course>findByIdUsers(Long id);
    Course save(Course course);
    void delete(Long id);

    void deleteCourseUserById(Long id);

    Optional<User> assignUser(User user, Long courseId);
    Optional<User> createUser(User user, Long courseId);
    Optional<User> deleteUser(User user, Long courseId);
}
