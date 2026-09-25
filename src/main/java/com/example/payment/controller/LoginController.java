package com.example.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        // This tells Spring to render login.html from src/main/resources/templates/
        return "login";
    }
}
