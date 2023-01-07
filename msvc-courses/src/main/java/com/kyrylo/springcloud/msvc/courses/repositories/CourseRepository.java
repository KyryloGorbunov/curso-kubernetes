package com.kyrylo.springcloud.msvc.courses.repositories;

import com.kyrylo.springcloud.msvc.courses.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course ,Long> {
}
