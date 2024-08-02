package com.example.account_management.repo;

import com.example.account_management.domain.entity.Account;
import com.example.account_management.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing Account data.
 * Provides methods for common database operations.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Finds an account by its associated user.
     *
     * @param user the user whose account is to be found
     * @return an Optional containing the found account, or empty if no account is found
     */
    Optional<Account> findByUser(User user);
}
