package com.kyrylo.springcloud.msvc.courses.services;

import com.kyrylo.springcloud.msvc.courses.clients.UserClientRest;
import com.kyrylo.springcloud.msvc.courses.models.User;
import com.kyrylo.springcloud.msvc.courses.models.entity.Course;
import com.kyrylo.springcloud.msvc.courses.models.entity.CourseUser;
import com.kyrylo.springcloud.msvc.courses.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository repository;

    private UserClientRest client;

    public CourseServiceImpl(CourseRepository repository, UserClientRest client) {
        this.repository = repository;
        this.client = client;
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

    @Override
    @Transactional
    public Optional<User> assignUser(User user, Long courseId) {
        Optional<Course> optionalCourse = repository.findById(courseId);
        if (optionalCourse.isPresent()) {
            User userMsvc = client.findById(user.getId());

            Course course = optionalCourse.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userMsvc.getId());

            course.addCourseUser(courseUser);
            repository.save(course);
            return Optional.of(userMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user, Long courseId) {
        Optional<Course> optionalCourse = repository.findById(courseId);
        if (optionalCourse.isPresent()) {
            User userNewMsvc = client.create(user);

            Course course = optionalCourse.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userNewMsvc.getId());

            course.addCourseUser(courseUser);
            repository.save(course);
            return Optional.of(userNewMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> deleteUser(User user, Long courseId) {
        Optional<Course> optionalCourse = repository.findById(courseId);
        if (optionalCourse.isPresent()) {
            User userMsvc = client.findById(user.getId());

            Course course = optionalCourse.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userMsvc.getId());

            course.removeCourseUser(courseUser);
            repository.save(course);
            return Optional.of(userMsvc);
        }
        return Optional.empty();
    }
}
