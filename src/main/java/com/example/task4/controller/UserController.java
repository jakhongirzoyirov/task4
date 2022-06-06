package com.example.task4.controller;

import com.example.task4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/deleteUsers")
    public ResponseEntity<?> deleteUsers(@RequestBody List<Long> idList, HttpServletRequest request) {
        userService.deleteAllUsersById(idList, request);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/blockUsers")
    public ResponseEntity<?> blockUsers(@RequestBody List<Long> idList, HttpServletRequest request) {
        userService.blockUsers(idList, request);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/unblockUsers")
    public ResponseEntity<?> unblockUsers(@RequestBody List<Long> idList) {
        userService.unblockUsers(idList);
        return ResponseEntity.ok(null);
    }
}
