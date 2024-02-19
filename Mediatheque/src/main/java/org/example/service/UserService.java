package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

}