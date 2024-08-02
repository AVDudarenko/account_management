package com.example.account_management.repo;

import com.example.account_management.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing User data.
 * Provides methods for common database operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to be found
     * @return an Optional containing the found user, or empty if no user is found
     */
    Optional<User> findByUsername(String username);
}
