package com.example.payment.service;

import com.example.payment.entity.Account;
import com.example.payment.entity.Transaction;
import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;
import com.example.payment.exception.AccountNotFoundException;
import com.example.payment.exception.InsufficientFundsException;
import com.example.payment.repository.AccountRepository;
import com.example.payment.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentService {
    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;

    public PaymentService(TransactionRepository transactionRepo, AccountRepository accountRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    @Transactional
    public PaymentResponse processTransfer(String idempotencyKey, PaymentRequest request) {
        return transactionRepo.findByIdempotencyKey(idempotencyKey)
                .map(Transaction::toResponse)
                .orElseGet(() -> {
                    Account source = accountRepo.findById(request.getSourceAccountId())
                            .orElseThrow(AccountNotFoundException::new);
                    Account target = accountRepo.findById(request.getTargetAccountId())
                            .orElseThrow(AccountNotFoundException::new);

                    if (source.getBalance().compareTo(request.getAmount()) < 0) {
                        throw new InsufficientFundsException();
                    }

                    source.setBalance(source.getBalance().subtract(request.getAmount()));
                    target.setBalance(target.getBalance().add(request.getAmount()));
                    accountRepo.save(source);
                    accountRepo.save(target);

                    Transaction txn = new Transaction();
                    txn.setIdempotencyKey(idempotencyKey);
                    txn.setSourceAccountId(source.getId());
                    txn.setTargetAccountId(target.getId());
                    txn.setAmount(request.getAmount());
                    txn.setCurrency(request.getCurrency());
                    txn.setStatus("SUCCESS");
                    transactionRepo.save(txn);

                    return txn.toResponse();
                });
    }

    // ✅ Added helper methods for controllers
    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public void deleteTransaction(Long id) {
        transactionRepo.deleteById(id);
    }
}
