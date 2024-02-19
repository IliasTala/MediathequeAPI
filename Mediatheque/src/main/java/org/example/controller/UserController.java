package org.example.controller;


import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.findAllUsers();
    }

    @PostMapping("/save")
    public User save(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long userId) {
        userService.deleteUserById(userId);
    }

    @GetMapping("/getBy/{id}")
    public ResponseEntity<User> getById(@PathVariable(name = "id") Long userId) {
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}