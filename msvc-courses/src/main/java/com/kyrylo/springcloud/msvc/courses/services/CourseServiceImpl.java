package com.kyrylo.springcloud.msvc.courses.services;

import com.kyrylo.springcloud.msvc.courses.entity.Course;
import com.kyrylo.springcloud.msvc.courses.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return (List<Course>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return repository.save(course);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
