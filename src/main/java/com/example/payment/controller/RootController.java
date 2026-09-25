package com.example.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        // Redirect root URL to login page
        return "redirect:/login";
        // Or: return "redirect:/dashboard"; if you prefer direct dashboard access
    }
}
