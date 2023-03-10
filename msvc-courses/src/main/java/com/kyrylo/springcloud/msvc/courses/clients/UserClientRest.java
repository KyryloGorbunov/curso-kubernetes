package com.kyrylo.springcloud.msvc.courses.clients;

import com.kyrylo.springcloud.msvc.courses.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msvc-usuarios", url = "msvc-users:8001")
public interface UserClientRest {

    @GetMapping("/{id}")
    User findById(@PathVariable Long id);

    @PostMapping("/")
    User create(@RequestBody User user);

    @GetMapping("/users-by-course")
    List<User> getUsersByCourse(@RequestParam Iterable<Long> ids);
}
