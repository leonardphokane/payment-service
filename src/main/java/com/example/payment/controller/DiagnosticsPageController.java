package com.example.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map; // <-- add this import

@Controller
public class DiagnosticsPageController {
    @GetMapping("/diagnostics")
    public String diagnostics(Model model) {
        model.addAttribute("diagnostics", Map.of("status", "UP"));
        return "diagnostics";
    }
}
