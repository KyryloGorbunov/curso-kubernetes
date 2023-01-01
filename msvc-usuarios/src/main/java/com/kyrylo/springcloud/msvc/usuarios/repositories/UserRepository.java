package com.kyrylo.springcloud.msvc.usuarios.repositories;

import com.kyrylo.springcloud.msvc.usuarios.models.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
