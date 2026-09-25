package com.example.payment.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String idempotencyKey;

    private Long sourceAccountId;
    private Long targetAccountId;
    private BigDecimal amount;
    private String currency;
    private String status;

    // Example helper to convert to DTO/response
    public com.example.payment.dto.PaymentResponse toResponse() {
        com.example.payment.dto.PaymentResponse response = new com.example.payment.dto.PaymentResponse();
        response.setTransactionId(this.id);
        response.setAmount(this.amount);
        response.setCurrency(this.currency);
        response.setStatus(this.status);
        return response;
    }
}
