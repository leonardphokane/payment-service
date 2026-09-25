package com.example.payment.controller;

import com.example.payment.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentsPageController {
    private final PaymentService paymentService;

    public PaymentsPageController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public String payments(Model model) {
        model.addAttribute("title", "Payments");
        model.addAttribute("transactions", paymentService.getAllTransactions());
        return "payments";
    }
}
