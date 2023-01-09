package com.kyrylo.springcloud.msvc.usuarios.repositories;

import com.kyrylo.springcloud.msvc.usuarios.models.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.email=?1")
    Optional<User> findByEmailWithQuery(String email);

    boolean existsByEmail(String email);
}
