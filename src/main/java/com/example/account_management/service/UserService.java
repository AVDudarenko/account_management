package com.example.account_management.service;

import com.example.account_management.domain.entity.User;
import com.example.account_management.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user operations.
 * Provides functionality for creating and encoding user data.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new user with an encoded password and saves them to the repository.
     *
     * @param username    the username of the new user
     * @param rawPassword the raw password of the new user
     * @param role        the role of the new user (ADMIN or USER)
     * @return the created and saved User object
     */
    public User createUser(String username, String rawPassword, String role) {
        String encodedPassword = passwordEncoder.encode(rawPassword); // Encode the password
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setRole(role);
        return userRepository.save(user);
    }
}
