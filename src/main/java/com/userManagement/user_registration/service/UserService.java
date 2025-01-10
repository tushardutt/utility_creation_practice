package com.userManagement.user_registration.service;
import com.userManagement.user_registration.model.User;
import com.userManagement.user_registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Save user to Redis
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Retrieve user by ID from Redis
    public User getUserById(String id) {
        return userRepository.findById(id);
    }

    //Retrieve user by email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
