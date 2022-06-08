package com.example.task4.controller;

import com.example.task4.entity.User;
import com.example.task4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/login-page")
    public String loginPage() {
        return "login-page";
    }

    @GetMapping("/register-page")
    public String registerPage() {
        return "register-page";
    }

    private final UserService userService;

    @GetMapping
    public String managementPage(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "management-page";
    }
}
