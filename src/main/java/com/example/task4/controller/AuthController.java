package com.example.task4.controller;

import com.example.task4.payload.RegisterDto;
import com.example.task4.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(RegisterDto registerDto) {
        authService.register(registerDto);
        return "redirect:/";
    }
}
