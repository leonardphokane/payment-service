package com.example.payment.controller;

import com.example.payment.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionsPageController {
    private final PaymentService paymentService;

    public TransactionsPageController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/transactions")
    public String transactions(Model model) {
        model.addAttribute("transactions", paymentService.getAllTransactions());
        return "transactions";
    }
}
