package com.example.jobmanager.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.jobmanager.entity.AppUser;
import com.example.jobmanager.repository.AppUserRepository;

@Controller
public class RegisterController {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(AppUserRepository appUserRepository,
                              PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(String username, String password) {

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        user.setEnabled(true);

        appUserRepository.save(user);

        return "redirect:/login";
    }
}