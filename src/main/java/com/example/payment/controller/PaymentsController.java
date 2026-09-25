package com.example.payment.controller;

import com.example.payment.service.PaymentService;
import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;
import com.example.payment.entity.Transaction;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentService paymentService;

    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/transfer")
    public PaymentResponse transfer(@RequestBody PaymentRequest request) {
        // Generate an idempotency key (could be UUID)
        String idempotencyKey = java.util.UUID.randomUUID().toString();
        return paymentService.processTransfer(idempotencyKey, request);
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return paymentService.getAllTransactions();
    }

    @DeleteMapping("/transactions/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        paymentService.deleteTransaction(id);
    }
}
