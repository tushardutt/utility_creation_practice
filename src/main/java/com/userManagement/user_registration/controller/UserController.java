package com.userManagement.user_registration.controller;
import com.userManagement.user_registration.model.User;
import com.userManagement.user_registration.service.UserService;
import jakarta.validation.Valid;
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

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        // ✅ Check if the email is already registered
        User existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("Email is already registered: " + user.getEmail());
        }

        // ✅ Proceed to register the new user
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok("User created successfully with ID: " + savedUser.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found with ID: " + id);
        }
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully with ID: " + id);
    }
}
