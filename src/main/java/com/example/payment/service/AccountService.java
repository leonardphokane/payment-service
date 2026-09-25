package com.example.payment.service;

import com.example.payment.entity.Account;
import com.example.payment.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepo;

    public AccountService(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    public Optional<Account> findById(Long id) {
        return accountRepo.findById(id);
    }

    public Account save(Account account) {
        return accountRepo.save(account);
    }

    public void delete(Long id) {
        accountRepo.deleteById(id);
    }
}
