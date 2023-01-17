package com.kyrylo.springcloud.msvc.usuarios.controllers;

import com.kyrylo.springcloud.msvc.usuarios.models.entity.User;
import com.kyrylo.springcloud.msvc.usuarios.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public Map<String, List<User>> findAll() {
        return Collections.singletonMap("users", service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        if (!user.getEmail().isEmpty() && service.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("message", "There is already a user with this email."));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isPresent()) {
            User userDb = userOptional.get();
            if (!user.getEmail().isEmpty() && !user.getEmail().equalsIgnoreCase(userDb.getEmail())
                    && service.findByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("message", "There is already a consumer with this email."));
            }
            userDb.setName(user.getName());
            userDb.setEmail(user.getEmail());
            userDb.setPassword(user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/users-by-course")
    public ResponseEntity<?> getUsersByCourse(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.findAllById(ids));
    }

    private ResponseEntity<Map<String, String>> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
