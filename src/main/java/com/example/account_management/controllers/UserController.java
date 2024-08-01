package com.example.account_management.controllers;

import com.example.account_management.domain.entity.Account;
import com.example.account_management.domain.entity.User;
import com.example.account_management.exception.ResourceNotFoundException;
import com.example.account_management.repo.AccountRepository;
import com.example.account_management.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

/**
 * Manages user account operations such as viewing balance, depositing, and withdrawing funds.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves the account details for the logged-in user.
     *
     * @param principal the authenticated user's principal
     * @return the account details of the user
     */
    @GetMapping("/account")
    public ResponseEntity<Account> getAccount(Principal principal) {
        // Find the user by username
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Find the account associated with the user
        Account account = accountRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        return ResponseEntity.ok(account);
    }

    /**
     * Deposits a specified amount into the logged-in user's account.
     *
     * @param amount    the amount to deposit
     * @param principal the authenticated user's principal
     * @return a response entity with the status of the operation
     */
    @PostMapping("/account/deposit")
    public ResponseEntity<?> deposit(@RequestParam BigDecimal amount, Principal principal) {
        // Find the user by username
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Find the account associated with the user
        Account account = accountRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        // Check if the account is blocked
        if (account.isBlocked()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Account is blocked");
        }

        // Update the account balance
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        return ResponseEntity.ok().body("Deposit successful");
    }

    /**
     * Withdraws a specified amount from the logged-in user's account.
     *
     * @param amount    the amount to withdraw
     * @param principal the authenticated user's principal
     * @return a response entity with the status of the operation
     */
    @PostMapping("/account/withdraw")
    public ResponseEntity<?> withdraw(@RequestParam BigDecimal amount, Principal principal) {
        // Find the user by username
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Find the account associated with the user
        Account account = accountRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        // Check if the account is blocked
        if (account.isBlocked()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Account is blocked");
        }

        // Check if the account has sufficient balance
        if (account.getBalance().compareTo(amount) < 0) {
            return ResponseEntity.badRequest().body("Insufficient funds");
        }

        // Update the account balance
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        return ResponseEntity.ok().body("Withdrawal successful");
    }
}