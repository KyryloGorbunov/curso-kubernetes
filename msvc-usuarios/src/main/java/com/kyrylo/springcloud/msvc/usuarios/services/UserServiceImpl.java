package com.kyrylo.springcloud.msvc.usuarios.services;

import com.kyrylo.springcloud.msvc.usuarios.clients.CourseClientRest;
import com.kyrylo.springcloud.msvc.usuarios.models.entity.User;
import com.kyrylo.springcloud.msvc.usuarios.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository repository;

    private CourseClientRest client;

    public UserServiceImpl(UserRepository repository, CourseClientRest client) {
        this.repository = repository;
        this.client = client;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        client.deleteCourseUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllById(Iterable<Long> ids) {
        return (List<User>) repository.findAllById(ids);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
