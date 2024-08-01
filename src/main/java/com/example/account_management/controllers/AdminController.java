package com.example.account_management.controllers;

import com.example.account_management.domain.entity.Account;
import com.example.account_management.exception.ResourceNotFoundException;
import com.example.account_management.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles administrative tasks such as viewing, blocking, and unblocking accounts.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Retrieves a list of all accounts.
     *
     * @return a list of accounts
     */
    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Blocks an account.
     *
     * @param accountId the ID of the account to block
     * @return the status of the operation
     */
    @PostMapping("/accounts/{accountId}/block")
    public ResponseEntity<?> blockAccount(@PathVariable Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        account.setBlocked(true);
        accountRepository.save(account);
        return ResponseEntity.ok().body("Account has been blocked");
    }

    /**
     * Unblocks an account.
     *
     * @param accountId the ID of the account to unblock
     * @return the status of the operation
     */
    @PostMapping("/accounts/{accountId}/unblock")
    public ResponseEntity<?> unblockAccount(@PathVariable Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        account.setBlocked(false);
        accountRepository.save(account);
        return ResponseEntity.ok().body("Account has been unblocked");
    }
}