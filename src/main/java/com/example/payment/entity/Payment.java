package com.example.payment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sourceAccountId;
    private Long targetAccountId;
    private Double amount;
    private String currency;
    private String status;
}
