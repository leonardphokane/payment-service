package com.example.payment.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentResponse {
    private Long transactionId;
    private String status;
    private BigDecimal amount;
    private String currency;
}
