package com.centrodedia.controller;

import com.centrodedia.model.User;
import com.centrodedia.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/idnumber/{idNumber}")
    public ResponseEntity<User> getUserByIdNumber(@PathVariable String idNumber) {
        return userService.findByIdNumber(idNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name")
    public List<User> getUsersByName(@RequestParam String firstname, @RequestParam String lastname) {
        return userService.findByName(firstname, lastname);
    }

    @GetMapping("/relative/{id}")
    public List<User> getUsersByRelativeId(@PathVariable Long id) {
        return userService.findByRelativeId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User newUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable Long id, @Valid @RequestBody User user) {
        try {
            User actualizada = userService.update(id, user);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
