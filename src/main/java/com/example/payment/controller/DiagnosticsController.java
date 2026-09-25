package com.example.payment.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/diagnostics")
public class DiagnosticsController {
    @GetMapping
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}
