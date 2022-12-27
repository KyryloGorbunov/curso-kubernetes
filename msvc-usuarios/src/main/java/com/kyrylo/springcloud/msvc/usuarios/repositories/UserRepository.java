package com.kyrylo.springcloud.msvc.usuarios.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kyrylo.springcloud.msvc.usuarios.models.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
