package com.example.account_management.repo;

import com.example.account_management.domain.entity.Account;
import com.example.account_management.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUser(User user);
}
