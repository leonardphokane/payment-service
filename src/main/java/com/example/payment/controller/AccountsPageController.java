package com.example.payment.controller;

import com.example.payment.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountsPageController {
    private final AccountService accountService;

    public AccountsPageController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public String accounts(Model model) {
        model.addAttribute("title", "Accounts");
        model.addAttribute("accounts", accountService.findAll());
        return "accounts";
    }
}
