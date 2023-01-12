package com.kyrylo.springcloud.msvc.usuarios.services;

import com.kyrylo.springcloud.msvc.usuarios.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void delete(Long id);
    List<User> findAllById(Iterable<Long> ids);

    Optional<User> findByEmail(String email);
}
