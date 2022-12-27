package com.kyrylo.springcloud.msvc.usuarios.services;

import java.util.List;
import java.util.Optional;

import com.kyrylo.springcloud.msvc.usuarios.models.entity.User;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void delete(Long id);
}
