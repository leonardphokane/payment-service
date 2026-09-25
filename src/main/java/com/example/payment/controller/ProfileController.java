package com.example.payment.controller;

import com.example.payment.entity.Profile;
import com.example.payment.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Profile profile = profileService.findAll().stream().findFirst().orElse(null);
        model.addAttribute("title", "Profile");
        model.addAttribute("profile", profile);
        return "profile";
    }
}
